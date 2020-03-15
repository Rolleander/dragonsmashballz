package DBZ;


public abstract class Form {

	protected float pos[]=new float[2];
	protected float speed[]=new float[2];
	protected boolean active=false;
	
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActivity(boolean b)
	{
		active=b;
	}
		
	public int[] getPos()
	{
		int[] i=new int[2];
		i[0]=(int)pos[0];
		i[1]=(int)pos[1];
		return i;
	}
	
	public float[] getSpeed(){		
		return speed;
	}
	
	public void setPos(int x, int y)
	{
		pos[0]=x;
		pos[1]=y;
	}
	
}
