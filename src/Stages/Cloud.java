package Stages;


import java.awt.Dimension;
import java.util.Random;


public class Cloud {

	private int x,y,id;
	private boolean left,leftd;
	private Dimension dim=new Dimension();
	
	public Cloud()
	{
		neu(true);
	}
	
	private void neu(boolean random)
	{
		id=(int)(Math.random()*3+1)-1;
		if((int)(Math.random()*2+1)==1)
		{
			left=true;		
			x=1000;
		}
		else
		{
			left=false;
			x=-150;
		}
		if((int)(Math.random()*2+1)==1)
		{
			leftd=true;			
		}
		else
		{
			leftd=false;
		}
		if(random)
		{
		x=(int)(Math.random()*1000+1);
		}
		y=(int)(Math.random()*350+1)-50;
		dim.setSize((int)(Math.random()*200+1)+50,(int)(Math.random()*200+1)+50);
		
	}
	
	public Dimension getDimension()
	{
		return dim;
	}
	
	public boolean getLeft()
	{
		return leftd;
	}
	
	public void move()
	{
		if(left)
		{
			x--;
		}
		else
		{
			x++;
		}
		if(x<-dim.width||x>1000)
		{
			neu(false);
		}
	}
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getID()
	{
		return id;
	}

	public void move(int x2, int y2) {
		// TODO Auto-generated method stub
		x+=x2;
		y+=y2;
		if(x<-dim.width||x>1000)
		{
			neu(false);
		}
	}
}
