package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageKame extends Stage{

	public StageKame()
	{
		
	}
	
	public void paint(Graphics g)
	{	
		g.drawImage(GameImages.maps[3], 0,0,(int)(1000),(int)(600),null);		
	}
	

	public String getName() {
		
		return "Kame House";
	}
	
public int getID() {
		
		return 2;
	}
}
