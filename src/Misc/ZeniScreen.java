package Misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;

public class ZeniScreen {

	private static int zeni, zdauer,zshow;
	private static Font font3=new Font("Arial",1,25);
	
	public static void addZenis(int z)
	{
		zeni=z;
		zdauer=200;
		zshow=zeni;
		Main.sound.playSound(17, true);
		
	}
	
	public static boolean schowZeniScreen()
	{
		if(zdauer>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int paint(Graphics g)
	{
		int x=200;
		int y=240;
		g.drawImage(GameImages.messagebox,x,y,null);
		g.drawImage(GameImages.menuicons[0],x+30,y+40,null);
		g.setFont(font3);
		g.setColor(Color.BLACK);
		g.drawString("You earned "+zshow+" Zeni!",x+150,y+50);
		if(zdauer>0)
		{
		zdauer--;
		
		}
		
		int z=zeni;
		zeni=0;
		return z;
	}
	
}
