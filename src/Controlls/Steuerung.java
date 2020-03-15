package Controlls;

import java.awt.event.KeyEvent;


public class Steuerung {

	
	private int tasten[][]=new int[3][8];
	
	public Steuerung()
	{
		//Einzelspieler
		tasten[0][0]=KeyEvent.VK_RIGHT;
		tasten[0][1]=KeyEvent.VK_LEFT;
		tasten[0][2]=KeyEvent.VK_UP;
		tasten[0][3]=KeyEvent.VK_DOWN;
		tasten[0][4]=KeyEvent.VK_A;
		tasten[0][5]=KeyEvent.VK_S;
		tasten[0][6]=KeyEvent.VK_D;
		tasten[0][7]=KeyEvent.VK_W;
		
		//Spieler 1
		tasten[1][0]=KeyEvent.VK_NUMPAD6;
		tasten[1][1]=KeyEvent.VK_NUMPAD4;
		tasten[1][2]=KeyEvent.VK_NUMPAD8;
		tasten[1][3]=KeyEvent.VK_NUMPAD5;
		tasten[1][4]=KeyEvent.VK_I;
		tasten[1][5]=KeyEvent.VK_O;
		tasten[1][6]=KeyEvent.VK_P;
		tasten[1][7]=KeyEvent.VK_0;
		
		//Spieler 2
		tasten[2][0]=KeyEvent.VK_D;
		tasten[2][1]=KeyEvent.VK_A;
		tasten[2][2]=KeyEvent.VK_W;
		tasten[2][3]=KeyEvent.VK_S;
		tasten[2][4]=KeyEvent.VK_C;
		tasten[2][5]=KeyEvent.VK_V;
		tasten[2][6]=KeyEvent.VK_B;
		tasten[2][7]=KeyEvent.VK_G;
	}
	
	public String getTastenBeschreibung(int nr)
	{
		String s="";
		switch(nr)
		{
		case 0: s= "RIGHT"; break;
		case 1: s= "LEFT"; break;
		case 2: s= "UP"; break;
		case 3: s= "DOWN"; break;
		case 4: s= "FIGHT"; break;
		case 5: s= "KI"; break;
		case 6: s= "BLOCK"; break;
		case 7: s= "POWERUP"; break;
		}
		return s;
	}
	
	public int getTaste(int spieler, int taste)
	{
		return tasten[spieler][taste];
	}
	
	public void setTaste(int spieler, int taste, int wert)
	{
		tasten[spieler][taste]=wert;
	}
	
	public void read(String s)
	{
		   String[] data = s.split("\\;");
		   
		   for(int i=0; i<3; i++)
			{
				for(int h=0; h<8; h++)
				{
					tasten[i][h]=Integer.parseInt(data[i*8+h]);
				}
			}
	}
	
	
	public String getSteuerung()
	{
		String s="";
		for(int i=0; i<3; i++)
		{
			for(int h=0; h<8; h++)
			{
				s+=tasten[i][h]+";";
			}
		}
		return s;
	}
	
}
