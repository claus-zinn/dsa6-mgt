package graph;

public class Dot {
    public static String toDot(Digraph g) {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph {\n");

        for (int v = 0; v < g.V(); ++v) {
            for (int to : g.adj(v)) {
                builder.append(String.format("%d -> %d%n", v, to));
            }
        }

        builder.append("}");

        return builder.toString();
    }

    public static String toDot(Graph g) {
        StringBuilder builder = new StringBuilder();
        builder.append("graph {\n");

        for (int v = 0; v < g.V(); ++v) {
            for (int to : g.adj(v)) {
                if (to >= v)
                    builder.append(String.format("%d -- %d%n", v, to));
            }
        }

        builder.append("}");

        return builder.toString();
    }
}
