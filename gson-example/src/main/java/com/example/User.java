package com.example;

import com.google.gson.annotations.SerializedName;

public class User {
	private String id;
	@SerializedName(value = "firstName", alternate = {"first_name"})
	private String firstName;
	@SerializedName(value = "lastName", alternate = {"last_name"})
	private String lastName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
    
}
