package DBZ;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.easyogg.OggClip;

import Fight.FighterData;


public class Sound {

	Clip sounds[]=new Clip[30]; //Anzahl Sounds 
	Clip fighters[][];//Fighter Sounds
	ArrayList<OggClip> music=new ArrayList<OggClip>();
	
	private boolean playMusic=true,playSounds=true;
	private int lastplayedsong;
	public final static int SONG_MENU=0,SONG_SELECTION=1,SONG_CREDITS=2,SONG_INTRO=3,SONG_STORY=6;
	
	public Sound()
	{
		System.out.println("Load Special Sounds");
		Main.LOADING="Loading Sounds";
		for(int i=0; i<sounds.length; i++){		
		sounds[i]=loadSound("FX/s"+i+".wav");	
		}
		 // FloatControl volume = (FloatControl) sounds[12].getControl(FloatControl.Type.MASTER_GAIN);
		// -80.0 bis 6.0206	
	
		
		
		System.out.println("Load Fighter Sounds");
		fighters=loadFighterSounds();
		System.out.println("Load Music");
		Main.LOADING="Loading Music";
		try {
			music.add(new OggClip("Music/menu.ogg"));
	
			music.add(new OggClip("Music/selection.ogg"));
			music.add(new OggClip("Music/Credits.ogg"));
			music.add( new OggClip("Music/intro.ogg"));
			
		 for(int i=0; i<7; i++)
		 {
			music.add( new OggClip("Music/battle"+i+".ogg"));
		 }
			System.out.println("Load additional Music");
			Main.LOADING="Loading additional Music";
		 File dir = new File(Main.ordnerpfad+"/Music");			
		 File[] fileList = dir.listFiles();
		 if(fileList!=null)
		 {
			 for(int i=0; i<fileList.length; i++)
			 {
				 File f=fileList[i];		
				 if(f.getName().toLowerCase().endsWith(".ogg"))
				 {
				 music.add(new OggClip( new FileInputStream(f.getPath())));
				 System.out.println("Found additional Battle Music: "+f.getName());
				 }
			 }
		 }		 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playSounds=false;
                playMusic=false;
	}
	
	public void playBattleMusic() {
		// TODO Auto-generated method stub
		int max=music.size()-4;
		playMusic((int)(Math.random()*max+1)+3);
	}
	
	   public boolean canPlayMusic()
	   {
		   return playMusic;
	   }
	   
	   public boolean canPlaySounds()
	   {
		   return playSounds;
	   }
	   
	 
	   
	   public void switchMusic()
	   {
		   Main.settings.switchPlayMusic();
		   playMusic=Main.settings.playMusic();
		   if(playMusic==false)
		   {
			   stopMusic();
		   }
		   else
		   {
			   playMusic(lastplayedsong);
		   }
	   }
	
	   public void switchSounds()
	   {
		   Main.settings.switchPlaySound();
		   playSounds=Main.settings.playSound();
		   if(playSounds==false)
		   {
			   killSounds();
		   }
	   }
	   
	   public void playKiDuell()
	   {
		   if(playSounds)
		   {
			   if(sounds[12].isRunning()==false)
			   {
				   sounds[12].start();
			   }
		   }
	   }
	   
	   public void stopKiDuell()
	   {
		   sounds[12].stop();
	   }
	   
	   public void stopMusic()
	   {
			for(int i=0; i<music.size(); i++)
			{			
					music.get(i).stop();
					
			}
	   }
	   
	public void playMusic(int nr)
	{
		for(int i=0; i<music.size(); i++)
		{				
			if(i!=nr)
			{
				music.get(i).stop();
				
			}
		}
	   if(playMusic)
	   {	
		music.get(nr).loop();
		lastplayedsong=nr;
	   }
	}
	
	
	
	public void playSound(int nr, boolean clear) // nr: soundnummer  clear: wenn false nur abspielen wenn sound gerade nicht abgespielt wird
	{		
		if(playSounds)
		{
		if(clear)
		{
		 sounds[nr].setMicrosecondPosition(0);
		
		}
		else
		{
			if(sounds[nr].getMicrosecondPosition()>=sounds[nr].getMicrosecondLength())
			{
				 sounds[nr].setMicrosecondPosition(0);
			}
		}
		
		sounds[nr].start();	
		}
	}
	
	public void loopSound(int nr)
	{	
		if(playSounds)
		{
		// sounds[nr].setMicrosecondPosition(0);
		sounds[nr].loop(999);	
		}
	}
	
	public void stopSound(int nr)
	{
		
		sounds[nr].stop();
		
	}
	
	public void killSounds()
	{
		for(int i=0; i<sounds.length; i++)
		{
			if(sounds[i].isRunning())
			{
				sounds[i].stop();
			}
		}
		for(int i=0; i<fighters.length; i++)
		{
		for(int h=0; h<fighters[0].length; h++)
		{
			if(fighters[i][h].isRunning())
			{
				fighters[i][h].stop();
			}
		}
		}
	}
	
	private Clip[][] loadFighterSounds()
	{
		Clip sounds[][]=new Clip[Main.fighteranz][11];
		for(int i=0; i<Main.fighteranz; i++)
		{
			String ordner="";
			switch(i)
			{
			case 0: ordner="Vegeta"; break;
			case 1: ordner="SSJVegeta"; break;
			case 2: ordner="Goku"; break;
			case 3: ordner="SSJGoku"; break;
			case 4: ordner="TeenGohan"; break;
			case 5: ordner="MysticGohan"; break;
			case 6: ordner="Gotenks"; break;
			case 7: ordner="Trunks"; break;
			case 8: ordner="Cell"; break;
			case 9: ordner="Buu"; break;
			case 10: ordner="Frieza"; break;
			case 11: ordner="Piccolo"; break;
			case 13: ordner="Android18"; break;
			case FighterData.IMPERFECTCELL: ordner="ImperfectCell"; break;
			case FighterData.CELLJR: ordner="CellJR"; break;
			case FighterData.C17: ordner="Android17"; break;
			case FighterData.C16: ordner="Android16"; break;
			case FighterData.GOTENKS: ordner="Gotenks"; break;
			case FighterData.SSJGOTENKS: ordner="Gotenks"; break;
			case FighterData.FATBOO: ordner="FatBoo"; break;
			case FighterData.SCNDCELL: ordner="Cell"; break;
			case FighterData.SSJVEGETA: ordner="Vegeta"; break;
			case FighterData.SSJ4VEGETA: ordner="SSJVegeta"; break;		
			case FighterData.KIDBOO: ordner="KidBuu"; break;
			case FighterData.SSJ3GOKU: ordner="SSJGoku"; break;
			case FighterData.SSJ2GOKU: ordner="SSJGoku"; break;
			case FighterData.SSJ4GOKU: ordner="SSJGoku"; break;
			case FighterData.GOKUSSJ5: ordner="SSJGoku"; break;
			case FighterData.FREEZER100: ordner="Frieza"; break;
			case FighterData.BROLY: ordner="Broly"; break;
			case FighterData.SSJ4BROLY: ordner="Broly"; break;
			case FighterData.SSJSONGOHAN: ordner="MysticGohan"; break;
			case FighterData.GOHAN: ordner="MysticGohan"; break;
			case FighterData.YAMCHA: ordner="Yamcha"; break;
			case FighterData.KRILLIN: ordner="Krillin"; break;
			case FighterData.SQUID: ordner="Squid"; break;
			case FighterData.JEECE: ordner="Jeice"; break;
			case FighterData.TENSHINHAN: ordner="Tien"; break;
			case FighterData.VEGETO: ordner="Vegito"; break;
			case FighterData.SSJGOTEN: ordner="Goten"; break;
			case FighterData.YOUNGTRUNKS: ordner="KidTrunks"; break;
			case FighterData.UUB: ordner="Uub"; break;
			case FighterData.NAPPA: ordner="Nappa"; break;
			case FighterData.GULDO: ordner="Guldo"; break;
			case FighterData.RECOOME: ordner="Recoome"; break;
			case FighterData.BURTER: ordner="Burter"; break;
			case FighterData.HERCULE: ordner="Hercule"; break;
			case FighterData.CELLSUPER: ordner="Cell"; break;
			case FighterData.GOHANSSJ2: ordner="TeenGohan"; break;
			case FighterData.COOLER: ordner="Cooler"; break;
			case FighterData.GOHANKID: ordner="TeenGohan"; break;
			case FighterData.TRUNKSFUTURE: ordner="Trunks"; break;
			case 27: ordner="Ginyu"; break;
			case 29: ordner="DrGero"; break;
		
			
		
			 default: ordner="Buu"; break;
	
			}
			for(int h=0; h<11; h++)
			{
				String sound="";
				switch(h)
				{
				case 0: sound="Defeat"; break;
				case 1: sound="Energy"; break;
				case 2: sound="Special"; break;
				}
				if(h>2&&h<7)
				{
					sound="Hit"+(h-2);
				}
				if(h>6&&h<11)
				{
					sound="Hurt"+(h-6);
				}
			sounds[i][h]=loadSound("Charactersounds/"+ordner+"/"+sound+".wav");
			}
		}
	
		return sounds;
	}
	
	public void playDamageSound(boolean strong)
	{
		if(playSounds)
		{
			int s=0;
			if(strong)
			{
			      s=(int)(Math.random()*2+1)+3;
			}
			else
			{
	      s=(int)(Math.random()*4+1)-1;
			}
		sounds[s].setMicrosecondPosition(0);
		sounds[s].start();
		}
	}
	
	public void playDefeatSound(int id)
	{
		if(playSounds)
		{
		fighters[id][0].setMicrosecondPosition(0);
		fighters[id][0].start();
		
		
		
		}
	}
	
	public void playSpecialSound(int id)
	{
		if(playSounds)
		{
		fighters[id][2].setMicrosecondPosition(0);
		fighters[id][2].start();
		}
	}
	
	public void playHitSound(int id)
	{
		if(playSounds)
		{
		if((int)(Math.random()*3+1)==1)
		{
		int s=2+(int)(Math.random()*4+1);
		fighters[id][s].setMicrosecondPosition(0);
		fighters[id][s].start();
		}
		}
	}
	
	public void playHurtSound(int id)
	{
		if(playSounds)
		{
		if((int)(Math.random()*2+1)==1)
		{
		int s=6+(int)(Math.random()*4+1);
		fighters[id][s].setMicrosecondPosition(0);
		fighters[id][s].start();
		}
		}
	}
	
	public void playLoadingSound(int id)
	{
		if(playSounds)
		{
		fighters[id][1].loop(10);
		sounds[13].loop(10);
		}
	}
	
	public void stopLoadingSound(int id)
	{
		
		fighters[id][1].stop();
		sounds[13].stop();
	}
	
	public void fireKiAttackSound()
	{
		if(playSounds)
		{
		sounds[7].setMicrosecondPosition(0);
		sounds[7].start();
		}
	}
	
	public void hitKiAttackSound()
	{
		if(playSounds)
		{
		sounds[8].setMicrosecondPosition(0);
		sounds[8].start();
		}
	}
	
	public void fireKiBeamSound()
	{
		if(playSounds)
		{
		sounds[9].setMicrosecondPosition(0);
		sounds[9].start();
		}
	}
	
	public void loadSpecialSound()
	{
		if(playSounds)
		{
			sounds[16].setMicrosecondPosition(0);
			sounds[16].start();
		}			
	}
	
	public void menuSound()
	{
		if(playSounds)
		{
			sounds[17].setMicrosecondPosition(0);
			sounds[17].start();
		}			
	}
	
	public void teleportSound()
	{
		if(playSounds)
		{
			sounds[14].setMicrosecondPosition(0);
			sounds[14].start();
		}			
	}
	
	public void fallGroundSound()
	{
		if(playSounds)
		{
			sounds[15].setMicrosecondPosition(0);
			sounds[15].start();
		}			
	}
	
	public void hitKiBeamSound()
	{
		if(playSounds)
		{
		sounds[10].setMicrosecondPosition(0);
		sounds[10].start();
		}
	}
	
	private Clip loadSound(String name)
	{
	    Clip clip = null;   
		try {	      
			try{
				// Open an audio input stream.
			    java.net.URL url = this.getClass().getClassLoader().getResource(name);
			  
			   
			      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			      clip = AudioSystem.getClip();
			      // Open audio clip and load samples from the audio input stream.
			      clip.open(audioIn);
			    clip.setMicrosecondPosition(0);			
			
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			    
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return clip;
	}

	public void setMusicAndSound() {
		// TODO Auto-generated method stub
		playMusic=Main.settings.playMusic();
		playSounds=Main.settings.playSound();
	}


	
	
	
}
