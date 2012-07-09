/*
ID: scv1191
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.util.*;


public class crypt1 {
    static int len;
    static int digits[];

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        len = Integer.parseInt(st.nextToken());
        digits = new int[len];

        st = new StringTokenizer(f.readLine());
        for(int i = 0 ; i < len; i ++){
            digits[i] = Integer.parseInt(st.nextToken());
        }
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static int solve(){
        int result = 0;
        for(int i = 0 ; i < len ; i ++){
            for(int j = 0 ; j<len; j ++ )
                for(int k = 0 ; k < len; k ++){
                    int a1 = digits[i] * 100 + digits[j] * 10 + digits[k];
                    for(int x = 0 ; x < len; x ++)
                        for(int y = 0; y < len ; y ++){
                            int a2 = digits[x];
                            int a3 = digits[y];
                            if(valid(a1,a2,a3))
                                result ++;
                        }
                }
        }
        return result;
    }

    static boolean in(int value){
        for(int i:digits)
            if(i == value)
                return true;
        return false;
    }

    static boolean valid(int a1, int a2, int a3){
        int v1 = a1*a2;

        if(v1/1000 >= 1 || v1/100 == 0)
            return false;

        if(!in(v1/100) || !in(v1%100/10) || !in(v1%10/1))
            return false;


        int v2 = a1*a3;

        if(v2/1000 >= 1 || v2/100 == 0)
            return false;

        if(!in(v2/100) || !in(v2%100/10) || !in(v2%10/1))
            return false;

        int v3 = v1 * 10 + v2;
        if(v3/10000 >= 1 || v3/1000 == 0)
            return false;

        if(!in(v3/1000) || !in(v3%1000/100) || !in(v3%100/10) || !in(v3%10/1))
            return false;

        return true;
    }
}
