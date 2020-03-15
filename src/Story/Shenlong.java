package Story;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;
import Menu.MenuData;
import Save.Profil;

public class Shenlong extends MenuData{
	
	Wunsch[] wunsch=new Wunsch[4];
	
	float w=0;
	private int wait=0,fertig=-1;
	
	
	public Shenlong(Profil p)
	{
		wunsch[0]=new Wunsch("99.999 Zeni",Wunsch.ART_GELD,99999);
	    
		
		
	}
	
	public Wunsch paint(Graphics g)
	{
		Wunsch wu=null;
		int wahl=-1;
		w+=1;
		  int gy=(int)(Math.sin(Math.toRadians(w))*8+Math.cos(Math.toRadians(w))*8);
		  for(int i=0; i<120; i++)
		  {
			  if(i%2==0)
			  {
				  g.setColor(new Color(150,0,0));
			  }
			  else
			  {
				  g.setColor(new Color(200,100,00));
			  }
		
		  
		  int[] y=new int[4];
		  int[] x=new int[4];
		int r=800;
		
			  x[0]=500;
			  y[0]=542;
			  x[1]=500+(int)(Math.cos(Math.toRadians(i*3+w/3))*r);
			  y[1]=542+(int)(Math.sin(Math.toRadians(i*3+w/3))*r);
			  x[2]=500+(int)(Math.cos(Math.toRadians(i*3+3+w/3))*r);
			  y[2]=542+(int)(Math.sin(Math.toRadians(i*3+3+w/3))*r);
			  x[3]=500;
			  y[3]=542;	
	
			  
		g.fillPolygon(x, y, 4);
		  }
		  g.drawImage(GameImages.maps[5],244,350+gy/2,null);
		   g.drawImage(GameImages.shenlong,150,gy+20,null);
		   if(fertig==-1)
		   {
		   int x=100;
		   int y=400;
		   g.drawImage(GameImages.cursor[0],x,y,800,60,null);
		   g.setFont(font);
		 
		   g.setColor(Color.BLACK);
		   FontMetrics fm=g.getFontMetrics(font);
		   
		   String t="Tell me your wish and it will be granted!";
		   g.drawString(t, x+400-fm.stringWidth(t)/2, y+40);
		   
		   String[] wuensche={"-","-","-","-"};
		 for(int i=0; i<4; i++)
		 {
			 if(wunsch[i]!=null)
			 {
				 wuensche[i]=wunsch[i].getName();
			 }
		 }
		   
		   String[] t1={wuensche[0],wuensche[2]};
		  int m1= this.paintMenu(g, t1, x, y+70, 390, 50);
		   String[] t2={wuensche[1],wuensche[3]};
		  int m2= this.paintMenu(g, t2, x+410, y+70, 390, 50);
		  if(m1>-1||m2>-1)
		  {
			  switch(m1)
			  {
			  case 0: wahl=0; break;
			  case 1: wahl=2; break;
			  }
			  switch(m2)
			  {
			  case 0: wahl=1; break;
			  case 1: wahl=3; break;
			  }
			  if(wunsch[wahl]!=null)
			  {
			  fertig=wahl;
			  wahl=-1;
			  wait=150;
			  Main.sound.playSound(23, true);
			  }
		  }
		  }
		   else
		  {
			  if(wait>0)
			  {
				  wait--;
			  }
			  else
			  {
				 wu=wunsch[fertig];
			  }
		  }
		  
		  
		return wu;
		
	}

}
