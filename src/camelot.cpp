/*
ID:scv1191
PROG:camelot
LANG:C++
*/

#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

const int kingMove[9][2] = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1},
                        {1, -1}, {1, 0}, {1, 1}, {-2, -2} };
const int knightMove[8][2] = { {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
                        {1, -2}, {2, -1}, {1, 2}, {2, 1} };

int dis[31][31][31][31], knightx[1000], knighty[1000], kingx, kingy, ans, r, c, tot;
int best = ~0U>>1, inf = 0x7f7f7f7f;

bool inMap(int x, int y)
{
    if (x < 1 || x > r) return false;
    if (y < 1 || y > c) return false;
    return true;
}

void init()
{
    int xx, yy, xxx, yyy;
    int dist[31][31], head, tail, x[1000]={0}, y[1000]={0};
    bool hash[31][31];
    for (int i = 1;i <= r;++i)
    for (int j = 1;j <= c;++j)
    {
        memset(dist, 127, sizeof(dist));
        memset(hash, 0, sizeof(hash));
        dist[i][j] = 0;
        head = 0, tail = 1;
        x[1] = i, y[1] = j;
        while (head < tail)
        {
            xx = x[++head], yy = y[head];
            hash[xx][yy] = 0;
            for (int k = 0;k < 8;++k)
            {
                xxx = xx+knightMove[k][0], yyy = yy+knightMove[k][1];
                if (inMap(xxx, yyy))
                {
                    if (dist[xx][yy]+1 < dist[xxx][yyy])
                    {
                        dist[xxx][yyy] = dist[xx][yy]+1;
                        if (!hash[xxx][yyy])
                        {
                            hash[xxx][yyy] = 1;
                            x[++tail] = xxx, y[tail] = yyy;
                        }
                    }
                }
            }
        }
        for (int ii = 1;ii <= 30;++ii)
        for (int jj = 1;jj <= 26;++jj)
        dis[i][j][ii][jj] = dist[ii][jj];
    }
}

int main()
{
    freopen("camelot.in", "r", stdin);
    freopen("camelot.out", "w", stdout);
    scanf("%d%d", &r, &c);
    init();
    char ch;
    bool flag;
    scanf(" %c%d", &ch, &kingx);
    kingy = ch - 'A' + 1;
    while (scanf(" %c%d", &ch, &knightx[tot]) == 2)
        knighty[tot++] = ch - 'A' + 1;
    for (int i = 1;i <= r;++i)
    for (int j = 1;j <= c;++j)
    {
        ans = 0;
        flag = false;
        for (int k = 0;k < tot;++k)
        {
            if (dis[knightx[k]][knighty[k]][i][j] == inf) flag = true;
            if (flag) break;
            ans += dis[knightx[k]][knighty[k]][i][j];
        }
        if (flag) continue;
        int kingstep = max(abs(kingx-i), abs(kingy-j));
        int tmp = ans;
        ans += kingstep;
        best = min(best, ans);
        for (int k = 0;k < tot;++k)
        {
            flag = false;
            int x = knightx[k], y = knighty[k];
            if (dis[x][y][kingx][kingy] == inf) flag = true;
            if (dis[kingx][kingy][i][j] == inf) flag = true;
            if (flag) continue;
            best = min(best, tmp-dis[x][y][i][j]+dis[x][y][kingx][kingy]+dis[kingx][kingy][i][j]);
            for (int l = 0;l < 9;++l)
            {
                flag = false;
                int xx = kingx+kingMove[l][0], yy = kingy+kingMove[l][1];
                if (inMap(xx, yy))
                {
                    if (dis[x][y][xx][yy] == inf) flag = true;
                    if (dis[xx][yy][i][j] == inf) flag = true;
                    if (flag) continue;
                    if (l == 8)
                    best = min(best, tmp-dis[x][y][i][j]+dis[x][y][xx][yy]+dis[xx][yy][i][j]+2);
                    else
                    best = min(best, tmp-dis[x][y][i][j]+dis[x][y][xx][yy]+dis[xx][yy][i][j]+1);
                }
            }
        }
    }
    printf("%d\n", best);
}
