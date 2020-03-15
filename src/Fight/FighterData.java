package Fight;

import java.util.ArrayList;

import Battle.Attack;
import Battle.Battle;
import DBZ.Main;



public class FighterData {

	private int[][] power=new int[Main.fighteranz][6];
	private static int[] dform=new int[Main.fighteranz];
	private int[] maxpower=new int[6];
	
	public static int SHIELD=200;
	public static final float KOTIME = 150;
	
	
	public FighterData()
	{
		
		setAttributPower();
		setMaxPower();
		
		transformList();
	}
	

	private void transformList() {
		dform[GOKU]=SSJGOKU;
		dform[SSJGOKU]=SSJ2GOKU;
		dform[SSJ2GOKU]=SSJ3GOKU;
		dform[SSJ3GOKU]=SSJ4GOKU;
	    dform[SSJ4GOKU]=GOKU;
	    
		dform[VEGETA]=SSJVEGETA;
		dform[SSJVEGETA]=SSJ2VEGETA;
		dform[SSJ2VEGETA]=SSJ4VEGETA;
		dform[SSJ4VEGETA]=VEGETA;
		
		dform[GOHANKID]=TEENGOHAN;
		dform[TEENGOHAN]=GOHANSSJ2;
		dform[GOHANSSJ2]=GOHANKID;
		
		dform[FREEZER]=FREEZER100;
		dform[IMPERFECTCELL]=SCNDCELL;
		dform[SCNDCELL]=CELL;
		dform[CELL]=CELLSUPER;
		dform[FATBOO]=BOO;
		dform[BOO]=BOOTENKS;
		dform[BOOTENKS]=BOOHAN;
		dform[BOOHAN]=KIDBOO;
		
		dform[BROLY]=SSJ4BROLY;
		dform[SSJ4BROLY]=BROLY;
		
		dform[SSJGOTEN]=GOTENKS;
		dform[YOUNGTRUNKS]=GOTENKS;
		dform[GOTENKS]=SSJGOTENKS;
		dform[SSJGOTENKS]=SSJ3GOTENKS;
		
		dform[TRUNKSFUTURE]=TRUNKS;
		dform[C13]=SUPERC13;
		dform[GOHAN]=SSJSONGOHAN;
		
	}
	public final static int RANDOM=9999;
	public final static int VEGETA=0,SSJ2VEGETA=1,GOKU=2,SSJGOKU=3,TEENGOHAN=4,MYSTIGGOHAN=5,SSJ3GOTENKS=6;
	public final static int TRUNKS=7,CELL=8,BOO=9,FREEZER=10,PICCOLO=11,BROLY=12,C18=13,KIDBOO=14;
	public final static int C16=15,IMPERFECTCELL=16,FATBOO=17,FREEZER100=18,SSJSONGOHAN=19,SSJGOTEN=20,YOUNGTRUNKS=21;
	public final static int VEGETO=22,SCNDCELL=23,C17=24,NAPPA=25,BABYVEGETA=26,GINYU=27,TENSHINHAN=28,DRGERO=29; 
	public final static int SSJ3GOKU=30,SSJ4GOKU=31,KRILLIN=32,BURTER=33,SQUID=34,JEECE=35,SSJVEGETA=36,YAMCHA=37;
	public final static int RECOOME=38,UUB=39,BARDOCK=40,GULDO=41,CELLJR=42,SSJ4VEGETA=43,SSJ4BROLY=44,HERCULE=45;
	public final static int JANEMBA=46,TAPION=47,KAI=48,SSJ2GOKU=49,CELLSUPER=50,C13=51,PUIPUI=52,GOHANSSJ2=53;
	public final static int DABURA=54,GOKUSSJ5=55,COOLER=56,GOHANKID=57,BOOTENKS=58,BOOHAN=59,TRUNKSFUTURE=60;
	public final static int SUPERC13=61,GOTENKS=62,SSJGOTENKS=63,GOHAN=64,METALCOOLER=65;
	
	public final static int GOKUKAIOKEN=99;
	
	public static String getName(int id)
	{
		String s="";
		switch(id)
		{
		case RANDOM: s="Random"; break;
		case 0: s="Vegeta"; break;
		case 1: s="SSj2 Vegeta"; break;
		case 2: s="Goku"; break;
		case 3: s="SSj Goku"; break;
		case 4: s="Teen Gohan SSj"; break;
		case 5: s="Mystic Gohan"; break;
		case 6: s="SSj3 Gotenks"; break;
		case 7: s="SSj Trunks"; break;
		case 8: s="Cell"; break;
		case 9: s="Super Buu"; break;
		case 10: s="Frieza"; break;
		case 11: s="Piccolo"; break;
		case 12: s="Broly"; break;
		case 13: s="C-18"; break;
		case 14: s="Kid Buu"; break;
		case 15: s="C-16"; break;
		case 16: s="Imperfect Cell"; break;
		case 17: s="Majin Buu"; break;
		case 18: s="Freezer 100%"; break;
		case 19: s="Son Gohan SSj"; break;
		case 20: s="Goten SSj"; break;
		case 21: s="Trunks SSj"; break;
		case 22: s="Vegito"; break;
		case 23: s="2nd Cell"; break;
		case 24: s="C-17"; break;
		case 25: s="Nappa"; break;
		case 26: s="Baby Vegeta"; break;
		case 27: s="Ginyu"; break;
		case 28: s="Tien"; break;
		case 29: s="Dr.Gero"; break;
		case 30: s="SSj3 Goku"; break;
		case 31: s="SSj4 Goku"; break;
		case 32: s="Krillin"; break;
		case 33: s="Burter"; break;
		case 34: s="Squidward"; break;
		case 35: s="Jeice"; break;
		case 36: s="SSj Vegeta"; break;
		case 37: s="Yamcha"; break;
		case 38: s="Recoome"; break;
		case 39: s="Uub"; break;
		case 40: s="Bardock"; break;
		case 41: s="Guldo"; break;
		case 42: s="CellJR"; break;
		case 43: s="SSj4 Vegeta"; break;
		case 44: s="SSj4 Broly"; break;
		case 45: s="Hercule"; break;
		case 46: s="Janemba"; break;
		case 47: s="Tapion"; break;
		case 48: s="Supreme Kai"; break;
		case 49: s="SSj2 Goku"; break;
		case 50: s="Super Cell"; break;
		case 51: s="C-13"; break;
		case 52: s="PuiPui"; break;
		case 53: s="Teen Gohan SSj2"; break;
		case 54: s="Dabura"; break;
		case 55: s="SSj5 Goku"; break;
		case 56: s="Cooler"; break;
		case 57: s="Teen Gohan"; break;
		case 58: s="Buu-Tenks"; break;
		case 59: s="Buu-Han"; break;
		case 60: s="Trunks"; break;
		case 61: s="Super C-13"; break;
		case 62: s="Gotenks"; break;
		case 63: s="SSj Gotenks"; break;
		case 64: s="Son Gohan"; break;
		case 65: s="Metal Cooler"; break;
		case GOKUKAIOKEN: s="Goku Kaioken"; break;
		}
		return s;
	}
	
	public static boolean kaufbar(int nr)
	{
		boolean b=true;
		switch(nr)
		{
		case FighterData.SQUID: b=false;
		case FighterData.HERCULE: b=false;
		case FighterData.SSJ4BROLY: b=false;
		case FighterData.GOKUSSJ5: b=false;
		}
		
		return b;
	}
	
	public static int getPrice(int nr)
	{
		int p=0;
		switch(nr)
		{
		case 0: p=0;  break;
		case 1: p=61000;  break;
		case 2: p=0;  break;
		case 3: p=55000;  break;
		case 4: p=56000;  break;
		case 5: p=70000;  break;
		case 6: p=62300;  break;
		case 7: p=40000;  break;
		case 8: p=50000;  break;
		case 9: p=60000;  break;
		case 10: p=40000;  break;
		case 11: p=25000;  break;
		case 12: p=75000;  break;
		case 13: p=30000;  break;
		case 14: p=62000;  break;
		case 15: p=26000;  break;
		case 16: p=32000;  break;
		case 17: p=54000;  break;
		case 18: p=57000;  break;
		case 19: p=42000;  break;
		case 20: p=15000;  break;
		case 21: p=16000;  break;
		case 22: p=90000;  break;
		case 23: p=43000;  break;
		case 24: p=36000;  break;
		case 25: p=27000;  break;
		case 26: p=64000;  break;
		case 27: p=27000;  break;
		case 28: p=31000;  break;
		case 29: p=28000;  break;
		case 30: p=63000;  break;
		case 31: p=74000;  break;
		case 32: p=23000;  break;
		case 33: p=33000; break;
		case 34: p=25000; break;
		case 35: p=29300; break;
		case 36: p=50300; break;
		case 37: p=24000; break;
		case 38: p=31500; break;
		case 39: p=34700; break;
		case 40: p=36400; break;
		case 41: p=17300; break;
		case 42: p=32000; break;
		case 43: p=73000;  break;
		case 44: p=100000;  break;
		case 45: p=15000; break;
		case 46: p=56500; break;
		case 47: p=32500; break;
		case 48: p=34850; break;
		case 49: p=61000;  break;
		case 50: p=64000;  break;
		case 51: p=49313;  break;
		case 52: p=33275;  break;
		case 53: p=65000;  break;
		case 54: p=43600;  break;
		case 55: p=250000;  break;
		case 56: p=46200;  break;
		case 57: p=47200;  break;
		case 58: p=64500;  break;
		case 59: p=68700;  break;
		case 60: p=37600;  break;
		case 61: p=52315;  break;
		case 62: p=49300;  break;
		case 63: p=55000;  break;
		case 64: p=38500;  break;
		case 65: p=43300; break;
		}
		return p;
	}
	
	public static String getSpecialNames(int nr, boolean truespecial)
	{
		String name1="",name2="";
		
		switch(nr)
		{
			case 0: name1="Big Bang Attack"; name2="Galick Gun"; break;//Vegeta
			case 1: name1="Final Flash"; name2="Big Bang Attack"; break;//SSJ2 Vegeta
			case 2: name1="Spirit Bomb"; name2="Kame-hame-ha"; break;//Goku
			case 3: name1="Angry Kame-hame-ha"; name2="Comet Combo"; break;//SSj Goku
			case 4: name1="Super Kame-hame-ha"; name2="Energy Volley"; break;//Teen Gohan SSj
			case 5: name1="Super Kame-hame-ha"; name2="Fire Ball"; break;//Mystic Gohan
			case 6: name1="Donut Beam"; name2="Super Ghost Kamikaze Attack"; break;//SSJ3 Gotenks
			case 7: name1="Flame Bomb"; name2="Buster Strike"; break;//Trunks SSj
			case 8: name1="Perfect Barriere"; name2="Super Kame-hame-ha"; break;//Cell
			case 9: name1="Die Humans"; name2="Super Kame-hame-ha"; break;//Boo
			case 10: name1="Death Ball"; name2="Death Beam"; break;//Freezer
			case 11: name1="Special Beam Cannon"; name2="Hell Grenade"; break;//Piccolo
			case 12: name1="Omega Blaster"; name2="Full Power Volley"; break;//Broly
			case 13: name1="Infinity Volley"; name2="Full Power Blast"; break;//C-18
			case 14: name1="Destroy the Planet"; name2="Raahhhhhhh"; break;//Kid Boo
			case 15: name1="Hell Thunder"; name2="Flying Fist"; break;//C-16
			case 16: name1="Special Beam Cannon"; name2="Absorbtion"; break;//Imperfect Cell
			case 17: name1="Super Kame-hame-ha"; name2="Blast Away"; break;//Fat Boo
			case 18: name1="Master of Universe"; name2="Shizo Slices"; break;//Freeza 100%
			case 19: name1="Masenko"; name2="Kame-hame-ha"; break;//Son Gohan SSj
			case 20: name1="Kame-hame-ha"; name2="Energy Volley"; break;//Goten SSj
			case 21: name1="Buster Cannon"; name2="Power Volley"; break;//Trunks SSj
			case 22: name1="Final Kame-hame-ha"; name2="Kebab Sword"; break;//Vegeto
			case 23: name1="Super-Absorb"; name2="Kame-hame-ha"; break;//2nd Cell
			case 24: name1="Falling Star"; name2="Photon Flash"; break;//C-17
			case 25: name1="Break Cannon"; name2="Volcano Explosion"; break;//Nappa
			case 26: name1="Revenge Deathball"; name2="Final Flash"; break;//Baby Vegta 
			case 27: name1="Bodys Change"; name2="Galaxy Dynamite"; break;//Ginyu
			case 28: name1="Kiku Cannon"; name2="Dodonpa"; break;//tien
			case 29: name1="Bionic Punisher"; name2="Energy Absorbtion"; break;//DrGero
			case 30: name1="Dragon Fist"; name2="Mega Energy Volley"; break;//ss3goku
			case 31: name1="10x Kame-hame-ha"; name2="Fireball"; break;//ss4goku
			case 32: name1="Destructo Disk"; name2="Solar Flare"; break;//krillin
			case 33: name1="Purple Comet Attack"; name2="Blue Hurricane"; break;//Burter
			case 34: name1="Clarinet Terror"; name2="Squid Beam"; break;//Squid
			case 35: name1="Crusher Ball"; name2="Purple Blaster"; break;//Jeece
			case 36: name1="Galick Buster"; name2="Energy Volley"; break;//SSj Vegeta
			case 37: name1="Spirit Ball"; name2="Wolf Fang Blast"; break;//Yamcha
			case 38: name1="Recoome Eraser Gun"; name2="Recoome Kaboom"; break;//Recoome
			case 39: name1="Super Kame-hame-ha"; name2="Super Energy Wave Volley"; break;//uub
			case 40: name1="Final Spirit Cannon"; name2="Saiyan Soul"; break;//Bardock
			case 41: name1="Telekinesis"; name2="Eye Lasers"; break;//Guldo
			case 42: name1="Super Kame-hame-ha"; name2="Special Beam Cannon"; break;//CellJR
			case 43: name1="Final Shine Attack"; name2="Big Bang Barrage"; break;//SSj4Vegeta
			case 44: name1="Omega Death"; name2="Infinity Blast"; break;//SSj4Broly
			case 45: name1="Dynamite Kick"; name2="Rolling Thunder"; break; //Hercule
			case 46: name1="Hell Gate"; name2="Illusion Strike"; break; //Janemba
			case 47: name1="Hero Strike"; name2="Friend Fire"; break; //Tapion
			case 48: name1="Universe Power"; name2="Energy Rain"; break; //Supreme Kai
			case 49: name1="Teleport Kame-hame-ha"; name2="Meteor Rush"; break; //ssj2 goku
			case 50: name1="Solar Kame-hame-ha"; name2="Perfect Energy"; break; //perfect cell
			case 51: name1="Deadly Bomber"; name2="Deadly Assault"; break; //c13
			case 52: name1="Energy Bullets"; name2="Nice Shot"; break; //puipui
			case 53: name1="Father-Son Kame-hame-ha"; name2="Gekiretsu Madan"; break; //teen gohan ssj2
			case 54: name1="Stone Spit"; name2="Evil Flame"; break; //dabura
			case 55: name1="Spirit Kame-hame-ha"; name2="Spirit Bomb"; break; //ssj5 goku
			case 56: name1="Supernova"; name2="Death Beam"; break; //Cooler
			case 57: name1="Masenko"; name2="Gekiretsu Madan"; break; //teen gohan 
			case 58: name1="Super Ghost Kamikaze Attack"; name2="Kame-hame-ha"; break; //buutenks
			case 59: name1="Super Kame-hame-ha"; name2="Chocolade Beam"; break; //buuhan
			case 60: name1="Buster Strike"; name2="Rush Forward"; break;//Trunks 
			case 61: name1="Deadly Revenge Bomber"; name2="Deadly Volley"; break;//super c13 
			case 62: name1="Big Tree Cannon"; name2="Energy Volley"; break;//gotenks
			case 63: name1="Super Ghost Kamikaze Attack"; name2="Super Kame-hame-ha"; break;//ssj gotenks
			case 64: name1="Super Kame-hame-ha"; name2="Gekiretsu Madan"; break;//son gohan
			case 65: name1="Supernova"; name2="Metal Hammer"; break;//metal cooler
		}
		String name="";
		if(truespecial)
		{
			name=name1;
		}
		else
		{
			name=name2;
		}
		return name;
	}
	
	
	
	public static int getMaxKi()
	{
		return 3000;
	}
	
	private static int getMaxKi2()
	{
		return 3000;
	}
	
	public static int[] getAttributes(int id)
	{
		int[] att=new int[8];
		/*Attribute
		 * 0.id
		 * 1.leben 
		 * 2.maxleben
		 * 3.ki
		 * 4.def
		 * 5.speed
		 * 6.ki load
		 * 7.attackspeed
		 * 
		 */
		att[0]=id;
		switch(id)
		{       //Leben     Def        Speed     KiLoad    Attackwait
		case 0: att[2]=750; att[4]=12; att[5]=8; att[6]=9; att[7]=8; break;//Vegeta
		case 1: att[2]=800; att[4]=11; att[5]=7; att[6]=10;  att[7]=7; break;//SSJ Vegeta
		case 2: att[2]=780; att[4]=11; att[5]=10; att[6]=8; att[7]=9;break;//Goku
		case 3: att[2]=870; att[4]=10; att[5]=8; att[6]=9; att[7]=9;break;//SSJ Goku
		case 4: att[2]=650; att[4]=8; att[5]=11; att[6]=11; att[7]=7;break;//SSJ2 Gohan
		case 5: att[2]=820; att[4]=9; att[5]=12; att[6]=10; att[7]=11; break;//Mystic Gohan
		case 6: att[2]=620; att[4]=10; att[5]=10; att[6]=9; att[7]=9;break;//SSJ3 Gotenks
		case 7: att[2]=710; att[4]=14; att[5]=12; att[6]=8; att[7]=6; break;//SSJ Trunks
		case 8: att[2]=980; att[4]=10; att[5]=10; att[6]=9; att[7]=9; break;//Cell
		case 9: att[2]=1100; att[4]=12; att[5]=8; att[6]=9; att[7]=10; break;//Boo
		case 10: att[2]=780; att[4]=10; att[5]=12; att[6]=11; att[7]=8; break;//Freeza
		case 11: att[2]=900; att[4]=10; att[5]=9; att[6]=8; att[7]=9; break;//Piccolo
		case 12: att[2]=1300; att[4]=8; att[5]=5; att[6]=6;  att[7]=20;break;//Broly	
		case 13: att[2]=680; att[4]=8; att[5]=7; att[6]=0;  att[7]=8;break;//C-18
		case 14: att[2]=760; att[4]=9; att[5]=11; att[6]=12;  att[7]=7;break;//Kid Boo
		case 15: att[2]=885; att[4]=10; att[5]=6; att[6]=0;  att[7]=15;break;//C-16
		case 16: att[2]=850; att[4]=9; att[5]=7; att[6]=7;  att[7]=9;break;//Imperfect Cell
		case 17: att[2]=970; att[4]=13; att[5]=7; att[6]=7;  att[7]=11;break;//Fat Boo
		case 18: att[2]=820; att[4]=8; att[5]=7; att[6]=6;  att[7]=12;break;//Freezer 100%
		case 19: att[2]=740; att[4]=9; att[5]=9; att[6]=8;  att[7]=9;break;//Son Gohan SSj
		case 20: att[2]=630; att[4]=11; att[5]=11; att[6]=9;  att[7]=7;break;//Goten SSj
		case 21: att[2]=680; att[4]=10; att[5]=11; att[6]=10;  att[7]=8;break;//Trunks SSj
		case 22: att[2]=1150; att[4]=12; att[5]=10; att[6]=10;  att[7]=7;break;//Vegeto
		case 23: att[2]=800; att[4]=10; att[5]=9; att[6]=8;  att[7]=8;break;//2nd Cell
		case 24: att[2]=780; att[4]=7; att[5]=8; att[6]=0;  att[7]=9;break;//C-17
		case 25: att[2]=1350; att[4]=9; att[5]=6; att[6]=5;  att[7]=17;break;//Nappa
		case 26: att[2]=840; att[4]=10; att[5]=9; att[6]=10;  att[7]=6; break;//BabyVegeta
		case 27: att[2]=950; att[4]=8; att[5]=8; att[6]=8;  att[7]=13;break;//ginyu
		case 28: att[2]=920; att[4]=9; att[5]=7; att[6]=8;  att[7]=12;break;//tenshinhan
		case 29: att[2]=550; att[4]=9; att[5]=9; att[6]=0;  att[7]=8; break;//DrGero
		case 30: att[2]=900; att[4]=12; att[5]=9; att[6]=8; att[7]=9;break;//SSJ3 Goku
		case 31: att[2]=950; att[4]=10; att[5]=8; att[6]=7; att[7]=11;break;//SSJ4 Goku
		case 32: att[2]=680; att[4]=10; att[5]=9; att[6]=9; att[7]=9;break;//Krillin
		case 33: att[2]=880; att[4]=9; att[5]=15; att[6]=7; att[7]=5;break;//Burter
		case 34: att[2]=500; att[4]=11; att[5]=10; att[6]=8; att[7]=7;break;//squid
		case 35: att[2]=720; att[4]=10; att[5]=8; att[6]=8; att[7]=9;break;//Jeece
		case 36: att[2]=760; att[4]=11; att[5]=8; att[6]=9;  att[7]=8; break;//SSJ Vegeta
		case 37: att[2]=770; att[4]=9; att[5]=9; att[6]=8; att[7]=7;break;//Yamcha
		case 38: att[2]=1330; att[4]=10; att[5]=7; att[6]=4;  att[7]=19;break;//Recoome
		case 39: att[2]=740; att[4]=10; att[5]=9; att[6]=9; att[7]=10;break;//Uub
		case 40: att[2]=720; att[4]=9; att[5]=8; att[6]=7; att[7]=9; break;//Bardock
		case 41: att[2]=520; att[4]=8; att[5]=7; att[6]=8; att[7]=10; break;//Guldo
		case 42: att[2]=480; att[4]=9; att[5]=10; att[6]=10; att[7]=7; break;//CellJR
		case 43: att[2]=930; att[4]=10; att[5]=7; att[6]=8; att[7]=10;break;//SSJ4 Vegeta
		case 44: att[2]=1500; att[4]=9; att[5]=6; att[6]=6;  att[7]=16;break;//SSj4Broly	
		case 45: att[2]=800; att[4]=6; att[5]=6; att[6]=7; att[7]=11; break;//Hercule
		case 46: att[2]=810; att[4]=12; att[5]=9; att[6]=9; att[7]=8; break;//Janemba
		case 47: att[2]=760; att[4]=10; att[5]=11; att[6]=8; att[7]=9; break;//Tapion
		case 48: att[2]=720; att[4]=9; att[5]=10; att[6]=10; att[7]=10; break;//Kai
		case 49: att[2]=900; att[4]=9; att[5]=9; att[6]=8; att[7]=8;break;//SSJ Goku
		case 50: att[2]=970; att[4]=11; att[5]=9; att[6]=8; att[7]=9; break;//Cell
		case 51: att[2]=760; att[4]=8; att[5]=7; att[6]=0;  att[7]=8;break;//C-13
		case 52: att[2]=700; att[4]=10; att[5]=11; att[6]=11; att[7]=5;break;//puipui
		case 53: att[2]=670; att[4]=9; att[5]=10; att[6]=10; att[7]=8;break;//SSJ2 Gohan
		case 54: att[2]=840; att[4]=9; att[5]=8; att[6]=8;  att[7]=10;break;//dabura
		case 55: att[2]=1250; att[4]=10; att[5]=10; att[6]=10;  att[7]=10;break;//ssj 5 goku
		case 56: att[2]=830; att[4]=10; att[5]=10; att[6]=9; att[7]=9; break;//Cooler
		case 57: att[2]=680; att[4]=8; att[5]=10; att[6]=9; att[7]=8;break;//teen Gohan
		case 58: att[2]=1050; att[4]=11; att[5]=8; att[6]=8; att[7]=8; break;//Boo
		case 59: att[2]=930; att[4]=10; att[5]=9; att[6]=9; att[7]=7; break;//Boo
		case 60: att[2]=750; att[4]=12; att[5]=10; att[6]=8; att[7]=7; break;// Trunks
		case 61: att[2]=980; att[4]=9;  att[5]=7;  att[6]=7;   att[7]=10;  break; //super 13
		case 62: att[2]=680; att[4]=10; att[5]=9; att[6]=9; att[7]=8;break;// Gotenks
		case 63: att[2]=665; att[4]=11; att[5]=9; att[6]=10; att[7]=9;break;//ssj Gotenks
		case 64: att[2]=715; att[4]=10; att[5]=8; att[6]=8;  att[7]=8;break;//Son Gohan 
		case 65: att[2]=730; att[4]=8; att[5]=9; att[6]=0; att[7]=8; break;//metal Cooler
		case GOKUKAIOKEN:  att[2]=0; att[4]=15; att[5]=15; att[6]=7; att[7]=5; break;
		}
		att[7]-=3;
		
		att[2]/=1.5;
		att[4]+=5;
		if(att[6]>0)
		{
		att[6]+=5;
		}
		//att[7]+=10;
		att[1]=att[2];
		att[3]=getMaxKi2()/3;
		return att;
	}
	
	public static int[] getDamages(int id)
	{
		int[] dam=new int[5];
		/*Damages:
		 *0. Normal Attack
		 *1. Heavy Attack
		 *2. Ki Attack
		 *3. Spezial
		 *4.second spezial
		 */
		switch(id)
		{//       Normal   Heavy      Ki          Special    Second  Special    
		case 0:dam[0]=12; dam[1]=35; dam[2]=22; dam[3]=500;  dam[4]=16; break;//Vegeta
		case 1:dam[0]=14; dam[1]=32; dam[2]=23; dam[3]=40;   dam[4]=210; break;//SSJ Vegeta
		case 2:dam[0]=13; dam[1]=34; dam[2]=20; dam[3]=600; dam[4]=17; break;//Goku
		case GOKUKAIOKEN:dam[0]=17; dam[1]=37; dam[2]=10; dam[3]=600; dam[4]=17; break;//Goku
		case 3:dam[0]=13; dam[1]=31; dam[2]=20; dam[3]=40; dam[4]=15; break;//SSJ Goku
		case 4:dam[0]=15; dam[1]=29; dam[2]=20; dam[3]=41; dam[4]=10; break;//SSJ Gohan
		case 5:dam[0]=16; dam[1]=36; dam[2]=20; dam[3]=40;dam[4]=210;  break;//Mystic Gohan
		case 6:dam[0]=17; dam[1]=37; dam[2]=20; dam[3]=43; dam[4]=160; break;//SSJ3 Gotenks
		case 7:dam[0]=10; dam[1]=39; dam[2]=20; dam[3]=520;dam[4]=15;  break;//SSJ Trunks
		case 8:dam[0]=15; dam[1]=36; dam[2]=20; dam[3]=5; dam[4]=15; break;//Cell
		case 9:dam[0]=13; dam[1]=31; dam[2]=20; dam[3]=20; dam[4]=17; break;//Boo
		case 10:dam[0]=12; dam[1]=30; dam[2]=19; dam[3]=540;dam[4]=18;  break;//Freeza
		case 11:dam[0]=13; dam[1]=33; dam[2]=20; dam[3]=38; dam[4]=10; break;//Piccolo
		case 12:dam[0]=19; dam[1]=41; dam[2]=20; dam[3]=450;dam[4]=24;  break;//Broly
		case 13:dam[0]=14; dam[1]=36; dam[2]=23; dam[3]=45;dam[4]=15;  break;//C-18
		case 14:dam[0]=11; dam[1]=34; dam[2]=20; dam[3]=550;dam[4]=16;  break;//Kid Boo
		case 15:dam[0]=16; dam[1]=39; dam[2]=21; dam[3]=80;dam[4]=250;  break;//C-16
		case 16:dam[0]=15; dam[1]=38; dam[2]=22; dam[3]=36;dam[4]=150;  break;//Imperfect Cell
		case 17:dam[0]=14; dam[1]=39; dam[2]=21; dam[3]=39;dam[4]=8;  break;//Fat Boo
		case 18:dam[0]=18; dam[1]=40; dam[2]=10; dam[3]=10; dam[4]=75;  break;//Freezer 100%
		case 19:dam[0]=13; dam[1]=35; dam[2]=22; dam[3]=42; dam[4]=16;  break;//Son Gohan SSj
		case 20:dam[0]=10; dam[1]=28; dam[2]=19; dam[3]=32; dam[4]=12;  break;//Goten SSj
		case 21:dam[0]=11; dam[1]=31; dam[2]=21; dam[3]=35; dam[4]=10;  break;//Trunks SSj
		case 22:dam[0]=15; dam[1]=38; dam[2]=20; dam[3]=30; dam[4]=20;  break;//Vegeto
		case 23:dam[0]=14; dam[1]=35; dam[2]=24; dam[3]=350;  dam[4]=17; break;//2nd Cell 
		case 24:dam[0]=15; dam[1]=34; dam[2]=22; dam[3]=30; dam[4]=17;  break;//C-17
		case 25:dam[0]=16; dam[1]=37; dam[2]=15; dam[3]=36; dam[4]=7;  break;//Nappa
		case 26:dam[0]=15; dam[1]=36; dam[2]=24; dam[3]=535;   dam[4]=16; break;//baby Vegeta
		case 27:dam[0]=16; dam[1]=38; dam[2]=21; dam[3]=0;   dam[4]=10; break;//ginyu
		case 28:dam[0]=17; dam[1]=40; dam[2]=18; dam[3]=175;   dam[4]=17; break;//tenshinhan
		case 29:dam[0]=12; dam[1]=32; dam[2]=23; dam[3]=18; dam[4]=0;  break;//Dr.Gero
		case 30:dam[0]=16; dam[1]=39; dam[2]=24; dam[3]=41; dam[4]=25; break;//SSJ3 Goku
		case 31:dam[0]=17; dam[1]=41; dam[2]=21; dam[3]=45; dam[4]=230; break;//SSJ4 Goku
		case 32:dam[0]=12; dam[1]=32; dam[2]=19; dam[3]=400; dam[4]=0; break;//Krillin
		case 33:dam[0]=10; dam[1]=25; dam[2]=20; dam[3]=12; dam[4]=30; break;//Burter
		case 34:dam[0]=7; dam[1]=32; dam[2]=15; dam[3]=2; dam[4]=20; break;//Squid
		case 35:dam[0]=12; dam[1]=34; dam[2]=21; dam[3]=20; dam[4]=20; break;//Jeece
		case 36:dam[0]=13; dam[1]=35; dam[2]=25; dam[3]=35;   dam[4]=10; break;//SSJ Vegeta
		case 37:dam[0]=12; dam[1]=34; dam[2]=18; dam[3]=21; dam[4]=16; break;//Yamcha
		case 38:dam[0]=17; dam[1]=41; dam[2]=16; dam[3]=37; dam[4]=3;  break;//Recoome
		case 39:dam[0]=12; dam[1]=36; dam[2]=22; dam[3]=36; dam[4]=15; break;//uub
		case 40:dam[0]=13; dam[1]=38; dam[2]=22; dam[3]=37; dam[4]=190;  break;//Bardock
		case 41:dam[0]=7; dam[1]=20; dam[2]=15; dam[3]=0; dam[4]=10;  break;//Guldo
		case 42:dam[0]=9; dam[1]=23; dam[2]=18; dam[3]=33; dam[4]=22;  break;//CellJR
		case 43:dam[0]=15; dam[1]=40; dam[2]=26; dam[3]=43; dam[4]=25; break;//SSJ4 Vegeta
		case 44:dam[0]=20; dam[1]=45; dam[2]=22; dam[3]=50; dam[4]=25;  break;//SSj4Broly
		case 45:dam[0]=10; dam[1]=50; dam[2]=10; dam[3]=600; dam[4]=8;  break;//Hercule
		case 46:dam[0]=14; dam[1]=38; dam[2]=20; dam[3]=110; dam[4]=9;  break;//Janemba
		case 47:dam[0]=11; dam[1]=40; dam[2]=21; dam[3]=75; dam[4]=13;  break;//Tapion
		case 48:dam[0]=9; dam[1]=25; dam[2]=24; dam[3]=450; dam[4]=7;  break;//Kai
		case 49:dam[0]=16; dam[1]=32; dam[2]=22; dam[3]=42; dam[4]=35; break;//SSJ2 Goku
		case 50:dam[0]=17; dam[1]=34; dam[2]=21; dam[3]=39; dam[4]=30; break;//perfect cell
		case 51:dam[0]=14; dam[1]=37; dam[2]=23; dam[3]=475; dam[4]=33;  break;//C-13
		case 52:dam[0]=5; dam[1]=10; dam[2]=20; dam[3]=13; dam[4]=150; break;//puipui
		case 53:dam[0]=15; dam[1]=32; dam[2]=22; dam[3]=45; dam[4]=33; break;//SSJ2 Gohan
		case 54:dam[0]=16; dam[1]=37; dam[2]=20; dam[3]=250;   dam[4]=8; break;//dabura
		case 55:dam[0]=18; dam[1]=45; dam[2]=22; dam[3]=70; dam[4]=12; break;//SSJ5 Goku
		case 56:dam[0]=15; dam[1]=34; dam[2]=20; dam[3]=500; dam[4]=17;  break;//Cooler
		case 57:dam[0]=12; dam[1]=32; dam[2]=21; dam[3]=37; dam[4]=12; break;//teen Gohan
		case 58:dam[0]=14; dam[1]=32; dam[2]=21; dam[3]=60; dam[4]=16; break;//buutenks
		case 59:dam[0]=13; dam[1]=35; dam[2]=24; dam[3]=42; dam[4]=150; break;//buuhan
		case 60:dam[0]=11; dam[1]=37; dam[2]=21; dam[3]=22; dam[4]=10;  break;//SSJ Trunks
		case 61: dam[0]=17; dam[1]=44; dam[2]=17;  dam[3]=495; dam[4]=18; break; //super 13      
		case 62: dam[0]=12; dam[1]=34; dam[2]=19; dam[3]=40; dam[4]=15; break;//Gotenks
		case 63: dam[0]=14; dam[1]=36; dam[2]=20; dam[3]=43; dam[4]=17; break;//ssj Gotenks
		case 64:dam[0]=14; dam[1]=38; dam[2]=20; dam[3]=39; dam[4]=13;  break;//Son Gohan SSj
		case 65:dam[0]=16; dam[1]=37; dam[2]=23; dam[3]=475; dam[4]=175;  break;//Metal Cooler
		}
		dam[0]*=1.3;
		dam[1]*=1.3;
		return dam;
	}
	
	public int getAttackDesign(int id) {
		// TODO Auto-generated method stub
		int kiattackdesign=0;
		switch(id)
		{
		case 0: kiattackdesign=31; break;
		case 1: kiattackdesign=39; break;
		case 2: kiattackdesign=11; break;
		case GOKUKAIOKEN: kiattackdesign=21; break;
		case 3: kiattackdesign=19; break;
		case 4: kiattackdesign=15; break;
		case 5: kiattackdesign=14; break;
		case 6: kiattackdesign=17; break;
		case 7: kiattackdesign=16; break;
		case 8: kiattackdesign=24; break;
		case 9: kiattackdesign=21; break;
		case 10: kiattackdesign=29; break;
		case 11: kiattackdesign=33; break;
		case 12: kiattackdesign=72; break;
		case 13: kiattackdesign=37; break;
		case 14: kiattackdesign=25; break;
		case 15: kiattackdesign=16; break;
		case 16: kiattackdesign=19; break;
		case 17: kiattackdesign=27; break;
		case 18: kiattackdesign=34; break;
		case 19: kiattackdesign=14; break;
		case 20: kiattackdesign=15; break;
		case 21: kiattackdesign=33; break;
		case 22: kiattackdesign=15; break;
		case 23: kiattackdesign=25; break;
		case 24: kiattackdesign=32; break;
		case 25: kiattackdesign=36; break;
		case 26: kiattackdesign=59; break;
		case 27: kiattackdesign=21; break;
		case 28: kiattackdesign=21; break;
		case 30: kiattackdesign=41; break;
		case 31: kiattackdesign=67; break;
		case 32: kiattackdesign=13; break;
		case 33: kiattackdesign=35; break;
		case 34: kiattackdesign=8; break;
		case 35: kiattackdesign=21; break;
		case 36: kiattackdesign=15; break;
		case 37: kiattackdesign=12; break;
		case 38: kiattackdesign=36; break;
		case 39: kiattackdesign=19; break;
		case 40: kiattackdesign=38; break;
		case 41: kiattackdesign=32; break;
		case 42: kiattackdesign=25; break;
		case 43: kiattackdesign=42; break;
		case 44: kiattackdesign=72; break;
		case 45: kiattackdesign=8; break;
		case 46: kiattackdesign=27; break;
		case 47: kiattackdesign=14; break;
		case 48: kiattackdesign=30; break;
		case 49: kiattackdesign=19; break;
		case 50: kiattackdesign=24; break;
		case 51: kiattackdesign=26; break;
		case 52: kiattackdesign=59; break;
		case 53: kiattackdesign=14; break;
		case 54: kiattackdesign=29; break;
		case 55: kiattackdesign=49; break;
		case 56: kiattackdesign=37; break;
		case 57: kiattackdesign=15; break;
		case 58: kiattackdesign=26; break;
		case 59: kiattackdesign=16; break;
		case 60: kiattackdesign=16; break;
		case 61: kiattackdesign=30; break;		
		case 62: kiattackdesign=15; break;
		case 63: kiattackdesign=15; break;
		case 64: kiattackdesign=36; break;
		case 65: kiattackdesign=25; break;
		}
		return kiattackdesign;
	}
	
	
	public static int[] getKiWastage(int id)
	{
		int[] kiw=new int[4];
		/*Damages:
		 *0. Normal Attack
		 *1. Spezail Attack
		 *2. second attack
		 *3. enforce aura
		 */
		int maxki=getMaxKi2();
		double faktor=0.97;
		kiw[1]=(int)(maxki*faktor);
		kiw[2]=(int) (maxki/2); 
		kiw[3]=(int) (getMaxKi()*0.3);
		switch(id)
		{
		case 0: kiw[0]=maxki/20;  break;
		case 1: kiw[0]=maxki/22; break;
		case 2: kiw[0]=maxki/12;  break;
		case GOKUKAIOKEN: kiw[0]=maxki/10;  break;
		case 3: kiw[0]=maxki/13;  break;
		case 4: kiw[0]=maxki/15; break;
		case 5: kiw[0]=maxki/14; break;
		case 6: kiw[0]=maxki/18; break;
		case 7: kiw[0]=maxki/16; break;
		case 8: kiw[0]=maxki/17; break;
		case 9: kiw[0]=maxki/25; break;
		case 10: kiw[0]=maxki/21;  break;
		case 11: kiw[0]=maxki/19; break;
		case 12: kiw[0]=maxki/11;  break;
		case 13: kiw[0]=maxki/15;  break;
		case 14: kiw[0]=maxki/15; break;
		case 15: kiw[0]=maxki/24; break;
		case 16: kiw[0]=maxki/15;  break;
		case 17: kiw[0]=maxki/19; break;
		case 18: kiw[0]=maxki/12;  break;
		case 19: kiw[0]=maxki/14;  break;
		case 20: kiw[0]=maxki/16;  break;
		case 21: kiw[0]=maxki/21;  break;
		case 22: kiw[0]=maxki/25;  break;
		case 23: kiw[0]=maxki/23;  break;
		case 24: kiw[0]=maxki/18;  break;
		case 25: kiw[0]=maxki/16;  break;
		case 26: kiw[0]=maxki/26; break;
		case 27: kiw[0]=maxki/18; break;
		case 28: kiw[0]=maxki/19;  break;
		case 29: kiw[0]=maxki/22;  break;
		case 30: kiw[0]=maxki/26;  break;
		case 31: kiw[0]=maxki/22;  break;
		case 32: kiw[0]=maxki/24;  break;
		case 33: kiw[0]=maxki/25;  break;
		case 34: kiw[0]=maxki/30;  break;
		case 35: kiw[0]=maxki/22;  break;
		case 36: kiw[0]=maxki/28;  break;
		case 37: kiw[0]=maxki/24;  break;
		case 38: kiw[0]=maxki/18; break;
		case 39: kiw[0]=maxki/25; break;
		case 40: kiw[0]=maxki/23;  break;
		case 41: kiw[0]=maxki/25; break;
		case 42: kiw[0]=maxki/23; break;
		case 43: kiw[0]=maxki/26; break;
		case 44: kiw[0]=maxki/18;  break;
		case 45: kiw[0]=maxki/24;  break;
		case 46: kiw[0]=maxki/22; break;
		case 47: kiw[0]=maxki/21;  break;
		case 48: kiw[0]=maxki/27; break;
		case 49: kiw[0]=maxki/21;break;
		case 50: kiw[0]=maxki/23; break;
		case 51: kiw[0]=maxki/22; break;
		case 52: kiw[0]=maxki/30;  break;
		case 53: kiw[0]=maxki/18; break;
		case 54: kiw[0]=maxki/19; break;
		case 55: kiw[0]=maxki/23; break;
		case 56: kiw[0]=maxki/23; break;
		case 57: kiw[0]=maxki/21; break;
		case 58: kiw[0]=maxki/20; break;
		case 59: kiw[0]=maxki/23; break;
		case 60: kiw[0]=maxki/18; break;
		case 61: kiw[0]=maxki/16; break;		
		case 62: kiw[0]=maxki/20; break;
		case 63: kiw[0]=maxki/22; break;
		case 64: kiw[0]=maxki/20; break;
		case 65: kiw[0]=maxki/21; break;
		}
		
	
		return kiw;
	}
	

	
	public static int[] getFirePos(int id,boolean truespecial){
		
		int x1=0,y1=0,x2=0,y2=0;
		
		
		switch(id)
		{
		case 0:  x1=68; y1=49;   x2=68; y2=49;  break;
		case 1:  x1=63; y1=49;   x2=63; y2=49;  break;
		case 2:  x1=44; y1=-30;   x2=64; y2=53;  break;
		case GOKUKAIOKEN:  x1=44; y1=-30;   x2=64; y2=53;  break;
		case 3:  x1=67; y1=52;   x2=67; y2=52;  break;
		case 4:  x1=59; y1=49;   x2=59; y2=49;  break;
		case 5:  x1=70; y1=51;   x2=70; y2=51;  break;
		case 6:  x1=68; y1=49;   x2=68; y2=49;  break;
		case 7:  x1=76; y1=40;   x2=76; y2=40;  break;
		case 8:  x1=49; y1=47;   x2=76; y2=37;  break;
		case 9:  x1=44; y1=4;   x2=80; y2=40;  break;
		case 10:  x1=42; y1=-15;   x2=88; y2=34;  break;
		case 11:  x1=89; y1=41;   x2=89; y2=42;  break;
		case 12:  x1=55; y1=44;   x2=55; y2=41;  break;
		case 13:  x1=74; y1=43;   x2=74; y2=43;  break;
		case 14:  x1=60; y1=-10;   x2=75; y2=57;  break;
		case 15:  x1=54; y1=52;   x2=80; y2=28;  break;
		case 16:  x1=105; y1=42;   x2=110; y2=39;  break;
		case 17:  x1=83; y1=48;   x2=55; y2=120;  break;
		case 18:  x1=96; y1=45;   x2=34; y2=17;  break;
		case 19:  x1=72; y1=42;   x2=75; y2=36;  break;
		case 20:  x1=70; y1=50;   x2=70; y2=52;  break;
		case 21:  x1=72; y1=49;   x2=80; y2=46;  break;
		case 22:  x1=74; y1=47;   x2=77; y2=41;  break;
		case 23:  x1=100; y1=37;   x2=81; y2=40;  break;
		case 24:  x1=80; y1=43;   x2=76; y2=40;  break;
		case 25:  x1=70; y1=33;   x2=150; y2=50;  break;
		case 26:  x1=50; y1=-15;   x2=76; y2=45;  break;
		case 27:  x1=55; y1=35;   x2=85; y2=42;  break;
		case 28:  x1=58; y1=27;   x2=97; y2=33;  break;
		case 29:  x1=56; y1=30;   x2=67; y2=42;  break;
		case 30:  x1=50; y1=57;   x2=65; y2=52;  break;
		case 31:  x1=80; y1=42;   x2=86; y2=44;  break;
		case 32:  x1=36; y1=16;   x2=120; y2=38;  break;
		case 33:  x1=86; y1=32;   x2=90; y2=41;  break;
		case 34:  x1=73; y1=40;   x2=80; y2=39;  break;
		case 35:  x1=84; y1=44;   x2=79; y2=38;  break;
		case 36:  x1=68; y1=41;   x2=70; y2=42;  break;
		case 37:  x1=81; y1=35;   x2=71; y2=42;  break;
		case 38:  x1=62; y1=31;   x2=50; y2=47;  break;
		case 39:  x1=78; y1=47;   x2=74; y2=44;  break;
		case 40:  x1=88; y1=41;   x2=69; y2=42;  break;
		case 41:  x1=50; y1=50;   x2=65; y2=38;  break;
		case 42:  x1=78; y1=44;   x2=80; y2=40;  break;
		case 43:  x1=76; y1=40;   x2=78; y2=35;  break;
		case 44:  x1=84; y1=26;   x2=94; y2=26;  break;
		case 45:  x1=85; y1=46;   x2=95; y2=44;  break;
		case 46:  x1=80; y1=33;   x2=57; y2=35;  break;
		case 47:  x1=50; y1=50;   x2=87; y2=44;  break;
		case 48:  x1=73; y1=37;   x2=56; y2=50;  break;
		case 49:  x1=77; y1=44;   x2=86; y2=25;  break;
		case 50:  x1=78; y1=34;   x2=83; y2=34;  break;
		case 51:  x1=70; y1=37;   x2=95; y2=29;  break;
		case 52:  x1=78; y1=41;   x2=77; y2=42;  break;
		case 53:  x1=71; y1=44;   x2=75; y2=45;  break;
		case 54:  x1=82; y1=32;   x2=153; y2=33;  break;
		case 55:  x1=86; y1=30;   x2=95; y2=28;  break;
		case 56:  x1=92; y1=16;   x2=85; y2=35;  break;
		case 57:  x1=75; y1=41;   x2=69; y2=58;  break;
		case 58:  x1=76; y1=40;   x2=81; y2=40;  break;
		case 59:  x1=79; y1=38;   x2=100; y2=31;  break;
		case 60:  x1=129; y1=50;   x2=43; y2=59;  break;
		case 61:  x1=58; y1=32;   x2=58; y2=39;  break;
		case 62:  x1=66; y1=46;   x2=66; y2=48;  break;
		case 63:  x1=66; y1=46;   x2=66; y2=48;  break;
		case 64:  x1=75; y1=38;   x2=66; y2=44;  break;
		case 65:  x1=83; y1=33;   x2=86; y2=41;  break;
		}
		
		
		int i[]=new int[2];
		if(truespecial)
		{
			i[0]=((x1)*2);
			i[1]=((y1)*2);
		}
		else
		{
			i[0]=((x2)*2);
			i[1]=((y2)*2);
		}		
		return i;
	}
	
	/*Attribute
	 * 0.id
	 * 1.leben 
	 * 2.maxleben
	 * 3.ki
	 * 4.def
	 * 5.speed
	 * 6.ki load
	 * 7.attackspeed
	 */
	private void setAttributPower()
	{
		for(int i=0; i<Main.fighteranz; i++)
		{
			for(int h=0; h<6; h++)
			{
				int s=0;				
				switch(h)
				{
				case 0://STRENGTH
					int[] d=getDamages(i);
				for(int j=0; j<2; j++)
				{
					s+=d[j];
				}
				break;
				case 1://DEFENCE
					s=getAttributes(i)[4];
					s+=getAttributes(i)[2]/5;
					break;
				case 2: //KI POWER
					d=getDamages(i);
				    s+=d[2]*3;
				d=getKiWastage(i);
					s-=d[0]/10;
					if(getAttributes(i)[6]==0)
					{
						s+=50;
					}
					else
					{
			        	s+=getAttributes(i)[6]*5;
					}
				break;
				
				case 3: //KI Load
					
				    d=getKiWastage(i);
					s-=d[0]/5;
					if(getAttributes(i)[6]==0)
					{
						s+=50;
					}
					else
					{
			        	s+=getAttributes(i)[6]*5;
					}
				break;
				
				case 4://SPEED
					s=getAttributes(i)[5]*5;
					s-=getAttributes(i)[7];
				break;
						
				case 5://OVERALL
					for(int j=0; j<5; j++)
					{
						s+=(int)((double)power[i][j]*0.2);
					}
					break;
				}
				
				power[i][h]=s;        
				
			}
		}
	}
	
	public static int getTransformForm(int id)
	{
		return dform[id];
	}
	
	public  static boolean canTransform(int id)
	{
		if(dform[id]>0)
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
	
	public float getRegeneration(int id)
	{
		float r=0;
		
		switch(id){
		
		case BOO:  r=0.1f; break;
		case FATBOO:  r=0.1f; break;
		case KIDBOO:  r=0.1f; break;
		case BOOTENKS:  r=0.1f; break;
		case BOOHAN:  r=0.1f; break;
		}
		
		return r;
	}
	
	private void setMaxPower()
	{
		for(int h=0; h<6; h++)
		{
			int max=0;
		  for(int i=0; i<Main.fighteranz; i++)
		   {
			if(power[i][h]>max)
			{
				max=power[i][h];
			}			
		   }
		
		  maxpower[h]=max;
		}
	}
	
	public int getAttributPower(int id, int att)
	{
		return power[id][att];
	}
	
	public int getMaxPower(int att)
	{
		return maxpower[att];
	}
	
	public boolean isCyborg(int id)
	{
	  boolean cyborg=false;
	  switch(id)
	  {
	  case 13: cyborg=true; break;
	  case 15: cyborg=true; break;
	  case 24: cyborg=true; break;
	  case 29: cyborg=true; break;
	  case 51: cyborg=true;  break;
	  case SUPERC13: cyborg=true; break;
	  case METALCOOLER: cyborg=true; break;
	  }
	  return cyborg;
	}
	
	public int getBloodArt(int id)
	{
		int art=0;
		switch(id)
		{
		case FighterData.CELL: art= 1; break;
		case FighterData.SCNDCELL: art= 1; break;
		case FighterData.CELLSUPER: art= 1; break;
		case FighterData.IMPERFECTCELL: art= 1; break;
		case FighterData.CELLJR: art= 1; break;
		case FighterData.BOO: art= 2; break;
		case FighterData.FATBOO: art= 2; break;
		case FighterData.KIDBOO: art= 2; break;
		case FighterData.BOOTENKS: art= 2; break;
		case FighterData.PICCOLO: art= 1; break;
		
		 default:  art=0; break;
		
		}
		return art;
	}

	public static int getPowerRange(int fid, boolean b) {
		
		int sr=10000;
		int ur=10000;
		
		
		
		switch(fid)
		{
		case FighterData.CELL:  ur=200; break;
		case FighterData.SCNDCELL: ur=170; break;
		case FighterData.HERCULE: ur=170; sr=270; break;
		case FighterData.TRUNKS: sr=80; break;
		case FighterData.IMPERFECTCELL: sr=170; break;
		case FighterData.FATBOO: sr=150; break;
		case FighterData.NAPPA: sr=250; break;
		case FighterData.KRILLIN: sr=220; break;
		case FighterData.RECOOME: sr=250; break;
		case FighterData.SSJ4VEGETA: sr=350; break;
		case FighterData.JANEMBA: sr=220; break;
		}
		
		sr+=getFirePos(fid,false)[0];
		ur+=getFirePos(fid,true)[0];
		
		if(b)
		{
			return ur;
		}
		else{
			
			return sr;
		}
	}


	


	
}
