package Menu;

import java.awt.Color;
import java.awt.Graphics;

import DBZ.Main;
import DBZ.Sound;
import Images.GameImages;
import Stages.Cloud;
import Stages.CloudStage;

public class Intro {

	CloudStage clouds=new CloudStage(10);
    private StartScreen screen=new StartScreen();

	public Intro()
	{
		
	}
	
	private int x=0;
	private float w=0;
	
	
	private int enid=-1,enx=0,eny=0;
	
	
	public StartScreen onStartScreen()
	{
		return screen;
	}
	
	public void paint(Graphics g)
	{
		if(screen!=null)
		{
			screen.paint(g);
			if(screen.finish())
			{
				screen=null;
				 Main.sound.playMusic(Sound.SONG_INTRO);
			}
		}
		else
		{
		
	int speed=10;
		g.setColor(new Color(150,150,250));
		g.fillRect( 0,0,(int)(1000),(int)(600));
		
		clouds.paintBackgroundClouds(g);
	  clouds.moveClouds(speed, 0);
		x+=speed;
		
		
		
		
		
		for(int i=-1; i<2; i++)
		{
			
			
			g.drawImage(GameImages.introback,i*846+x,0,null);
		}
		
	
		
		if(enid!=-1)
		{
			enx+=3;
			int ey=(int)(eny+(Math.sin(Math.toRadians(w+111))*70));
			g.drawImage(GameImages.fighter[enid][2],enx+150,ey,-150,150,null);
			if(enx>1050)
			{
				enid=-1;
			}
		}
		else
		{
			
			if((int)(Math.random()*150+1)==1)
			{
				enid=(int)(Math.random()*49+1)-1;
				enx=-150;
				eny=(int)(Math.random()*400+1);
			}
		}
		
		
		if(x>846)
		{
			x=0;
		}
		
		w+=1;
		int x=(int)(400+(Math.cos(Math.toRadians(w+90))*50));
		int y=(int)(135+(Math.sin(Math.toRadians(w+90))*100));
		g.drawImage(GameImages.effects[61],x-50,y+60,700,80,null);
		g.drawImage(GameImages.fighter[1][3],x,y,-150,150,null);
	
		 x=(int)(500+(Math.cos(Math.toRadians(w+270))*50));
		 y=(int)(185+(Math.sin(Math.toRadians(w+270))*100));
		g.drawImage(GameImages.effects[60],x-50,y+50,600,100,null);
		g.drawImage(GameImages.fighter[7][3],x,y,-150,150,null);
	
		
		
		 x=(int)(750+(Math.cos(Math.toRadians(w))*50));
		 y=(int)(155+(Math.sin(Math.toRadians(w))*100));
		g.drawImage(GameImages.effects[60],x-50,y+50,350,100,null);
		g.drawImage(GameImages.fighter[4][3],x,y,-150,150,null);
		
		
		 x=(int)(850+(Math.cos(Math.toRadians(w+180))*50));
		 y=(int)(225+(Math.sin(Math.toRadians(w+180))*100));
		g.drawImage(GameImages.effects[60],x-50,y+50,350,100,null);
		g.drawImage(GameImages.fighter[3][3],x,y,-150,150,null);
	
		
	
		g.drawImage(GameImages.logo,200,20,null);
		
		g.setColor(Color.WHITE);
		g.drawString("PRESS MOUSE TO CONTINUE",270,580);
	}
	}
	

}
