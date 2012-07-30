/*
ID: scv1191
LANG: JAVA
TASK: fence9
*/

import java.io.*;
import java.util.StringTokenizer;

public class fence9 {
    static int n, m, p;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        int ret = 0;

        if(n < p){
            ret = count(n,m) + count(p - n, m) + (m - 1);
        }
        else if(n == p){
            ret = count(n, m);
        }
        else{
            ret = count(n, m ) - count(n-p, m) - gcd(n-p,m) + 1;
        }

        out.println(ret);
        out.close();
        System.exit(0);
    }

    static int count(int h, int w){
        int ret = (h + 1) * ( w + 1 );
        ret -= (h + w) * 2;
        ret -= gcd(h, w) + 1 - 2;
        return ret/2;
    }

    static int gcd( int x, int y){
        return x == 0 ? y : gcd(y%x, x);
    }
}
