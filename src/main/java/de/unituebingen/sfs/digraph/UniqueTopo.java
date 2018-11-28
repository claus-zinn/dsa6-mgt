package de.unituebingen.sfs.digraph;

import com.google.common.collect.Iterables;
import graph.Digraph;
import graph.Topological;

import java.util.ArrayList;
import java.util.List;

public class UniqueTopo {
    public static boolean uniqueTopo(Digraph g) {

	// your code comes HERE

        return true; // or false
    }

    public static void main(String[] args) {

	// some test input
        Digraph bookGraph = new Digraph(7);
        bookGraph.addEdge(0, 5);
        bookGraph.addEdge(0, 1);
        bookGraph.addEdge(3, 5);
        bookGraph.addEdge(5, 2);
        bookGraph.addEdge(6, 0);
        bookGraph.addEdge(1, 4);
        bookGraph.addEdge(0, 2);
        bookGraph.addEdge(3, 6);
        bookGraph.addEdge(3, 4);
        bookGraph.addEdge(6, 4);
        bookGraph.addEdge(3, 2);

        System.out.println(uniqueTopo(bookGraph));

        bookGraph.addEdge(2, 1);

        System.out.println(uniqueTopo(bookGraph));
    }
}
