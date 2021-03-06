package com.tinkerpop.gremlin.compiler.functions.g.graph;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory;
import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.GremlinEvaluator;
import com.tinkerpop.gremlin.compiler.Tokens;
import com.tinkerpop.gremlin.compiler.functions.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class AddEdgeFunctionTest extends BaseTest {

    public void testAddEdge() {
        Graph graph = TinkerGraphFactory.createTinkerGraph();
        GremlinEvaluator.declareVariable(Tokens.GRAPH_VARIABLE, new Atom<Graph>(graph));

        Function<Edge> function = new AddEdgeFunction();
        assertEquals(function.getFunctionName(), "add-e");
        this.stopWatch();
        Atom<Edge> atom = function.compute(createUnaryArgs(graph, null, graph.getVertex("1"), "co-developer", graph.getVertex("6")));
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertTrue(atom.isEdge());
        assertEquals(atom.getValue().getOutVertex().getId(), "1");
        assertEquals(atom.getValue().getInVertex().getId(), "6");
        assertEquals(atom.getValue().getLabel(), "co-developer");

        this.stopWatch();
        atom = function.compute(createUnaryArgs(null, graph.getVertex("1"), "co-developer", graph.getVertex("4")));
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertTrue(atom.isEdge());
        assertEquals(atom.getValue().getOutVertex().getId(), "1");
        assertEquals(atom.getValue().getInVertex().getId(), "4");
        assertEquals(atom.getValue().getLabel(), "co-developer");


        Map<Atom, Atom> map = new HashMap<Atom, Atom>();
        map.put(new Atom<String>("weight"), new Atom<Double>(0.5d));
        this.stopWatch();
        atom = function.compute(createUnaryArgs(map, graph.getVertex("1"), "co-worker", graph.getVertex("2")));
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertTrue(atom.isEdge());
        assertEquals(atom.getValue().getOutVertex().getId(), "1");
        assertEquals(atom.getValue().getInVertex().getId(), "2");
        assertEquals(atom.getValue().getLabel(), "co-worker");
        assertEquals(atom.getValue().getProperty("weight"), 0.5d);

    }
}
