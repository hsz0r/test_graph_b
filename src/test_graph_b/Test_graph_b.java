/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_graph_b;

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
        test.addEdge(1, 4);
        test.addEdge(3, 2);
        test.addEdge(4, 2);
        test.addEdge(4, 3);



        test.tarjan();
        test.kosaraju();
    }
    
}
