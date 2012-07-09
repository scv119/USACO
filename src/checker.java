/*
ID: scv1191
LANG: JAVA
TASK: checker
*/

import java.io.*;
import java.util.StringTokenizer;

public class checker {
    static class Node{
        Node next;
        int value;
    }

    static int N;
    static int count;
    static int pcount;
    static Node head;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("checker.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        count = 0;
        pcount = 0;
        solve(out);
        out.println(count);

        out.close();
        System.exit(0);
    }

    static void solve(PrintWriter out){
        int[] arr = new int[N];
        head = new Node();
        head.value = 0;
        Node pre = head;
        for(int i = 1; i <N; i ++){
            Node now = new Node();
            now.value = i;
            pre.next = now;
            pre = now;
        };
        solve(arr,0,0,0,out);
    }

    static void solve(int[] arr, int len, int dig, int rdig, PrintWriter out){
        if(len == N){
            if(pcount < 3){
                for(int i = 0; i < N; i ++){
                    if(i!=0)
                        out.print(" ");
                    out.print(arr[i]+1);
                }
                out.println();
                pcount ++;
            }
            count ++;
        }
        else{

            Node pre = null;
            Node next;
            Node now = head;
            for(; now != null ; pre = now, now = now.next){
                int i = now.value;
                int dig_num = len - i + N - 1;
                int d_mask = 1 << dig_num;
                if((d_mask&dig) == d_mask)
                    continue;

                int rdig_num = i + len;
                int r_mask = 1 << rdig_num;
                if((r_mask&rdig) == r_mask)
                    continue;


                next = now.next;
                if(pre == null)
                    head = next;
                else
                    pre.next = next;

                arr[len] = i;
                solve(arr, len + 1, dig | d_mask, rdig | r_mask, out);
                if(pre == null)
                {
                    head = now;
                    now.next = next;
                }
                else{
                    pre.next = now;
                    now.next = next;
                }
            }
        }
    }
}
