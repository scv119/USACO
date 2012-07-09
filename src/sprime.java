/*
ID: scv1191
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.StringTokenizer;

public class sprime {
    static int N;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        solve(out);
        out.close();
        System.exit(0);
    }

    static boolean prime(int a){
        if(a <= 1)
            return false;
        for(int x = 2; x*x<=a;x++){
            if(a%x==0)
                return false;
        }
        return true;
    }

    static void solve(PrintWriter out){
        solve(2,1,out);
        solve(3,1,out);
        solve(5,1,out);
        solve(7,1,out);
    }
    static void solve(int pre, int len, PrintWriter out){
        if(len  == N)        {
            out.println(pre);
            return;
        }
        for(int i = 1; i<= 9 ; i +=2){
            int value = pre * 10 + i;
            if(prime(value)){
                solve(value, len +1, out);
            }
        }

    }



}
