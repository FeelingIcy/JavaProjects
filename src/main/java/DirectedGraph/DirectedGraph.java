package DirectedGraph;

import java.util.*;

public class DirectedGraph {

    public static class Vertex{
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

        Arc(Vertex begin, Vertex end, int weight){
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
            return 2*begin.hashCode()+end.hashCode()+17*weight;
        }
    }

    private final Set<Vertex> vertexes = new HashSet<>();
    private final Set<Arc> arcs = new HashSet<>();

    public DirectedGraph() {

    }

    public DirectedGraph(Set<Vertex> vertexes, Set<Arc> arcs) {
        this.vertexes.addAll(vertexes);
        this.arcs.addAll(arcs);
        for (Arc i: arcs) {
            this.vertexes.add(i.begin);
            this.vertexes.add(i.end);
        }
    }

    public Vertex receiveVertex(String name){
        for(Vertex i: vertexes){
            if (Objects.equals(i.name, name)) return i;
        }
        return null;
    }

    public void addVertex(String name){

        if (this.receiveVertex(name) == null){
            vertexes.add(new Vertex(name));
        }
    }

    public Arc receiveArc(String begin, String end){
        for (Arc i: arcs) {
            if (Objects.equals(i.begin.name, begin) && Objects.equals(i.end.name, end)) return i;
        }
        return null;
    }

    public void addArc(String begin, String end, int weight){
        if (this.receiveArc(begin, end) == null){
            if (this.receiveVertex(begin) == null) this.addVertex(begin);
            if (this.receiveVertex(end) == null) this.addVertex(end);
            arcs.add(new Arc(receiveVertex(begin), receiveVertex(end), weight));
        }
    }

    public void deleteVertex(String name){
        if (this.receiveVertex(name) != null){
            vertexes.remove(receiveVertex(name));
        }
    }

    public void deleteArc(String begin, String end){
        if(this.receiveArc(begin, end) != null){
            arcs.remove(receiveArc(begin, end));
        }
    }

    public Set<Arc> getIncomingArcs(Vertex vertex) {
        Set<Arc> result = new HashSet<>();
        for (Arc arc: arcs) {
            if (arc.end.equals(vertex)) result.add(arc);
        }
        return result;
    }

    public Set<Arc> getOutcomingArcs(Vertex vertex) {
        Set<Arc> result = new HashSet<>();
        for (Arc arc: arcs) {
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
}
