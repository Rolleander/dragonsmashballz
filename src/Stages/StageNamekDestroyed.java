package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageNamekDestroyed extends Stage{

	public StageNamekDestroyed()
	{
		floor=true;
	}
	
	public void paint(Graphics g)
	{	
		g.drawImage(GameImages.maps[11], 0,0,(int)(1000),(int)(600),null);		
	}
	

	public String getName() {
		
		return "Dying Namek";
	}
	
public int getID() {
		
		return 11;
	}
}
