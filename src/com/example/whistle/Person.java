package com.example.whistle;

public class Person {
	
	String userName,  location,  phoneNumber,  name;
	int id;
	
	public Person()
	{
		
	}
	public Person(String userName, String location, String phoneNumber, String name)
	{
		this.location = location;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	public String getLocation()
	{
		return location;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public void setName(String name)
	{
		this.name= name;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	public void setID(int id)
	{
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
