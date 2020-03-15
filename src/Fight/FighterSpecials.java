package Fight;

import Battle.Attack;
import Battle.Battle;
import Battle.Damage;
import Battle.Explosion;
import Battle.KiAttack;
import Battle.KiAura;
import Battle.KiBeam;
import Battle.Smash;
import DBZ.Main;


public class FighterSpecials {

	public FighterSpecials()
	{
		
	}
	
	public int spezialAttack(boolean original, int id,int[] damage,  double specialplus,float[] pos, boolean left, int spielerid)
	{
		
		int[] p=FighterData.getFirePos(id, original);
		
	
		
		int specialbild=21;
		int lplus=0;
		
		
		
		
		pos[1]-=100;
		
		pos[1]+=p[1];
		
		if(left)
		{
			pos[0]+=100;
			pos[0]-=p[0];
			lplus=-50;
		}
		else
		{
			pos[0]-=100;
			pos[0]+=p[0];
			lplus=50;
		}
		
		
		
			if(original)
			{
			Attack a=null;
		
			switch(id)
			{
			case 0: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/4,0,42,170);
			a.setExplosion(new Explosion(0,0,5)); a.setCharge();
			break;//Big Bang Attack   
			case 1:
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,1,130); break;//Final Flash
			case 2: a=new KiAttack((int)pos[0],(int)pos[1]-100,new Damage(damage[3],(int) (lplus/2),2),lplus/6,2,45,(int) (250*specialplus)); 
			a.setExplosion(new Explosion(0,0,4));
			a.rotate(5); a.setCharge();
			break;//Spirit Bomb
			case 3: a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,160); break;//Kamehameha
			case 4: a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,120); break;//masenko
			case 5: a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,1,120); break;//Super Kamehameha
			case 6: a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,2,130); break;//Big Tree Cannon
			case 7: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/4,0,40,150); 
			a.setExplosion(new Explosion(0,0,3)); a.setCharge();
			break;//Burning Cupple
			case 8: 
				
				a=new KiAura((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),47,(int) (250*specialplus));
				break;           //Perfect Barrier
			case 9:
				for(int i=0; i<90; i++)
				{
					float speedx=(float) (Math.cos((float)i)*3);
					float speedy=(float) (Math.sin((float)i)*3);
					
					 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int)speedx,(int)speedy),speedx*3,speedy*3, 46, 70);
					a.setWait(i/5);
					a.setSpecial();
					a.setTrueSpecial();
					a.setAbsender(spielerid);
					a.cantDruckDuell();
					a.rotate(25);
					if(i<89)
					{
					Battle.newAttack(a);
					}
				}
				
				break;
			case 10: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),3),lplus/5,2,48,(int) (160*specialplus));  
			a.setExplosion(new Explosion(0,0,4));
			a.rotate(15); a.setCharge();
			break;//Deathball
			case 11: a=new KiBeam((int)(pos[0]),(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,2,20); //Höllenspirale
			a.setPower(999999);  
			
			break;
			case 12: 
				 a=new KiBeam((int)(pos[0]),(int)pos[1],new Damage(10,(int) (lplus/2),0),lplus/6,KiBeam.CYAN_BEAM,70);
					a.setAbsender(spielerid);
					a.setSpecial();
				 Battle.newAttack(a);	
				 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/6,0,72,170); 
					a.setExplosion(new Explosion(0,0,3));
				 break;//Gama Blaster			
			case 13:
				//unendlichkeitskugel
				for(int i=0; i<10; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,59,100);
					a.setWait(i*20);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setTrueSpecial();
					if(i<9)
					{
					Battle.newAttack(a);
					}
				}	
				break;
			case 14: //zerstöre den planeten
				 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),3),lplus/6,2,46,(int) (200*specialplus));  
					a.setExplosion(new Explosion(0,0,4));
					a.rotate(12); a.setCharge();
				break;
			case 15://Höllengranate
				for(int i=0; i<5; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,44,100);
					a.setWait(i*5);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setTrueSpecial();
					if(i<4)
					{
					Battle.newAttack(a);
					}
				}	
				break;
			case 16: //Höllenspirale
				 a=new KiBeam((int)(pos[0]),(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,2,30); //Höllenspirale
					a.setPower(899999);  
				break;
			case 17: //super kamehameha 
			 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,140); break;
			 
			case 18: //master of the universe
			
				for(int i=0; i<5; i++)
				{
					a=new KiBeam((int)pos[0],(int)pos[1]+(int)(Math.random()*30+1)-15,new Damage(damage[3],(int) (lplus/2),0),lplus/3,(int)(Math.random()*2+1)+2,(int)(Math.random()*50)+50); 
					a.setWait(i*15);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setTrueSpecial();
					if(i<5)
					{
					Battle.newAttack(a);
					}
				}	
				break;
				
			case 19://Masenko!!
			 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,2,140);
			
			 break;
			
			case 20: //kamehameha
				 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,100);			
				break;
				
			case 21: //tree cannon
				 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,2,80);			
				break;	
				
			case 22: //final kamehameha
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,1,120);	
				a.setAbsender(spielerid);
				a.setSpecial();
				a.setWait(20);
				a.setTrueSpecial();
				Battle.newAttack(a);	
				 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,100);
					a.setWait(20);
					
					Explosion exp=new Explosion((int)pos[0],(int)pos[1],4);
					exp.setSize(100, 100);
					Battle.newExplosion(exp);
					 exp=new Explosion((int)pos[0],(int)pos[1],9);
					exp.setSize(150, 150);
					Battle.newExplosion(exp);
				break;
				
			case 23://2cnd Cell super Absorb
				specialbild=26;
				  a=new Smash((int)pos[0],(int)pos[1],new Damage(damage[3],0,0),1);
				    a.setAbsorbDamage();		
					a.setSize(200, 200);
				break;
				
			case 24://aggroattack
				
				for(int i=0; i<15; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,43,100);
					a.setWait(i*10);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setTrueSpecial();
					if(i<14)
					{
					Battle.newAttack(a);
					}
				}	
				break;
				
			case 25: //Kapa
				 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus),0),lplus/3,KiBeam.CYAN_BEAM,130);			
				break;	
				
			case 26: //revenge death ball
				 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),3),lplus/6,2,65,(int) (210*specialplus));  
					a.setExplosion(new Explosion(0,0,4));
					a.rotate(20); a.setCharge();
				break;
				
			case 27: //body change
				 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus),0),lplus/3,KiBeam.CYAN_BEAM,70);		
				 a.setSpecialFunktion(Attack.BODYCHANGE);
				break;	
				
			case 28: //kiku cannon
				
				for(int i=0; i<5; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,66,200);
					a.setWait(i*40);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setTrueSpecial();
					if(i<4)
					{
					Battle.newAttack(a);
					}
				}	
				break;
				
			case 29: //eye laser
				 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,5);			
					a.setAbsender(spielerid);
					a.setSpecial();
					a.setTrueSpecial();
					Battle.newAttack(a);
					 a=new KiBeam((int)pos[0],(int)pos[1]+5,new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,5);			
						
				 break;
				
			case 30: //ssj3 dragon fist
				
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.DRAGON_FIST,140); 
				break;
				
			case 31: //10x kamehameha
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.RED_BEAM,160); 
				break;
				
				
			case 32://destructo disc
				a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/2.5f,0,44,200);
				a.setCharge();
				break;
				
			case 33://Burter Special
				
				int wink=-45;
				for(int i=0; i<54; i++)
				{
				    
					float speedx=(float) (Math.cos((float)Math.toRadians( wink)));
					float speedy=(float) (Math.sin((float)Math.toRadians(wink)));
					if(left)
					{
						speedx*=-1;
					}
					wink+=10;
					if(wink>45)
					{
						wink=-45;
					}
					 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int)speedx,(int)speedy),speedx*10,speedy*10, 30, 70);				
					 a.cantDruckDuell();
					a.setAbsender(spielerid);
					a.setWait((i/9)*10);
					
					a.setTrueSpecial();
					
					if(i<53)
					{
					Battle.newAttack(a);
					}
				}		
        	  
				
				break;
				
			case 34: //clarinet terror
				
				 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],0,0),0,0,54,2000);
		         a.setStuntime((int)(150*specialplus));
					
				break;
				
			case 35://Crusher Ball
				
				a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,59,100);
				a.setMulitHit(1000); a.setCharge();
				
				break;
				
			case 36: //Garlick Beam
				
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.CYAN_BEAM,130); 
				
				
				break;
				
	       case 37://Spirit Ball
				
				a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,49,80);
				a.setMulitHit(1000); a.setCharge();
				
				break;
				
	       case 38: //eraser gun
	    	   
	    		 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus),0),lplus/3,KiBeam.ROSA_BEAM,120);			
	 			
	    	   
	    	   break;
	    	   
	       case 39://Kame hame ha
	   		a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.BLUE_BEAM,125); 
			
	    	   break;
	    	   
	       case 40://Spirit Cannon
		   		a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.BLUE_BEAM,135); 
				
		    	   break;  
	    	   
	       case 41://timestop
	    		 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],0,0),0,0,54,2000);
		         a.setStuntime((int)(200*specialplus));
		        
	    	   break;
	    	   
	       case 42://Kame hame ha
		   		a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.BLUE_BEAM,120); 
				
		    	   break;  
		    	   
	       case 43: //final shine attack
	    		a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.CYAN_BEAM,200); 
				
	    	   break;
	    	   
	       case 44: //omega death
	    		a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,72,(int)(250*specialplus));
				a.setMulitHit(10);
				a.rotate(15); a.setCharge();
			
	    	   break;
	       case 45://Dynamite Kick
	    		specialbild=21;
	    		if((int)(Math.random()*2+1)==1)
	    		{
	    			 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),0,0,46,200);
	    			 exp=new Explosion((int)pos[0],(int)pos[1],5);
						exp.setSize(200, 200);
						a.cantDruckDuell();
						Battle.newExplosion(exp);
	    		}
	    		else
	    		{
	    			 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(2,(int) (lplus/2),0),0,0,46,200);
	    			 a.cantDruckDuell();	
	    		}
					a.setMaxTime(1);
			      
					
	    	   break;
	    	   
	   	case 46: //Hell Gate
			
			for(int i=0; i<5; i++)
			{
				a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*10+5)-5,new Damage(damage[3],(int) (lplus/2),0),lplus/4,0,65,150);
				a.setWait(i*10);
				a.setAbsender(spielerid);
				a.setSpecial();
				a.rotate(20);
				a.cantDruckDuell();
				a.setCharge();
				a.setTrueSpecial();
				if(i<4)
				{
				Battle.newAttack(a);
				}
			}	
	   		break;	
	   		
	   	case 47: //hero strike
	   		
	   		for(int i=0; i<6; i++)
			{
				a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*10+5)-5,new Damage(damage[3],(int) (lplus/2),0),lplus/4,0,73,150);
				a.setWait(i*15);
				a.setAbsender(spielerid);
				a.setSpecial();
				a.cantDruckDuell();
				a.setTrueSpecial();
				if(i<5)
				{
				Battle.newAttack(a);
				}
			}	
	   		
	   		break;
	   		
		case 48: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/5,0,42,120);
		a.setExplosion(new Explosion(0,0,5));
		a.setStuntime(150); a.setCharge();
		break;
		
		case 49:  //teleport kamehameha
		
			
			 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,180); 
			
			 
			break;
			
		case 50:  //solar kamehameha
			
			
			 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.CYAN_BEAM,190); 
			
			 
			break;
			
			
		case 51: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/5,0,75,(int) (200*specialplus));
		a.setExplosion(new Explosion(0,0,5)); //deadly bomber
		a.rotate(5); a.setCharge();
		break;
		
		case 52:
			
			for(int i=0; i<50; i++)
			{				
      			
				a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,76,80);
				a.rotate(15);
				a.setWait(i*3);
				a.cantDruckDuell();
				if(i%2==0)
				{
			    	a.setSinusWinkel(15,150);
				}
				else
				{
					a.setSinusWinkel(-15,150);
				}
				a.setAbsender(spielerid);
				a.setTrueSpecial();
				if(i<49)
				{
				Battle.newAttack(a);
				}
			}		
			break;
			
		case 53: //father son kamehameha
			a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.CYAN_BEAM,145); 
			break;
	   		
		case 54: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],0,0),lplus/5,0,77,100);
		a.setExplosion(new Explosion(0,0,3));//stone spit
		a.setStuntime(150); 
		break;
		

		case 55:  //spirit kamehameha
			 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.BLACK_BEAM,200); 
			break;
			
		case 56: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/5,1,75,(int) (180*specialplus));
		a.setExplosion(new Explosion(0,0,5)); //supernova
		a.rotate(10); a.setCharge();
		break;
		
		case 57: //masenko
			a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.GREEN_BEAM,110); 
			break;
			
		case 58: 
		
			 //kamikaze ghost attack
			for(int i=0; i<8; i++)
			{
				a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,84,150);
				a.setWait(i*5);
				a.setAbsender(spielerid);
				a.setExplosion(new Explosion(0,0,5));
				a.cantDruckDuell();
				a.setTrueSpecial();
				if(i<7)
				{
				Battle.newAttack(a);
				}
			}											
			break;//Fat Attack
			
		case 59: // kamehameha
			a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.BLUE_BEAM,150); 
			break;
			
		case 60:	
		 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),0,0,46,250);
		 exp=new Explosion((int)pos[0],(int)pos[1],3);
		exp.setSize(250, 250);
		Battle.newExplosion(exp);
	         break;
	         
		case 61: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/5,1,75,(int) (190*specialplus));
		a.setExplosion(new Explosion(0,0,5)); //revenge bomber
		a.rotate(15); a.setCharge();
		break;
		
		case 62: // tree cannon
			a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.CIRCLE_BEAM,120); 
			break;
			
		case 63: 			
			 //kamikaze ghost attack
			for(int i=0; i<10; i++)
			{
				a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[3],(int) (lplus/2),0),lplus/3,0,83,150);
				a.setWait(i*5);
				a.setAbsender(spielerid);
				a.setExplosion(new Explosion(0,0,5));			
				a.setTrueSpecial();
				a.cantDruckDuell();
				if(i<9)
				{
				Battle.newAttack(a);
				}
			}											
			break;
			
		case 64: //super kamehame ha
			a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/3,KiBeam.YELLOW_BEAM,128); 
			break;
			
		case 65: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[3],(int) (lplus/2),0),lplus/5,1,75,(int) (160*specialplus));
		a.setExplosion(new Explosion(0,0,5)); //supernova
		a.rotate(15); a.setCharge();
		break;
	
			}
			
				a.setAbsender(spielerid);
				a.setSpecial();
				a.setTrueSpecial();
				Battle.newAttack(a);		
				
					
				Main.sound.playSpecialSound(id);
			
			}
			else//Second Special
			{
				Attack a=null;
				switch(id)
				{
				case 0: a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,1,70);  break;//Garlic Strahl 
				case 1:a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/5,0,42,95);	  a.setCharge(); break;//Big Bang Attack
				case 2: a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,90); 
			
				specialbild=26;
				break;//Kamehameha
				case 3:  
					for(int i=0; i<10; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,41,70);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<9)
						{
						Battle.newAttack(a);
						}
					}											
					break;//Fat Attack
				case 4:
					for(int i=0; i<20; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,41,70);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<19)
						{
						Battle.newAttack(a);
						}
					}											
					break;//Fat Attack

				case 5: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,40,100);	a.setCharge(); break;//Fire breaker
				case 6: 
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,83,150);
					 	a.setExplosion(new Explosion(0,0,5)); break;//kamikaze ghost attack
				case 7://schwerthieb trunks
					
					specialbild=10;
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/5),0),0,0,41,100);
					a.setMaxTime(20);
					
						
					a.setAbsender(spielerid);
					a.setSpecial();
					
					Explosion exp=new Explosion((int)pos[0],(int)pos[1],3);
						exp.setSize(100, 100);
						Battle.newExplosion(exp);
					
					
		                break;
				
				case 8:
				
					specialbild=15;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,110);           //Perfect kamehameha
					break;
				case 9: 
					
					specialbild=15;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,90);  break;//kamehameha
				
				case 10://Death Beam
					
					specialbild=26;
					a=new KiBeam((int)(pos[0]),(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),(float)lplus/1.5f,KiBeam.ROSA_BEAM,10); break;//Deathbeam
				case 11: 
				
					specialbild=15;
					for(int i=0; i<25; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,11,50);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<24)
						{
						Battle.newAttack(a);
						}
					}	
					break;//Höllengranate
				case 12: 	
					
					specialbild=23;
					for(int i=0; i<10; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,72,85);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<9)
						{
						Battle.newAttack(a);
						}
					}	
					
					break;//Gama attack		
					
				case 13:
					
					specialbild=21;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,1,70);         
					break;
					
				case 14://Mundstrahl
					
					specialbild=26;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,3,85);         	
					break;
					
				case 15://Handschuss
				
					specialbild=26;
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,9,70);
					a.setCharge();
					break;
					
				case 16: //Absorb(Cell)
					
					specialbild=27;
					  a=new Smash((int)pos[0],(int)pos[1],new Damage(damage[4],0,0),1);
					    a.setAbsorbDamage();
						
						a.setSize(200, 200);
					break;
					
				case 17: //Fat Boo Blow Away
				
				
						specialbild=27;
						 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),0,0,46,200);
							
					
							 exp=new Explosion((int)pos[0],(int)pos[1],3);
							exp.setSize(200, 200);
							Battle.newExplosion(exp);
					break;
					
				case 18:
					
					specialbild=26;
					for(int i=0; i<3; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,44,130);
						a.setWait(30*i);
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<2)
						{
						Battle.newAttack(a);
						}
					}	
					break;
					
				case 19: 
					
					specialbild=26;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,85);         	
					break;
					
				case 20:
					specialbild=26;
					for(int i=0; i<10; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,41,80);
						a.setWait((int)(Math.random()*70+1)+10);
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<9)
						{
						Battle.newAttack(a);
						}
					}		
					break;
					
				case 21:
					specialbild=26;
					for(int i=0; i<15; i++)
					{
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,43,60);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<14)
						{
						Battle.newAttack(a);
						}
					}		
					break;
					
				case 22://kebab sword
					specialbild=26;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,15);         	
					
					break;
					
				case 23: //kamehameha
					specialbild=21;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,100);         	
				   
					break;
					
				case 24: //strahl
					specialbild=15;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.CYAN_BEAM,95);         	
				   
					break;
					
				case 25: //Explosion
					
					specialbild=26;
					 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),0,0,46,300);
						
				
						 exp=new Explosion((int)pos[0],(int)pos[1],2);
						exp.setSize(300, 300);
						Battle.newExplosion(exp);
					break;
					
				case 26: //strahl
					specialbild=26;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.ROSA_BEAM,110);         	
				   
					break;
					
				case 27://galxy dynamite
					specialbild=26;
					for(int i=0; i<15; i++)
					{
						int po=((int)(Math.random()*2+1)-1)*2;
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,41+po,100);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<14)
						{
						Battle.newAttack(a);
						}
					}		
					break;
					
				case 28: //dodonpa
					specialbild=26;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.YELLOW_BEAM,25);         	
				   
					break;
					
				case 29:
					//Dr.Gero Absorb
					specialbild=26;
					a=new Smash((int)pos[0],(int)pos[1],new Damage(0,0,0),10);
					a.setSmashTime(100);
					break;
					
				case 30: //ssj3 goku rage attack
					specialbild=21;
					for(int i=0; i<15; i++)
					{				
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,41,115);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<14)
						{
						Battle.newAttack(a);
						}
					}		
					break;
					
					
				case 31:
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/5,0,67,150);	
					a.setExplosion(new Explosion(0,0,3));
					a.setCharge();
					break;
					
					
	          case 32: //solar flare
					
					specialbild=27;
					 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],0,0),0,0,54,250);
			         a.setStuntime(200);
						
					break;
					
	          case 33:// Fast Hand Attack
	        	  
	        		specialbild=10;
					for(int i=0; i<5; i++)
					{
						int ax=(int)pos[0];
						int ay=(int)pos[1]-50+i*20;
						a=new KiAttack(ax,ay,new Damage(damage[4],(int) (lplus/2),0),lplus/2,0,30,70);
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<4)
						{
						Battle.newAttack(a);
						}
					}		
	        	  
	        	  break;
					
	          case 34: //tintenstrahl
	        	  
	      		specialbild=15;
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.CYAN_BEAM,40);         	
		
	        	  
	        	  break;
				
	          case 35: //Purble Beam
	        	  
	        		specialbild=15;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.ROSA_BEAM,90);         	
				        	  
	        	  break;
	        	  
	          case 36://SSj Vegeta
	        	  
	        		specialbild=26;
					for(int i=0; i<20; i++)
					{				
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,43,80);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<19)
						{
						Battle.newAttack(a);
						}
					}		
	        	  
	        	  break;
	        	  
	          case 37: //Kamehameha
	        	  
	        		specialbild=26;
					a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.YELLOW_BEAM,85);         	
				        	  
	        	  break;
	        	  
	          case 38:
	        	  
	      		specialbild=26;
				a=new KiAura((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),46,(int) 300);
		
	        	  
	        	  break;
	        	  
	          case 39:
	        	  
	        	  specialbild=15;
	        		for(int i=0; i<10; i++)
					{
						int po=((int)(Math.random()*2+1)-1)*2;
						a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,41+po,100);
						a.setWait((int)(Math.random()*50+1));
						a.setAbsender(spielerid);
						a.setSpecial();
						a.cantDruckDuell();
						if(i<9)
						{
						Battle.newAttack(a);
						}
					}		
	        	  
	        	  break;
	        	  
	          case 40:
	        	  specialbild=26;
	        	  a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/5,0,49,95);
	        	  a.setCharge();
	        	  break;
	        	  
	          case 41://Eye Lasers
	        	  specialbild=26;
	        		 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.YELLOW_BEAM,7);			
						a.setAbsender(spielerid);
						a.setSpecial();
						Battle.newAttack(a);
					 a=new KiBeam((int)pos[0],(int)pos[1]+5,new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.YELLOW_BEAM,7);			
				
	        	  
	        	  break;
	        	  
	          case 42://Special Beam Cannon
	        	  specialbild=26;
	        		 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.CIRCLE_BEAM,15);			
	        	  break;
	        	  
	          case 43:
	        	  
		      		specialbild=26;
		      		
		      		for(int i=0; i<10; i++)
					{				
		      		
						a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,49,70);
						a.setWait(i*5);
						if(i%2==0)
						{
					   a.setSinusWinkel(i+3, 30+i*5);
						}
						else
						{
							   a.setSinusWinkel((i+3)*-1, 30+i*5);
						}
						a.setAbsender(spielerid);
						a.cantDruckDuell();
					    a.rotate(10);
						a.setSpecial();
						if(i<9)
						{
						Battle.newAttack(a);
						}
					}		
		    		
		      		
		      		
					break;  
		        	  
	          case 44://Infinity Beam
	        	  specialbild=15;
	        		 a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.CIRCLE_BEAM,150);			
	        	  break; 
		        
	          case 45: //Rolling Thunder
	        		specialbild=26;
	        		for(int i=0; i<10; i++)
					{
	        			
						int po=0;
						if(left)
						{
						po= (int) (i*30);
						}
						else
						{
							po= (int) (i*-30);
						}
						
						a=new KiAttack((int)pos[0]-po,(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),0,0,41,60);
						a.setMaxTime(10);
						a.cantDruckDuell();
							
						a.setAbsender(spielerid);
						a.setSpecial();
						if(i<9)
						{
						Battle.newAttack(a);
						}
						 exp=new Explosion((int)pos[0]-po,(int)pos[1],1);
							exp.setSize(50, 50);
							Battle.newExplosion(exp);
					}		
	        	  break;
	              
	          case 46: //Illusion Kick
	        		specialbild=26;
	        		for(int i=0; i<8; i++)
					{ 			
						int po=0;
					
						if(left)
						{
						po= (int) (i*30);
						}
						else
						{
							po= (int) (i*-30);
						}
						
						
						a=new KiAttack((int)pos[0]-po,(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),0,0,41,60);
						a.setMaxTime(10);
						a.cantDruckDuell();
							
						a.setAbsender(spielerid);
						a.setSpecial();
						if(i<9)
						{
						Battle.newAttack(a);
						}
						 exp=new Explosion((int)pos[0]-po,(int)pos[1],2);
							exp.setSize(60, 60);
							Battle.newExplosion(exp);
					}		
	        	  break;
	        	  
	          case 47:
	        	  
	      		specialbild=26;
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.YELLOW_BEAM,70);         	
			        	  
       
	        	  
	        	  break;
	        	  
	      	case 48: // bollen
				specialbild=26;
				for(int i=0; i<40; i++)
				{				
					a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*100+1)-50,new Damage(damage[4],(int) (lplus/2),0),lplus/2,0,43,75);
					a.setWait((int)(Math.random()*50+1));
					a.setAbsender(spielerid);
					a.cantDruckDuell();
					a.setSpecial();
					if(i<39)
					{
					Battle.newAttack(a);
					}
				}		
				break;
				
	      	case 49: //goku schüsse
	      		specialbild=15;
	      		
	      		for(int i=0; i<7; i++)
				{				
	      			float sy=(float) (Math.sin(Math.toRadians(i*5-15))*10);
	      			float sx=(float) (Math.cos(Math.toRadians(i*5-15))*10);
	      			if(left){
	      				
	      				sx*=-1;
	      			}
	      			
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),sx,sy,41,100);
					a.setWait(i);
					a.setAbsender(spielerid);
					a.cantDruckDuell();
					a.setSpecial();
					if(i<6)
					{
					Battle.newAttack(a);
					}
				}		
	      		break;
	      	case 50: //cell schüsse
	      		specialbild=26;
	      		
	      		for(int i=0; i<10; i++)
				{				
	      			float sy=(float) (Math.sin(Math.toRadians(i*3-15))*10);
	      			float sx=(float) (Math.cos(Math.toRadians(i*3-15))*10);
	      			if(left){
	      				
	      				sx*=-1;
	      			}
	      			
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),sx,sy,49,90);
					a.setWait(i);
					a.setAbsender(spielerid);
					a.cantDruckDuell();
					a.setSpecial();
					if(i<9)
					{
					Battle.newAttack(a);
					}
				}		
	      		break;		
	     	case 51: //c13 schüsse
	      		specialbild=26;		
	      		
	      		for(int i=0; i<7; i++)
				{				
	      			
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,27,70);
					a.setWait(i*10);
					if(i%2==0)
					{
				    	a.setSinusWinkel(10,100);
					}
					else
					{
						a.setSinusWinkel(-10,100);
					}
					a.cantDruckDuell();
					a.setAbsender(spielerid);
					a.setSpecial();
					if(i<6)
					{
					Battle.newAttack(a);
					}
				}		
	      		break;	
				
	    	case 52: a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/2,0,49,100);	 a.setCharge();   break;
			
	    	case 53:
				for(int i=0; i<7; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,40,80);
					a.setWait(i*5);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setSinusWinkel(5,50+i*10);
					if(i<6)
					{
					Battle.newAttack(a);
					}
				}											
				break;//Fat Attack
         	case 54: //feuerball
				
				specialbild=26;
				 a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),0,0,46,300);
					
			
					 exp=new Explosion((int)pos[0],(int)pos[1],4);
					exp.setSize(300, 300);
					Battle.newExplosion(exp);
				break;
				
        	case 55:
        		specialbild=15;
        		a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),2),lplus/3,0,45,300); 
			a.setExplosion(new Explosion(0,0,4));
			a.rotate(10); a.setCharge(); 	a.setMulitHit(1000);
			break;//Spirit Bomb
				
    		case 56://Death Beam			
				specialbild=15;
				a=new KiBeam((int)(pos[0]),(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),(float)lplus/1.5f,KiBeam.ROSA_BEAM,10); break;//Deathbeam
	
    		case 57:
				for(int i=0; i<18; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,17,75);
					a.setWait(i*5);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setSinusWinkel(5,5+i*3);
					if(i<17)
					{
					Battle.newAttack(a);
					}
				}											
				break;
				
    		case 58: 		
				specialbild=15;
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.ROSA_BEAM,90);  break;//kamehameha
    	
    		case 59: 		
    			specialbild=26;
				  a=new Smash((int)pos[0],(int)pos[1],new Damage(damage[4],0,0),55);
				    a.setAbsorbDamage();
				    
					 exp=new Explosion((int)pos[0],(int)pos[1],11);
						exp.setSize(150, 150);
						Battle.newExplosion(exp);
					
					a.setSize(150, 150);

				break;
				
    		case 60:
    			specialbild=26;
        		for(int i=0; i<8; i++)
				{ 			
					int po=0;
				
					if(left)
					{
					po= (int) (i*30);
					}
					else
					{
						po= (int) (i*-30);
					}
					int r=(int)(Math.random()*30+1)-15;
					
					a=new KiAttack((int)pos[0]-po,(int)pos[1]+r,new Damage(damage[4],(int) (lplus/2),0),0,0,41,60);
					a.setMaxTime(10);
					a.cantDruckDuell();
						
					a.setAbsender(spielerid);
					a.setSpecial();
					if(i<9)
					{
					Battle.newAttack(a);
					}
					 exp=new Explosion((int)pos[0]-po,(int)pos[1]+r,1);
						exp.setSize(60, 60);
						Battle.newExplosion(exp);
				}												
				break;
				
    		case 61: //deadly volleay
    			specialbild=26;		
				for(int i=0; i<10; i++)
				{
					a=new KiAttack((int)pos[0],(int)pos[1]+(int)(Math.random()*50+1)-25,new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,75,85);
					a.setWait(i*5);
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setSinusWinkel(5,5+i*3);
					if(i<9)
					{
					Battle.newAttack(a);
					}
				}											
				break;
				
    		case 62: //volley
	      		specialbild=26;		     		
	      		for(int i=0; i<20; i++)
				{					      			
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,0,17,65);
					a.setWait(i*3);		
					if(i%2==0)
					{
						 a.setSinusWinkel(5,50);
					}
					else
					{
						 a.setSinusWinkel(-5,50);
					}	
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					if(i<19)
					{
					Battle.newAttack(a);
					}
				}		
	      		break;	
	      		
    		case 63: 		
    			specialbild=26;	
				a=new KiBeam((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,KiBeam.BLUE_BEAM,85);  break;//kamehameha
    	
    		case 64: //volley
	      		specialbild=26;		     		
	      		for(int i=0; i<20; i++)
				{					      			
					a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus/2),0),lplus/3,(10f-i)/3f,33,50);						
					a.setAbsender(spielerid);
					a.setSpecial();
					a.cantDruckDuell();
					a.setWait(i%2+i/3);
					if(i<19)
					{
					Battle.newAttack(a);
					}
				}		
	      		break;	
	      		
    		case 65:
    			a=new KiAttack((int)pos[0],(int)pos[1],new Damage(damage[4],(int) (lplus),0),lplus/4,0,46,80);	
    			a.rotate(25); a.setCharge();  
    			break;//Metal Hammer
			
		
				}
				
		
				if(a!=null)
				{				
				a.setAbsender(spielerid);
				a.setSpecial();
				Battle.newAttack(a);
				}
				Main.sound.playHitSound(id);
			}
			
		
			
			pos[1]+=100;
			
			pos[1]-=p[1];
			
			if(left)
			{
				pos[0]-=100;
				pos[0]+=p[0];
				
			}
			else
			{
				pos[0]+=100;
				pos[0]-=p[0];
				
			}
			
		return specialbild;
	}


	
	
}

