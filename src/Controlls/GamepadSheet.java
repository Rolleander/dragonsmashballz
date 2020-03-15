package Controlls;

public class GamepadSheet extends ControlSheet {

	
	
	private int[] buttonnr=new int[6];
	private String padname="-";
	
	public GamepadSheet()
	{
		for(int i=0; i<buttonnr.length; i++)
		{
			buttonnr[i]=-1;
		}
	}
	
	public void setPadname(String p)
	{
		padname=p;
		for(int i=0; i<buttonnr.length; i++)
		{
			buttonnr[i]=-1;
		}
	}
	
	public String getPadname()
	{
		return padname;
	}
	
	public void setButtonNr(int nr, int button)
	{
		buttonnr[nr]=button;
	}
	
	public int[] getButtons()
	{
		return buttonnr;
	}
}
