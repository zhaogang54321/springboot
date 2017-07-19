package com.turing.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.manage.entity.Student;
import com.turing.manage.mapper.StudentMapper;
import com.turing.manage.service.IStudentService;
import com.turing.manage.util.BeanUtils;
import com.turing.redisConfig.RedisClient;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper mapper;
	
	@Override
	public List<Student> queryAll(int page, int rows) {
		return mapper.queryAll();
	}

	@Override
	public Student queryOne(String id) {
		return mapper.queryOne(id);
	}

	@Override
	@Transactional
	public int update(Student student) {
		return mapper.update(student); 
	}

	@Override
	@Transactional
	public int save(Student student) {
		return	mapper.save(student); 
	}

	@Override
	@Transactional
	public int deleteOne(String id) {
		return mapper.deleteOne(id);
	}

}
