package Controlls;

import DBZ.ErrorLogger;
import Fight.Fighter;


public class PlayerMovement {

	public PlayerMovement()
	{
		
	}
	
	public int getAction(boolean[] tasten)
	{
		int action=0;
		//Angriffe > Bewewgung
		if(tasten[6]&&tasten[0]==false&&tasten[1]==false&&tasten[2]==false&&tasten[3]==false)
		{
			action=17; //blocken
		}
		if(tasten[6]==false)
		{//Normal Bewegen
			
		for(int i=0; i<4; i++)
		{
		if(tasten[i])
		{
			action=i+1;
		}
		}
		
		}
		else
		{//Sprinten
			for(int i=0; i<4; i++)
			{
			if(tasten[i])
			{
				action=i+5;
			}
			}
		}
		
		
		//Angriffe
		if(tasten[4]){			
			
			if(tasten[2]){	//hoch
				action=10;
			}
			else if(tasten[3]){	//runter
				action=11;
			}
			else
			{//gerade
				action=9;
			}
			
			
		}
		
		
		
		//Ki Angriffe
		if(tasten[5]){		

			 if(tasten[6]){
				//2.te Special
				
				
					action=19;
				
			}
			else if(tasten[4]){
				//Ki attacke
				
				action=16;
				
			}
			else
			{//Ki Laden
				action=15;
				
			}
		}
		
		if(tasten[4]&&tasten[6]){
			//Spezial
			
			
				action=18;
			
			
		}
		if(tasten[7])
		{
			action=22;
			if(tasten[3])
			{//Specialmoveladen
				action=20;
			}
			if(tasten[2])
			{//Transform
				action=21;
			}
		}
		
	if(tasten[8])
	{
		
			action=18;			
		
	}
	
	if(tasten[9])
	{
		
			action=19;			
		
	}
	
		
		return action;
	}
	
}
