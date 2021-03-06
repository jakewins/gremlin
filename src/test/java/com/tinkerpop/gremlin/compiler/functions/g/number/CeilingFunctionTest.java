package com.tinkerpop.gremlin.compiler.functions.g.number;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class CeilingFunctionTest extends BaseTest {

    public void testCeiling() {
        Function<Long> function = new CeilingFunction();
        this.stopWatch();
        Atom<Long> atom = function.compute(createUnaryArgs(1.4));
        printPerformance(function.getFunctionName() + " function", 1, "argument", this.stopWatch());
        assertEquals(atom.getValue(), new Long(2));

        this.stopWatch();
        atom = function.compute(createUnaryArgs(1.5));
        printPerformance(function.getFunctionName() + " function", 1, "argument", this.stopWatch());
        assertEquals(atom.getValue(), new Long(2));

        this.stopWatch();
        atom = function.compute(createUnaryArgs(1));
        printPerformance(function.getFunctionName() + " function", 1, "argument", this.stopWatch());
        assertEquals(atom.getValue(), new Long(1));

    }
}

