/*
ID: scv1191
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class milk3 {
    static Set<Integer> set;
    static int A;
    static int B;
    static int C;

    static int dp[][][];

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());// first integer
        set =new HashSet<Integer>();
        dp = new int[21][21][21];

        solve(0,0,C);

        int[] arr = new int[set.size()];
        int i = 0;
        for(int x:set){
            arr[i++] = x;
        }
        Arrays.sort(arr);

        for(i = 0 ; i < arr.length ; i ++){
            if(i > 0)
                out.print(" ");
            out.print(arr[i]);
        }
        out.println();
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve(int a, int b, int c)
    {
        if(dp[a][b][c] == 1)
            return;
        dp[a][b][c] = 1;
        if(a == 0){
            set.add(c);
        }
        if(a != 0){
            int a2b = Math.min(a, B-b);
            solve(a-a2b, b+a2b, c);
            int a2c = Math.min(a, C-c);
            solve(a-a2c,b, c + a2c);
        }

        if(b != 0){
            int b2a = Math.min(b, A-a);
            solve(a+b2a, b-b2a, c);
            int b2c = Math.min(b, C-c);
            solve(a,b - b2c, c + b2c);
        }

        if(c != 0){
            int c2a = Math.min(c, A-a);
            solve(a+c2a, b, c-c2a);
            int c2b = Math.min(c,B-b);
            solve(a,b + c2b, c -c2b);
        }

    }
}