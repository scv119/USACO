/*
ID: scv1191
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class barn1 {
    static int M;
    static int len;
    static int c;
    static int arr[];

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        M = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());
        int c= Integer.parseInt(st.nextToken());
        arr = new int[len];
        for(int i = 0 ; i < c; i ++ ){
            st = new StringTokenizer(f.readLine());
            int idx = Integer.parseInt(st.nextToken());
            arr[idx-1] = 1;
        }
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static int solve(){
        int start_idx = -1;
        int end_idx = -1;
        int occupied = 0;
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] == 1)   {
                start_idx = i;
                break;
            }
        }

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[arr.length-1-i] == 1)   {
                end_idx = arr.length-1-i;
                break;
            }
        }

        occupied = end_idx - start_idx + 1;
        List<Integer> list = new ArrayList<Integer>();
        int len = 0;
        for(int i = start_idx; i <= end_idx ; i++){
            if(arr[i] == 1){
                if(len > 0)
                    list.add(len);
                len = 0;
            }
            else{
                len ++;
            }
        }
        Collections.sort(list);
        for(int i = list.size()-1; i >=0 ; i--){
            if(M == 1)
                break;
            occupied -= list.get(i);
            M--;
        }
        return occupied;

    }


}
