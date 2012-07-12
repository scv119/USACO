/*
ID: scv1191
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.*;

public class msquare {
    static int start;
    static int[] arr;
    static int result;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        start = 0 + (1 << 3) + (2 << 6) + (3 << 9) + (7 << 12) + (6 << 15) + (5 << 18) + (4 << 21);
        arr = new int[8];
        for(int i = 0 ; i < 4; i ++){
            arr[i] = Integer.parseInt(st.nextToken()) -1;
        }
        for(int i = 7 ; i >= 4 ; i --){
            arr[i] = Integer.parseInt(st.nextToken())-1;
        }
        result = 0;
        for(int i = 0 ; i < 8 ;i ++){
            result += (arr[i] << (i*3));
        }

//        debug(start);
//        debug(result);
        Map<Integer,int[]> pre = new HashMap<Integer,int[]>();
        Queue<Integer> q = new LinkedList<Integer>();
        pre.put(start, new int[]{-1, -1});
        q.add(start);
        boolean find = false;
        if(result == start)
            find = true;
        while(q.size()>0 && !find){
            int x = q.poll();
            debug(x);
            for(int i = 0 ; i < 3; i ++){
                int next = -1;
                if(i == 0)
                    next = A(x);
                else if(i == 1)
                    next = B(x);
                else
                    next = C(x);

//                debug(next);
                if(!pre.containsKey(next)){
                    pre.put(next,new int[]{i,x});
                    q.add(next);
                    if(next == result)
                    {
                        find = true;
                        break;
                    }
                }
            }
        }
        int count = 0;
        StringBuffer sb = new StringBuffer();
        int cur = result;
        while(true){
            int[] arr = pre.get(cur);
            if(arr[0] == -1)
                break;
            count ++;
            cur = arr[1];
            sb.append((char)(arr[0]+'A'));
        }

        out.println(count);
        StringBuffer rsb = new StringBuffer(sb.length());
        for(int i = sb.length()-1; i >=0 ; i --){
            rsb.append(sb.charAt(i));
        }
        out.println(rsb);
        out.close();
        System.exit(0);
    }

    static void debug(int n){
//        System.out.println(Integer.toBinaryString(n));
//        int mask = 7;
//        for(int i = 0 ; i < 8 ; i ++){
//            int result = n & mask;
//            System.out.print((result+1) + " ");
//            if(i%4==3)
//                System.out.println();
//            n = n >> 3;
//        }
    }

    static int A(int origin){
        for(int i = 0 ; i <4; i ++){
            origin = exchange(origin,i,i+4);
        }
        return origin;
    }

    static int B(int origin){
        for(int i = 3; i >= 1; i --){
           origin = exchange(origin,i,i-1);
           origin = exchange(origin,i+4,i+3);
        }
        return origin;
    }

    static int C(int origin){
        origin = exchange(origin,1,5);
        origin = exchange(origin,5,6);
        origin = exchange(origin,6,2);
        return origin;

    }

    static int exchange(int value, int i, int j){
        int mask = 7;
        i = i * 3;
        j = j * 3;
        int mask_i = mask << i;
        int mask_j = mask << j;
        int tmp_i = (value&mask_i)>>i;
        int tmp_j = (value&mask_j)>>j;
        value = (value&~mask_i&~mask_j);
        value = value|(tmp_i<<j)|(tmp_j<<i);
        return value;
    }
}
