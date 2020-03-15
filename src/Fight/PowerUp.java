package Fight;


public class PowerUp {

	
	public final static int HEILUNG=0,DEFPLUS=1,ATKPLUS=2,LOADPLUS=3,SPEEDPLUS=4,TELEPORT=5;
	
	private int art;
	private int dauer;
	private int power;
	
	private int time;
	
	private int[] atts;
	private int[] damage;
	private float[] pos;
	
	public PowerUp(int a,int p, int d)
	{
		art=a;
		dauer=d;
		power=p;
	}
	
	public PowerUp(int a,int p)
	{
		art=a;
		dauer=1;
		power=p;
	}
	
	
	
	public void use(int[] a, float[] p,int[] d, boolean left)
	{	
		
		atts=a;
		pos=p;
		damage=d;
		if(time>0)
		{
			if(time==dauer)
			{
			//set effekt
				switch(art)
				{
				case HEILUNG: atts[Fighter.ATT_HEALTH]+=power; break;
				case DEFPLUS: atts[Fighter.ATT_DEF]+=power;  break;
				case ATKPLUS: damage[0]+=power;  damage[1]+=power; break;
				case LOADPLUS: atts[Fighter.ATT_KILOAD]+=power; break;
				case SPEEDPLUS: atts[Fighter.ATT_SPEED]+=power;   atts[Fighter.ATT_ATKSPEED]-=power; break;
				case TELEPORT: 
					if(left)
					{
					pos[0]-=power;
					}
					else{				
						pos[0]+=power;
					}
				    break;
				}
			}
			if(time==0)
			{
			//clear time effekt	
				switch(art)
				{
				case DEFPLUS: atts[Fighter.ATT_DEF]-=power;  break;
				case ATKPLUS: damage[0]-=power;  damage[1]-=power; break;
				case LOADPLUS: atts[Fighter.ATT_KILOAD]-=power; break;
				case SPEEDPLUS: atts[Fighter.ATT_SPEED]-=power;   atts[Fighter.ATT_ATKSPEED]+=power; break;
				}
			}
			time--;
		}
		
	}
	
	public int[] getAtts()
	{
		return atts;
	}
	
	public float[] getPos()
	{
		return pos;
	}


	public void activate() {
		// TODO Auto-generated method stub
		time=dauer;
	}

	public int[] getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
	
}
