package com.example.whistle;



public class Game {
	
	String time,  date;
	String location,  description;
	
	Game(String time, String date, String location, String description  )
	{
		this.time = time;
		this.date = date;
		this.location = location;
		this.description = description;
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
	public String getDate()
	{
		return date;
	}

}
