package DBZ.World;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


import DBZ.Main;
import Fight.Fight;
import Images.GameImages;
import Misc.ZeniScreen;
import Story.Chapter;
import Story.StoryBattle;



public class WorldMap {

	
	private boolean open=true,action=false;
	private Dragonballs balls=new Dragonballs();
	private Chara player=new Chara();
	private Map[] map;
	private int currentmap=0;
	private Chapter chapter;
	private StoryBattle battle;
	private Font font=new Font("Arial",0,20);
	private Font fontsmall=new Font("Arial",0,12);
	private int wasser,wz;
	private int schiffwait;
	
	private boolean nichtsaktivieren=false;
	private boolean shenlong=false;
	
	public WorldMap(Map[] map, boolean[] dragonball )
	{
		open=true;
	this.map=map;	
	loadMap(currentmap);
	int maxx=map[0].getDimension().width*32;
	int maxy=map[0].getDimension().height*32;
	balls.init(dragonball, maxx, maxy);
	}
	
	public void switchMap(int nr)
	{
	loadMap(nr);
		if(nr==0)
		{
		player.setPos(	724*32-400,497*32-200);
		}
	}
	
	private void loadMap(int nr)
	{
		currentmap=nr;
		int[] i={map[currentmap].getDimension().width*32,map[currentmap].getDimension().height*32};
		player.setMapBorder(i);

	   
	}
	
	public void setChapters(Chapter  chapters)
	{
		this.chapter=chapters;
		
	}
	
	public boolean isOpen()
	{
		return open;
	}
	
	public void paint(Graphics g, int progress)
	{
		int x=player.getX();
		int y=player.getY();
		
		
		if(progress>17)
		{
			player.setStufe(1);
		}
		
		if(schiffwait>0)
		{
			g.drawImage(GameImages.raumschiff,0,0,null);
			schiffwait--;
		}
		else
		{
			
		
		
		wz++;
		if(wz>25)
		{
			wasser++;
			wz=0;
			if(wasser>1){
				
				wasser=0;
			}
		}
		
		for(int i=-1; i<32; i++)
		{
		  for(int h=0; h<20; h++)
		  {
			  int tx=x-512+i*32;
			  int ty=y-288+h*32;
			  
			  int t=map[currentmap].getTile(tx/32,ty/32, 0);
			  int t2=map[currentmap].getTile(tx/32,ty/32, 1);
				
			  int xp=tx-((tx/32)*32);
			  int yp=ty-((ty/32)*32);
			  
			  if(t==0)
			  {
				  switch(wasser)
				  {
				  case 0: break;
				  case 1:t=98; break;
				  case 2: t=99; break;
				  }
			  }
			  
			  g.drawImage(GameImages.maptiles[currentmap][t],i*32-xp,h*32-yp,null);
			  if(t2>0)
			  {
			  g.drawImage(GameImages.maptiles[currentmap][t2],i*32-xp,h*32-yp,null);
			  }
		  }
		}
		if(currentmap==0)
		{
		for(int i=0; i<7; i++)
		{						
			if(balls.frei(i))
			{
				int dx=balls.getPos(i)[0];
				int dy=balls.getPos(i)[1];
				int size=16;
				  int t=map[currentmap].getTile(dx/32,dy/32, 0);
				if(t>0)
				{
				  g.drawImage(GameImages.dragonballs[i],dx-x,dy-y,size,size,null);
				
				}
				else
				{
					  size=64;
					  dx-=24;
					  dy-=24;
				}
				if(action)
				{
				
				int sx=player.getX()+512;
				int sy=player.getY()+288;
				   
				if(sx>=dx&&sx<=dx+size&&sy>=dy&&sy<=dy+size)
				{
				
						
						Main.sound.playSound(18, true);
						shenlong=balls.sammeln(i);
						
				
				}
				}
			}
		}
		}
		
	
		
		player.paint(g);
		player.move();
		 int sx=150*32;
		 int sy=50*32;
		 
		 boolean stehtfrei=true;
		 
		if(progress>=5)
		{
		//Raumschiff
	
		 paintStoryIcon(g,0,"Spaceship",150*32,50*32);
		 
		 if(action){
			 
			 if(x+512>=sx&&x+512<=sx+50&&y+288>=sy&&y+288<=sy+50)
			 {
				 if(nichtsaktivieren==false)
				 {
				 nichtsaktivieren=true;
				 Main.sound.playSound(12, true);
				 action=false;
	             currentmap++;
	          
	             if(currentmap>1)
	             {
	            	 currentmap=0;
	             }
	         	loadMap(currentmap);
	             schiffwait=200;
				 }
				   stehtfrei=false;
			 }
		 }
		 }
		 
		
		
		
			 ArrayList<StoryBattle> fights=chapter.getStoryBattles();
			 for(int h=0; h<fights.size(); h++){
				 if(h<=progress)
				 {
				 StoryBattle b=fights.get(h);
				 if(b.isOnMap(currentmap))
				 {
				sx=b.getPos()[0];
				 sy=b.getPos()[1];
				 
				if(b.getIcon()>0)
				{
				 paintStoryIcon(g,b.getIcon(),b.getName(),sx,sy);
				 if(action){
					 
					 if(x+512>=sx&&x+512<=sx+50&&y+288>=sy&&y+288<=sy+50)
					 {
						 if(nichtsaktivieren==false)
						 {
						 Main.sound.playSound(17, true);
						 action=false;
						
						 nichtsaktivieren=true;
						 battle=b;
						 }
						
						 stehtfrei=false;
					 }
				 }
				 }
				 }
				 }
			 }
			 
		if(stehtfrei)
		{
			nichtsaktivieren=false;
		}
		
	    
		paintMiniMap(g,790,0,progress,chapter);
		
		
		if(naherDragonball()&&currentmap==0)
		{
		paintDragonRadar(g,0,440);
		}
		
		iconwait++;
		if(iconwait>20)
		{
			iconwait=0;
			if(updown)
			{
				icony--;
				if(icony<0)
				{
					
					updown=!updown;
				}
			}
			else
			{
				icony++;
				if(icony>3)
				{
					
					updown=!updown;
				}
			}
			
		
		}
		}
	}
	
	public StoryBattle wantFight()
	{
		StoryBattle f=battle;
		battle=null;
		return f;
	}
	
private boolean updown=false;
private int icony=0,iconwait=0;;

	private void paintStoryIcon(Graphics g, int i, String name, int sx, int sy) {
		int x=player.getX();
		int y=player.getY();
		g.drawImage(GameImages.worldicons[i],sx-x,sy-y-icony,null);
		
		
		g.setFont(font);
		FontMetrics fm=g.getFontMetrics(font);
		
		g.setColor(new Color(50,50,50));
		g.drawString(name,sx-x+25-fm.stringWidth(name)/2-1,sy-y-icony-6);
		g.setColor(new Color(100,100,250));
		g.drawString(name,sx-x+25-fm.stringWidth(name)/2,sy-y-icony-5);
		
	}

	public void paintDragonRadar(Graphics g, int x, int y)
	{
		
		int size=150;
		int size2=size/2;

		g.setColor(new Color(100,100,100));
		g.fillOval(x,y,size+10,size+10);
		
		
		
		
		g.setColor(Color.BLACK);
		g.drawOval(x,y,size+10,size+10);
		
		x+=5;
		y+=5;
		g.setColor(new Color(65,255,50));
		g.fillOval(x,y,size,size);
		
		for(int i=0; i<4; i++)
		{
			g.setColor(new Color(15,180,50));
			int is;
			is=20*i;
			if(i==0)
			{
				is=3;
			}
			
			g.drawOval(x+size2-is,y+size2-is,is*2,is*2);
		}
		
		
		g.setColor(new Color(255,255,80));
		int spx=player.getX()+512;
		int spy=player.getY()+288;

		for(int i=0; i<7; i++)
		{						
			if(balls.frei(i))
			{
				int dx=balls.getPos(i)[0];
				int dy=balls.getPos(i)[1];
				
				
				
				int abstand=(int) Point.distance(spx,spy,dx,dy);
	
				if(abstand<1000){
		
					int bx=0,by=0;
					
					bx=(int)((((double)((spx)-dx))/(double)1000)*size2);
					by=(int)((((double)((spy)-dy))/(double)1000)*size2);
					bx*=-1;
					by*=-1;
					
					g.fillOval(x+size2+bx-2,y+size2+by-2,4,4);
				}
			}
		}
		
		
	}
	
	

	
	private void paintMiniMap(Graphics g,int x, int y, int progress, Chapter c)
	{
		g.setColor(new Color(100,100,100));
		g.fillRect(x,y,210,161);
		
		int maxx=map[currentmap].getDimension().width;
		int maxy=map[currentmap].getDimension().height;
		
		g.drawImage(GameImages.minimap[currentmap],x+5,y+5,null);
		g.setColor(Color.BLACK);
		g.drawRect(x,y,210,161);
		int sx,sy;
		
		if(progress>=5)
		{
			  sx=150*32;
			 sy=50*32;
			   sx=(int)((((double)sx/((double)maxx*32)))*200);
			     sy=(int)((((double)sy/((double)maxy*32)))*153);
			     g.drawImage(GameImages.ring2,x+sx-8,y+sy-8,null);
			     
		}
	g.setColor(Color.BLACK);
	g.setFont(fontsmall);
			for(int i=0; i<c.getBattleAmount(); i++)
			{
				if(i<=progress)
				{
		StoryBattle s=c.getFight(i);
		if(s.isOnMap(currentmap))
		{
		 sx=s.getPos()[0]+512;
		 sy=s.getPos()[1]+288;
		   sx=(int)((((double)sx/((double)maxx*32)))*200);
		     sy=(int)((((double)sy/((double)maxy*32)))*153);
		 
		     if(i==progress)
		     {
		    	 
			   g.drawImage(GameImages.messagepfeil,x+sx-8,y+sy-8-icony,16,8,null);
		     }
		     else{
		    
		    g.drawImage(GameImages.ring,x+sx-5-2,y+sy-2-5,null);
		    g.drawString(""+(i+1),x+sx+3,sy+2);
		     }
			}
				}
				
			}
		g.setFont(font);
		 sx=player.getX()+512;
		 sy=player.getY()+288;
	     sx=(int)((((double)sx/((double)maxx*32)))*200);
	     sy=(int)((((double)sy/((double)maxy*32)))*153);
	     
	     
	     g.setColor(Color.YELLOW);
			for(int i=0; i<7; i++)
			{						
				if(balls.frei(i))
				{
					int dx=balls.getPos(i)[0];
					int dy=balls.getPos(i)[1];
					
				     dx=(int)((((double)dx/(double)28800))*200);
				     dy=(int)((((double)dy/(double)21728))*151);
					
					//g.drawLine(x+sx,y+sy,x+dx,y+dy);
				}
			}
	     
	     g.setColor(Color.RED);
	   
	     g.fillOval(x+sx-2,y+sy-2,4,4);
	}
	
	public void makeMove(boolean[] taste)
	{
	   int x= player.getX()/32;
	   int y=player.getY()/32;
	   
	  boolean[] move=new boolean[9];
	   for(int i=0; i<3; i++)
	   {
		   for(int h=0; h<3; h++)
		   {
			   move[i*3+h]=map[currentmap].isBlocked(x+i-1, y+h-1);
          
		   }
	   }
	   player.blockRoute(move);
	   action=false;
		if(taste[0])
		{
		player.makeMove(1);
		}
		else if(taste[1])
		{
			player.makeMove(0);
		}
		else if(taste[2])
		{
			player.makeMove(2);
		}
		else if(taste[3])
		{
			player.makeMove(3);
		}
		else if(taste[5])
		{
			player.makeMove(4);
		}
		action=true;
	}
	

	
	private boolean naherDragonball() {
		boolean nah=false;
		int x=player.getX()+512;
		int y=player.getY()+288;
		for(int i=0; i<7; i++)
		{						
			if(balls.frei(i))
			{
				int dx=balls.getPos(i)[0];
				int dy=balls.getPos(i)[1];
				if(inRadius(x,dx,y,dy,1000))
				{
					nah=true;
					break;
				}
				
			}
		}
		return nah;
	}
	
	private boolean inRadius(int x1, int x2, int y1, int y2, int r)//Punkt im Radius des anderen Punkts?
	{
		boolean b=false;	
		int abstand=(int) Point.distance(x1,y1,x2,y2);
		if(abstand<=r)
		{
			b=true; 
		}
		return b;
	}
	
	public boolean ruftShenlong()
	{
		return shenlong;
	}
	
	public void endeShenlong()
	{
		int maxx=map[0].getDimension().width*32;
		int maxy=map[0].getDimension().height*32;
	
		balls.clear(maxx,maxy);
	
		shenlong=false;
	}
}
