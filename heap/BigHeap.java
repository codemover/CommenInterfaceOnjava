package tooffer;

import java.util.ArrayList;
import java.util.Arrays;

public class BigHeap {
	
	
	
	/*
	 *交换堆中的两个元素 
	 */
	private void swap(ArrayList<Integer> heapList,int srcIndex,int dstIndex)
	{
		int tmp = heapList.get(srcIndex);
		
		heapList.set(srcIndex,heapList.get(dstIndex));
		heapList.set(dstIndex, tmp);
		
	}
	
	/*
	 *将指定元素的位置进行上移操作 
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
	 *将指定元素的位置进行下移操作 
	 */
	private void HeapDown(ArrayList<Integer> heapList,int index)
	{
		int heapSize = heapList.size(); //这里进行了重复的计算,可以将作为形参传入，或者将该函数，写成非递归形式
		
		if(index > heapSize - 1)
		{//节点不存在
			return;
		}
		
		int child = index * 2; //左孩子节点
		
		if(child > (heapSize - 2))
		{//当前节点为叶子节点，不能进行下移操作，直接返回
		 //-2是由于最后一个元素已经是要删除的节点，不在计算范围之内
			return;
		}
		else if(child < heapSize - 2) 
		{//有两个孩子节点
			 if(heapList.get(child).intValue() < heapList.get(child + 1).intValue())
			 {
				 child++; //右孩子结点值大，作为新的父节点
			 }
		}
		
		if(heapList.get(child).intValue() > heapList.get(index).intValue())
		{//孩子节点的值大，进行下移
			swap(heapList,child, index);
			HeapDown(heapList,child);//继续进行下移操作
		}
		
	}
	
	/*
	 *向大顶堆中插入一个元素
	 */
	public void HeapInsert(ArrayList<Integer> heapList,int value)
	{
		int heapSize = heapList.size();
		
		if(heapSize == 0)
		{//第一个元素不为堆中的元素，跳过
			heapList.add(-100);
		}
		
		heapList.add(value);
		heapSize++; //添加新元素后，改变堆的大小
		
		HeapUp(heapList,heapSize - 1);
	}
	
	/*
	 *从大顶堆中删除一个元素 
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
		{//元素不存在
			return;
		}
		
		heapList.set(index, heapList.get(heapSize-1)); //将最后一个叶子节点值赋值到当前节点
		HeapDown(heapList,index); 
		
		int parent = index / 2;
		
		if(parent > 0 && ( heapList.get(index).intValue() > (Integer)heapList.get(parent).intValue() ))
		{//如果下移操作后该元素大于父节点还要进行上移
			HeapUp(heapList,index);
		}
		
		heapList.remove(heapSize - 1);
	}
	
	/*
	 *调整堆结构	
	 */
	public void heapAdjust(ArrayList<Integer>heapList,int index,int end)
	{
		int child = -1,heapSize = heapList.size();
		
		end = end > heapSize - 1 ? heapSize - 1 : end; //确保结束正确
		
		while(index <= end / 2)
		{//只需调整非叶子节点
			child = index * 2; //左孩子节点
			if(child + 1 <= end && (heapList.get(child+1).intValue() > heapList.get(child).intValue()) )
			{
				child += 1; //右孩子节点值大右孩子节点上移
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
	 * 初始化时调整堆结构
	 * 由于堆是完全二叉树结构，所以只有前一半节点是非叶子节点
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
	 * 堆排序
	 * 将大顶堆堆顶元素和最后一个元素交换，调整剩下元素的对结构
	 * 直到调整到最后一个元素就排好序
	 */
	public void heapSort(ArrayList<Integer>heapList)
	{
		int heapSize = heapList.size();
		
		for(int i = heapSize - 1; i > 1; i--)
		{
			swap(heapList, 1, i); //交换堆顶和最后一个元素
			heapAdjust(heapList, 1, i - 1);
		}
	}
	
	/*
	 *  打印堆元素
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
