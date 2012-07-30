/*
ID: scv1191
LANG: JAVA
TASK: camelot
*/

import java.io.*;
import java.util.*;

public class camelot {
    static int h;
    static int w;
    static int kx;
    static int ky;
    static int step[][][][];
//    static int dis[][][][];

    static List<int[]> knights;

    static int to[] = new int[]{1,2,1,-2,-1,2,-1,-2,2,1,2,-1,-2,1,-2,-1};
    static int kk[]  = new int[]{0,0,1,0,-1,0,0,1,0,-1,1,1,1,-1,-1,1,-1,-1};


    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        kx = (int)(st.nextToken().charAt(0)-'A');
        ky = Integer.parseInt(st.nextToken())-1;
        step = new int[w][h][w][h];
//        dis  = new int[w][h][w][h];

        knights = new ArrayList<int[]>();

        String line;
        while((line = f.readLine())!=null){
            st = new StringTokenizer(line);
            while(st.hasMoreTokens()){
                int x = (int)(st.nextToken().charAt(0)-'A');
                int y = Integer.parseInt(st.nextToken())-1;
                knights.add(new int[]{x,y});
            }
        }

        out.println(solve());
        out.close();
        System.exit(0);
    }

    static int solve(){
        for(int[] cor:knights)
                sp(cor[0],cor[1]);
        for(int i = 0 ; i < 9 ; i ++){
            int tkx = kx + kk[i*2];
            int tky = ky + kk[i*2+1];
            if(tkx < 0 || tkx >= w || tky <0 || tky >= h)
                continue;
            sp(tkx,tky);
        }

        if(knights.size() == 0)
            return 0;

        int min = Integer.MAX_VALUE;
        for(int k = 0 ; k < 9; k ++)
        for(int x = 0 ; x < w; x ++)
            for(int y = 0 ; y < h ; y ++){

                int tkx = kx + kk[k*2];
                int tky = ky + kk[k*2+1];
                if(tkx < 0 || tkx >= w || tky <0 || tky >= h)
                    continue;
                for(int pick = 0; pick <knights.size() ; pick++){
                    int st = 0;
                    if(k != 0)
                        st = 1;
                    boolean valid = true;
                    for(int i = 0 ; i < knights.size() ; i ++){
                        int[] cor = knights.get(i);
                        if(i == pick){
                            int s = step[cor[0]][cor[1]][tkx][tky];
                            int s1 = step[tkx][tky][x][y];
                            if(s == -1 || s1 == -1){
                                valid = false;
                                break;
                            }
                            st += s;
                            st += s1;
                            continue;
                        }


                        int s = step[cor[0]][cor[1]][x][y];
                        if(s == -1)
                        {
                            valid = false;
                            break;
                        }
                        st += s;
                    }
                    if(!valid)
                        continue;
                    if(st < min)
                        min = st;
                }
            }

        return min;

    }

    static void sp(int x, int y){
        for(int i = 0 ; i < w; i ++)
            Arrays.fill(step[x][y][i],-1);
        Queue<int[]> q = new LinkedList<int[]>();
        step[x][y][x][y] = 0;
//        dis[x][y][x][y]  = Math.max(Math.abs(x-kx),Math.abs(y-ky));
        q.add(new int[]{x,y});
        while(q.size()>0){
            int cor[] = q.poll();
            for(int i = 0 ; i < 8 ; i ++){
                int nx = cor[0] + to[i*2];
                int ny = cor[1] + to[i*2+1];
                if(nx < 0 || nx >= w || ny < 0 || ny >= h || step[x][y][nx][ny] >= 0)
                    continue;
                int dd = Math.max(Math.abs(nx-kx),Math.abs(ny-ky));
//                dis[x][y][nx][ny] = Math.min(dd,dis[x][y][cor[0]][cor[1]]);
                step[x][y][nx][ny] = step[x][y][cor[0]][cor[1]]+1;
                q.add(new int[]{nx,ny});
            }
        }
    }
}
