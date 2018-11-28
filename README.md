# Assignment 6: Directed graphs

Implement all of the following:

  1. From the [creative problems](http://algs4.cs.princeton.edu/42digraph/) for
     directed graphs:

     * 33: Unique topological ordering.

     Design an algorithm to determine whether a digraph has a unique topological ordering.  Hint: a
     digraph has a unique topological ordering if and only if there is a directed edge between each pair
     of consecutive vertices in the topological order (i.e., the digraph has a Hamiltonian path). If the
     digraph has multiple topological orderings, then a second topological order can be obtained by
     swapping a pair of consecutive vertices.

  2. From the [web exercises](http://algs4.cs.princeton.edu/42digraph/) for
     directed graphs:
     
     * 30: One-way street theorem

     Implement an algorithm to orient the edges in an undirected graph so that it is strongly
     connected. Robbins theorem asserts that this is possible if and only if the undirected graph is
     [two-edge connected](https://en.wikipedia.org/wiki/K-edge-connected_graph) (no bridges).
     In this case, a solution is to run DFS and oriented all edges in
     the DFS tree away from the root and all of the remaining edges toward the root.

     * 34: Number of paths in a DAG

     Given a DAG and two distinguished vertices s and t, design a linear-time algorithm to compute the number
     of directed paths from s to t.  **Hint: topological sort.**

     * 35: Path of length L in a DAG

     Given a DAG and two distinguished vertices s and t, design an algorithm to determine if there exists a path
     from s to t containing exactly L edges.


To save you some time, your repository contains most of the classes from the book that you will
need, or might consider using.
