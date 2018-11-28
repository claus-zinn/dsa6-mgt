package de.unituebingen.sfs.digraph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import graph.*;

public class OneWayStreet {
    public static Digraph oneWayStreet(Graph g) {
	
        Digraph digraph = new Digraph(g.V());

	// your code to construct the strongly connected digraph comes here...

        return digraph;
    }

    public static void main(String[] args) {
	
        // A given: creation of a random graph consisting of one CC that does not have a bridge.
        Graph g;
        while (true) {
            g = GraphGenerator.simple(10, 0.1);

            CC cc = new CC(g);
            Bridge bridge = new Bridge(g);

            if (cc.count() == 1 && bridge.components() == 1)
                break;
        }

        Digraph dg = OneWayStreet.oneWayStreet(g);

        // Your code to check whether graph and digraph have the same number of vertices, and (directed) edges COMES HERE

        // Your code to check whether graph is indeed strongly connected COMES HERE

	// printing the graph
        System.out.println(Dot.toDot(g));
        System.out.println(Dot.toDot(dg));
    }
}
