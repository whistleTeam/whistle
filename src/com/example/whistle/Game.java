package com.example.whistle;



public class Game {
	
	String time,  date;
	String location,  description;
	int id;
	public Game()
	{
		
	}
	
	Game(String time, String date, String location, String description  )
	{
		this.time = time;
		this.date = date;
		this.location = location;
		this.description = description;
	}
	
	public String getDate()
	{
		return date;
	}
	public String getTime()
	{
		return time;
	}
	public String getLocation()
	{
		return location;
	}
	public String getDescription()
	{
		return description;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setTime(String time)
	{
		this.time= time;
	}
	public void setLocation(String location)
	{
		this.location =  location;
	}
	public void setDescription(String description)
	{
		this.description= description;
	}
	public void setDate(String date)
	{
		this.date = date;
	}

}
