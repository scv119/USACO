/*
ID: scv1191
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cowtour {
    static int N;
    static int p[][];
    static double g[][];
    static double d[][];
    static double dm[];



    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        p = new int[N][2];
        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());
        }
        g = new double[N][N];
        d = new double[N][N];

        for(int i = 0 ; i < N ; i ++)
            Arrays.fill(d[i],-1);

        for(int i = 0 ; i <  N ; i ++){
            String s= f.readLine();
            for(int j = 0 ; j < i; j ++){
                char c = s.charAt(j);
                g[i][j] = g[j][i] = (double)Math.sqrt((p[i][0]-p[j][0]) * (p[i][0]-p[j][0]) + (p[i][1]-p[j][1]) * (p[i][1]-p[j][1]));
                if(c == '1'){
                    d[i][j] = d[j][i] = g[i][j];
                }
            }
        }

        floyd();

        dm = new double[N];
        for(int i=0 ; i < N; i ++){
            double max = 0;
            for(int j = 0 ; j < N ; j++){
                if(j == i)
                    continue;
                if(d[i][j] > max)
                    max = d[i][j];
            }
            dm[i]=max;
        }

        double max = 0;
        double min = -1;
        for(int i = 0 ; i<N; i ++)
            for(int j = 0 ; j < i ; j++) {
                if(d[i][j] > 0 && d[i][j] > max)
                    max = d[i][j];
                if(d[i][j]<0){
                    double value = dm[i]+dm[j]+g[i][j];
                    if(min==-1 || value < min) {
                        min = value;
                    }
                }
            }

        out.format("%.6f%n",Math.max(min,max));
        out.close();
        System.exit(0);
    }

    static void floyd(){
        for(int m = 0 ; m < N ; m ++)
            for(int i = 0 ; i < N ; i ++)
                if(i!=m)
                    for(int j = 0 ; j < N; j ++)
                        if(j!=m && j!=i){
                            if(d[i][m]>=0 && d[j][m]>=0 && (d[i][j]<0 || d[i][j] > d[i][m] + d[j][m]))
                                d[j][i] = d[i][j] = d[i][m] + d[m][j];
                        }
    }
}
