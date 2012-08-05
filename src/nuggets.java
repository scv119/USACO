/*
ID: scv1191
LANG: JAVA
TASK: nuggets
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nuggets {
    static int N;
    static int arr[];
    static int start;
    static int len;
    static int offset;
    static int max;
    static int valid = 0;
    static int now[] = new int[256];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(f.readLine());

        start = 0;
        len = 0;
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static int solve(){
        int tmp = arr[0];
        for(int i = 1; i < N ; i ++)
            tmp = gcd(tmp, arr[i]);
        if(tmp != 1)
            return 0;
        Arrays.sort(arr);
        max = 0;
        int i = 1;
        now[start] = 1;
        len ++;
        offset = 0;
        while(true){
            boolean contain = false;
            for(int x:arr){
                if(i-x <0)
                    continue;
                tmp = i -x;
                if(now[(start + tmp - offset + 256)%256] == 1){
                    contain = true;
                    break;
                }
            }
            int pre = now[(start+len)%256];
            if(contain){
                now[(start+len)%256] = 1;
                valid ++;
            }
            else{
                now[(start+len)%256] = 0;
                max = i;
            }
            len ++;
            if(len > 256) {
                len = 256;
                if(pre == 1)
                    valid --;
                start = (start + 1)%256;
                offset ++;

            }
            if(valid == 255 && now[(start+len)%256] == 1)
                return max;
            i++;
        }
    }

    static int gcd(int a, int b){
        return a == 0 ? b : gcd(b%a, a);
    }
}
