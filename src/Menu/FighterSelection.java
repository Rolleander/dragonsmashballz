package Menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Controlls.Keys;
import DBZ.Main;
import DBZ.PlayerControl;
import Fight.FighterData;
import Fight.Team;
import Images.GameImages;

import Save.Profil;
import Stages.Stage;
import Stages.StageCastle;
import Stages.StageCell;
import Stages.StageCity;
import Stages.StageCityDestroyed;
import Stages.StageDesert;
import Stages.StageKorin;
import Stages.StageMountain;
import Stages.StageField;
import Stages.StageForrest;
import Stages.StageIce;
import Stages.StageIsland;
import Stages.StageKaioworld;
import Stages.StageKame;
import Stages.StageLabor;
import Stages.StageLookout;
import Stages.StageNamek;
import Stages.StageNamekDestroyed;
import Stages.StagePlain;
import Stages.StageRandom;
import Stages.StageSnow;
import Stages.StageSpace;
import Stages.StageTimeChamber;
import Stages.StageTournament;
import Story.Chapter;

public class FighterSelection extends MenuData{

	private int selec=0,stageselection=0,pselect=0;
	
	
	private FighterData fdata=new FighterData();
	private boolean multi=false,mapselection=true,teamfight=false,steuerungwahl=true;
	private boolean[] freefighters;
	private Stage[] stages={new StagePlain(),new StageKame(),new StageCity(),new StageNamek(),new StageMountain(),new StageDesert(),new StageField(),new StageIce(),new StageTournament(),new StageKorin(),new StageIsland(),new StageLookout(),new StageTimeChamber(),new StageCityDestroyed(),new StageCell(),new StageKaioworld(),new StageSpace(),new StageSnow(),new StageNamekDestroyed(),new StageForrest(),new StageLabor(),new StageCastle(),new StageRandom()  };
	

	public static int fighters[]={FighterData.GOKU,FighterData.SSJGOKU,FighterData.SSJ2GOKU,FighterData.SSJ3GOKU,FighterData.SSJ4GOKU,FighterData.VEGETA,FighterData.SSJVEGETA,FighterData.SSJ2VEGETA,FighterData.SSJ4VEGETA,FighterData.VEGETO,FighterData.GOHANKID,FighterData.TEENGOHAN,FighterData.GOHANSSJ2,FighterData.GOHAN,FighterData.SSJSONGOHAN,FighterData.MYSTIGGOHAN,FighterData.TRUNKSFUTURE,FighterData.TRUNKS,FighterData.KRILLIN,FighterData.YAMCHA,FighterData.TENSHINHAN,FighterData.PICCOLO,FighterData.SSJGOTEN,FighterData.YOUNGTRUNKS,FighterData.GOTENKS,FighterData.SSJGOTENKS,FighterData.SSJ3GOTENKS
		,FighterData.UUB,FighterData.NAPPA,FighterData.GULDO,FighterData.RECOOME,FighterData.BURTER,FighterData.JEECE,FighterData.GINYU,FighterData.FREEZER,FighterData.FREEZER100,FighterData.DRGERO,FighterData.C16,FighterData.C17,FighterData.C18,FighterData.IMPERFECTCELL,FighterData.SCNDCELL,FighterData.CELL,FighterData.CELLSUPER,FighterData.CELLJR,FighterData.KAI,FighterData.PUIPUI,FighterData.DABURA,FighterData.FATBOO,FighterData.BOO,FighterData.BOOTENKS,FighterData.BOOHAN,FighterData.KIDBOO,FighterData.HERCULE,FighterData.BARDOCK
		,FighterData.COOLER,FighterData.METALCOOLER,FighterData.TAPION,FighterData.BROLY,FighterData.SSJ4BROLY,FighterData.C13,FighterData.SUPERC13,FighterData.JANEMBA,FighterData.BABYVEGETA,FighterData.GOKUSSJ5,FighterData.SQUID,FighterData.RANDOM};
	
	
	private int scrollx=0;
	private  ArrayList<Integer> selection=new ArrayList<Integer>();
	private  ArrayList<Integer> teamselection=new ArrayList<Integer>();
	private  ArrayList<PlayerControl> steuerung=new ArrayList<PlayerControl>();
	private Font minifont=new Font("Arial",0,12);
	public FighterSelection(boolean[] free)
	{
		freefighters=free;
		selection.add(randomCharacter());
		selection.add(randomCharacter());
		teamselection.add(0);
		teamselection.add(1);
		steuerung.add(new PlayerControl(1));
		steuerung.add(new PlayerControl());
	}
	
	
	
	public void setMulitSelection(boolean b)
	{
		multi=b;
	
	}
	
	public void setTeamFight(boolean b)
	{
		teamfight=b;
		steuerungwahl=b;
	}
	
	public void withoutSteer()
	{
	
		steuerungwahl=false;
		
	}
	
	public int paintButton(Graphics g, int x, int y,String[] title,int wahl, int mx, int my)
	{
		int s=-1;
		for(int i=0; i<title.length; i++)
		{
		g.setColor(new Color(0,0,0));		
		g.fillRect(x+56, y, 100, 38);
		
		g.setColor(new Color(0,0,0));		
		g.fillRoundRect(x, y-2, 56, 45,5,5);
		if(fighters[selection.get(i)]==FighterData.RANDOM)
		{
			g.drawImage(GameImages.fighterselection[1],x+3,y,50,40,null);			
		}
		else
		{
		g.drawImage(GameImages.faces[ fighters[selection.get(i)]],x+3,y,null);
		}	
		if(wahl==i)
		{
			g.setColor(new Color(250,250,0));
		}
		else
		{
			g.setColor(new Color(150,150,150));
		}
		if(mx>x&&mx<x+150&&my>y&&my<y+38)
	    {
			
			g.setColor(new Color(240,240,240));
	    	if(klickn==1)
	    	{
	    		s=i;
	    	}
	    }
			
		g.fillRoundRect(x+58, y+2, 96, 34, 10, 10);
		g.setColor(new Color(0,0,0));
		g.drawString(title[i],x+65,y+20);
		
		x+=160;
		}
		return s;
	}
	
	public int paintTeamButton(Graphics g, int x, int y,int wahl, int mx, int my)
	{
		int s=-1;
		int pi=0;
		
		if(teamselection.size()<10)
		{
			pi=1;
		}
		
		for(int i=0; i<teamselection.size()+pi; i++)
		{	
		
			
			String titel="";
			if(i==teamselection.size())
			{
				titel="New";
			}
			else
			{
             
			titel="Team "+(teamselection.get(i)+1);
			if(steuerungwahl==false)
			{
				titel="SP."+(i+1);
			}
			
			
			}
			g.setColor(new Color(0,0,0));		
			g.fillRoundRect(x, y-2, 56, 45,5,5);
			if(i==teamselection.size())
			{
				g.drawImage(GameImages.pluscharacter,x+3,y,null);
			}
			else
			{
				if(fighters[selection.get(i)]==FighterData.RANDOM)
				{
					g.drawImage(GameImages.fighterselection[1],x+3,y,50,40,null);	
				}
				else
				{
				g.drawImage(GameImages.faces[ fighters[selection.get(i)]],x+3,y,null);
				}
			}	
				
		if(wahl==i)
		{
			g.setColor(new Color(250,250,0));
		}
		else
		{
			g.setColor(new Color(150,150,150));
		}
		
		if(mx>x&&mx<x+56&&my>y-22&&my<y+45)
	    {
			if(i<teamselection.size())
			{
			if(mx>x&&mx<x+58&&my>y-22&&my<y-2)
		    {
				if(klickn==1)
		    	{
				
				int nt=teamselection.get(i);
				nt++;
				if(nt>3)
				{
					nt=0;
				}
				teamselection.set(i, nt);
		    	}
		    }
			}
			g.setColor(new Color(240,240,240));
	    	if(klickn==1)
	    	{
	    		s=i;
	    	}
	    }
			
		g.fillRoundRect(x-1, y-22, 58, 20, 3, 3);
		g.setColor(new Color(0,0,0));
		
		g.drawString(titel,x+1,y-5);
		
		
		//X
		int af=2;
		if(i<teamselection.size()&&teamselection.size()>af)
		{
		g.drawImage(GameImages.subcharacter,x+35,y-32,null);
	  	if(mx>x+45&&mx<x+55&&my>y-32&&my<y-22)
	    {
	  		if(klickn==1)
	    	{
	  			selection.remove(i);
	  			teamselection.remove(i);
	  			steuerung.remove(i);
	  			pselect=0;
	  			break;
	    	}
	    }
		}
		
		//Steuerung
		if(steuerungwahl)
		{
		if(i<teamselection.size())
		{
			g.drawImage(GameImages.multisteer[steuerung.get(i).getIconID()],x-10,y+20,null);
		 	if(mx>x-10&&mx<x+10&&my>y+20&&my<y+40)
		    {
		  		if(klickn==1)
		    	{
		  			int nt=steuerung.get(i).getPlayerControls();
					nt++;
					int grenze=10;						
					if(nt>grenze)
					{
		        	nt=0;
					}
					steuerung.set(i,new PlayerControl( nt));
		    	}
		    }
		}
		}
		
		x+=65;
		
		
		
		}
		return s;
	}
	

	public void paint(Graphics g,int mx, int my)
	{

		g.setFont(font);
		g.drawImage(GameImages.menuback,0,0,(int)(1000),(int)(600),null);
		//Title
		g.drawImage(GameImages.cursor[0],(int)(200),(int)(20),(int)(600),(int)(50),null);
		g.setColor(new Color(0,0,0));
		g.drawString("Character Selection",(int)(360),(int)(55));
		g.setColor(new Color(200,200,200));
		g.fillRect(0,(int)(140),(int)(1000),(int)(240));
	
		g.setColor(new Color(0,0,0));
	
		g.drawRect((int)(0),(int)(140),(int)(1000),(int)(600));
		
	//	if(((scrollx+1)*14)<fighters.length)
	//	{
		g.drawImage(GameImages.nextrow,900,160,null);
		if(mx>900&&mx<950&&my>160&&my<360)
	    {
			if(klickn==1)
	    	{
			scrollx++;
			  if(((scrollx)*14)>fighters.length-1){
				  scrollx=0;
			  }
			}
	    }
     // 	}
		//if(scrollx>0)
		//{
			g.drawImage(GameImages.nextrow,130,160,-50,200,null);
			if(mx>80&&mx<130&&my>160&&my<360)
		    {
				if(klickn==1)
		    	{
				scrollx--;
				if(scrollx<0)
				{
					scrollx=(fighters.length-1)/14;
				}
				}
		    }
		//}
		//
		g.setFont(font3);
		if(teamfight)
		{
			int s=paintTeamButton(g,150,100,pselect,mx,my);
			if(s>-1)
			{
				if(s==teamselection.size())
				{				
					teamselection.add(teamselection.size()%2);
					
					selection.add(randomCharacter());
					steuerung.add(new PlayerControl());
					pselect=s;
				}
				else
				{
				pselect=s;
				}
				//scrollx=selection[pselect]/14;
			}
		}
		else if(multi)
		{
			
			String[] title={"Fighter 1","Fighter 2"};
			int s=paintButton(g,150,100,title,pselect,mx,my);
			if(s>-1)
			{
				pselect=s;
				scrollx=selection.get(pselect)/14;
			}
		}
		else
		{
			String[] title={"Fighter"};
			int s=paintButton(g,150,100,title,pselect,mx,my);
			if(s>-1)
			{
				pselect=s;
				scrollx=selection.get(pselect)/14;
			}
		}
		
		
	 for(int i=0; i<14; i++)
	 {
		int y=150;
		int x=i;
		int id=i+scrollx*14;
		
		if(id<fighters.length)
		{
		
		if(i>6)
		{
			y+=120;
			x-=7;
		}
		g.setColor(new Color(0,0,0));
		g.fillRect((int)((148+x*105)),(int)((y-2)),(int)(104),(int)(104));
		
		boolean locked=false;
		       		
		     if(fighters[id]==FighterData.RANDOM)
		     {
		    		g.drawImage(GameImages.fighterselection[1],(int)((150+x*105)),(int)(y),(int)(100),(int)(100),null);
					
		     }
		     else
		     {
			   if(freefighters[fighters[id]])
			   {
					g.drawImage(GameImages.fighterselection[fighters[id]+2],(int)((150+x*105)),(int)(y),(int)(100),(int)(100),null);
					 
			   }
			   else 
			   {
					g.drawImage(GameImages.fighterselection[0],(int)((150+x*105)),(int)(y),(int)(100),(int)(100),null);
					locked=true;
			   }
		     }
		g.setFont(font2);
		
		
		
		
		String name=FighterData.getName(fighters[id]);
		if(locked==false)
		{
	//	FontMetrics metrics = g.getFontMetrics(font2);
		g.setColor(new Color(0,0,0,200));
		//g.fillRect(((153+x*105)),y+87,metrics.stringWidth(name),14);	
		g.fillRect((150+x*105),y+87,100,14);
		}
		
		
		if(selection.get(pselect)==id)
		{
			g.setColor(new Color(255,255,0));
			g.fillRect((int)((148+x*105)),(int)((y-2)),(int)(104),(int)(4));
			g.fillRect((int)((148+x*105)),(int)((y+98)),(int)(104),(int)(4));
			g.fillRect((int)((148+x*105)),(int)((y-2)),(int)(4),(int)(104));
			g.fillRect((int)((248+x*105)),(int)((y-2)),(int)(4),(int)(104));
		}


		if(locked==false)
		{
			g.setColor(Color.BLACK);
			g.drawString(name,(int)((152+x*105)),(int)((y+99)));
			g.setColor(Color.GRAY);
		if(mx>150+x*105&&mx<250+x*105&&my>y&&my<y+100)
	    {
	    	g.setColor(new Color(255,255,255,100));
	    	g.fillRect((int)((150+x*105)),(int)(y),(int)(100),(int)(100));
	    	g.setColor(new Color(255,255,0));
	    	if(klickn==1)
	    	{
	    		selection.set(pselect, id);
	    		if(fighters[id]!=FighterData.RANDOM)
	    		{
	    		Main.sound.playSpecialSound(fighters[id]);
	    		}
	    		
	    	}
	         selec=id;
	    }
		g.drawString(FighterData.getName(fighters[id]),(int)((153+x*105)),(int)((y+99)));
		}
		
		
		}
		
	 }
	 int ay=405;
	 
		g.setColor(new Color(200,200,200));
		
		g.fillRect((int)(100),(int)(ay-20),(int)(400),(int)(155));
		g.setColor(new Color(0,0,0));
		g.drawRect((int)(100),(int)(ay-20),(int)(400),(int)(155));
	 
	/* g.setColor(new Color(0,0,0));
	 g.setFont(font3);
	 String[] anz={"STRENGTH","DEFENCE","KI POWER","SPEED","SPECIAL","OVERALL"};
	 for(int i=0; i<anz.length; i++){
		 
		 g.drawString(anz[i], (int)(110),ay+i*25);
	 }*/
	 
		 if(fighters[selec]!=FighterData.RANDOM)
		 {
			 
			 g.setColor(new Color(150,150,150));
			 g.fillRect(104,390,260,30);
			 g.fillRect(105,440,260,16);
			 g.fillRect(105,479,260,16);
			 
			 g.setFont(font);
			 String name=FighterData.getName(fighters[selec]);
			 g.setColor(new Color(50,50,250));
			 g.drawString(name,103,415);
			 g.drawString(name,107,415);
			 g.drawString(name,105,413);
			 g.drawString(name,105,417);
			 g.setColor(new Color(150,150,250));
			 g.drawString(name,105,415);
			 g.setFont(font3);
			 g.setColor(Color.BLACK);
			 g.drawString("Sepcial Technique:",110,438);
			 g.drawString("Ultimate Teqchnique:",110,475);
			
			 g.setColor(new Color(0,0,150));
			 g.drawString(FighterData.getSpecialNames(fighters[selec], false),110,455);
			 g.drawString(FighterData.getSpecialNames(fighters[selec], true),110,492);
			 if(FighterData.canTransform(fighters[selec]))
			 {
				 int transform=FighterData.getTransformForm(fighters[selec]);
				
				 g.setColor(new Color(0,0,150));
				 g.drawString(FighterData.getName(transform),105,530);
				 g.setColor(Color.BLACK);
				 g.drawString("Transforms into: ",105,513);
				 g.drawImage(GameImages.faces[transform],250,499,null);
				 g.drawRect(249,498,51,41);
			 }
			
		Polygon p=new Polygon();
		Polygon p2=new Polygon();
		for(int i=0; i<5; i++)
		{
			int x=(int) (Math.cos(Math.toRadians(i*72))*70);
			int y=(int) (Math.sin(Math.toRadians(i*72))*70);
		   	p.addPoint(x+430, y+460);		   	
		    int min=fdata.getAttributPower(fighters[selec], i);		  
			int max=fdata.getMaxPower(i);
	
			double proz=(((double)min/(double)max)*70);
			 x=(int) (Math.cos(Math.toRadians(i*72))*proz);
			 y=(int) (Math.sin(Math.toRadians(i*72))*proz);
			 p2.addPoint(x+430,y+460);
		}
		g.setColor(new Color(10,20,80));
		g.fillPolygon(p);
		g.setColor(new Color(100,120,250));
		g.fillPolygon(p2);
		
		g.setFont(minifont);
		FontMetrics fm=g.getFontMetrics();
		for(int i=0; i<5; i++)
		{
			int x=(int) (Math.cos(Math.toRadians(i*72))*75);
			int y=(int) (Math.sin(Math.toRadians(i*72))*75);
			g.drawImage(GameImages.atticons[i],x+415,y+445,null);
			  int min=fdata.getAttributPower(fighters[selec], i);		  
			int max=fdata.getMaxPower(i);
			double proz=(((double)min/(double)max)*70);
			 x=(int) (Math.cos(Math.toRadians(i*72))*proz);
			 y=(int) (Math.sin(Math.toRadians(i*72))*proz);
			 g.setColor(Color.BLACK);
			 String s=""+min;
			 int sx=x+430-fm.stringWidth(""+min)/2;
			 g.drawString(s,sx+1 ,y+465);
			 g.drawString(s,sx-1 ,y+465);
			 g.drawString(s,sx ,y+464);
			 g.drawString(s,sx ,y+466);
			 g.setColor(Color.WHITE);
			 g.drawString(s,sx,y+465);
		}
	
		
			 
	 }
	 
	 
	 String[] choice={"Start!"};
	 int wahl=paintMenu(g,choice,620,550,350,50);
	 if(wahl==0)
	 {
		 
		 if(this.teamfight)
		 {
			 
			 go=true;
		 }
		 else
		 {
		 go=true;
		 }
	 }
	 String[] choice2={"Back"};
	 wahl=paintMenu(g,choice2,50,550,350,50);
	if(wahl==0)
	 {
		 exit=true;
	 }
	 if(mapselection)
	 {
	 g.drawImage(GameImages.mapselection,(int)(640),(int)(400),(int)(240),(int)(130),null);
	 g.drawImage(GameImages.stages[stages[stageselection].getID()+1], (int)(660),(int)(404),(int)(200),(int)(120),null);
	 g.setColor(new Color(255,255,255));
	 String map=stages[stageselection].getName();
	 g.setFont(font3);
	 g.setColor(Color.BLACK);
	 g.drawString(map,670,525);
	 if(my>400&&my<530)
	 {
	  if(mx>640&&mx<658)
	  {
		  g.setColor(new Color(255,255,255,150));
		  g.fillRect((int)(642),(int)(402),(int)(16),(int)(126));
		  if(klickn>0)
		  {
			  stageselection--;
			  if(stageselection<0)
			  {
				  stageselection=stages.length-1;
			  }
		  }
	  }
	  if(mx>862&&mx<880)
	  {
		  g.setColor(new Color(255,255,255,150));
		  g.fillRect((int)(862),(int)(402),(int)(16),(int)(126));
		  if(klickn>0)
		  {
			  stageselection++;
			  if(stageselection>stages.length-1)
			  {
				  stageselection=0;
			  }
		  }
	  }
	 }
	 }
	}
	
	private int randomCharacter() {
		int c=0;
		boolean fertig=false;
		do{
		c=(int)(Math.random()*Main.fighteranz+1)-1;
		if(freefighters[ fighters[c]])
		{
			fertig=true;
		}
		}while(fertig==false);
		return c;
	}


	
	
	public int getSelection(int slc)
	{
		int f=fighters[selection.get(slc)];
		if(f==FighterData.RANDOM)
		{
			f=fighters[randomCharacter()];
		}
		return f;
	}
	
	public   ArrayList<Integer> getSelection()
	{
		 ArrayList<Integer> l=new  ArrayList<Integer>();
		 for(int i=0; i<selection.size(); i++){
			 int f=fighters[selection.get(i)];
			 if(f==FighterData.RANDOM)
				{
					f=fighters[randomCharacter()];
				}
			l.add(f); 
		 }
		return l;
	}
	
	public int getSelectionAnz()
	{
		return selection.size();
	}

	public Stage getStageSelection()
	{
		if(stages[stageselection].getID()>-1)
		{
		return  stages[stageselection];
		}
		else
		{
			return  stages[(int)(Math.random()*(stages.length-1)+1)-1];
		}
	}
	
	public void setMapSelection(boolean b)
	{
		mapselection=b;
	}
	
	public  ArrayList<Integer> getTeam()
	{
		
		return teamselection;
	}
	
	public  ArrayList<PlayerControl> getSteuerung()
	{

		return steuerung;
	}

}
