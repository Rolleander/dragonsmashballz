package Save;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import Achievements.Achievements;
import DBZ.Main;
import Settings.ControlSettings;
import Settings.GameSettings;

public class ProfilSaver {

	
	
	
	public ProfilSaver()
	{
		
	}
	
	public void saveControlSettings(ControlSettings control)
	{
		 ObjectOutputStream o =null;
		try
		{
		FileOutputStream  fos = new FileOutputStream( Main.ordnerpfad+"/ControlSettings.dat" );
		   o = new ObjectOutputStream( fos );
		  o.writeObject( control);
		}
		catch ( IOException e ) { System.err.println("Error while Saving: "+ e ); }
		finally { try { o.close(); } catch ( Exception e ) { e.printStackTrace(); } }
	}
	
	public void saveProfil(Profil p)
	{
		 ObjectOutputStream o =null;
		try
		{
			FileOutputStream   fos = new FileOutputStream(Main.ordnerpfad+"/SaveGame.dat" );
			
		  o = new ObjectOutputStream( fos );
		  o.writeObject( p);
		}
		catch ( IOException e ) { System.err.println("Error while Saving: "+ e ); }
		finally { try { o.close(); } catch ( Exception e ) { e.printStackTrace(); } }
	}
	
	public void saveMatchHistory(MatchHistory history)
	{
		 ObjectOutputStream o =null;
		try
		{
			FileOutputStream   fos = new FileOutputStream(Main.ordnerpfad+"/MatchHistory.dat" );
		  o = new ObjectOutputStream( fos );
		  o.writeObject( history);
		}
		catch ( IOException e ) { System.err.println("Error while Saving: "+ e ); }
		finally { try { o.close(); } catch ( Exception e ) { e.printStackTrace(); } }
	}
	
	public void saveAchievements(Achievements history)
	{
		 ObjectOutputStream o =null;
		try
		{
			FileOutputStream   fos = new FileOutputStream(Main.ordnerpfad+"/Achievements.dat" );
		  o = new ObjectOutputStream( fos );
		  o.writeObject( history);
		}
		catch ( IOException e ) { System.err.println("Error while Saving: "+ e ); }
		finally { try { o.close(); } catch ( Exception e ) { e.printStackTrace(); } }
	}
	
	public void saveGameSettings(GameSettings history)
	{
		 ObjectOutputStream o =null;
		try
		{
			FileOutputStream   fos = new FileOutputStream(Main.ordnerpfad+"/GameSettings.dat" );
		  o = new ObjectOutputStream( fos );
		  o.writeObject( history);
		}
		catch ( IOException e ) { System.err.println("Error while Saving: "+ e ); }
		finally { try { o.close(); } catch ( Exception e ) { e.printStackTrace(); } }
	}
	
}
