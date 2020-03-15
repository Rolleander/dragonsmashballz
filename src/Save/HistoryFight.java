package Save;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryFight implements Serializable {
	
	public ArrayList<Integer> fighter=new ArrayList<Integer>();
	public ArrayList<Integer> healthproz=new ArrayList<Integer>();
	public ArrayList<Integer> team=new ArrayList<Integer>();
	public ArrayList<Integer> controll=new ArrayList<Integer>();
	public int map;
	public String winner;
	public String mode;
	public String date;
}
