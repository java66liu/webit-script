// Copyright (c) 2013-2014, Webit Team. All Rights Reserved.
package webit.script.util.iter;

/**
 *
 * @author Zqq
 */
public class LongArrayIterAdapter extends AbstractIter {

    private final long[] array;
    private final int max;

    public LongArrayIterAdapter(long[] array) {
        this.array = array;
        this.max = array.length - 1;
    }

    protected Long _next() {
        return array[_index];
    }

    public boolean hasNext() {
        return _index < max;
    }
}
