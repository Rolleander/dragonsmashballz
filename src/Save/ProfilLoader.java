package Save;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import Achievements.Achievements;
import DBZ.Main;
import Settings.ControlSettings;
import Settings.GameSettings;

public class ProfilLoader {
	
	
	public ProfilLoader(){
		
	}
	
	public ControlSettings readControlSettings() throws ClassNotFoundException, IOException
	{
		   ControlSettings control=null; 
		   InputStream file = new FileInputStream(Main.ordnerpfad+"/ControlSettings.dat" );
		   InputStream buffer = new BufferedInputStream( file );
		   ObjectInput input = new ObjectInputStream ( buffer );
		   control= (ControlSettings)input.readObject();
		   input.close();
		   return control;
	}
	
	public MatchHistory readMatchHistory() throws ClassNotFoundException, IOException
	{
		   MatchHistory history=null; 
		   InputStream file = new FileInputStream(Main.ordnerpfad+"/MatchHistory.dat" );
		   InputStream buffer = new BufferedInputStream( file );
		   ObjectInput input = new ObjectInputStream ( buffer );
		   history= (MatchHistory)input.readObject();
		   input.close();
		   return history;
	}
	
	public GameSettings readGameSettings() throws ClassNotFoundException, IOException
	{
		   GameSettings history=null; 
		   InputStream file = new FileInputStream(Main.ordnerpfad+"/GameSettings.dat" );
		   InputStream buffer = new BufferedInputStream( file );
		   ObjectInput input = new ObjectInputStream ( buffer );
		   history= (GameSettings)input.readObject();
		   input.close();
		   return history;
	}
	
	public Achievements readAchievements() throws ClassNotFoundException, IOException
	{
		   Achievements history=null; 
		   InputStream file = new FileInputStream(Main.ordnerpfad+"/Achievements.dat" );
		   InputStream buffer = new BufferedInputStream( file );
		   ObjectInput input = new ObjectInputStream ( buffer );
		   history= (Achievements)input.readObject();
		   input.close();
		   return history;
	}
	
	public Profil readProfil() throws ClassNotFoundException, IOException
	{
		   Profil profil=null; 
		   InputStream file = new FileInputStream( Main.ordnerpfad+"/SaveGame.dat" );
		   InputStream buffer = new BufferedInputStream( file );
		   ObjectInput input = new ObjectInputStream ( buffer );
		   profil= (Profil)input.readObject();
		   input.close();
		   return profil;
	}
	
}
