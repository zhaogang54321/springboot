package com.turing.manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.manage.entity.Student;
import com.turing.manage.service.IStudentService;
import com.turing.manage.util.BeanUtils;
import com.turing.manage.util.MyRandom;
import com.turing.redisConfig.RedisClient;

@Controller
@RequestMapping(value="/student" )
public class StudentController {

	/**
	 * 模型层
	 */
	@Autowired
	private IStudentService service;
	/**
	 * redis对象
	 */
	@Autowired
	private RedisClient redisClient;
	
	private final static String STUDENTZSET = "studentzset";	//存放学生主键的有序集合
	private final static String STUDENTHASH = "studenthash";	//存放学生具体信息的hash
	/**
	 * 查询
	 * @param mm
	 * @return
	 */
	@RequestMapping(value ="/query")
	public String query(ModelMap mm,
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int rows){
		List<Student> list = new ArrayList<Student>();
		boolean cache = false;	//假设redis缓存里没有数据
		if (redisClient != null) {
			cache = redisClient.exists(STUDENTZSET);	//判断redis是否有key值
			try {
				if (cache) {
					long start = (page-1)*rows;
					long end = page*rows-1;
					Set<String> rediszset = redisClient.zrevrange(STUDENTZSET, start, end);
					list = new Page();
					for (String id : rediszset) {
						Map<String, String> stuMap = redisClient.hgetall(STUDENTHASH+id);
						Student stu = BeanUtils.mapToBean(stuMap,Student.class);
						list.add(stu);
					}
					//===================封装分页对象
					long rowCount = redisClient.zcard(STUDENTZSET);
					Page p = (Page) list;
					p.setPageNum(page);	//当前页
					p.setPageSize(rows);//页面大小
					p.setPages( rowCount / rows + rowCount % rows == 0 ? 0 : 1  );//总页数
					p.setTotal(rowCount);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//如果连接不到redis，或者redis里没有数据
		if (redisClient==null || cache==false) {
			PageHelper.startPage(page, rows);
			list = service.queryAll(page,rows);
			
		}

		//存储分页信息
		mm.put("pageInfo", new PageInfo<Student>(list));
		mm.put("page", page);
		mm.put("rows", rows);
		
		return "student_query";
	}
	/**
	 * 转到修改页面
	 * @param id
	 * @param mm
	 * @return
	 */
	@GetMapping(value="/editpage/{id}")
	public String editpage(@PathVariable("id")String id,ModelMap mm){
		Student student=null;
		boolean cache = false;	//假设redis缓存里没有数据
		if (redisClient != null) {
			cache = redisClient.exists(STUDENTHASH+id);	//判断redis是否有key值
			if(cache){
				try {
					Map<String, String> map = redisClient.hgetall(STUDENTHASH+id);
					student = BeanUtils.mapToBean(map,Student.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//如果连接不到redis，或者redis里没有数据
		if (redisClient==null || cache==false) {
			student = service.queryOne(id);
		}
		mm.put("student", student);
		return "student_edit";
	}
	/**
	 * 修改保存
	 * @param student
	 * @return
	 */
	@PostMapping(value="/editsave")
	public String editsave(Student student){
		boolean cache = false;	//假设redis缓存里没有数据
		if (redisClient != null) {
			cache = redisClient.exists(STUDENTHASH+student.getId());
			if (cache) {
				try {
					//因为lsh是系统默认属性，修改时此值是不改的，固修改redis值时，需要从redis里先取出来
					Map<String, String> map = redisClient.hgetall(STUDENTHASH+student.getId());
					Student stu = BeanUtils.mapToBean(map, Student.class);
					org.springframework.beans.BeanUtils.copyProperties(student, stu, "lsh");
					//1，先改数据库
					int ge = service.update(student);
					//2，再改redis
					if (ge > 0) {
						redisClient.hmset(STUDENTHASH+student.getId(), BeanUtils.beanToMap(stu));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//如果连接不到redis，或者redis里没有数据
		if (redisClient==null || cache==false) {
			service.update(student);
		}
		return "redirect:query.action";
	}
	/**
	 * 转到添加页面
	 * @return
	 */
	@GetMapping(value="/addpage")
	public String addpage(){
		return "student_add";
	}
	/**
	 * 添加保存
	 * @param student
	 * @return
	 */
	@PostMapping(value="/addsave")
	public String addsave(Student student){
		student.setId( MyRandom.getValue(6)  );
		//1，添加数据到数据库中
		int ge = service.save(student);
		if (redisClient != null && ge > 0) {
			//2，查询数据，得到完整的student对象
			Student stu = service.queryOne(student.getId());
			try {
				//3，添加数据到哈希中
				redisClient.hmset(STUDENTHASH+stu.getId(), BeanUtils.beanToMap(stu));
				//4，添加数据到zset中
				redisClient.zadd(STUDENTZSET, Double.parseDouble(String.valueOf( stu.getLsh().getTime() )), stu.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:query.action";
	}
	/**
	 * 根据id，删除一条数据
	 * @param id 主键
	 * @return
	 */
	@GetMapping(value="/deleteOne/{id}")
	public String delete(@PathVariable("id")String id){
		//1，先删除数据库
		int ge = service.deleteOne(id);
		boolean cache = false;	//假设redis缓存里没有数据
		if (redisClient != null && ge > 0) {
			cache = redisClient.exists(STUDENTHASH+id);
			if (cache) {
				try {
					//2，再删除redis里的zset集合好hash
					redisClient.del(STUDENTHASH+id);
					redisClient.zrem(STUDENTZSET, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return "redirect:/student/query.action";
	}
}
