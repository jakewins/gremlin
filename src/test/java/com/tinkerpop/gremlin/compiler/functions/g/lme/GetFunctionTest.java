package com.tinkerpop.gremlin.compiler.functions.g.lme;

import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory;
import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.Function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class GetFunctionTest extends BaseTest {

    public void testGet() {
        Function<Object> function = new GetFunction();
        List<Atom> list = Arrays.asList(new Atom("pavel"), new Atom(23));
        Map<Atom, Atom> map = new HashMap<Atom, Atom>();
        map.put(new Atom<String>("name"), new Atom<String>("pavel"));
        map.put(new Atom<String>("id"), new Atom<String>("23"));
        Vertex vertex = TinkerGraphFactory.createTinkerGraph().getVertex(1);

        this.stopWatch();
        Atom<Object> atom = function.compute(createUnaryArgs(list, 1));
        printPerformance(function.getFunctionName() + " function", 1, "list get", this.stopWatch());
        assertEquals(atom.getValue(), 23);

        this.stopWatch();
        atom = function.compute(createUnaryArgs(map, new Atom<String>("name")));
        printPerformance(function.getFunctionName() + " function", 1, "map get", this.stopWatch());
        assertEquals(atom.getValue(), "pavel");

        this.stopWatch();
        atom = function.compute(createUnaryArgs(vertex, "name"));
        printPerformance(function.getFunctionName() + " function", 1, "vertex get", this.stopWatch());
        assertEquals(atom.getValue(), "marko");

        try {
            function.compute(createUnaryArgs(list, "bad"));
            assertTrue(false);
        } catch(Exception e) {
            assertTrue(true);
        }

    }
}
