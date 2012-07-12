/*
ID: scv1191
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stamps {
    static int N;
    static int K;
    static int s[];
    static int arr[];

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        s = new int[N];
        arr = new int[2000001];
        for(int i = 0 ; i < N ; i ++){
            if(!st.hasMoreElements())
                st = new StringTokenizer(f.readLine());
            s[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(s);
        Arrays.fill(arr, -1);
        arr[0]=0;
        int value = 1;
        while(true){
            int min = -1;
            for(int x:s){
                if(value < x || arr[value-x] == -1)
                    continue;
                if(min == -1 || min > (arr[value-x]+1))
                    min = arr[value-x]+1;
            }
            if(min == -1 || min > K) {
                value = value - 1;
                break;
            }
            arr[value] = min;
            value ++;
        }
        out.println(value);
        out.close();
        System.exit(0);
    }
}
