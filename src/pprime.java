/*
ID: scv1191
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.StringTokenizer;

public class pprime {
    static int a;
    static int b;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        solve(out);
        out.close();
        System.exit(0);
    }

    static boolean prime(int a){
        for(int x = 2; x*x<=a;x++){
            if(a%x==0)
                return false;
        }
        return true;
    }

    static void solve(PrintWriter out){
        int a_len = (a + "").length();
        int b_len = (b + "").length();
        for(int i = 2 ; i <= 9 ; i ++){
            if(i >= a && i <= b && prime(i)){
                out.println(i);
            }
        }
        for(int len = 2; len <= 9; len ++){
            if(len < a_len)
                continue;
            if(len > b_len)
                break;
            for(int i = 1; i <= 9 ; i ++){
                solve(i,i,1,len-2,out);
            }
        }
    }

    static void solve(int pre, int post, int len1, int len, PrintWriter out){
        int base =  (int)Math.pow(10,len1);
        if(len == 0){
            int value = pre * base + post;
            if(value >= a && value <= b && prime(value)){
                out.println(value);
            }
        }
        else if(len == 1){
            for(int i = 0 ; i <=9 ; i ++){
                int value = pre * base * 10 + i * base + post;
                if(value >= a && value <= b && prime(value)){
                    out.println(value);
                }
            }
        }
        else{
            for(int i = 0 ; i<= 9 ; i ++){
                solve(pre*10+i, i*base+post, len1 + 1, len -2, out);
            }
        }
    }

}
