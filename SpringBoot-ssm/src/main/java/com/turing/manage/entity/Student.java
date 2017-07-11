package com.turing.manage.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Student implements Serializable{

	private String id;
	private String name;
	private Integer age;
	private Date lsh;
	
	public Date getLsh() {
		return lsh;
	}
	public void setLsh(Date lsh) {
		this.lsh = lsh;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
