package Settings;

import java.io.Serializable;

public class GameSettings implements Serializable{

	
	
	private boolean fullscreen=false;
	private boolean color=true;
	private boolean playSound=true;
	private boolean playMusic=true;
	private boolean bloodOn=true;
	private boolean killMessage=true;
	private int fps=70;
	
	
	public GameSettings()
	{
		
	}
	
	public void setResolution(boolean b)
	{
		fullscreen=b;
	}
	
	public void switchResolution()
	{
		fullscreen=!fullscreen;
	}
	
	public void switchColorMode()
	{
		color=!color;
	}
	
	public void switchPlaySound()
	{
		playSound=!playSound;
	}
	
	public void switchPlayMusic()
	{
		playMusic=!playMusic;
	}
	
	public void switchBloodOn()
	{
		bloodOn=!bloodOn;
	}
	
	public void switchKillMessage()
	{
		killMessage=!killMessage;
	}
	
	public void setFPS(int newfps)
	{
		fps=newfps;
	}
		
	public boolean isFullscreen()
	{
		return fullscreen;
	}
	
	public boolean isColorOn()
	{
		return color;
	}
	
	public boolean playSound()
	{
		return playSound;
	}
	
	public boolean playMusic()
	{
		return playMusic;
	}
	
	public boolean isBloodOn()
	{
		return bloodOn;
	}
	
	public boolean showKillMessage()
	{
		return killMessage;
	}
	
	public int getFPS()
	{
		return fps;
	}
	
}
