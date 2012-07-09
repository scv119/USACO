/*
ID: scv1191
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;
import java.util.StringTokenizer;

class ariprog {
    static int N;
    static int M;
    static int[]        map;
    static int          scount;
    static int[]        arr;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        scount = 0;
        st = new StringTokenizer(f.readLine());
        M = Integer.parseInt(st.nextToken());
        map = new int[2*M*M+1];
        init();
        arr = new int[scount];
        int idx = 0;
        for(int i = 0; i < map.length ; i++)
            if(map[i] == 1)
                arr[idx++] = i;
        Arrays.sort(arr);
//        for(int i = 0 ; i < arr.length ; i ++)
//            map.put(arr[i],i);
        if(solve(out) == 0)
            out.println("NONE");

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void init()
    {
        for(int p = 0 ; p <= M ; p ++)
            for(int q = 0 ; q <= M ; q++ ){
                int value = p*p + q*q;
                if(map[value] == 0){
                    map[value] =1 ;
                    scount ++;
                }
            }

    }

    static int solve(PrintWriter out){
        int count = 0;
        int upper = (arr[arr.length-1] - arr[0])/(N-1);
        int max_value = arr[arr.length-1];
        for(int b = 1; b <= upper; b++){
            for(int a:arr){
                int max = a + b * (N-1);
                if(max > max_value)
                    continue;
                boolean in = true;
                for(int i = 1 ; i < N; i ++){
                    int value = a + b * i;
                    if(map[value]==0) {
                        in = false;
                        break;
                    }
                }
                if(in){
                count ++;
                out.println(a+" "+b);
                }

            }
        }
        return count;
    }
}