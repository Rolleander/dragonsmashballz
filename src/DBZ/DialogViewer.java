package DBZ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Fight.Fighter;
import Images.GameImages;
import Menu.MenuData;
import Story.Dialog;
import Story.DialogAktion;



public class DialogViewer extends MenuData {

	private Dialog dialog;
	private boolean stop=true;
	private int scroll=0;
	private int scrolltime=0;
	private int x=200,y=220;
	
	
	
	public DialogViewer()
	{
		
	}
	
	
	
	public Fighter[] paint(Graphics g, Fighter[] fighter)
	{
		if(dialog!=null)
		{
			stop=true;
			String text=dialog.getMessage();
			int speeker=dialog.getDialogSpeeker();
		
			//DialogAktion
			DialogAktion aktion=dialog.getDialogAktion();
			if(aktion!=null)
			{
				int akt=aktion.getAktion();
				int pla=aktion.getFighter();
				int val=aktion.getValue();
			switch(akt)
			{
			case DialogAktion.MAKEMOVE: 	fighter[pla].makeMove(val);
			if(val!=15){dialog.clearAktion();}  break;
			case DialogAktion.CHANGEID: 	fighter[pla].changeFighterId(val);
			      fighter[pla].pumpAnimation();   dialog.clearAktion();   break;
			case DialogAktion.PLAYSOUND:  Main.sound.playSound(val, true);
			dialog.clearAktion();break;
			case DialogAktion.PLAYFIGHTERSOUND: Main.sound.playDefeatSound(pla);
			dialog.clearAktion();break;
			case DialogAktion.KILLFIGHTER: fighter[pla].die(); dialog.clearAktion(); break;
			case DialogAktion.EINFLIEGEN: fighter[pla].einfliegen(); dialog.clearAktion(); break;
			
			}
				
		
			}
			boolean anz=true;
			for(int i=0; i<fighter.length; i++)
			{
				if(fighter[i]!=null)
				{
					if(fighter[i].inBewegung())
					{
						anz=false;
					}
				}
			}
			
			if(anz)
			{
			g.drawImage(GameImages.messagebox,x,y,null);
			g.drawImage(GameImages.fighterselection[speeker+2],x+11,y+10,null);
			g.setFont(font3);
			
			if(scroll<text.length())
			{
				scrolltime++;
				if(scrolltime>3)
				{
					scrolltime=0;
					Main.sound.playSound(18, true);
				scroll++;
				}
			}
			
			g.setColor(Color.BLACK);
			String[] t = text.split("\\/n ");	
			int txb=0;
			for(int i=0; i<t.length; i++)
			{
				
				String txt=t[i];
				if(txb<=scroll)
				{
					if(scroll<=txb+t[i].length())
					{
						txt=t[i].substring(0, scroll-txb);
					}
					g.drawString(txt,x+130,y+30+i*20);
				}
	
				txb+=t[i].length();
				
			}
			
			if(scroll==text.length())
			{
				scrolltime++;
				if(scrolltime>60)
				{
					scrolltime=0;
				}
				g.drawImage(GameImages.messagepfeil,x+300,y+110+scrolltime/20,null);
					
			}
			
		
		
			if(klick)
			{			
				if(scroll<text.length())
				{
					scroll=text.length();
					Main.sound.playSound(18, true);
				}
				else
				{

				
					Main.sound.playSound(17, true);
					scroll=0;	
				
					if(dialog.lastMessage()==false)
					{
						 aktion=dialog.getDialogAktion();
						 if(aktion!=null)
						 {
						 if(aktion.getAktion()==DialogAktion.MAKEMOVE)
						 {
							 if(aktion.getValue()==15)
							 {
								 fighter[aktion.getFighter()].setVollesKi();
							 }
						 }
						 }
						dialog.nextMessage();
						
					}
					else
					{
						dialog=null;
					}
					
				}
				
			}
			
			}
		}
		else
		{
			stop=false;
		}
		klickn=0;
		klick=false;
		return fighter;
	}
	
	public void setDialog(Dialog dialog)
	{
		this.dialog=dialog;
	}
  
	public boolean stopBattle()
	{
		if(dialog!=null)
		{
		return true;
		}
		else{
			return false;
			
		}
	}


	
}
