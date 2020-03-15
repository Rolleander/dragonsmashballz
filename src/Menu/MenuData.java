package Menu;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import DBZ.Main;
import Images.GameImages;


public abstract class MenuData {

	protected Font font=new Font("Arial",1,30);
	protected Font font2=new Font("Arial",1,15);
	protected Font font3=new Font("Arial",1,17);
	
	protected int  mx,my,klickn=0;
	protected boolean open=true,go=false,klick=false,exit=false;
	
	 protected void paintTitle(Graphics g,String title)
	   {
		   g.setColor(new Color(150,150,150));
		   g.drawImage(GameImages.cursor[0],0,0,(int)(1000),(int)(80),null);
		   g.setFont(font);
		   g.setColor(new Color(0,0,0));
		   FontMetrics fm=g.getFontMetrics(font);
		
		   g.drawString(title,(int)(500-fm.stringWidth(title)/2),(int)(50));
	   }
	 
	 protected int mainMenuSelection()
	 {
		 int wahl=-1;
			int x=mx;
			int y=my;
			
	     //exit
	     if(inBereich(x,y,13,540,192,594))
	     {
	    	 wahl=5;
	     }
	     if(inBereich(x,y,64,250,224,350))
	     {
	    	 wahl=0;
	     }
	     if(inBereich(x,y,265,250,425,350))
	     {
	    	 wahl=1;
	     }
	     if(inBereich(x,y,478,250,635,350))
	     {
	    	 wahl=2;
	     }
	     if(inBereich(x,y,386,400,540,500))
	     {
	    	 wahl=3;
	     }
	     if(inBereich(x,y,575,400,730,500))
	     {
	    	 wahl=4;
	     }
	     
		 return wahl;
	 }
	 
	 
	 
	 protected boolean inBereich(int mx, int my,int x, int y, int b, int h)
	 {
		if(mx>=x&&mx<=b&&my>=y&&my<=h)
		{
			return true;
		}
		else
		{
			return false;
		}		
	 }

	   protected int paintMenu(Graphics g, String[] menu, int xp, int yp, int width, int height)
	   {
		   int choice=-1;
		   g.setFont(font);
		
		   for(int i=0; i<menu.length; i++)
		   {
				int x=mx;
				int y=my;
				  
				if(x>xp&&x<xp+width&&y>(yp+i*60)&&y<(yp+height+i*60))
				{
					   g.drawImage(GameImages.cursor[1], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
						   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   Main.sound.menuSound();
						   choice=i;
					   }
				}
				else
				{
					   g.drawImage(GameImages.cursor[0], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
						   g.setColor(new Color(50,50,50));
				}				
			
			   g.drawString(menu[i], (int)((xp+width/10)),(int)((yp+35+i*60)) );
		 
		   }
		   return choice;
	   }
	   
	   protected int paintMenuHorz(Graphics g, String[] menu, int xp, int yp, int width, int height)
	   {
		   int choice=-1;
		   g.setFont(font);
		
		   for(int i=0; i<menu.length; i++)
		   {
				int x=mx;
				int y=my;
				  
				if(x>xp+i*(width+20)&&x<xp+width+i*(width+20)&&y>(yp)&&y<(yp+height))
				{
					   g.drawImage(GameImages.cursor[1], (int)(xp+i*(width+20)),(int)((yp)) ,(int)(width),(int)(height),null);
						   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   Main.sound.menuSound();
						   choice=i;
					   }
				}
				else
				{
					   g.drawImage(GameImages.cursor[0], (int)(xp+i*(width+20)),(int)((yp)) ,(int)(width),(int)(height),null);
						   g.setColor(new Color(50,50,50));
				}				
			
			   g.drawString(menu[i], (int)((xp+width/10+i*(width+20))),(int)((yp+35)) );
		 
		   }
		   return choice;
	   }
	   
	   protected int paintLockedMenu(Graphics g, String[] menu,boolean[] locked, int xp, int yp, int width, int height)
	   {
		   int choice=-1;
		   g.setFont(font);
		
		   for(int i=0; i<menu.length; i++)
		   {
				int x=mx;
				int y=my;
				if(locked[i]==false)
				{
				if(x>xp&&x<xp+width&&y>(yp+i*60)&&y<(yp+height+i*60))
				{
					   g.drawImage(GameImages.cursor[1], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
						
					   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   Main.sound.menuSound();
						   choice=i;
					   }
				}
				else
				{
					   g.drawImage(GameImages.cursor[0], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
						
					   g.setColor(new Color(50,50,50));
				}		
				}
				else
				{
					   g.drawImage(GameImages.cursor[2], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
						
					   g.setColor(new Color(255,0,0));
				}
			
			   g.drawString(menu[i], (int)((xp+width/10)),(int)((yp+35+i*60)) );
				  
		   }
		   return choice;
	   }
	   
	   protected int paintHiddenMenu(Graphics g, String[] menu,boolean[] locked, int xp, int yp, int width, int height)
	   {
		   int choice=-1;
		   g.setFont(font);
		
		   for(int i=0; i<menu.length; i++)
		   {
				int x=mx;
				int y=my;
				if(locked[i]==false)
				{
				if(x>xp&&x<xp+width&&y>(yp+i*60)&&y<(yp+height+i*60))
				{
					   g.drawImage(GameImages.cursor[1], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
					   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   Main.sound.menuSound();
						   choice=i;
					   }
				}
				else
				{
					   g.drawImage(GameImages.cursor[0], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
					   g.setColor(new Color(50,50,50));
				}	
				
				   g.drawString(menu[i], (int)((xp+width/10)),(int)((yp+35+i*60)) );
				
				}
		   }
		   return choice;
	   }
	   
	   protected int paintSMenu(Graphics g, String[] menu, int xp, int yp, int width, int height, int wahl)
	   {
		   int choice=-1;
		   g.setFont(font2);
		
		   for(int i=0; i<menu.length; i++)
		   {
				Graphics2D g2d = (Graphics2D) g;
				int x=mx;
				int y=my;
				
				if(x>xp&&x<xp+width&&y>(yp+i*60)&&y<(yp+height+i*60))
				{
					  g.drawImage(GameImages.cursor[1], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
					   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   Main.sound.menuSound();
						   choice=i;
					   }
				}
				else if(wahl==i)
				{
					  g.drawImage(GameImages.cursor[1], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
					   g.setColor(new Color(0,0,0));
				}
				else
				{
					  g.drawImage(GameImages.cursor[0], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
					   g.setColor(new Color(50,50,50));
				}				
			 
		
			   g.drawString(menu[i], (int)((xp+width/10)),(int)((yp+35+i*60)) );
				  
		   }
		   return choice;
	   }
	   
	   protected void paintMenu(Graphics g, String[] menu, int xp, int yp, int width, int height,int select)
	   {
		   int choice=-1;
		   g.setFont(font);
			
		   for(int i=0; i<menu.length; i++)
		   {
				
			
				if(i==select)
				{
					  g.drawImage(GameImages.cursor[1], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
					   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   choice=i;
					   }
				}
				else
				{
					if(i<select)
					{
						  g.drawImage(GameImages.cursor[0], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
						  g.setColor(new Color(50,50,50));
				  
					}
					else 
					{
						  g.drawImage(GameImages.cursor[2], (int)(xp),(int)((yp+i*60)) ,(int)(width),(int)(height),null);
							g.setColor(new Color(100,100,100));
					}
					 
				}	
			
				 g.drawString(menu[i], (int)((xp+width/10)),(int)((yp+35+i*60)) );
		   }
		 
	   }
	   
	   protected int paintMainMenu(Graphics g, String[] menu, int xp, int yp)
	   {
		   int choice=-1;
		   g.setFont(font);
		int width=472;
		int height=55;
		
		int x=mx;
		int y=my;
		   for(int i=0; i<menu.length; i++)
		   {
				
				  
				if(x>xp&&x<xp+width&&y>yp&&y<yp+height)
				{
					   g.drawImage(GameImages.titlemenucursors[1], (int)(xp),(int)yp ,(int)(width),(int)(height),null);
						
					   g.setColor(new Color(0,0,0));
					   if(klick)
					   {
						   Main.sound.menuSound();
						   choice=i;
					   }
				}
				else
				{
					   g.drawImage(GameImages.titlemenucursors[0], (int)(xp),yp ,(int)(width),(int)(height),null);
						
					   g.setColor(new Color(255,255,255));
				}				
			
			   g.drawString(menu[i], (int)((xp+75)),yp+37 );
				  
			   yp+=70;
			   xp+=25;
		   }
		   return choice;
	   }
	   
	   protected boolean paintButton(Graphics g, int sx, int sy,int width, int height, String title)
		{
		   boolean select=false;
			int x=mx;
			int y=my;
		
		   if(x>sx&&x<sx+width&&y>sy&&y<sy+height)
		   {
			   g.drawImage(GameImages.cursor[1], sx,sy ,width,height,null);
			   g.setColor(new Color(0,0,0));
		         select=true;
		   }
		   else
		   {
			   g.drawImage(GameImages.cursor[0], sx,sy ,width,height,null);
			   g.setColor(new Color(255,255,255));
		   }
	
		   g.setFont(font);
			FontMetrics fm=g.getFontMetrics(font);
		   g.drawString(title, sx+width/2-fm.stringWidth(title)/2,sy+35 );
		  
		   return select;
		}
	   public boolean wantExit()
		{
			boolean b=exit;
			exit=false;
			return b;
		}
	   
		public boolean wantGo()
		{
			boolean b=go;
			go=false;
			return b;
		}	
	   
	   public void setMousePos(int x, int y)
	   {
	
			mx=x;
			my=y;
	   }
	   
	   public void setKlick(int nr)
	   {
		   klickn=nr;
		   if(klickn>0)
		   {
			   klick=true;
		   }
		   else
		   {
			   klick=false;
		   }
	   }
}
