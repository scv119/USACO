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


    public static void main(String args[]) throws IOException{
//        BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
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
        for(int[] arr:knights){
            sp(arr[0],arr[1]);
        }

        if(knights.size() == 0)
            return 0;

        int min = Integer.MAX_VALUE;
        for(int x = 0 ; x < w; x ++)
            for(int y = 0 ; y < h ; y ++){
                int st = 0;
                int k_s = Math.max(Math.abs(x-kx),Math.abs(y-ky));
                boolean valid = true;
                for(int[] cor:knights){
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
                st += k_s;
                if(st < min)
                    min = st;
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
