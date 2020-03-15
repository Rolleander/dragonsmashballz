package Misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import DBZ.Main;
import DBZ.Sound;
import Images.GameImages;
import Menu.FighterSelection;
import Save.Profil;

public class Credits {

	private boolean run=false;
	private int y=0;
	
	private  ArrayList<String> credits=new ArrayList<String>();
	private  ArrayList<Integer> info=new ArrayList<Integer>();
	private Font fontsmall=new Font("Arial",1,25);
	private Font fontbig=new Font("Arial",Font.BOLD,50);
	
	public Credits()
	{
	
	read();
	}
	
	private void read()
	{
		
		 String zeile;
				try {
					 BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("DBZ/Ressourcen/Credits.txt")));//Textdatei mit Name name einlesen			 		   
						   while((zeile = reader.readLine())!= null){
				    	if(zeile.length()>0)
				    	{
				    	if( zeile.substring(0, 2).equals("b."))
				    	{
				    		zeile=zeile.substring(2);
				    		info.add(1); 
				    	}
				    	else
				    	{
				    		info.add(0);
				    	}
				    	}
				    	else
				    	{
				    		info.add(0);
				    	}
				    	credits.add(zeile);
				    
				    }
				     
				   }
			       catch (IOException f) 
			    {
			    System.err.println("Error2 :"+f);
			    }
				
				
	}
	
	public void show(Profil p){
		
		 credits=new ArrayList<String>();
		info=new ArrayList<Integer>();
		read();
		
		credits.add("");
		credits.add("Your Score: "+getScore(p));
		credits.add("");
		credits.add("-~-~-");
		info.add(0);
		info.add(0);
		info.add(0);
		info.add(0);
		
		run=true;
		
		y=credits.size()*50+650;
		Main.sound.playMusic(Sound.SONG_CREDITS);
	   Main.hideMouse(true);
	}
	
	private int getScore(Profil profil) {
		// TODO Auto-generated method stub
		int p=0;
		
		p+=profil.getFighterAmount()*200;
		if(profil.getFighterAmount()==Main.fighteranz)
		{
			p+=1000;
		}
		p+=profil.getSpielzeit()/60;
		p+=profil.getItemAmount()*50;
		if(profil.getItemAmount()==90)
		{
			p+=5000;
		}
		p+=profil.getStoryProgress()*100;
		int[][] stat=profil.getStatistic();
		for(int i=0; i<stat.length; i++)
		{
			p+=stat[i][0]*3;
			p+=stat[i][4]/20;
		}
	
		
		return p;
	}

	public void stop()
	{
	 run=false;	
     Main.hideMouse(false);
	}
	
	public boolean isRunning()
	{
		return run;
	}
	
	
	public void paint(Graphics g){
	  
	
		//g.setColor(Color.BLACK);
	 // g.fillRect(0,0,1000,600);
	  
	
	  
	  //Background
	  
	  
	  
	
	
	  int max=credits.size();
	  for(int i=0; i<max; i++)
	  {
		  Font font=null;
		  if(info.get(max-i-1)==1)
		  {
			  font=fontbig;
		  }
		  else
		  {
			  font=fontsmall;
		  }
		  g.setFont(font);
		  FontMetrics fm=g.getFontMetrics(font);
		  
		  String text=credits.get(max-i-1);
		  int ty=y+50-(i*50);
		  int tx=500-fm.stringWidth(text)/2;
		  
		  if(ty>-50&&ty<700)
		  {
			  int ii=max-i-1;
			
			  g.setColor(new Color(0,0,0));
			  g.drawString(text,tx-1,ty);
			  g.drawString(text,tx,ty-1);
			  g.drawString(text,tx-1,ty-1);
			  g.drawString(text,tx+1,ty);
			  g.drawString(text,tx,ty+1);
			  g.drawString(text,tx+1,ty+1);
			
			  
			  g.setColor(Color.WHITE);
			  g.drawString(text,tx,ty);
			  int a=ii/5-3;
			  if(a>25)
			  {
				  int a2=(a/25)*25;
				  a=(a-a2);
			  }
			  if(ii%5==0&&ii>10)
			  {
			  int fid=FighterSelection.fighters[ii/5-3];
			  
			  g.drawImage(GameImages.fighter[fid][a],tx-120,ty-75,null);
			  g.drawImage(GameImages.faces[fid],tx+fm.stringWidth(text)+50,ty-30,null);
			  }
		  }
		  
		  
	  }
	  
	  y--;
	  if(y<-350)
	  {
		  ZeniScreen.addZenis(100);
		  stop();
		  
	  }
	  
		
	}
	
}
