// Copyright (c) 2013-2014, Webit Team. All Rights Reserved.
package webit.script.core.ast.statements;

import java.util.LinkedList;
import java.util.List;
import webit.script.Context;
import webit.script.core.LoopInfo;
import webit.script.core.ast.Loopable;
import webit.script.core.ast.Statement;

/**
 *
 * @author Zqq
 */
public final class Continue extends Statement implements Loopable {

    private final int label;

    public Continue(int label, int line, int column) {
        super(line, column);
        this.label = label;
    }

    public Object execute(final Context context) {
        context.continueLoop(label);
        return null;
    }

    public List<LoopInfo> collectPossibleLoopsInfo() {
        LinkedList<LoopInfo> list;
        (list = new LinkedList<LoopInfo>()).add(new LoopInfo(LoopInfo.CONTINUE, label, line, column));
        return list;
    }
}
