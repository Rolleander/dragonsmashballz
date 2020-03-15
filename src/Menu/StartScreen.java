package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import DBZ.Main;
import Images.GameImages;

public class StartScreen {

	private boolean finish=false;
	private boolean started=false;
	
	
	public StartScreen()
	{
		
	}
	
	int yp=0;
	private int time;
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1000,600);
		
		if(Main.main.prepareImage(GameImages.brolllogo, null))
		{		
			if(started==false)
			{
				Main.sound.playSound(24, true);
				started=true;
				Timer timer=new Timer();
				timer.schedule(new Loop(),0, 100);
			}
			
			g.drawImage(GameImages.brollintro,0,yp-600,null);
			g.drawImage(GameImages.brollintro,0,yp,null);
			yp+=15;
			if(yp>600)
			{
				yp=0;
			}
			int yp=50;
			g.setColor(new Color(43,43,43));
			g.fillRoundRect(245,195+yp,510,110,10,10);
			g.setColor(new Color(100,100,100));
			g.fillRoundRect(250,200+yp,500,100,10,10);
			g.drawImage(GameImages.brolllogo,250,200+yp,null);
			g.setColor(new Color(0,0,0));
			g.drawRoundRect(245,195+yp,510,110,10,10);
		    if(time>=75)
		    {
		    	g.setColor(new Color(255,255,255,255-(85-time)*25));
		    	g.fillRect(0,0,1000,600);
		    }
		}
		
	}

	public boolean finish() {
		// TODO Auto-generated method stub
		return finish;
	}

	private class Loop extends TimerTask{
        private int t=0;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			time++;
			if(time==85)		
			{
				finish=true;
				cancel();
			}
		}
		
	}
	
}
