package Battle;


import java.awt.Color;
import java.awt.Graphics;

import DBZ.Main;


public class BloodSplatter {

	private int x,y,time,design;
	private boolean end=false;
	private double winkel=0;
	private double blood[][]=new double[20][6];
	/*
	 * 0=x
	 * 1=y
	 * 2=size
	 */
	
	public BloodSplatter(int bx, int by,double grad, int art)
	{
	x=bx;
	y=by;
	time=19;
	winkel=grad;
	design=art;
	}
	
	public boolean enden()
	{
		return end;
	}
	
	public void paint(Graphics g)
	{
		if(end==false)
		{
	   
		if(time<20)
		{
			for(int i=0; i<20; i++)
			{
				blood[i][0]=x+((int)(Math.random()*time+1)-time/2)/2;
				blood[i][1]=y+((int)(Math.random()*time+1)-time/2)/2;
				blood[i][2]=2.5;
				blood[i][5]=(int)(Math.random()*50)+1;
			}
		
		}
		else
		{
			int art=0;
			if(design==2)
			{
				art=15;
			}
			
			for(int i=0; i<20-art; i++)
			{
				if(Main.PAINT)
				{
				switch(design)
				{
				case 0:	g.setColor(new Color((int) (255-blood[i][5]),(int)blood[i][2]*5,0)); break;
				case 1:	g.setColor(new Color((int) (200-blood[i][5]),0,(int)(255-blood[i][5]))); break;
				case 2:	g.setColor(new Color((int) (255-blood[i][5]),150,(int) (200-blood[i][5]))); break;
				}			
				g.fillOval((int)(blood[i][0]),(int)(blood[i][1]),(int)(blood[i][2]+art/3),(int)(blood[i][2]+art/3));
				}
				blood[i][0]+=blood[i][3];
			    blood[i][1]+=blood[i][4];
			
			    blood[i][4]+=0.05;
			    if(time%20==0)
			    {
			    	if(blood[i][2]<5)
			    	{
			     blood[i][2]+=0.5;	
			    	}
			    }
			    if(time==20)
				{
				  	blood[i][3]=((Math.random()*50+1)-25)/75+Math.cos(winkel)*3;
				  	blood[i][4]=((Math.random()*50+1)-25)/75+Math.sin(winkel)*2;
				}			
			  
			}
			
			
			
			if(blood[0][1]>700)
			{
			end=true;
			}
		}
		time++;
		}
	}
	
	
}
