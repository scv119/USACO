/*
ID: scv1191
LANG: JAVA
TASK: butter
*/

import java.io.*;
import java.util.*;

public class butter {
    static int P;
    static int C;
    static int N;

    static List[] g;
    static List<Integer> cow;
    static int dis[][];

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("butter.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cow = new ArrayList<Integer>();
        g   = new List[P];
        for(int i = 0 ; i < P ; i ++){
            g[i] = new ArrayList<int[]>();
        }

        for(int i = 0  ;i  < N ; i ++){
            st = new StringTokenizer(f.readLine());
            cow.add(Integer.parseInt(st.nextToken())-1);
        }

        for(int i = 0; i < C; i ++){
            st = new StringTokenizer(f.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int len = Integer.parseInt(st.nextToken());
            g[s].add(new int[]{e,len});
            g[e].add(new int[]{s,len});
        }

        dis = new int[P][P];

        out.println(solve());
        out.close();
        System.exit(0);
    }

    static int INF = Integer.MAX_VALUE;
    static class Node implements Comparable{
        int id;
        int value;
        boolean visited;

        @Override
        public int compareTo(Object o) {
            return this.value - ((Node)o).value;
        }
    }

    static int solve(){
        int min = INF;
        for(int x :cow){
            dijkstra(x);
        }
        for(int n = 0 ;  n < P ; n ++){
            int res  = 0;
            for(int x:cow){
                if(dis[x][n] == INF){
                    res = INF;
                    break;
                }
                else{
                    res += dis[x][n];
                }
            }
            if(res < min)
                min = res;
        }

        return min;
    }

    static void dijkstra(int start){

        Node[] list = new Node[P];
        for(int i = 0 ; i < P ; i ++){
            list[i] = new Node();
            list[i].id = i;
            list[i].value = INF;
            list[i].visited = false;
        }

        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        list[start].visited = true;
        list[start].value = 0;

        for(Node n:list){
            heap.add(n);
        }

        while(heap.size()>0){
            Node n = heap.poll();
            if(n.value == INF)
                break;
            n.visited = true;
            for(Object o:g[n.id]){
                int[] arr = (int[])o;
                int id = arr[0];
                int dis = arr[1];
                if(list[id].value > (n.value + dis)){
                    list[id].value = n.value + dis;
                    if(list[id].visited == false){
                        heap.remove(list[id]);
                        heap.add(list[id]);
                    }
                }
            }
        }

        for(Node node:list){
            dis[start][node.id] = node.value;
        }
    }
}
