package de.unituebingen.sfs.digraph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import graph.Digraph;
import graph.DigraphGenerator;
import graph.Dot;
import graph.Topological;
import util.StdRandom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Paths {

    // ex 34
    public static int numberDirectedPaths(Digraph g, int s, int t) {

	// your code HERE 

        return 0; // 
    }

    // ex 35
    public static boolean hasPathLength(Digraph g, int s, int t, int length) {

	// your code HERE 	
        return true; // or false;
    }

    public static void main(String[] args) {

	// create a random graph, with random vertices
	Digraph dag = DigraphGenerator.dag(10, 30);
	int v1 = StdRandom.uniform(dag.V());
	int v2 = StdRandom.uniform(dag.V());

	// then do call your code above to test/run it.
    }
}
