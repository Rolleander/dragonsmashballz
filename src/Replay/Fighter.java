package Replay;

import java.util.ArrayList;

public class Fighter {

	
	
	private ArrayList<Integer> xpos=new ArrayList<Integer>();
	private ArrayList<Integer> ypos=new ArrayList<Integer>();
	private ArrayList<Integer> pid=new ArrayList<Integer>();
	private ArrayList<Integer> fid=new ArrayList<Integer>();
	private ArrayList<Boolean> left=new ArrayList<Boolean>();

	public Fighter()
	{
		
	}
	
	public void add(int x, int y, int p, int f, boolean dir)
	{
		xpos.add(x);
		ypos.add(y);
		pid.add(p);
		fid.add(f);
		left.add(dir);
	}
	
	public int getFighterId(int t)
	{
		return fid.get(t);
	}
	
	public  int getXpos(int t)
	{
		return xpos.get(t);
	}
	
	public int getYpos(int t)
	{
		return ypos.get(t);
	}
	
	public int getPid(int t)
	{
		return pid.get(t);
	}
	
	public boolean getDir(int l)
	{
		return left.get(l);
	}
	
}
