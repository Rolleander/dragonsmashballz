package Tournament;

import java.awt.Graphics;

import Fight.Fight;
import Fight.Fighter;
import Menu.MenuData;

public abstract class TournamentGame extends MenuData{
	

	protected int finishscreen=0;
	protected int round=0;
	
	protected boolean fight=false,finish=false,tournamentgo=false;
	protected Fight tfight;

	
	

	public boolean wantFight()
	{
		boolean b=fight;
		fight=false;
		return b;
	}
	
	public Fight getFight()
	{
		return tfight;
	}
	
	protected void finish(boolean sieg) {
		finishscreen=300;
		finish=sieg;
	}
	
  	abstract public void win();

  	abstract public void paint(Graphics g);

	abstract public void lose();

	protected abstract void init();

	
	public void start()
	{
		tournamentgo=true;
	}
	
	public boolean isRunning()
	{
		return tournamentgo;
	}

	public void stop() {
		tournamentgo=false;
		round=0;
	
		exit=false;
		fight=false;
		init();
	}

	public void startTournament(Fighter f) {
		// TODO Auto-generated method stub
		
	}
	
	public void startTournamen()
	{
		
	}
	

}
