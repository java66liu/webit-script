// Copyright (c) 2013-2014, Webit Team. All Rights Reserved.
package webit.script.exceptions;

import webit.script.core.ast.Statement;
import webit.script.util.Stack;

/**
 *
 * @author Zqq
 */
public class ScriptRuntimeException extends UncheckedException {

    private final Stack<Statement> statementStack = new Stack<Statement>(8);

    public final ScriptRuntimeException registStatement(Statement statement) {
        statementStack.push(statement);
        return this;
    }

    public Stack<Statement> getStatementStack() {
        return statementStack;
    }

    @Override
    public void printBody(PrintStreamOrWriter out, String prefix) {
        Statement statement;
        for (int i = statementStack.size() - 1; i >= 0; i--) {
            statement = statementStack.peek(i);
            out.print(prefix)
                    .print("\tat line ")
                    .print(statement.getLine())
                    .print("(")
                    .print(statement.getColumn())
                    .print(") ")
                    .println(statement.getClass().getSimpleName());
        }
    }

    public ScriptRuntimeException(String message) {
        super(message);
    }

    public ScriptRuntimeException(String message, Statement statement) {
        super(message);
        registStatement(statement);
    }

    public ScriptRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptRuntimeException(String message, Throwable cause, Statement statement) {
        super(message, cause);
        registStatement(statement);
    }

    public ScriptRuntimeException(Throwable cause) {
        super(cause);
    }

    public ScriptRuntimeException(Throwable cause, Statement statement) {
        super(cause);
        registStatement(statement);
    }
}
