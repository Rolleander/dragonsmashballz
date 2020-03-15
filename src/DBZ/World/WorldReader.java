package DBZ.World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class WorldReader {

	
	public WorldReader(){
		
		
	}
	
	
	public Map[] read()
	{
		Map[] map=new Map[2];
		for(int i=0; i<2; i++)
		{
		    System.out.println("Read Worldmap"+(i+1)+"...");
			//URL filename = getClass().getResourceAsStream(Main.bf+"World"+(i+1)+".txt");	 
		 	String zeile=""; //Lesestrings

		 	 
		 StringBuffer m=new StringBuffer("");
				try {
					 BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("DBZ/Ressourcen/World"+(i+1)+".txt")));//Textdatei mit Name name einlesen			 		   
				    while((zeile = reader.readLine())!= null){
				    	m.append(zeile);
				    }
				     
				   }
			       catch (IOException f) 
			    {
			    System.err.println("Error2 :"+f);
			    }
			
			    System.out.println("Read succesfully");
			    System.out.println("Create Map"+(i+1));
	byte[][][]	mb=readMap(m.toString());
		
		 map[i]=new Map(mb);
		}
		return map;
	}


	private byte[][][] readMap(String m) {
		
		   String[] maps = m.split("\\;");
			String[] mapss = maps[0].split("\\,");
   	  int  mapw=Integer.parseInt(mapss[0]);
   	  int  maph=Integer.parseInt(mapss[1]);
   	  
   	    int x=0,y=0;
   	    byte[][][] fields=new byte[mapw][maph][2];
   	    
   	    for(int i=1; i<maps.length; i++)
   	    {
   	    	String[] felds = maps[i].split("\\,");
   	    	fields[x][y][0]=(byte)Integer.parseInt(felds[0]);
   	    	byte f2=(byte)Integer.parseInt(felds[1]);
   	 
   	    	 	fields[x][y][1]=f2;
   	    	y++;
   	    	if(y>=maph)
   	    	{
   	    		y=0;
   	    		x++;
   	    	}
   	    }
		return fields;
	}




}

