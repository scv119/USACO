/*
ID: scv1191
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class zerosum {
    static int N;
    static List<String> list;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
//        N = 3;
        list = new ArrayList<String>();
        solve();
        Collections.sort(list);
        for(String s:list)
        out.println(s);
        out.close();
        System.exit(0);
    }

    static void solve(){
        int[] arr = new int[N-1];
        solve(0,arr);
    }

    static void solve(int idx, int ops[]){
        //0 _ , 1 - , 2 +
        if(idx == N-1){
            int tmp1 = 1;
            int tmp2 = 0;
            int op = 0;
            for(int i = 0 ; i < N-1; i ++){
                int v = i + 2;
                if(ops[i] == 0){
                    if(op == 0){
                        tmp1 = tmp1 * 10 + v;
                    }
                    else{
                        tmp2 = tmp2 * 10 + v;
                    }
                }
                else{
                    if(op!=0){
                        tmp1 = op == 1? (tmp1-tmp2):(tmp1+tmp2);
                        tmp2 = v;
                        op = ops[i];
                    }
                    else{
                        op = ops[i];
                        tmp2 = v;
                    }
                }
            }
            int result = 0;
            if(op !=0)
                result =  op == 1? (tmp1-tmp2):(tmp1+tmp2);
            else
                result = tmp1;
            if(result == 0)
            {
                StringBuffer sb = new StringBuffer();
                for(int i = 0 ; i < 2*N-1;i ++){
                    if(i%2==0)
                        sb.append((i/2+1));
                    else{
                        int p = ops[i/2];
                        if(p == 0)
                            sb.append(' ');
                        else if(p == 1)
                            sb.append('-');
                        else
                            sb.append('+');
                    }
                }
                list.add(sb.toString());
            }
        }
        else{
            for(int i = 0 ; i <3 ; i ++){
                ops[idx]=i;
                solve(idx+1,ops);
            }
        }
    }
}
