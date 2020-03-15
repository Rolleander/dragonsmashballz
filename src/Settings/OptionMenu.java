package Settings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import Battle.Blood;
import DBZ.Main;
import Images.GameImages;
import Menu.MenuData;

public class OptionMenu extends MenuData {

	
	
	
	public OptionMenu()
	{
		  controlmenu=new ControlMenu();
		  
	}
	
	private ControlMenu controlmenu;
	private String[] options={"Graphic Settings","Music Settings","Controls","Gamespeed","Help","Back"};
    private int submenu=0;
	private int fps=-1;
	private int shownr=-1;
	
	public void paint(Graphics g)
	{
		if(submenu==0)
		{
		 int m=paintMenu(g,options,150,150,700,50);
		 paintTitle(g,"Settings");
		  switch(m){
		  case 0: //Graphic settings
			submenu=1; break;
		  case 1: //Music settings
			  submenu=2; break;
		  case 2: //Controls
			  submenu=3; 
			  break;
		  case 3://gamespeed
			  submenu=4;
			  break;
		  case 4:
			  submenu=5;
			  break;
		  case 5:
			  exit=true;
			  break;		  			  
		  }
		}
		else if(submenu==1)
		{
			 paintTitle(g,options[0]);
			String[] gr=new String[5];
		    String resolution="Resolution: Fullscreen";
		    if(!Main.settings.isFullscreen())
		    {
		    	resolution="Resolution: 1000x600";
		    }
		    gr[0]=resolution;
		    String blood="Blood: On";
		    if(!Main.settings.isBloodOn())
		    {
		    	blood="Blood: Off";
		    }
		    gr[1]=blood;
		    String col="Color: On";
		    if(!Main.settings.isColorOn())
		    {
		    	col="Color: Off";
		    }
		    gr[2]=col;
		    String kill="Killer Display: On";
		    if(!Main.settings.showKillMessage())
		    {
		    	kill="Killer Display: Off";
		    }
		    gr[3]=kill;
		    gr[4]="Back";
		    int m=paintMenu(g,gr,150,150,700,50);
			switch(m)
			{
			case 0:  Main.setGroesse(!Main.settings.isFullscreen()); break;
			case 1:  Blood.switchBloodOn(); break;
			case 2:  Main.switchColor(); break;
			case 3:  Main.settings.switchKillMessage(); break;
			case 4:  submenu=0; break;
			}
		}
		else if(submenu==2)
		{
			paintTitle(g,options[1]);
			String[] gr=new String[3];
			gr[0]="Music: Off";
			if(Main.settings.playMusic())
			{
				gr[0]="Music: On";
			}
			gr[1]="Sound: Off";
			if(Main.settings.playSound())
			{
				gr[1]="Sound: On";
			}
		    gr[2]="Back";
		    int m=paintMenu(g,gr,150,150,700,50);
		    switch(m)
			{
			case 0:  Main.sound.switchMusic(); break;
			case 1:  Main.sound.switchSounds(); break;
			case 2:  submenu=0; break;
			}
		}
		else if(submenu==3)
		{		
			   controlmenu.setMousePos(mx,my);
		       controlmenu.setKlick(klickn);
			   controlmenu.paint(g);
			   if(controlmenu.wantExit())
			   {
				   submenu=0;
			   }			   
		}
		else if(submenu==4)
		{
		
			 controlmenu.setMousePos(mx,my);
		       controlmenu.setKlick(klickn);		
			   controlmenu.setDragged(dragged);
			   controlmenu.paintFPS(g);
	        	fps=   controlmenu.newfps;	
			   if(controlmenu.wantExit())
			   {
				   submenu=0;
			   }
		}
		else if(submenu==5)
		{	String[] help={"Gameplay","Item & Buildmenu","Connecting Controller","Settings","Back"};
		  if(shownr>-1)
		  {
				paintTitle(g,"Help: "+help[shownr]);
				
				g.setColor(new Color(50,50,50));
				g.fillRect(18,78,964,454);
				g.drawImage(GameImages.help[0],20,80,null);
				
				 String[] choice2={"Back"};
				 int wahl=paintMenuHorz(g,choice2,10,550,300,50);
				switch(wahl)
				{
				case 0:  shownr=-1; break;
				}
				
		  }
		  else
		  {
			paintTitle(g,options[4]);	
			 int m=paintMenu(g,help,150,150,700,50);
			 if(m==4)
			 {
				 submenu=0;
			 }
			 else
			 {
				 shownr=m;
			 }
		  }
		}
	}

	boolean dragged;
	public void setDragged(boolean dragged) {
		// TODO Auto-generated method stub
		this.dragged=dragged;
	}
	
	public int getFPS()
	{
		return fps;
	}
	
	/*
	 *   case 3://blood switch
			  Blood.switchBloodOn();
			  if(Blood.getBloodOn())
			  {
				  options[3]="Blood: On";
			  }
			  else
			  {
				  options[3]="Blood: Off";
			  }
			  break;
		  case 4://blood switch
			  
			  Main.switchColor(!color);
			  if(color)
			  {
				  options[4]="Color: On";
			  }
			  else
			  {
				  options[4]="Color: Off";
			  }
			  break;
	 */
}
