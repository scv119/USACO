/*
ID: scv1191
LANG: JAVA
TASK: ttwo
*/

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class ttwo {
    static int map[][] = new int[10][10];
    static int dp[][][][][][] = new int[10][10][4][10][10][4];
    //1 obstacle
    //direct N 0 E 1 S 2 W 3
    static int cow[] = new int[3];
    static int farmer[] = new int[3];
    static int ret = 0;

    static int op[] = new int[]{-1,0,0,1,1,0,0,-1};

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        for(int i = 0 ; i < 10 ; i ++){
            String s = f.readLine();
            for(int j = 0 ;  j < 10; j ++){
                char c = s.charAt(j);
                int value = 0;
                if(c == '*')
                    map[i][j] = 1;
                else if(c == 'F'){
                    farmer[0] = i;
                    farmer[1] = j;
                    farmer[2] = 0;
                }
                else if(c == 'C'){
                    cow[0] = i;
                    cow[1] = j;
                    cow[2] = 0;
                }

            }
        }


        solve();
        out.println(ret);
//        out.println(result);                           // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve()
    {
        dp[farmer[0]][farmer[1]][farmer[2]][cow[0]][cow[1]][cow[2]] = 1;
        while(farmer[0] != cow[0] || farmer[1] != cow[1]){
            ret ++;
            next(farmer);
            next(cow);
            if(dp[farmer[0]][farmer[1]][farmer[2]][cow[0]][cow[1]][cow[2]] ==1)   {
                ret = 0;
                break;
            }
            dp[farmer[0]][farmer[1]][farmer[2]][cow[0]][cow[1]][cow[2]] =1;

        }
    }

    static void next(int[] status){
        int x_off = op[status[2]*2];
        int y_off = op[status[2]*2+1];
        int x = status[0]+x_off;
        int y = status[1]+y_off;
        if(x>=0 && x < 10 && y >=0 && y < 10 && map[x][y] == 0){
            status[0]=x;
            status[1]=y;
        }
        else{
            status[2] = (status[2]+1)%4;
        }


    }

}