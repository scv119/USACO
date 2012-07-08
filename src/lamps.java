/*
ID: scv1191
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {
    static int C;
    static int N;
    static List<String> result;
    static Map<Integer,Integer> limit;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        C = Integer.parseInt(st.nextToken());
        result = new ArrayList<String>();
        limit = new HashMap<Integer, Integer>();
        st = new StringTokenizer(f.readLine());
        while(true)
        {
            int n= Integer.parseInt(st.nextToken());
            if(n == -1)
                break;
            limit.put(n-1,1);
        }

        st = new StringTokenizer(f.readLine());
        while(true)
        {
            int n= Integer.parseInt(st.nextToken());
            if(n == -1)
                break;
            limit.put(n-1,0);
        }


        solve();
        Collections.sort(result);
        int count = 0;
        String pre = "";
        for(String s:result){
            boolean valid = true;
            for(int key:limit.keySet()){
                if(s.charAt(key)-'0' != limit.get(key))
                {
                    valid = false;
                    break;
                }
            }
            if(valid)  {
                if(!s.equals(pre)) {
                    pre = s;
                out.println(s);
                count ++;
                }
            }
        }
        if(count == 0)
            out.println("IMPOSSIBLE");

        out.close();
        System.exit(0);
    }

    static void solve(){
        if(C <= 1)
            solve(C);
        else if(C == 2) {
            solve(0);
            solve(2);
        }
        else if(C%2==1){
            solve(1);
            solve(3);
        }
        else{
            solve(0);
            solve(2);
            solve(4);
        }

    }

    static void solve(int count){
        int[] s= new int[N];
        Arrays.fill(s,1);
        solve(count, 0, s);
    }

    static void solve(int count, int type, int[] s){
        if(count == 0){
            StringBuffer sb = new StringBuffer();
            for(int i:s)
                sb.append(i);
            result.add(sb.toString());
            return;
        }
        for(int t = type; t <= 4 -count; t ++){
            int[] tmp = s.clone();
            for(int i = 0 ; i < tmp.length ; i ++){
                if(t == 0){
                    tmp[i]^=1;
                }
                else if(t==1){
                    if(i%2==0)
                        tmp[i]^=1;
                }
                else if(t==2){
                    if(i%2==1)
                        tmp[i]^=1;
                }
                else{
                    if(i%3==0)
                        tmp[i]^=1;
                }
            }
            solve(count-1, type+1, tmp);
        }
    }
}
