package DBZ.World;

import java.awt.Dimension;

public class Map {

	private byte[][][] map;
   Dimension dim=new Dimension();
 
	
	public Map(byte[][][] map)
	{
		this.map=map;
		dim.setSize(map.length, map[0].length);
		System.out.println(dim.width+" "+dim.height);
	}
	
	public Dimension getDimension()
	{
		return dim;
	}
	
	public byte getTile(int x, int y, int t)
	{
		byte tb=(byte)0;
     if(x>-1&&x<map.length&&y>-1&&y<map[0].length)
		{
		tb= map[x][y][t];
		
		}
		return tb;
	}

	public boolean isBlocked(int x, int y) {
	
		
		boolean block=false;
		
		   if(x>-1&&x<map.length&&y>-1&&y<map[0].length)
			{
			int t=map[x][y][0];
			if(t==0)			
			{
			
				block=true;
			}
			
			int t2=map[x][y][1];
			if(t2>9&&t2<30)			
			{
			
				block=true;
			}
			
			
			}
		
		return block;
		
	}
	
	
}
