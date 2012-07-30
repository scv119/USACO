/*
ID: scv1191
LANG: C++
TASK: game1
*/
#include<stdio.h>
FILE* fin=fopen("game1.in","r");
FILE* fout=fopen("game1.out","w");
int n,board[105],sum[105][105],score[105][105],total;
int min(int a, int b)
{
    return a<b?a:b;
}
int main()
{
    int n;
    fscanf(fin,"%d",&n);
    total=0;
    for(int i=0;i<n;i++)
    {
        fscanf(fin,"%d",&board[i]);
        total+=board[i];
        sum[i][i]=score[i][i]=board[i];
    }
    for(int i=0;i<n;i++)
    {
        for(int j=i+1;j<n;j++)
        {
            sum[i][j]=sum[i][j-1]+board[j];
        }
    }
    for(int l=1;l<n;l++)
    {
        for(int i=0;i<n-l;i++)
        {
            score[i][i+l]=sum[i][i+l]-min(score[i][i+l-1],score[i+1][i+l]);
        }
    }
    fprintf(fout,"%d %d\n",score[0][n-1],total-score[0][n-1]);
    return 0;
}