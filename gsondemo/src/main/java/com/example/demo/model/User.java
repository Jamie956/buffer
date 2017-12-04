package com.example.demo.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public String name;
    public int age;
    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    public String emailAddress;
	public String getName() {
		return name;
	}
	public User() {}
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public User(String name, int age, String emailAddress) {
		this.name = name;
		this.age = age;
		this.emailAddress = emailAddress;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", emailAddress=" + emailAddress + "]";
	}
}
