package Achievements;

import java.io.Serializable;

public class Task implements Serializable{

	
    public  String name,text;
    public  boolean finished=false;
    public  boolean hidden=false;
    public  int points,category;
	 
     
	public Task(String name, String text,int points, boolean hidden, int category)	
	{
		this.name=name;
		this.text=text;
		this.hidden=hidden;
		this.points=points;
		this.category=category;
	}
	
	public Task(String name, String text,int points,int category)	
	{
		this.name=name;
		this.text=text;
		this.points=points;
		this.category=category;
	}
	
}
