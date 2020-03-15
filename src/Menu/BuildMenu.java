package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;



import DBZ.Main;
import Fight.FighterData;
import FighterBuild.Build;
import FighterBuild.Effect;
import FighterBuild.Item;
import FighterBuild.ItemData;
import Images.GameImages;
import Save.Profil;

public class BuildMenu extends MenuData{
	
	
	private int bselect=-1;
	private int scrolly=0;
	private ItemData idata=new ItemData();
	private Build[] build=new Build[Main.fighteranz];
	ArrayList<Item> items=	idata.getItems();
	
	public BuildMenu()
	{

	}
	
	public void open(Build[] build){
		
		this.build=build;
	}
	
	public Build[] getBuild()
	{
		return build;
	}

	public void paint(Graphics g, Profil profil) {
		// TODO Auto-generated method stub
		this.paintTitle(g, "Character Build");
		int x=50,y=90;
		if(bselect==-1)
		{

			for(int i=0; i<Main.fighteranz; i++)
			{
			      boolean unlocked=profil.haveFighter(FighterSelection.fighters[i]);
			      if(unlocked)
			      {
				  if(mx>=x&&mx<=x+80&&my>=y&&my<=y+50)
				  {
				
				  if(klickn==1)
				  {
					  
				  bselect=i;
				  }
				  g.setColor(new Color(150,150,150));
				  }
				  else
				  {
					  if(build[FighterSelection.fighters[i]].hatItems())
					  {
						  g.setColor(new Color(50,150,50));
					  }
					  else
					  {
					  g.setColor(new Color(50,50,50));
					  }
				  }
			      }
			      else{
			    	  g.setColor(new Color(150,0,0));
			      }
				
				  g.fillRoundRect(x,y,80,50,5,5);
					 g.setColor(new Color(100,100,100));
				  g.drawRoundRect(x,y,80,50,5,5);
				  g.setColor(Color.BLACK);
				  g.fillRect(x+13,y+3,56,46);	
				  if(unlocked==false)
				  {
					  g.drawImage(GameImages.fighterselection[0],x+15,y+5,50,40,null);
				  }
				  else
				  {
					  g.drawImage(GameImages.faces[FighterSelection.fighters[i]],x+15,y+5,null);
				  }
				  
					x+=90;
					if(x>=900)
					{
					y+=55;
					x=50;
					}
			}
		}
		else
		{

			x=100;
			y=50;
			int fid=FighterSelection.fighters[bselect];
			g.setColor(new Color(100,100,100));
			g.fillRoundRect(x,y,800,500,10,10);
			g.setColor(new Color(150,150,150));
			g.fillRoundRect(x+5,y+5,790,490,7,7);
			
			g.drawImage(GameImages.fighterselection[fid+2],x+5,y+5,null );
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("Items:", x+400, y+25);
			g.drawString(FighterData.getName(fid), x+110, y+40);
			int itnr=paintItemList(g,x+400,y+30,profil.getItems());
			if(itnr>-1)
			{
				
				build[fid].addItem(itnr+1);
			}
			paintBuild(g,x+10,y+110,fid);
			paintData(g,x+10,y+250,fid);
			
			
			String[] m={"Close"};
			
			if(paintMenu(g, m, x+10,y+450, 380,50)>-1)
			{
				bselect=-1; 
			}
		
           String[] m2={"Clear Build"};
			
			if(paintMenu(g, m2, x+120,y+50, 200,50)>-1)
			{
				build[fid].clear();
			}
			
		}
		
	}
	
	
  
	private void paintData(Graphics g, int x, int y, int fid) {
		// TODO Auto-generated method stub
		String[] d={"Health","Strength","Defence","Speed","Attack-Speed","Ki-Power","Ki-Load","Special"};
		int werte[]=new int[8];
	
		werte[0]=FighterData.getAttributes(fid)[2];
		werte[2]=FighterData.getAttributes(fid)[4];
		werte[3]=FighterData.getAttributes(fid)[5];
		werte[4]=35-FighterData.getAttributes(fid)[7];
		werte[6]=FighterData.getAttributes(fid)[6];
		
		werte[1]=FighterData.getDamages(fid)[0];
		werte[5]=FighterData.getDamages(fid)[2];
		werte[7]=FighterData.getDamages(fid)[3]+FighterData.getDamages(fid)[4];
		
		int werte2[]=getNewData(werte,fid);
		
	
		
		g.setColor(new Color(50,50,50));
		g.fillRoundRect(x,y-13,300,200,5,5);
		
	
		g.setFont(font3);
		
		for(int i=0; i<d.length; i++)
		{
			g.setColor(Color.WHITE);
			g.drawString(d[i], x+5, y+5);
			g.setColor(new Color(100,100,250));
			g.drawString(""+werte[i], x+120, y+5);
			
			g.setColor(Color.WHITE);
			g.drawString(">>>",x+160,y+5);
			if(werte2[i]>werte[i])
			{
				g.setColor(new Color(100,250,100));
			}
			else 	if(werte2[i]<werte[i])
			{
				g.setColor(new Color(250,100,100));
			}
			else
			{
				g.setColor(new Color(100,100,250));
			
			}
			
			g.drawString(""+werte2[i], x+200, y+5);
			y+=25;
			
		}
		
	}

	private int[] getNewData(int[] w, int fid) {
		
		int[] werte=new int[8];
		for(int i=0; i<8; i++)
		{
			werte[i]=w[i];
		}
		
		int[] it=build[fid].getBuild();
		for(int i=0; i<it.length; i++){
			
			if(it[i]>0)
			{
				Item item=items.get(it[i]-1);
				ArrayList<Effect> eff=item.getEffects();
				for(int h=0; h<eff.size(); h++)
				{
					Effect e=eff.get(h);
					int art=e.getType()-1;
					if(art>-1&&art<werte.length)
					{
						float power=e.getPower();
						if(e.getCalculate()==Effect.TYP_ADD)
						{
							werte[art]+=(int)power;
						}
						if(e.getCalculate()==Effect.TYP_PROZ)
						{
							werte[art]=(int)((float)werte[art]*power);
						}
					}
				}
				
			}
		}
		
		return werte;
	}

	private void paintBuild(Graphics g, int x, int y, int fid) {
		// TODO Auto-generated method stub
		int[] it=build[fid].getBuild();
		
		int is=-1;
		for(int i=0; i<7; i++)
		{
			g.setColor(new Color(50,50,50));
			if(it[i]>0)
			{
				if(mx>x&&mx<x+45&&my>y&&my<y+45)
				{
					is=i;
					
					g.setColor(new Color(100,100,100));
					
					
				}
			}
			
			g.fillRoundRect(x,y,45,45,3,3);
			
			if(mx>x&&mx<x+45&&my>y&&my<y+45)
			{
				if(it[i]>0)
				{
				g.setColor(new Color(200,0,0));
				g.fillRect(x+35,y,10,10);
				g.setColor(Color.WHITE);
				g.drawLine(x+35,y,x+45,y+10);
				g.drawLine(x+45,y,x+35,y+10);
				
				if(mx>=x+35&&my<=y+10)
				{
					if(klick)
					{
						build[fid].removeItem(i);

			    		   Main.sound.menuSound();
					}
				}
				}
			}
			
			if(it[i]>0)
			{
			Item item=items.get(it[i]-1);			
			g.drawImage(GameImages.itemicons[item.getIcon()],x+2,y,null);
			}
			x+=50;
		}
		x-=50*7;
		g.setColor(new Color(50,50,50));
		g.fillRoundRect(x,y+50,345,50,5,5);
		if(is>-1)
		{
			Item item=items.get(it[is]-1);	
			g.setFont(font3);
			g.setColor(new Color(100,100,250));
			g.drawString(item.getName(), x+10, y+70);
			g.setFont(font2);
			g.setColor(Color.WHITE);
			g.drawString(item.getBeschreibung(), x+10, y+90);
		}
	}

	public int paintItemList(Graphics g, int x, int y, boolean[] besitz)
	{
		int wahl=-1;
	
	 x+=150;
	  if(scrolly>0)
	    {
		g.drawImage(GameImages.messagepfeil,x+15,y-5,40,-15,null);
		if(mx>=x+15&&mx<=x+55&&my>=y-20&&my<=y-5){		
		
			if(klickn==1){
				   Main.sound.menuSound();
				scrolly--;
			}
		}	
	    }
	    x-=150;
	for(int i=0; i<10; i++)
	{
		int id=i+scrolly*10;
		Item it=null;
		if(id<items.size())
		{
			if(besitz[id])
			{
	 it=items.get(id);
		g.setColor(new Color(20,20,20));
		g.fillRoundRect(x-1,y-1,382,42,3,3);
			}
		
		}		
		
		
		if(mx>x&&mx<x+380&&my>y&&my<y+40)
		{
		     g.setColor(new Color(50,50,50));	
		     if(klick)
		     {
		    	 if(it!=null)
		    	 {
		       Main.sound.menuSound();
		    	 wahl=id;
		    	 }
		     }
		}
		else
		{
     g.setColor(new Color(80,80,80));	
		}
		if(!besitz[id])
		{
			   g.setColor(new Color(170,170,170));	
		}
		g.fillRoundRect(x,y,380,40,3,3);
		
		
		if(it!=null)
		{
			g.drawImage(GameImages.itemicons[it.getIcon()],x+5,y,null);
			g.setFont(font3);
			g.setColor(new Color(100,100,250));
			g.drawString(it.getName(), x+50, y+17);
			g.setFont(font2);
			g.setColor(Color.WHITE);
			g.drawString(it.getBeschreibung(), x+50, y+35);
		}
		else
		{
			g.setFont(font3);
			g.setColor(new Color(250,250,250));			
			g.drawString("Capsule "+(id+1), x+50, y+17);
			g.drawString("Purchase capsule in item shop", x+120, y+35);
		}
	y+=45;	
	
	}
     	
	 x+=150;
	    if(scrolly*10<items.size()-10)
	    {
		g.drawImage(GameImages.messagepfeil,x+15,y,null);
		if(mx>=x+15&&mx<=x+55&&my>=y&&my<=y+15){			
			if(klickn==1){				
				scrolly++;
				   Main.sound.menuSound();
			}
		}	
	    }
	
	
		
		
		return wahl;
	}
	
	

}
