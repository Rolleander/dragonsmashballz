package Controlls;

import java.awt.event.KeyEvent;

import Settings.ControlSettings;

public class KeyboardSheet extends ControlSheet{

	
	private int[] keys=new int[8];
	
	public KeyboardSheet()
	{
		for(int i=0; i<keys.length; i++)
		{
			keys[i]=-1;
		}
	}
	
	public void setKey(int nr, int key)
	{
		keys[nr]=key;
	}
	
	public int[] getKeys()
	{
		return keys;
	}

	public void standardKeys() {
		// TODO Auto-generated method stub
		player=0;
		keys[ControlSettings.KEYS_UP]=KeyEvent.VK_UP;
		keys[ControlSettings.KEYS_DOWN]=KeyEvent.VK_DOWN;
		keys[ControlSettings.KEYS_LEFT]=KeyEvent.VK_LEFT;
		keys[ControlSettings.KEYS_RIGHT]=KeyEvent.VK_RIGHT;
		keys[ControlSettings.KEYS_ATTACK]=KeyEvent.VK_A;
		keys[ControlSettings.KEYS_KILOAD]=KeyEvent.VK_S;
		keys[ControlSettings.KEYS_DEFENCE]=KeyEvent.VK_D;
		keys[ControlSettings.KEYS_SPECIAL]=KeyEvent.VK_W;
	}
	
}
