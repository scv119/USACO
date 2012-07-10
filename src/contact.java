/*
ID: scv1191
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.*;

public class contact {


    public static class Pair implements Comparable {
        int id;
        int count;
        int len;

        @Override
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if(this.count != p.count){
                return p.count-this.count;
            }
            if(this.len != p.len){
                return this.len - p.len;
            }
            return this.id - p.id;
        }

        Pair(int id, int len, int count){
            this.id = id;
            this.len = len;
            this.count = count;
        }

        public String toString(){
            String s = Integer.toBinaryString(id);
            while(s.length()<len){
                s = "0"+s;
            }
            return s;
        }
    }



    static int A;
    static int B;
    static int N;
    static StringBuffer sb;
    static char arr[];
    static int count[][];
    static int max;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("contact.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        sb = new StringBuffer();
        String line;
        while((line = f.readLine())!=null)
            sb.append(line);
        max = 1<<(B+1);
        count = new int[max][B+1];


        for(int len = A; len <= B; len ++){
            int value = 0;
            int vlen  = 0;
            int mask  = 0;
            for(int i = 0; i < len; i ++){
                mask = mask | (1<<i);
            }
            for(int idx = 0 ; idx < sb.length() ; idx++){
                value = value * 2 + sb.charAt(idx) - '0';
                value = value & mask;
                vlen  = vlen < len ? vlen +1:vlen ;
                if(vlen < len)
                    continue;
                count[value][vlen]++;

            }
        }
        List<Pair> list = new ArrayList<Pair>();

        for(int i = 0 ; i < max; i ++){
            for(int j = 1 ;j <= B; j++)
            if(count[i][j] > 0){
                list.add(new Pair(i, j, count[i][j]));
            }
        }

        Collections.sort(list);

        int pre = -1;
        int count = 0;
        int lc  = 0;
        boolean nl = false;
        for(int i = 0 ; i < list.size() ; i ++){

            Pair p = list.get(i);
            if(pre == -1 || pre != p.count){
                if(pre != -1) {
                    out.println();
                    nl = true;
                }
                lc ++;
                if(lc > N){
                    break;
                }
                pre = p.count;
                out.println(pre);
                nl = true;
                count = 0;
            }

                if(count > 0 && count%6==0) {
                    out.println();
                    nl = true;
                }
                if(count%6!=0) {
                    out.print(" ");
                    nl = false;
                }
                out.print(p.toString());
                nl = false;
                count ++;

        }
        if(!nl)
            out.println();
        out.close();
        System.exit(0);
    }



}
