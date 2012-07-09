/*
ID: scv1191
LANG: JAVA
TASK: palsquare
*/

import java.io.*;
import java.util.*;

class palsquare {
    public static int base = 0;
    public static List<String> result;

    public static void main (String [] args) throws IOException {
        result = new ArrayList<String>();
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        base = Integer.parseInt(st.nextToken());    // first integer

        solve();
        for(String i:result){
            out.println(i);
        }
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve()
    {
        for(int i = 1 ; i < 300; i ++){
            int sq = i * i;
            String result = "";
            while(sq != 0){
                int left = sq%base;
                sq = sq/base;
                if(left <= 9)
                    result = left+result;
                else
                    result = (char)(left - 10 + 'A') + result;
            }
            String rev = "";
            for(int j = result.length() - 1; j>= 0 ; j--){
                rev = rev + result.charAt(j);
            }
            if(rev.equals(result))   {
                String ori = "";
                int tmp = i;
                while(tmp != 0){
                    int left = tmp%base;
                    tmp = tmp/base;
                    if(left <= 9)
                        ori = left + ori;
                    else
                        ori = (char)(left - 10 + 'A') + ori;
                }
                palsquare.result.add(ori+" "+result);
            }
        }
    }

}