package Story;

public class DialogAktion {
	
	public final static int MAKEMOVE=0,CHANGEID=1,PLAYSOUND=2,PLAYFIGHTERSOUND=3,KILLFIGHTER=4,EINFLIEGEN=5;
	
	private int fighter;
	private int aktion;
	private int value;

	public DialogAktion(int fighter,int aktion, int value)
	{
		this.fighter=fighter;
		this.aktion=aktion;
		this.value=value;
	}
	
	public int getFighter()
	{
		return fighter;
	}
	
	public int getAktion()
	{
		return aktion;
	}
	
	public int getValue()
	{
		return value;
	}
	
	
}
