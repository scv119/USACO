/*
ID: scv1191
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.*;

public class gift1 {
    static int len;
    static int[] money;
    static String[] names;
    static Map<String,Integer> map;
    static int[] receive;
    static int[] give;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        len = Integer.parseInt(st.nextToken());
        money = new int[len];
        receive  = new int[len];
        give = new int[len];
        names = new String[len];
        map = new HashMap<String, Integer>();
        for(int i = 0 ; i < len; i ++){
            st = new StringTokenizer(f.readLine());
            String name = st.nextToken();
            map.put(name, i);
            names[i] = name;
        }

        for(int i = 0 ; i < len ; i ++){
            st = new StringTokenizer(f.readLine());
            String name = st.nextToken();
            st = new StringTokenizer(f.readLine());
            int m = Integer.parseInt(st.nextToken());
            int send = Integer.parseInt(st.nextToken());
            int idx = map.get(name);
            money[idx] = m;
            if(send > 0){
                int send_peruser = m/send;
                give[idx] = send_peruser * send;
                for(int j = 0 ; j < send; j ++){
                    st = new StringTokenizer(f.readLine());
                    String rname = st.nextToken();
                    receive[map.get(rname)]+= send_peruser;
                }
            }
        }

        for(int i = 0 ; i < len; i ++){
            int value = receive[i] - give[i];
            out.println(names[i]+" "+value);
        }
        out.close();
        System.exit(0);
    }


}
