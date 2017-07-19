package com.turing.manage.mapper;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.turing.manage.entity.Student;

public interface StudentMapper {

	@Results(id = "studentResult", value = {
		@Result(property = "id",  column = "id", id = true),
		@Result(property = "name", column = "name"),
		@Result(property = "age", column = "age"),
		@Result(property = "lsh", column = "lsh",javaType=Date.class,jdbcType=JdbcType.TIMESTAMP)
	})
	@Select("select id, name, age, lsh from student order by lsh desc")
	public List<Student> queryAll();
	@Select("select id, name, age,lsh from student where id = #{id}")
	public Student queryOne(String id);
	@Update("update student set name=#{name},age=#{age} where id=#{id}")
	public int update(Student student);
	@Insert("insert into student(id,name,age) values(#{id},#{name},#{age})")
	public int save(Student student);
	@Delete("delete from student where id = #{id}")
	public int deleteOne(String id);
}
