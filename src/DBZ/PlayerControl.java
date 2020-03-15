package DBZ;

public class PlayerControl {


	private boolean isComputer;
	private int controls;
	private String name;
	private int iconid;
	
	public PlayerControl()
	{		
		comp();
	}
	
	private void comp()
	{
		name="Comp";
		iconid=0;
		isComputer=true;
		controls=0;
	}
	
	public PlayerControl(int number)
	{
		if(number==0)
		{
		comp();	
		}
		else
		{
		name="Player "+(number);
		isComputer=false;
		iconid=number;
		controls=number;
		}
	}
	
/*	//Simonolus Abfrage
	public boolean isPlayerANeger()
	{
		if(name.equals("Neger")){
			return true;
		}
		else
		{
			return false;
		}
	}
	*/
	
	public boolean isComputer()
	{
		return isComputer;
		
	}
	
	public int getPlayerControls(){
		return controls;
	}
	
	public String getControlName()
	{
		return name;
	}
	
    public int getIconID()
    {
    	return iconid;
    }
}
