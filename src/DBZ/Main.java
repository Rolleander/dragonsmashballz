	package DBZ;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Achievements.Achievements;
import Battle.Attack;
import Battle.Battle;
import Battle.Blood;
import Controlls.ControlSheet;
import Controlls.GamePads;
import Controlls.GamepadSheet;
import Controlls.KeyboardSheet;
import Controlls.Keys;
import Controlls.PlayerMovement;
import Fight.Fight;
import Fight.Fighter;
import Fight.Team;
import FighterBuild.Item;
import FighterBuild.ItemData;
import Images.GameImages;
import KI.EasyKI;
import KI.FighterKI;
import KI.HeavyKI;
import Menu.HUD;
import Menu.Menu;
import Menu.VersusScreen;
import Save.ProfilLoader;
import Save.ProfilSaver;
import Settings.ControlSettings;
import Settings.GameSettings;
import Stages.Stage;
import Story.Dialog;
import Story.Zuschauer;




public class Main extends JFrame{

	/**
	 * 
	 */
	public static	String ordnerpfad=System.getProperty("user.home")+"/BRollGames/DragonSMASHBall";
	private static BufferedImage screen =new BufferedImage(300,200,BufferedImage.TYPE_INT_RGB);
    private static Dimension paintDimension=new Dimension(0,0); 
    public static String LOADING="";
	private static final long serialVersionUID = 1L;

	public static final String GAMEVERSION = "0.92";
	  static String bf="/DBZ/Ressourcen/";
	//Display
	public static double xf,yf;
	public static FighterKI currentKI=new HeavyKI();
	public static int resolution[]=new int[2];
	public static int fullresolution[]=new int[2];
	public static boolean PAINT=true;
	//Bilder
	public static int fighteranz=66;
		private static Image icon;
	public static Sound sound;
	

	private static Keys keys;
	private static GamePads gamepads;

	public static ControlSettings controlsettings;
	public static GameSettings settings;
    public static Achievements achievements;
	
	private PlayerMovement playerAction=new PlayerMovement();
	private Fighter[] player=new Fighter[4];
	private HUD huds=new HUD();
	private Battle battle=new Battle();
	private static Menu menu;
    private Zuschauer zuschauer;
    
   
	private static boolean fightPause=false;
	
	private Stage stage;
    private VersusScreen vscreen=new VersusScreen();
  
    private DialogViewer dialogviewer=new DialogViewer();
	public static Team team;
	private StartLoader startloader=new StartLoader();
	
    //Mulitplayer    
    public static Main main;
    private Font errorfont=new Font("Arial",1,15);
   
  	private BufferedImage mapimage;
 	public static void main(String[] args)
	{
	    System.out.println("##############################################");
	    System.out.println("Launch DragonSMASHBallZ");
	    System.out.println("**********************************************");
	    System.out.println("Create Frame...");

		 main=new Main();
		
	Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
	   
		 main.setSize(300,200);
		 main.paintDimension.setSize(300, 200);
		   main.setLocation(dimension.width/2-150, dimension.height/2-150);
		   fullresolution[0]=dimension.width;
		   fullresolution[1]=dimension.height;
		//  main.setUndecorated(true);
		main.setVisible(true);
		main.setEnabled(true);
		main.setResizable(false);  
		
		URL filename = Main.class.getResource(bf+"icon.png");
	   
	
		icon=Toolkit.getDefaultToolkit().getImage( filename );	
		main.setIconImage(icon);
		main.setTitle("Dragon SMASH Ball Z");
	  /*    main.addWindowListener(     
			         new WindowAdapter() {
			            public void windowClosing( WindowEvent e ) 
			            {  
			               System.exit( 0 );
			            }
	
			         }
			    );*/
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.createBufferStrategy(2);
	
	    resolution[0]=1000;
	    resolution[1]=600;	    
	 
	    xf=(double)dimension.getWidth()/(double)resolution[0];
	    yf=(double)dimension.getHeight()/(double)resolution[1];	    
	     
	    System.out.println("Initialisize...");
		   
	    main.setContentPane(
	    		new JPanel(){	    			
	    			public void paintComponent(Graphics g)
	    			{
	    				paint(g);
	    			}    			
	    		}
	    		);
	   
	    main.init();
	    
	}
 	private boolean gameLoaded=false;
 	private class Loader implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			 
		
			ProfilLoader loader=new ProfilLoader();
			ProfilSaver saver=new ProfilSaver();
			System.out.println("Load Game Settings...");
			LOADING="Loading Settings";
			try {
				settings=loader.readGameSettings();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				settings=new GameSettings();
				 System.out.println("No  Game Settings found!");
				   System.out.println("Create new Game Settings...");
			}
			
			menu=new Menu();
		    System.out.println("Load Controller...");
		    LOADING="Loading Controller";
		    keys=new Keys();
		    gamepads=new GamePads();	
		    gamepads.search();
		    System.out.println("Read Controller Settings...");		   
		    try {
				controlsettings=loader.readControlSettings();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
			   System.out.println("No Controller Settings found!");
			   System.out.println("Create new Controller Settings...");
			   controlsettings=new ControlSettings();
			   saver.saveControlSettings(controlsettings);	   
			}
		    
		    System.out.println("Focusing...");
		    setFocusable(true);
		    requestFocus();	
		    System.out.println("Render Images...");
		    LOADING="Loading Images";
		    new GameImages(main);		    
		    System.out.println("Load Music & Sounds.. ");
		  
		    sound=new Sound();		    
		    System.out.println("Load Listeners...");
		    addKeyListener(keys);
		    addMouseListener(menu);
		    addMouseMotionListener(menu);		
		    hideMouse(true);	   
		    huds=new HUD();
		    System.out.println("START Game");
		    screen =new BufferedImage(1000,600,BufferedImage.TYPE_INT_RGB);
		    
		    doStartSettings();
			gameLoaded=true;
			
		}

		
 		
 	}
 	
 	private void doStartSettings() {
		// TODO Auto-generated method stub
 		setGroesse(false);
		changeFPS(settings.getFPS());
		sound.setMusicAndSound();
		switchColor(settings.isColorOn());
		Blood.setBloodOn(settings.isBloodOn());
	}
 	
 	
	
	public void init()
	{
		Thread t=new Thread(new Loader());
		t.start();
		run();		
	}
	
	public static int getPressedKey()
 	{
 		return keys.getLastKey();
 	}
 	
 	public static int getGamePadButton(String nr)
 	{
 		return gamepads.getPressedButton(nr);
 	}
 	
 	public static void refreshGamepads()
 	{
 		gamepads.search();
 	}
 	
 	public static ArrayList<String> getGamePadList()
 	{
 		return gamepads.getGamepadNames();
 	}
	
	public static Rectangle getRectangle()
	{
		return main.getBounds();	
	}
	
	public static boolean hide=false;
	
	public static void hideMouse(boolean h)
	{
		Cursor c =null; 
		if(h)
		{
			c = Toolkit.getDefaultToolkit().createCustomCursor(
		              new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB),
		                new Point(1, 1), "Custom Cursor"); 
			
		}
		else
		{
			 c = Toolkit.getDefaultToolkit().createCustomCursor(
		              GameImages.mouse0,
		                new Point(1, 1), "Custom Cursor"); 
		}
		hide=h;
		 main.setCursor(c);
	}
	
	
	
	public static int vx;
	public static int vy;
	
	public static void setGroesse(boolean full)
	{	
		if(full)
		{
		  
			main.setVisible(false);
		//	main.revalidate();
			main.dispose();
			main.setSize(fullresolution[0], fullresolution[1]);
		    xf=(double)fullresolution[0]/(double)resolution[0];
		    yf=(double) fullresolution[1]/(double)resolution[1];	
		    main.setLocation(0, 0);
		    main.repaint(0, 0, fullresolution[0], fullresolution[1]);
		    paintDimension.setSize(fullresolution[0], fullresolution[1]);
		    main.setUndecorated(true);
		    main.setVisible(true);    
	        settings.setResolution(true);
		    vx=0;
		    vy=0;
		}
		else
		{
			main.setVisible(false);
		//	main.revalidate();
			main.dispose();
			main.setSize(resolution[0]+6, resolution[1]+27);
			xf=1;
			yf=1;
			paintDimension.setSize(resolution[0], resolution[1]);
			main.setLocation((fullresolution[0]-resolution[0])/2, (fullresolution[1]-resolution[1])/2);
			 main.setUndecorated(false);
			 main.setVisible(true);
			 settings.setResolution(false);
			 vx=3;
			 vy=24;
		}
		    
	}
	

	private void newFight(Fight fight,String modus)
	{
		player=fight.getFighter();
		stage=fight.getStage();
		team=fight.getTeam();
		zuschauer=fight.getZuschauer();
	
		Dialog d=fight.getDialog();
		if(d!=null)
		{
			dialogviewer.setDialog(d);
		}
    	mapimage=new BufferedImage(stage.getDimension().width,stage.getDimension().height,BufferedImage.TYPE_INT_ARGB);
		stage.paint(mapimage.createGraphics());
		
		sound.playBattleMusic();
		Battle.reset();
		   for(int i=0; i<player.length; i++)
					{
					   if(player[i]!=null)
					   {
						   player[i].setMapBorder(stage.getDimension());
						   player[i].setMapFloor(stage.haveFloor());
						   
						   if(team.getSteuerung(i).isComputer())
							  {
							
								  player[i].setComputer();
								  player[i].setComputerZiel(getComputerZiel(i,-1));
							  }					   
					   }
	
					}
		 
		    huds.open(player,modus,fight.getFightModus(),fight.getStage().getID());
		   
		    Battle.clearExplosions();
		    Blood.resetBlood();		    
	}
	

	private int zeit,zeit2;
	
	
	public static void switchColor()
	{
		settings.switchColorMode();
		if(!settings.isColorOn())
		{
			screen =new BufferedImage(1000,600,BufferedImage.TYPE_BYTE_GRAY);
		}
		else
		{
			screen =new BufferedImage(1000,600,BufferedImage.TYPE_INT_RGB);
		}
	}
	
	private void switchColor(boolean on)
	{
		if(!on)
		{
			screen =new BufferedImage(1000,600,BufferedImage.TYPE_BYTE_GRAY);
		}
		else
		{
			screen =new BufferedImage(1000,600,BufferedImage.TYPE_INT_RGB);
		}
	}
	
	
	public void paint(Graphics p)
	{
		 Graphics g=screen.getGraphics();
      if(!gameLoaded)
      {
    	 if(startloader!=null)
    	 {
    		 startloader.paint(g);
    	
    	 } 		 
      }
      else
      {
    	 
    	  gamepads.run();
		if(menu.isOpen())
		{
			int fps=menu.getFPS();
			if(fps!=-1)
			{
				changeFPS(fps);
			}
		  if(keys!=null)
		  {
			keys.takt();
			int cheat=keys.activateCheat();
			if(cheat>0)
			{
				switch(cheat)
				{
				case 1: 
					System.out.println("CHEAT! - All Characters unlocked!");
					menu.freischaltenAlle();
					break;
				case 2: 
					System.out.println("CHEAT! - All Characters locked!");
					menu.nichtfreischaltenAlle();
					break;	
				case 3: 
					System.out.println("CHEAT! - All Items unlocked!");
					menu.freischaltenItems();
					break;	
				case 4: 
					System.out.println("CHEAT! - +99999 Zeni");
					menu.cheatMoney();
					break;	
				}
			
			}
		  }
		menu.paint(g);	
		
	  	menu.setKey(this.getPlayerControll(1));
		
	   Fight f=menu.getFight();
		if(f!=null)
		{//Battle start
			hideMouse(true);
			vscreen.open(f);   		
			
		}
	
		}
		else
		{
			//cheaten verhindern : nur im menü erlaubt
			keys.activateCheat();
		
			
			if(menu.isNetworkFight()==false)
			{
			
			if(vscreen.isActiv())
			{
				keys.getLastKey(); //verhindern von sofort pause öffnen danach
				vscreen.paint(g);
				if(vscreen.canClose())
				{//Fight begin
				   newFight(vscreen.getFight(),vscreen.getModus());
				}
			}
			else
			{
				
				if(keys.getLastKey()==KeyEvent.VK_ESCAPE&&huds.start())
				{				
				fightPause=!fightPause;
				if(fightPause)
				{
					hideMouse(false);
				}
				else
				{
					hideMouse(true);
				}
				sound.killSounds();
				}		
				
				if(fightPause)
				{
					if(menu.paintPauseMenu(g))
					{
						fightPause=false;
					}
				}
				else
				{
				if(dialogviewer.stopBattle()==false&&huds.start())
				{
					controllPlayer();
				}

	   	         Graphics gfight=g;
				stage.paintStage(gfight);
				stage.paintOverworld(gfight,player);
			 
				if(zuschauer!=null)
				{
					zuschauer.paint(gfight);
				}
				for(int i=0; i<player.length; i++)
				{
					if(player[i]!=null)
					{
						player[i].paint(gfight);
					}
				}
		
				player=battle.paint(gfight,player);
				Blood.paint(gfight);
				battle.getSpecialEffect().paint(gfight);
				
			//	fightmapviewer.paint(g, fightmap, player,stage.getDimension(),mapimage);
				
				
			/*	if(fight!=null)
				{
					player=fight.usePlayMode(player);
				}*/
				
			    dialogviewer.setKlick(menu.getKlick());	    
				player=dialogviewer.paint(g,player);
				if(dialogviewer.stopBattle())
				{
				for(int i=0; i<player.length; i++)
				{
					if(player[i]!=null)
					{
						player[i].makeNoMove();
					}
				}
				}
			
				huds.paint(g, player);
		
				}
			}
			}
			else
			{
				
			}
			
		}	
		menu.resetKlick();
		//Spielzeit weiterzählen
		zeit=(int)System.currentTimeMillis();
		if(zeit/1000!=zeit2/1000)
		{
			menu.tick();
	    	
		}
		zeit2=(int)System.currentTimeMillis();
		
		
	
		String logger=ErrorLogger.getError();
	
		if(logger!=null)
		{
			
			g.setFont(errorfont);
			g.setColor(Color.BLACK);
			FontMetrics fm=g.getFontMetrics(errorfont);
			int leng=fm.stringWidth(logger);
			g.fillRect(0,580,leng+5,20);
			g.setColor(Color.RED);
			g.drawString(logger,2,595);
		}
		
	
		
		
      }
      p.drawImage(screen,vx,vy,paintDimension.width,paintDimension.height,null);
      
	}
	



	
	private int getComputerZiel(int id, int ziel) {
		
		boolean set=false;
		for(int i=0; i<player.length; i++)
		{
			if(player[i]!=null)
			{
				if(team.isEnemy(id, i))
				{
					if(set==false)
					{
						ziel=i;
						set=true;
					}
					else
					{
					if((int)(Math.random()*2+1)==1)
					{
					ziel=i;	
					}
					}
				}
			}
		}	
		return ziel;
	}


	
	private boolean[] getPlayerControll(int nr)
	{
		boolean[] controll=new boolean[10];		
		nr-=1;
		ArrayList<ControlSheet> sheets=	controlsettings.getSheets();
		for(ControlSheet sheet: sheets)
		{	
			if(sheet.isActive()&&sheet.getPlayer()==nr)
			{
			if(sheet instanceof KeyboardSheet)
			{
				//Tastatursteuerung
				KeyboardSheet keysheet=(KeyboardSheet)sheet;
				int tasten[]=keysheet.getKeys();
				for(int i=0; i<tasten.length; i++)
				{
					if(tasten[i]!=-1)
					{
					controll[i]=keys.isKeyPressed(tasten[i]);
					}
				}				
			}
			else{				
				//Gamepadsteuerung
		
				GamepadSheet padsheet=(GamepadSheet)sheet;
				String gamepadname=padsheet.getPadname();		
				controll=gamepads.getGamepadContol(gamepadname,padsheet);
			}
			break;
			}			
		}
		return controll;
	}
	
	private void controllPlayer()
	{
		Attack[] attacks=Battle.getAttacks();
		for(int i=0; i<player.length; i++)
		{
			if(player[i]!=null)
			{				
				if(team.getSteuerung(i).isComputer())
				{
					int ziel=player[i].getComputerZiel();		
					player[i].makeComputerMove(player[ziel], attacks);
					if(player[ziel].isDead()||(int)(Math.random()*500+1)==1)
					{
						player[i].setComputerZiel(getComputerZiel(i,ziel));
					}
				}
				else
				{		
					
					int control = team.getSteuerung(i).getPlayerControls();
					boolean[] moves=getPlayerControll(control);
					int action=playerAction.getAction(moves);
					player[i].makeMove(action);				
			     
				}
			}
		}
	}
	



	public static void openMenu()
	{
		int l=HUD.getLoser();		
		menu.addStatistic(HUD.getStatistic(),HUD.getPCStatistic());		
		menu.addHistoryFight(HUD.getHistory());
		menu.open(l);
	}
	
	public static ArrayList<Item> getFighterItems(int id) {
		// TODO Auto-generated method stub
	   int[] items=menu.getFighterItems(id);
	   ArrayList<Item> item=new ArrayList<Item>();
	   ItemData idata=new ItemData();
	   if(items!=null)
	   {
	   for(int i=0; i<items.length; i++)
	   {
		   item.add(idata.getItems().get(items[i]));
	   }
	   }
	   return item;
	}
	
	
	
	
	/* private Image dbImage;
	 private Graphics dbg;
	  public void update (Graphics g)
		{
	    	// DOUBLE BUFFER gegen Bildschirmflackern
		    if (dbImage == null)
			{
				dbImage = createImage (this.getSize().width, this.getSize().height);
				dbg = dbImage.getGraphics ();
			}
	    	dbg.setColor (getBackground ());
			dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);
	    	dbg.setColor (getForeground());
			paint (dbg);
	    	g.drawImage (dbImage, 0, 0, this);
		}*/
	    
	    
	 
	private   java.util.Timer gametimer;
	private  Loop gameloop;
	
		double t= System.currentTimeMillis();
		public void run ()
		{
			
			gameloop=new Loop();
			gametimer = new Timer();   
			gametimer.schedule(gameloop, 0, 1000 / 70);
			 
		}
		
		public  void changeFPS(int newfps)
		{
		//	gametimer.cancel();
			//gametimer.cancel();
			settings.setFPS(newfps);
			gameloop.cancel();
			gametimer.cancel();
			
			gameloop=new Loop();
			gametimer = new Timer();   
			gametimer.schedule(gameloop, 0, 1000 / newfps);
			
		}
		
		
		private class Loop extends java.util.TimerTask{   
			
			
			
			public void run() 
			//this becomes the loop  
			{     

				repaint();
			}
						
		}


		public static void cancelFight() {
			// TODO Auto-generated method stub			
			menu.openMainMenu();
			fightPause=false;
		}

	

		public static FighterKI getFighterKI()
		{
			
			FighterKI ki=null;
			
			if(currentKI instanceof HeavyKI)
			{
				ki=new HeavyKI();
			}
			else if(currentKI instanceof EasyKI)
			{
				ki=new EasyKI();
			}
			return ki;
		}
		
	
}
