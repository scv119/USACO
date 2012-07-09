/*
ID: scv1191
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.StringTokenizer;

public class friday {
    static int[] len = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int years = Integer.parseInt(st.nextToken());
        int[] result = solve(years);
        for(int i = 0 ; i < result.length; i ++){
            if(i == 0)
                out.print(result[i]);
            else
                out.print(" "+result[i]);
        }
        out.println();
        out.close();
        System.exit(0);
    }

    static int[] solve(int N){
        int[] results = new int[7];
        int next = 2;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 1; j <= 12; j ++){
                int days =  daysOfMonth(i,j);
                int value = (12+next)%7;
                results[value] ++;
                next =  (days + next)%7;
            }
        }
        return results;
    }

    static int daysOfMonth(int N, int m){
        if(isLeap(N) && m == 2)
            return 29;
        return len[m];
    }

    static boolean isLeap(int N){
        int year = N + 1900;
        if(year%100==0){
            if(year%400==0)
                return true;
        }
        else{
            if(year%4 ==0)
                return true;
        }
        return false;
    }
}
