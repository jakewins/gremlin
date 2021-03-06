package com.tinkerpop.gremlin.compiler.functions.g.string;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ConcatFunctionTest extends BaseTest {

    public void testOneStringConcat() {
        Function<String> function = new ConcatFunction();
        this.stopWatch();
        Atom<String> atom = function.compute(createUnaryArgs("marko"));
        printPerformance(function.getFunctionName() + " function", 1, "argument concat", this.stopWatch());
        assertEquals(atom.getValue(), "marko");
    }

    public void testTwoStringConcat() {
        Function<String> function = new ConcatFunction();
        this.stopWatch();
        Atom<String> atom = function.compute(createUnaryArgs("marko", "rodriguez"));
        printPerformance(function.getFunctionName() + " function", 2, "argument concat", this.stopWatch());
        assertEquals(atom.getValue(), "markorodriguez");
    }

    public void testThreeObjectConcat() {
        Function<String> function = new ConcatFunction();
        this.stopWatch();
        Atom<String> atom = function.compute(createUnaryArgs("marko", 1, "rodriguez", 7.0d));
        printPerformance(function.getFunctionName() + " function", 4, "argument concat", this.stopWatch());
        assertEquals(atom.getValue(), "marko1rodriguez7.0");
    }
}
