/*
ID: scv1191
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class dualpal {
    public static int size = 0;
    public static int begin = 0;

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        size = Integer.parseInt(st.nextToken());    // first integer
        begin = Integer.parseInt(st.nextToken());

        int count = size;
        int start = begin + 1;
        while(count > 0){
            if(is(start)){
                out.println(start);
                count --;
            }
            start ++;
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
        System.out.println(is(12));
    }

    static boolean is(int n){
        int count = 0;
        for(int base = 2 ; base <= 10; base++){
            String result = "";
            int tmp = n;
            while(tmp != 0 ){
                int left = tmp%base;
                tmp = tmp/base;
                result = left + result;
            }
            String rev = "";
            for(int i = result.length() - 1; i >= 0 ; i --){
                rev = rev + result.charAt(i);
            }
            if(rev.equals(result)){
//                System.out.println(base+" "+result);
                count ++;
            }
        }
        if(count >= 2)
            return true;
        return false;
    }

}