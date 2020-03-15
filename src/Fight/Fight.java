package Fight;

import java.util.ArrayList;

import DBZ.Main;
import DBZ.PlayerControl;
import Stages.Stage;
import Story.Dialog;
import Story.Zuschauer;

public class Fight {

	
	public final static int  MODUS_NORMAL=0,MODUS_TIME=1,MODUS_ZOMBIE=2;
	
	private Fighter[] fighter;
	private Team team;
    private int fightmodus;
	private Stage stage;
	private Dialog dialog;
	private String modus="Default";
	
	 private Zuschauer zuschauer;
	
	public Fight()
	{
		
	}
	
	public void setModus(int m)
	{
		fightmodus=m;
	}
	
	public int getFightModus()
	{
		return fightmodus;
	}
	
	
	public void setZuschauer(Zuschauer z)
	{
		zuschauer=z;
	}
	
	public Zuschauer getZuschauer()
	{
		return zuschauer;
	}
	

	public void setDialog(Dialog d)
	{
		dialog=d;
	}
	
	public Dialog getDialog()
	{
		return dialog;
	}
	
	public Fighter[] usePlayMode(Fighter[] fighter)
	{
		
		
		
		return fighter;
	}
	
	
	
	
	public void newFight( ArrayList<Integer> fighter,  ArrayList<Integer> teams,  ArrayList<PlayerControl> controls)
	{
		
		team=new Team(teams);		
		team.setSteuerung(controls);
				
		setFighters(fighter);
	}
	
	public void newFight(Fighter[] fighter,  ArrayList<Integer> teams,  ArrayList<PlayerControl> controls)
	{
		
		team=new Team(teams);		
		team.setSteuerung(controls);
		this.fighter=fighter;
	}
	
	
	public void setModus(String m)
	{
		modus=m;
	}
	
	
	
	public void setStage(Stage stage)
	{
		this.stage=stage;
	}
	
	public Stage getStage()
	{
		return stage;
	}
	
	private void setFighters(ArrayList<Integer> fighters) {
		
		fighter=new Fighter[10];
		for(int i=0; i<fighters.size(); i++)
		{
			fighter[i]= new Fighter();
			fighter[i].init(fighters.get(i), i,false);
			
		}
	}

	public int getFighterAmount()
	{
		int f=0;
		for(int i=0; i<fighter.length; i++)
		{
			if(fighter[i]!=null)
			{
				f++;
			}
		}
		return f;
	}
	
	public Team getTeam()
	{
		return team;
	}
	

	
	public Fighter[] getFighter()
	{
		
		return position(fighter);
	}
	
	private Fighter[] position(Fighter[] f) {
		// TODO Auto-generated method stub
           int[] teamanz=new int[4]; 
           int[] tp=new int[4];
		for(int i=0; i<f.length; i++)
		{
			if(f[i]!=null)
			{
				teamanz[team.getTeam(i)]++;			
			}
		}
		
		int x=100,y=450;
		boolean hoch=false;
		for(int t=0; t<4; t++)
		{
			float s=300f/(float)teamanz[t];
		
			for(int i=0; i<f.length; i++)
			{
			
				
				if(f[i]!=null)
				{

				    if(team.getTeam(i)==t)
				    {
				    	switch(t)
						{
						case 0: x=100; y=400;  hoch=true; f[i].setRichting(false);  break;
						case 1: x=900; y=400;  hoch=true; f[i].setRichting(true); break;
						case 2: x=350; y=100;  hoch=false; f[i].setRichting(false); break;
						case 3: x=350; y=450;  hoch=false; f[i].setRichting(true); break;
						}
				    	
				    	if(hoch)
				    	{
				    		y-=s*tp[t];
				    		
				    	}
				    	else
				    	{
				    		x+=s*tp[t];
				    	}
				    	
				    	
				    	f[i].setPos(x, y);
				    	
				    	tp[t]++;
				    }
					
				}
			}
		}
		
		return f;
	}


	public void setFighter(Fighter[] fighter)
	{
		this.fighter=fighter;
	}

	public String getModus() {
		// TODO Auto-generated method stub
		return modus;
	}
	
	
	
	
}
