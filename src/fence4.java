/*
ID: scv1191
LANG: JAVA
TASK: fence4
*/

import java.io.*;
import java.util.*;

public class fence4 {
    static int N;
    static int obX, obY;
    static List<Seg> segs;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fence4.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence4.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        segs = new ArrayList<Seg>(N);
        st = new StringTokenizer(f.readLine());
        obX = Integer.parseInt(st.nextToken());
        obY = Integer.parseInt(st.nextToken());
        Point now = null;
        Point first = null;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(f.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point tmp = new Point(x,y);
            if(now == null){
                first = tmp;
            }
            else{
                Seg s = new Seg();
                s.x = now;
                s.y = tmp;
                segs.add(s);
            }
            now = tmp;
        }
        Seg s = new Seg();
        s.x = now;
        s.y = first;
        segs.add(s);

        boolean valid = true;
        for(int i = 0 ; i < N ; i ++)
            for(int j = i + 1; j < N ; j ++){
                if(inter(segs.get(i).x, segs.get(i).y, segs.get(j).x, segs.get(j).y)){
                    valid = false;
                    break;
                }
            }

        if(!valid){
            out.println("NOFENCE");
        }
        else{
            Collections.sort(segs);
            List<Seg> visible = new ArrayList<Seg>();
            for(Seg seg:segs){

            }

        }

        out.close();
        System.exit(0);
    }

    static class Point{
        double x, y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    static class angle{
        double s;
        double e;
    }

    static class Seg implements Comparable{
        Point x, y;

        @Override
        public int compareTo(Object o) {
            Seg s = (Seg)o;
            double ret =  dis(mid(), new Point(obX,obY)) - dis(s.mid(), new Point(obX, obY));
            if(ret < 0)
                return -1;
            if(ret > 0)
                return 1;
            return 0;
        }

        Point mid(){
            return new Point((x.x + y.x)/2, (x.y+y.y)/2);
        }
    }


    static double crossProduct(Point a, Point b){
        return a.x * b.y - a.y * b.x;
    }

    static double dotProduct(Point a, Point b){
        return a.x * b.x + a.y * b.y;
    }


    static double dis(Point a, Point b){
        return Math.sqrt((a.x - b.x)*(a.x - b.x)+(a.y - b.y)*(a.y - b.y));
    }

    static boolean inter(Point a, Point b, Point c, Point d){
        if(crossProduct(new Point(c.x-d.x, c.y-d.y), new Point(b.x-d.x, b.y-d.y)) * crossProduct(new Point(c.x-d.x, c.y-d.y), new Point(a.x-d.x, a.y -d.y)) <= 0
                && crossProduct(new Point(b.x -a.x, b.y - a.y), new Point(d.x -a.x, d.y - a.y)) * crossProduct(new Point(b.x - a.x, b.y - a.y), new Point(c.x -a.x, c.y - a.y)) <= 0 )
            return true;
        return false;
    }

}
