/******************************************************************************
 *  Compilation:  javac GraphGenerator.java
 *  Execution:    java GraphGenerator V E
 *  Dependencies: Graph.java
 *
 *  A graph generator.
 *
 *  For many more graph generators, see
 *  http://networkx.github.io/documentation/latest/reference/generators.html
 *
 ******************************************************************************/

package graph;

import util.SET;
import util.StdOut;
import util.StdRandom;

/**
 *  The <tt>GraphGenerator</tt> class provides static methods for creating
 *  various graphs, including Erdos-Renyi random graphs, random bipartite
 *  graphs, random k-regular graphs, and random rooted trees.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphGenerator {
    private static final class Edge implements Comparable<Edge> {
        private int v;
        private int w;

        private Edge(int v, int w) {
            if (v < w) {
                this.v = v;
                this.w = w;
            }
            else {
                this.v = w;
                this.w = v;
            }
        }

        public int compareTo(Edge that) {
            if (this.v < that.v) return -1;
            if (this.v > that.v) return +1;
            if (this.w < that.w) return -1;
            if (this.w > that.w) return +1;
            return 0;
        }
    }

    // this class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Returns a random simple graph containing <tt>V</tt> vertices and <tt>E</tt> edges.
     * @param V the number of vertices
     * @param E the number of vertices
     * @return a random simple graph on <tt>V</tt> vertices, containing a total
     *     of <tt>E</tt> edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static Graph simple(int V, int E) {
        if (E > (long) V*(V-1)/2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                throw new IllegalArgumentException("Too few edges");
        Graph G = new Graph(V);
        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            Edge e = new Edge(v, w);
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(v, w);
            }
        }
        return G;
    }

    /**
     * Returns a random simple graph on <tt>V</tt> vertices, with an 
     * edge between any two vertices with probability <tt>p</tt>. This is sometimes
     * referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on <tt>V</tt> vertices, with an edge between
     *     any two vertices with probability <tt>p</tt>
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph simple(int V, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        Graph G = new Graph(V);
        for (int v = 0; v < V; v++)
            for (int w = v+1; w < V; w++)
                if (StdRandom.bernoulli(p))
                    G.addEdge(v, w);
        return G;
    }

    /**
     * Returns the complete graph on <tt>V</tt> vertices.
     * @param V the number of vertices
     * @return the complete graph on <tt>V</tt> vertices
     */
    public static Graph complete(int V) {
        return simple(V, 1.0);
    }

    /**
     * Returns a complete bipartite graph on <tt>V1</tt> and <tt>V2</tt> vertices.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @return a complete bipartite graph on <tt>V1</tt> and <tt>V2</tt> vertices
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph completeBipartite(int V1, int V2) {
        return bipartite(V1, V2, V1*V2);
    }

    /**
     * Returns a random simple bipartite graph on <tt>V1</tt> and <tt>V2</tt> vertices
     * with <tt>E</tt> edges.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param E the number of edges
     * @return a random simple bipartite graph on <tt>V1</tt> and <tt>V2</tt> vertices,
     *    containing a total of <tt>E</tt> edges
     * @throws IllegalArgumentException if no such simple bipartite graph exists
     */
    public static Graph bipartite(int V1, int V2, int E) {
        if (E > (long) V1*V2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)            throw new IllegalArgumentException("Too few edges");
        Graph G = new Graph(V1 + V2);

        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int i = StdRandom.uniform(V1);
            int j = V1 + StdRandom.uniform(V2);
            Edge e = new Edge(vertices[i], vertices[j]);
            if (!set.contains(e)) {
                set.add(e);
                G.addEdge(vertices[i], vertices[j]);
            }
        }
        return G;
    }

    /**
     * Returns a random simple bipartite graph on <tt>V1</tt> and <tt>V2</tt> vertices,
     * containing each possible edge with probability <tt>p</tt>.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param p the probability that the graph contains an edge with one endpoint in either side
     * @return a random simple bipartite graph on <tt>V1</tt> and <tt>V2</tt> vertices,
     *    containing each possible edge with probability <tt>p</tt>
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph bipartite(int V1, int V2, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        Graph G = new Graph(V1 + V2);
        for (int i = 0; i < V1; i++)
            for (int j = 0; j < V2; j++)
                if (StdRandom.bernoulli(p))
                    G.addEdge(vertices[i], vertices[V1+j]);
        return G;
    }

    /**
     * Returns a path graph on <tt>V</tt> vertices.
     * @param V the number of vertices in the path
     * @return a path graph on <tt>V</tt> vertices
     */
    public static Graph path(int V) {
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }

    /**
     * Returns a complete binary tree graph on <tt>V</tt> vertices.
     * @param V the number of vertices in the binary tree
     * @return a complete binary tree graph on <tt>V</tt> vertices
     */
    public static Graph binaryTree(int V) {
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[i], vertices[(i-1)/2]);
        }
        return G;
    }

    /**
     * Returns a cycle graph on <tt>V</tt> vertices.
     * @param V the number of vertices in the cycle
     * @return a cycle graph on <tt>V</tt> vertices
     */
    public static Graph cycle(int V) {
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[V-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian cycle graph on <tt>V</tt> vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a graph that is an Eulerian cycle on <tt>V</tt> vertices
     *         and <tt>E</tt> edges
     * @throws IllegalArgumentException if either V &le; 0 or E &le; 0
     */
    public static Graph eulerianCycle(int V, int E) {
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        Graph G = new Graph(V);
        int[] vertices = new int[E];
        for (int i = 0; i < E; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[E-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian path graph on <tt>V</tt> vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a graph that is an Eulerian path on <tt>V</tt> vertices
     *         and <tt>E</tt> edges
     * @throws IllegalArgumentException if either V &le; 0 or E &lt; 0
     */
    public static Graph eulerianPath(int V, int E) {
        if (E < 0)
            throw new IllegalArgumentException("negative number of edges");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian path must have at least one vertex");
        Graph G = new Graph(V);
        int[] vertices = new int[E+1];
        for (int i = 0; i < E+1; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }

    /**
     * Returns a wheel graph on <tt>V</tt> vertices.
     * @param V the number of vertices in the wheel
     * @return a wheel graph on <tt>V</tt> vertices: a single vertex connected to
     *     every vertex in a cycle on <tt>V-1</tt> vertices
     */
    public static Graph wheel(int V) {
        if (V <= 1) throw new IllegalArgumentException("Number of vertices must be at least 2");
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // simple cycle on V-1 vertices
        for (int i = 1; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[V-1], vertices[1]);

        // connect vertices[0] to every vertex on cycle
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], vertices[i]);
        }

        return G;
    }

    /**
     * Returns a star graph on <tt>V</tt> vertices.
     * @param V the number of vertices in the star
     * @return a star graph on <tt>V</tt> vertices: a single vertex connected to
     *     every other vertex
     */
    public static Graph star(int V) {
        if (V <= 0) throw new IllegalArgumentException("Number of vertices must be at least 1");
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // connect vertices[0] to every other vertex
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], vertices[i]);
        }

        return G;
    }

    /**
     * Returns a uniformly random <tt>k</tt>-regular graph on <tt>V</tt> vertices
     * (not necessarily simple). The graph is simple with probability only about e^(-k^2/4),
     * which is tiny when k = 14.
     * @param V the number of vertices in the graph
     * @return a uniformly random <tt>k</tt>-regular graph on <tt>V</tt> vertices.
     */
    public static Graph regular(int V, int k) {
        if (V*k % 2 != 0) throw new IllegalArgumentException("Number of vertices * k must be even");
        Graph G = new Graph(V);

        // create k copies of each vertex
        int[] vertices = new int[V*k];
        for (int v = 0; v < V; v++) {
            for (int j = 0; j < k; j++) {
                vertices[v + V*j] = v;
            }
        }

        // pick a random perfect matching
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V*k/2; i++) {
            G.addEdge(vertices[2*i], vertices[2*i + 1]);
        }
        return G;
    }

    /**
     * Unit tests the <tt>GraphGenerator</tt> library.
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int V1 = V/2;
        int V2 = V - V1;

        StdOut.println("complete graph");
        StdOut.println(complete(V));
        StdOut.println();

        StdOut.println("simple");
        StdOut.println(simple(V, E));
        StdOut.println();

        StdOut.println("Erdos-Renyi");
        double p = (double) E / (V*(V-1)/2.0);
        StdOut.println(simple(V, p));
        StdOut.println();

        StdOut.println("complete bipartite");
        StdOut.println(completeBipartite(V1, V2));
        StdOut.println();

        StdOut.println("bipartite");
        StdOut.println(bipartite(V1, V2, E));
        StdOut.println();

        StdOut.println("Erdos Renyi bipartite");
        double q = (double) E / (V1*V2);
        StdOut.println(bipartite(V1, V2, q));
        StdOut.println();

        StdOut.println("path");
        StdOut.println(path(V));
        StdOut.println();

        StdOut.println("cycle");
        StdOut.println(cycle(V));
        StdOut.println();

        StdOut.println("binary tree");
        StdOut.println(binaryTree(V));
        StdOut.println();

        StdOut.println("4-regular");
        StdOut.println(regular(V, 4));
        StdOut.println();

        StdOut.println("star");
        StdOut.println(star(V));
        StdOut.println();

        StdOut.println("wheel");
        StdOut.println(wheel(V));
        StdOut.println();
    }

}