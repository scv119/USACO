/*
ID: scv1191
LANG: JAVA
TASK: fence4
*/

import javax.swing.text.Segment;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class fence4 {

    static int N;
    static point ob;
    static point[] corners;
    static List<segment> fences;
    static boolean sortByDis = true;
    static double eps = 1e-8;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fence4.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence4.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        ob = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        corners = new point[N];
        fences  = new ArrayList<segment>();
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(f.readLine());
            corners[i] = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            corners[i].id = i;
        }
        for(int i = 0 ; i < N ; i ++){
            int end = (i+1)%N;
            if(end != 0)
                fences.add(new segment(corners[i],corners[end]));
            else
                fences.add(new segment(corners[end],corners[i]));
            fences.get(i).id = i;
        }
        boolean valid = true;
        for(int i = 0 ; i < N ; i ++)
            for(int j = i + 1; j < N ; j ++){
                if(intersect(fences.get(i),fences.get(j))){
                    valid =false;
                    break;
                }
            }

        if(!valid)
            out.println("NOFENCE");
        else{
            Collections.sort(fences);
            List<segment> avaiable = solve();
            sortByDis = false;
            Collections.sort(avaiable);
            out.println(avaiable.size());
            for(segment seg:avaiable){
                out.println((int)(seg.start.x)+" "+(int)(seg.start.y)+" "+(int)(seg.end.x)+" "+(int)(seg.end.y));
            }
        }
        out.close();
        System.exit(0);
    }


    static List<segment> solve(){
        List<segment> ret = new ArrayList<segment>();
        List<angle>   angles = new ArrayList<angle>();

        angles.add(new angle(0,Math.PI*2));

        for(segment seg:fences){
            List<angle> as = getangles(seg);
            boolean valid = false;
            for(angle a:as){
                List<angle> tmp = new ArrayList<angle>();
                for(angle n:angles){
                    double start = Math.max(n.start, a.start);
                    double end   = Math.min(n.end,   a.end );

                    if( Math.abs(end - start) < eps || end < start){
                        tmp.add(n);
                        continue;
                    }
                    else{
                        valid = true;
                        if(Math.abs(start - n.start) < eps && Math.abs(end - n.end) < eps){
                        }
                        else if( Math.abs(end - n.end) < eps){
                            tmp.add(new angle(n.start, start));
                        }
                        else if(Math.abs(start - n.start) < eps )
                            tmp.add(new angle(end, n.end));
                        else{
                            tmp.add(new angle(n.start, start));
                            tmp.add(new angle(end, n.end));
                        }
                    }
                }
                angles = tmp;
            }
            if(valid)
                ret.add(seg);
        }

        return ret;
    }

    static List<angle> getangles(segment seg){
        List<angle> ret = new ArrayList<angle>(2);
        double angel1, angel2;

        double start = (dotproduct(new point(seg.start.x - ob.x, seg.start.y - ob.y), new point(1,0))/distance(seg.start, ob));

        if(seg.start.y >= ob.y)
            angel1 = Math.acos(start);
        else
            angel1 = Math.PI * 2 - Math.acos(start);

        double end   = (dotproduct(new point(seg.end.x - ob.x, seg.end.y - ob.y), new point(1,0))/distance(seg.end, ob));

        if(seg.end.y >= ob.y)
            angel2 = Math.acos(end);
        else
            angel2 = Math.PI * 2 - Math.acos(end);

        if(Math.abs(angel1 - angel2) < Math.PI){
            ret.add(new angle(Math.min(angel1,angel2), Math.max(angel1,angel2)));
        }
        else{
            ret.add(new angle(0, Math.min(angel1, angel2)));
            ret.add(new angle(Math.max(angel1, angel2), Math.PI*2));
        }
        return ret;

    }




    static class angle{
        double start;
        double end;

        angle(){};

        angle(double start, double end){this.start = start; this.end = end;}
    }

    static class point{
        double x, y;
        int id;

        point(){};

        point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    static class segment implements Comparable{
        point start;
        point end;
        int   id;

        public segment(){};

        public segment(point x, point y){
            this.start = x;
            this.end = y;
        }

        point mid(){
            return new point((start.x + end.x)/2, (start.y + end.y)/2);
        }

        @Override
        public int compareTo(Object o) {
            segment seg = (segment)o ;
            if(sortByDis){
                double ret = Math.min(distance(ob, this.start),distance(ob, this.end)) - Math.min(distance(ob, seg.start),distance(ob, seg.end));
                if(ret <  0)
                    return -1;
                if( ret > 0 )
                    return 1;
                return 0;
            }

            int ret = this.end.id - seg.end.id;
            if(ret != 0)
                return  ret;
            return this.start.id - seg.start.id;
        }
    }

    static point minus(point a, point b){
        point ret = new point();
        ret.x = a.x - b.x;
        ret.y = a.y - b.y;
        return ret;
    }

    static boolean intersect(segment a, segment b){
        if(crossproduct(minus(a.end, a.start), minus(b.end, a.start)) * crossproduct(minus(a.end, a.start), minus(b.start , a.start)) < 0  &&
                crossproduct(minus(b.end, b.start), minus(a.end, b.start)) * crossproduct(minus(b.end, b.start), minus(a.start , b.start)) < 0)
            return true;
        return false;
    }

    static double distance(point a, point b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    static double crossproduct(point a, point b){
        return a.x * b.y - a.y * b.x;
    }

    static double dotproduct(point a, point b){
        return a.x * b.x + a.y * b.y;
    }

}
