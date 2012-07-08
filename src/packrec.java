/*
ID: scv1191
LANG: JAVA
TASK: packrec
*/

import java.io.*;
import java.util.*;

public class packrec {

    static int rec[][];
    static int min;
    static List<int[]> list;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("packrec.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
        rec = new int[4][2];
        list = new ArrayList<int[]>();
        min = Integer.MAX_VALUE;
        for(int i = 0 ; i < 4; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            rec[i][0] = Integer.parseInt(st.nextToken());
            rec[i][1] = Integer.parseInt(st.nextToken());
        }
        solve();
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        out.println(min);
        Set<String> set= new HashSet<String>();
        for(int[] arr:list){
            String s= arr[0]+" "+arr[1];
            if(set.contains(s))
                continue;
            out.println(arr[0]+" "+arr[1]);
            set.add(s);
        }
        out.close();
        System.exit(0);
    }

    static void solve(){
        int arr[] = new int[4];
        for(int a = 0; a < 4; a ++)
            for(int b = 0; b < 4; b++)
                for(int c = 0 ; c < 4; c ++)
                    for(int d = 0; d < 4; d ++){
                        if(a == b || a == c || a== d || b == c || b == d || c == d)
                            continue;
                        arr[0] = a;
                        arr[1] = b;
                        arr[2] = c;
                        arr[3] = d;
                        for(int i = 0 ; i<2; i ++)
                            for(int j = 0 ; j < 2; j ++)
                                for(int k = 0 ; k < 2; k ++)
                                    for(int l = 0 ; l < 2; l ++){
                                        solvex(rec[arr[0]][i], rec[arr[0]][(i + 1) % 2], rec[arr[1]][j], rec[arr[1]][(j + 1) % 2], rec[arr[2]][k], rec[arr[2]][(k + 1) % 2], rec[arr[3]][l], rec[arr[3]][(l + 1) % 2]);
                                    }
                    }

    }

    static void solvex(int x1, int y1, int x2, int y2, int x3,int y3, int x4, int y4){
        int w; int h;
        //case 1
        w = x1 + x2 + x3 + x4;
        h = Math.max(y1,Math.max(y2,Math.max(y3,y4)));
        test(w,h);

        //case 2
        w = Math.max(x1 + x2 + x3,x4);
        h = Math.max(y1,Math.max(y2,y3))+y4;
        test(w,h);

        //case 3
        w = Math.max(x1+x2,x4)+x3;
        h = Math.max(y3,Math.max(y1,y2)+y4);
        test(w,h);
        //case 4
        w = x1 + x4 + Math.max(x2,x3);
        h = Math.max(y1,Math.max(y2 + y3,y4));
        test(w,h);
//        //case 5
//        w = x3 + x4 + Math.max(x1,x2);
//        h = Math.max(y3,Math.max(y1 + y2,y4));
//        test(w,h);
        //case 6
        h = Math.max(y1+y3,y2 +y4);
        if(x1<= x3 && x2 <= x4){
            w = x3 + x4;
        }
        else if(x1 > x3 && y4 > y3){
            w = x1 + Math.max(x2,x4);

        }
        else if(x1 > x3 && y4 < y3){
            w = Math.max(x1 + x2, x3+x4);
        }
        test(w,h);
    }

    static void test(int w, int h){
        if(w * h < min){
            min = w * h;
            list.clear();
            int arr[] = new int[2];
            if(w <= h){
                arr[0] = w;
                arr[1] = h;
            }
            else{
                arr[1] = w;
                arr[0] = h;
            }
            list.add(arr);
        }
        if(w*h == min){
            int arr[] = new int[2];
            if(w <= h){
                arr[0] = w;
                arr[1] = h;
            }
            else{
                arr[1] = w;
                arr[0] = h;
            }
            list.add(arr);
        }
    }

}
