package Controlls;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Rumbler;

public class GamePad {

	  private Controller controller;
	  private Component[] comps;  // holds the components

	  // comps[] indices for specific components
	  private int xAxisIdx, yAxisIdx, zAxisIdx, rzAxisIdx;
	                              // indices for the analog sticks axes
	  private int povIdx;         // index for the POV hat
	  private  ArrayList<Integer> buttonsIdx=new ArrayList<Integer>();   // indices for the buttons

	  private Rumbler[] rumblers;
	  private int rumblerIdx;      // index for the rumbler being used
	  private boolean rumblerOn = false;   // whether rumbler is on or off
	  private String controllername;

	  public GamePad(Controller c)
	  {
		  controller=c;
		  controllername=controller.getName();
		  System.out.println("Found Gamepad: "+controllername);
		  findCompIndices();
		  findRumblers();
	  }
	  public void poll()
	  // update the component values in the controller
	  {  
		  controller.poll(); 
	  }
	  
	  public String getControllerName()
	  {
		  return controllername;
	  }
	  
	  public float getX()
	  {
		  return  comps[ xAxisIdx ].getPollData();
	  }
	  
	  public float getY()
	  {
		  return  comps[ yAxisIdx ].getPollData();
	  }

	  public boolean[] getButtons()

	  {
	    boolean[] buttons = new boolean[buttonsIdx.size()];
	    float value;
	    for(int i=0; i < buttons.length; i++) {
	      value = comps[ buttonsIdx.get(i) ].getPollData();
	      buttons[i] = ((value == 0.0f) ? false : true);
	    }
	    return buttons;
	  }  


	  public boolean isButtonPressed(int pos)
	  {
	    if ((pos < 1) || (pos > buttonsIdx.size())) {
	      System.out.println("Button position out of range (1-" +
	                          buttonsIdx.size() + "): " + pos);
	      return false;
	    }

	 
	    float value = comps[ buttonsIdx.get(pos-1)].getPollData();   
	       // array range is 0-NUM_BUTTONS-1
	    return ((value == 0.0f) ? false : true);
	  } // end of isButtonPressed()



	  // ------------------- trigger a rumbler -------------------


	  public void setRumbler(boolean switchOn)
	  // turn the rumbler on or off
	  {
	    if (rumblerIdx != -1) {
	      if (switchOn)
	        rumblers[rumblerIdx].rumble(0.8f);  // almost full on for last rumbler
	      else  // switch off
	        rumblers[rumblerIdx].rumble(0.0f);
	      rumblerOn = switchOn;    // record rumbler's new status
	    }
	  }  // end of setRumbler()


	  public boolean isRumblerOn()
	  {  return rumblerOn;  }


	
	  private void findCompIndices()
	  {
	    comps = controller.getComponents();
	    if (comps.length == 0) {
	      System.out.println("No Components found");
	      System.exit(0);
	    }
	    else
	      System.out.println("Num. Components: " + comps.length);

	    // get the indices for the axes of the analog sticks: (x,y) and (z,rz)
	    xAxisIdx = findCompIndex(comps, Component.Identifier.Axis.X, "x-axis");
	    yAxisIdx = findCompIndex(comps, Component.Identifier.Axis.Y, "y-axis");

	    zAxisIdx = findCompIndex(comps, Component.Identifier.Axis.Z, "z-axis");
	    rzAxisIdx = findCompIndex(comps, Component.Identifier.Axis.RZ, "rz-axis");

	    // get POV hat index
	    povIdx = findCompIndex(comps, Component.Identifier.Axis.POV, "POV hat");

	    findButtons(comps);
	  } 
	  
	 private int findCompIndex(Component[] comps, Component.Identifier id, String nm)
	 {
		 Component c;
		 for(int i=0; i < comps.length; i++) {
			 c = comps[i];
			 if ((c.getIdentifier() == id) && !c.isRelative()) {
				// System.out.println("Found " + c.getName() + "; index: " + i);
				 return i;
			 }
		 }
	//	 System.out.println("No " + nm + " component found");
		 return -1;
	 	}  // end of findCompIndex()

	 private void findButtons(Component[] comps)
	  {
	   
	    Component c;
	    for(int i=0; i < comps.length; i++) {
	      c = comps[i];
	      if (isButton(c)) {    // deal with a button
	        
	          buttonsIdx.add(i);
	        //  System.out.println("Found " + c.getName() + "; index: " + i);
	                 
	      }
	    }
	  }  
	 
	 private boolean isButton(Component c)
	  {
	    if (!c.isAnalog() && !c.isRelative()) {    // digital and absolute
	      String className = c.getIdentifier().getClass().getName();
	      // System.out.println(c.getName() + " identifier: " + className);
	      if (className.endsWith("Button"))
	        return true;
	    }
	    return false;
	  }  // end of isButton()
	   
	 
	 private void findRumblers()
	  /* Find the rumblers. Use the last rumbler for making vibrations, 
	     an arbitrary decision. */
	  {
	    // get the game pad's rumblers
	    rumblers = controller.getRumblers();
	    if (rumblers.length == 0) {
	      System.out.println("No Rumblers found");
	      rumblerIdx = -1;
	    }
	    else {
	      System.out.println("Rumblers found: " + rumblers.length);
	      rumblerIdx = rumblers.length-1;    // use last rumbler
	    }
	  }  // end of findRumblers()
	
}
