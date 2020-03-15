package DBZ;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class ErrorLogger {

	static String error=null;
	static int time=0;
	
	public static String getError()
	{
		if(time>0)
		{
			time--;
		}
		else			
		{
			error=null;
		}
		return error;
	}
	
	public static void setError(String err)
	{
		error=err;
		//write(error);
		time=100;
	}
	
	public static void setFatalError(String err)
	{		
		write(err);
		System.exit(0);
	}
	
	public static void writeFPS(int fps)
	{
		error="FPS:"+fps;
		time=5;
	}
	
	
	private static void write(String err)
	{
		String ordnerpfad=System.getProperty("user.home")+"/BRollGames/DragonSMASHBall";
		String datei="ErrorLog.txt";
		  try {
			    err+=" ..."+System.currentTimeMillis()+"...";
			    FileWriter out = new FileWriter(ordnerpfad+"/"+datei,true);
			    BufferedWriter bw = new BufferedWriter(out);
			    
			    bw.write(err);
			     bw.newLine();
	             
			bw.close();			
			
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void SetDisplayTime(int i) {
		// TODO Auto-generated method stub
		time=i;
	}
	
}
