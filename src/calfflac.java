/*
ID: scv1191
LANG: JAVA
TASK: calfflac
*/

import java.io.*;

public class calfflac {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("calfflac.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")),true);

        char[] text = new char[20000];
        int textLength = 0;
        while(in.ready())
            text[textLength++] = (char)in.read();
        String s = new String(text, 0, textLength);

        int resLen = 0;
        String res = null;
        String ss = null;

        for(int i = 0; i < s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                ss = res(s,i,i);
                if(ss != null && ff(ss) > resLen){
                    resLen = ff(ss);
                    res = ss;
                }
            }
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isLetter(c)){
                int ncpos = ssa(s, c, i + 1);
                if(ncpos != -1){
                    ss = res(s,i,ncpos);
                    if(ss != null && ff(ss) > resLen){
                        resLen = ff(ss);
                        res = ss;
                    }
                }
            }
        }

        out.println(resLen);
        out.println(res);
        System.exit(0);
    }

    private static int ssa(String s, char c, int beg){
        for(int i = beg; i < s.length(); i++){
            if(s.charAt(i) == c)
                return i;
            else if(Character.isLetter(s.charAt(i)))
                return -1;
        }
        return -1;
    }

    private static int ff(String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetter(s.charAt(i)))
                count ++;
        }
        return count;
    }

    private static String res(String s, int low, int high){
        int first = low,last = high;
        while(low >=0 && high <= s.length()-1){
            if(! Character.isLetter(s.charAt(low))){
                low--;
                continue;
            }
            if(!Character.isLetter(s.charAt(high))){
                high++;
                continue;
            }
            if(!isCharEqual(s.charAt(low),s.charAt(high)))
                return s.substring(first,last+1);
            else{
                first = low; last = high;
                low--; high++;
            }
        }
        return s.substring(first,last+1);
    }

    private static boolean isCharEqual(char c1, char c2){
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }
}
