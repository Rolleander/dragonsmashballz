package Story;

import java.util.ArrayList;

import DBZ.PlayerControl;
import Fight.Fight;
import Fight.Fighter;
import Stages.Stage;


public class StoryBattle {

	Fighter[] fighter;
	Stage stage;
	private String name;
	private Dialog dialog;
	private int[] pos=new int[2];
	private int map=0,icon;
  private int geld;
  private int nr;
  private   ArrayList<Integer> team;
  private Zuschauer zuschauer;

	
	public StoryBattle(Fighter[] fighter, Stage stage, String name, int nr)
	{
		this.fighter=fighter;
		this.stage=stage;
		this.name=name;
		this.nr=nr;
	}
	
	public void setZuschauer(Zuschauer z)
	{
		zuschauer=z;
	}
	
	public Zuschauer getZuschauer()
	{
		return zuschauer;
	}
	

	
	public void setDifferentMap(int mnr)
	{
		map=mnr;
	}
	
	public int getNr()
	{
		return nr;
	}
	
	public void setGeld(int g)
	{
		geld=g;
	}
	
	
	
	public void setIcon(int nr)
	{
		icon=nr;
	}
	
	public int getIcon()
	{
		return icon;
	}
	
	public void onMap(int nr)
	{
		map=nr;
	}
	
	public boolean isOnMap(int nr)
	{
		if(nr==map)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setPos(int x, int y)
	{
		pos[0]=x*32-10;
		pos[1]=y*32-10;
	}
	
	public Fight getFight()
	{
		Fight fight=new Fight();
		fight.setZuschauer(zuschauer);
		ArrayList<Integer> te=new  ArrayList<Integer>();
		  ArrayList<PlayerControl> st=new  ArrayList<PlayerControl>();
		 for(int i=0; i<fighter.length; i++)
		 {
			 fighter[i].cantTransform();
		  if(i==0)
		  {  
			te.add(0);
		  st.add(new PlayerControl(1));
		  }
		  else
		  {
		  te.add(1);
		  st.add(new PlayerControl());
		  }
		 }
		 if(team!=null)
		 {
			 te=team;
		 }
		  fight.setDialog(dialog);
		  fight.newFight(fighter, te, st);
		  fight.setStage(stage);
		
		return fight;
	}
	
	public void setTeams(ArrayList<Integer> t)
	{
		team=t;
	}
	
	public int[] getPos(){		
		return pos;
	}
	
	public void setDialog(Dialog dialog)
	{
		this.dialog=dialog;
	}
	
	public Dialog getDialog()
	{
		return dialog;
	}
	
	public Fighter[] getFighter()
	{
		return fighter;
	}
	
	public Stage getStage()
	{
		return stage;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isTeamFight()
	{
		if(fighter.length>2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getMoney() {
		// TODO Auto-generated method stub
		return geld;
	}
	
}
