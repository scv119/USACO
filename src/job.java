/*
ID: scv1191
LANG: JAVA
TASK: job
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class job {
    static int N;
    static int m1;
    static int m2;

    static int time1[];
    static int time2[];

    static int f_time1[];
    static int f_time2[];
    static int delay1[];
    static int delay2[];

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("job.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        time1 = new int[m1];
        time2 = new int[m2];

        f_time1 = new int[N];
        f_time2 = new int[N];
        delay1 = new int[m1];
        delay2 = new int[m2];

        for(int i = 0; i < m1 + m2 ; i ++) {
            if(!st.hasMoreElements())
                st = new StringTokenizer(f.readLine());
            if(i < m1) {
                time1[i] = Integer.parseInt(st.nextToken());
            }
            else {
                time2[i-m1] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(time1);
        Arrays.sort(time2);

        int max1 = 0;
        int max2 = 0;

        for(int i = 0 ; i < N; i ++) {
            int min = Integer.MAX_VALUE;
            int minidx = 0;
            for(int j = 0 ; j < m1; j ++) {
                if(delay1[j] + time1[j] < min) {
                    minidx = j;
                    min = delay1[j] + time1[j];
                }
            }
            delay1[minidx] += time1[minidx];
            f_time1[i] = delay1[minidx];
            max1 = Math.max(f_time1[i], max1);
        }

        for(int i = 0 ; i < N; i ++) {
            int min = Integer.MAX_VALUE;
            int minidx = 0;
            for(int j = 0 ; j < m2; j ++) {
                if(delay2[j] + time2[j] < min) {
                    minidx = j;
                    min = delay2[j] + time2[j];
                }
            }
            delay2[minidx] += time2[minidx];
            f_time2[i] = delay2[minidx];

        }

        for(int i = 0; i < N; i ++) {
            max2 = Math.max(f_time1[i] + f_time2[N-1-i], max2);
        }


        out.println(max1+" "+max2);


        out.close();
        System.exit(0);
    }
}
