package Settings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controlls.ControlSheet;
import Controlls.GamepadSheet;
import Controlls.KeyboardSheet;
import DBZ.Main;
import Images.GameImages;
import Menu.MenuData;

public class ControlMenu extends MenuData{

   private int width=200;
   private int height=300;
   
   private int selectsheet=0;
   private int select=-1;
   
   private int scrollpos=0;
   private ArrayList<String> padnames=new ArrayList<String>();
	private boolean firstshow=false;
	private boolean showpadnames=false;
   
	public ControlMenu()
	{

	}
	
	
	public void paint(Graphics g)
	{
		
		g.drawImage(GameImages.menuback,0,0,null);
		if(firstshow==false)
		{
			padnames=Main.getGamePadList();
	    	firstshow=true;	
	    	showpadnames=true;
		}
		this.paintTitle(g, "Assignment of keys");
	     ArrayList<ControlSheet> sheets = Main.controlsettings.getSheets();
	     int x=50;
	     int y=100;
	     
	     if(showpadnames)
	     {
             g.setColor(new Color(50,50,50));	    	 
	    	 g.fillRoundRect(195,95,610,410,30,30);
	    	 g.setColor(new Color(200,200,200));	    	 
	    	 g.fillRoundRect(200,100,600,400,30,30);
	    
	    	 g.setFont(font);
	     	 g.setColor(Color.BLACK);
	    	 g.drawString("Connected Controllers:",220,150);
	    	
	    	 g.setFont(font2);
	    	 int py=180;
	    	 if(padnames.size()==0)
	    	 {
	    		 g.setColor(Color.BLACK);
	    		 g.drawString("No Controllers connected",220,py+17);
	    	 }
	    	 else
	    	 {
	    	 for(String s: padnames)
	    	 {
	    		 g.setColor(new Color(150,150,150));
	    		 g.fillRoundRect(210,py,580,25,5,5);
	    		 g.setColor(Color.BLACK);
	    		 g.drawString(s,220,py+17);
	    		 py+=30;
	    	 }
	    	 }
	    	 g.setColor(Color.BLACK);
	    	 g.drawString("Click to close this information",220,485);
	    	 if(klick)
	    	 {
	    		 showpadnames=false;
	    	 }
	    	
	     }
	     else
	     {
	 
	     for(int i=0; i<4; i++)
	     {
	    	 int wahl=i+scrollpos;
	    	 if(wahl>-1&&wahl<sheets.size())
	    	 {
	    		 ControlSheet sheet=sheets.get(wahl);
		    	 g.setColor(new Color(50,50,50));
		    	 g.fillRoundRect(x-2,y-2,width+4,height+4,10,10);
		    	 g.setColor(new Color(200,200,200));
		    	 g.fillRoundRect(x,y,width,height,10,10);
		    	 if(sheet instanceof KeyboardSheet)
		    	 {
		    		 paintKeyboardSheet(g,x,y,wahl,(KeyboardSheet)sheet);
		    	 }
		    	 else{
		    		 paintGamepadSheet(g,x,y,wahl,(GamepadSheet)sheet);		    		 
		    	 }
		    	 x+=width+20;
	    	 }	 
	     }
	     
	     x=50;
	     y=450;
	     for(int i=0; i<sheets.size(); i++)
	     {
	    	 int id=0;
	    	 if(sheets.get(i) instanceof GamepadSheet)
	    	 {
	    		 id=1;
	    	 }
	    	 g.drawImage(GameImages.controllicons[id],x,y,null);
	    	 if(mx>=x&&mx<=x+40&&my>=y&&my<=y+40)
	    	 {
	    		 if(klick)
	    		 {
	    			 scrollpos=i;
	    		 }
	    	 }
	    	 if(i<scrollpos||i>scrollpos+3)
	    	 {
	    		 g.setColor(new Color(0,0,0,150));
	    		 g.fillRect(x,y,40,40);
	    	 }
	    	 x+=45;
	     }
	     
	    
	     String[] choice2={"Back","New Keyboard","New Gamepad"};
		 int wahl=paintMenuHorz(g,choice2,10,550,300,50);
		switch(wahl)
		{
		case 0:  exit=true; break;
		case 1: Main.controlsettings.newKeyboard(); break;
		case 2: Main.controlsettings.newGamepad(); break;
		}
		
	     }
	}


	private void paintGamepadSheet(Graphics g,int x, int y,int s, GamepadSheet sheet) {
		
		int[] keys=sheet.getButtons();
		g.drawImage(GameImages.gamepad,x-10,y+10,null);	
		g.drawImage(GameImages.subcharacter,x+180,y+10,20,-10,null);
		if(mx>=x+190&&mx<=x+200&&my>=y&&my<=y+10)
		{
			if(klick)
			{
			Main.controlsettings.deleteSheet(s);
			}
		}
		boolean active=sheet.isActive();
		int aid=0;
		if(!active)
		{
			aid=1;
		}
		g.drawImage(GameImages.active[aid],x,y,null);
		if(mx>=x&&mx<=x+20&&my>=y&&my<=y+20)
		{
			if(klick)
			{
		 Main.controlsettings.changeActivity(s, !active);
			}
		}
		g.setFont(font2);
		String name=sheet.getPadname();
		g.setColor(new Color(0,0,0));
		g.drawString("Name: ",x+5,y+270);
		g.setColor(new Color(0,0,150));
		g.drawString(name,x+5,y+285);
		g.setColor(new Color(150,150,150));
		if(mx>=x+78&&mx<=x+178&&my>=y+18&&my<=y+40)
		{
			g.setColor(new Color(100,100,100));
			if(klick)
			{
				Main.controlsettings.changeGamepad(s, getNextName(name));
			}
		}		
		g.fillRoundRect(x+78,y+18,100,22,5,5);
	
		g.setColor(new Color(0,0,0));
		g.drawString("Switch",x+100,y+34);
		g.drawImage(GameImages.padchooser,x+80,y+20,null);
		
		y+=30;
		
		int forplayer=sheet.getPlayer();
		
		
		for(int i=0; i<keys.length+1; i++)
		{
			y+=25;
			if(i==keys.length)
			{
				y+=10;
			}
				
			g.setColor(new Color(150,150,150));			
			Rectangle r=new Rectangle(x,y,width,22);
			if(r.inside(mx, my))
			{
				if(klick)
				{
					selectsheet=s;
					select=i;
				
				}
				g.setColor(new Color(100,100,100));
			}
			if(selectsheet==s)
			{
				if(select==i)
				{
					g.setColor(new Color(50,150,50));
					if(i<keys.length)
					{
						
					int k=Main.getGamePadButton(name);
					if(k>-1)
					{
					Main.controlsettings.editSheet(s, i, k);
					}
					
					}
				}
			}
			g.fillRect(x,y,width,22);
			g.setColor(Color.BLACK);
			if(i<keys.length)
			{
			g.drawString(ControlSettings.padnames[i]+" :",x+5,y+15);
			g.setColor(new Color(50,50,200));
		     String key=	"Button "+keys[i];
           if(keys[i]==-1)
           {
        	   key="-";
           }
			g.drawString(""+key,x+80,y+15);
			}
			else
			{
				g.drawImage(GameImages.multisteer[forplayer+1],x+90,y+2,null);				
				//Player wahl
				if(selectsheet==s)
				{
					if(select==i)
					{
				for(int h=-3; h<4; h++)
				{
					int pid=forplayer+h+1;
					if(pid<1)
					{
						pid=pid+10;
					}
					else if(pid>10)
					{
						pid=pid-10;
					}
					int xp=x+90+h*25;
					int yp=y+2;
					g.drawImage(GameImages.multisteer[pid],xp,yp,null);
					if(mx>=xp&&mx<=xp+20&&my>=yp&&my<=yp+20)
					{
						
						if(klick)
						{						
							Main.controlsettings.changePlayer(s,pid-1);
							klick=false;
						}
					}
				}
					}
				}
				
			
			}
		}
	
		
		
	}


	private String getNextName(String name) {
		// TODO Auto-generated method stub
		String s="-";	
		if(padnames.size()>0)
		{
		int dex=padnames.indexOf(name);
		dex++;
		if(dex>padnames.size())
		{
			dex=0;
		}
		s=padnames.get(dex);
		}
		return s;
	}


	private void paintKeyboardSheet(Graphics g,int x, int y,int s, KeyboardSheet sheet) {
		// TODO Auto-generated method stub
		
		int[] keys=sheet.getKeys();
		g.drawImage(GameImages.keyboard,x,y+10,null);	
		g.drawImage(GameImages.subcharacter,x+180,y+10,20,-10,null);
		if(mx>=x+190&&mx<=x+200&&my>=y&&my<=y+10)
		{
			if(klick)
			{
			Main.controlsettings.deleteSheet(s);
			}
		}
		boolean active=sheet.isActive();
		int aid=0;
		if(!active)
		{
			aid=1;
		}
		g.drawImage(GameImages.active[aid],x,y,null);
		if(mx>=x&&mx<=x+20&&my>=y&&my<=y+20)
		{
			if(klick)
			{
		 Main.controlsettings.changeActivity(s, !active);
			}
		}
		y+=30;
		g.setFont(font2);
		int forplayer=sheet.getPlayer();
		
		
		for(int i=0; i<keys.length+1; i++)
		{
			y+=25;
			if(i==keys.length)
			{
				y+=10;
			}
				
			g.setColor(new Color(150,150,150));			
			Rectangle r=new Rectangle(x,y,width,22);
			if(r.inside(mx, my))
			{
				if(klick)
				{
					selectsheet=s;
					select=i;
				
				}
				g.setColor(new Color(100,100,100));
			}
			if(selectsheet==s)
			{
				if(select==i)
				{
					g.setColor(new Color(50,150,50));
					if(i<keys.length)
					{
					int k=Main.getPressedKey();
					if(k>-1)
					{
					Main.controlsettings.editSheet(s, i, k);
					}
					}
				}
			}
			g.fillRect(x,y,width,22);
			g.setColor(Color.BLACK);
			if(i<keys.length)
			{
			g.drawString(ControlSettings.keynames[i]+" :",x+5,y+15);
			g.setColor(new Color(50,50,200));
			
		     String key=	KeyEvent.getKeyText(keys[i]);
		     int maxl=12;
		     if(keys[i]==-1)
		     {
		    	 key="-";
		     }
		     else
		     {
		     if(key.length()>maxl)
		     {
		    	 key=key.substring(0,maxl)+"...";
		     }
		     }
			g.drawString(key,x+80,y+15);
			}
			else
			{
				g.drawImage(GameImages.multisteer[forplayer+1],x+90,y+2,null);
				
				//Player wahl
				if(selectsheet==s)
				{
					if(select==i)
					{
				for(int h=-3; h<4; h++)
				{
					int pid=forplayer+h+1;
					if(pid<1)
					{
						pid=pid+10;
					}
					else if(pid>10)
					{
						pid=pid-10;
					}
					int xp=x+90+h*25;
					int yp=y+2;
					g.drawImage(GameImages.multisteer[pid],xp,yp,null);
					if(mx>=xp&&mx<=xp+20&&my>=yp&&my<=yp+20)
					{
						
						if(klick)
						{						
							Main.controlsettings.changePlayer(s,pid-1);
							klick=false;
						}
					}
				}
					}
				}
				
			
			}
		}
	
		
		
	}

    int gokux=-200;
    int gokux2=-200;
    int fps=Main.settings.getFPS();
    int newfps=-1;
    boolean dragged;
	public void paintFPS(Graphics g) {
		// TODO Auto-generated method stub
		newfps=-1;
		g.drawImage(GameImages.menuback,0,0,null);
		this.paintTitle(g, "Gamespeed");
		
	    g.setFont(font);
	    g.setColor(new Color(50,50,50));
	    g.fillRoundRect(195,115,650,160,20,20);
	    
	    g.setColor(new Color(200,200,200));
	    g.fillRoundRect(200,120,640,150,20,20);
	    
	    g.setColor(Color.BLACK);	    
	    g.drawString("FPS: "+fps,220,150);
	    g.setFont(font2);
	    g.drawString("Here you can adjust the frames-per-seconds if the game runs too slow or fast",220,180);
		g.setColor(new Color(100,100,100));
		g.fillRect(210,200,600,40);
		g.setColor(new Color(50,50,50));
		g.drawRect(210,200,600,40);
		for(int i=1; i<11; i++)
		{
			g.drawString(""+(i*10),205+i*60,260);
		}
		g.setColor(Color.BLACK);
		int reglerx=fps*6;
		g.fillRect(reglerx+205,200,10,41);
	    if(mx>=210&&mx<=810&&my>=200&&my<=240)
	    {
	    	if(klick||dragged)
	    	{
	    		fps=(mx-210)/6;
	    		if(fps<1)
	    		{
	    			fps=1;
	    		}
	    		if(fps>100)
	    		{
	    			fps=100;
	    		}
	    		newfps=fps;
	    	}
	    }
	   
	    g.setColor(new Color(150,150,150));
	    g.fillRect(0,295,1000,230);
	    g.setColor(new Color(50,50,50));
	    g.fillRect(0,300,1000,200);
	    
	    for(int i=0; i<25; i++)
	    {
	    	int x=gokux+i*50;
	    	 if(x>1000)
	    	 {
	    		 x-=1200;
	    	 }
	    	 Graphics2D g2d = (Graphics2D) g;
	    	  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, ((float)i/25f)));    	
	    	 g2d.drawImage(GameImages.fighter[2][4],x,250,200,200,null);
	    	
	    }
	    g.setColor(Color.BLACK);
        g.drawString("Running Test Graphics",400,520);
   	
	    g.drawImage(GameImages.fighter[3][2],gokux2,300,200,200,null);
	    g.setColor(Color.WHITE);
	    g.drawString(""+(int)(Math.random()*1000000+1),gokux2+20,420);
		gokux+=5;
		if(gokux>1000)
		{
			gokux=-200;
		}
		gokux2+=10;
		if(gokux2>1000)
		{
			gokux2=-200;
		}
		
		  String[] choice2={"Back"};
		 int wahl=paintMenuHorz(g,choice2,10,550,300,50);
		switch(wahl)
		{
		case 0:  exit=true; break;
		}
		
	     
	}


	public void setDragged(boolean dragged) {
		// TODO Auto-generated method stub
		this.dragged=dragged;
	}
	
	
}
