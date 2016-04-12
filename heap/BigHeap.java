package tooffer;

import java.util.ArrayList;
import java.util.Arrays;

public class BigHeap {
	
	
	
	/*
	 *�������е�����Ԫ�� 
	 */
	private void swap(ArrayList<Integer> heapList,int srcIndex,int dstIndex)
	{
		int tmp = heapList.get(srcIndex);
		
		heapList.set(srcIndex,heapList.get(dstIndex));
		heapList.set(dstIndex, tmp);
		
	}
	
	/*
	 *��ָ��Ԫ�ص�λ�ý������Ʋ��� 
	 */
	private void HeapUp(ArrayList<Integer> heapList,int index)
	{
				
		if(index > 1)
		{
			int parent = index / 2;
			int parentVal = heapList.get(parent).intValue();
			int indexVal =  heapList.get(index).intValue();
			
			if(indexVal > parentVal)
			{
				swap(heapList,parent,index);
				HeapUp(heapList,parent);
			}
			
		}
	}
	
	/*
	 *��ָ��Ԫ�ص�λ�ý������Ʋ��� 
	 */
	private void HeapDown(ArrayList<Integer> heapList,int index)
	{
		int heapSize = heapList.size(); //����������ظ��ļ���,���Խ���Ϊ�βδ��룬���߽��ú�����д�ɷǵݹ���ʽ
		
		if(index > heapSize - 1)
		{//�ڵ㲻����
			return;
		}
		
		int child = index * 2; //���ӽڵ�
		
		if(child > (heapSize - 2))
		{//��ǰ�ڵ�ΪҶ�ӽڵ㣬���ܽ������Ʋ�����ֱ�ӷ���
		 //-2���������һ��Ԫ���Ѿ���Ҫɾ���Ľڵ㣬���ڼ��㷶Χ֮��
			return;
		}
		else if(child < heapSize - 2) 
		{//���������ӽڵ�
			 if(heapList.get(child).intValue() < heapList.get(child + 1).intValue())
			 {
				 child++; //�Һ��ӽ��ֵ����Ϊ�µĸ��ڵ�
			 }
		}
		
		if(heapList.get(child).intValue() > heapList.get(index).intValue())
		{//���ӽڵ��ֵ�󣬽�������
			swap(heapList,child, index);
			HeapDown(heapList,child);//�����������Ʋ���
		}
		
	}
	
	/*
	 *��󶥶��в���һ��Ԫ��
	 */
	public void HeapInsert(ArrayList<Integer> heapList,int value)
	{
		int heapSize = heapList.size();
		
		if(heapSize == 0)
		{//��һ��Ԫ�ز�Ϊ���е�Ԫ�أ�����
			heapList.add(-100);
		}
		
		heapList.add(value);
		heapSize++; //�����Ԫ�غ󣬸ı�ѵĴ�С
		
		HeapUp(heapList,heapSize - 1);
	}
	
	/*
	 *�Ӵ󶥶���ɾ��һ��Ԫ�� 
	 */
	public void HeapDelete(ArrayList<Integer> heapList,int value)
	{
		int index = 1,heapSize = heapList.size();
		for(; index < heapSize; index++)
		{
			if(heapList.get(index).intValue() == value)
			{
				break;
			}
		}
		
		if (index >= heapSize)
		{//Ԫ�ز�����
			return;
		}
		
		heapList.set(index, heapList.get(heapSize-1)); //�����һ��Ҷ�ӽڵ�ֵ��ֵ����ǰ�ڵ�
		HeapDown(heapList,index); 
		
		int parent = index / 2;
		
		if(parent > 0 && ( heapList.get(index).intValue() > (Integer)heapList.get(parent).intValue() ))
		{//������Ʋ������Ԫ�ش��ڸ��ڵ㻹Ҫ��������
			HeapUp(heapList,index);
		}
		
		heapList.remove(heapSize - 1);
	}
	
	/*
	 *�����ѽṹ	
	 */
	public void heapAdjust(ArrayList<Integer>heapList,int index,int end)
	{
		int child = -1,heapSize = heapList.size();
		
		end = end > heapSize - 1 ? heapSize - 1 : end; //ȷ��������ȷ
		
		while(index <= end / 2)
		{//ֻ�������Ҷ�ӽڵ�
			child = index * 2; //���ӽڵ�
			if(child + 1 <= end && (heapList.get(child+1).intValue() > heapList.get(child).intValue()) )
			{
				child += 1; //�Һ��ӽڵ�ֵ���Һ��ӽڵ�����
			}
			
			if(heapList.get(child).intValue() > heapList.get(index).intValue())
			{
				swap(heapList, child, index);
				index = child;
			}
			else
			{
				break;
			}
		}
	}
	
	
	/*
	 * ��ʼ��ʱ�����ѽṹ
	 * ���ڶ�����ȫ�������ṹ������ֻ��ǰһ��ڵ��Ƿ�Ҷ�ӽڵ�
	 */
	public void heapInitAdjust(ArrayList<Integer> heapList)
	{
		int heapSize = heapList.size();
		
		for(int i = heapSize / 2; i > 0; i--)
		{
			heapAdjust(heapList, i, heapSize - 1);
		}	
		
	}
	
	/*
	 * ������
	 * ���󶥶ѶѶ�Ԫ�غ����һ��Ԫ�ؽ���������ʣ��Ԫ�صĶԽṹ
	 * ֱ�����������һ��Ԫ�ؾ��ź���
	 */
	public void heapSort(ArrayList<Integer>heapList)
	{
		int heapSize = heapList.size();
		
		for(int i = heapSize - 1; i > 1; i--)
		{
			swap(heapList, 1, i); //�����Ѷ������һ��Ԫ��
			heapAdjust(heapList, 1, i - 1);
		}
	}
	
	/*
	 *  ��ӡ��Ԫ��
	 */
	public void PrintHeap(ArrayList<Integer> heapList)
	{
		for(int i = 1; i < heapList.size(); i++)
		{
			System.out.print(heapList.get(i) + " ");
		}
		
		System.out.println();
	}
	
	
	
	public static void main(String args[])
	{
		ArrayList<Integer> heapList = new ArrayList<>(Arrays.asList(null,1,3,4,5,8,2,7));
		
		BigHeap bigHeap = new BigHeap();
		
		bigHeap.heapInitAdjust(heapList);
		bigHeap.PrintHeap(heapList);
		
		bigHeap.HeapInsert(heapList, 6);
		bigHeap.PrintHeap(heapList);
		
		bigHeap.heapSort(heapList);
		bigHeap.PrintHeap(heapList);
	}
}
