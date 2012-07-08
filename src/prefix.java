/*
ID: scv1191
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

public class prefix {
    static Node root;
    static class Node{
        char value;
        Node sibing;
        Node child;
        boolean end;

        Node(){
            value = '.';
            end = false;
        }
    }

    static String s;
    static int len;
    static int dp[];

    static void add(String s){
        Node n  = root;

        for(int i = 0 ; i < s.length(); i ++){
            char c= s.charAt(i);
            boolean end = false;
            if(i == s.length() - 1)
                end = true;
            if(n.value == '.'){
                n.value = c;
            }
            else{
                while(n.value != c){
                    Node sib = n.sibing;
                    if(sib == null)
                    {
                        n.sibing = new Node();
                        n.sibing.value = c;

                    }
                    n = n.sibing;
                }

            }
            if(n.child == null)
            {
                n.child = new Node();
            }
            if(end)
                n.end = true;
            n = n.child;
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        root = new Node();

        while(true){
            boolean stop = false;
            StringTokenizer st = new StringTokenizer(f.readLine());
            while(true){
                if(!st.hasMoreTokens())
                    break;
                String s= st.nextToken();

                if(s.equals(".")){
                    stop = true;
                    break;
                }
                add(s);
            }
            if(stop)
                break;
        }
        String line;
        StringBuffer sb = new StringBuffer();
        while((line = f.readLine())!=null)
            sb.append(line) ;
        s = sb.toString();
        len = s.length();
        dp = new int[len];
        solve();
        int i = len -1;
        for( ; i>=-1 ; i --){
            if(i == -1){
                break;
            }
            if(dp[i] == 1){
                break;
            }
        }
        out.println((i+1));
        out.close();
        System.exit(0);
    }

    static void solve(){
        for(int i = 0 ; i < len; i ++){
            if(i == 0)
                match(i);
            else if(dp[i-1] == 1)
                match(i);
        }
    }

    static void match(int idx){
        Node n = root;
        for(;idx<len;idx++){
            char c = s.charAt(idx);
            while(true){
                char des = n.value;
                if(des == '.')
                    return;
                if(des == c)
                    break;
                if(des != c && n.sibing!=null)
                    n = n.sibing;
                else if(n.sibing == null)
                    return;
            }
            if(n.end)
                dp[idx] = 1;
            n = n.child;
            if(n == null)
                return;
        }
    }
}
