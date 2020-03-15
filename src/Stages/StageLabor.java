package Stages;

import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;

public class StageLabor extends Stage {

	public StageLabor()
	{
		floor=false;
	}
	
	public void paint(Graphics g)
	{	
		g.drawImage(GameImages.maps[12], 0,0,(int)(1000),(int)(600),null);		
	}
	

	public String getName() {
		
		return "Geros Laboratory";
	}
	
public int getID() {
		
		return 12;
	}
	
}
