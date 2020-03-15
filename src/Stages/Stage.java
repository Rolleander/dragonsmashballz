package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import Fight.Fighter;



public abstract class Stage  {

	protected CloudStage clouds;
	protected boolean floor=true;
	
	protected Dimension dimension=new Dimension(1000,600);
	
	public Stage()
	{
		
	}
	
	public Dimension getDimension()
	{
		return dimension;
	}
	
	public void paintStage(Graphics g)
	{
		paint(g);
		
	}
	

	
	public abstract void paint(Graphics g);
	
	public abstract String getName();

	public abstract int getID();
	
	public boolean haveFloor()
	{
		return floor;
	}

	//Paint Shadows
	public void paintOverworld(Graphics g, Fighter[] player) {
		// TODO Auto-generated method stub
		
		if(floor) 
		{
		for(int i=0; i<player.length; i++)
		{
			if(player[i]!=null)
			{
		     if(player[i].fliegtEin()==false)
		     {
			    int x=player[i].getPos()[0];
			    int y=player[i].getPos()[1]-(dimension.height-600);
			    if(y>0)
			    {
			    int size=y/4;
			    if(size+50<=255&&size+50>0)
			    {
			    g.setColor(new Color(0,0,0,size+50));
			  
			    	g.fillOval(x-size/2,dimension.height-70,size,size/5);
			    }
			    }
		     }
			}
		}
		}
	}
}
