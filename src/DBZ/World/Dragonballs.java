package DBZ.World;

public class Dragonballs {

	private int[][] pos=new int[7][2]; 
	private boolean[] active;
	
	public Dragonballs(){
		
		
	}
	
	
	public void init(boolean[] besitz, int maxx, int maxy)
	{
	active=besitz;	
	
	for(int i=0; i<7; i++)
	{
		if(active[i]==false)
		{
			pos[i][0]=(int)(Math.random()*(maxx-100)+1)+50;
			pos[i][1]=(int)(Math.random()*(maxy-100)+1)+50;
		}
	}
	   
	}
	
	public void clear(int maxx, int maxy)
	{
		for(int i=0; i<7; i++){
			
			active[i]=false;
			pos[i][0]=(int)(Math.random()*(maxx-100)+1)+50;
			pos[i][1]=(int)(Math.random()*(maxy-100)+1)+50;
		}
	}
	
	public boolean sammeln(int d)
	{
		boolean alle=true;
		active[d]=true;
		for(int i=0; i<7; i++)
		{
			if(active[i]==false)
			{
				alle=false;
				break;
			}
		}
		return alle;
	}
	
	public boolean frei(int d)
	{
		return !active[d];
	}
	
	public int[] getPos(int d)
	{
		return pos[d];
	}
	
	
	
}
