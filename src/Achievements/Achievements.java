package Achievements;

import java.io.Serializable;
import java.util.ArrayList;

public class Achievements implements Serializable{

	
	
	
	private ArrayList<Task> tasks=new ArrayList<Task>();
	public String[] categories={"All","Profil","Story","Battle","Special"};
	
	public Achievements()
	{
		//create achievements
		
		//Wins
		tasks.add(new Task("I am a Warrior!","Play your first Match",5,2));
		tasks.add(new Task("Call me Goku","Play 10 Matches",10,2));
		tasks.add(new Task("Saiyan Prince","Play 50 Matches",20,2));
		tasks.add(new Task("Master of the Universe","Play 300 Matches",50,2));
		tasks.add(new Task("DBZ Freak","Play 1000 Matches",99,2));
		//Owned Fighters
		tasks.add(new Task("Little Fighter","Own 5 Fighters",10,0));
		tasks.add(new Task("My Collection","Own 20 Fighters",20,0));
		tasks.add(new Task("Team Z","Own all purchasable Fighters",50,0));
		tasks.add(new Task("Special Guest","Own Hercule",10,true,0));
		tasks.add(new Task("Expensive Fusion","Purchase Vegito",10,true,0));  
		//Owned Items
		tasks.add(new Task("Itembag","Own 5 Items",10,0));
		tasks.add(new Task("Collector","Own 25 Items",20,0));
		tasks.add(new Task("Fullbuild","Own all Items",50,0));	
		tasks.add(new Task("My first Build","Create a Build for your Fighter",5,0));	
		//Money
		tasks.add(new Task("My first Penny","Earn Zenis",10,0));
		tasks.add(new Task("Money!","Have 100.000 Zenis",20,0));
		tasks.add(new Task("Ready to Rumble","Win your first Tournament Game",10,0));
		tasks.add(new Task("Champion","Win 10 Tournament Games",20,0));
		tasks.add(new Task("Tenkaichi","Win your first World Tournament Game",10,0));
		tasks.add(new Task("The new Hercule","Win 10 World Tournament Games",20,0));
		
		//Storyline
		tasks.add(new Task("Saiyan Saga","Finish the Saiyan Storyline",30,1));
		tasks.add(new Task("Cyborg Saga","Finish the Cyborg Storyline",50,1));
		tasks.add(new Task("Buu Saga","Finish the Buu Storyline",50,1));
		tasks.add(new Task("Better change Difficulty","Lose the first Story Battle",10,true,1));
		tasks.add(new Task("Its over 9000!","Vegeta meassured your Powerlevel",10,true,1));
		tasks.add(new Task("Its not over 9000!","Lose against Nappa with Goku",10,true,1));
		tasks.add(new Task("My own Body is the best","Win as Ginyu without Body Change",20,1));
		tasks.add(new Task("Cyborg Power","Win without Healing C-18 against Vegeta and Piccolo",20,1));
		tasks.add(new Task("Squidwards Revenge","Finish Squidwards Storyline",20,true,1));
		tasks.add(new Task("Broly unstoppable","Unlock SSj4 Broly",20,true,1));
		tasks.add(new Task("New Idea? No!","Unlock SSj5 Goku",20,true,1));
		//Teamfight
		tasks.add(new Task("Doublekill","Kill 2 Enemies in one Fight",10,2));
		tasks.add(new Task("Triplekill","Kill 3 Enemies in one Fight",20,2));
		tasks.add(new Task("Quadrakill","Kill 4 Enemies in one Fight",30,2));
		tasks.add(new Task("Pentakill","Kill 5 Enemies in one Fight",40,2));
		tasks.add(new Task("Overkill","Kill more than 5 Enemies in one Fight",50,2));
		tasks.add(new Task("Kame-hame-ha","Deal 500 max. Damage",10,2));
		tasks.add(new Task("Spirit Bomb","Deal 1000 max. Damage",20,2));
		tasks.add(new Task("Gamma Blaster","Deal 2000 max. Damage",50,2));
		tasks.add(new Task("Its a Draw!","End a Match with all Fighters killed",20,2));
		tasks.add(new Task("Human Champion","Defeat Hercule 1v1",10,true,2));
		tasks.add(new Task("Goku is the Greatest","Watch Goku winning agianst Vegeta 1v1",10,true,2));
		tasks.add(new Task("Vegeta finally wins","Watch Vegeta winning against Goku 1v1",10,true,2));	
		tasks.add(new Task("Amazing Battle","Fight 3 Minutes long",20,2));
		tasks.add(new Task("Full Recovery","Heal your full Health with Buu's passive Regeneration",10,2));	
		tasks.add(new Task("Super Saiyan","Transform into Super Saiyan",10,2));
		tasks.add(new Task("Cyborg Master","Fire 10 Special Techniques in one Game with a Cyborg",10,2));
		tasks.add(new Task("Absorber","heal 1000 Health with Absorbing",10,2));
		tasks.add(new Task("Lightning Aura","Load 1 Power Lightning",10,2));
		tasks.add(new Task("This is Madness","Fire a Spirit Bomb loaded with 10 Power Lightnings",20,2));
		tasks.add(new Task("Necrophiliac","Destroy a corpse",10,true,2));
		tasks.add(new Task("I want them dead","End a Teamfight without corpses",20,true,2));
		tasks.add(new Task("Random Party","Start a Game with 10 random Players and a random Map",10,true,2));
		//Special
	
		tasks.add(new Task("Help me!","Read the Help Information",5,3));			
		tasks.add(new Task("True Fan","Watch the Credits",5,3));	
		tasks.add(new Task("Pro Game","Connect a Gamepad",5,3));	
		tasks.add(new Task("Batlle Party","Connect 2 Gamepads",5,3));	
		
		tasks.add(new Task("Too much Gore","Deactivate Blood",5,true,3));	
		tasks.add(new Task("Nostalgic","Turn off the Color",5,true,3));	
		tasks.add(new Task("Need for Speed","Change the Game Speed FPS",5,true,3));	
	}
	
	
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	
	public ArrayList<Task> getTasksByFilter(int filter)
	{
       ArrayList<Task> t=new ArrayList<Task>();
       if(filter>0)
       {
       for(int i=0; i<tasks.size(); i++)
       {
    	   if(tasks.get(i).category==filter-1)
    	   {
    		   t.add(tasks.get(i));
    	   }
       }
       }
       else
       {
    	   t=tasks;
       }
       return t;
	}
	
	
}
