package isptec.utils;
import java.util.*;

public class StringList extends ArrayList<String>
{
	
	public StringList() { }
	
	public StringList(StringList v) { super(v); }
	
	public StringList remove(String item , StringList list )
	{
		StringList newList = new StringList(this);
		return newList.removeInside(item, list);
		
	}
	
	public StringList remove(StringList list )
	{
		StringList newList = new StringList(this);
		return newList.removeInside(list);
		
	}
	
	public StringList removeInside(StringList list )
	{
		int sz = list.size();
		String st;
		for (int i = 0; i < sz; i++)
		{
			st = (String) list.get(i);
			if (st.trim().equals("") != true)
				remove(st);
		}
		return this;
	}
	
	public StringList removeInside(String item , StringList list )
	{
		if (item.trim().equals("") != true)
			remove(item);
		removeInside(list );
		return this;
	}
	
	public void sort()
	{
		Collections.sort(this , new CompareStrings());
	}

	private class CompareStrings implements Comparator
	{
        @Override
		public int compare(Object o1, Object o2)
		{
			String s1 = (String) o1;
			String s2 = (String) o2;
			return s1.compareToIgnoreCase(s2);
		}
	}
	
}
