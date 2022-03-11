package DirectedGraph;

import java.util.*;

public class DirectedGraph {

    public static class Vertex {
        private String name;

        Vertex(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vertex)) return false;
            return Objects.equals(this.name, ((Vertex) obj).name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static class Arc {
        private final Vertex begin;
        private final Vertex end;
        private int weight;

        Arc(Vertex begin, Vertex end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }

        public void setWeight(int value) {
            weight = value;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Arc)) return false;
            Arc arc = (Arc) obj;
            return weight == arc.weight && begin.equals(arc.begin) && end.equals(arc.end);
        }

        @Override
        public int hashCode() {
            return 2 * begin.hashCode() + end.hashCode() + weight;
        }
    }

    private final Map<String, Vertex> vertexes = new HashMap<>();
    private final Set<Arc> arcs = new HashSet<>();

    public DirectedGraph(Set<Vertex> vertexSet, Set<Arc> arcs) {
        this.arcs.addAll(arcs);
        for (Vertex vertex : vertexSet) this.vertexes.put(vertex.name, vertex);
        for (Arc i : arcs) {
            this.vertexes.put(i.begin.name, i.begin);
            this.vertexes.put(i.end.name, i.end);
        }
    }

    public Vertex getVertex(String name) {
        return vertexes.get(name);
    }

    public void addVertex(Vertex vertex) {
        vertexes.put(vertex.name, vertex);
    }

    public void addVertex(String name) {
        addVertex(new Vertex(name));
    }

    public void deleteVertex(Vertex vertex) {
        arcs.removeAll(getIncomingArcs(vertex));
        arcs.removeAll(getOutcomingArcs(vertex));
        vertexes.remove(vertex.name);
    }

    public void setNameVertex(Vertex vertex, String newName) {
        vertexes.remove(vertex.name);
        vertex.name = newName;
        vertexes.put(vertex.name, vertex);
    }

    public Arc getArcByName(String begin, String end) {
        for (Arc i : arcs) {
            if (Objects.equals(i.begin.name, begin) && Objects.equals(i.end.name, end)) return i;
        }
        return null;
    }

    public Arc getArcByVertexes(Vertex begin, Vertex end) {
        for (Arc i : arcs) {
            if (begin.equals(i.begin) && end.equals(i.end)) return i;
        }
        return null;
    }

    public void addArc(String begin, String end, int weight) {
        Vertex beginVertex;
        Vertex endVertex;
        if (vertexes.get(begin) != null) {
            beginVertex = getVertex(begin);
        } else {
            beginVertex = new Vertex(begin);
            addVertex(beginVertex);
        }
        if (vertexes.get(end) != null) {
            endVertex = getVertex(end);
        } else {
            endVertex = new Vertex(end);
            addVertex(endVertex);
        }
        arcs.add(new Arc(beginVertex, endVertex, weight));
    }

    public void deleteArc(String begin, String end) {
        arcs.remove(getArcByName(begin, end));
    }

    public Set<Arc> getIncomingArcs(Vertex vertex) {
        Set<Arc> result = new HashSet<>();
        for (Arc arc : arcs) {
            if (arc.end.equals(vertex)) result.add(arc);
        }
        return result;
    }

    public Set<Arc> getOutcomingArcs(Vertex vertex) {
        Set<Arc> result = new HashSet<>();
        for (Arc arc : arcs) {
            if (arc.begin.equals(vertex)) result.add(arc);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DirectedGraph)) return false;
        DirectedGraph graph = (DirectedGraph) obj;
        return arcs.equals(graph.arcs) && vertexes.equals(graph.vertexes);
    }

    @Override
    public int hashCode() {
        return vertexes.hashCode() + arcs.hashCode();
    }
}
