package Menu;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Achievements.Achievements;
import Battle.Blood;
import Controlls.GamePads;
import Controlls.Keys;
import Controlls.Steuerung;
import DBZ.Main;
import DBZ.PlayerControl;
import DBZ.Sound;
import Fight.Fight;
import Fight.Fighter;
import Fight.FighterData;
import Fight.Team;
import FighterBuild.Item;
import Images.GameImages;
import Misc.Credits;
import Misc.ZeniScreen;
import Save.HistoryFight;
import Save.MatchHistory;
import Save.Profil;
import Save.ProfilLoader;
import Save.ProfilSaver;
import Settings.ControlSettings;
import Settings.OptionMenu;
import Stages.Stage;
import Story.Chapter;
import Story.Story;
import Story.StoryBattle;
import Story.Wunsch;
import Tournament.Tournament;
import Tournament.TournamentGame;
import Tournament.TournamentWorld;


public class Menu extends MenuData implements MouseListener,MouseMotionListener{

   
	   private Fight fight;
	   
	//private boolean newgame=false;
	//SubMenus
	FighterSelection selection;
	private double submenu=0;
	private boolean mainmenu=true,playintro=true;
     private boolean dragged=false;

	private TournamentGame tournament;
	private StoryMenu storymenu;
    
	private OptionMenu optionmenu;

	private ProfilLoader savereader=new ProfilLoader();
	private ProfilSaver savewriter=new ProfilSaver();
	public static MatchHistory matchhistory;
	private Profil profil;
	private Story story;
	
    private ProfilMenu profilmenu=new ProfilMenu();
	private Credits credits;
	private Intro intro=new Intro();
    private int fps=-1;
    
	public Menu()	
	{
	    System.out.println("##############################################");
	    System.out.println("Read DragonSMASHBallZ");
	    System.out.println("**********************************************");
	
	    
	    System.out.println("Read Data Path...");
	    File file=new File(Main.ordnerpfad);
	    if(!file.exists())
	    {
	    	System.out.println("Warning: No Data Path found!");
	    	System.out.println("Create new Data Path...");
	    	file.mkdirs();
	    }
	    System.out.println("Read Profil....");
	    Main.LOADING="Loading Profil";
	    try {
			profil=savereader.readProfil();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("WARNING: No Profil found!");
	        System.out.println("Create new Profil");
	    	profil=new Profil();
	    	savewriter.saveProfil(profil);
		}
	    profil.update();//Update 
	    System.out.println("Read MatchHistory....");
	    Main.LOADING="Loading Match History";
	    try {
			matchhistory=savereader.readMatchHistory();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("WARNING: No Match History found!");
	        System.out.println("Create new Match History");
	    	matchhistory= new MatchHistory();
	    	savewriter.saveMatchHistory(matchhistory);
		}
	    System.out.println("Read Achievements...");
	    try {
			Main.achievements=savereader.readAchievements();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("WARNING: No Achievements found!");
	        System.out.println("Create new Achievements");
	        Main.achievements=new Achievements();
	        savewriter.saveAchievements(Main.achievements);
		}
	    
	  optionmenu=new OptionMenu();
	    System.out.println("Load free Fighters");
	    Main.LOADING="Loading Fighters";
	    selection=new FighterSelection(profil.getFighters());
	 
	    System.out.println("Load Story");
	    Main.LOADING="Loading Story";
	    System.out.println("Story Progress: "+profil.getStoryProgress());
	    story=new Story(profil.getStoryProgress());
	    System.out.println("Load World...");
	    Main.LOADING="Loading World";
	    storymenu=new StoryMenu(story,profil.getDragonball());
	    System.out.println("Load Credits");
	    credits=new Credits();
	    
	}

public boolean[] getDragonballlist()
{
	return profil.getDragonball();
}


	
   public boolean isOpen()
   {
	   return open;
   }
   
  private float flyw=0;
   
   private String[] singleplayer={"Story Mode","Tournament","World Tournament","Training","Watch Battle","Back"};
   private String[] multiplayer={"Team Fight","Tournament","Show Network IP","Join Network Battle","Back"};
 

private boolean networkfight;
   
   private void save()
   {
	   savewriter.saveProfil(profil);
	   
	  
   }
   
   private void saveControlls()
   {
	   savewriter.saveControlSettings(Main.controlsettings);
	   savewriter.saveGameSettings(Main.settings);
   }
   
   
   
   public void paint(Graphics g)
   {
	 
	   flyw+=1.5;
	   Main.hideMouse(false);
	   if(playintro)
	   {
		   g.setFont(font);
		   intro.paint(g);
		   if(klick&&intro.onStartScreen()==null){
			   Main.hideMouse(false);
			   
			   playintro=false;
			   Main.sound.playMusic(Sound.SONG_MENU);
		   }
	   }
	   else
	   {
	   if(ZeniScreen.schowZeniScreen()){
		   
		 
		   int z=  ZeniScreen.paint(g);
		   if(z>0)
		   {
			   profil.addZeni(z);
			   save();
		   }
		   
	   }
	   else
	   {
	   
	   if(mainmenu)
	   {
		   
		 

	   g.drawImage(GameImages.title,0,0,null);	  
	   
	
	   paintGoku(g);
	   

	   
	   String[] ms={"Singleplayer","Multiplayer","Profil","Settings","Credits","Exit"};
	int m=   this.paintMainMenu(g,ms , 350, 120);
	   
	 //  int m=mainMenuSelection();
	   
	   if(m>-1)
	   {
		   
		   if(klick)
		   {
	   switch(m)
	   {
	   case 0: submenu=1; break;
	   case 1: submenu=2; break;
	   case 2: submenu=4; break;
	   case 3: submenu=3; break;
	   case 4:  //credits 
		   credits.show(profil);
		   submenu=5;
		   
	   break; 
	   case 5: save(); System.exit(0); break;
	   }
	   mainmenu=false;
	 
	   klick=false;
		   }
		   else
		   {
			  
		   }
		   
	   }
	 
	   }
	   else
	   {
		  
		   boolean tourn=false;
		   if(tournament!=null)
		   {
			   if(tournament.isRunning()==true)
			   {
				   tourn=true;
			   }
		   }
		   
		   if(tourn==false)
		   {
		   if(submenu==1)//Singleplayer
		   {
			   
				g.drawImage(GameImages.menuback,0,0,(int)(1000),(int)(600),null);
				
				   paintGoku(g);
				 int m=paintMenu(g,singleplayer,150,100,700,50);
				 paintTitle(g,"SINGLEPLAYER");
				  switch(m){
				  case 0: submenu=1.6; Main.sound.playMusic(Sound.SONG_STORY); Main.hideMouse(true); break;
				  case 1: submenu=1.1;  Main.sound.playMusic(Sound.SONG_SELECTION);  break;
				  case 2: submenu=1.8;  Main.sound.playMusic(Sound.SONG_SELECTION);  break;
				  case 3: submenu=1.4;  Main.sound.playMusic(Sound.SONG_SELECTION);  break;
				  case 4: submenu=1.5;  Main.sound.playMusic(Sound.SONG_SELECTION);  break;		
				  case 5: submenu=0;  mainmenu=true;  break;
				  
				  }
				
				  klick=false;
				  klickn=0;
				  selection.setKlick(klickn);
				  
		   }
		   if(submenu==1.6)
		   {
			   
			   storymenu.setMousePos(mx,my);
			
			   storymenu.setKlick(klickn);
			   storymenu.paint(g,profil);
			   
			 	klickn=0;
			 	if(storymenu.wantGo())
			 	{
			 		fight=storymenu.getFight();
			 		close();
			 	}
			 	
			 	Wunsch w=storymenu.hatWunsch();
			 	if(w!=null)
			 	{
			 		
			 		erfuelleWunsch(w);
			 	}
			if(storymenu.wantExit())
			{
				submenu=1;
				Main.sound.playMusic(Sound.SONG_MENU);
			}
		   }
		   if(submenu>1&&submenu<1.4||submenu==1.7)//Tournament,Power,Survival
		   {
			
			   selection.setMousePos(mx,my);
	           selection.setKlick(klickn);
	           selection.setTeamFight(false);
	          	klickn=0;
	          	if(submenu==1.2)
	          	{
	          		selection.setMulitSelection(true);
	          	}
	          	else
	          	{
	          		selection.setMulitSelection(false);
	          	}
		       if(submenu!=1.1)
		       {
		    	selection.setMapSelection(true);
		       }
		       else
		       {
		    	   selection.setMapSelection(false);
		       }
			   selection.paint(g, mx,my);				
			 
				   if(selection.wantExit()){
					   submenu=1;  		
					   Main.sound.playMusic(Sound.SONG_MENU);
				   }
				   if(selection.wantGo())
				   {
					      if(submenu==1.1)
					      {//tournament
					    	  int id=selection.getSelection(0);
					    	  tournament=new Tournament();
					    	tournament.start();
					    	
					    	Fighter f=new Fighter();
					    	f.init(id, 0,false);
					    	tournament.startTournament(f);
					      }			
					      else if(submenu==1.2){
					    	  //power battle
					    	  int id1=selection.getSelection(0);
							  int id2=selection.getSelection(1);		
					    	
							   Stage stage=selection.getStageSelection();
							   newFight(id1,id2,stage,"Power Battle");
							  close();
							  
							   }
					      else    if(submenu==1.3){
					    	  //survival
					    	  int id1=selection.getSelection(0);
					    	  int id2=0;	
					    	  
							
							   Stage stage=selection.getStageSelection();
							   newFight(id1,id2,stage,"Survival");
							   close();
							   }
					   
				   }  					   	
				   klick=false;
			   
		   }
		   if(submenu==1.4||submenu==1.5)//Training, Watch Battle 
		   {
			
			   selection.setMousePos(mx,my);
	           selection.setKlick(klickn);
	          	klickn=0;
		    	selection.setMulitSelection(true);
		    	selection.setTeamFight(false);
		    	selection.setMapSelection(true);
			   selection.paint(g,mx,my);				
			 
				   if(selection.wantExit()){
					   submenu=1;  		
					   Main.sound.playMusic(Sound.SONG_MENU);
				   }
				   if(selection.wantGo())
				   {
					   int id1=selection.getSelection(0);
					   int id2=selection.getSelection(1);		
					
						  Stage stage=selection.getStageSelection();
					   if(submenu==1.4){
					   
					   newFight(id1,id2,stage,"Training");
					   }
					   else
					   {
						  
						   newWatchFight(id1,id2,stage,"Watch Battle");
					   }
			
					  
					  close();
				   }  					   	
				   klick=false;			   
		   }
		   if(submenu==1.8)
		   {//World Tournament
			   selection.setMousePos(mx,my);
	           selection.setKlick(klickn);
	          	klickn=0;
	        
		    	selection.setMulitSelection(false);
		    	selection.setMapSelection(false);	
		    		selection.setTeamFight(false);	
		    	
			   selection.paint(g,mx,my);							 
				   if(selection.wantExit()){
					   submenu=1;  		
					   Main.sound.playMusic(Sound.SONG_MENU);
				   }
				   if(selection.wantGo())
				   {
					   ArrayList<Integer> pla =new ArrayList<Integer>();
					   pla.add( selection.getSelection(0));
					   tournament=new TournamentWorld(pla);
					   tournament.start();
				   }
		   }
		   if(submenu==2)//Multiplayer
		   {
			   
				g.drawImage(GameImages.menuback,0,0,(int)(1000),(int)(600),null);
				 paintGoku(g);
				 int m=paintMenu(g,multiplayer,150,150,700,50);
				 paintTitle(g,"MULTIPLAYER");
				  switch(m){
				  case 0: submenu=2.3;   Main.sound.playMusic(Sound.SONG_SELECTION);  break;
				  case 1: submenu=2.4;  Main.sound.playMusic(Sound.SONG_SELECTION);   break;
				  case 2: 	
					  Socket c=new Socket();
					try {
						c.getLocalAddress();
						multiplayer[2]=  InetAddress.getLocalHost().getHostName()+": ";
						c.getLocalAddress();
						multiplayer[2]+=  InetAddress.getLocalHost().getHostAddress();
					
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  break;
				  case 3: 	
					  submenu=2.5;  Main.sound.playMusic(Sound.SONG_SELECTION);  break;
				  case 4: submenu=0;  mainmenu=true;  break;
				  }
				  klick=false;
				  klickn=0;
				  selection.setKlick(klickn);
		   }
		   if(submenu>=2.1&&submenu<=2.5)
		   {
			  
			   selection.setMousePos(mx,my);
	           selection.setKlick(klickn);
	          	klickn=0;
		    	selection.setMulitSelection(true);
		    	selection.setMapSelection(true);
		    	
		    	if(submenu==2.3||submenu==2.4)
		    	{
		    		selection.setTeamFight(true);
		    		if(submenu==2.4)
		    		{
		    			selection.withoutSteer();
		    			selection.setMapSelection(false);
		    		}
		    	}
		    	else
		    	{
		    		if(submenu==2.5)
		    		{
		    			selection.setTeamFight(false);
		    			selection.setMulitSelection(false);
		    		  	selection.setMapSelection(false);
		    		}
		    		else
		    		{
		    		selection.setTeamFight(false);
		    		}
		    	}
		    
		    		
			   selection.paint(g,mx,my);							 
				   if(selection.wantExit()){
					   submenu=2;  		
					   Main.sound.playMusic(Sound.SONG_MENU);
				   }
				   if(selection.wantGo())
				   {
					   int id1=selection.getSelection(0);
					   int id2=selection.getSelection(1);		
					   
					 
						
					   Stage  stage=selection.getStageSelection();
					   
					   if(submenu==2.3)
					   {
						   ArrayList<Integer> fa=selection.getSelection();
						   ArrayList<Integer> te=selection.getTeam();
						   ArrayList<PlayerControl> st=selection.getSteuerung();
						   
						   fight=new Fight();
							  fight.newFight(fa, te, st);
							  fight.setStage(stage);
							  fight.setModus("Teamfight");
							  close();
					   }
					   else if(submenu==2.4)
					   {
						   ArrayList<Integer> pla =selection.getSelection();
						   tournament=new TournamentWorld(pla);
						   tournament.start();
						 
					   }	
					   else if(submenu==2.5)
					   {//network fight
						  
						   
						   submenu=2.9;

					   }
					   else
					   {
						   newLokalFight(id1,id2,stage,"");
						   close();
					   }
					
					   
					 
				   }  					   	
				   klick=false;
		   }
		   if(submenu==2.9)
		   {
			  
		   }
		   if(submenu==3)//Options
		   {
			   g.drawImage(GameImages.menuback,0,0,(int)(1000),(int)(600),null);				
			   paintGoku(g);
			   optionmenu.setKlick(klickn);
			   optionmenu.setDragged(dragged);
			   optionmenu.setMousePos(mx, my);
			   optionmenu.paint(g);
			   fps=optionmenu.getFPS();
			   if(optionmenu.wantExit())
			   {
				   submenu=0;  mainmenu=true;  saveControlls();
			   }				
				  klick=false;
		   }
		   else if(submenu==4)
		   {
			  profilmenu.setMousePos(mx,my);
	          profilmenu.setKlick(klickn);
	        
			   Profil p=profilmenu.paint(g,profil,matchhistory,flyw);
			   if(p!=null)
			   {
				   profil=p;
				   save();
			   }
			   
			   if(profilmenu.wantExit())
			   {
					submenu=0;  mainmenu=true;
			   }
			
			
		   }
		   else if(submenu==5)
		   {
			   //credits
			   if(credits.isRunning())
			   {
				   g.drawImage(GameImages.menuback,0,0,null);
				   this.paintGoku(g);
				   credits.paint(g);
				   if(klick)
				   {
					   credits.stop();
				   }
			   }
			   else
			   {
					submenu=0; mainmenu=true;
					Main.sound.playMusic(Sound.SONG_MENU);
			   }
		   }
	   }
		   else
		   {//Tournament
			   tournament.setMousePos(mx,my);
			   tournament.setKlick(klickn);
	          	klickn=0;		  
			   tournament.paint(g);
			   if(tournament.wantExit())
			   {
				   tournament.stop();
				   submenu=1;
			   }
			   if(tournament.wantFight())
			   {
				 fight=tournament.getFight();
			
				   close();
			   }
		   }
	   }
	  
	   }
	   }
	   klick=false;
	   dragged=false;
   }


private void paintGoku(Graphics g) {
	// TODO Auto-generated method stub
		 
	 
	  int gy=(int)(Math.sin(Math.toRadians(flyw))*5+Math.cos(Math.toRadians(flyw))*5);
	   g.drawImage(GameImages.songohan,980,gy,-306,267,null);
	   gy=(int)(Math.sin(Math.toRadians(flyw))*10+Math.cos(Math.toRadians(flyw))*10);
	   g.drawImage(GameImages.songoku,20,50+gy,null);
}


public int getFPS()
{
	return fps;
}

private void newLokalFight(int id1, int id2, Stage stage,String modus) {
	  ArrayList<Integer> fa=new  ArrayList<Integer>();
	  fa.add(id1);
	  fa.add(id2);
	  ArrayList<Integer> te=new  ArrayList<Integer>();
	  te.add(0);
	  te.add(1);
	  ArrayList<PlayerControl> st=new  ArrayList<PlayerControl>();
	  st.add(new PlayerControl(1));
	  st.add(new PlayerControl(2));
	  
	  
	  fight=new Fight();
	  fight.newFight(fa, te, st);
	  fight.setStage(stage);
	 fight.setModus(modus);
}


private void newWatchFight(int id1, int id2, Stage stage,String modus) {
	  ArrayList<Integer> fa=new  ArrayList<Integer>();
	  fa.add(id1);
	  fa.add(id2);
	  ArrayList<Integer> te=new  ArrayList<Integer>();
	  te.add(0);
	  te.add(1);
	  ArrayList<PlayerControl> st=new  ArrayList<PlayerControl>();
	  st.add(new PlayerControl());
	  st.add(new PlayerControl());
	  
	  
	  fight=new Fight();
	  fight.newFight(fa, te, st);
	  fight.setStage(stage);
		 fight.setModus(modus);
	  
}

private void newFight(int id1, int id2, Stage stage,String modus) {
	
	  ArrayList<Integer> fa=new  ArrayList<Integer>();
	  fa.add(id1);
	  fa.add(id2);
	  ArrayList<Integer> te=new  ArrayList<Integer>();
	  te.add(0);
	  te.add(1);
	  ArrayList<PlayerControl> st=new  ArrayList<PlayerControl>();
	  st.add(new PlayerControl(1));
	  st.add(new PlayerControl());
	  
	  
	  fight=new Fight();
	  fight.newFight(fa, te, st);
	  fight.setStage(stage);
		 fight.setModus(modus);
	  
  }


   
   
   public void open(int loser)
   {
	   open=true;
	  Main.sound.killSounds();
	
	  if(submenu==1.6)
	  {//Story
          
		  story.refresh();		  
		 
		 int geld= storymenu.refresh(story,loser);
		 if(geld>0)
		 {
			 ZeniScreen.addZenis(geld);
		 }
		 profil.setStoryProgress(storymenu.getStoryProgress());
	  }
	  if(tournament!=null)
	  {
		  Main.sound.playMusic(Sound.SONG_SELECTION);
	  if(tournament.isRunning())
	  {
		  if(loser==0)
		  {
			  tournament.lose();
		  }
		  else
		  {
			  tournament.win();
		  }
	  }
	  }
	  else
	  { if(submenu>1&&submenu<3&&submenu!=2)
	  {
		  if(submenu==1.6)
		  {
			  Main.sound.playMusic(Sound.SONG_STORY);
		  }
		  else
		  {
		  Main.sound.playMusic(Sound.SONG_SELECTION);
		  }
	  }
	  else
	  {
		  Main.sound.playMusic(Sound.SONG_MENU);}
	  }
	  this.save();
   }
   
 /*  public void openMain()
   {
	   open=true;
	 
	  Main.sound.killSounds();
	  if(submenu>1&&submenu<3&&submenu!=2)
	  {
		  Main.sound.playMusic(Sound.SONG_SELECTION);
	  }
	  else
	  {
	  Main.sound.playMusic(Sound.SONG_MENU);
	  }
   }*/

  public void close()
   {
	   open=false;
   }
   
   public int getKlick()
   {
	   return klickn;
   }
   
   
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		klick=true;
		klickn=e.getButton();
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx=(int) (e.getX()/Main.xf)-Main.vx;
		my=(int) (e.getY()/Main.yf)-Main.vy;
		dragged=true;
	}

	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mx=(int) (e.getX()/Main.xf)-Main.vx;
		my=(int) (e.getY()/Main.yf)-Main.vy;
	}
	

	
	public void tick(){
		
		profil.tick();
	}
	
	
	public void resetKlick() {
		klickn=0;
		klick=false;
	}
	
	
	public Fight getFight()
	{
		Fight f=fight;
		fight=null;
		return f;
	}

	public void setKey(boolean[] tastenDruck) {
		
		storymenu.makeMove(tastenDruck);
	}

	public void freischaltenAlle() {
		// TODO Auto-generated method stub
		for(int i=0; i<Main.fighteranz; i++)
		{
	profil.setFighter(i, true);
		}
		Main.sound.playSound(19, true);
		save();
	}

	public void nichtfreischaltenAlle() {
		// TODO Auto-generated method stub
		for(int i=0; i<Main.fighteranz; i++)
		{
	profil.setFighter(i, false);
		}
		profil.setFighter(0, true);
		profil.setFighter(2, true);
		
		Main.sound.playSound(20, true);
		save();
	}
	
	/*
	 * 0. siege
	 * 1. niederlagen
	 * 2. Kills
	 * 3. schaden erteilt
	 * 4. schaden erhalten
	 * 5. Maximaler Schaden
	 * 
	 */
  

	public void addStatistic(int[][] statistic, int[][] pcstatistic) {
		// TODO Auto-generated method stub
		int[][] stat=profil.getStatistic();
		int[][] stat2=profil.getPCStatistic();
		for(int i=0; i<stat.length; i++)
		{
			for(int h=0; h<8; h++)
			{
				if(h==5)
				{
					if(statistic[i][h]>stat[i][h]){	
						stat[i][h]=statistic[i][h];
					}
					if(pcstatistic[i][h]>stat2[i][h]){	
						stat2[i][h]=pcstatistic[i][h];
					}
				}
				else
				{
					stat[i][h]+=statistic[i][h];
					stat2[i][h]+=pcstatistic[i][h];
				}
			}
		}
		profil.setStatistic(stat);
		profil.setPCStatistic(stat2);
	}

	public void freischaltenItems() {
		// TODO Auto-generated method stub
		for(int i=0; i<100; i++)
		{
			profil.setItem(i, true);
		}
		Main.sound.playSound(22, true);
		save();		
	}
	
	public void cheatMoney()
	{
		profil.addZeni(99999);
		Main.sound.playSound(21, true);
		save();
	}
	
	
	
	   
	  private void erfuelleWunsch(Wunsch w) {
		// TODO Auto-generated method stub
		  int art=w.getArt();
		  int wert=w.getWert();
		  switch(art)
		  {
		  case Wunsch.ART_GELD: profil.addZeni(wert);  break;
		  }
		  
	save();	
	}
	  
	  public boolean isNetworkFight()
	  {
		  return networkfight;
	  }
	  
	  
	
	  public int[] getFighterItems(int fighter)
	  {
		  return profil.getBuilds()[fighter].getBuild();
	  }
	  
	

	public void open() {
		// TODO Auto-generated method stub
		  open=true;
		  Main.sound.killSounds();
		  Main.sound.playMusic(Sound.SONG_MENU);
		  networkfight=false;
		  this.save();
	}

	
	private PauseMenu pause=new PauseMenu();
	public boolean paintPauseMenu(Graphics g) {
		// TODO Auto-generated method stub
		boolean close=false;
		pause.setMousePos(mx,my);
		pause.setKlick(klickn);
		pause.paint(g);		
	    close=pause.wantExit();	
		return close;
	}

	public void openMainMenu() {
		// TODO Auto-generated method stub
		mainmenu=true;
		submenu=0;
		if(tournament!=null)
		{
		tournament.stop();
		tournament=null;
		}
		open();
	}

	public void addHistoryFight(HistoryFight history) {
		// TODO Auto-generated method stub
		if(history!=null)
		{
		matchhistory.addFight(history);
		savewriter.saveMatchHistory(matchhistory);
		}
		
	}

	
	
}

