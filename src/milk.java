/*
ID: scv1191
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.*;

public class milk {
    static class farmer{
        int price;
        int count;
    }
    static int total;
    static List<farmer> list;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        total = Integer.parseInt(st.nextToken());
        list = new ArrayList<farmer>();
        int count = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < count; i ++){
            st = new StringTokenizer(f.readLine());
            farmer ff = new farmer();
            ff.price = Integer.parseInt(st.nextToken());
            ff.count = Integer.parseInt(st.nextToken());
            list.add(ff);
        }
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static int solve(){
        Collections.sort(list, new Comparator<farmer>() {
            public int compare(farmer f1, farmer f2){
                return f1.price - f2.price;
            }
        });
        int result = 0;
        for(farmer f:list){
            if(total<=f.count){
                result += total * f.price;
                break;
            }
            else{
                result += f.count * f.price;
                total -= f.count;
            }
        }
        return result;
    }
}
