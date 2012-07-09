/*
ID: scv1191
LANG: JAVA
TASK: clocks
*/

import java.io.*;
import java.util.StringTokenizer;

class clocks {
    static int begin[];
    static String[] moves = new String[]{"","ABDE","ABC","BCEF","ADG","BDEFH","CFI","DEGH","GHI","EFHI"};
    static int[] min;

    public static void main (String [] args) throws IOException {
        min = null;
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        begin = new int[9];
        for(int i = 0 ; i < 3; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0 ;  j < 3; j ++)
                begin[i*3 + j] = Integer.parseInt(st.nextToken())%12;
        }

        solve(1, 0, new int[27], 0, begin);
        for(int i = 0 ; i < 27 ; i ++){
            if(min[i] == 0)
                break;
            if(i > 0)
                out.print(" ");
            out.print(min[i]);
        }
        out.println();

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve(int start, int count, int[] pre, int len, int now[]){
        boolean fullfill = true;
        for(int i:now){
            if (i != 0)          {
                fullfill = false;
                break;
            }
        }
        if(fullfill){
            if(min == null || small(pre, min))
                min = pre.clone();
            return;
        }

        for(int i = start; i <= 9 ; i ++){
            int tmp[] = now.clone();
            String move = moves[i];
            if(i == start && count == 3)
                    continue;

            for(int k = 0 ; k < move.length(); k ++){
                int idx = move.charAt(k) - 'A';
                tmp[idx] = (tmp[idx] + 3) % 12;
            }
            int next_count = 1;
            if(i == start)
                next_count = count + 1;
            pre[len] = i;
            solve(i,next_count,pre, len + 1,tmp);
            pre[len] = 0;
        }


    }

    static boolean small(int[] arr1, int[] arr2){
       for(int i = 0 ; i < arr1.length ; i++){
           if(arr1[i] < arr2[i])
               return true;
           if(arr1[i] > arr2[i])
               return false;
       }
       return false;
    }
}