package Menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Achievements.Task;
import DBZ.Main;
import Fight.FighterData;
import FighterBuild.Item;
import FighterBuild.ItemData;
import Images.GameImages;
import Save.HistoryFight;
import Save.MatchHistory;
import Save.Profil;

public class ProfilMenu extends MenuData{

	private boolean save=false;
	
	private int submenu=0;
	
	private ItemData idata=new ItemData();
	private BuildMenu bmenu=new BuildMenu();
	
	private int iselect=-1;
	private int fselect=-1;

	private int scrolly=0;
	private int sort=-1;
	private int[] fightid=new int[Main.fighteranz];

	  private int historyscroll=0;
	
	public ProfilMenu()
	{
		for(int i=0; i<Main.fighteranz; i++){
			
			fightid[i]=i;
		}
		historyscroll=99999;
		
	}
	
	  private void paintGoku(Graphics g,float flyw) {
			// TODO Auto-generated method stub
				 
			 
			  int gy=(int)(Math.sin(Math.toRadians(flyw))*5+Math.cos(Math.toRadians(flyw))*5);
			   g.drawImage(GameImages.songohan,930,gy+70,-306,267,null);
			   gy=(int)(Math.sin(Math.toRadians(flyw))*10+Math.cos(Math.toRadians(flyw))*10);
			   g.drawImage(GameImages.songoku,20,250+gy,null);
		}
	
	public Profil paint(Graphics g,Profil profil, MatchHistory history, float flyw)
	{
		g.drawImage(GameImages.menuback,0,0,(int)(1000),(int)(600),null);
		paintGoku(g,flyw);
		
		if(submenu==0)
		{

		 paintTitle(g,"Playerprofil");
		 
		  int sekund=profil.getSpielzeit();
			int stunde = sekund / (60*60);
			int minute = sekund / 60 - (stunde*60);
			int sekunde = sekund % 60;
			String time=stunde+" STD. "+minute+" MIN."+sekunde+" SEK.";
		 
		 String[] name={"Money: ","Playtime:  ","Fighters: "};
		 String[] value=new String[3];
		 value[0]=""+profil.getZeni()+" Zeni";
		 value[1]=time;
		 value[2]=profil.getFighterAmount()+" / "+(Main.fighteranz);
		 
		 int x=10,y=140;
		 g.setColor(new Color(100,100,100));
		 g.fillRoundRect(x,y,550,160,10,10);
		 g.setColor(new Color(150,150,150));
		 g.fillRoundRect(x+5,y+5,540,150,10,10);
		 
		 
		 g.setFont(font);
		 FontMetrics fm=g.getFontMetrics(font);
		 for(int i=0; i<name.length; i++)
		 {
			 g.drawImage(GameImages.menuicons[i],x,y+55*i,null);
			 g.setColor(new Color(255,255,0));
			 g.drawString(name[i], x+60,y+55*i+35);
			 g.setColor(Color.WHITE);
			 g.drawString(value[i], x+60+fm.stringWidth(name[i]),y+55*i+35);
		 }
		 
	
    //Dragonballs
		 
		// g.setColor(new Color(150,150,150));
		// g.fillRoundRect(x,y-65,430,60,10,10);
		 for(int i=0; i<7; i++)
		 {
			 g.setColor(new Color(50,50,50));
			 g.fillOval(x+5+i*60,y-65,60,60);
			 if(profil.getDragonball(i))
			 {
				 g.drawImage(GameImages.dragonballs[i],x+10+i*60,y-60,null);
			 }
			 else
			 {				 
					Graphics2D g2d = (Graphics2D) g;
					  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 0.1f));
								
				 g2d.drawImage(GameImages.dragonballs[i],x+10+i*60,y-60,null);
				  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 1f));	
					
			 }
		 }
		 
		   
		
		 String[] pl={"Fighter Shop","Item Shop","Character Build","Statistic","Achievements","Back"};
		 int m=paintMenu(g,pl,580,200,400,50);
		 switch(m)
		 {
		 case 0:  submenu=1; break;
		 case 1: submenu=2; break;
		 case 2: submenu=3;  bmenu.open(profil.getBuilds());  break;
		 case 3: submenu=4; break;
		 case 4: submenu=5; break;
		 case 5: exit=true; break;
		 }
		
		}
		else 
		{
			switch(submenu)
			{
			case 1: paintFighterShop(g,profil); break;
			case 2: paintItemShop(g,profil); break;
			case 3:
				bmenu.setKlick(klickn);
				bmenu.setMousePos(mx, my);
				bmenu.paint(g,profil); 
				
				break;
			case 4: paintStatistic(g,profil,history); break;
			case 5: paintAchievements(g); break;
			}
			 String[] pl2={"Back"};			
			 int m=paintMenu(g,pl2,150,550,700,50);
			if(m==0)
			{
				if(submenu==3){
					//save 
					profil.setBuilds(bmenu.getBuild());
					save=true;
					
				}
				submenu=0;
				for(int i=0; i<Main.fighteranz; i++){
					
					fightid[i]=i;
				}
				sort=-1;
				scrolly=0;
			}
		}
		 
		 if(save)
		 {
			 save=false;
			 return profil;
		 }
		 else
			 
		 {
			 return null;
		 }
	}
	

     int afilter=0;
     int ascroll=0;
	private void paintAchievements(Graphics g) {
		// TODO Auto-generated method stub
		this.paintTitle(g, "Achievements");
		int x=50;
		int y=90;
		g.setColor(new Color(50,50,50));
		g.fillRoundRect(x-2,y-2,914,454,20,20);
		g.setColor(new Color(100,100,100));
		g.fillRoundRect(x,y,910,450,20,20);
		
		g.setColor(new Color(150,150,150));
		g.fillRoundRect(x+10,y+10,380,430,10,10);
		g.fillRoundRect(x+400,y+10,500,430,10,10);
		
		String[] filter=Main.achievements.categories;
		
		int fy=y+25;
		for(int i=0; i<filter.length; i++)
		{
			g.setColor(new Color(20,20,20));
			g.fillRoundRect(x+10,fy,380,40,10,10);
			
			if(mx>=x+10&&mx<=x+390&&my>=fy&&my<=fy+40)
			{
				g.setColor(new Color(80,80,80));
				if(klick)
				{
					afilter=i;
					ascroll=0;
				}
			}
			else
			{
			g.setColor(new Color(120,120,120));
			}
			if(i==afilter)
			{
				g.setColor(new Color(70,70,70));
			}
			g.fillRoundRect(x+15,fy+5,370,30,10,10);
			g.setColor(Color.WHITE);
			g.drawString(filter[i],x+30,fy+30);
			fy+=45;
			
		}
		
		ArrayList<Task> tasks=Main.achievements.getTasksByFilter(afilter);
		
		int by=y+25;
		for(int i=0; i<9; i++)
		{		
			int id=i+ascroll*9;
			int xp=x+400;
			if(id<tasks.size())
			{
				Task t=tasks.get(id);
				boolean done=t.finished;
				String title=t.name;
				String text=t.text;
				int points=t.points;
				boolean hidden=t.hidden;
				if(done)
				{
					g.drawImage(GameImages.achblock[1],x+400,by,null);
					g.setColor(Color.WHITE);
					g.setFont(font);
					g.drawString(""+points,xp+7,by+33);
					g.setFont(font3);
					g.setColor(Color.BLACK);
					g.drawString(title,xp+50,by+18);
					g.setColor(Color.WHITE);
					g.setFont(font2);
					g.drawString(text,xp+50,by+35);
				}
				else
				{
					g.drawImage(GameImages.achblock[0],x+400,by,null);
					g.setColor(Color.BLACK);
					g.setFont(font);
					g.drawString(""+points,xp+7,by+33);
					g.setFont(font3);
					g.setColor(new Color(128,128,128));
					g.drawString(title,xp+50,by+18);
					if(hidden)
					{
						text="This is a hidden Achievment";
					}
					g.setColor(Color.BLACK);
					g.setFont(font2);
					g.drawString(text,xp+50,by+35);
				}
				
			
			}
			by+=45;
		}
		x=440;
		y+=10;
	    if(ascroll>0)
	    {
		g.drawImage(GameImages.messagepfeil,x+15,y+10,40,-15,null);
		if(mx>=x+15&&mx<=x+55&&my>=y-5&&my<=y+10){			
			if(klickn==1){
			
				ascroll--;
			}
		}	
	    }
	    if(ascroll*9<tasks.size()-9)
	    {
		g.drawImage(GameImages.messagepfeil,x+15,y+420,null);
		if(mx>=x+15&&mx<=x+55&&my>=y+420&&my<=y+435){			
			if(klickn==1){				
				ascroll++;
			}
		}	
	    }
	}

	private void sortStatistic(Profil p, int sort) {
		
		
		int[][] x=null;
		if(statisticwahl==0)
		{
			x=p.getStatistic();	 
		}
		else if(statisticwahl==1)
	  	{
	    	x=p.getPCStatistic();
		}
			 		if(windeath)
			 		{
			 			 if(sort>=0)
			 		     {
			 		    	 sort++;
			 		     }	
			 			
			 		}
			 		else{
			 			
			 			 if(sort>=1)
			 		     {
			 		    	 sort++;
			 		     }	
			 			
			 		}
	    		
		  int temp;
			for(int i=1; i<x.length; i++) {
				for(int j=0; j<x.length-i; j++) {
					if(x[FighterSelection.fighters[fightid[j]]][sort]<x[FighterSelection.fighters[fightid[j+1]]][sort]) {
						temp=fightid[j];
						fightid[j]=fightid[j+1];
						fightid[j+1]=temp;					
						}				
				}
			}
	}

	int statisticwahl=0;
	boolean windeath=false;
	private void paintStatistic(Graphics g, Profil profil, MatchHistory history) {
		// TODO Auto-generated method stub
	
	
		int x=100,y=80;
		g.setColor(new Color(100,100,100));
		g.fillRoundRect(x,y,800,460,10,10);
		g.fillRoundRect(x-90,y,100,40,10,10);
		g.fillRoundRect(x-90,y+50,100,40,10,10);
		g.fillRoundRect(x-90,y+100,100,40,10,10);
		
		g.setColor(new Color(150,150,150));		
		g.fillRoundRect(x+5,y+5,790,450,7,7);	
		boolean newb=false;
		for(int i=0; i<3; i++)
		{
			g.setColor(new Color(150,150,150));		
			if(statisticwahl==i){ 	g.setColor(new Color(200,200,200));	}
			g.fillRoundRect(x-85,y+5+i*50,100,30,10,10);
			if(mx>=x-90&&mx<=x&&my>=y+i*50&&my<=y+i*50+30)
			{
				if(klick)
				{
					statisticwahl=i;
					
					if(i<2){					
						newb=true;
					}
				}
			}
		}
		g.setColor(Color.BLACK);
		g.setFont(font3);
		g.drawString("Player",x-80,y+25);
		g.drawString("Computer",x-80,y+75);
		g.drawString("History",x-80,y+125);
		
		int[][] stat=null;
		if(statisticwahl==0)
		{
			stat=profil.getStatistic();
			
		}
		else if(statisticwahl==1)
		{
			stat=profil.getPCStatistic();
		}
		if(newb)
		{
			if(sort>-1)
			{
			sortStatistic(profil,sort);
			}
		}
		if(statisticwahl==2)
		{
			this.paintTitle(g, "Match History  Fights: "+history.getFightAmount());
			paintMatchHistory(g,history);
		}
		else
		{
		
		if(statisticwahl==0)
		{
			this.paintTitle(g, "Player Statistic");
		}
		else{
			
			this.paintTitle(g, "Computer Statistic");
		}
		
		x+=10;
		y+=20;		
		
		int[] xpos={x+70,x+200,x+270,x+430,x+600,x+750};
		String[] name={"Win/Def","Kills","Dmg.done","Dmg.taken","Max.Dmg."};
		for(int i=0; i<5; i++)
		{
			if(sort==i)
			{
			g.setColor(new Color(200,200,200));
			g.fillRoundRect(xpos[i],y-10,xpos[i+1]-xpos[i],30,5,5);
			}
			
				if(mx>=xpos[i]&&mx<=xpos[i+1]&&my>=y-10&&my<=y+20){			
					if(klickn==1){		
						if(sort!=i||sort==0)
						{
						sort=i;
						if(sort==0)
						{
							 windeath=!windeath;
							
						}
						sortStatistic(profil,sort);
						}
					}
				}	
			g.setColor(Color.BLACK);
			g.drawString(name[i],xpos[i],y+13);
		}
   
	      
		for(int i=0; i<8; i++)
		{
			g.setColor(new Color(50,50,50));
		
			g.fillRoundRect(x+5,y+18+i*50,750,44,3,3);
			int id=i+scrolly*8;
			if(id<Main.fighteranz)
			{
				if(profil.haveFighter(FighterSelection.fighters[fightid[id]])==false&&FighterData.kaufbar(FighterSelection.fighters[fightid[id]])==false)
				{
	
			g.drawImage(GameImages.fighterselection[0],x+10,y+20+i*50,50,40,null);
				}
				else
				{
					g.drawImage(GameImages.faces[FighterSelection.fighters[fightid[id]]],x+10,y+20+i*50,null);
				}
			g.setColor(Color.WHITE);

	        
			   int fid=FighterSelection.fighters[fightid[id]];
			      String ausgb[]=new String[8];
			      ausgb[0]=stat[fid][0]+"/"+stat[fid][1];
			      ausgb[1]=""+stat[fid][2];
			      ausgb[2]=""+stat[fid][3];
			      ausgb[3]=""+stat[fid][4];
			      ausgb[4]=""+stat[fid][5];
			      double pergame=(double)stat[fid][2]/((double)stat[fid][0]+(double)stat[fid][1]) ; 
			
			      DecimalFormat f = new DecimalFormat("#0.00"); 
                  String zahl=f.format(pergame); 
                  if(stat[fid][0]+stat[fid][1]==0)
                  {
                	  zahl="0";
                  }
                
		    for(int h=0; h<5; h++)
		    {
		    	g.setFont(font);
		    	if(h==1)
		    	{
		    		g.drawString(ausgb[h],xpos[h],y+45+i*50);
		    		g.setFont(font2);
		    		g.drawString("Average: "+zahl,xpos[h]-30,y+60+i*50);
		    	}
		    	else
		    	{
		    		g.drawString(ausgb[h],xpos[h],y+50+i*50);
		    	}
		    
		    }
			}
		}
	    if(scrolly>0)
	    {
		g.drawImage(GameImages.messagepfeil,x+15,y+15,40,-15,null);
		if(mx>=x+15&&mx<=x+55&&my>=y&&my<=y+15){		
		
			if(klickn==1){
			
				scrolly--;
			}
		}	
	    }
	    if(scrolly*8<Main.fighteranz-8)
	    {
		g.drawImage(GameImages.messagepfeil,x+15,y+420,null);
		if(mx>=x+15&&mx<=x+55&&my>=y+420&&my<=y+435){			
			if(klickn==1){				
				scrolly++;
			}
		}	
	    }
	    
	    g.setColor(Color.BLACK);
		g.setFont(font2);
		int maxs=Main.fighteranz/8+1;
		if(Main.fighteranz%8==0)
		{
			maxs--;
		}
		g.drawString("Page: "+(scrolly+1)+"/"+maxs,20,240);
		
		int[] max=new int[6];
		for(int i=0; i<stat.length; i++)
		{
			for(int h=0; h<6; h++)
			{
				max[h]+=stat[i][h];
			}
		}
		g.setColor(Color.WHITE);
	    for(int i=0; i<5; i++)
	    {
	    	String s=""+max[i+1];
	    	if(i==0)
	    	{
	    		s=max[0]+"/"+max[1];
	    	}
	    	g.drawString(s,xpos[i],530);
	    }
		
		}
	}


  
	private void paintMatchHistory(Graphics g, MatchHistory history) {
		// TODO Auto-generated method stub
		int max=history.getFightAmount();
		int x=120;
		int y=88;
	
		g.setColor(Color.BLACK);
		g.setFont(font2);
		int maxs=history.getFightAmount()/9+1;
		if(history.getFightAmount()%9==0)
		{
			maxs--;
		}
		if(historyscroll==99999)
		{
			historyscroll=maxs-1;
		}
		g.drawString("Page: "+(historyscroll+1)+"/"+maxs,20,240);
	
		    
		for(int i=0; i<9; i++)
		{
			int fid=i+historyscroll*9;
			if(fid<max)
			{
			g.setColor(new Color(120,120,120));
			g.fillRoundRect(x,y,760,45,5,5);
			BufferedImage b=new BufferedImage(200,45,BufferedImage.TYPE_INT_RGB);
		
			HistoryFight f=history.getFight(fid);
			b.createGraphics().drawImage(GameImages.stages[f.map+1],0,-30,null);
			Graphics2D g2d = (Graphics2D) g;
			  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 0.75f));
			g2d.drawImage(b,x+10,y,null);
			  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 1));
			
			String mod=f.mode;
			if(mod.length()>=25)
			{
				mod=mod.substring(0,25);
				mod+="...";
			}
			g.setColor(Color.BLACK);			
			g.drawString(mod,x+15,y+39);
			g.drawString(f.winner,x+15,y+14);
			g.drawString(mod,x+15,y+41);
			g.drawString(f.winner,x+15,y+16);
			g.drawString(mod,x+14,y+40);
			g.drawString(f.winner,x+14,y+15);
			g.drawString(mod,x+16,y+40);
			g.drawString(f.winner,x+16,y+15);
			
			g.setColor(new Color(200,200,200));			
			g.drawString(mod,x+15,y+40);
			g.drawString(f.winner,x+15,y+15);
			
			ArrayList<Integer> fighter=f.fighter;
			ArrayList<Integer> control=f.controll;
			ArrayList<Integer> team=f.team;
            int[] teamanz=new int[4];
            for(int h=0; h<team.size(); h++)
            {
            	teamanz[team.get(h)]++;
            }
			int[] startpos=new int[4];
			startpos[0]=220+x;
			startpos[1]=startpos[0]+teamanz[0]*45+25;
			startpos[2]=startpos[1]+teamanz[1]*45+25;
			startpos[3]=startpos[2]+teamanz[2]*45+25;
			int[] teamnr=new int[4];
            for(int h=0; h<fighter.size(); h++)
            {
            	int t=team.get(h);
            	int xp=startpos[t]+teamnr[t]*45;
            	Color c=null;
            	switch(t)
        		{
        		case 0: c=new Color(0,0,150); break;
        		case 1: c=new Color(150,0,0); break;
        		case 2: c=new Color(0,150,0); break;
        		case 3: c=new Color(150,150,0); break;
        		}
            	g.setColor(c);
            	g.fillRect(xp-3,y+3,45,39);
            	g.drawImage(GameImages.faces[fighter.get(h)],xp,y+8,40,35,null);
            	int leben=(int) ((float)f.healthproz.get(h)/2.5f);
            	if(leben<0)
            	{
            		leben=0;
            	}
            	else if(leben>40)
            	{
            		leben=40;
            	}
            	if(leben>0)
            	{
            	g.setColor(new Color(100,100,100));
            	g.fillRect(xp,y+4,40,4);
       
            	g.setColor(new Color(250-leben*4,leben*6,0));
            	g.fillRect(xp,y+4,leben,4);
            	g.setColor(new Color(0,0,0));
            	g.drawRect(xp,y+4,40,4);
            	}
            	
            	g.setColor(Color.BLACK);
            	g.drawRect(xp-3,y+3,45,39);
            	g.drawImage(GameImages.multisteer[control.get(h)],xp+22,y+27,null);
            	teamnr[t]++;
            }
            for(int h=0; h<3; h++)
            {
            	if(teamanz[h]>0&&teamanz[h+1]>0)
            	{
            		int xp=startpos[h+1]-28;
            		g.drawImage(GameImages.vs,xp,y+12,25,20,null);
            	}
            }
            	
            g.setColor(Color.BLACK);
			g.drawString(f.date,x+625,y+15);
		
			
			}
			else{				
				break;
			}
			y+=50;
		}
		
		x=80;
		y=80;
		g.setColor(Color.BLACK);
		  if(historyscroll>0)
		    {
			g.drawImage(GameImages.messagepfeil,x+15,y+15,40,-15,null);
			if(mx>=x+15&&mx<=x+55&&my>=y&&my<=y+15){		
			
				if(klickn==1){
				historyscroll--;
				}
			}	
		    }
		  y=100;
		    if(historyscroll*9<history.getFightAmount()-9)
		    {
			g.drawImage(GameImages.messagepfeil,x+15,y+420,null);
			if(mx>=x+15&&mx<=x+55&&my>=y+420&&my<=y+435){			
				if(klickn==1){				
					historyscroll++;
				}
			}	
		    }
	}

	private void paintItemShop(Graphics g, Profil profil) {
		// TODO Auto-generated method stub
		this.paintTitle(g, "Item Shop");
		ArrayList<Item>items=idata.getItems();
		int x=50,y=90;
        g.setColor(new Color(20,20,20));
        g.fillRoundRect(x-12,y-12,914,264,20,20);
        g.setColor(new Color(70,70,70));
        g.fillRoundRect(x-10,y-10,910,260,20,20);
       
		for(int i=0; i<90; i++)
		{		
			
			g.setColor(new Color(50,50,50));
			g.fillRoundRect(x,y,40,40,5,5);
		
			if(iselect==i)
			{
				g.setColor(new Color(150,150,150));
			}
			else
			{
				
			g.setColor(new Color(100,100,100));
			
			}
			
			  if(mx>=x&&mx<=x+40&&my>=y&&my<=y+40)
			  {
				  g.setColor(new Color(150,150,150));
				  if(klickn==1)
				  {
					  iselect=i;
				  }
			  }
			  if(profil.haveItem(i))
			  {
				  g.setColor(new Color(0,180,0));
			  }
			  g.fillRoundRect(x+2,y+2,36,36,3,3);
				Item it=items.get(i);
				if(it!=null)
				{
					g.drawImage(GameImages.itemicons[it.getIcon()],x,y,null);
				}
			x+=50;
			if(x>=950)
			{
				y+=50;
				x=50;
			}
		}
		
		
       x=100;
		y=370;
		g.setColor(new Color(50,50,50));
		  g.fillRoundRect(x,y,800,150,15,15);
	
			  			g.setFont(font);
			  g.setColor(Color.WHITE);
				g.drawString("Money: "+profil.getZeni()+" Zeni",x+480,y+40);
		
				g.setColor(new Color(50,50,200));
				if(iselect>-1)
				{
					Item it=items.get(iselect);
					if(it!=null)
					{
					g.drawString(it.getName(),x+20,y+40);
					
					int preis=it.getPreis();
					 if(!profil.haveItem(iselect))
					 {
					if(profil.getZeni()>=preis)
					{
						
							 String[] pl2={"Buy Item"};			
							 int m=paintMenu(g,pl2,x+150,y+90,450,50);
							if(m==0)
							{
								profil.addZeni(-preis);
								profil.setItem(iselect, true);
								save=true;
								
							}
						
							 g.setColor(new Color(100,100,100));
					}
					else
					{
						  g.setColor(new Color(100,0,0));
					}
					g.drawString("Price: "+preis,x+480,y+70);
					 }
					 else
					 {
						 g.setColor(new Color(0,0,180));
						 g.drawString("Sold out",x+480,y+70);
					 }
					g.setColor(Color.WHITE);
					g.setFont(font3);
					g.drawString(it.getBeschreibung(),x+20,y+65);
					
					}
				}
			
	}

	
	private void paintFighterShop(Graphics g, Profil profil) {
		g.setFont(font);
		int x=50,y=30;
		
		int fa=Main.fighteranz;
		int hw=fa/10;
		int iw=fa-10*hw;
		//if(iw>0)
		{
			hw++;
		}

		for(int h=0; h<hw; h++)
		{
		for(int i=0; i<10; i++)
		{
			boolean darf=true;
			if(h==hw-1)
			{
				if(i>=iw)
				{
				darf=false;
				}
			}
			
			if(h*10+i<fa)
			{
			if(darf)
			{
			
			  int bx=x+i*90;
			  int by=y+h*55;
			  boolean kannkaufen=true;
			  if(FighterData.kaufbar(FighterSelection.fighters[h*10+i])==false&&profil.haveFighter(FighterSelection.fighters[h*10+i])==false)
			  {
				  kannkaufen=false;
			  }
			  if(kannkaufen)
			  {
			  if(mx>=bx&&mx<=bx+80&&my>=by&&my<=by+50)
			  {
				  g.setColor(new Color(200,200,200));
				  if(klickn==1)
				  {
					  Main.sound.menuSound();
					fselect=FighterSelection.fighters[h*10+i];  
				  }
			  }
			  else
			  {
				  g.setColor(new Color(100,100,100));
				  if(profil.getZeni()<FighterData.getPrice(FighterSelection.fighters[h*10+i]))
					{
					  g.setColor(new Color(100,0,0));
					}
			  }
			  if(profil.haveFighter(FighterSelection.fighters[h*10+i]))
				 {
				  g.setColor(new Color(0,180,0));
			     }
			 
			  
			  g.fillRoundRect(bx,by,80,50,5,5);
			  g.setColor(new Color(50,50,50));
			  g.drawRoundRect(bx,by,80,50,5,5);
			  g.setColor(Color.BLACK);
			  g.fillRect(bx+13,by+3,56,46);
			  
			 
			  g.drawImage(GameImages.faces[FighterSelection.fighters[h*10+i]],bx+15,by+5,null);
			  }
			  else
			  {
				  g.setColor(new Color(100,0,0));
				  g.fillRoundRect(bx,by,80,50,5,5);
				  g.setColor(new Color(50,50,50));
				  g.drawRoundRect(bx,by,80,50,5,5);
				  g.setColor(Color.BLACK);
				  g.fillRect(bx+13,by+3,56,46);
				  g.drawImage(GameImages.fighterselection[0],bx+15,by+5,50,40,null);
			  }
			 
			}
			else
			{
				
			}
			}
		}
		}
		
		y+=150;
		g.setColor(new Color(50,50,50));
		  g.fillRoundRect(x+50,y+200,800,150,15,15);
			g.setColor(Color.BLACK);
			  g.drawRoundRect(x+50,y+200,800,150,15,15);
			  g.setColor(new Color(20,20,20));
			  g.fillRoundRect(x+60,y+210,120,120,15,15);
			  			
			  g.setColor(Color.WHITE);
				g.drawString("Money: "+profil.getZeni()+" Zeni",x+530,y+240);
		if(fselect>-1)
		{
			
			g.drawImage(GameImages.fighterselection[fselect+2],x+70,y+220,null);
			g.drawString(FighterData.getName(fselect),x+200,y+240);
			
		
			int[] p=FighterData.getAttributes(fselect);
			int price=FighterData.getPrice(fselect);
			if(profil.haveFighter(fselect)==false)
			{
			if(profil.getZeni()>=price)
			{
		
			
			 String[] pl2={"Buy Character"};			
			 int m=paintMenu(g,pl2,x+200,y+280,450,50);
			if(m==0)
			{
				profil.addZeni(-price);
				profil.setFighter(fselect, true);
				save=true;
				fselect=-1;
			}
			g.setColor(new Color(0,180,0));
			}
			else
			{
				g.setColor(new Color(180,0,0));
			}
			g.drawString("Price: "+price+" Zeni",x+200,y+270);
			}
			else
			{
				g.setColor(new Color(0,0,180));
				g.drawString("Sold out",x+200,y+270);
			}
			
		}
		
	}
	
	
	
}
