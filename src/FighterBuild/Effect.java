package FighterBuild;

public class Effect {



	public static final  int  EFFECT_STATUS=0,EFFECT_HEALTH=1,EFFECT_STRENGTH=2,EFFECT_DEFENCE=3,EFFECT_SPEED=4,EFFECT_KILOAD=7,
	EFFECT_SPECIAL=8,EFFECT_POWERUP=9,EFFECT_ATTACKSPEED=5,EFFECT_REGENERATEHEALTH=10,EFFECT_REGENERATEKI=11,EFFECT_KIPOWER=6;		
    public static final int TYP_ADD=0,TYP_PROZ=1;
    
    public static final int STATUS_KI2=0,STATUS_BLITZ=1,STATUS_NORMALHEAL=2,STATUS_FULLKICRIT=3,STATUS_PRIZEMONEY=4;
	
	private int typ,art;
	private float power;
	
	public Effect(float p, int t, int a)
	{
		power=p;
		art=a;
		typ=t;
	}
	
	public float getPower()
	{
		return power;
	}
	
	public int getType(){
		return typ;
	}
	
	public int getCalculate()
	{
		return art;
	}
	
}
