package Story;

import java.util.ArrayList;

import Fight.Fighter;
import Fight.FighterData;
import Menu.Menu;
import Stages.Stage;
import Stages.StageCity;
import Stages.StageMountain;
import Stages.StageKame;
import Stages.StageLabor;
import Stages.StageNamek;
import Stages.StageNamekDestroyed;
import Stages.StagePlain;
import Stages.StageTournament;

public class Story {

	private  Chapter chapter;
	private int progress;

	public Story(int pr)
	{		   
		progress=pr;
		loadChapter();	
	
	}
	


	public Chapter getChapter(){
		
		return chapter;
	}
	
	public void setProgress(int prog){
	 progress=prog;
	 
	}
	
	public String save()
	{
		return ""+progress;
	}
	
	public void refresh()
	{
		loadChapter();
		
		
		
	}
	
	
	private Fighter[] getFighter(int id1, int id2)
	{
		Fighter[] player=new Fighter[2];
	    player[0]=new Fighter();
	    player[1]=new Fighter();
	    player[0].setActivity(true);
	    player[0].setPos(200, 400);
	    player[1].setActivity(true);
	    player[1].setPos(800, 400);
	    player[0].init(id1,0,false);
	    player[1].init(id2,1,true);
	    return player;
	}
	

	private Fighter[] getFighter(int id1, int id2, int id3) {
		// TODO Auto-generated method stub
		Fighter[] player=new Fighter[3];
	    player[0]=new Fighter();
	    player[1]=new Fighter();
	    player[2]=new Fighter();
	    player[0].setActivity(true);
	    player[0].setPos(200, 400);
	    player[1].setActivity(true);
	    player[1].setPos(800, 400);
	    player[2].setActivity(true);
	    player[2].setPos(800, 200);
	    player[0].init(id1,0,false);
	    player[1].init(id2,1,true);
	    player[2].init(id3,2,true);
	    return player;
	}
	




	public int getProgress() {
		// TODO Auto-generated method stub
		return progress;
	}



	public void nextStory() {
		// TODO Auto-generated method stub
		progress++;
	}
	

	
	
	/*Attribute
	 * 0.id
	 * 1.leben 
	 * 2.maxleben
	 * 3.ki
	 * 4.def
	 * 5.speed
	 * 6.ki load
	 * 7.attack speed
	 */
	
	private void loadChapter() {
		
		
		/*
		 * Positionsberechnung:
		 *  x-faktor: 724/157 = 4.611
		 *  y-faktor: 497/108 = 4.60
		 *  => Faktor : 4.6
		 */
		
		
		chapter=new Chapter("");
		
		int spieler,gegner;
		Stage stage;
		StoryBattle battle;
		Dialog dialog;
		int bnr=0;
		Fighter[] fighter;
		Zuschauer z;
//##########################   TRAINING MIT KRILLIN  ###################################################################################
		gegner=FighterData.KRILLIN;
	   spieler=FighterData.GOKU;
	 stage=new StageKame();
	    fighter=getFighter(spieler,gegner);
	    fighter[1].setAttribut(4, 25);
		battle=new StoryBattle(fighter,stage,"Training mit Krillin ",bnr);	
		dialog=new Dialog();	
		dialog.addMessage("Hallo Goku! Wie wär's mit einem kleinen Training?",gegner,null);
		dialog.addMessage("Klar doch! Wir haben seit dem Tunrier nicht mehr /n gegeneinander gekämpft!", spieler,null);
		dialog.addMessage("Na dann, ich hoffe du weißt noch wie man kämpft!",gegner,null);
		dialog.addMessage("Das wirst du gleich sehen...", spieler,null);		
		battle.setDialog(dialog);	
		battle.setIcon(3);
		battle.setPos(724,497);
		battle.setGeld(3000);
		chapter.addFight(battle);
		bnr++; 	z=null;
	
		//##########################   Kampf gegen Piccolo  ###################################################################################
				gegner=FighterData.PICCOLO;
			   spieler=FighterData.GOKU;
			 stage=new StageTournament();
			    fighter=getFighter(spieler,gegner);
			    fighter[1].setLeben(1200);
				battle=new StoryBattle(fighter,stage,"Kampf gegen Piccolo ",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
				battle.setZuschauer(z);
				dialog=new Dialog();	
				dialog.addMessage("Huauaua keiner kann mich jetzt noch stoppen!",gegner,null);
				dialog.addMessage("Freu dich nicht zu früh Belzebub!", spieler,null);
				dialog.addMessage("Ich werde den Oberteufel endlich rächen..... /n und danach sind die anderen dran! ",gegner,null);
				dialog.addMessage("Ich habe keine Angst vor dir!", spieler,null);		
				battle.setDialog(dialog);	
				battle.setIcon(4);
				battle.setPos(428,584);
				battle.setGeld(7500);
				chapter.addFight(battle);
				bnr++;
				//##########################   Die Sayajins kommen  ###################################################################################
				gegner=FighterData.TENSHINHAN;
			   spieler=FighterData.NAPPA;
			 stage=new StageMountain();
			    fighter=getFighter(spieler,gegner,FighterData.YAMCHA);
			    
				battle=new StoryBattle(fighter,stage,"Die Sayajins kommen",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, true);
				z.addZuschauer(FighterData.PICCOLO, true);
				z.addZuschauer(FighterData.VEGETA, false);
				battle.setZuschauer(z);
				dialog=new Dialog();	
				dialog.addMessage("Verschwindet von unserem Planeten ihr Monster!",gegner,null);
				dialog.addMessage("Oh was für eine nete Begrüßung, nicht wahr Vegeta?", spieler,null);
				dialog.addMessage("Hier scheint es ja doch noch Krieger zu geben... /n ..aber ihre Kampfkraft ist trotzdem mickrig. ",FighterData.VEGETA,null);
				dialog.addMessage("Pass auf Tenshinhan, die Typen sehen stark aus!", FighterData.YAMCHA,null);	
				dialog.addMessage("Ich muss sie aufhalten Yamchu! /n Sonst werden wir alle sterben!",gegner,null);
				dialog.addMessage("Ihr könnt mich ruhig beide angreifen ihr Mikroben!", spieler,null);
				dialog.addMessage("Grr... ", FighterData.YAMCHA,null);
				dialog.addMessage("Los den schnappen wir uns! ",gegner,null);
				dialog.addMessage("Hahah dann kann der Spaß beginnen!", spieler,null);
				battle.setDialog(dialog);	
				battle.setIcon(5);
				battle.setPos(390,416);
				battle.setGeld(12000);
				chapter.addFight(battle);
				bnr++;			z=null;
				
				//##########################   Nappa  ###################################################################################
				gegner=FighterData.PICCOLO;
				   spieler=FighterData.NAPPA;
			 stage=new  StageMountain();
			   fighter=getFighter(spieler,gegner,FighterData.KRILLIN);
		
		
				battle=new StoryBattle(fighter,stage,"Nappa Unbesiegbar",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.VEGETA, false);
				battle.setZuschauer(z);
				dialog=new Dialog();	
			
				dialog.addMessage("So der nächste bitte!", spieler,null);
				dialog.addMessage("Grrr... er ist zu stark.",gegner,null);
				dialog.addMessage("Schon keine Motivation mehr? /n Und ich verrate euch was, Vegeta ist noch viel /n stärker als ich! Hahahaa!", spieler,null);
				dialog.addMessage("Warte nur bis Son Goku kommt!", FighterData.KRILLIN,null);
				dialog.addMessage("Nicht vordrängeln, du bist als nächster dran. /n Aber vorher der grüne Kerl!", spieler,null);
				dialog.addMessage("Halt deine große Klappe und versuchs doch!",gegner,null);
				dialog.addMessage("Hahah bettel ruhig um deinen Tod!", spieler,null);
				battle.setDialog(dialog);	
				battle.setIcon(5);
				battle.setPos(420,380);
				battle.setGeld(15000);
				chapter.addFight(battle);
				bnr++;		z=null;
				
				
				//##########################   Son Goku erscheint ###################################################################################
				gegner=FighterData.NAPPA;
			   spieler=FighterData.GOKU;
			 stage=new  StageMountain();
			    fighter=getFighter(spieler,gegner);
			    fighter[0].unsichtbar();
			    fighter[1].setLeben(2000);
				battle=new StoryBattle(fighter,stage,"Son Goku erscheint",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
				z.addZuschauer(FighterData.YAMCHA, false);
				z.addZuschauer(FighterData.VEGETA, true);
				battle.setZuschauer(z);
				dialog=new Dialog();	
				dialog.addMessage("Oh wer kommt den da?",gegner,null);
				dialog.addMessage("Es ist Son Goku!", FighterData.YAMCHA,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Arrgg.. beeil dich Son Goku....", FighterData.YAMCHA,null);
				
				dialog.addMessage("Sind das die Sayajins?", spieler,null);
				dialog.addMessage("Ja Son Goku wir haben keine Chance!", FighterData.YAMCHA,null);
				dialog.addMessage("Hmm.. er kommt mir so bekannt vor...",FighterData.VEGETA,null);
				dialog.addMessage("Hahah jetzt wirds erst richtig lustig!",gegner,null);
				dialog.addMessage("Was ihr meinen Freunden angetan habt...", spieler,null);
				dialog.addMessage("...das werdet ihr büßen!", spieler,null);
				dialog.addMessage("Arggggggghhh!", spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Vegeta! Wie hoch ist seine Kampfkraft?", gegner,new DialogAktion(0,0,15));
				dialog.addMessage("Sie steigt immer höher....! Sie ist....",FighterData.VEGETA,new DialogAktion(0,0,15));
				dialog.addMessage("ITS OVER 9000!!!!!!!!!!!!!!!",FighterData.VEGETA,new DialogAktion(1,2,21));
				battle.setDialog(dialog);	
				battle.setIcon(5);
				battle.setPos(420,426);
				battle.setGeld(20000);
				chapter.addFight(battle);
				bnr++;		z=null;
				
				//##########################   Showdown mit Vegeta  ###################################################################################
				gegner=FighterData.VEGETA;
			   spieler=FighterData.GOKU;
			   fighter=new Fighter[3];
				 for(int i=0; i<3; i++)
				 {
					 fighter[i]=new Fighter();
					 fighter[i].setActivity(true);
				 }				
				 fighter[0].setPos(200, 200);
				 fighter[2].setPos(400, 400);
				 fighter[1].setPos(800, 400);
			
				 fighter[0].init(spieler,0,false);
				 fighter[2].init(FighterData.NAPPA,2,false);
				 fighter[1].init(gegner,1,true);
				
				 ArrayList<Integer>	 te=new  ArrayList<Integer>();
					te.add(0); te.add(1); te.add(2); 			
					    fighter[1].setLeben(1600);	
					    fighter[2].setAttribut(1,10);
			 stage=new  StageMountain();
			    
	           fighter[2].setAttribut(1,10);
			    fighter[1].setLeben(1500);
				battle=new StoryBattle(fighter,stage,"Showdown mit Vegeta",bnr);
				battle.setTeams(te);
				dialog=new Dialog();	
				dialog.addMessage("Urghh... Vegeta.. hilf mir!",FighterData.NAPPA,null);
				dialog.addMessage("Du bist ein Versager....",gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Bitte!",FighterData.NAPPA,null);
				dialog.addMessage("Stirb!",gegner,new DialogAktion(1,0,18));
				dialog.addMessage("..wie kannst du sowas nur tun? /n Ihr seid Monster!", spieler,null);
				dialog.addMessage("Hahaha jetzt zeige ich dir meine Power!", gegner,null);
				dialog.addMessage("Hyaaaaaaaaaaaaaaaaaaaaaa!", gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Wie du willst! ", spieler,null);
				dialog.addMessage("Und los! Hahahahah", gegner,null);
				battle.setDialog(dialog);	
				battle.setIcon(5);
				
				battle.setPos(450,360);
				battle.setGeld(25000);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################   Willkommen auf Namek  ###################################################################################
				gegner=FighterData.GULDO;
			   spieler=FighterData.KRILLIN;
			 stage=new StageNamek();
			    fighter=getFighter(spieler,gegner);
			    fighter[1].setAttribut(4, 15);
			    fighter[1].setLeben(1200);		    
				battle=new StoryBattle(fighter,stage,"Willkommen auf Namek",bnr);	
				dialog=new Dialog();	
				dialog.addMessage("Wow wir sind endlich auf Namek!", spieler,null);
				dialog.addMessage("Oh wen haben wir denn da?", gegner,null);
				dialog.addMessage("Hallo Du! Mein Name ist Krillin und wir /n sind nach Namek gekommen um die Dragonballs /n zu finden.", spieler,null);
				dialog.addMessage("Ihr wollt also die Dragonballs haben? kikiki", gegner,null);
				dialog.addMessage("Ja, kannst du uns dabei helfen?", spieler,null);
				dialog.addMessage("Hohoho euch helfen...  /n das wird Meister Freezer gefallen!",gegner,null);
				dialog.addMessage("Freezer?", spieler,null);
				dialog.addMessage("Wenn ich mit dir fertig bin wirst du ihn noch früh /n genug kennenlernen! Und jetzt bleib schön still!", gegner,null);
				battle.setDialog(dialog);	
				battle.setIcon(9);			
				battle.setPos(580,300);
				battle.setGeld(10000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;	
				//##########################  Ein unerwarteter Besuch ###################################################################################
				gegner=FighterData.RECOOME;
			   spieler=FighterData.VEGETA;
			 stage=new StageNamek();
			    fighter=getFighter(spieler,gegner);
			   
			    fighter[1].setLeben(2200);		    
				battle=new StoryBattle(fighter,stage,"Ein unerwarteter Besuch",bnr);	
				dialog=new Dialog();	
				dialog.addMessage("Das ist also Namek.. /n Freezers Leute reden zu viel, die Dragonballs /n sind schon so gut wie mein.", spieler,null);
				dialog.addMessage("Hohoho wenn das nicht Vegeta ist!", gegner,null);
				dialog.addMessage("Recoome aus dem Ginyu Sonderkomando! /n Das ist Freezers Spezieleinheit! ", spieler,null);
				dialog.addMessage("Freezer weiß bescheid von deinem kleinen Ausflug.", gegner,null);
				dialog.addMessage("Grrr.. ", spieler,null);
				dialog.addMessage("Ich bin extra gekommen um deine witzigen Pläne /n zunichte zu machen.",gegner,null);
				dialog.addMessage("Na komm dann versuchs doch Großer!", spieler,null);
				dialog.addMessage("Hahah Recooooome.....", gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Jetzt wirds hart...", spieler,new DialogAktion(0,0,17));
				battle.setDialog(dialog);	
				battle.setIcon(9);			
				battle.setPos(450,350);
				battle.setGeld(30000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################  Das Ginyu Kommando ###################################################################################
				gegner=FighterData.VEGETA;
			   spieler=FighterData.BURTER;
			 stage=new StageNamek();
			  fighter=new Fighter[4];
			 for(int i=0; i<4; i++)
			 {
				 fighter[i]=new Fighter();
				 fighter[i].setActivity(true);
			 }				
			 fighter[0].setPos(200, 200);
			 fighter[1].setPos(800, 400);
			 fighter[2].setPos(800, 200);
			 fighter[3].setPos(200, 400);
			 fighter[0].init(spieler,0,false);
			 fighter[1].init(gegner,1,true);
			 fighter[2].init(FighterData.KRILLIN,2,true);
			 fighter[3].init(FighterData.RECOOME,3,false);
				 te=new  ArrayList<Integer>();
				te.add(0); te.add(1); te.add(1); te.add(0);
 
				  
				    fighter[1].setLeben(1000);
				
				    fighter[2].setLeben(750);
				   
				    fighter[3].setLeben(1500);
				    
				battle=new StoryBattle(fighter,stage,"Das Ginyu Kommando",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.GINYU, false);
				z.addZuschauer(FighterData.JEECE, false);
				
				battle.setZuschauer(z);
				battle.setTeams(te);
				dialog=new Dialog();	
				dialog.addMessage("Recoome und Guldo haben mir von euch erzählt...", FighterData.GINYU,null);
				dialog.addMessage("Hahaha deine Krieger sind Waschlappen!", gegner,null);
				dialog.addMessage("Ich hab extra für dich mit nur der halben Kraft /n gekmäpft! Du darfst doch auch mal gewinnen. ", FighterData.RECOOME,null);
				dialog.addMessage("Grrrr.. elendiger Schwätzer!  ", gegner,null);
				dialog.addMessage("Also gut Jungs ich würde sagen wir losen!", FighterData.GINYU,null);
				dialog.addMessage("Ja Super! Einer bekommt den kleinen und der andere /n den Zwiebelkopf!",spieler,null);
				dialog.addMessage("Also gut... Schere ...Stein...Papier!",FighterData.GINYU,null);
				dialog.addMessage("Juhu! Ich bin dran!",spieler,null);
				dialog.addMessage("Oh Vegeta du hast ganz schön Glück! /n Jetzt zeig ich dir meine wahre Power! ", FighterData.RECOOME,null);
				dialog.addMessage("Du Kleiner, wir müssen zusammen kämpfen /n sonst haben wir keine Chance!",gegner,null);
				dialog.addMessage("Hmm.. ja du hast recht die sind zu stark!",FighterData.KRILLIN,null);
				dialog.addMessage("Viel Spaß euch und übertreibts nicht!",FighterData.GINYU,null);
				dialog.addMessage("Danke Captain du bist echt Klasse! ",FighterData.RECOOME,null);
				dialog.addMessage("Auf die Plätze fertig los!",FighterData.BURTER,null);
				battle.setDialog(dialog);	
				battle.setIcon(7);			
				battle.setPos(200,450);
				battle.setGeld(30000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################  Son Goku wieder da! ###################################################################################
				gegner=FighterData.RECOOME;
			   spieler=FighterData.GOKU;
			 stage=new StageNamek();
			    fighter=getFighter(spieler,gegner,FighterData.BURTER);
			    fighter[0].unsichtbar();
				battle=new StoryBattle(fighter,stage,"Son Goku wieder da!",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
				z.addZuschauer(FighterData.VEGETA, false);
				z.addZuschauer(FighterData.GINYU, true);
				z.addZuschauer(FighterData.JEECE, true);
				battle.setZuschauer(z);
				dialog=new Dialog();	
				dialog.addMessage("Urgg... wir haben versagt...", FighterData.KRILLIN,null);
				dialog.addMessage("Sie haben doch die Wahrheit gesagt... /n Sie sind einfach zu stark... ", FighterData.VEGETA,null);
				dialog.addMessage("Schon den Mut verloren Vegeta? /n Jetzt mache ich dich für immer kalt!", gegner,null);
				dialog.addMessage("Son Goku!!!! /n wo bleibst du nur???", FighterData.KRILLIN,null);
				dialog.addMessage("Sagt ade zur schönen Welt! ", FighterData.BURTER,null);
				dialog.addMessage("Hallo Leute!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Son Goku!!! /n Du musst uns helfen wir können nicht gewinnen!", FighterData.KRILLIN,null);
				dialog.addMessage("Pff der schon wieder... ", FighterData.VEGETA,null);
				dialog.addMessage("Oh noch ein Kamerad zum sterben! /n Meister Freezer hat langsam genug von euch!", gegner,null);
				dialog.addMessage("Ich bin nicht gekommen um zu sterben sondern /n um meine Freunde zu retten! ", spieler,null);
				dialog.addMessage("Hahahah weißt du nicht wer wir sind? /n Mit deiner mickrigen Kampfkraft kannst du uns /n nichts anhaben!", FighterData.BURTER,null);
				dialog.addMessage("Wenn das so ist dann....", spieler,null);
				dialog.addMessage("Hyaaaaaaaaaaaaaaa!", spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Wwwwas! Das kann nicht sein!",gegner,null);
				dialog.addMessage("DDDDas ist irgendein Trick oder so! /n Ich bin doch der schnellste im Universum!",FighterData.BURTER,null);
				dialog.addMessage("Mach sie fertig Son Goku!", FighterData.KRILLIN,null);			
				battle.setDialog(dialog);	
				battle.setIcon(9);			
				battle.setPos(150,230);
				battle.setGeld(25000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################  Ginyu greift ein ###################################################################################
				gegner=FighterData.GOKU;
			   spieler=FighterData.GINYU;
			 stage=new StageNamek();
			  fighter=new Fighter[3];
				 for(int i=0; i<3; i++)
				 {
					 fighter[i]=new Fighter();
					 fighter[i].setActivity(true);
				 }				
				 fighter[0].setPos(200, 200);
				 fighter[1].setPos(200, 400);
				 fighter[2].setPos(800, 200);
			
				 fighter[0].init(spieler,0,false);
				 fighter[1].init(FighterData.JEECE,1,false);
				 fighter[2].init(gegner,2,true);
				
					 te=new  ArrayList<Integer>();
					te.add(0); te.add(0); te.add(1); 

					
					    fighter[2].setLeben(2000);	
					    fighter[0].setAttribut(1,100);
					 
			 
				battle=new StoryBattle(fighter,stage,"Ginyu greift ein",bnr);
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN,true);
				z.addZuschauer(FighterData.VEGETA, true);
		
				battle.setZuschauer(z);
				battle.setTeams(te);
				dialog=new Dialog();	
				dialog.addMessage("Er...hat sie einfach besiegt!!!",spieler,null);
				dialog.addMessage("Ihr solltet euren Gegner niemals unterschätzen!",gegner,null);
				dialog.addMessage("Du hast es geschafft Son Goku!",FighterData.KRILLIN,null);
				dialog.addMessage("Hmm.. Du scheinst echt stark zu sein. /n Wenn du willst darfst du meinem Team beitreten.",spieler,null);
				dialog.addMessage("Nein mit solchen Schurken will ich nichts zu tun haben!",gegner,null);
				dialog.addMessage("Er lehnt das Angebot des Captains ab!!!",FighterData.JEECE,null);
				dialog.addMessage("Kakarott ist ein friedlicher Sayajin./n Er wird euerer Truppe niemals beitreten!  /n Ich hab es selber kaum glauben können.",FighterData.VEGETA,null);
				dialog.addMessage("Schweig du Versager! /n Ich wusste garnicht dass Prinz Vegeta /n Hilfe von Unterklasse Kriegern braucht!",spieler,null);
				dialog.addMessage("Hahah hast du etwa Angst?",FighterData.VEGETA,null);
				dialog.addMessage("Captain nur noch wir sind übrig...",FighterData.JEECE,null);
				dialog.addMessage("Wir sin das Ginyu Sonderkomando! Ha!",FighterData.GINYU,null);
				dialog.addMessage("Grrr.. zu zweit sieht die Pose beknackt aus. /n Das werdet ihr mir büßen!",FighterData.GINYU,null);
				dialog.addMessage("Jetzt ist der Captain sauer, spricht euer /n letztes Gebot! Hahahaha! ",FighterData.JEECE,null);
				dialog.addMessage("Hyaaaaaaaaaa!!",FighterData.GINYU,new DialogAktion(0,0,15));
				dialog.addMessage("Seine Kampfkraft ist bei 100.000!!!",FighterData.JEECE,null);
				dialog.addMessage("Diese Stärke.. also sind die Gerüchte doch wahr! /n Ich glaube selbst du hast keine Chance /n gegen ihn Son Goku...",FighterData.VEGETA,null);
				dialog.addMessage("Hmm.. dann passt mal alle gut auf!",gegner,null);
				dialog.addMessage("Hyaaaaaaaaaaaaaa!!!",gegner,new DialogAktion(2,0,15));
				dialog.addMessage("Captain seine Kampfkraft steigt immer höher!",FighterData.JEECE,null);
				dialog.addMessage("Was? Wie hoch ist sie jetzt?",spieler,null);
				dialog.addMessage("Über 140.000 Captain!!!",FighterData.JEECE,null);
				dialog.addMessage("Hahah ihr habt verloren! /n Kakarott wird euch fertig machen!",FighterData.VEGETA,null);
				dialog.addMessage("Das glaubst auch nur du... /n Auf diesen Moment habe ich schon immer gewartet!",spieler,null);
				dialog.addMessage("Ich weiß was der Captain vorhat! /n Ihr seid schon so gut wie tot!",FighterData.JEECE,null);
				dialog.addMessage("Das hoffe ich doch, sonst wird der Kampf /n ziemlich eintönig!",gegner,null);
				dialog.addMessage("Viel Glück Goku!",FighterData.KRILLIN,null);
				dialog.addMessage("Was macht er da?",gegner,null);
				dialog.addMessage("Er hat sich selber verletzt!",FighterData.KRILLIN,null);
				dialog.addMessage("Ha jetzt ist Schluss mit lustig!",FighterData.JEECE,null);
				battle.setDialog(dialog);					
				battle.setIcon(7);			
				battle.setPos(180,250);
				battle.setGeld(15000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################  Im Körper des Feindes ###################################################################################
				gegner=FighterData.GOKU;
			   spieler=FighterData.VEGETA;
			   
			 stage=new StageNamek();
			 
			  fighter=new Fighter[3];
				 for(int i=0; i<3; i++)
				 {
					 fighter[i]=new Fighter();
					 fighter[i].setActivity(true);
				 }				
				 fighter[0].setPos(200, 200);
				 fighter[2].setPos(200, 400);
				 fighter[1].setPos(800, 200);
			
				 fighter[0].init(spieler,0,false);
				 fighter[2].init(FighterData.GINYU,2,false);
				 fighter[1].init(gegner,1,true);
				
					 te=new  ArrayList<Integer>();
					te.add(0); te.add(1); te.add(0); 			
					    fighter[1].setLeben(1600);	
					    fighter[2].setAttribut(1,10);
		
				battle=new StoryBattle(fighter,stage,"Im Körper des Feindes",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
			
		
				z.addZuschauer(FighterData.JEECE, true);
				battle.setZuschauer(z);
				battle.setTeams(te);
				dialog=new Dialog();
				dialog.addMessage("Klasse Son Goku hat ihn besiegt!",FighterData.KRILLIN,null);
				dialog.addMessage("Nein du Trottel...",spieler,null);	
				dialog.addMessage("Hahaha das ist echt ein Klasse Körper!",gegner,null);	
				dialog.addMessage("Urggh.. Ich kann mich kaum bewegen... /n Er hat seinen Körper absichtlich verletzt.",FighterData.GINYU,null);	
				dialog.addMessage("Vielen Dank für deine Energie! /n Mit diesem Körper bin ich unbesiegbar!",gegner,null);	
				dialog.addMessage("Was? Son Goku steckt jetzt in Ginyus Körper?",FighterData.KRILLIN,null);
				dialog.addMessage("Ja Krillin.. ich kann euch nicht mehr helfen... /n Urghhh...",FighterData.GINYU,null);	
				dialog.addMessage("Son Goku!!!",FighterData.KRILLIN,new DialogAktion(2,DialogAktion.KILLFIGHTER,0));
				dialog.addMessage("Hahaha jetzt mach ich euch fertig!",gegner,null);
				dialog.addMessage("Urggh... glaub ja nicht du kannst meine Energie /n richtig kontrollieren... ",FighterData.GINYU,null);
				dialog.addMessage("Jetzt bin ich wohl grefragt! /n Ich wollte schon immer Kakarott schlagen!",spieler,null);	
				dialog.addMessage("Komm doch her du Versager...!",gegner,null);
				battle.setDialog(dialog);	
				battle.setIcon(7);			
				battle.setPos(120,270);
				battle.setGeld(30000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################  Freezer reichts ###################################################################################
				gegner=FighterData.KRILLIN;
			   spieler=FighterData.FREEZER;
			 stage=new StageNamek();
			    fighter=getFighter(spieler,gegner,FighterData.VEGETA);
			    fighter[0].unsichtbar();
				battle=new StoryBattle(fighter,stage,"Freezer reichts!",bnr);	
			
				dialog=new Dialog();	
			   
				dialog.addMessage("Ha ich wusste doch dass ich stärker bin!",FighterData.VEGETA,null);
				dialog.addMessage("Du hast Gokus Körper übel zugerichtet. /n Zum Glück hat er seinen Körper wieder und /n Ginyu hüpft hier irgendwo als Frosch rum.",FighterData.KRILLIN,null);
				dialog.addMessage("Ja.. das war gerade noch rechtzeitig, /n sonst hätte er jetzt Vegetas Körper.",FighterData.GOKU,null);
				dialog.addMessage("Hmm Freezer wird sicher bald erfahren dass /n das Ginyu Komando verschwunden ist.",FighterData.VEGETA,null);
				dialog.addMessage("Besser wir bringen dich gleich in den /n Heilapparat Goku.",FighterData.KRILLIN,null);
				dialog.addMessage("Ja macht bitte schnell... ",FighterData.GOKU,null);
				dialog.addMessage("So das wäre geschafft. /n Ruh dich auch aus Kleiner wir bekommen sicher /n bald Besuch.",FighterData.VEGETA,null);
				dialog.addMessage("Ja wird wohl besser so sein...",FighterData.KRILLIN,null);
				dialog.addMessage("Da bist du also Vegeta!!!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Freezer!",FighterData.VEGETA,null);
				dialog.addMessage("Hast mich wohl nicht so früh erwartet... /n Das ganze Komando wurde ausgelöscht! /n Ich kann nicht glauben dass ihr dafür /n verwantwortlich seid...!",spieler,null);
				dialog.addMessage("Unterschätze mich nicht Freezer!",FighterData.VEGETA,null);
				dialog.addMessage("Oh hab ich dir schonmal erzählt wie hoch meine /n Kampfkraft ist?",spieler,null);
				dialog.addMessage("Bei über 1.000.000 und mit jeder Entwicklung /n verdoppelt sie sich. ",spieler,null);
				dialog.addMessage("Uhh... red keinen Unsinn!",FighterData.VEGETA,null);
				dialog.addMessage("Willst du etwa schon aufgeben? /n Wäre besser für dich ich machs auch schmerzlos.",spieler,null);
				dialog.addMessage("Wenn wir ihn zusammen angreifen können wir es /n schaffen! Schnell Kleiner!",FighterData.VEGETA,null);
				dialog.addMessage("Hahah dann zeig mal was ein Sayajin so kann!",spieler,null);
				battle.setDialog(dialog);	
				battle.setIcon(9);			
				battle.setPos(100,380);
				battle.setGeld(25000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//##########################  Retter Piccolo  ###################################################################################
				gegner=FighterData.FREEZER;
			   spieler=FighterData.PICCOLO;
			 stage=new StageNamek();
			    fighter=getFighter(spieler,gegner);
			    fighter[0].unsichtbar();
			    fighter[1].setLeben(1000);
				battle=new StoryBattle(fighter,stage,"Retter Piccolo",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
				z.addZuschauer(FighterData.VEGETA, false);
				battle.setZuschauer(z);
				dialog=new Dialog();	  
				dialog.addMessage("Du bist und bleibst ein Waschlappen Vegeta...",gegner,null);
				dialog.addMessage("Wie kann er so stark sein.../n Ich versteh das nicht..",FighterData.VEGETA,null);
				dialog.addMessage("Du ödest mich langsam an... /n Ich hätte gründlicher vorgehen müssen als ich euch /n alle auslöschte.",gegner,null);
				dialog.addMessage("Du wirst irgendwann dafür bezahlen!",FighterData.VEGETA,null);
				dialog.addMessage("Was sollen wir jetzt tun? /n Wir können ihm nichts ausrichten!",FighterData.KRILLIN,null);
				dialog.addMessage("Es dauert noch bis Son Goku geheilt ist. /n Solange müssen wir durchhalten!",FighterData.VEGETA,null);
				dialog.addMessage("Hallo Krillin!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Piccolo! Unser Wunsch hat funktioniert!",FighterData.KRILLIN,null);
				dialog.addMessage("Der Namekianer strahlt eine gewaltige Energie aus. /n Seit wann ist der so stark? ",FighterData.VEGETA,null);
				dialog.addMessage("Ich habe mich mit dem letzten Krieger fusioniert /n um Freezer zu schlagen und alle Namekianer /n zu rächen!",spieler,null);
				dialog.addMessage("Große Worte für einen Namekianer! Hahahah!",gegner,null);
				dialog.addMessage("Freezer ich werde dich schlagen!",spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Dann komm doch her Großmaul!",gegner,null);
				battle.setDialog(dialog);	
				battle.setIcon(9);			
				battle.setPos(80,420);
				battle.setGeld(35000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Super Sayajin? ###################################################################################
				gegner=FighterData.VEGETA;
			   spieler=FighterData.FREEZER;
			 stage=new StageNamek();
			    fighter=getFighter(spieler,gegner);
			    fighter[1].setLeben(1600);
				battle=new StoryBattle(fighter,stage,"Super Sayajin?",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.PICCOLO, true);
				z.addZuschauer(FighterData.KRILLIN, true);
				battle.setZuschauer(z);
				dialog=new Dialog();	  
				dialog.addMessage("Das war nicht schlecht für einen Namekianer... /n Sogar besser als Vegeta!",spieler,null);
				dialog.addMessage("Er ist einfach zu stark... /n Ich habe mich überschätzt es tut mir Leid.",FighterData.PICCOLO,null);
				dialog.addMessage("Son Goku wo bleibst du???",FighterData.KRILLIN,null);
				dialog.addMessage("Yahhhhh!!!!!",gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Oha da ist einer sauer!",spieler,null);
				dialog.addMessage("Immer höre ich nur Kakarott ... /n Sogar ein Namekianer ist jetzt stärker! /n Ich bin der Prinz der Sayajins das kann nicht sein!",gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Er ist wieder stärker geworden!",FighterData.KRILLIN,null);
				dialog.addMessage("Es hat keinen Zweck Vegeta du hast keine Chance!",spieler,null);
				dialog.addMessage("Haha Freezer... ich bin jetzt ein Super Sayajin!",gegner,null);
				dialog.addMessage("Was? Du bist ein Super Sayajin? /n Das kann nicht sein!",spieler,null);
				dialog.addMessage("Ich bin stärker wie nie zuvor und dein Untergang! /n Ich werde alle Sayajins rächen!",gegner,null);
				dialog.addMessage("Ich hoffe das ist ein schlechter Scherz. /n Du weißt ich mag diese Geschichte nicht.",spieler,null);
				dialog.addMessage("Ich sehe deine Furcht! Wie fühlt man sich wenn man /n von einer Geschichte besiegt wird?",gegner,null);
				dialog.addMessage("Jetzt reichts! Ich stopf dir dein freches Mundwerk! /n Ich vollende nun was ich damals angefangen habe! /n Stirb!!!!",spieler,null);
				
				battle.setDialog(dialog);	
				battle.setIcon(10);			
				battle.setPos(150,400);
				battle.setGeld(30000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Vegetas Opfer ###################################################################################
				gegner=FighterData.FREEZER;
			   spieler=FighterData.GOKU;
			 stage=new StageNamek();
			    
			 fighter=new Fighter[3];
			 for(int i=0; i<3; i++)
			 {
				 fighter[i]=new Fighter();
				 fighter[i].setActivity(true);
			 }				
			 fighter[0].setPos(200, 200);
			 fighter[2].setPos(200, 400);
			 fighter[1].setPos(800, 420);
			 fighter[0].unsichtbar();
		
			 fighter[0].init(spieler,0,false);
			 fighter[2].init(FighterData.VEGETA,2,false);
			 fighter[1].init(gegner,1,true);
			 
			 
			
				 te=new  ArrayList<Integer>();
				te.add(0); te.add(1); te.add(0); 	
				
				    fighter[1].setLeben(1600);	
				    
				    fighter[2].setAttribut(1,10);
			 
			 
				battle=new StoryBattle(fighter,stage,"Vegetas Opfer",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
		
				battle.setZuschauer(z);
				
				battle.setTeams(te);
				dialog=new Dialog();	  
				dialog.addMessage("So Vegeta ich hoffe du hast es nun verstanden.",gegner,null);		
				dialog.addMessage("Das kann nicht sein! /n Ich muss ihn doch besiegen!",FighterData.VEGETA,null);
				dialog.addMessage("Ich habe es langsam satt immer gegen Versager /n zu kämpfen. Ihr nervt mich gewaltig!",gegner,null);
				dialog.addMessage("Son Goku beeil dich! Freezer wird sauer!",FighterData.KRILLIN,null);
				dialog.addMessage("Was wollt ihr immer mit diesem Son Goku?",gegner,null);	
				dialog.addMessage("Das tat gut. Ich bin bereit Leute!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));	
				dialog.addMessage("Son Goku du bist wieder da!",FighterData.KRILLIN,null);
				dialog.addMessage("Wer ist das schon wieder? /n Wo kommen die nur alle her?",gegner,null);
				dialog.addMessage("Das ist Kakarott! /n Er wird dich besiegen und uns rächen!",FighterData.VEGETA,null);
				dialog.addMessage("Ich mag so Leute nicht die denken sie /n können mich besiegen!",gegner,null);
				dialog.addMessage("Hast du Vegeta und Krillin so zugerichtet?",spieler,null);	
				dialog.addMessage("Ja klar, und der Namekianer da drüben geht auch /n auf mein Konto!",gegner,null);
				dialog.addMessage("Grrr.. das wirst du bezahlen!",spieler,null);
				dialog.addMessage("Haha Freezer das ist dein Ende! /n Son Goku ist ein Super Sayajin geworden!",FighterData.VEGETA,null);
				dialog.addMessage("Wirst du endlich aufhören mit deiner Geschichte!",gegner,null);
				dialog.addMessage("Du wirst verlieren Freezer er wird /n dich besiegen!",FighterData.VEGETA,null);
				dialog.addMessage("Wenn ich was noch mehr hasse als Versager... /n dann sind es Schwätzer!",gegner,null);
				dialog.addMessage("Haaaaa!",gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Das hilft dir jetzt auch nicht mehr! Hahaha!",FighterData.VEGETA,null);
				dialog.addMessage("Stirb!",gegner,new DialogAktion(1,0,19));
				dialog.addMessage("Vegeta!!!!",spieler,null);
				dialog.addMessage("Arggg... Kakarott.../n ...ich muss dir noch was über deine Herkunft erzählen... /n Du stammst vom Planeten Vegeta so wie alle Sayajins..",FighterData.VEGETA,null);
				dialog.addMessage("Hört der niemals auf zu quaseln?",gegner,null);
				dialog.addMessage("Unsere Heimat wurde nicht von einem Asteroiden zerstört... /n sondern von Freezer!",FighterData.VEGETA,null);
				dialog.addMessage("Oh wer hätte das gedacht...",gegner,null);
				dialog.addMessage("Bitte verpsrich mir dass du alle Sayajins rächst.../n und Freezer besiegst!",FighterData.VEGETA,null);
				dialog.addMessage("Vegeta.... /n Das ist das erste mal dass du mich etwas bittest. /n Du hast zwar viele schreckliche Dinge getan.. /n aber du warts ein stolzer Krieger.",spieler,null);
				dialog.addMessage("Bitte geb mir etwas von deinem Stolz /n Damit ich unser Volk rächen kann!",spieler,null);
				dialog.addMessage("Mir kommen gleich die tränen!",gegner,null);
				dialog.addMessage("Vegeta ist tot...",FighterData.KRILLIN,null);
				dialog.addMessage("Freeezer!!!!!!!!!!!!!!",spieler,new DialogAktion(0,0,15));
				
				battle.setDialog(dialog);	
				battle.setIcon(8);			
				battle.setPos(400,450);
				battle.setGeld(35000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Super Sayajin Son Goku ###################################################################################
				gegner=FighterData.FREEZER;
			   spieler=FighterData.GOKU;
			 stage=new StageNamekDestroyed();
			    
			 fighter=new Fighter[3];
			 for(int i=0; i<3; i++)
			 {
				 fighter[i]=new Fighter();
				 fighter[i].setActivity(true);
			 }				
			 fighter[0].setPos(200, 200);
			 fighter[2].setPos(200, 400);
			 fighter[1].setPos(800, 420);
		
			 fighter[0].init(spieler,0,false);
			 fighter[2].init(FighterData.KRILLIN,2,false);
			 fighter[1].init(gegner,1,true);
			 fighter[1].unsichtbar();
			 
			
				 te=new  ArrayList<Integer>();
				te.add(0); te.add(1); te.add(0); 	
				
				    fighter[1].setLeben(1700);	
				    fighter[1].setAttribut(1, 1000);
				    
				    fighter[2].setAttribut(1,10);
			 
			 
				battle=new StoryBattle(fighter,stage,"Super Sayajin Son Goku",bnr);	
				battle.setTeams(te);
				dialog=new Dialog();	  
				dialog.addMessage("Du hast Freezer besiegt! /n Die Genkidama hat ihn vernichtet!",FighterData.KRILLIN,null);		
				dialog.addMessage("Man bin ich kaputt... /n Endlich ist es vorbei.",spieler,null);		
				dialog.addMessage("Ddddda ...o...b..e..n...!",FighterData.KRILLIN,null);		
				dialog.addMessage("Was hast du Krillin?",spieler,null);	
				dialog.addMessage("Hahaha habt ihr geglaubt ich wäre schon besiegt?",gegner,new DialogAktion(1,DialogAktion.EINFLIEGEN,0));	
				dialog.addMessage("Freezer!!!",spieler,null);
				dialog.addMessage("Jetzt ist Schluss mit lustig! /n Ich werde einen nach dem anderen umlegen!",gegner,null);	
				dialog.addMessage("Ich bin total erschöpft...",FighterData.GOKU,null);
				dialog.addMessage("Mit dir fang ich an!",gegner,new DialogAktion(1,0,15));	
				dialog.addMessage("Son Goku Hilfe!!!!!!!!",FighterData.KRILLIN,null);
				dialog.addMessage("Stirb!",gegner,new DialogAktion(1,0,18));				
				dialog.addMessage("Krillin!!!!!!!!!!!!!",spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Hahaha einer weniger!",gegner,null);	
				dialog.addMessage("Er war mein bester Freund und kann nicht mehr /n wiedererweckt werden!",spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Freezer dafür wirst du bezahlen!!!!!",spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Yahhhhhhhhhhhhhh!!!!",spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Was ist jetzt los?? Woher hat er nur die ganze Energie!",gegner,null);	
				dialog.addMessage("Yahhhhhhhhhhhhhh!!!!",spieler,new DialogAktion(0,0,15));
				dialog.addMessage("Und es wird immer mehr! Was geht hier vor sich???",gegner,null);
				spieler=FighterData.SSJGOKU;
				dialog.addMessage("Yahhhhhhhhhhhhhh!!!!",spieler,new DialogAktion(0,DialogAktion.CHANGEID,FighterData.SSJGOKU));
				dialog.addMessage("Wwwwer bist du???",gegner,null);
			
				dialog.addMessage("Ich bin der Super Sayajin Son Goku und werde /n dich ein für allemal vernichten!",spieler,null);
				battle.setDialog(dialog);	
				battle.setIcon(7);			
				battle.setPos(460,470);
				battle.setGeld(25000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Freezers letzter Trumpf ###################################################################################
				gegner=FighterData.FREEZER;
			   spieler=FighterData.SSJGOKU;
			 stage=new StageNamekDestroyed();
			    fighter=getFighter(spieler,gegner);
			    fighter[0].setAttribut(1, 400);
			    fighter[1].setAttribut(1, 400);
				battle=new StoryBattle(fighter,stage,"Freezers letzter Trumpf",bnr);	
				dialog=new Dialog();	  
				dialog.addMessage("Gib auf Freezer. /n Du hast verloren!",spieler,null);
				dialog.addMessage("Nein das kann nicht sein! /n Ich bin der Stärkste im ganzen Universum!",gegner,null);
				dialog.addMessage("Wie kann mich so ein Knilch besiegn!",gegner,null);
				dialog.addMessage("Dein Spiel ist aus! ",spieler,null);
				dialog.addMessage("Nein! ",gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Pump so viel du willst es wird dir nichts nützen. /n Ich bin stärker als du, akzeptier es. ",spieler,null);
				dialog.addMessage("Niemals ich bin der Stärkste!!! ",gegner,new DialogAktion(1,0,15));
				gegner=FighterData.FREEZER100;
				dialog.addMessage("Yahhhhhh!!!",gegner,new DialogAktion(1,DialogAktion.CHANGEID,FighterData.FREEZER100));
			
				dialog.addMessage("Haha nun hast du es mit 100% meiner Kraft zu tun!",gegner,null);
				dialog.addMessage("Kraft allein nützt dir nichts! /n Ich bin schneller und immer noch stärker als du!",spieler,null);
				dialog.addMessage("Haha das werden wir ja sehen!",gegner,new DialogAktion(1,0,15));
				dialog.addMessage("Shizo Scheiben!",gegner,new DialogAktion(1,0,19));
				dialog.addMessage("Du willst einfach nichts lernen!",spieler,null);
				battle.setDialog(dialog);	
				battle.setIcon(9);			
				battle.setPos(550,370);
				battle.setGeld(20000);
				battle.setDifferentMap(1);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Der Junge aus der Zukunft ###################################################################################
				gegner=FighterData.TRUNKS;
			   spieler=FighterData.GOKU;
			 stage=new StagePlain();
			    fighter=getFighter(spieler,gegner);
			  
				battle=new StoryBattle(fighter,stage,"Der Junge aus der Zukunft",bnr);	
				z=new Zuschauer();
				z.addZuschauer(FighterData.KRILLIN, false);
				z.addZuschauer(FighterData.VEGETA, false);
				z.addZuschauer(FighterData.YAMCHA, false);
				z.addZuschauer(FighterData.TENSHINHAN, false);
				z.addZuschauer(FighterData.PICCOLO, false);
				battle.setZuschauer(z);
				dialog=new Dialog();	  
				dialog.addMessage("Wow hast du gerade Mecha Freezer besiegt?",spieler,null);
				dialog.addMessage("Ja das war sein letzter Versuch sich zu rächen.",gegner,null);
				dialog.addMessage("Wer bist du überhaupt? /n Und warum kannst du dich auch in einen /n Super Sayajin verwandeln?",spieler,null);
				dialog.addMessage("Du wirst mir sicher kaum glauben können... /n Ich bin Trunks und komme aus der Zukunft. /n Vegeta ist mein Vater...",gegner,null);
				dialog.addMessage("WAS VEGETA?!?",spieler,null);
				dialog.addMessage("Bitte nicht so laut! /n Ich will nicht dass er es weiß.",gegner,null);
				dialog.addMessage("Das wird schwierieg.",spieler,null);
				dialog.addMessage("Bulma, meine Mutter hat in der Zukunft eine /n Zeitmaschine gebaut.  Mit dieser bin ich in die /n Vergangenheit geflogen  um euch zu warnen.",gegner,null);
				dialog.addMessage("Du hast die Gefahr gerade doch beseitigt?",spieler,null);
				dialog.addMessage("Nein das war nur ein Witz gegen dass /n was euch bevor steht!",gegner,null);
				dialog.addMessage("Es werden Cyborgs kommen mit grenzenloser Energie /n stärker als ein Super Sayajin!",gegner,null);
				dialog.addMessage("Und das ist noch nicht alles du wirst schwer krank. /n Ich habe extra eine Medizin mitgebracht, /n in meiner Zeit bist du schon gestorben...",gegner,null);
				dialog.addMessage("Das klingt echt nicht gut!",spieler,null);
				dialog.addMessage("Ja deshalb bin ich gekommen um euch zu helfen. /n Aber vorher möchte ich gerne sehen was drauf hast.",gegner,null);
				dialog.addMessage("Kannst du dich auch verwandeln?",gegner,null);
				dialog.addMessage("Ja genau so wie du! Sieh her..",spieler,null);
				spieler=FighterData.SSJGOKU;
				dialog.addMessage("Yahhhhhhh!",spieler,new DialogAktion(0,DialogAktion.CHANGEID,spieler));
				dialog.addMessage("Dann bitte ich um einen kurzen Testkampf!",gegner,null);
				dialog.addMessage("Kein Problem!",spieler,null);
				battle.setDialog(dialog);	
				battle.setIcon(6);			
				battle.setPos(400,262);
				battle.setGeld(10000);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Das Geheime Labor ###################################################################################
				gegner=FighterData.DRGERO;
			   spieler=FighterData.TRUNKS;
			 stage=new StageLabor();
			    fighter=getFighter(spieler,gegner);
			  fighter[1].setLeben(1000);
			  fighter[0].unsichtbar();
				battle=new StoryBattle(fighter,stage,"Das Geheime Labor",bnr);	
			    
				dialog=new Dialog();	  
				dialog.addMessage("Endlich ist es soweit! /n Die Cyborgs sind gleich fertig!",gegner,null);
				dialog.addMessage("Hmm? Woher kam das Geräusch?",gegner,null);
				dialog.addMessage("Hab ich dich gefunden!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Oha! Wer bist denn du? /n Und wie hast du mein Labor gefunden?",gegner,null);
				dialog.addMessage("Ich bin gekommen um deine finsteren Pläne zu stoppen!",spieler,null);
				dialog.addMessage("Hahaha da bist du leider schon zu spät./n Die Cyborgs werden jeden Moment auferstehen!",gegner,null);
				dialog.addMessage("Grr.. Dann zählt wohl jede Sekunde!",spieler,null);
				
				battle.setDialog(dialog);	
				battle.setIcon(15);			
				battle.setPos(510,100);
				battle.setGeld(20000);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Die Cyborgs ###################################################################################
				gegner=FighterData.SSJVEGETA;
			   spieler=FighterData.C18;
			 stage=new StagePlain();
			   
			 fighter=new Fighter[4];
			 for(int i=0; i<4; i++)
			 {
				 fighter[i]=new Fighter();
				 fighter[i].setActivity(true);
			 }				
			 fighter[0].setPos(200, 200);
			 fighter[2].setPos(200, 400);
			 fighter[1].setPos(800, 400);
			 fighter[3].setPos(800, 200);
			 fighter[0].unsichtbar();
			 fighter[2].unsichtbar();
			 
			 
			 fighter[0].init(spieler,0,false);
			 fighter[2].init(FighterData.C17,2,false);
			 fighter[1].init(gegner,1,true);
			 fighter[3].init(FighterData.PICCOLO,3,true);
			 fighter[1].setLeben(1300);
			 fighter[3].setLeben(1200);
			 
			
				 te=new  ArrayList<Integer>();
				te.add(0); te.add(1); te.add(0); 	te.add(1);
					
				 
			 
			 
				battle=new StoryBattle(fighter,stage,"Die Cyborgs",bnr);	
		
				z=new Zuschauer();
				z.addZuschauer(FighterData.TRUNKS, true);
		
				battle.setZuschauer(z);
			
			    battle.setTeams(te);
				dialog=new Dialog();	  
				dialog.addMessage("Es ist zu spät die Cyborgs sind befreit!",FighterData.TRUNKS,null);
				dialog.addMessage("Kein Problem mit denen werde ich locker fertig!",gegner,null);
				dialog.addMessage("Du hast keine Ahnung wie stark sie sind!",FighterData.TRUNKS,null);
				dialog.addMessage("Hallo!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Hi!",FighterData.C17,new DialogAktion(2,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Das sind C18 und C17! /n Sie haben euch alle getötet in meiner Zeit!",FighterData.TRUNKS,null);
				dialog.addMessage("Die schnappen wir uns!",FighterData.PICCOLO,null);
				
				battle.setDialog(dialog);	
				battle.setIcon(6);			
				battle.setPos(625,138);
				battle.setGeld(25000);
				chapter.addFight(battle);
				bnr++;		z=null;
				//########################## Der grüne Tod ###################################################################################
				gegner=FighterData.PICCOLO;
			    spieler=FighterData.IMPERFECTCELL;
     			stage=new StageCity();
			    fighter=getFighter(spieler,gegner);
			    fighter[1].setLeben(1400);
			    fighter[0].unsichtbar();
			    fighter[1].unsichtbar();
				battle=new StoryBattle(fighter,stage,"Der grüne Tod",bnr);				    
				dialog=new Dialog();	  
				dialog.addMessage("Ah hier gibt es viel zum absorbieren!",spieler,null);
				dialog.addMessage("Mit jedem absorbierten Mensch werde ich mächtiger!",spieler,new DialogAktion(0,DialogAktion.EINFLIEGEN,0));		
				dialog.addMessage("Das muss Cell sein!",gegner,new DialogAktion(1,DialogAktion.EINFLIEGEN,0));
				dialog.addMessage("Oha wer bist denn du?",spieler,null);
				dialog.addMessage("Du bist doch das Laborprojekt aus der Zukunft /n von diesem verrückten Professor!",gegner,null);
				dialog.addMessage("Ja ich bin Cell und bald werde ich perfekt sein!",spieler,null);
				dialog.addMessage("Du kommst mir gerade recht zum absorbieren!",spieler,null);  			
				battle.setDialog(dialog);	
				battle.setIcon(14);			
				battle.setPos(220,220);
				battle.setGeld(20000);
				chapter.addFight(battle);
				bnr++;		z=null;
				
	}



	
	

	
}
