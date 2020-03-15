package Tournament;

import java.awt.Color;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;
 

 
public class KoSystemPanel 
{
    int abstand,spielerGesamt, anzahlRunden=1, laenge,strichd=6;
    int xpos,ypos;
    int cfight,cround;
    int[] spieler;
    
    public KoSystemPanel(int anzahl,int abstand, int laenge, int xpos, int ypos) 
    {
        this.abstand=abstand;
        this.spielerGesamt=anzahl;
        this.laenge=laenge;
        this.xpos=xpos;
        this.ypos=ypos;
        int i=2;
        while(i<anzahl)
        {
            anzahlRunden++;
            i=i*2;
        }
        anzahlRunden++;
     
      
    }
    
  
    public void paint(Graphics g,int[] fighter, int[] round, int[] platz)
    {
    	
    	
    	
    	g.setColor(Color.BLACK);
        double spielerInRunde=spielerGesamt;
        int xStart=abstand+xpos,yStart=ypos,abstandInRunde=abstand;
        for(int i=1;i<anzahlRunden;i++)
        {
        	int[] f=new int[(int)spielerInRunde];
        	int[] s=new int[(int)spielerInRunde];;
     
          for(int h=0; h<f.length; h++){
        		f[h]=-1;
        	    
          }
        	for(int h=0; h<fighter.length; h++)
        	{
        		     		
        	    if(round[h]==i)
        	    {        	    	
        	    	f[platz[h]-1]=fighter[h];
        	       s[platz[h]-1]=spieler[h];
        	    }
        	}
        	
        	
        	
            paintRunde(g,xStart,yStart,i-1,(int)spielerInRunde,abstandInRunde,f,s);
            spielerInRunde=Math.ceil(spielerInRunde/2);
            abstandInRunde=(int) Math.pow(2,i)*abstand;
            yStart=yStart-laenge;
            xStart=(int) (Math.pow(2,i))*abstand+xpos;
        }
        g.fillRect(xStart-laenge/2+xpos,yStart-laenge, strichd,laenge);
        int bx=xStart-laenge/2+xpos-25+strichd/2;
        int by=yStart-laenge-20+strichd/2;
        
        int f=-1;
    	for(int h=0; h<fighter.length; h++)
    	{
    	    if(round[h]==anzahlRunden)
    	    {
    	    	f=fighter[h];
    	    }
    	}
    	if(f>-1){
    	       g.fillRect(bx-2,by-2,54,44);
               g.drawImage(GameImages.faces[f],bx,by,null);
        
    		
    	}
    }
    
    private void paintRunde(Graphics g, int xPos, int yPos,int ro, int anzahl, int sektionsAbstand, int[] fighter,int[] spieler)
    {
    	
        for(int i=0;i<anzahl;i++)
        {
        	  int bx=xPos-25+strichd/2;
              int by=yPos-20+strichd/2;
        	if(cround==ro)
        	{
        		if(i/2==cfight)
        		{
        			g.setColor(new Color(255,255,0));
        		g.fillRect(bx-5,by-5,60,50);
        		}
        	}
        	g.setColor(Color.BLACK);
            g.fillRect(xPos, yPos-laenge, strichd, laenge);
            
        	if(fighter[i]>-1)
        	{       
            g.fillRect(bx-2,by-2,54,44);
            g.drawImage(GameImages.faces[fighter[i]],bx,by,null);
            if(spieler[i]>0)
            {
            	
            	g.setColor(Color.white);
            	g.drawString("P"+spieler[i], bx, by+55);
            }
        	}
        	g.setColor(Color.BLACK);
            if(i%2==0)
            {
             
                g.fillRect(xPos, yPos-laenge, sektionsAbstand, strichd);
            }
            else
            {
             
                g.fillRect(xPos-sektionsAbstand, yPos-laenge,sektionsAbstand, strichd);
            }
     
 
            xPos+=2*sektionsAbstand;
        }
    }


	public void setActuellFight(int round, int cfight, int[] spieler) {
	this.cfight=cfight;
	this.cround=round;
	this.spieler=spieler;
	}
    
    

}