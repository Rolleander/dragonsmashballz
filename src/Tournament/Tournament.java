package Tournament;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import DBZ.Main;
import DBZ.PlayerControl;
import Fight.Fight;
import Fight.Fighter;
import Fight.FighterData;
import Images.GameImages;

import Misc.ZeniScreen;
import Stages.Stage;
import Stages.StageCell;
import Stages.StageCity;
import Stages.StageCityDestroyed;
import Stages.StageKame;
import Stages.StageLookout;
import Stages.StageNamek;
import Stages.StagePlain;
import Stages.StageSpace;
import Stages.StageTimeChamber;


public class Tournament extends TournamentGame{

	Fighter player;

	FighterData fdata=new FighterData();
	//Enemies
	private Fighter[] fighter=new Fighter[8];
	//Stages
	private Stage[] stages={new StageKame(),new StagePlain(),new StageLookout(),new StageCity(),new StageNamek(),new StageCell(),new StageTimeChamber(),new StageSpace()};
	private String[] battle=new String[fighter.length];
 
	
	public Tournament()
	{
		init();
	}
	
	public void win()
	{
		round++;
		fight=false;
		if(round>=stages.length)
		{
			finish(true);
		}
	}
	
	

	public void lose()
	{
		fight=false;
		finish(false);
	}
	
	
	public void init()
	{
		for(int i=0; i<fighter.length; i++)
		{
			fighter[i]=new Fighter();
			int id=getFighterFromList(i);
		
			battle[i]="Stage "+(i+1)+": VS "+fdata.getName(id);
			fighter[i].init(id, 1,false);
			fighter[i].setPos(150, 350);
			
		   fighter[i].setMapBorder(stages[0].getDimension());
		}
		  
		
	}
	
	private int getFighterFromList(int i) {
		// TODO Auto-generated method stub
		int id=0;	
	     int r=(int)(Math.random()*3+1);
		switch(i)
		{//Fighter
		case 0:	switch(r)
			{
			case 1: id=FighterData.KRILLIN; break;
			case 2: id=FighterData.YAMCHA; break;
			case 3: id=FighterData.SSJGOTEN; break;
			}
			break;
		case 1:	switch(r)
		{
		case 1: id=FighterData.GOKU; break;
		case 2: id=FighterData.UUB; break;
		case 3: id=FighterData.GOHAN; break;
		}
		break;
		case 2:	switch(r)
		{
		case 1: id=FighterData.PICCOLO; break;
		case 2: id=FighterData.TENSHINHAN; break;
		case 3: id=FighterData.KAI; break;
		}
		break;
		case 3:	switch(r)
		{
		case 1: id=FighterData.TRUNKS; break;
		case 2: id=FighterData.IMPERFECTCELL; break;
		case 3: id=FighterData.C17; break;
		}
		break;
		case 4:	switch(r)
		{
		case 1: id=FighterData.FREEZER; break;
		case 2: id=FighterData.GINYU; break;
		case 3: id=FighterData.VEGETA; break;
		}
		break;
		case 5:	switch(r)
		{
		case 1: id=FighterData.CELL; break;
		case 2: id=FighterData.C16; break;
		case 3: id=FighterData.HERCULE; break;
		}
		break;	
		case 6:	switch(r)
		{
		case 1: id=FighterData.BOO; break;
		case 2: id=FighterData.SSJ3GOTENKS; break;
		case 3: id=FighterData.SSJ2GOKU; break;
		}
		break;
		case 7:	switch(r)
		{
		case 1: id=FighterData.BROLY; break;
		case 2: id=FighterData.JANEMBA; break;
		case 3: id=FighterData.BABYVEGETA; break;
		}
		break;
		}
		return id;
	}

	private void nextFight() {
	
		tfight=new Fight();
	
		  ArrayList<Integer> fa=new  ArrayList<Integer>();
		  fa.add(player.getAttributes()[0]);
		  fa.add(fighter[round].getAttributes()[0]);
		  ArrayList<Integer> te=new  ArrayList<Integer>();
		  te.add(0);
		  te.add(1);
		  ArrayList<PlayerControl> st=new  ArrayList<PlayerControl>();
		  st.add(new PlayerControl(1));
		  st.add(new PlayerControl());
	  
		  tfight=new Fight();
		 tfight.newFight(fa, te, st);
		  tfight.setStage(stages[round]);
		 tfight.setModus("Tournament  Stage."+(round+1));
		 fight=true;
	}
	
	public void startTournament(Fighter f)
	{
		player=f;
		player.setPos(150, 100);
		 player.setMapBorder(stages[0].getDimension());
		round=0;
		
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(GameImages.menuback,0,0,(int)(1000),(int)(600),null);
		
		
		if(finishscreen>0)
		{
			finishscreen--;
			if(finish)
			{
				g.drawImage(GameImages.congratulations,250,100,null);
				
			}
			else
			{
				g.drawImage(GameImages.failed,350,100,null);
			}
			
			if(finishscreen==0)
			{
				if(finish)
				{
					ZeniScreen.addZenis(25000); 
				}
				else
				{
					if(round>=1)
					{
					ZeniScreen.addZenis((round+1)*3000); 
					}
				}
				stop();
				exit=true;
			}
		}
		else
		{
		paintMenu(g,battle,400,50,500,50,round);
		 String[] choice={"Fight!"};
		 int wahl=paintMenu(g,choice,620,545,350,50);
		 if(wahl==0)
		 {
		
			 nextFight();
			 
		 }
		 String[] choice2={"Cancel Tournament"};
		 wahl=paintMenu(g,choice2,50,545,350,50);
		if(wahl==0)
		 {
			 exit=true;
		 }
		
		g.setColor(new Color(0,0,0,100));
		g.fillRect(40,40,220,140);
		g.setColor(new Color(0,0,0));
		g.fillRect(40,40,220,10);
		g.fillRect(40,180,220,10);
		g.fillRect(40,40,10,140);
		g.fillRect(250,40,10,140);
	
		player.makeMove(0);
		
		player.paint(g);
		
	
		g.drawString("You",50,170);
		g.drawImage(GameImages.vs,110,210,null);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(40,290,220,140);		
		if(round<fighter.length)
		{
		
		g.drawImage(GameImages.stages[stages[round].getID()+1], (int)(50),(int)(300),(int)(200),(int)(120),null);
	 
		 fighter[round].makeMove(0);
		 fighter[round].paint(g);	
		 
		}
		g.drawString("Opponent",50,410);
	
		}
	
	}

	
	
	

}
