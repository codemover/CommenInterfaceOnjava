package commeninterface;

public class KmpMatch {
	
	public int[] getNext(String str)
	{
		if(str == null || str.length() == 0)
		{
			return null;
		}
		
		int strLen = str.length();
		int next[] = new int[strLen+1]; //next�����ʾ������Ϊi���ַ��������ǰ��׺�ĳ���Ϊnext[i]��������Ҫ��һ���ռ�
		next[0] = next[1] = 0;
		
		int j;
		for(int i = 1; i < strLen; i++)
		{
			j = next[i];
			
			if(str.charAt(i) == str.charAt(j))
			{
				next[i+1] = next[i] + 1; //ֱ���ҵ����ü���
			}
			else
			{//����Ѱ��next[next[i]]
				while(j > 0 && str.charAt(i) != str.charAt(j))
				{
					j = next[j]; //�ݹ����next[]��ֱ���ҵ��ַ���Ȼ�next[1];
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
	
		int matchLen = 0; //��һ����ƥ��ĳ���
		for(int i = 0; i < originStr.length(); i++)
		{
			if(originStr.charAt(i) == findStr.charAt(matchLen))
			{
				matchLen++;
				if(matchLen == findStr.length())
				{//�ҵ��Ӵ�
					return true;
				}
			}
			else
			{//ͨ��next��������findStr��ת��λ��
				while(matchLen > 0 && originStr.charAt(i) != findStr.charAt(matchLen))
				{
					matchLen = next[matchLen];
				}
			}
		}
		
		return false;
	}
}
