package Story;

public class Wunsch {

	
	public static final int ART_GELD=0,ART_FIGHTER=1,ART_ITEM=2,ART_MAP=3;
	
	private int art;
	private String name;
	private int value;
	
	public Wunsch(String n, int a, int v)
	{
		name=n;
		art=a;
		value=v;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public int getArt()
	{
		return art;
	}
	
	public int getWert()
	{
		return value;
	}
}
