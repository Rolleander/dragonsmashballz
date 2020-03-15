package Story;

import java.util.ArrayList;

public class Chapter {

	private String name;
	
	private  ArrayList<StoryBattle> fights=new ArrayList<StoryBattle>();
	
	public Chapter(String name)
	{
		this.name=name;
	}
	

	
	public String getName()
	{
		return name;
	}
	
	public  ArrayList<StoryBattle>  getStoryBattles()
	{
		return fights;
	}
	
	public StoryBattle getFight(int id)
	{
		return fights.get(id);
	}
	
	public int getBattleAmount()
	{
		return fights.size();
	}

	
	public void addFight(StoryBattle fight)
	{
		fights.add(fight);
	}
	
}
