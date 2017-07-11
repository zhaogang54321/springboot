package com.turing.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.manage.entity.Student;
import com.turing.manage.mapper.StudentMapper;
import com.turing.manage.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper mapper;
	
	@Override
	public List<Student> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public Student queryOne(String id) {
		// TODO Auto-generated method stub
		return mapper.queryOne(id);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		mapper.update(student);
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		mapper.save(student);
	}

	@Override
	public void deleteOne(String id) {
		// TODO Auto-generated method stub
		mapper.deleteOne(id);
	}

}