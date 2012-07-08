/*
ID: scv1191
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.*;

public class castle {
    static int M;
    static int N;
    static int len;
    static int g[][];
    static int comp[];
    static int mask[];
    static List<int[]> list;

    static int g1[][];

    public static void main(String args[]) throws IOException{
        Date date =new Date();
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/shenchen/IdeaProjects/USACO/src/test.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        g = new int[N][M];
        len = N*M;
        mask = new int[]{-1,-M,1,M};
        list = new LinkedList<int[]>();

        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0 ; j < M ; j ++)
            {
                int value = Integer.parseInt(st.nextToken());
                g[i][j] = value;
            }
        }

        solve(out);
        out.close();
        Date date1 =new Date();
        System.out.println(date1.getTime()-date.getTime());
        System.exit(0);
    }

    static void solve(PrintWriter out){
        int num = 0;
        comp = new int[len];
        Arrays.fill(comp,-1);
        for(int i = 0 ; i <len ; i++){
            if(comp[i] == -1){
                dfs(i, comp, num);
                num ++;
            }
        }
        int comp_size[] = new int[num];
        out.println(num);
        int max = 0;
        for(int x:comp){
            comp_size[x]++;
        }
        for(int x:comp_size){
            if(x > max)
                max = x;
        }
        out.println(max);
//        g1 = new int[num][num];
        max = 0;
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j < M ; j ++){
                int idx = i * M + j;
                int idx1 = 0;
                if(i != 0){
                    idx1 = idx - M;
                    if(comp[idx] != comp[idx1] ){

                        if(comp_size[comp[idx]]+comp_size[comp[idx1]]>max)
                            max =  comp_size[comp[idx]]+comp_size[comp[idx1]];
                    }
                }
                if(j !=0 ){
                    idx1 = idx - 1;
                    if(comp[idx] != comp[idx1] ){

                        if(comp_size[comp[idx]]+comp_size[comp[idx1]]>max)
                            max =  comp_size[comp[idx]]+comp_size[comp[idx1]];
                    }

                }
                if(i != N-1){
                    idx1 = idx + M;
                    if(comp[idx] != comp[idx1] ){

                        if(comp_size[comp[idx]]+comp_size[comp[idx1]]>max)
                            max =  comp_size[comp[idx]]+comp_size[comp[idx1]];
                    }
                }
                if(j != M-1){
                    idx1 = idx + 1;
                    if(comp[idx] != comp[idx1] ){

                        if(comp_size[comp[idx]]+comp_size[comp[idx1]]>max)
                            max =  comp_size[comp[idx]]+comp_size[comp[idx1]];
                    }
                }
            }

        out.println(max);

        for(int i = 0; i < M ;i ++)
            for(int j = N-1; j >=0; j --){
                int idx = j*M+i;
                int idx1;
                if(j !=0 ){
                    idx1 = idx - M;
                    if(comp[idx] != comp[idx1] && comp_size[comp[idx]]+comp_size[comp[idx1]] == max){
                        out.println((j+1)+" "+(i+1)+" N");
                        return;
                    }
                }

                if(i != M-1){
                    idx1 = idx + 1;
                    if(comp[idx] != comp[idx1] && comp_size[comp[idx]]+comp_size[comp[idx1]] == max){
                        out.println((j+1)+" "+(i+1)+" E");
                        return;
                    }
                }
            }

    }

    static void dfs(int v, int arr[], int comp){
        arr[v] = comp;
        for(int i = 0 ; i <4 ;i ++){
            int m = 1<<i;
            int value = v + mask[i];
            int x = v/M;
            int y = v%M;
            if(value >=0 && value <len  && (g[x][y] & m) == 0 && arr[value] == -1)
                dfs(value, arr, comp);
        }
    }
}
