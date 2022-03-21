package DirectedGraph;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectedGraphTest {
    DirectedGraph graph = new DirectedGraph((
            Set.of(
                    new DirectedGraph.Vertex("vv"),
                    new DirectedGraph.Vertex("cc"),
                    new DirectedGraph.Vertex("ff"),
                    new DirectedGraph.Vertex("kk"))
    ),
            Set.of(
                    new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                    new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                    new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
    );

    DirectedGraph graph2 = new DirectedGraph((
            Set.of(
                    new DirectedGraph.Vertex("vv"),
                    new DirectedGraph.Vertex("cc"),
                    new DirectedGraph.Vertex("ff"),
                    new DirectedGraph.Vertex("kk"))
    ),
            Set.of(
                    new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                    new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                    new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
    );

    @Test
    public void addVertex() {
        graph.addVertex("cc");
        graph.addVertex("ss");
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("ff"),
                        new DirectedGraph.Vertex("ss"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
        );
        assertEquals(expected, graph);
    }

    @Test
    public void addArc() {
        graph.addArc("vv", "ii", 3);
        graph.addArc("ii", "vv", 3);
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("ii"),
                        new DirectedGraph.Vertex("ff"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("ii"), 3),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("ii"), new DirectedGraph.Vertex("vv"), 3),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
        );
        assertEquals(expected, graph);
    }

    @Test
    public void deleteVertex() {
        graph.deleteVertex(graph.getVertex("ff"));
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9))
        );
        assertEquals(expected, graph);
    }

    @Test
    public void deleteArc() {
        graph.deleteArc("vv", "cc");
        graph.deleteArc("aa", "tt");
        graph.deleteArc("kk", "vv");
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("ff"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
        );
        assertEquals(expected, graph);
    }

    @Test
    public void setNameVertex() {
        graph.addArc("ff", "kk", 3);
        graph.setNameVertex(graph.getVertex("ff"), "ii");
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("ii"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("ii"), new DirectedGraph.Vertex("kk"), 3),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ii"), 5))
        );
        assertEquals(expected, graph);
    }

    @Test
    public void setWeightArc() {
        graph.getArcByName("vv", "cc").setWeight(6);
        graph.getArcByName("kk", "cc").setWeight(0);
        graph.getArcByVertexes(graph.getVertex("vv"), graph.getVertex("cc")).setWeight(8);
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("vv"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("ff"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 8),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
        );
        assertEquals(expected, graph);
    }

    @Test
    public void getIncomingArcs() {
        assertEquals(Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9))
                , graph.getIncomingArcs(graph.getVertex("cc")));
    }

    @Test
    public void getOutcomingArcs() {
        DirectedGraph graph = new DirectedGraph(Set.of(), Set.of(
                new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("ii"), 4),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
        );
        assertEquals(Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("cc"), 4),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("vv"), new DirectedGraph.Vertex("ii"), 4)),
                graph.getOutcomingArcs(graph.getVertex("vv")));
    }

    @Test
    public void mixedTests() {
        graph.deleteVertex(graph.getVertex("ii"));
        graph.getArcByVertexes(graph.getVertex("vv"), graph.getVertex("cc")).setWeight(8);
        graph.setNameVertex(graph.getVertex("vv"), "oo");
        graph.setNameVertex(graph.getVertex("aa"), "ll");
        DirectedGraph expected = new DirectedGraph((
                Set.of(
                        new DirectedGraph.Vertex("oo"),
                        new DirectedGraph.Vertex("cc"),
                        new DirectedGraph.Vertex("ff"),
                        new DirectedGraph.Vertex("kk"))
        ),
                Set.of(
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("oo"), new DirectedGraph.Vertex("cc"), 8),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("kk"), new DirectedGraph.Vertex("cc"), 9),
                        new DirectedGraph.Arc(new DirectedGraph.Vertex("cc"), new DirectedGraph.Vertex("ff"), 5))
        );
        assertEquals(expected, graph);
    }
}
