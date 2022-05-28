#include<stdio.h>
#include<stdlib.h>
#define MAXSIZE 100
//#define ElemType int
/* 线性表的链式存储*/
typedef int ElemType;
typedef struct
{
    ElemType *data;
    int len;
    int listsize;
}sqlist;

int insert(sqlist *L, ElemType x, int i)
{
    int j;
    if(i < 1 || i > L->len+1)
        return 0;
    if(L->len == L->listsize)
        L->data = (ElemType *)realloc(L->data, (L->listsize+1)*sizeof(ElemType));
    for(j = L->len-1; j >= i-1; j--)
        L->data[j+1] = L->data[j];
    L->data[i-1] = x;
    L->len++;
    return 1;
}

void initlist(sqlist *L)
{
    L->data = (ElemType *)malloc(sizeof(ElemType)*MAXSIZE);
    L->len = 0;
    L->listsize = MAXSIZE;
}
void print(sqlist *L)
{
    int i;
    for(i = 1; i <= L->len; i++)
        printf("%d\t", L->data[i-1]);
    printf("\n");
}

void delxtoy(sqlist *L, ElemType x, ElemType y) //习题2—4—4
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

main(){
    int i, x;
    sqlist L, L1, L2;
    initlist(&L);
    for(i = 1; i <= 5; i++)
    {
        scanf("%d", &x);
        insert(&L, x, i);
    }
    print(&L);

    delxtoy(&L, 2, 5);
    print(&L);
}
