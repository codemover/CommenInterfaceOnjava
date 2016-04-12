package commeninterface;

public class KmpMatch {
	
	public int[] getNext(String str)
	{
		if(str == null || str.length() == 0)
		{
			return null;
		}
		
		int strLen = str.length();
		int next[] = new int[strLen+1]; //next数组表示，长度为i的字符串最长公共前后缀的长度为next[i]，所以需要多一个空间
		next[0] = next[1] = 0;
		
		int j;
		for(int i = 1; i < strLen; i++)
		{
			j = next[i];
			
			if(str.charAt(i) == str.charAt(j))
			{
				next[i+1] = next[i] + 1; //直接找到不用计算
			}
			else
			{//继续寻找next[next[i]]
				while(j > 0 && str.charAt(i) != str.charAt(j))
				{
					j = next[j]; //递归查找next[]，直到找到字符相等或next[1];
				}
				
				if(str.charAt(i) == str.charAt(j))
				{
					next[i+1] = next[j] + 1; //next
				}
				else
				{
					next[i+1] = 0;
				}
			}
		}
		
		return next;
	}
	
	
	public boolean findsubString(String originStr,String findStr,int next[])
	{	
		if(originStr == null || findStr == null || originStr.length() == 0 || findStr.length() == 0)
		{
			return false;
		}
	
		int matchLen = 0; //上一次已匹配的长度
		for(int i = 0; i < originStr.length(); i++)
		{
			if(originStr.charAt(i) == findStr.charAt(matchLen))
			{
				matchLen++;
				if(matchLen == findStr.length())
				{//找到子串
					return true;
				}
			}
			else
			{//通过next数组计算出findStr跳转的位置
				while(matchLen > 0 && originStr.charAt(i) != findStr.charAt(matchLen))
				{
					matchLen = next[matchLen];
				}
			}
		}
		
		return false;
	}
}
