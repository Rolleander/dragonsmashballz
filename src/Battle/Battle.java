package Battle;


import java.awt.Graphics;

import Fight.Fighter;
import Fight.FighterData;
import Images.GameImages;




public class Battle {

	private static Attack[] angriffe=new Attack[150];
	
	private FighterData data=new FighterData();
	private static Explosion[] explosions=new Explosion[50];
	
	private SpecialEffect special=new SpecialEffect();
	private DruckDuell duell=new DruckDuell();
	
	public Battle()
	{
	
	}
	
	public static void clearExplosions()
	{
		for(int i=0; i<50; i++)
		{
			explosions[i]=null;
		}
	}
	
	public static void newAttack(Attack a)
	{
		for(int i=0; i<angriffe.length; i++)
		{
			if(angriffe[i]==null)
			{
				angriffe[i]=a;
				break;
			}
		}
	}
	
	public static void newExplosion(Explosion ex)
	{
		for(int i=0; i<explosions.length; i++)
		{
			if(explosions[i]==null)
			{
				explosions[i]=ex;
				break;
			}
		}
	}
	
	public static Attack[] getAttacks()
	{
		return angriffe;
	}
	
	int presst=0;
	public Fighter[] paint(Graphics g,  Fighter[] player)
	{
		special.reset();
	 
		for(int i=0; i<angriffe.length; i++)
		{
			if(angriffe[i]!=null)
			{
				angriffe[i].paint(g);
				for(int h=0; h<player.length; h++)					
				{
					if(player[h]!=null)
					{
					Damage d=angriffe[i].crash(h, player[h].getPos()[0],player[h].getPos()[1],20,data.getBloodArt(player[h].getAttributes()[0]));
					if(d!=null)
					{
						d.absender=angriffe[i].getAbsender();
						if(angriffe[i].getAttackTyp()==0)
						{
						player[angriffe[i].getAbsender()].punchHit();
						
						}
					
						if(angriffe[i].isAbsorbDamage()&&player[h].isCyborg()==false)
						{
							player[angriffe[i].getAbsender()].heal(d.damage/2);
						}
						
						int stun=angriffe[i].getStunTime();
						if(stun>0)
						{
							player[h].stun(stun);
						}
						
						int sfunk=angriffe[i].getSpecialFunktion();			
						if(sfunk>0)						
						{
							if(sfunk==Attack.BODYCHANGE)
							{
							//Körpertausch
							int aid=angriffe[i].getAbsender();
							
							Fighter f=player[aid];
							player[aid]=player[h];
							player[h]=f;
							
							
					
							player[aid].setSpielerID(aid,h);
						    
							player[h].setSpielerID(h,aid);
							
							special.setSpecial(false,aid);
							}
							
						
						}
						else
						{
					player[h].hit(d,angriffe[i].getPos()[0],angriffe[i].getPos()[1]);
						}
					}
					}
				}
				
				
				
				if(angriffe[i].killAttack()){
					angriffe[i]=null;
				}
				else
				{
					if(angriffe[i].isSpecial())
					{
						int id=angriffe[i].getAbsender();
						player[id].usesSpecial();
						special.setSpecial(true,id);
						int[] srcpos=angriffe[i].getSrcPos();
						int[] hitpos=angriffe[i].getPos();
						special.setSrcPos(srcpos, id);
						special.setHitPos(hitpos, id);
						
					}
				}
					
			}
		}
		
		boolean[] press=new boolean[player.length];
		for(int i=0; i<player.length; i++)
		{
			if(player[i]!=null){
				press[i]=player[i].druecktDruckDuell();	
			}
		}
		angriffe=duell.duell(g,angriffe,press);
		
		for(int i=0; i<explosions.length; i++)
		{
			if(explosions[i]!=null)
			{
				explosions[i].paint(g);
				if(explosions[i].killExplosion())
				{
					explosions[i]=null;
				}
			}
		}
		presst++;
		if(presst>9)
		{
			presst=0;
		}
		boolean[] b=duell.getPlayerInDuell(angriffe);
		for(int i=0; i<10; i++){
			if(b[i] &&player[i]!=null)
			{
				int x=player[i].getPos()[0]-25;
				int y=player[i].getPos()[1]-125;
				g.drawImage(GameImages.duellpress[presst/5],x,y,null);
			}
		}
		
		return player;
	}

	public static void reset() {
		for(int i=0; i<angriffe.length; i++)
		{
			angriffe[i]=null;
			
		}
	}
	
	public SpecialEffect getSpecialEffect()
	{
		return special;
	}

	
	
}
