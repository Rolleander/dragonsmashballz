package Battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import DBZ.Main;
import Images.GameImages;

public class DruckDuell {

	
	
    
    private ArrayList<Druck> duell=new ArrayList<Druck>();
    
    private class Druck{
    	
    	public int partner1,partner2;
    	public int zeit=150;    	
    	public int press1,press2;
   
    }
    
    private float winkel=0;
	
	public DruckDuell()
	{
		
	}
	

	public boolean isFree(int id)
	{
		boolean b=true;
		for(Druck d: duell)
		{
			if(d.partner1==id||d.partner2==id)
			{
				b=false;
				break;
			}
		}
		return b;
	}
	
     private void destroyAttack(Attack a)
     {
    	  Explosion exp=new Explosion(a.getPos()[0],a.getPos()[1],2);
		  Battle.newExplosion(exp);
		  Main.sound.playSound(8, true);
     }
     
     public boolean[] getPlayerInDuell(Attack[] att)
     {
    	boolean[] b=new boolean[10];
    	for(Druck d: duell)
    	{
    		if(d.zeit>0)
    		{
    		b[att[d.partner1].getAbsender()]=true;
    		b[att[d.partner2].getAbsender()]=true;
    		}
    	}
    	
    	return b;
     }
	
	public Attack[] duell(Graphics g, Attack[] att, boolean[] press)
	{
		//Überprüfe auf neue Druckduelle		
		for(int i=0; i<att.length; i++)
		{
			if(att[i]!=null&&isFree(i))
			{							  
	 			float xspeed=att[i].getSpeed()[0];
	 			if(xspeed>0)
	 			{// Rechtsfliegende sind die Träger des Duells
	 				
	 				for(int h=0; h<att.length; h++)
	 				{
	 					if(i!=h)
	 					{
	 					if(att[h]!=null&&isFree(h))
	 					{
	 						if(Main.team.isEnemy(att[i].getAbsender(), att[h].getAbsender()))
	 						{
	 						float xspeed2=att[h].getSpeed()[0];
	 						if(xspeed2<0)
	 						{
	 					        //Bereit für Druckduell
	 							  Rectangle rec1=att[i].getSize();
	 							  Rectangle rec2=att[h].getSize();
	 							  if(rec1.intersects(rec2))
	 							  {
	 								  if(att[i].canDruckDuell())
	 								  {
	 									  if(att[h].canDruckDuell())
	 									  {
	 										  //Wahres Druckduell
	 										  if(att[i].getAttackTyp()==Attack.TYP_AURA)
	 										  {
	 											 if(att[h].getAttackTyp()==Attack.TYP_AURA)
	 											 {
	 												 //nichts passiert, nur crash effekt
	 											   int xdiff=( att[h].getPos()[0]+att[i].getPos()[0])/2;
	 											   int ydiff=( att[h].getPos()[1]+att[i].getPos()[1])/2;
	 												 showExplosions(xdiff,ydiff);
	 											 }
	 											 else
	 											 {
	 												 //Andere Attacke zerstören
	 												 destroyAttack(att[h]);
	 												 att[h]=null;
	 											 }
	 										  }
	 										  else if(att[h].getAttackTyp()==Attack.TYP_AURA)
	 										  {
	 											  //i zerstören
	 											 destroyAttack(att[i]);
	 											 att[i]=null;
	 											 break;
	 										  }
	 										  else
	 										  {
	 										  Druck d=new Druck();
	 										  d.partner1=i;
	 										  d.partner2=h;
	 										  if(att[i].isUltimateAttack())
	 										  {
	 											  d.press1+=5;
	 										  }
	 										 if(att[h].isUltimateAttack())
	 										  {
	 											  d.press2+=5;
	 										  }
	 										  duell.add(d);
	 										  break;
	 										  }
	 									  }
	 									  else
	 									  {
	 										  // h zerstören
	 										  destroyAttack(att[h]);
	 										 att[h]=null;
	 									  }
	 								  }
	 								  else
	 								  {
	 									  if(att[h].canDruckDuell())
	 									  {
	 										  // i zerstören
	 										     destroyAttack(att[i]);
	 										    att[i]=null;
	 				 						    break;
	 									  }
	 									  else
	 									  {
	 										  if(att[i].isSpecial())
	 										  {
	 											  if(att[h].isSpecial())
	 											  {
	 												  // Beide zerstören
	 		 											destroyAttack(att[i]);
	 		 											destroyAttack(att[h]);
	 		 											 att[i]=null;
	 		 											 att[h]=null;	
	 											  }
	 											  else
	 											  {
	 													destroyAttack(att[h]);
	 													 att[h]=null;
	 											  }
	 										  }
	 										  else
	 										  {
	 										 	if(att[h].isSpecial())		
	 										 	{
	 										 		destroyAttack(att[i]);
													 att[i]=null;
	 										 	}
	 										 	else
	 										 	{
	 										 	  // Beide zerstören
		 											destroyAttack(att[i]);
		 											destroyAttack(att[h]);
		 											 att[i]=null;
		 											 att[h]=null;	
	 										 	}
	 										  }
	 										  break;
	 									  }
	 								  }
	 							  }
	 						}
	 						}
	 					}
	 					}
	 				}
	 				
	 				
	 			}
		     			
			}							
	   }
		
		
		winkel+=15;
	    for(int i=0; i<duell.size(); i++)
	    {
	    	Druck druck=duell.get(i);
	    	int at1=druck.partner1;
	    	int at2=druck.partner2;
	    	
	    	
	    	if(att[at1]!=null&&att[at2]!=null)
	    	{
	    	int x1=att[at1].getPos()[0];
	    	int y1=att[at1].getPos()[1];
	    	int x2=att[at2].getPos()[0];
	    	int y2=att[at2].getPos()[1];
	    	
	    	paintSpecialEffects(g,(x1+x2)/2,(y1+y2)/2);	   
	    	att[at1].stopMovement(true);
	    	att[at2].stopMovement(true); 
	    	float f=(float) (((Math.random()*20)-10)/5);
	    	
        	if(druck.zeit<=0)
	    	{
	    		//Finish Duell	    	
		    	if(druck.press1==druck.press2)
		    	{
		    		//Kill both
		    		destroyAttack(att[at1]);
		    		destroyAttack(att[at2]);
		    		att[at1]=null;
		    		att[at2]=null;
		    		duell.remove(i);
		    	}
		    	else
		    	{
		    		if(druck.press1>druck.press2){
		    			//nach rechts drücken
		    			f=(float) (Math.random()*10);
		    			att[at1].moveDruckDuell(f);
				    	att[at2].moveDruckDuell(-f);
				    	if(att[at2].getSrcPos()[0]-att[1].getPos()[0]<=150)
				    	{
				    		att[at1].stopMovement(false);
				    		destroyAttack(att[at2]);
				    		att[at2]=null;
				    		duell.remove(i);
				    	}
		    		}
		    		else
		    		{
		    			//nach links drücken	
		    			f=-(float) (Math.random()*10);
		    			att[at1].moveDruckDuell(f);
				    	att[at2].moveDruckDuell(-f);
				    	if(att[at2].getPos()[0]-att[at1].getSrcPos()[0]<=150)
				    	{
				    		att[at2].stopMovement(false);
				    		destroyAttack(att[at1]);
				    		att[at1]=null;
				    		duell.remove(i);
				    	}
		    		}
		    	}
	    	}
	    	else
	    	{	    	
	    		att[at1].moveDruckDuell(f);
		    	att[at2].moveDruckDuell(-f);
	    		druck.zeit--;
	    		if(press[att[at1].getAbsender()])
		    	{
		    		druck.press1++;
		    	}
		    	if(press[att[at2].getAbsender()])
		    	{
		    		druck.press2++;
		    	}
	    	}
	    	
	    }
	    	else
	    	{
	    		//end duell
	    		if(att[at1]==null)
	    		{
	    			 att[at2].stopMovement(false);
	    		}
	    		else
	    		{
	    		    att[at1].stopMovement(false);
	    		}
	    		duell.remove(i);		
	    	}
	    	
	    	
	    }
		
		return att;
	}

	/*
	 * 	int x=(ex+px)/2-size/2;
		  int y=(ey+py)/2-size/2;
	 */
	private void paintSpecialEffects(Graphics g, int x, int y)
	{
		int size=200;
		//  x+=size/2;
		//  y+=size/2;
		  if(Main.PAINT)
		  {
		  for(int h=0; h<60; h++)
		  {
			  if(h%6==0)
			  {
				  g.setColor(new Color(250,100,0));
				  
				  int[] yp=new int[4];
				  int[] xp=new int[4];
				int r=800;
				
					  xp[0]=x;
					  yp[0]=y;
					  xp[1]=x+(int)(Math.cos(Math.toRadians(h*6+winkel/3))*r);
					  yp[1]=y+(int)(Math.sin(Math.toRadians(h*6+winkel/3))*r);
					  xp[2]=x+(int)(Math.cos(Math.toRadians(h*6+6+winkel/3))*r);
					  yp[2]=y+(int)(Math.sin(Math.toRadians(h*6+6+winkel/3))*r);
					  xp[3]=x;
					  yp[3]=y;	
				g.fillPolygon(xp, yp, 4);
			  }
			 
		
		  }
		  
		Graphics2D g2d=(Graphics2D)g;	  	  
	    AffineTransform affineTransform = new AffineTransform(); 
			 affineTransform.setToTranslation(x-size/2,y-size/2);						
			 affineTransform.rotate(Math.toRadians(winkel),size/2,size/2); 	
			    affineTransform.scale((double)size/(double)70,(double)size/ (double)70);		
		      g2d.drawImage(GameImages.effects[46],affineTransform,null);
		      
		    showExplosions(x,y);
		  }
	}
	
	private void showExplosions(int x, int y)
	{
		  if((int)(Math.random()*5+1)==1)
	      {
	    	x+=(int)(Math.random()*150+1)-100;
	    	y+=(int)(Math.random()*150+1)-100;
			Explosion exp=new Explosion(x,y,6);
		    Battle.newExplosion(exp);
		    Main.sound.playSound(12, true);
	      }
	}
	

	
}
