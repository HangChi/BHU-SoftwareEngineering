#include<stdio.h>
#include<stdlib.h>
#define MAXSIZE 100
//#define ElemType int
/* 线性表的链式存储*/
typedef int ElemType;
// 定义别名

typedef struct
{
    ElemType *data;			//存储空间基址
    int len;				//长度
    int listsize;			//当前分配的存储容量（以sizeof(ElemType)为单位）
}sqlist;
// 定义结构体

void initlist(sqlist *L)
{
    L->data = (ElemType *)malloc(sizeof(ElemType)*MAXSIZE);
    L->len = 0;
    L->listsize = MAXSIZE;
	//初始存储容量
}
// 初始化顺序表

int getlen(sqlist *L)
{
    return(L->len);
}

ElemType getelem(sqlist *L,int i)
{
    if(i >= 1 && i <= L->len)
        return (L->data[i-1]);
}
// 获取元素

int locate(sqlist *L,ElemType x)
{
    int i = 0;
    while(i < L->len && L->data[i] != x)
        i ++;
    if(i >= L->len)
        return -1;
    else
        return (i + 1);
}
// 查找定位元素

/*思路：
  从顺序表L第1个元素开始，逐个进行给定值x和数据元素值的比较，
  若某个数据元素的值和给定值x相等，则返回该数据元素的位序；
  若直到最后一个数据元素，其值和x都不相等，返回-1.
*/

int insert(sqlist *L, ElemType x, int i)
{
    int j;
    if(i < 1 || i > L->len+1)
        return 0;
    if(L->len == L->listsize)
        {
			L->data = (ElemType *)realloc(L->data, (L->listsize+1)*sizeof(ElemType));
			L->listsize++;
		}
    for(j = L->len-1; j >= i-1; j--)
        L->data[j+1] = L->data[j];
    L->data[i-1] = x;
    L->len++;
    return 1;
}
// 插入元素（在顺序表L中第i个位置插入一个值为x的数据元素）
/*思路：
  在长度为n的顺序表中插入一个数据元素时，首先确定插入位置i的合理性，
  从最后一个元素开始向前遍历到第i个位置，分别将他们都向后移动一个位置，
  将要插入的元素放入表中，表长加1.
*/

int del(sqlist *L, int i)
{
    int j;
    if(i < 1 || i > L->len)
        return 0;
    else
    {
        for(j = i; j <= L->len - 1; j++)
            L->data[j-1] = L->data[j];

        L->len--;
        return i;
    }
}
// 删除元素（将顺序表第i个元素删除）
/*

*/

void print(sqlist *L)
{
    int i;
    for(i = 1; i <= L->len; i++)
        printf("%d\t", L->data[i-1]);
    printf("\n");
}
// 打印顺序表

void move(sqlist *L)
{
    int i = 0, j = L->len-1;
    ElemType temp;
    while(i < j)
    {
        while(L->data[i] % 2 == 1 && i <= L->len-1)
            i++;
        while(L->data[j] % 2 == 0 && j >= 0)
            j--;
        if(i < j)
        {
            temp = L->data[i];
            L->data[i] = L->data[j];
            L->data[j] = temp;
        }
    }
}

void merge(sqlist *L1, sqlist *L2, sqlist *L)
{
    int i = 0, j = 0, k = 1;
    while(i < L1->len && j < L2->len)
        if(L1->data[i] < L2->data[i])
            {
                insert(L, L1->data[i], k);
                k++;
                i++;
            }
        else
            {
                insert(L, L2->data[j], k);
                k++;
                j++;
            }
    
    while(i < L1->len)
    {
        insert(L, L1->data[i], k);
        k++;
        i++;
    }
    
    while(j < L2->len)
    {
        insert(L, L2->data[j], k);
        k++;
        j++;
    }
}


void a_b(sqlist *L1, sqlist *L2, sqlist *L3)    //习题2-4-1
{	
    int i = 0, j, k = 0;
    while(i < L1->len)
    {
        j = 0;
        while(j < L2->len && L1->data[i] != L2->data[j])
            j++;
        if(L1->data[i] == L2->data[j])
            L3->data[k++] = L1->data[i];
        i++;
    }
    L3->len = k;
}


void delx_to_y(sqlist *L, ElemType x, ElemType y) //习题2—4—4
{
    int k = 0, i = 0;
    while(i < L->len)
    {
        if(L->data[i] >= x && L->data[i] <= y)
            k++;
        else
            L->data[i-k] = L->data[i];
        i++;
    }
    L->len -= k;
}
// 删除x到y的元素

main()
{
    int i, x;
    sqlist L, L1, L2;
    initlist(&L1);				// 初始化L1
    for(i = 1; i <= 5; i++)
    {
        scanf("%d", &x);
        insert(&L1, x, i);
    }
    print(&L1);

    initlist(&L2);
    for(i = 1; i <= 4; i++)
    {
        scanf("%d", &x);
        insert(&L2, x, i);
    }
    print(&L2);

    initlist(&L);
    merge(&L1, &L2, &L);
    print(&L);

    del(&L, 2);
    print(&L);

    delx_to_y(&L, 2, 5);
    print(&L);
}