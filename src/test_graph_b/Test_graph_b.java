/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_graph_b;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author hsz0r
 */
public class Test_graph_b {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        graph_b test = new graph_b(5);

        test.addEdge(0, 1);
        test.addEdge(0, 2);
        test.addEdge(0, 3);
        test.addEdge(0, 4);
        test.addEdge(2, 0);
        test.addEdge(2, 4);
        test.addEdge(3, 0);
        test.addEdge(3, 1);
        test.addEdge(3, 4);
        test.addEdge(4, 0);
        test.addEdge(4, 1);
        
        test.tarjan();
        test.fleury();
        test.kosaraju();
        test.cycle();
    }
}
