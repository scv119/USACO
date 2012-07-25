/*
ID: scv1191
LANG: JAVA
TASK: kimbits
*/

import java.io.*;
import java.util.StringTokenizer;

public class kimbits {
    static int N;
    static long I;
    static int L;
    static long c[][];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        init();
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        I = Long.parseLong(st.nextToken());
        out.println(solve("",N,L,I));
        out.close();
        System.exit(0);
    }

    static void init(){
        c = new long[33][33];
        for(int i = 1 ; i <33; i ++) {
            c[i][0] = 1;
            c[i][1] = i;
        }
        for(int i = 2; i < 33; i ++){
            for(int k = 1; k <= i ;k++){
                c[i][k] = c[i-1][k]+c[i-1][k-1];
            }
        }
    }

    static String solve(String pre,int len,int bitcount, long size){
        if(len == 1) {
            if(size == 1)
                return pre+"0";
            return pre+"1";
        }
        long sub_size = count(len-1,bitcount);
        if(sub_size >= size)
            return solve(pre+"0",len-1,bitcount,size);
        return solve(pre+"1",len-1,bitcount-1,size-sub_size);
    }

    static long count(int len, int bitcount){
        if(len == 0)
            return 0;
        bitcount = Math.min(bitcount,len);
        long result = 0;
        for(int i = 0 ; i <= bitcount; i ++){
            result += c[len][i];
        }
        return result;
    }


}
