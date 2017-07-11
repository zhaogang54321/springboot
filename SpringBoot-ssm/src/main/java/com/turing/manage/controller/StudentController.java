package com.turing.manage.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.manage.entity.Student;
import com.turing.manage.service.IStudentService;
import com.turing.manage.util.MyRandom;

@Controller
@RequestMapping(value="/student" )
public class StudentController {

	/**
	 * 模型层
	 */
	@Autowired
	private IStudentService service;
	
	/**
	 * 查询
	 * @param mm
	 * @return
	 */
	@RequestMapping(value ="/query")
	public String query(ModelMap mm,
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int rows){

		PageHelper.startPage(page, rows);
		List<Student> list = service.queryAll();
		mm.put("list", list);
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
		Student student = service.queryOne(id);
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
		service.update(student);
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
		service.save(student);
		return "redirect:query.action";
	}
	/**
	 * 根据id，删除一条数据
	 * @param id 主键
	 * @return
	 */
	@GetMapping(value="/deleteOne/{id}")
	public String delete(@PathVariable("id")String id){
		service.deleteOne(id);
		return "redirect:/student/query.action";
	}
}
