// Copyright (c) 2013, Webit Team. All Rights Reserved.
package webit.script.core.ast.expressions;

import webit.script.Context;
import webit.script.core.ast.BinaryOperator;
import webit.script.core.ast.Expression;
import webit.script.core.ast.Optimizable;
import webit.script.util.ALU;
import static webit.script.util.ALU.toBoolean;
import webit.script.util.StatmentUtil;

/**
 *
 * @author Zqq
 */
public final class OrOperator extends BinaryOperator implements Optimizable {

    public OrOperator(Expression leftExpr, Expression rightExpr, int line, int column) {
        super(leftExpr, rightExpr, line, column);
    }

    public Object execute(final Context context, final boolean needReturn) {
        Object left = StatmentUtil.execute(leftExpr, context);
        return toBoolean(left) ? left : StatmentUtil.execute(rightExpr, context);
    }

    public Expression optimize() {
        return (leftExpr instanceof DirectValue && rightExpr instanceof DirectValue)
                ? new DirectValue(ALU.or(((DirectValue) leftExpr).value, ((DirectValue) rightExpr).value), line, column)
                : this;
    }
}
