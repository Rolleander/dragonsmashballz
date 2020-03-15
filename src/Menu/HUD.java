package Menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import DBZ.Main;
import DBZ.PlayerControl;
import Fight.Fighter;
import Fight.FighterData;
import Fight.Team;
import Images.GameImages;
import Misc.MiniTimer;
import Save.HistoryFight;




public class HUD {


	private Font font=new Font("Arial",3,30);
	private Font font2=new Font("Arial",3,50);
	private static int[] damage;
	private static int[] timer;
	
	private static int[] combo;
	private static int sieg=-1;
	private static int loser;
	
	
	private static int[] damagetaken;
	private static int[] damagedone;
	private static int[] maxdamage;
	private static int[] kills;
   
	private static int nextenemy=-1,nextwait=0;
    private String teamsieg="",modus="Default";
    private static int[][] statistic,pcstatistic;
    
    
    private int starttimer=0;
    private static HistoryFight history;
    private int mapID;
    
    //kill anzeige
    private static MiniTimer minitimer=new MiniTimer();
    private static String[] killmessages={"has killed","has defeated","has slain","has beaten","wiped out","has annihilated"};
    private static String[] multikill={"Doublekill","Triplekill","Quadrakill","Pentakill","Hexakill","Megakill","Megakill","Megakill","Megakill","Megakill"};
    private static int killer,opfer,killmessage;
    
  
    
	public HUD()
	{
	
	}
	

	public void open(Fighter[] f,String modus, int fm, int mapID)
	{
		this.mapID=mapID;
	damage=new int[f.length];
	statistic=new int[Main.fighteranz][8];
	pcstatistic=new int[Main.fighteranz][8];
	damagedone=new int[f.length];
	maxdamage=new int[f.length];
	damagetaken=new int[f.length];
	timer=new int[f.length];
	
	combo=new int[f.length];
	kills=new int[f.length];
	history=null;
	this.modus=modus;
	sieg=-1;
	String st="Story Fight";
	starttimer=120;
	if(modus.length()>=st.length())
	{
	   if(modus.substring(0,st.length()).equals(st))
	   {
		   starttimer=0;
	
	   }
	}
    if(starttimer>0)
    {
    	Timer timer=new Timer();
    	timer.schedule(new Loop(),0, 10);
    }
	
	
		
	}
	
	
	
	public boolean start()
	{
		
		if(starttimer<=0)
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
	
	private class Loop extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			starttimer--;
			if(starttimer==0)
			{
				cancel();
			}
		}
		
	}
	
	private class Loop2 extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub	
			sieg--;
			if(sieg==0)
			{
			   
				cancel();
			}
		}
		
	}
	
	private void startSiegTimer()
	{
		sieg=100;
		Timer timer=new Timer();
    	timer.schedule(new Loop2(),0, 30);
	}
	
	public void paintHUDBar(Graphics g,int x, int y, int spiegeln,  int leben, int maxleben, int ki, int maxki,int blitze,int shield,int spielerid)
	{
	int proz=(int)(((double)leben/(double)maxleben)*300);		
	
		BufferedImage img;
		 img = new BufferedImage(400,50,BufferedImage.TYPE_INT_ARGB);
		 img.getGraphics().drawImage(GameImages.hud,0,0,400,50,null);
		 if(proz>0)
		 {
		 img.getGraphics().drawImage(GameImages.leben,66,2,proz-2,16,null);
		 if(shield>0)
		 {
			 proz=(int)(((double)shield/(double)FighterData.SHIELD)*300);	
		 img.getGraphics().drawImage(GameImages.schild,66,14,proz-2,4,null);
		 }
		 }
		 proz=(int)(((double)ki/(double)maxki)*180);
		 if(proz>0)
		 {
		 img.getGraphics().drawImage(GameImages.ki,75,26,proz,18,null);
		 if(ki>=FighterData.getKiWastage(0)[1])
		 {
			// img.getGraphics().setColor(new Color(50,100,235));
			 img.getGraphics().drawRect(75,26,proz-1,17);
		 }
		 }
		 if(spiegeln>1)
		 {
			 img.getGraphics().drawImage(GameImages.faces[spielerid],8,45,50,-50,null);	
		 }
		 else
		 {
			 img.getGraphics().drawImage(GameImages.faces[spielerid],8,4,null);	
		 }
		 
		 //BLitze
		 for(int i=0; i<10; i++)
		 {
			 if(blitze>i)
			 {
				 img.getGraphics().drawImage(GameImages.blitzicon[1],269+i*8,19,null);
			 }
			 else
			 {
				 img.getGraphics().drawImage(GameImages.blitzicon[0],269+i*8,19,null);
			 }
		 }
		 
	 
		 if(spiegeln==1)
		 {
			  g.drawImage(img,x+400,y,-400,(int)(50),null);
		 }		 
		 else if(spiegeln==2)
		 {
			 g.drawImage(img,x,y+50,(int)(400),(int)(-50),null);
		 }	
		 else if(spiegeln==3)
		 {
			  g.drawImage(img,x+400,y+50,-400,(int)(-50),null);
		 }
		 else
		 {
		  g.drawImage(img,x,y,(int)(400),(int)(50),null);
		 }
	}
	
	public void paint(Graphics g, Fighter[] fighter)
	{
		if(starttimer>0)
		{
			if(starttimer<=20)
			{
				if(starttimer==20)
				{
					Main.sound.playSound(26, true);
				}
				g.drawImage(GameImages.hudfight,388,250,null);
			}
			else
			{
				if(starttimer==100)
				{
					Main.sound.playSound(25, true);
				}
				g.drawImage(GameImages.hudready,388,250,null);
				
			}
			
		}
		
		
		int fanz=0;
		for(int i=0; i<fighter.length; i++)
		{
			if(fighter[i]!=null)
			{
				fanz++;
			}
		}
	
		
		/*
		if(sieg==0)
		{
			
			for(int i=0; i<fighter.length; i++)
			{
				if(fighter[i]!=null)
				{
					if(fighter[i].amBoden()&&Main.team.getSteuerung(i)!=Team.CTRL_COMP){
					//	System.out.println("Am Boden ");
						int team=Main.team.getTeam(i);
						for(int h=0; h<fighter.length; h++)
						{
							if(fighter[h]!=null)
							{
								if(fighter[h].isDead()==false){
									if(Main.team.getSteuerung(h)==3)
										{
										//System.out.println("Ist Gegner");
										if(team==Main.team.getTeam(h)){
											
									Main.team.switchSteuerung(i,h);
								//	System.out.println("geatuscht mit: "+h);
									break;
										}
									}
								}
							}
						}
					}
				}
			}
		}*/
		
		if(fanz<=4)
		{
		for(int i=0; i<fighter.length; i++)
		{
			if(fighter[i]!=null)
			{
		int leben=fighter[i].getAttributes()[1];
		int maxleben=fighter[i].getAttributes()[2];		 
		 int ki=fighter[i].getAttributes()[3];
			int maxki=FighterData.getMaxKi();
			int id=fighter[i].getAttributes()[0];
          int blitze=fighter[i].getSpecialPlus();
          int shield=fighter[i].getShield();
          if(shield>0){
        	  
        	  shield=FighterData.SHIELD-shield;
        	  
          }
          else
          {
        	  shield=0;
          }
			switch(i)
			{
			case 0: paintHUDBar(g,0,0,0,leben,maxleben,ki,maxki,blitze,shield,id); break;
			case 1: paintHUDBar(g,600,0,1,leben,maxleben,ki,maxki,blitze,shield,id); break;
			case 2: paintHUDBar(g,0,550,2,leben,maxleben,ki,maxki,blitze,shield,id); break;
			case 3: paintHUDBar(g,600,550,3,leben,maxleben,ki,maxki,blitze,shield,id); break;
			}
			}
		}  
		  
	  
	  
	
		}
		else
		{
		if(sieg==-1)
		{
			for(int i=0; i<fighter.length; i++)
			{
				if(fighter[i]!=null)
				{
					if(fighter[i].isDead()==false)
					{
				int xpos=fighter[i].getPos()[0]-50;
				int ypos=fighter[i].getPos()[1]-85;
				
				g.setColor(new Color(100,100,100));
				g.fillRect(xpos,ypos,100,20);
				
				int leben=fighter[i].getAttributes()[1];
				int maxleben=fighter[i].getAttributes()[2];		 
				 int ki=fighter[i].getAttributes()[3];
					int maxki=FighterData.getMaxKi();
				//	int id=fighter[i].getAttributes()[0];
		          int blitze=fighter[i].getSpecialPlus();
		       PlayerControl steuer=Main.team.getSteuerung(i);
		       
		       
		          g.setColor(new Color(50,50,50));
		          g.fillRect(xpos+1,ypos+1,98,8);
		          g.fillRect(xpos+1,ypos+11,98,8);
		          
		          Color c=Main.team.getTeamColor(Main.team.getTeam(i));
		          g.setColor(c);
		          g.fillRect(xpos+1,ypos-5,98,5);
		          
		          int proz=(int)(((double)leben/(double)maxleben)*98);
		          if(proz>98)
		          {
		        	  proz=98;
		          }
		          if(proz<0)
		          {
		        	  proz=0;
		          }
				 g.drawImage(GameImages.leben,xpos+1,ypos+1,proz,8,null);
				   proz=(int)(((double)ki/(double)maxki)*98);
				   if(proz>98)
			          {
			        	  proz=98;
			          }
			          if(proz<0)
			          {
			        	  proz=0;
			          }
				   g.drawImage(GameImages.ki,xpos+1,ypos+11,proz,8,null);
					 if(ki>=FighterData.getKiWastage(0)[1])
					 {
						g.setColor(Color.WHITE);
						 g.drawRect(xpos+1,ypos+11,proz,8);
					 }
			
				   g.drawImage(GameImages.multisteer[steuer.getIconID()],xpos-20,ypos,null);
				   
				   for(int h=0; h<10; h++)
				   {
					   int blx=xpos+1+h*8;
					   int bly=ypos+15;
					   if(blitze>h)
						 {
							 g.drawImage(GameImages.blitzicon[1],blx,bly,null);
						 }
						 else
						 {
							 g.drawImage(GameImages.blitzicon[0],blx,bly,null);
						 }
				   }
					}
				}
			}
			
			
		       
			
			
		}
		}
		

		  g.setFont(font);
		  for(int i=0; i<fighter.length; i++)
			{
		if(fighter[i]!=null)
		{
			int dx=fighter[i].getPos()[0];
			int dy=fighter[i].getPos()[1]+70;
			FontMetrics fm=g.getFontMetrics(font);
			String text=combo[i]+"";
			String text2=""+damage[i];
			int tl1=fm.stringWidth(text);
			int tl2=fm.stringWidth(text2);
		
			  if(damage[i]>0)
			  {
				  dx-=10;
				  g.setColor(new Color(0,0,0));	       
				  g.drawString(text,dx-tl1/2,dy );
				  g.setColor(getAttackColor(combo[i],50));	       
				  g.drawString(text,dx-tl1/2,dy+2 );
				  g.drawImage(GameImages.fighticon[1],dx+tl1/2+10,dy-22,null);
				  dy+=25;
				  dx+=10;
				  g.setColor(new Color(0,0,0));	       
				  g.drawString(text2,dx-tl2/2,dy );
				  g.setColor(getAttackColor(damage[i],500));	      
				  g.drawString(text2,dx-tl2/2,dy+2 );
				  g.drawImage(GameImages.fighticon[0],dx+tl2/2+10,dy-22,null);
			  }
			  
			  
			  if(timer[i]>0)
			  {
			  timer[i]--;
			  if(timer[i]==0)
			  {
				  if(damage[i]>maxdamage[i])
				  {
					  maxdamage[i]=damage[i];
				  }
				  damage[i]=0;
				  combo[i]=0;
			  }
			  }
		}
			}
		
		
		
		if(sieg==-1)
		{
			if(fanz>2)
			{
			int koteams=0;
			int lteam=0;
			
			for(int i=0; i<4; i++)
			{
			
				ArrayList<Integer> teamid=Main.team.getTeamFighters(i);
				boolean teamko=true;
			
				for(int h=0; h<teamid.size(); h++)
				{
					if(fighter[teamid.get(h)]!=null)
					{
					if(fighter[teamid.get(h)].isDead()==false)
					{
						teamko=false;
					
					}
					
					}
				}
		
				if(teamko)
				{
					koteams++;
				}
				else
				{
					lteam=i;
				}
			}
			
			/*if(koteams==4)
			{
				
				sieg=100;
				teamsieg="Draw!";
				String text=modus+": "+teamsieg+"   Teams: "+teamf;
				MatchHistory.writeMatchHistory(text);
				Main.sound.playSound(29, true);
			   
			}*/
			if(koteams==3)
			{
				for(int i=0; i<fighter.length; i++)
				{
					if(fighter[i]!=null)
					{
					if(Main.team.getTeam(i)==lteam)
					{
					   fighter[i].sieg();	
					}
					}
				}
				if(lteam==0)
				{
					loser=1;
				}
				else
				{
					loser=0;
				}
				startSiegTimer();
				teamsieg="Team "+(lteam+1)+" Won!";
				
				history=new HistoryFight();
				history.winner=teamsieg;
				history.mode=modus;
				ArrayList<Integer> health=new ArrayList<Integer>();
				ArrayList<Integer> fighters=new ArrayList<Integer>();
				for(int i=0; i<fighter.length; i++)
				{
					if(fighter[i]!=null)
					{
					fighters.add(fighter[i].getAttributes()[0]);
					int proz=(int) (((float)fighter[i].getAttributes()[1]/(float)fighter[i].getAttributes()[2])*100);
					health.add(proz);
					}
				}
				history.healthproz=health;
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date today = Calendar.getInstance().getTime();        
				history.date=df.format(today);
			    history.fighter=fighters;
			    history.team=Main.team.getTeams();
			    history.controll=Main.team.getControllIcons();
			    history.map=mapID;
			   
				Main.sound.playSound(27, true);
			}
			
		
			
			
			}
			else
			{
				
				
			    if(fighter[0].isDead()||fighter[1].isDead())
				{
			    	//TODO
			    	PlayerControl str1=Main.team.getSteuerung(0);
			      	PlayerControl str2=Main.team.getSteuerung(1);
			      	
			      	if(fighter[0].isDead())
		      		{
		      			loser=0;
		      			fighter[1].sieg();
		      		}
		      		else
		      		{
		      			loser=1;
		      			fighter[0].sieg();
		      		}		      		
			      	
			      	if(str1.isComputer()&&str2.isComputer())
			      	{
			      			teamsieg="Comp"+(2-loser)+" Wins!";
			      	}
			      	else if(!str1.isComputer()&&!str2.isComputer())
			      	{
			      		if(loser==0)
			      		{
			      			teamsieg="Player"+(str2.getIconID())+" Wins!";	
			      		}
			      		else
			      		{
			      			teamsieg="Player"+(str1.getIconID())+" Wins!";
			      		}
			      		
			      	}
			      	else
			      	{
			      		if(str1.isComputer())
			      		{
			      			if(loser==0)
			      			{
			      				teamsieg="Victory!";
			      			}
			      			else
			      			{
			      				teamsieg="Defeat...";
			      			}
			      			
			      		}
			      		else
			      		{
			      			if(loser==1)
			      			{
			      				teamsieg="Victory!";
			      			}
			      			else
			      			{
			      				teamsieg="Defeat...";
			      			}
			      			
			      		}
			      	}
			      	
			    	startSiegTimer();
				
					history=new HistoryFight();
					history.winner=teamsieg;
					history.mode=modus;
					ArrayList<Integer> health=new ArrayList<Integer>();
					ArrayList<Integer> fighters=new ArrayList<Integer>();
					for(int i=0; i<fighter.length; i++)
					{
						if(fighter[i]!=null)
						{
						fighters.add(fighter[i].getAttributes()[0]);
						int proz=(int) (((float)fighter[i].getAttributes()[1]/(float)fighter[i].getAttributes()[2])*100);
						health.add(proz);
						}
					}
					history.healthproz=health;
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date today = Calendar.getInstance().getTime();        
					history.date=df.format(today);
				    history.fighter=fighters;
				    history.team=Main.team.getTeams();
				    history.controll=Main.team.getControllIcons();
				    history.map=mapID;
				    Main.sound.playSound(27, true);
				}
			
			}
			
			
		}
		
	
		
		  if(sieg>=0)
		  {
			  
				
			  g.drawImage(GameImages.ko, (int)(400),(int)(300),(int)(200),(int)(120),null);
			 
			  
			  
		      if(sieg<70)
		        {	    	  
				
		    	  g.setFont(font2);
		    	  g.setColor(new Color(150,0,0));
				 FontMetrics fm=g.getFontMetrics(font2);
				 
				 g.drawString(teamsieg,500-fm.stringWidth(teamsieg)/2,440);
				 g.setFont(font);
		        }
		      
				try
				{
					//Pausenzeit
					Thread.sleep (20);
				}
				catch (InterruptedException ex)
				{
				}
				
				if(sieg==0)
				{
					for(int i=0; i<damage.length; i++)
					{
						if(timer[i]>0)
						{
								  if(damage[i]>maxdamage[i])
								  {
									  maxdamage[i]=damage[i];
								  }					
						}
						damage[i]=0;
						timer[i]=0;
					}
					Main.hideMouse(false);
					saveStatistic(fighter);
				    Main.openMenu();
				}
			
		  }
		 
				
				//killanzeige
		  if(fanz>2)
		  {
				if(minitimer.isRunning())
				{
					int f1=killer;
					int f2=opfer;
					//fighter id
					int d1=0,d2=0;
					int t1=0,t2=0;
					String s1="",s2="";
					if(fighter[f1]!=null)
					{
						d1=fighter[f1].getAttributes()[0];
						t1=Main.team.getTeam(f1);
						s1=Main.team.getSteuerung(f1).getControlName();
					}
					if(fighter[f2]!=null)
					{
						d2=fighter[f2].getAttributes()[0];
						t2=Main.team.getTeam(f2);
						s2=Main.team.getSteuerung(f2).getControlName();
					}
			
					
					int anzy=80;
					g.setFont(font);
					String as=s1+" "+killmessages[killmessage]+" "+s2;
					if(kills[f1]>1)
					{
						as=s1+" "+multikill[kills[f1]-2]+"s "+s2;
					}
					FontMetrics fm=g.getFontMetrics();
					int stx=500-fm.stringWidth(as)/2;
					int sty=anzy+30;
					g.setColor(Color.BLACK);
					g.drawString(as,stx+1,sty);
					g.drawString(as,stx-1,sty);
					g.drawString(as,stx,sty+1);
					g.drawString(as,stx,sty-1);
					g.setColor(Color.WHITE);
					g.drawString(as,stx,sty);
					
					int anzx=500-fm.stringWidth(as)/2-70;					
					g.setColor(Main.team.getTeamColor(t1));
					g.fillRect(anzx,anzy,60,50);
					g.drawImage(GameImages.faces[d1],anzx+5,anzy+5,null);
					anzx=500+fm.stringWidth(as)/2+10;
					g.setColor(Main.team.getTeamColor(t2));
					g.fillRect(anzx,anzy,60,50);
					g.drawImage(GameImages.faces[d2],anzx+5,anzy+5,null);
					
				}
		  }
		
	   }
	

	
	private void saveStatistic(Fighter[] fighter) {
		// TODO Auto-generated method stub
		for(int i=0; i<fighter.length; i++)
		{
			if(fighter[i]!=null)
			{
				int s[]=new int[8];
					int id=fighter[i].getAttributes()[0];
					
					if(fighter[i].hatGesiegt())
					{
						s[0]++;
					}
					else
					{
						s[1]++;
					}  
					s[2]=kills[i];
					s[3]=damagedone[i];
					s[4]=damagetaken[i];
					s[5]=maxdamage[i];
					if(Main.team.getSteuerung(i).isComputer()){
						pcstatistic[id]=s;
			     	}
					else
					{
						statistic[id]=s;
					}
			}
		}
		
		
	}
	
	public static int[][] getStatistic()
	{
		return statistic;
	}
	
	public static int[][] getPCStatistic()
	{
		return pcstatistic;
	}
	
	public static HistoryFight getHistory()
	{
		return history;
	}


	private Color getAttackColor(int wert, int mwert)
	{
		int farb=(int)(((double)wert/(double)mwert)*255);
		if(farb>255)
		{
			farb=255;
		}		
		Color c=new Color(255,255-farb,0);		
		return c;
	}
	


	public static void damageCounter(int spieler,int spieler2, int dam, boolean tot)
	{
		
		damage[spieler]+=dam;
		damagedone[spieler]+=dam;
		damagetaken[spieler2]+=dam;
		timer[spieler]=50;
		combo[spieler]++;
		if(tot)
		{
			kills[spieler]++;
			if(Main.settings.showKillMessage())
			{
			minitimer.start(2.5f);
			killer=spieler;
			opfer=spieler2;
			killmessage=(int)(Math.random()*killmessages.length+1)-1;
			}
		}
	}
	
	
	
	public static int getLoser()
	{
		return loser;
	}

	
	public int getNextEnemy()
    {
		if(nextwait>0)
		{
		nextwait--;
		return -1;
		}
		else
		{
		return nextenemy;
		}
	}
	
	public void resetNextEnemy()
	{
		nextenemy=-1;
	}


	
}
