/*
ID: scv1191
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

class namenum {
    static Set<String> set;
    static String map[] = new String[]{"","","ABC","DEF","GHI","JKL","MNO","PRS","TUV","WXY"};

    public static void main (String [] args) throws IOException {
        set = new HashSet<String>();
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader f1 = new BufferedReader(new FileReader("dict.txt"));
        String line;
        while(true){
            line = f1.readLine();
            if(line == null)
                break;
            StringTokenizer st  = new StringTokenizer(line);
            set.add(st.nextToken());
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        String input = st.nextToken();    // first integer
        List<String> result = get(input);
        if(result.size()>0)
        for(String s:result)
            out.println(s);
        else
            out.println("NONE");
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static List<String> get(String n){
        List<String> result = new ArrayList<String>();
        solve("",n,result);
        return result;
    }

    static void solve(String pre, String left, List<String> save){
        if(left.length() == 0){
            if(set.contains(pre))
                save.add(pre);
            return;
        }

        int k = left.charAt(0) - '0';
        left = left.substring(1);
        String s = map[k];
        for(int i = 0 ; i <3; i ++){
            solve(pre+s.charAt(i),left,save);
        }
    }

}