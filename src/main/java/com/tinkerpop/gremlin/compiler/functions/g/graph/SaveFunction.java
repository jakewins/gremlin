package com.tinkerpop.gremlin.compiler.functions.g.graph;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.parser.GraphMLWriter;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.AbstractFunction;
import com.tinkerpop.gremlin.compiler.functions.FunctionHelper;
import com.tinkerpop.gremlin.compiler.operations.Operation;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class SaveFunction extends AbstractFunction<Boolean> {

    private static final String FUNCTION_NAME = "save";

    public Atom<Boolean> compute(final List<Operation> parameters) throws RuntimeException {
        final int size = parameters.size();
        if (size == 0 || size > 2)
            throw new RuntimeException(this.createUnsupportedArgumentMessage());

        final Graph graph = FunctionHelper.getGraph(parameters, 0);
        final String filename;

        if (size == 2) {
            filename = (String) parameters.get(1).compute().getValue();
        } else {
            filename = (String) parameters.get(0).compute().getValue();
        }

        try {
            OutputStream stream = new FileOutputStream(filename);
            GraphMLWriter.outputGraph(graph, stream);
            return new Atom<Boolean>(true);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getFunctionName() {
        return FUNCTION_NAME;
    }
}
