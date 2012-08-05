/*
ID: scv1191
LANG: JAVA
TASK: fence8
*/

import java.io.*;
import java.util.*;

public class fence8 {
    static int N;
    static int R;
    static int boards[];
    static int rails[];
    static int ret;
    static int sumPreRails[];

    static int boardsLeft;
    static int sumBoards;
    static int railsNeed;
    static boolean hasMore;
    static int pre;


    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fence8.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence8.out")));
        N = Integer.parseInt(f.readLine());
        boards = new int[N];
        for(int i = 0 ; i < N; i ++)
            boards[i] = Integer.parseInt(f.readLine());
        R = Integer.parseInt(f.readLine());
        rails = new int[R];
        sumPreRails = new int[R];
        for(int i = 0 ; i < R; i ++) {
            rails[i] = Integer.parseInt(f.readLine());

        }
        f.close();

        Arrays.sort(rails);
        for(int i = 0 ; i < R; i ++){
            sumPreRails[i] = rails[i];
            if(i>0)
                sumPreRails[i] += sumPreRails[i-1];
        }
        Arrays.sort(boards);
        int maxRails  = 0;
        int tmp = 0;

        for(int i:boards)
            sumBoards+=i;
        for(;maxRails<R;maxRails++){
            tmp += rails[maxRails];
            if(tmp > sumBoards)
                break;
        }

        int start = 0;
        int end   = maxRails + 1;

        ret = solve(start,end);
        out.println(ret);
        out.close();
        System.exit(0);
    }

    static int solve(int start, int end){
        if(start == end - 1){
            return start;
        }
        int mid = (start + end)/2;
        if(valid(mid))
            return solve(mid, end);
        else
            return solve(start,mid);
    }

    static boolean valid(int count){
        if(count == 0)
            return true;
        boardsLeft = sumBoards;
        railsNeed = sumPreRails[count-1];
        boolean ret = false;
        ret = dfs(count-1,0);
        return ret;
    }

    static boolean dfs(int railNo, int board){
        if(railNo < 0)
            return true;

        if(railsNeed > boardsLeft)    //剪枝1
            return false;

        boolean ret = false;
        for(int i = board;i < N && !ret; i ++)
            if(rails[railNo] <= boards[i]){
                boards[i] -= rails[railNo];
                if(boards[i] < rails[0]){
                    boardsLeft -= boards[i];
                }
                if(railNo!=0 && rails[railNo] == rails[railNo-1]){     //剪枝2
                    ret = dfs(railNo-1, i);
                }
                else{
                ret =  ret || dfs(railNo-1, 0);
                }

                if(boards[i] < rails[0]){
                    boardsLeft += boards[i];
                }
                boards[i] += rails[railNo];

            }


        return ret;
    }

}
