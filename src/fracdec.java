/*
ID: scv1191
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class fracdec {
    static int N;
    static int D;
    static int dp[];
    static List<Integer> list;
    static int circle = -1;
    static int big = 0;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dp = new int[D];
        Arrays.fill(dp, -1);
        list = new ArrayList<Integer>();
        big = N/D;
        N = N%D;
        int idx = 0;
        if(N==0){
            list.add(0);
        }
        while(true){
            if(N == 0)
                break;
            if(dp[N]!=-1){
                circle = dp[N];
                break;
            }
            dp[N] = idx;
            idx ++;
            N = N * 10;
            int value = N/D;
            list.add(value);
            N = N%D;
        }
        int count = 0;
        out.print(big);
        out.print(".");
        count += (big+".").length();
        for(int i = 0 ; i < list.size() ; i++){
            if(i == circle) {
                out.print("(");
                count ++;
                if(count%76==0)
                    out.println();
            }{
                out.print(list.get(i));
                count ++;
                if(count%76==0)
                    out.println();
            }
        }
        if(circle>=0)  {
            out.print(")");
            count ++;
            if(count%76==0)
                out.println();
        }
        if(count%76!=0)
            out.println();

        out.close();
        System.exit(0);
    }
}
