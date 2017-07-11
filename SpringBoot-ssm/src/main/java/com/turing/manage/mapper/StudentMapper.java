package com.turing.manage.mapper;

import java.util.List;

import javax.ws.rs.DELETE;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.turing.manage.entity.Student;

public interface StudentMapper {

	@Select("select id, name, age from student order by lsh desc")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age"),
        @Result(property = "lsh", column = "lsh")
    })
	public List<Student> queryAll();
	@Select("select id, name, age from student where id = #{id}")
	public Student queryOne(String id);
	@Update("update student set name=#{name},age=#{age} where id=#{id}")
	public void update(Student student);
	@Insert("insert into student(id,name,age) values(#{id},#{name},#{age})")
	public void save(Student student);
	@Delete("delete from student where id = #{id}")
	public void deleteOne(String id);
}
