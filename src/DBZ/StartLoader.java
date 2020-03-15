package DBZ;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Scanner;

public class StartLoader {

	public StartLoader()
	{
		
	}
	
	private float winkel,radius=120;;
	private Font font=new Font("Arial",Font.BOLD,23);
	private Font font2=new Font("Arial",Font.BOLD,15);
	
	
	public void paint(Graphics g)
	{
		int xmid=150;
		int ymid=100;
		g.setColor(new Color(100,100,100));
		g.fillRect(0,0,xmid*2,ymid*2);
		
		 String   loading=Main.LOADING;
		
		g.setFont(font);
		FontMetrics fm=g.getFontMetrics();
		String n="Game is loading...";
		g.setColor(new Color(50,50,50));
		g.drawString(n, xmid-fm.stringWidth(n)/2+2, ymid+10);
		int anz=15;
		int size=15;
		g.setColor(new Color(80,80,80));
		int r=(int)radius+10;
		g.fillOval(xmid-r,ymid-r/2,r*2,r);
		g.setColor(new Color(100,100,100));
		r=(int)radius-10;
		g.fillOval(xmid-r,ymid-r/2,r*2,r);

		for(int i=0; i<anz; i++)
		{
			float w=winkel+(180/anz)*i;
			int x= (int) (Math.cos(Math.toRadians(w))*radius);
			int y= (int) (Math.sin(Math.toRadians(w))*radius/2);
			int cm=i*10;
	
			
			g.setColor(new Color(255-cm,255-cm,255-cm));
			
			g.fillOval(x+xmid-size/2,y+ymid-size/2,size,size);
			cm/=2;
			g.setColor(new Color(255-cm,255-cm,255-cm));
			g.drawOval(x+xmid-size/2,y+ymid-size/2,size,size);
		}
		
		g.setColor(Color.WHITE);
		winkel+=3;
		if(winkel>=360)
		{
			winkel=0;
		}
	
		g.drawString(n, xmid-fm.stringWidth(n)/2, ymid+12);
		g.setFont(font2);
		 fm=g.getFontMetrics();
		g.drawString(loading, xmid-fm.stringWidth(loading)/2,ymid+85);
	}
	
}
