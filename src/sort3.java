/*
ID: scv1191
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.*;

public class sort3 {
    static int len;
    static int arr[];
    static int l[];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        len = Integer.parseInt(st.nextToken());
        arr = new int[len];
        l = new int[4];
        for(int i = 0 ; i <len ; i ++){
            st = new StringTokenizer(f.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            l[arr[i]]++;
        }
        int sum = 0;
        int c12 = 0;
        int c21 = 0;

        for(int i = 0 ; i < len; i ++){
            if(i < l[1] && arr[i] != 1){
                sum ++;
                if(arr[i] == 2)
                    c12 ++;
            }
            else if(i >= l[1] && i < l[1] + l[2] && arr[i] == 3){
                sum ++;
            }
            else if(i >= l[1] && i < l[1] + l[2] && arr[i] == 1){
                c21 ++;
            }
        }
        if(c21 > c12)
            sum += Math.abs(c12-c21);
        out.println(sum);
        out.close();
        System.exit(0);
    }
}
