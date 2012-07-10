/*
ID: scv1191
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {

    static int g[][];
    static int N;
    static int INF = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));


        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());

        g = new int[N][N];

        st = new StringTokenizer(f.readLine());
        for(int i = 0 ; i < N ;i ++){

            for(int j = 0 ; j< N; j ++){
                if(!st.hasMoreElements())
                    st = new StringTokenizer(f.readLine());
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        out.println(prim());



        out.close();
        System.exit(0);
    }


    static class Node implements Comparable{
        int id;
        int value;
        int visit;


        @Override
        public int compareTo(Object o) {
            return this.value - ((Node)o).value;
        }
    }

    static int prim(){
        Node list[] = new Node[N];
        for(int i = 0; i < N; i ++)
        {
            list[i] = new Node();
            list[i].id = i;
            list[i].value = INF;
        }
        list[0].value = 0;

        PriorityQueue<Node> q = new PriorityQueue<Node>();
        for(Node node:list)
            q.add(node);

        int ret = 0;

        while(q.size()>0){
            Node node = q.poll();
            if(node.value == INF)
                break;
            node.visit = 1;
            ret += node.value;

            for(int j = 0 ; j <  N ; j ++){
                if(g[node.id][j]<list[j].value && list[j].visit == 0)
                {
                    list[j].value = g[node.id][j];
                    q.remove(list[j]);
                    q.offer(list[j]);
                }
            }
        }
        return ret;

    }

}
