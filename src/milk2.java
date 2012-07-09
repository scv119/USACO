/*
ID: scv1191
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
    public static int N = 0;
    static List<Region> list = new ArrayList<Region>();

    static int milk;
    static int idle;

    static class Region{
        int start;
        int end;
    }


    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        N = Integer.parseInt(st.nextToken());    // first integer
        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            Region r = new Region();
            r.start = Integer.parseInt(st.nextToken());
            r.end   = Integer.parseInt(st.nextToken());

            list.add(r);
        }
        solve();
        out.println(milk+" "+idle);                           // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve()
    {
        Collections.sort(list,new Comparator<Region>(){
            public int compare(Region r1, Region r2){
            return r1.start - r2.start;
            }
        });

        int start = -1;
        int end   = 0;
        for(Region r:list){
            if(start == -1){
                start = r.start;
                end   = r.end;
            }
            else{
                if(r.start<=end && r.end > end)
                    end = r.end;
                else if(r.start > end){
                    if(end - start > milk)
                        milk = end - start;
                    if(r.start - end > idle)
                        idle = r.start - end;
                    start = r.start;
                    end   = r.end;
                }
            }
        }
        if(end - start > milk)
            milk = end - start;
    }

}