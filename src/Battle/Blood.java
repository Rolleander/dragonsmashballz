package Battle;


import java.awt.Graphics;

public class Blood {

	private static BloodSplatter[] blood=new BloodSplatter[50];
	private static boolean bloodon=true;
	
	public Blood()
	{
		
	}
	
	public static void newBlood(int x, int y, double winkel,int art)
	{
		if(bloodon)
		{
		for(int i=0; i<blood.length; i++)
		{
			if(blood[i]==null)
			{
				blood[i]=new BloodSplatter(x,y,winkel,art);
				
				break;
			}
		}
		}
	}
	
	public static void resetBlood()
	{
		for(int i=0; i<blood.length; i++)
		{
			blood[i]=null;
		}
	}
	
	public static void paint(Graphics g)
	{
		if(bloodon)
		{
		for(int i=0; i<blood.length; i++)
		{
			if(blood[i]!=null)
			{
				if(blood[i].enden()==false)
				{
					blood[i].paint(g);
			
				}
				else
				{
					blood[i]=null;
				}
			}
		}
		}
	}
	
	public static boolean getBloodOn()
	{
		return bloodon;
	}
	
	public static  void switchBloodOn()
	{
		bloodon=!bloodon;
	}

	public static void setBloodOn(boolean bloodOn2) {
		// TODO Auto-generated method stub
		bloodon=bloodOn2;
	}
	
	
	
	
}
