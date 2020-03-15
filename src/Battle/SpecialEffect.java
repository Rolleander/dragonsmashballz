package Battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class SpecialEffect {

	private int mp=20;
	private boolean[] special=new boolean[mp];
	
	private int[][] srcpos=new int[mp][2];
	private int[][] hitpos=new int[mp][2];
	
	private int[] wave;
	private int[] winkel;
   private int winkelplus=5;
	
	private Font font=new Font("Arial",1,30);
   
	public SpecialEffect()
	{
		  wave=new int[special.length];
		  winkel=new int[special.length];
		for(int i=0; i<special.length; i++)
		{
			wave[i]=0;
			winkel[i]=30;
		}
	}
	
	public void setSpecial(boolean s, int id)
	{
		special[id]=s;
	}
	
	
    public void reset()
    {
    	for(int i=0; i<special.length; i++)
		{
			special[i]=false;
			
		}
    }
	
	public void setSpecial(boolean s)
	{
		for(int i=0; i<special.length; i++)
		{
			special[i]=s;
		}
	}
	
	public void setSrcPos(int[] pos, int id)
	{
		srcpos[id]=pos;
	}
	
	public void setHitPos(int[] pos, int id)
	{
		hitpos[id]=pos;
	}

	
	public void paint(Graphics g)
	{
		if(Main.PAINT)
		{
		for(int i=0; i<special.length; i++)
		{
			
			if(special[i])
			{
				drawRadialWave(g,srcpos[i][0],srcpos[i][1],i);
				drawRadialLines(g,hitpos[i][0],hitpos[i][1],i);			

			}
		}
		}
	}
	

	private void drawRadialWave(Graphics g, int x, int y, int id)
	{
		wave[id]+=10;
		if(wave[id]>150)
		{
			wave[id]=10;
		}
	
		
		
			int s=wave[id];
		g.drawImage(GameImages.effects[54],x-s/2,y-s/2,s,s,null);
		
		
	}
	
	
	
	private void drawRadialLines(Graphics g,int x, int y, int id)
	{
		int c=(int)(Math.random()*255+1)-1;
		g.setColor(new Color(c,c,c));
		int i=0;
		int r=1000;
		for(int h=0; h<10; h++)
		{
		    
			i+=winkel[id];
			int[] sx=new int[5];
			int[] sy=new int[5];
			 sx[0]=(int) (Math.cos(Math.toRadians(i))*50)+x;
			sy[0]=(int) (Math.sin(Math.toRadians(i))*50)+y;
			 sx[2]=(int) (Math.cos(Math.toRadians(i))*r)+x;
			sy[2]=(int) (Math.sin(Math.toRadians(i))*r)+y;		
			i+=winkelplus;
			 sx[1]=(int) (Math.cos(Math.toRadians(i))*50)+x;
				sy[1]=(int) (Math.sin(Math.toRadians(i))*50)+y;
				 sx[3]=(int) (Math.cos(Math.toRadians(i-winkelplus/2))*1.5*r)+x;
					sy[3]=(int) (Math.sin(Math.toRadians(i-winkelplus/2))*1.5*r)+y;
				 sx[4]=(int) (Math.cos(Math.toRadians(i))*r)+x;
				sy[4]=(int) (Math.sin(Math.toRadians(i))*r)+y;
			g.fillPolygon(sx, sy, 5);
		}
	
		winkel[id]++;
		if(winkel[id]>60)
		{
			winkel[id]=30;
		}
	
	}
	
	

}
