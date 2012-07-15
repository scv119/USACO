/*
ID: scv1191
LANG: JAVA
TASK: spin
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class spin {
    static int speed[];
    static List[] wedges;


    static class Interval implements Comparable{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Object o) {
            Interval to = (Interval)o;
            if(this.start != to.start)
                return this.start - to.start;
            return this.end - to.end;
        }

        public Interval intersect(Interval a){
            int end = Math.min(a.end, this.end);
            int start = Math.max(a.start, this.start);
            if(start > end)
                return null;
            return new Interval(start, end);
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("spin.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
        speed = new int[5];
        wedges = new List[5];
        for(int i = 0 ; i < 5; i ++)
            wedges[i] = new ArrayList<Integer>();
        for(int i = 0 ; i < 5; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            speed[i] = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < w * 2 ; j ++){
                wedges[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static String solve(){
        for(int t = 0 ; t < 360; t ++){
            if(check(t))
                return t + "";
        }
        return "none";
    }

    static boolean check(int t){
        List<Interval> rslist = new ArrayList<Interval>();
        rslist.add(new Interval(0,359));
        for(int i = 0 ; i < 5; i ++){
            if(rslist.size() == 0)
                continue;
            int offset = t * speed[i];
            List<Interval> list = new ArrayList<Interval>();
            for(int j = 0 ; j < wedges[i].size(); j += 2){
                int start = ((Integer)(wedges[i].get(j)) + offset)%360;
                int end   = ((Integer)(wedges[i].get(j+1)) + start)%360;
                if(start <= end){
                    list.add(new Interval(start,end));
                }
                else{
                    list.add(new Interval(start,359));
                    list.add(new Interval(0, end));
                }
            }
//            Collections.sort(list);
            List<Interval> tmp = new ArrayList<Interval>();
            int idx1 = 0;
            int idx2 = 0;
            for(Interval it1 : rslist)
                for(Interval it2:list){
                    Interval it = it1.intersect(it2);
                    if(it != null)
                        tmp.add(it);
                }
//            Collections.sort(tmp);
            rslist = tmp;
        }
        if(rslist.size() > 0)
            return true;
        return false;
    }
}
