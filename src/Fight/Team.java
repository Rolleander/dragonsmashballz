package Fight;

import java.awt.Color;
import java.util.ArrayList;

import DBZ.PlayerControl;
import Story.Chapter;

public class Team {

	private int[] team;
	private PlayerControl[] steuerung;
	
	public final static int CTRL_COMP=0,CTRL_P0=1,CTRL_P1=2,CTRL_P2=3;
	
	public Team(ArrayList<Integer> team)
	{
		this.team=new int[team.size()];
		for(int i=0; i<team.size(); i++)
		{
			this.team[i]=team.get(i);
		
		}		
	}
	
	public void switchTeam(int id1, int id2)
	{
	
	
		int t1=team[id1];
		team[id1]=team[id2];
		team[id2]=t1;
	/*  int  t1=steuerung[id1];
		steuerung[id1]=steuerung[id2];
		steuerung[id2]=t1;*/
		
	
	}
	
	public void switchSteuerung(int i, int h) {
		// TODO Auto-generated method stub
		PlayerControl str1=steuerung[i];
		steuerung[i]=steuerung[h];
		steuerung[h]=str1;
	}
	
	public void setSteuerung(ArrayList<PlayerControl> st)
	{
		this.steuerung=new PlayerControl[st.size()];
		for(int i=0; i<st.size(); i++)
		{
			this.steuerung[i]=st.get(i);

		}

	}
	
	public PlayerControl getSteuerung(int id)
	{
		return steuerung[id];
	}
	
	public boolean isEnemy(int id, int id2)
	{
	
		if(team[id]!=team[id2])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getTeam(int id)
	{
		
		return team[id];
	}
	
	public Color getTeamColor( int id)
	{
		Color c=null;
		switch(id)
		{
		case 0: c=new Color(0,0,150); break;
		case 1: c=new Color(150,0,0); break;
		case 2: c=new Color(0,150,0); break;
		case 3: c=new Color(150,150,0); break;
		}
		
		return c;
	}

	public ArrayList<Integer> getTeamFighters(int id)
	{
		ArrayList<Integer> f=new ArrayList<Integer>();		
		for(int i=0; i<team.length; i++)
		{
			if(team[i]==id)
			{
				f.add(i);
			}
		}		
		return f;
	}

	public ArrayList<Integer> getTeams() {
		ArrayList<Integer> t=new ArrayList<Integer>();
		for(int i=0; i<team.length; i++)
		{
			t.add(team[i]);
		}
		return t;
	}

	public ArrayList<Integer> getControllIcons()
	{
		ArrayList<Integer> t=new ArrayList<Integer>();
		for(int i=0; i<steuerung.length; i++)
		{
			t.add(steuerung[i].getIconID());
		}
		return t;
	}
	
	
}
