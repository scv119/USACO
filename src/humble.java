/*
ID: scv1191
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.*;

public class humble {

    static int K;
    static int N;
    static int s[];
    static int p[];
    static int list[];
    static int len;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("humble.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));


        StringTokenizer st = new StringTokenizer(f.readLine());


        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        s = new int[K];
        p = new int[K];
        Arrays.fill(p,-1);
        list = new int[100001];
        len = 0;
        list[len++] = 1;


        st = new StringTokenizer(f.readLine());
        for(int i = 0 ; i < K ;i ++){
            if(!st.hasMoreElements())
                st = new StringTokenizer(f.readLine());
            s[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(s);


        while(len<=N){
            int max = list[len-1];

            int value = Integer.MAX_VALUE;
            int pidx = -1;
            for(int i = 0 ; i < K ; i ++){
                if(p[i]==len-1)
                    continue;
                int vv = 0;
                while(true){
                    vv = list[p[i]+1] * s[i];
                    if(vv <= max)
                        p[i]++;
                    else
                        break;
                }
                if( vv < value){
                    value = vv;
                    pidx = i;
                }
            }
            list[len++]=value;
            p[pidx]++;
        }


        out.println(list[N]);
        out.close();
        System.exit(0);
    }
}
