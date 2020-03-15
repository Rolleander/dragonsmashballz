package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageNamek extends Stage{

	public StageNamek()
	{
		
	}
	
	public void paint(Graphics g)
	{	
		g.drawImage(GameImages.maps[2], 0,0,(int)(1000),(int)(600),null);		
	}
	

	public String getName() {
		
		return "Namek";
	}
	
public int getID() {
		
		return 1;
	}
}
