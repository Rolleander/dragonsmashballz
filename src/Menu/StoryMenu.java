package Menu;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


import DBZ.Main;
import DBZ.Sound;
import DBZ.World.WorldMap;
import DBZ.World.WorldReader;
import Fight.Fight;
import Save.Profil;
import Story.Chapter;
import Story.Shenlong;
import Story.Story;
import Story.StoryBattle;
import Story.Wunsch;



public class StoryMenu extends MenuData {

	private Story story;

	private Chapter chapter;
	private Fight battle;
	private int lastbattle=0,money=0;
	private Shenlong shenlong;
	
	private Wunsch wunsch;


	private WorldMap world;
	
	public StoryMenu(Story story, boolean[] dragonballs)
	{
		this.story=story;
	 chapter=	this.story.getChapter();
	 
	    WorldReader reader=new WorldReader();
	    world=new WorldMap(reader.read(),dragonballs);
	    world.setChapters(chapter);
	
	 
	}
	
	
	
	
	public void makeMove(boolean[] keys)
	{
		
		world.makeMove(keys);
		if(keys[6]){
		    
			   
				 exit=true;
				 Main.hideMouse(false);
			 
		}
	}
	
	public void paint(Graphics g,Profil p) {
	
		if(world.isOpen())
		{
			
			if(world.ruftShenlong())
			{
				if(shenlong==null)
				{
					shenlong=new Shenlong(p);
					Main.hideMouse(false);
					Main.sound.playMusic(Sound.SONG_SELECTION);
				}
				else
				{
				 Wunsch wahl=  shenlong.paint(g);
					shenlong.setMousePos(mx, my);
					shenlong.setKlick(klickn);
					if(wahl!=null)
					{
						shenlong=null;
						Main.hideMouse(true);
						Main.sound.playMusic(Sound.SONG_STORY);
						world.endeShenlong();
						wunsch=wahl;
						
						
						
					}
				}
			}
			else
			{
				world.paint(g,story.getProgress());
				StoryBattle b=world.wantFight();
		    if(b!=null)
		    {
		    	
		    	battle=b.getFight();
		    	lastbattle=b.getNr();
		    	money=b.getMoney();
		    	 battle.setModus("Story Fight: "+b.getName());
		    	go=true;
		    }
			}
			
			
			
		    
		}
		
	
	}
	

	
	
	public Fight getFight()
	{
		Fight b=battle;
		
		battle=null;
		return b;
	}


   public Wunsch hatWunsch()
   {
	   Wunsch w=wunsch;
	   wunsch=null;
	   return w;
   }
	
	public int refresh(Story story, int loser) {
	    	this.story=story;
	    	int geld=0;
	    
	    	
	    	 if(loser!=0)
	    	 {
	    		
	    		 if(lastbattle==this.story.getProgress()){
	    			 
	    				 story.nextStory();
	    				 if(story.getProgress()==17)
	    				 {
	    					 world.switchMap(0);
	    					 
	    				 }
	    				 geld=money;
	    		 }
	    		 else
	    		 {
	    			 geld=money/4;
	    		 }
	    	 }
	    	 chapter=	this.story.getChapter();
	    	 world.setChapters(chapter);
	    	 Main.hideMouse(true);
	    	 return geld;
	}

	
	public int getStoryProgress()
	{
		return story.getProgress();
	}
   

}
