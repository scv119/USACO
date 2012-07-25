/*
ID: scv1191
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class shopping {
    static int s;
    static int b;
    static int values[];
    static int mem[];
    static int need[];
    static int offer[][];
    static Map<Integer,Integer> pcode;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        s =  Integer.parseInt(st.nextToken());
        offer = new int[s][10];
        pcode = new HashMap<Integer,Integer>();
        for(int i = 0 ;  i < s; i ++){
            st = new StringTokenizer(f.readLine());
            offer[i][0] = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < offer[i][0]; j ++){
                int code = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int cid = getId(code);
                offer[i][cid+2]=count;
            }
            offer[i][1] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(f.readLine());
        b =  Integer.parseInt(st.nextToken());
        need = new int[b];
        int kneed = 0;
        values = new int[b];
        for(int i = 0 ; i < b ; i ++){
            st = new StringTokenizer(f.readLine());
            int code = getId(Integer.parseInt(st.nextToken()));
            need[code] = Integer.parseInt(st.nextToken());
            kneed += (int)(Math.pow(10,code))*need[code];
            values[code] = Integer.parseInt(st.nextToken());
        }
        mem = new int[55556];
        Arrays.fill(mem, -1);
        out.println(solve(kneed));
        out.close();
        System.exit(0);
    }

    static int solve(int need){
        if(mem[need] != -1)
            return mem[need];


        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < s; i ++){
            int next = need;
            boolean bad = false;
            int mask = 10;
            for(int k = 0 ; k < b; k ++){
                if(next%mask/(mask/10) >= offer[i][k+2]) {
                    next -= offer[i][k+2]*(mask/10);
                }
                else{
                    bad = true;
                    break;
                }
                mask *= 10;
            }
            if(bad)
                continue;
            int value = solve(next)+offer[i][1];
            if(value < min)
                min = value;
        }

        int value = 0;
        int tmp   = need;
        for(int k = 0 ; k < b; k ++){
            value += (tmp%10) *values[k];
            tmp = tmp /10;
        }
        if(value < min)
            min = value;

        mem[need] = min;
        return min;
    }

    static int getId(int code){
        if(pcode.containsKey(code))
            return pcode.get(code);
        pcode.put(code, pcode.size());
        return pcode.get(code);
    }
}
