/*
ID: scv1191
LANG: JAVA
TASK: cryptcow
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class cryptcow {

    static char[] s = "Begin the Escape execution at the Break of Dawn".toCharArray();
    static char[] dest;
    static int Cidx = 'C' - 'A'  + 26;
    static int Oidx = 'O' - 'A'  + 26;
    static int Widx = 'W' - 'A'  + 26;

    static Map<String,Boolean> map = new HashMap<String,Boolean>() ;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("cryptcow.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cryptcow.out")));

        dest = f.readLine().toCharArray();
        int depth = valid(dest);
        if(depth == -1){
            out.println("0 0");
            out.close();
            System.exit(0);
        }

        if(dfsid(dest, depth))
            out.println("1 "+depth);
        else
            out.println("0 0");
        out.close();
        System.exit(0);
    }

    static boolean dfsid(char[] arr, int depth){
        String ss= new String(arr);
        if(map.containsKey(ss))
            return map.get(ss) ;

//        System.out.println(arr);
        if(depth == 0){
            if(equals(arr,s)) {
                map.put(ss,true);
                return true;
            }
            else{
                map.put(ss,false);
                return false;
            }
        }

        char next[] = new char[arr.length-3];

        int Cpos[] = new int[depth];
        int Opos[] = new int[depth];
        int Wpos[] = new int[depth];
        int odx = 0;
        int cdx = 0;
        int wdx = 0;
        char first = ' ';
        int fidx = -1;
        char last  = ' ';
        int lidx  = -1;
        for(int i = 0 ; i < arr.length ; i ++){
            char c = arr[i];
            if(c == 'C' || c == 'O' || c == 'W') {
                if(first == ' ') {
                    first = c;
                    fidx = i;
                }
                last = c;
                lidx = i;
            }
            if(c == 'O')
                Opos[odx++] = i;
            if(c == 'C')
                Cpos[cdx++] = i;
            if(c == 'W')
                Wpos[wdx++] = i;
        }
        if(first != 'C' || last != 'W')
            return false;
        boolean ret = false;
        for(int i:Cpos){

            for(int j:Opos){
                if(j<=i)
                    continue;


                for(int k:Wpos){

                    if(k<=j)
                        continue;

                    if(i > Cpos[0] && k <Opos[Opos.length-1] || i > Opos[0] && k < Wpos[Wpos.length-1])
                        continue;


                    swap(arr, next, i,j,k);
                    ret = dfsid(next, depth - 1);
                    if(ret) {
                        map.put(ss,ret);
                        return ret;
                    }
                }
            }
        }
        map.put(ss,ret);

        return false;
    }

    static boolean equals(char[] arr, char[] arr1){
        if(arr.length != arr1.length)
            return false;
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] != arr1[i])
                return false;
        }
        return true;
    }

    static void swap(char[] arr, char[]next, int cidx, int oidx, int fidx){
        cp(arr,0,next,0,cidx);
        cp(arr,oidx+1,next,cidx,fidx-oidx-1);
        cp(arr,cidx+1,next,cidx+fidx-oidx-1,oidx-cidx-1);
        cp(arr,fidx+1,next,fidx-2,arr.length-fidx-1);
    }

    static void cp(char[] src, int start, char[] dest, int start1, int len){
        for(int i = 0 ; i < len ; i ++)
            dest[start1+i] = src[start+i];
    }

    static int c2i(char c){
        int idx = 0;
        if(c == ' ')
            return 52;
        if(c<='Z')
            idx = c - 'A' + 26;
        else
            idx = c - 'a';
        return idx;
    }

    static int valid(char[] arr){
        int count[] = new int[53];
        for(char c:s){
            count[c2i(c)] ++;
        }
        int count_dest[] = new int[53];
        for(char c:arr){
            count_dest[c2i(c)]++;
        }
        for(int i = 0 ; i < 53; i ++){
            if(i != (Cidx) && i != (Oidx) && i != (Widx) && count[i] != count_dest[i])
                return -1;
            count_dest[i] -= count[i];
        }
        if(count_dest[Cidx] == count_dest[Oidx] && count_dest[Cidx] == count_dest[Widx] && count_dest[Widx] >= 0)
            return count_dest[Widx];
        return -1;
    }
}
