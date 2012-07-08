/*
ID: scv1191
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.StringTokenizer;

public class ride {
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        String group = st.nextToken();
        st = new StringTokenizer(f.readLine());
        String comet = st.nextToken();

        out.println(ok(comet, group));
        out.close();
        System.exit(0);
    }

    static String ok(String comet, String group){
        int c = 1;
        int g = 1;
        for(int i = 0 ; i < comet.length() ; i ++)
        {
            char cc = comet.charAt(i);
            c = c * (cc - 'A' + 1) % 47;
        }

        for(int i = 0 ; i < group.length() ; i ++)
        {
            char cc = group.charAt(i);
            g = g * (cc - 'A' + 1) % 47;
        }

        if(c == g)
            return "GO";
        return "STAY";
    }
}
