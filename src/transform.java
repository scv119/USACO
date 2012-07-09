/*
ID: scv1191
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {
    public static int N = 0;
    static int src[][];
    static int des[][];
    static int result;

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        N = Integer.parseInt(st.nextToken());    // first integer
        src = new int[N][N];
        des = new int[N][N];
        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            String s= st.nextToken();
            for(int j = 0 ; j< N  ;j ++)
                if(s.charAt(j) == '@')
                src[i][j] =  1  ;
        }
        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            String s= st.nextToken();
            for(int j = 0 ; j< N  ;j ++)
                if(s.charAt(j) == '@')
                    des[i][j] =  1  ;
        }

        solve();
        out.println(result);                           // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve()
    {
        if(equal(des, r90(src))){
            result = 1;
            return;
        }
        if(equal(des, r180(src))){
            result = 2;
            return;
        }
        if(equal(des, r270(src))){
            result = 3;
            return;
        }
        if(equal(des, ref(src))){
            result = 4;
            return;
        }
        int[][] tmp = href(src);
        if(equal(des, tmp) || equal(des, r90(tmp)) || equal(des, r180(tmp)) || equal(des, r270(tmp))){
            result = 5;
            return;
        }
        if(equal(des,src)){
            result = 6;
            return;
        }
        result = 7;
    }

    static int[][] r90(int[][] src){
        int[][] result = new int[N][N];
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j <N; j ++)
                result[j][N-1-i] = src[i][j] ;
        return result;
    };

    static int[][] r180(int[][] src){
        int[][] result = new int[N][N];
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j <N; j ++)
                result[N-1-i][N-1-j] = src[i][j] ;
        return result;
    }

    static int[][] r270(int[][] src){
        int[][] result = new int[N][N];
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j <N; j ++)
                result[N-1-j][i] = src[i][j] ;
        return result;
    }

    static int[][] ref(int[][] src){
        int[][] result = new int[N][N];
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j <N; j ++)
                result[i][N-1-j] = src[i][j] ;
        return result;
    }

    static int[][] href(int[][] src){
        int[][] result = new int[N][N];
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j <N; j ++)
                result[N-i-1][j] = src[i][j] ;
        return result;
    }

    static boolean equal(int[][] src, int[][] des){
        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < N ; j ++)
                if(src[i][j] != des[i][j])
                    return false;
        return true;

    }

}