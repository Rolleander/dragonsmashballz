package Save;

import java.io.Serializable;

import DBZ.Main;
import FighterBuild.Build;


public class Profil implements Serializable {

	
	private boolean fighter[]=new boolean[Main.fighteranz];
	private int zeni=80000;
	private int spielzeit=0;
	private int siege=0,niederlagen=0;
	private int teamfights=0;
	private int storyprogress=0;
	private boolean[] dragonballs=new boolean[7];
    private boolean[] item=new boolean[100];
    private Build[] build=new Build[Main.fighteranz];
    
    private int[][] statistic=new int[Main.fighteranz][8];
    private int[][] pcstatistic=new int[Main.fighteranz][8];
    private int[] mainstatistic=new int[50];
	/*
	 * 0. siege
	 * 1. niederlagen
	 * 2. Kills
	 * 3. schaden erteilt
	 * 4. schaden erhalten
	 * 5. Maximaler Schaden
	 * 
	 */
  
	public Profil()
	{
		for(int i=0; i<fighter.length; i++)
		{
			fighter[i]=false;
			
		}
		for(int i=0; i<item.length; i++)
		{
			item[i]=false;
		}
		for(int i=0; i<build.length; i++)
		{
			build[i]=new Build();
		}
		fighter[0]=true;//Vegeta
		fighter[2]=true;//Goku
		
	}
	
	public void update()
	{
		if(Main.fighteranz>fighter.length)
		{
			//Update Fighter 
			boolean[] f=new boolean[Main.fighteranz];
			Build[] b=new Build[Main.fighteranz];
			int[][] stat=new int[Main.fighteranz][8];
			int[][] stat2=new int[Main.fighteranz][8];
			for(int i=0; i<f.length; i++)
			{
				if(i<fighter.length)
				{
				f[i]=fighter[i];
				b[i]=build[i];
				stat[i]=statistic[i];
				stat2[i]=pcstatistic[i];
				}
				else{					
					b[i]=new Build();
				}
				
			}
			statistic=stat;
			pcstatistic=stat2;
			build=b;
			fighter=f;	
		}
	}
	
	
	public int[] getMainStatistic()
	{
		return mainstatistic;
	}
	
	public void setMainStatistic(int nr, int w)
	{
		mainstatistic[nr]=w;
	}
	
	public int[] getPCStatistic(int nr)
	{
		return pcstatistic[nr];
	}
	
	public void setPCStatistic(int nr, int[] s)
	{
		statistic[nr]=s;
	}
	
	public int[][] getPCStatistic()
	{
		return pcstatistic;
	}
	
	public void setPCStatistic(int[][] s)
	{
		pcstatistic=s;
	}
	
	public int[] getStatistic(int nr)
	{
		return statistic[nr];
	}
	
	public void setStatistic(int nr, int[] s)
	{
		statistic[nr]=s;
	}
	
	public int[][] getStatistic()
	{
		return statistic;
	}
	
	public void setStatistic(int[][] s)
	{
		statistic=s;
	}
	
	public boolean getDragonball(int id)
	{
		return dragonballs[id];
	}
	
	public void setDragonball(int id, boolean d)
	{
		dragonballs[id]=d;
	}
	
	public int getFighterAmount()
	{
		int a=0;
		for(int i=0; i<fighter.length; i++)
		{
			if(fighter[i])
			{
				a++;
			}
		}
		return a;
	}
	
	public int getItemAmount()
	{
		int a=0;
		for(int i=0; i<item.length; i++)
		{
			if(item[i])
			{
				a++;
			}
		}
		return a;
	}
	
	public boolean[] getFighters()
	{
		return fighter;
	}
	
	public boolean haveFighter(int id)
	{
		return fighter[id];
	}
	
	public void setFighter(int id,boolean b)
	{
		fighter[id]=b;
	}
	
	public int getStoryProgress()
	{
		return storyprogress;
	}
	
	public int getTeamFights()
	{
		return teamfights;
	}
	
	public void setStoryProgress(int prog)
	{
		storyprogress=prog;
	}
	
	public void setTeamFights(int t)
	{
		teamfights=t;
	}
	
	public int getSiege()
	{
		return siege;
	}
	
	public void setSiege(int s)
	{
		siege=s;
	}
	
	public int getNiederlagen()
	{
		return niederlagen;
	}
	
	public void setNiederlagen(int n)
	{
		niederlagen=n;
	}
	
	public void setZeni(int z)
	{
		zeni=z;
	
	}
	
	public void addZeni(int add)
	{
		zeni+=add;
	}
	
	public void tick()
	{
		spielzeit++;
	}
	
	public int getSpielzeit()
	{
		return spielzeit;
	}
	
	public void setSpielzeit(int z)
	{
		spielzeit=z;
	}
	
	public int getZeni()
	{
		return zeni;
	}

	public boolean[] getDragonball() {
	
		return dragonballs;
	}
	
	public boolean[] getItems()
	{
		return item;
	}
	
	public void setItem(int nr, boolean b)
	{
		item[nr]=b;
	}
	
	
	public boolean haveItem(int nr)
	{
		return item[nr];
	}
	
	public void setBuild(int nr, Build b)
	{
		build[nr]=b;
	}
	
	public Build getBuild(int nr)
	{
		return build[nr];
	}

	public Build[] getBuilds() {
		// TODO Auto-generated method stub
		return build;
	}

	public void setBuilds(Build[] build2) {
		// TODO Auto-generated method stub
		build=build2;
	}
	
	
}
