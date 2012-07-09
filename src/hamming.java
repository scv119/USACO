/*
ID: scv1191
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.*;

class hamming {
    static int N;
    static int B;
    static int D;


    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
//        // Get line, break into tokens
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());    // first integer
        D = Integer.parseInt(st.nextToken());
//        N = 16;
//        B = 7;
//        D = 3;

        solve(out);                         // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve(PrintWriter out)
    {
        int max = (int)Math.pow(2,B);
        List<Integer> list = new LinkedList<Integer>();
        for(int i = 0 ; i < max; i ++){
            boolean ok = true;
            for(int x:list){
                ok = ok && diff(x,i);
                if(ok == false)
                    break;
            }
            if(ok)
                list.add(i);
            if(list.size()>=N)
                break;
        }
        for(int i = 0 ; i < list.size() ; i ++){
            if(i%10!=0)
                out.print(" ");
            if(i%10==0 && i !=0)
                out.println();
            out.print(list.get(i));
        }
        out.println();
    }

    static boolean diff(int a, int b){
        int count = 0;
        for(int i = 0 ; i <B; i ++){
            int mask = 1 << i;
            if((mask&a) != (mask&b))
                count ++;
        }

        if(count >= D)
            return true;
        return false;
    }

}