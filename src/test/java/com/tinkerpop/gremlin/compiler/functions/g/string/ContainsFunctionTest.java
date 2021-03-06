package com.tinkerpop.gremlin.compiler.functions.g.string;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ContainsFunctionTest extends BaseTest {

    public void testContains() {
        Function<Boolean> function = new ContainsFunction();
        this.stopWatch();
        Atom<Boolean> atom = function.compute(createUnaryArgs("marko", "rk"));
        printPerformance(function.getFunctionName() + " function", 1, "starts with check", this.stopWatch());
        assertTrue(atom.getValue());
        this.stopWatch();
        atom = function.compute(createUnaryArgs("marko", "pa"));
        printPerformance(function.getFunctionName() + " function", 1, "starts with check", this.stopWatch());
        assertFalse(atom.getValue());
    }
}