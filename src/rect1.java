/*
ID: scv1191
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class rect1 {
    static class rect{
        int llx;
        int lly;
        int urx;
        int ury;
        int color;

        public rect(int llx, int lly, int urx, int ury, int color){
            this.llx = llx;
            this.lly = lly;
            this.urx = urx;
            this.ury = ury;
            this.color = color;
        }

        public List<rect> diff(rect rect1){
            List<rect> list = new ArrayList<rect>(4);
            int llx = Math.max(this.llx, rect1.llx);
            int lly = Math.max(this.lly, rect1.lly);
            int urx = Math.min(this.urx, rect1.urx);
            int ury = Math.min(this.ury, rect1.ury);
            if(llx < urx && lly < ury){
                if(llx > this.llx){
                    list.add(new rect(this.llx, this.lly, llx, this.ury, this.color));
                }
                if(urx < this.urx){
                    list.add(new rect(urx, this.lly, this.urx, this.ury, this.color));
                }
                if(ury < this.ury){
                    list.add(new rect(llx,ury,urx,this.ury,this.color));
                }
                if(this.lly < lly){
                    list.add(new rect(llx,this.lly,urx, lly, this.color));
                }
            }
            else{
                list.add(this);
            }
            return list;
        }
    }

    static int X;
    static int Y;
    static int N;
    static int count[];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("rect1.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        List<rect> list = new ArrayList<rect>();
        list.add(new rect(0,0,X,Y,1));
        count = new int[2501];
        for(int i = 0 ; i <N ; i ++){
            List<rect> tmp = new ArrayList<rect>();
            st = new StringTokenizer(f.readLine());
            int llx = Integer.parseInt(st.nextToken());
            int lly = Integer.parseInt(st.nextToken());
            int urx = Integer.parseInt(st.nextToken());
            int ury = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            rect r = new rect(llx,lly,urx,ury,color);
            tmp.add(r);
            for(rect r1:list){
                List<rect> results =r1.diff(r);
                tmp.addAll(results);
            }
            list = tmp;
        }
        for(rect r:list){
            count[r.color] += (r.urx-r.llx) * (r.ury - r.lly);
        }
        for(int i = 1; i < 2501; i ++){
            if(count[i]>0)
                out.println(i+" "+count[i]);
        }
        out.close();
        System.exit(0);
    }


}
