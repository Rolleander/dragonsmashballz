package Controlls;

import java.util.ArrayList;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class GamePads {

	
	private ArrayList<GamePad> gamepads=new ArrayList<GamePad>();
	
	
	public GamePads()
	{
		
	}
	
	public void search()
	{
		  ControllerEnvironment ce =ControllerEnvironment.getDefaultEnvironment();
		  Controller[] cs = ce.getControllers();
		  gamepads.clear();
	
			   for(int i=0; i<cs.length; i++)
			   {
				   Controller.Type type= cs[i].getType();
				   if ((type == Controller.Type.GAMEPAD) ||(type == Controller.Type.STICK))
				   {
					   gamepads.add(new GamePad(cs[i]));			   
				   }
			   }
	}
	
	public int getControllerAmount()
	{
		return gamepads.size();
	}
	
	public ArrayList<String> getGamepadNames()
	{
		ArrayList<String> names=new ArrayList<String>();
		for(GamePad pad: gamepads)
		{
			names.add(pad.getControllerName());
		}
		return names;
	}
	
	public void run()
	{
		for(GamePad pad: gamepads)
		{
			pad.poll();
		}
	}
	
	private int getIDbyName(String name)
	{
		int id=-1;
		for(int i=0; i<gamepads.size(); i++)
		{
			if(gamepads.get(i).getControllerName().equals(name))
			{
				id=i;
				break;
			}
		}
		return id;
	}


	public boolean[] getGamepadContol(String gamepadname, GamepadSheet sheet) {
		// TODO Auto-generated method stub
		boolean[] control=new boolean[10];
		int padID=getIDbyName(gamepadname);	
		if(padID>-1)
		{
			GamePad pad=gamepads.get(padID);
		
			float xstick=pad.getX();
			float ystick=pad.getY();
			
			boolean[] buttons=pad.getButtons();
		
			//Joystick Steering
			float sensitive=0.2f;
			   if(xstick>sensitive)
		       {
		    	   control[0]=true;
		       }
		       else if(xstick<-sensitive)
		       {		    	   
		    	 control[1]=true;
		       }
		       if(ystick>sensitive)
		       {
		    	  control[3]=true;
		       }
		       else if(ystick<-sensitive)
		       {
		    	  control[2]=true;
		       }
		     //Buttons
		    int[] buttonid=  sheet.getButtons(); 		
		    for(int i=0; i<buttonid.length; i++)
		    {
		    	if(buttonid[i]!=-1)
		    	{		    		
		    	control[i+4]=buttons[buttonid[i]];
		    	}
		    }
		}
		return control;
	}

	public int getPressedButton(String nr) {
		// TODO Auto-generated method stub
		int t=-1;
		for(GamePad pad: gamepads)
		{
			if(pad.getControllerName().equals(nr))
			{
			boolean[] butts=pad.getButtons();
			for(int i=0; i<butts.length; i++)
			{
				if(butts[i])
				{
					t=i;
					break;
				}
			}
			}
		}
		return t;
	}
	
	
}
