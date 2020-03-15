package Misc;

import java.util.Timer;
import java.util.TimerTask;

public class MiniTimer {

	private Timer timer=new Timer();
	private Loop loop=new Loop();
	private boolean finished=false;
	private boolean isRunning=false;
	
	public MiniTimer()
	{
		
	}
	
	public void start(float d)
	{   
		finished=false;
		isRunning=true;
		if(isRunning)
		{
		timer.cancel();
		loop.cancel();
		loop=new Loop();
		timer=new Timer();
		
		}
		timer.schedule(loop, (long) (d*1000));
	}
	
	private class Loop extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			finished=true;
			isRunning=false;
			cancel();
		}		
	}
	
	public boolean isRunning()
	{
		if(isRunning&&finished==false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isFinished()
	{
		return finished;
	}
	
}
