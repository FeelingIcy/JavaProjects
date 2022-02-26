package DirectedGraph;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectedGraphTest {

    @Test
    public void addVertex() {
        DirectedGraph graph = new DirectedGraph();
        graph.addVertex("vv");
        graph.addVertex("cc");
        graph.addVertex("ss");
        DirectedGraph expected = new DirectedGraph(Set.of(
                new DirectedGraph.Vertex("vv"),
                new DirectedGraph.Vertex("cc"),
                new DirectedGraph.Vertex("ss")
        ), Set.of());
        assertEquals(expected, graph);
    }

    @Test
    public void addArc() {
        DirectedGraph graph = new DirectedGraph();
        graph.addArc("vv", "cc", 3);
        graph.addArc("tt", "kk", 5);
        DirectedGraph expected = new DirectedGraph(Set.of(), Set.of(
                new DirectedGraph.Arc(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        3),
                new DirectedGraph.Arc(
                        new DirectedGraph.Vertex("tt"),
                        new DirectedGraph.Vertex("kk"),
                        5)));
        assertEquals(expected, graph);
    }

    @Test
    public void deleteVertex() {
        DirectedGraph graph = new DirectedGraph((Set.of(
                new DirectedGraph.Vertex("vv"),
                new DirectedGraph.Vertex("cc"))),
                Set.of());
        graph.deleteVertex("cc");
        DirectedGraph expected = new DirectedGraph(Set.of(new DirectedGraph.Vertex("vv")), Set.of());
        assertEquals(expected, graph);
    }

    @Test
    public void deleteArc() {
        DirectedGraph graph = new DirectedGraph((Set.of(
                new DirectedGraph.Vertex("ff"),
                new DirectedGraph.Vertex("cc"))),
                Set.of(new DirectedGraph.Arc(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        4)));
        graph.deleteArc("vv", "cc");
        DirectedGraph expected = new DirectedGraph(Set.of(
                new DirectedGraph.Vertex("vv"),
                new DirectedGraph.Vertex("ff"),
                new DirectedGraph.Vertex("cc")),
                Set.of());
        assertEquals(expected, graph);
    }

    @Test
    public void setNameVertex() {
        DirectedGraph graph = new DirectedGraph((Set.of(
                new DirectedGraph.Vertex("ff"),
                new DirectedGraph.Vertex("cc"))),
                Set.of());
        graph.receiveVertex("ff").setName("ii");
        DirectedGraph expected = new DirectedGraph(Set.of(
                new DirectedGraph.Vertex("ii"),
                new DirectedGraph.Vertex("cc")),
                Set.of());
        assertEquals(expected, graph);
    }

    @Test
    public void setNameArc() {
        DirectedGraph graph = new DirectedGraph((Set.of()),
                Set.of(new DirectedGraph.Arc(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        4)));
        graph.receiveArc("vv", "cc").setWeight(6);
        DirectedGraph expected = new DirectedGraph(Set.of(
                new DirectedGraph.Vertex("vv"),
                new DirectedGraph.Vertex("cc")),
                Set.of(new DirectedGraph.Arc(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        6 )));
        assertEquals(expected, graph);
    }

    @Test
    public void getIncomingArcs() {
        DirectedGraph graph = new DirectedGraph((Set.of()), Set.of(
                new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("сс"), new DirectedGraph.Vertex("ff"), 5)));
        assertEquals(Set.of(
                new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"),new DirectedGraph.Vertex("cc"), 4),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"),new DirectedGraph.Vertex("cc"), 9))
                , graph.getIncomingArcs(graph.receiveVertex("cc")));
    }

    @Test
    public void getOutcomingArcs() {
        DirectedGraph graph = new DirectedGraph((Set.of()), Set.of(
                new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("ii"), 9),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("сс"), new DirectedGraph.Vertex("ff"), 5)));
        assertEquals(Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"),new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"),new DirectedGraph.Vertex("ii"), 9))
                , graph.getOutcomingArcs(graph.receiveVertex("vv")));
    }
}
