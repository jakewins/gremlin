package com.tinkerpop.gremlin.compiler.functions.g.util;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.functions.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class TypeFunctionTest extends BaseTest {

    public void testType() {
        Function<String> function = new TypeFunction();
        this.stopWatch();
        String type = function.compute(createUnaryArgs("marko")).getValue();
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertEquals(type, "string");

        this.stopWatch();
        type = function.compute(createUnaryArgs(true)).getValue();
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertEquals(type, "boolean");

        this.stopWatch();
        type = function.compute(createUnaryArgs(1.0)).getValue();
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertEquals(type, "double");

    }
}
