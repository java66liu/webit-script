// Copyright (c) 2013, Webit Team. All Rights Reserved.
package webit.script.resolvers.impl;

import webit.script.io.Out;
import webit.script.resolvers.OutResolver;

/**
 *
 * @author zqq
 */
public class CharArrayOutResolver implements OutResolver {

    public void render(Out out, Object bean) {
        out.write((char[]) bean);
    }

    public Class getMatchClass() {
        return char[].class;
    }
}
