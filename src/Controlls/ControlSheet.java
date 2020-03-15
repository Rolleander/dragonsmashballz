package Controlls;

import java.io.Serializable;

public abstract class ControlSheet implements Serializable{

	
	protected int player;
	protected boolean active=true;
	
	public int getPlayer()
	{
		return player;
	}
	
	public void setPlayer(int nr)
	{
		player=nr;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean b)
	{
		active=b;
	}
}
