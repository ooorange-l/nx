package com.nx.domain;

public class User {
	private String phone;
	private String name;//姓名
	private int sex;//性别（1男、0女）
	private String number;//学号
	private String grade;//班级
	private String qq;
	//方向（前端，后端，产品，运营,视觉）
	private String directed;
	//面试状态1~8（1.一面未开始，2.一面通知，3.一面开始，4.一面结果通知，5.二面未开始，6.二面通知，7.二面开始，8.二面结果通知）
	private int status;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDirected() {
		return directed;
	}
	public void setDirected(String directed) {
		this.directed = directed;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

	
}
