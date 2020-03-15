package FighterBuild;

import java.io.Serializable;
import java.util.ArrayList;

public class Build implements Serializable {

	
	private ArrayList<Integer> item=new ArrayList<Integer>();
	
	
	public Build()
	{
		
	}
	
	public Build(int[] b)
	{
		for(int i=0; i<7; i++)
		{
			if(b[i]>0)
			{
				item.add(b[i]);
			}
			
		}
	}
	
	public int[] getBuild()
	{
	   int[] b=new int[7];
	for(int i=0; i<7; i++)
	{
	   if(i<item.size())	
	   {
		b[i]=item.get(i);
	   }
	   else{	   
		   b[i]=0;
	   }
	}
	  return b;
	}
	
	public void removeItem(int nr)
	{
		item.remove(nr);
	}
	
	public void addItem(int nr)
	{
		
		if(item.size()<7)
		{
			boolean frei=true;
			for(int i=0; i<item.size(); i++)
			{
				if(item.get(i)==nr)
				{
					frei=false;
					break;
				}
			}
			if(frei)
			{
			item.add(nr);
			}
		}
	}

	public void clear() {
		// TODO Auto-generated method stub
		 item=new ArrayList<Integer>();
	}

	public boolean hatItems() {
		
		
		if(item.size()>0)
		{
		return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
}
