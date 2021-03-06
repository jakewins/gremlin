package com.tinkerpop.gremlin.compiler.functions.g.string;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class StringLengthFunctionTest extends BaseTest {

    public void testStringLength() {
        Function<Integer> function = new StringLengthFunction();
        this.stopWatch();
        Atom<Integer> atom = function.compute(createUnaryArgs("marko"));
        printPerformance(function.getFunctionName() + " function", 1, "string length of 5", this.stopWatch());
        assertEquals(atom.getValue(), new Integer(5));
        this.stopWatch();
        atom = function.compute(createUnaryArgs(""));
        printPerformance(function.getFunctionName() + " function", 1, "string length of 0", this.stopWatch());
        assertEquals(atom.getValue(), new Integer(0));
    }

    public void testIllegalArguments() {
        try {
            Function<Integer> function = new StringLengthFunction();
            function.compute(createUnaryArgs());
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(true);
        }

    }
}
