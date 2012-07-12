/*
ID: scv1191
LANG: JAVA
TASK: ratios
*/

import java.io.*;
import java.util.StringTokenizer;

public class ratios {
    static int dp[][][];
    static int dp1[][][];
    static int limit[];
    static int input[][];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

        input = new int[4][3];
        limit = new int[3];
        dp1   = new int[100][3][3];



        for(int i = 0 ; i <4 ; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0 ; j <3; j ++)
                input[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < 100 ; i ++){
            for(int j = 1; j <4 ; j ++){
                for(int k = 0 ; k < 3; k ++)
                dp1[i][j-1][k] = i * input[j][k];
            }
        }

        for(int i = 0 ; i <3 ;i++){
            limit[i] = input[0][i]*100;
        }
        dp = new int[limit[0]+1][limit[1]+1][limit[2]+1];
        int arr[][] = new int[3][3];
        int x = 0;
        int y = 0;
        int z = 0;
        for(int i =0 ; i < 100; i ++){
            for(int j = 0 ; j < 100;j ++) {
                x= dp1[i][0][0] + dp1[j][1][0];
                y= dp1[i][0][1] + dp1[j][1][1];
                z= dp1[i][0][2] + dp1[j][1][2];
                if(x > limit[0] || y > limit[1]|| z>limit[2])
                    continue;
//                System.out.println(i+" "+j+" "+x+" "+y+" "+z );
                dp[x][y][z] = i*10000+j*100;
            }
        }
        int result = -1;
        int code    = -1;
        for(int i = 0 ; i < 100; i ++){
            x = input[0][0] * i;
            y = input[0][1] * i;
            z = input[0][2] * i;
            boolean find = false;
            for(int k = 0 ; i < 100 ; k ++){
                if(dp1[k][2][0] > x || dp1[k][2][1] > y || dp1[k][2][2]>z)
                    break;
                if(dp[x-dp1[k][2][0]][y-dp1[k][2][1]][z-dp1[k][2][2]] > 0){
                    result = i;
                    code = dp[x-dp1[k][2][0]][y-dp1[k][2][1]][z-dp1[k][2][2]]+k;
                    find = true;
                    break;
                }
            }
            if(find)
                break;
        }
        if(result == -1)
            out.println("NONE");
        else
            out.println((code/10000)+" "+(code%10000/100)+" "+(code%100)+" "+result);
        out.close();
        System.exit(0);
    }
}
