/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_graph_b;

import java.util.ArrayList;
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

    void search(int v, int[][] matrix, Stack<Integer> stack) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[v][i] != 0) {
                matrix[v][i] = matrix[i][v] = 0;
                search(i, matrix, stack);
            }
        }
        stack.push(v);
    }

    public void fleury() {
        Stack<Integer> stack = new Stack<Integer>();
        int[][] matrix = convert(adj, vertexes);
        search(0, matrix, stack);
        ArrayList<Integer> stack_contents = new ArrayList<Integer>();
        while (!stack.empty()) {
            stack_contents.add(stack.firstElement());
            stack.pop();
        }
        for (int i = 0; i < stack_contents.size() - 1; i++) {
            if (stack_contents.get(i) == stack_contents.get(i + 1)) {
                ArrayList<Integer> arrayList = new ArrayList<Integer>();
            }
        }
        if (stack.empty()) {
            System.out.println("no euler cycle");
        } else {
            for (Integer i : stack) {
                System.out.println(i + " ");
            }
        }
    }

    public void cycle() {
        int[][] matrix = convert(adj, vertexes);
        int[] deg = new int[vertexes];
        for (int i = 0; i < vertexes; ++i) {
            for (int j = 0; j < vertexes; ++j) {
                deg[i] += matrix[i][j];
            }
        }
        int first = 0;
        while (deg[first] != 0) {
            ++first;
        }
        int v1 = -1, v2 = -1;
        boolean bad = false;
        for (int i = 0; i < vertexes; ++i) {
            if ((deg[i] & 1 ) == 0) {
                if (v1 == -1) {
                    v1 = i;
                } else if (v2 == -1) {
                    v2 = i;
                } else {
                    bad = true;
                }
            }
        }
        if (v1 != -1) {
            ++matrix[v1][v2];
            ++matrix[v2][v1];
        }
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(first);
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (!stack.empty()) {
            int v = stack.firstElement();
            int i;
            for (i = 0; i < vertexes; ++i) {
                if (matrix[v][i] == 0) {
                    break;
                }
            }
            if (i == vertexes) {
                res.add(v);
                stack.pop();
            } else {
                --matrix[v][i];
                --matrix[i][v];
                if (matrix[v][i] < 0) {
                    break;
                }
                stack.push(i);
            }
        }
        if (v1 != -1) {
            for (int i = 0; i + 1 < res.size(); ++i) {
                if (res.get(i) == v1 && res.get(i + 1) == v2 || res.get(i) == v2 && res.get(i + 1) == v1) {
                    ArrayList<Integer> res2 = new ArrayList<Integer>();
                    for (int j = i + 1; j < res.size(); ++j) {
                        res2.add(res.get(j));
                    }
                    for (int j = 1; j <= i; ++j) {
                        res2.add(res.get(j));
                    }
                    res = res2;
                    break;
                }
            }
        }
        for (int i = 0; i < vertexes; ++i) {
            for (int j = 0; j < vertexes; ++j) {
                if (matrix[i][j] == 0) {
                    bad = true;
                }
            }
        }

        if (bad) {
            System.out.println("no euler cycle");
        } else {
            for (int i = 0; i < res.size(); ++i) {
                System.out.println(res.get(i) + 1 + " ");
            }
        }
    }
}
