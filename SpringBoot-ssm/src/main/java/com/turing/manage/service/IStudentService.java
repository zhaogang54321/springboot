package com.turing.manage.service;

import java.util.List;

import com.turing.manage.entity.Student;

public interface IStudentService {
	/**
	 * 查询所有学生数据
	 * @return
	 */
	List<Student> queryAll(int page, int rows);
	/**
	 * 根据主键查询一个对象
	 * @param id 主键
	 * @return 对象
	 */
	Student queryOne(String id);
	/**
	 * 修改保存
	 * @param student
	 */
	int update(Student student);
	/**
	 * 添加保存
	 * @param student
	 */
	int save(Student student);
	/**
	 * 根据主键删除一条数据
	 * @param id
	 */
	int deleteOne(String id);

}
