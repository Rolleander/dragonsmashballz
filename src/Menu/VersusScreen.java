package Menu;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import DBZ.Main;
import Fight.Fight;
import Fight.Fighter;
import Fight.FighterData;
import Fight.Team;
import Images.GameImages;



public class VersusScreen {

	int time;
	private boolean aktiv=false;
	private FighterData fdata=new FighterData();
	private int size=0;
    private String modus;
	private Fight fight;
	private Font font=new Font("Arial",1,40);
	private Font font2=new Font("Arial",1,30);
	
	private boolean storyFight=false;
	
	public VersusScreen()
	{
		
	}
	
	public Fight getFight()
	{
		return fight;
	}
	
	private class Loop extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			time++;
			if(time==400)
			{
				cancel();
			}
		}
		
	}
	
	public void open(Fight fight)
	{
		this.fight=fight;
		this.modus=fight.getModus();
		storyFight=false;
		if(modus.length()>="Story Fight".length())
		{
		if(modus.substring(0,"Story Fight".length()).equals("Story Fight"))
		{
			storyFight=true;
		}
		}
		
		open();
	}
	
	private void open()
	{
		
		Timer t=new Timer();
		t.schedule(new Loop(), 0, 10);
   size=0;
		aktiv=true;
		time=0;
		Main.sound.stopMusic();
		Main.sound.loopSound(11);
		Main.sound.fireKiBeamSound();
	
	}
	private float winkel,winkel2;
	public void paint(Graphics g)
	{
		
		if(time>0)
		{
			if(storyFight)
			{
				g.drawImage(GameImages.storybattle,0,0,null);
				winkel+=5.5f;
				winkel2+=7.7f;
				for(int i=0; i<2; i++)
				{
			    AffineTransform affineTransform = new AffineTransform(); 
			   affineTransform.setToTranslation(330-200,170-200);
			   if(i==0)
			   {
			       affineTransform.rotate(Math.toRadians(winkel),200,200); 	
			   }
			   else
			   {
			       affineTransform.rotate(Math.toRadians(winkel2),200,200); 	
			   }
		       Graphics2D g2d=(Graphics2D) g;
				 g2d.drawImage(GameImages.glitter,affineTransform,null);
				}
				
				
				g.setColor(new Color(255,255,255));
				g.fillRect(0,590,(int)((time)*2.5),5);
			}
			else
			{
			if(fight.getFighterAmount()>2)
			{
				
				g.drawImage(GameImages.menuback,0,0,null);
				Fighter[] player=fight.getFighter();
				Team team=fight.getTeam();
				
				int i=0;
				for(int h=0; h<player.length; h++)
				{
				   if(player[h]!=null)
				   {
					
						   
					  int id=player[h].getAttributes()[0];
					int x=150;
					int y=2+i*60;
					if(time>((i)*20)+20)
					{
						if(time==((i)*20)+20)
						{
							Main.sound.fireKiAttackSound();
						}
						g.setColor(new Color(100,100,100));
						g.fillRoundRect(x,y, 700, 55, 10,5);
					
						g.setColor(team.getTeamColor(team.getTeam(i)));
						
					
						g.fillRoundRect(x+2,y+2, 696, 51, 9,4);
					g.drawImage(GameImages.faces[id],x+5,y+7,null);
					g.setColor(Color.WHITE);
								g.setFont(font2);
					g.drawString(FighterData.getName(id),x+70,y+40);
					g.drawString("Team: "+(team.getTeam(i)+1),x+350,y+40);
					g.drawImage(GameImages.multisteer[team.getSteuerung(i).getIconID()],x+480,y+20,null);
					}
					   i++;
				   }
				}
				
			}
			else
			{
				Fighter[] f=fight.getFighter();
				int id1=f[0].getAttributes()[0];
				int id2=f[1].getAttributes()[0];
				g.drawImage(GameImages.versusscreen,0,0,(int)(1000),(int)(600),null);
				
				if(time==200)
				{
					Main.sound.fireKiBeamSound();
				}
				
			int bild=time;	
		
		
			if(time>=200)
			{
				bild=200;
				g.setFont(font);
				FontMetrics fm=g.getFontMetrics(font);
				g.drawString(FighterData.getName(id1),245-fm.stringWidth(FighterData.getName(id1))/2,435);
				g.drawString(FighterData.getName(id2),745-fm.stringWidth(FighterData.getName(id2))/2,435);
				
			
				
			}
				
			if(time>=200)
			{
			size+=50;
			if(size>1000)
			{
				size=0;
			}
			}	
			Graphics2D g2d = (Graphics2D) g;
			  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 0.2f));
				
			g.drawImage(GameImages.effects[54],(int)((500-size/2)),(int)((300-size/2)),(int)(size),(int)(size),null);
			g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 1f));
	
			
			g.setColor(new Color(0,0,0));
			g.fillRect((int)((245-bild/2)),(int)((295-bild/2)),(int)((bild+10)),(int)((bild+10)));
			g.fillRect((int)((745-bild/2)),(int)((295-bild/2)),(int)((bild+10)),(int)((bild+10)));
			
				g.drawImage(GameImages.fighterselection[id1+2],(int)((250-bild/2)),(int)((300-bild/2)),(int)(bild),(int)(bild),null);
				g.drawImage(GameImages.fighterselection[id2+2],(int)((750-bild/2)),(int)((300-bild/2)),(int)(bild),(int)(bild),null);
			
			if(time==240)
			{
				Main.sound.playSpecialSound(id1);
			}
			if(time==200)
			{
				Main.sound.playSpecialSound(id2);
			}
			g.setColor(new Color(255,255,255));
			g.fillRect(0,590,(int)((time)*2.5),5);
		
			}
		}
		}
	}
	
	public boolean canClose()
	{
		if(time<400)
		{		
			return false;
		}
		else
		{
			if(aktiv)
			{
				aktiv=false;
				Main.sound.stopSound(11);
				return true;
			}
			else
			{
				return false;
			}
		
		}
	}
	
	public void close()
	{
		
		aktiv=true;
		time=0;
	}
	
	

	public boolean isActiv()
	{
		return  aktiv;
	}

	public String getModus() {
		// TODO Auto-generated method stub
		return modus;
	}

	
	
}
