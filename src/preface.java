/*
ID: scv1191
LANG: JAVA
TASK: preface
*/

import java.io.*;
import java.util.StringTokenizer;

public class preface  {
    static char one[] = new char[]{'I','X','C','M'};
    static char five[] = new char[] {'V','L','D'};
    static int dp[][] = new int[10][3];
    public static void main(String args[]) throws IOException{
//        BufferedReader f = new BufferedReader(new FileReader("preface.in"));
        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        init();
        int[] c1 = new int[one.length];
        int[] c5 = new int[five.length];

        int idx = 0;
        for(int radix = 1; radix <= 1000; radix *= 10){
            int base = (N+1)/radix/10;
            int left = (N+1)%(radix*10);
            int m = left/radix;
            int l = left%radix;
            for(int i = 1 ; i <= 9 ; i ++){
                int count = base * radix;
                if(i<m)
                    count += radix;
                if(i==m)
                    count += l;
                c1[idx]+=dp[i][0]*count;
                if(idx < 3){
                    c5[idx]+=dp[i][1]*count;
                    c1[idx+1]+=dp[i][2]*count;
                }
            }
            idx ++;
        }


        for(idx = 6 ; idx >=0; idx --){
            int k = idx/2;
            if(idx%2==0){
                if(c1[k] > 0)
                    break;
            }
            else{
                if(c5[k]>0)
                    break;
            }
        }

        for(int i = 0 ; i <= idx; i++){
            int k = i/2;
            if(i%2==0){
                out.println(one[k]+" "+c1[k]);
            }
            else{
                out.println(five[k]+" "+c5[k]);
            }
        }
        out.close();
        System.exit(0);
    }

    static void init(){
        for(int i = 1; i < 10 ; i ++){
            if(i == 4){
                dp[i][0] = 1;
                dp[i][1]  = 1;
            }
            else if(i == 9){
                dp[i][0] = 1;
                dp[i][2] = 1;

            }else{
            int rm = i%5;
            int tmp = i/5;
                dp[i][0] = rm;
                dp[i][1] = tmp;
            }
        }

    }
}
