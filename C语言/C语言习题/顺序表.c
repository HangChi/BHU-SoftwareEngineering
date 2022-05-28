#include<stdio.h>
#include<stdlib.h>
#define INITSIZE 100	//顺序表存储空间的初始分配量 
typedef int ElemType;
//顺序表的 类型定义 
typedef struct
{
	ElemType *data;		//存储空间基地址 
	int length;			//顺序表长度 
	int listsize;		//当前存储空间容量 
}sqlist;


//初始化操作（创建一个空的顺序表L） 
void initlist(sqlist *L)
{
	L->data = (ElemType *)malloc(sizeof(ElemType) * INITSIZE);	//申请存储空间 
	L->length = 0;						//初始长度为0 
	L->listsize = INITSIZE;				//容量为初始量 
} 

//求表长操作
int getlen(sqlist *L)
{
	return (L->length);
} 

//取元素操作（取出顺序表L第i个数据元素的值）
int  getelem(sqlist *L,int i,ElemType *e)
{
	if(i < 1||i > L->length)		//参数不合理，取元素失败返回0 
		return 0;
	*e = L->data[i-1];
	return 1;						//取元素成功，返回1 
}

//元素定位操作（查找第一个与 x 值相等的数据元素的位置） 
/*
	算法思路：从顺序表L的第1个元素开始，逐个进行给定值x和
数据元素的比较，若某个数据元素的值和给定值x相等，则返回该
数据元素的位序；若直到最后一个数据元素，其值和x值都不相等，
则返回0. 
*/ 
int locate(sqlist *L,ElemType x)
{
	int i = 0;					//置初始下标值为0 
	while(i < L->data)
		if(L->data[i] == x) 
			return i+1;
		else
			i++;
		return 0;
} 

//插入操作（在第i个位置上插入一个值为x的元素）
/*
	算法思路：在长度为n的顺序表中插入一个数据元素时，首先确定
插入位置i的合理性，从最后一个元素开始向前遍历到第i个位置，分别
将他们都向后移动一个位置，将要插入的元素放入表中，表长加1. 
*/
int insert(sqlist *L,int i,ElemType x)
{
	int j;
	if(i < 1||i > L->length+1)
		return 0;
	if(L->length == L->listsize)
	{
		L->data = (ElemType *)realloc(L->data,(L->listsize+1)*sizeof(ElemType));
		L->listsize++;
	}
	for(j = L->length-1;j >= i-1;j--)
		L->data[j+1] = L->data[j];
	L->data[i-1] = x;
	L->length++;
	return 1;
}

//删除操作（删除第i个元素） 
/*
	算法思路:在长度为n的顺序表中删除一个数据元素时，
首先确定删除位置的i的合理性(1 <= i <= n)。若参数i合理，
则从位序为i+1的数据元素开始，把位序为i+1,i+2,...,n的
数据元素依次移到位序为i,i+1,...,n-1的位置上即可。 
*/
 int dele(sqlist *L,int i,ElemType *e)
 {
 	int j;
 	if(i < 1||i>L->length)
 		return 0;
	*e = L->data[i-1];
	for(j = i;j < L->length;j++)
		L->data[j-1] = L->data[j];
	L->length--;
	return 1;
 } 
 
 //输出操作
 void list(sqlist *L)
 {
 	int i;
 	for(i = 0;i < L->length-1;i++)
 		printf("%d\t",L->data[i]);
	printf("\n");
 } 
