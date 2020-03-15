package Tournament;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import DBZ.Main;
import DBZ.PlayerControl;
import Fight.Fight;
import Fight.FighterData;
import Images.GameImages;
import Misc.ZeniScreen;
import Stages.StageTournament;

public class TournamentWorld extends TournamentGame {

	
	private int anz=16;
	private KoSystemPanel panel=new KoSystemPanel(16,30,60,20,400);
	
	private int[] fighter=new int[anz];
	private int[] fround=new int[anz]; 
	private int[] fplatz=new int[anz]; 
	private int[] spieler=new int[anz]; 
    
	private int maxround=8,cfight=0;
	private int fighter1,fighter2;
	
	private boolean geld=false;
	
	public TournamentWorld(ArrayList<Integer> selection){
				
	   	for(int i=0; i<16; i++)
	   	{
	   		if(i<selection.size())
	   		{
	   		fighter[i]=selection.get(i);
	   		spieler[i]=i+1;
	   		}
	   		else
	   		{
	   			boolean fertig=false;
	   			do{
	   				
	   			fighter[i]=(int)(Math.random()*Main.fighteranz+1)-1;
	   			fertig=true;
	   			for(int h=0; h<i; h++)
	   			{
	   				if(fighter[h]==fighter[i])
	   				{
	   					fertig=false;
	   					break;
	   				}
	   			}
	   			}while(fertig==false);
	   		}
	   		fround[i]=1;	
	   		fplatz[i]=i+1;
	   	}	
	   	
	  
	   	
	   	for(int i=0; i<50; i++)
	   	{
	   		int f1=(int)(Math.random()*anz+1)-1;
	   		int f2=(int)(Math.random()*anz+1)-1;
	   		int s=fighter[f1];
	   		fighter[f1]=fighter[f2];
	   		fighter[f2]=s;
	   		s=spieler[f1];
	   		spieler[f1]=spieler[f2];
	   		spieler[f2]=s;
	   	}
	  
	   	
	   	init();
	   	round=0;
	}
	
	protected void init() {
		
		round=0;
		cfight=0;
		maxround=8;
		controllPCRound();
	}

	

	@Override
	public void paint(Graphics g) {
		
		
		g.drawImage(GameImages.menuback,0,0,null);
		paintTitle(g, "World Tournament");
		g.setFont(font3);
		panel.setActuellFight(round,cfight,spieler);
	   panel.paint(g,fighter,fround,fplatz);
	   if(round<4)
		{
	   String[] choice={"Start Fight"};
		 int wahl=paintMenu(g,choice,620,550,350,50);
		 if(wahl==0)
		 {
			
            nextFight();
         
		 }
		 String[] choice2={"Cancel Tournament"};
		 wahl=paintMenu(g,choice2,50,550,350,50);
		if(wahl==0)
		 {
			 exit=true;
		 }
		}
	   else
	   {
		   String[] choice2={"Back"};
		   int wahl=paintMenu(g,choice2,325,550,350,50);
			if(wahl==0)
			 {
				 exit=true;
			
				 
			 }
	   }
	}
	
	private void nextFight() {
		
		
		int f1=0,f2=0;
		for(int i=0; i<fighter.length; i++)
		{
			if(fround[i]==round+1)
			{
				if(fplatz[i]==cfight*2+1)
				{
					fighter1=i;
					f1=fighter[i];
				}
				else if(fplatz[i]==cfight*2+2)
				{
					fighter2=i;
					f2=fighter[i];
				}
			}
		}
		
		  ArrayList<Integer> fa=new  ArrayList<Integer>();
		  fa.add(f1);
		  fa.add(f2);
		  ArrayList<Integer> te=new  ArrayList<Integer>();
		  te.add(0);
		  te.add(1);
		  ArrayList<PlayerControl> st=new  ArrayList<PlayerControl>();
	
		if(spieler[fighter2]>0&&spieler[fighter1]>0)
		{
		
			  st.add(new PlayerControl(1));
			  st.add(new PlayerControl(2));
		}
		else if(spieler[fighter2]>0)
		{
			  st.add(new PlayerControl());
			  st.add(new PlayerControl(1));
		}
		else if(spieler[fighter1]>0)
		{
			  st.add(new PlayerControl(1));
			  st.add(new PlayerControl());
		}
	
		tfight=new Fight();
		  tfight=new Fight();
		 tfight.newFight(fa, te, st);
		  tfight.setStage(new StageTournament());
		  tfight.setModus("World Tournament Round."+(round+1));
		 fight=true;		  
		
	}
	
	private void nextRound() {
		// TODO Auto-generated method stub
	
		
		cfight++;
		if(cfight>=maxround)
		{
		cfight=0;
		
		round++;
			maxround/=2;
		}
		
	if(round<4)
	{
	controllPCRound();
	}
	else
	{
		int sp=0;
		int round=0;
		for(int i=0; i<fighter.length; i++)
		{
			if(spieler[i]>0)
			{
				sp++;
				if(fround[i]>round)
				{
				round=fround[i];
				}
			}
		}

	if(geld==false)
	{
		geld=true;
		if(sp==1)
		{
		ZeniScreen.addZenis(round*3500);		
		}
		else
		{
			int w=round*4000-500*sp;
			if(w<1)
			{
				w=100;
			}
			ZeniScreen.addZenis(w);	
		}
	}
	}
	
	
	}
	
	public void controllPCRound()
	{
		
		for(int i=0; i<fighter.length; i++)
		{
			if(fround[i]==round+1)
			{
				if(fplatz[i]==cfight*2+1)
				{
					fighter1=i;
				}
				else if(fplatz[i]==cfight*2+2)
				{
					fighter2=i;
				}
			}
		}
		
		if(spieler[fighter2]==0&&spieler[fighter1]==0)
		{
			
		int p1=	FighterData.getPrice(fighter[fighter1]);
		int p2=	FighterData.getPrice(fighter[fighter2]);
		    
		
		   p1=(int)(Math.pow(p1, 1.7f)/10000d)+(int)(Math.random()*100+1);
		   p2=(int)(Math.pow(p2, 1.7f)/10000d)+(int)(Math.random()*100+1);
		   
		  int r;
		  r=(int)(Math.random()*(p2+p1)-1);
		   if(p2>p1)
		   {
			 
			   if(p1>r)
			   {
				   win();
			   }
			   else
			   {
				   lose();
			   }
		   }
		   else
		   {
			 
			   if(p2>r)
			   {
				  lose();
			   }
			   else
			   {
				   win();
			   }
		   }
		 
		   
		
		   

		}

	}

	@Override
	public void win() {
		// TODO Auto-generated method stub
		
		fround[fighter1]++;
	    fplatz[fighter1]=Math.round((float)fplatz[fighter1]/2);   
	    
		nextRound();
	}
	
	@Override
	public void lose() {
		// TODO Auto-generated method stub
		
		fround[fighter2]++;
	    fplatz[fighter2]=Math.round((float)fplatz[fighter2]/2);   
		nextRound();
	}




	

	
}
