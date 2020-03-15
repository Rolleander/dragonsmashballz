package Menu;

import java.awt.Color;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;

public class PauseMenu extends MenuData{

	
	public PauseMenu()
	{
		
	}
	
	private String[] m={"Return to Mainmenu","Frame:  ","Music:","Sound: ","Back"};
	
	public void paint(Graphics g)
	{
		int x=300;
		int y=100;
		g.setColor(new Color(50,50,50));
		g.fillRoundRect(x-5,y-5,410,410,100,100);
	
		g.setColor(new Color(100,100,100));
		g.fillRoundRect(x,y,400,400,100,100);
		g.setColor(new Color(50,50,50));
		g.fillRoundRect(x-5,y-5,410,60,100,50);
		g.setColor(new Color(150,150,150));
		g.fillRoundRect(x,y,400,50,100,50);
		
		g.drawImage(GameImages.pause,x+110,y,null);
		if(Main.settings.isFullscreen())
		{
			m[1]="Frame: Fullscreen";
		}
		else
		{
			m[1]="Frame: Standard";
		}
		if(Main.settings.playMusic())
		{
			m[2]="Music: On";
		}
		else
		{
			m[2]="Music: Off";
		}
		if(Main.settings.playSound())
		{
			m[3]="Sounds: On";
		}
		else
		{
			m[3]="Sounds: Off";
		}
	
		int wahl=this.paintMenu(g, m, x+20,y+75,360,50);
		switch(wahl)
		{
		case 0: Main.cancelFight(); break;
		case 1: Main.setGroesse(!Main.settings.isFullscreen()); break;
		case 2: Main.sound.switchMusic(); break;
		case 3: Main.sound.switchSounds(); break;
		case 4: exit=true; break;
		}
	}

	
}
