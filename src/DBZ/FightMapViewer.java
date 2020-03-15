package DBZ;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Fight.Fighter;

public class FightMapViewer {

	
	
	
	public FightMapViewer()
	{
		
	}
	
	
	public void paint(Graphics g, Image b, Fighter[] f, Dimension dim,Image mapimage)
	{
		//Größte Distanz zwischen Spielern ermitteln:
		ArrayList<Integer> posx=new ArrayList<Integer>();
		ArrayList<Integer> posy=new ArrayList<Integer>();
		for(int i=0; i<f.length; i++)
		{
			if(f[i]!=null)
			{
				
				posx.add(f[i].getPos()[0]);
				posy.add(f[i].getPos()[1]);
				
			}
		}
		int maxy=0,maxx=0;
		int xpos=0,ypos=0;
		for(int i=0; i<posx.size(); i++)
		{
			for(int h=0; h<posy.size(); h++)
			{
				if(i!=h)
				{
					int m=(int) Point.distance(posx.get(i),0, posx.get(h), 0);
				   if(m>maxx)
				   {
					   maxx=m;
					 
						  xpos=(posx.get(h)+posx.get(i))/2;
					 
				   }
					 m=(int) Point.distance(0,posy.get(i),0, posy.get(h));
					   if(m>maxy)
					   {
						   maxy=m;
						   ypos=(posy.get(h)+posy.get(i))/2;
					   }
				}
			}
		}
		
		/*xpos-=100;
		ypos=-100;
		maxx+=200;
		maxy+=200;*/
		
		
		maxx+=100;
		maxy+=100;
		
		
		int minx=500;
		int miny=300;
		
		if(maxx>maxy)
		{
			if(maxx<minx)
			{
				maxx=minx;
			}
			maxy=(int)((float)maxx*0.6f);
		}
		else{
			
			if(maxy<miny)
			{
				maxy=miny;
			}
			maxx=(int)((float)maxy*(10.0f/6.0f));
		}
		
		g.drawImage(mapimage,-xpos+maxx/2,-ypos+maxy/2,null);
		BufferedImage bild=new BufferedImage(maxx,maxy,BufferedImage.TYPE_INT_ARGB);
		bild.createGraphics().drawImage(b,-xpos+maxx/2,-ypos+maxy/2,null);	
		
		g.drawImage(bild,0,0,1000,600,null);
		
		
		
	}
	
}

