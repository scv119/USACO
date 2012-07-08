/*
ID: scv1191
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.StringTokenizer;

public class beads {
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
        f.readLine();
        StringTokenizer st = new StringTokenizer(f.readLine());
        String beads = st.nextToken();
        out.println(solve(beads));
        out.close();
        System.exit(0);
    }

    static int solve(String beads){
        int len = beads.length();
        int max = 0;

        for(int i = 0 ; i < len; i ++){
            char left = 'w';
            char right = 'w';
            int value = 0;
            for(int j = 0; j < len; j ++){
                char c = beads.charAt((j+i)%len);
                if(right != 'w' && c != 'w' && right != c)
                    break;
                if(right == 'w')
                    right = c;
                value ++;
            }

            for(int j = 0; j < len; j ++){
                char c = beads.charAt((i-1-j+len)%len);
                if(left != 'w' && c != 'w' && left != c)
                    break;
                if(left == 'w')
                    left = c;
                value ++;
            }
            if(value > len)
                value = len;
            if(value > max)
                max = value;
        }
        return max;

    }
}
