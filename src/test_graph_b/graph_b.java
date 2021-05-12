/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_graph_b;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author hsz0r
 */
public class graph_b {

    public int vertexes;
    public Vector<Integer> adj[];

    public graph_b(int v) {
        vertexes = v;
        adj = new Vector[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new Vector();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    private int[][] convert(Vector<Integer> adj[], int V) {
        int[][] matrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j : adj[i]) {
                matrix[i][j] = 1;
            }
        }
        return matrix;
    }

    private void dfs(int v, boolean visited[], Stack stack) {
        visited[v] = true;
        Integer i;
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }
        stack.push(new Integer(v));
    }

    private graph_b transpose() {
        graph_b graph = new graph_b(vertexes);
        for (int v = 0; v < vertexes; v++) {
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                graph.adj[i.next()].add(v);
            }
        }
        return graph;
    }

    public void tarjan() {
        Stack stack = new Stack();
        boolean visited[] = new boolean[vertexes];
        for (int i = 0; i < vertexes; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < vertexes; i++) {
            if (visited[i] == false) {
                dfs(i, visited, stack);
            }
        }
        while (stack.empty() == false) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public void kosaraju() {
        Stack stack = new Stack();
        boolean visited[] = new boolean[vertexes];
        for (int i = 0; i < vertexes; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < vertexes; i++) {
            if (visited[i] == false) {
                dfs(i, visited, stack);
            }
        }
        graph_b gr = transpose();

        for (int i = 0; i < vertexes; i++) {
            visited[i] = false;
        }

        while (stack.empty() == false) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
    
    public void fleury(){
        
    }
}
