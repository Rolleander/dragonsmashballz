package Story;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import DBZ.Main;
import Images.GameImages;

public class Zuschauer {

	  private   ArrayList<Integer> zuschauer=new ArrayList<Integer>() ;
	  private   ArrayList<Boolean> zuschauerplatz=new  ArrayList<Boolean>();
	  
	  private   ArrayList<Integer> ypos=new  ArrayList<Integer>();
	  private float winkel;
	  private Dimension dim=new Dimension(150,150);
	
	public Zuschauer()
	{
		
	}
	
	public void addZuschauer(int nr, boolean platz)
	{
		zuschauer.add(nr);
		zuschauerplatz.add(platz);
		ypos.add((int)Math.random()*100+1+200);
	}
	
	public ArrayList<Integer> getZuschauer()
	{
		return zuschauer;
	}
	
	public ArrayList<Boolean> getZuschauerPlatz()
	{
		return zuschauerplatz;
	}

	public void paint(Graphics g) {
		
		winkel+=2;
		
		int lx=20;
		int rx=850;
		
		for(int i=0; i<zuschauer.size(); i++)
		{
			
			int x=0;
			int y=ypos.get(i)+(int)(Math.sin(Math.toRadians(winkel+i*30))*7+Math.cos(Math.toRadians(winkel+i*30))*7);
			if(zuschauerplatz.get(i))
			{//links schauer
				
				x=rx;
				g.drawImage(GameImages.fighter[zuschauer.get(i)][4],x+dim.width,y,(int)(-dim.width),(int)(dim.height),null);
				rx-=80;
			}
			else
			{// rechts schauer
				
			x=lx;
			g.drawImage(GameImages.fighter[zuschauer.get(i)][4],x,y,(int)(dim.width),(int)(dim.height),null);	
			lx+=80;
			}
			
		}
		
	}
	
	
}
