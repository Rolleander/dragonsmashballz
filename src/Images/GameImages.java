package Images;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.net.URL;


public class GameImages {

	public static int fighteranz=DBZ.Main.fighteranz;
	public static Image fighter[][] = new Image[fighteranz][30];
	public static Image specialfighter[][] = new Image[1][30];
	public static Image faces[] = new Image[fighteranz];
	public static Image clouds[] = new Image[3];
	public static Image maps[] = new Image[22];
	public static Image aura[] = new Image[10];
	public static Image chapterimages[] = new Image[5];
	public static Image stages[] = new Image[maps.length+1];
	public static Image fighterselection[] = new Image[fighteranz+2];
	public static Image effects[],explosions[],congratulations,messagepfeil,messagebox,failed,hud,leben,ki,nextrow,title,ko,menuback,mapselection,vs,versusscreen;
	public static Image blitzicon[] = new Image[2];
	public static Image fighticon[] = new Image[2];
	public static Image multisteer[] = new Image[11];
	public static Image menuicons[] = new Image[3];
	public static Image cursor[] = new Image[3];
	public static Image active[] = new Image[2];
	public static Image dragonballs[] = new Image[7];
	public static Image itemicons[] = new Image[10];
	public static Image worldicons[] = new Image[26];
	public static Image maptiles[][]=new Image[2][100];
	public static Image titlemenucursors[] = new Image[2];
	public static Image minimap[]=new Image[2];
	public static Image controllicons[]=new Image[2];
	public static Image help[]=new Image[10];
	public static Image atticons[]=new Image[5];
	public static Image achblock[]=new Image[2];
	public static Image duellpress[]=new Image[2];
	public static Image mapchars[]=new Image[48];
	public static Image mouse0,keyboard,gamepad,padchooser,pause,brollintro,storybattle,glitter;
	public static Image pod,stars,pluscharacter,subcharacter,introback,logo,schild,shenlong,songoku,songohan,ring,ring2,raumschiff,brolllogo,hudready,hudfight;
	 static String bf="/DBZ/Ressourcen/";
	private Frame frame=null;
	
	public GameImages(Frame f)
	{
		this.frame=f;
		renderImages();
	}
	
	public void renderImages()
	{
		
	    System.out.println("Render Fighters");
		fighter=bilderKaempfer();
	    System.out.println("Render Effects");
		 effects=bilderEffekte();
		    System.out.println("Render Explosions");
		 explosions=bilderExplosionen();
		    System.out.println("Render Others");
		 URL filename = getClass().getResource(bf+"hud.png");
		hud=Toolkit.getDefaultToolkit().getImage( filename );	
		 filename = getClass().getResource(bf+"mapselection.png");
			mapselection=Toolkit.getDefaultToolkit().getImage( filename );
		 filename = getClass().getResource(bf+"leben.png");
			leben=Toolkit.getDefaultToolkit().getImage( filename );
			 filename = getClass().getResource(bf+"shield.png");
				schild=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"keyboard.png");
				keyboard=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"gamepad.png");
				gamepad=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"pads.png");
				padchooser=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"pause.png");
				pause=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"brollintro.jpg");
				brollintro=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"battlestory.jpg");
				storybattle=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"glitter.png");
				glitter=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"achblock.png");
				achblock[0]=Toolkit.getDefaultToolkit().getImage( filename );
				filename = getClass().getResource(bf+"achblock2.png");
				achblock[1]=Toolkit.getDefaultToolkit().getImage( filename );
			
			  filename = getClass().getResource(bf+"spacepod.gif");
				pod=Toolkit.getDefaultToolkit().getImage( filename );
				 filename = getClass().getResource(bf+"stars.gif");
					stars=Toolkit.getDefaultToolkit().getImage( filename );
					 filename = getClass().getResource(bf+"pluscharacter.png");
						pluscharacter=Toolkit.getDefaultToolkit().getImage( filename );
						 filename = getClass().getResource(bf+"subcharacter.png");
							subcharacter=Toolkit.getDefaultToolkit().getImage( filename );
							
			 filename = getClass().getResource(bf+"dbmouse0.png");
				mouse0=Toolkit.getDefaultToolkit().getImage( filename );
				 filename = getClass().getResource(bf+"logo.png");
					logo=Toolkit.getDefaultToolkit().getImage( filename );
					 filename = getClass().getResource(bf+"songokumenu.gif");
						songoku=Toolkit.getDefaultToolkit().getImage( filename );
						 filename = getClass().getResource(bf+"gohanmenu.gif");
							songohan=Toolkit.getDefaultToolkit().getImage( filename );
							
				 filename = getClass().getResource(bf+"dbmouse1.png");
					Toolkit.getDefaultToolkit().getImage( filename );
					 filename = getClass().getResource(bf+"nextrow.png");
						nextrow=Toolkit.getDefaultToolkit().getImage( filename );
						 filename = getClass().getResource(bf+"messagebox.png");
							messagebox=Toolkit.getDefaultToolkit().getImage( filename );
							 filename = getClass().getResource(bf+"messagepfeil.png");						
							messagepfeil=Toolkit.getDefaultToolkit().getImage( filename );
							
							 filename = getClass().getResource(bf+"duellpress.png");
								duellpress[1]=Toolkit.getDefaultToolkit().getImage( filename );
								duellpress[0]=frame.createImage(new FilteredImageSource(duellpress[1].getSource(), new CropImageFilter(0,0,50,40)));				
								duellpress[1]=frame.createImage(new FilteredImageSource(duellpress[1].getSource(), new CropImageFilter(50,0,50,40)));				
								
			 filename = getClass().getResource(bf+"ki.png");
			ki=Toolkit.getDefaultToolkit().getImage( filename );	
			 filename = getClass().getResource(bf+"intro.gif");
				introback=Toolkit.getDefaultToolkit().getImage( filename );	
				 filename = getClass().getResource(bf+"shenlong.gif");
					shenlong=Toolkit.getDefaultToolkit().getImage( filename );	
			 filename = getClass().getResource(bf+"title.png");
			title=Toolkit.getDefaultToolkit().getImage( filename );	
	 filename = getClass().getResource(bf+"cursor.png");
			cursor[2]=Toolkit.getDefaultToolkit().getImage( filename );	
			for(int i=0; i<3; i++)
			{
				cursor[i]=frame.createImage(new FilteredImageSource(cursor[2].getSource(), new CropImageFilter(0,i*74,781,74)));				
		
			}
			
			
			 filename = getClass().getResource(bf+"ko.png");		 
			ko=Toolkit.getDefaultToolkit().getImage( filename );	
			 filename = getClass().getResource(bf+"congratulations.png");		 
			 congratulations=Toolkit.getDefaultToolkit().getImage( filename );	
			 filename = getClass().getResource(bf+"failed.png");	
			 failed=Toolkit.getDefaultToolkit().getImage( filename );	
			 
			 filename = getClass().getResource(bf+"vs.png");				 
				vs=Toolkit.getDefaultToolkit().getImage( filename );	
				 filename = getClass().getResource(bf+"ring.png");				 
					ring=Toolkit.getDefaultToolkit().getImage( filename );	
					 filename = getClass().getResource(bf+"ring2.png");				 
						ring2=Toolkit.getDefaultToolkit().getImage( filename );	
					 filename = getClass().getResource(bf+"raumschiff.jpg");				 
						raumschiff=Toolkit.getDefaultToolkit().getImage( filename );	
				 filename = getClass().getResource(bf+"versusscreen.png");		 
					versusscreen=Toolkit.getDefaultToolkit().getImage( filename );	
					
					 filename = getClass().getResource(bf+"ready.png");		 
						hudready=Toolkit.getDefaultToolkit().getImage( filename );	
						 filename = getClass().getResource(bf+"fight.png");		 
							hudfight=Toolkit.getDefaultToolkit().getImage( filename );	
					
					filename = getClass().getResource(bf+"menuback.png");		 
				menuback=Toolkit.getDefaultToolkit().getImage( filename );
				 filename = getClass().getResource(bf+"alphabet.gif");	 
				Image[]font=new Image[41];
				font[40]=Toolkit.getDefaultToolkit().getImage( filename );		
					for(int i=0; i<41; i++)
					{
						font[i]=frame.createImage(new FilteredImageSource(font[40].getSource(), new CropImageFilter(i*40,0,40,40)));				
					}
			
				
				for(int m=0; m<2; m++)
				{
					 filename = getClass().getResource(bf+"world"+(m+1)+".png");
						minimap[m]=Toolkit.getDefaultToolkit().getImage( filename );
				
				 filename = getClass().getResource(bf+"tiles"+(m+1)+".png");	 
				maptiles[m][99]=Toolkit.getDefaultToolkit().getImage( filename );		
				for(int i=0; i<10; i++)
				{
					for(int h=0; h<10; h++)
					{
						maptiles[m][i*10+h]=frame.createImage(new FilteredImageSource(maptiles[m][99].getSource(), new CropImageFilter(h*32,i*32,32,32)));				
					}
				}	
				}
				
				 filename = getClass().getResource(bf+"mapchara.png");	 
					mapchars[47]=Toolkit.getDefaultToolkit().getImage( filename );		
					for(int i=0; i<4; i++)
					{
						for(int h=0; h<4; h++)
						{
							for(int j=0; j<3; j++)
							{
							mapchars[i*12+h*3+j]=frame.createImage(new FilteredImageSource(mapchars[47].getSource(), new CropImageFilter((j*24)+i*72,h*32,24,32)));				
						
							}
						}
					}	
					
					
					 filename = getClass().getResource(bf+"titlemenucursor.png");	 
						titlemenucursors[1]=Toolkit.getDefaultToolkit().getImage( filename );
						 filename = getClass().getResource(bf+"controllicons.png");	 
							controllicons[1]=Toolkit.getDefaultToolkit().getImage( filename );
						for(int i=0; i<2; i++)
						{
							titlemenucursors[i]=frame.createImage(new FilteredImageSource(titlemenucursors[1].getSource(), new CropImageFilter(0,i*55,472,55)));				
							controllicons[i]=frame.createImage(new FilteredImageSource(controllicons[1].getSource(), new CropImageFilter(i*40,0,40,40)));				
							
						}
					
					 filename = getClass().getResource(bf+"worldicons.png");	 
						worldicons[worldicons.length-1]=Toolkit.getDefaultToolkit().getImage( filename );		
						for(int i=0; i<worldicons.length; i++)
						{
							worldicons[i]=frame.createImage(new FilteredImageSource(worldicons[worldicons.length-1].getSource(), new CropImageFilter(i*50,0,50,50)));				
						}
						
						 filename = getClass().getResource(bf+"atticons.png");	 
							atticons[atticons.length-1]=Toolkit.getDefaultToolkit().getImage( filename );		
							for(int i=0; i<atticons.length; i++)
							{
								atticons[i]=frame.createImage(new FilteredImageSource(atticons[atticons.length-1].getSource(), new CropImageFilter(i*30,0,30,30)));				
							}
						
						 filename = getClass().getResource(bf+"itemicons.png");	 
							itemicons[itemicons.length-1]=Toolkit.getDefaultToolkit().getImage( filename );		
							for(int i=0; i<itemicons.length; i++)
							{
								itemicons[i]=frame.createImage(new FilteredImageSource(itemicons[itemicons.length-1].getSource(), new CropImageFilter(i*40,0,40,40)));				
							}
				
				 filename = getClass().getResource(bf+"iconfaces.png");	 
				faces[faces.length-1]=Toolkit.getDefaultToolkit().getImage( filename );		
				for(int i=0; i<faces.length; i++)
				{
					faces[i]=frame.createImage(new FilteredImageSource(faces[faces.length-1].getSource(), new CropImageFilter(i*50,0,50,50)));				
				}
				
				 filename = getClass().getResource(bf+"aura.png");	 
					aura[9]=Toolkit.getDefaultToolkit().getImage( filename );		
					for(int i=0; i<10; i++)
					{
						aura[i]=frame.createImage(new FilteredImageSource(aura[9].getSource(), new CropImageFilter(i*100,0,100,100)));				
					}
				
				 filename = getClass().getResource(bf+"selection.png");	 
					fighterselection[fighterselection.length-1]=Toolkit.getDefaultToolkit().getImage( filename );		
					for(int i=0; i<fighterselection.length; i++)
					{
						fighterselection[i]=frame.createImage(new FilteredImageSource(fighterselection[fighterselection.length-1].getSource(), new CropImageFilter(i*100,0,100,100)));				
					}	

					 filename = getClass().getResource(bf+"menuicons.png");
					 int l=menuicons.length;
						menuicons[l-1]=Toolkit.getDefaultToolkit().getImage( filename );		
						for(int i=0; i<l; i++)
						{
							menuicons[i]=frame.createImage(new FilteredImageSource(menuicons[l-1].getSource(), new CropImageFilter(i*50,0,50,50)));				
						}	
						
						 filename = getClass().getResource(bf+"dragonballs.png");
						dragonballs[6]=Toolkit.getDefaultToolkit().getImage( filename );		
							for(int i=0; i<7; i++)
							{
								dragonballs[i]=frame.createImage(new FilteredImageSource(dragonballs[6].getSource(), new CropImageFilter(i*50,0,50,50)));				
							}		
					
					 filename = getClass().getResource(bf+"clouds.gif");
					clouds[2]=Toolkit.getDefaultToolkit().getImage( filename );		
					for(int i=0; i<3; i++)
					{
						clouds[i]=frame.createImage(new FilteredImageSource(clouds[2].getSource(), new CropImageFilter(i*150,0,150,150)));				
					}		
					
					 filename = getClass().getResource(bf+"multisteer.png");
					 int s=multisteer.length;
						multisteer[s-1]=Toolkit.getDefaultToolkit().getImage( filename );		
						for(int i=0; i<s; i++)
						{
							multisteer[i]=frame.createImage(new FilteredImageSource(multisteer[s-1].getSource(), new CropImageFilter(i*20,0,20,20)));				
						}	
						
						 filename = getClass().getResource(bf+"active.png");
						
							active[1]=Toolkit.getDefaultToolkit().getImage( filename );		
							for(int i=0; i<2; i++)
							{
							active[i]=frame.createImage(new FilteredImageSource(active[1].getSource(), new CropImageFilter(i*20,0,20,20)));				
							}	

					 filename = getClass().getResource(bf+"chapters.png");
					chapterimages[4]=Toolkit.getDefaultToolkit().getImage( filename );		
					for(int i=0; i<5; i++)
					{
						chapterimages[i]=frame.createImage(new FilteredImageSource(chapterimages[4].getSource(), new CropImageFilter(i*100,0,100,100)));				
					}	
					
					for(int i=0; i<maps.length; i++)
					{
						 filename = getClass().getResource("/DBZ/Maps/map"+i+".gif");
						maps[i]=Toolkit.getDefaultToolkit().getImage( filename );
					}
					
					 filename = getClass().getResource(bf+"stages.png");
						stages[stages.length-1]=Toolkit.getDefaultToolkit().getImage( filename );		
						for(int i=0; i<stages.length; i++)
						{
							stages[i]=frame.createImage(new FilteredImageSource(stages[stages.length-1].getSource(), new CropImageFilter(i*200,0,200,120)));				
						}		
							
						 filename = getClass().getResource(bf+"blitzicons.png");
							blitzicon[1]=Toolkit.getDefaultToolkit().getImage( filename );	
							 filename = getClass().getResource(bf+"fighticons.png");
								fighticon[1]=Toolkit.getDefaultToolkit().getImage( filename );	
							for(int i=0; i<2; i++)
							{
								blitzicon[i]=frame.createImage(new FilteredImageSource(blitzicon[1].getSource(), new CropImageFilter(i*8,0,8,17)));				
							fighticon[i]=frame.createImage(new FilteredImageSource(fighticon[1].getSource(), new CropImageFilter(i*25,0,25,25)));				
							
							}	
							
							
							filename = getClass().getResource(bf+"helpGamepad.jpg");	
							help[0]=Toolkit.getDefaultToolkit().getImage( filename );	
							
							filename = getClass().getResource(bf+"brolllogo.png");	
							brolllogo=Toolkit.getDefaultToolkit().getImage( filename );	
							 
							
	}
	
	public Image[] bilderEffekte()
	{
		Image e[]=new Image[85];
		URL filename = getClass().getResource(bf+"effects.png");
		Image b=Toolkit.getDefaultToolkit().getImage( filename );	
		for(int g=0; g<4; g++)
		{
			for(int f=0; f<10; f++)
			{
				e[g*10+f]=frame.createImage(new FilteredImageSource(b.getSource(), new CropImageFilter(f*35,g*35,35,35)));
			}
		}	
		for(int g=0; g<4; g++)
		{
			for(int f=0; f<5; f++)
			{
				e[g*5+f+40]=frame.createImage(new FilteredImageSource(b.getSource(), new CropImageFilter(f*70,g*70+140,70,70)));
			}
		}	
		for(int g=0; g<5; g++)
		{		
				e[g+60]=frame.createImage(new FilteredImageSource(b.getSource(), new CropImageFilter(0,g*70+420,350,70)));
		}	
		for(int g=0; g<4; g++)
		{
			for(int f=0; f<5; f++)
			{
				e[g*5+f+65]=frame.createImage(new FilteredImageSource(b.getSource(), new CropImageFilter(f*70,g*70+770,70,70)));
			}
		}	
		return e;
	}
	
	public Image[] bilderExplosionen()
	{
		Image e[]=new Image[50];
		URL filename = getClass().getResource(bf+"bombs.png");
		Image b=Toolkit.getDefaultToolkit().getImage( filename );	
		
			for(int f=0; f<10; f++)
			{
				e[f]=frame.createImage(new FilteredImageSource(b.getSource(), new CropImageFilter(f*35,0,35,35)));
			}
		
		for(int g=0; g<8; g++)
		{
			for(int f=0; f<5; f++)
			{
				e[g*5+f+10]=frame.createImage(new FilteredImageSource(b.getSource(), new CropImageFilter(f*70,g*70+35,70,70)));
			}
		}	
		
		return e;
	}
	
	public Image[][] bilderKaempfer()
	{
		Image fighter[][] = new Image[fighteranz][30];
		for(int i=0; i<fighteranz; i++)
		{
			URL filename = getClass().getResource("/DBZ/FighterImages/fighter"+(i+1)+".png");
			fighter[i][29]= Toolkit.getDefaultToolkit().getImage( filename );	
		for(int g=0; g<3; g++)
		{
			for(int f=0; f<10; f++)
			{
				fighter[i][g*10+f]=frame.createImage(new FilteredImageSource(fighter[i][29].getSource(), new CropImageFilter(f*100,g*100,100,100)));
			}
		}	
		}
		
		URL filename = getClass().getResource("/DBZ/FighterImages/gokukaioken.png");
			specialfighter[0][29]= Toolkit.getDefaultToolkit().getImage( filename );	
		for(int g=0; g<3; g++)
		{
			for(int f=0; f<10; f++)
			{
				specialfighter[0][g*10+f]=frame.createImage(new FilteredImageSource(specialfighter[0][29].getSource(), new CropImageFilter(f*100,g*100,100,100)));
			}
		}	
	    
	
		
		return fighter;
	}
	
	
	
}
