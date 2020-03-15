package FighterBuild;

import java.util.ArrayList;

public class Item {

	

	private String name,beschreibung;
	private int preis,bildid;
	
	private ArrayList<Effect> effect=new ArrayList<Effect>();
	

	
	public Item(String name,String beschreibung,int preis, int bildid)
	{
		this.name=name;
		this.bildid=bildid;
		this.preis=preis;
		this.beschreibung=beschreibung;
	}
	
	
	public void addEffect(float eff, int type, int art)
	{
		effect.add(new Effect(eff,type,art));
		
	}
	
	public ArrayList<Effect> getEffects()
	{
		return effect;
	}
		
	
	public String getName()
	{
		return name;
	}
	
	public String getBeschreibung()
	{
		return beschreibung;
	}

	public int getPreis() {
		// TODO Auto-generated method stub
		return preis;
	}

	public int getIcon() {
		// TODO Auto-generated method stub
		return bildid;
	}
	
	
	
	
	
}
