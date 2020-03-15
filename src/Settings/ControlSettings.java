package Settings;

import java.io.Serializable;
import java.util.ArrayList;

import Controlls.ControlSheet;
import Controlls.GamepadSheet;
import Controlls.KeyboardSheet;

public class ControlSettings implements Serializable{

	//Tastatur
	public final static int KEYS_RIGHT=0,KEYS_LEFT=1,KEYS_UP=2,KEYS_DOWN=3;
	public final static int KEYS_ATTACK=4,KEYS_KILOAD=5,KEYS_DEFENCE=6,KEYS_SPECIAL=7;
	public final static String[] keynames={"Right","Left","Up","Down","Attack","Ki-Load","Defence","Skill"};
	
	//Gamepad
	public final static String[] padnames={"Attack","Ki-Load","Defence","Skill","Ultimate","Special"};
	
	
	private ArrayList<ControlSheet> sheets=new ArrayList<ControlSheet>();
	
	
	public ControlSettings(){
		
		//Standard keys for Singleplayer
		KeyboardSheet key=new KeyboardSheet();
		key.standardKeys();
		sheets.add(key);
		
	}

	
	public void editSheet(int nr, int tasteid, int taste)
	{
		ControlSheet s=sheets.get(nr);
		if(s instanceof KeyboardSheet)
		{
			((KeyboardSheet) s).setKey(tasteid, taste);
		}
		else
		{
			((GamepadSheet)s).setButtonNr(tasteid, taste);
		}
	}
	
	public void changeGamepad(int nr, String newpad)
	{
		ControlSheet s=sheets.get(nr);
		if(s instanceof GamepadSheet)
		{
		((GamepadSheet)s).setPadname(newpad);
		}
	}
	
	
	
	public void newKeyboard()
	{
		sheets.add(new KeyboardSheet());
	}
	
	public void deleteSheet(int nr)
	{
		sheets.remove(nr);
	}
	
	public void newGamepad()
	{
		sheets.add(new GamepadSheet());
	}
	
	public ArrayList<ControlSheet> getSheets()
	{
		return sheets;
	}

	public boolean existSheet(int nr) {
		// TODO Auto-generated method stub
		boolean b=false;
		for(ControlSheet cs: sheets)
		{
			if(cs.getPlayer()==nr)
			{
				b=true;
				break;
			}
		}
		return b;
	}
	
	public ControlSheet getSheetForPlayer(int nr){
	  ControlSheet cs=null;
	  for(ControlSheet c: sheets)
		{
		  if(c.getPlayer()==nr)
		  {
			  cs=c;
			  break;
		  }
		}
	  return cs;
	}


	public void changePlayer(int s, int pid) {
		// TODO Auto-generated method stub
		sheets.get(s).setPlayer(pid);
	}
	
	public void changeActivity(int s, boolean active){
		sheets.get(s).setActive(active);
	}

	
}
