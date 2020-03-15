package Replay;

public class Replay {

	
	
	private Fighter[] fighter;
	
	public Replay(int fanz)
	{
		fighter=new Fighter[fanz];
		for(int i=0; i<fanz; i++)
		{
			fighter[i]=new Fighter();
		}
	}
	

	
}
