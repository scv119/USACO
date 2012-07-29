/*
ID: scv1191
LANG: JAVA
TASK: fence4
*/

import java.io.*;
import java.util.StringTokenizer;

public class fence4 {
    static class seg{
        int x1, y1;
        int x2, y2;

        public seg(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2=  x2;
            this.y2= y2 ;
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fence4.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence4.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());
        out.println(i1 + i2);
        out.close();
        System.exit(0);
    }


    static boolean inter(int ax, int ay, int bx, int by, int cx, int cy, int dx, int dy){
        double o1, o2 , p1, p2, q1, q2;
        //o1x+p1y=q1
        //o2x+p2y=q2

        o1 = ay - by;
        p1 = bx - ax;
        q1 = (bx - ax ) * ay + (ay - by ) * ax;

        o2 = cy - dy;
        p2 = dx - bx;
        q2 = (dx - cx) * cy + (cy - dy) * cx;


        double det = o1 * p2 - p1 * o2;
        if(det == 0)
            return false;
        double resx = q1 * p2 - p2 * q2;
        double resy = o1 * q2 - q1 * o2;

        if(resx >= ax && resx <= bx )
            return true;
        return false;
    }
}
