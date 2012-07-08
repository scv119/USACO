/*
ID: scv1191
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

public class frac1 {
    static Map<Float,float[]> map;
    static int N;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new HashMap<Float, float[]>();
        for(int i = 1 ; i <= N; i ++)
            for(int j = 0 ; j <= i; j ++){
                float value = 1f * j/i;
                if(map.containsKey(value))
                    continue;
                int x = gcd(j,i);
                map.put(value, new float[]{value, j/x,i/x});
            }
        List<float[]> list = new ArrayList<float[]>(map.size());
        for(float[] arr:map.values()){
            list.add(arr);
        }
        Collections.sort(list, new Comparator<float[]>() {
            @Override
            public int compare(float[] floats, float[] floats1) {
                if(floats[0] - floats1[0] < 0)
                    return -1;
                else if(floats[0] - floats1[0] == 0)
                    return 0;
                return 1;
            }
        });
        for(float[]  arr:list){
            out.println((int)arr[1]+"/"+(int)arr[2]);
        }
        out.close();
        System.exit(0);
    }

    static int gcd(int i, int j){
        return i == 0 ? j : gcd(j%i,i);
    }
}
