package DBZ;



public class Animation {

	private int duration=0;
	private int image=0;
	private int maximage=0;
	private int timer=0;
	private boolean run=false;
	
	public Animation()
	{
		
	
	}
	
	public void newAnimation(int dauer, int start, int ende)
	{
		if(run==false)
		{
		timer=0;
		duration=dauer;
		run=true;
		image=start;
		maximage=ende;
		}
	}
	
	public void killAnimation()
	{
		run=false;
	}
	

	
	public int nextAnimation()
	{
		timer++;
		if(timer>=duration)
		{
			run=false;
		}
		double acbild=(((double)timer/(double)duration)*((double)maximage-(double)image)+(double)image);
		double bild =( Math.round( acbild ));
		return (int)bild;
	}
	
	public boolean isRunning()
	{
		return run;
	}
}
