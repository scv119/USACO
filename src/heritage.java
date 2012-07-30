/*
ID: scv1191
LANG: JAVA
TASK: heritage
*/

import java.io.*;
import java.util.StringTokenizer;

public class heritage {
    static String in_order;
    static String pre_order;

    static Node root;
    static StringBuilder sb;


    static class Node{
        char value;
        Node lchild;
        Node rchild;
    }
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));

        in_order = f.readLine();
        pre_order = f.readLine();
        root = new Node();
        build(root, 0, in_order.length()-1, 0,pre_order.length()-1 );
        sb = new StringBuilder();
        post(root);
        out.println(sb);
        out.close();
        System.exit(0);
    }

    static void build(Node cur, int ioStart, int ioEnd, int poStart, int poEnd){
        cur.value = pre_order.charAt(poStart);
        int ioMid = -1;
        int poMid = -1;
        for(int i = ioStart ; i <= ioEnd; i ++){
            if(in_order.charAt(i) == cur.value){
                ioMid = i;
                break;
            }
        }

        poMid = poEnd - (ioEnd - ioMid);

        if(ioMid > ioStart){
            Node lchild = new Node();
            cur.lchild = lchild;
            build(lchild, ioStart, ioMid-1, poStart + 1, poMid);
        }

        if( ioMid < ioEnd){
            Node rchild = new Node();
            cur.rchild = rchild;
            build(rchild, ioMid+1, ioEnd, poMid + 1, poEnd);
        }
    }

    static void post(Node node){
        if(node.lchild!=null)
            post(node.lchild);
        if(node.rchild!=null)
            post(node.rchild);
        sb.append(node.value);
    }

}
