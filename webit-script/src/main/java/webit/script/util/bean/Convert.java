// Copyright (c) 2013-2014, Webit Team. All Rights Reserved.
package webit.script.util.bean;

import webit.script.util.ClassEntry;
import webit.script.util.ClassUtil;
import webit.script.util.StringUtil;

/**
 *
 * @author Zqq
 */
public class Convert {

    public static Object convert(String string, Class cls) {
        if (cls == String.class) {
            return string;
        }
        if (cls == int.class) {
            if (string == null || string.length() == 0) {
                return 0;
            }
            return toInt(string);
        } else if (cls == boolean.class) {
            return toBoolean(string);
        }
        if (string == null) {
            return null;
        }
        if (cls.isArray()) {
            if (cls == Class[].class) {
                return toClassArray(string);
            } else if (cls == ClassEntry[].class) {
                return toClassEntryArray(string);
            } else if (cls == String[].class) {
                return toStringArray(string);
            } else if (cls == int[].class) {
                return toIntArray(string);
            } else if (cls == Integer[].class) {
                return toIntegerArray(string);
            } else if (cls == boolean[].class) {
                return toBoolArray(string);
            } else if (cls == Boolean[].class) {
                return toBooleanArray(string);
            }
        } else if (cls == Boolean.class) {
            return toBoolean(string);
        }
        if (string.length() == 0) {
            return null;
        }
        if (cls == Class.class) {
            return toClass(string);
        } else if (cls == ClassEntry.class) {
            return toClassEntry(string);
        } else if (cls == Integer.class) {
            return toInt(string);
        }
        return string;
    }

    private static Class toClass(String string) {
        try {
            return ClassUtil.getClass(string);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static boolean toBoolean(String string) {
        return string != null && string.equalsIgnoreCase("true");
    }

    private static int toInt(String string) {
        return Integer.valueOf(string);
    }

    private static ClassEntry toClassEntry(String string) {
        try {
            return ClassEntry.wrap(string);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String[] toStringArray(String string) {
        if (string == null) {
            return null;
        }
        return StringUtil.toArray(string);
    }

    private static Class[] toClassArray(String string) {
        final String[] strings = StringUtil.toArray(string);
        final int len = strings.length;
        final Class[] entrys = new Class[len];
        for (int i = 0; i < len; i++) {
            entrys[i] = toClass(strings[i]);
        }
        return entrys;
    }

    private static boolean[] toBoolArray(String string) {
        final String[] strings = StringUtil.toArray(string);
        final int len = strings.length;
        final boolean[] entrys = new boolean[len];
        for (int i = 0; i < len; i++) {
            entrys[i] = toBoolean(strings[i]);
        }
        return entrys;
    }

    private static Boolean[] toBooleanArray(String string) {
        final String[] strings = StringUtil.toArray(string);
        final int len = strings.length;
        final Boolean[] entrys = new Boolean[len];
        for (int i = 0; i < len; i++) {
            entrys[i] = toBoolean(strings[i]);
        }
        return entrys;
    }

    private static int[] toIntArray(String string) {
        final String[] strings = StringUtil.toArray(string);
        final int len = strings.length;
        final int[] entrys = new int[len];
        for (int i = 0; i < len; i++) {
            entrys[i] = toInt(strings[i]);
        }
        return entrys;
    }

    private static Integer[] toIntegerArray(String string) {
        final String[] strings = StringUtil.toArray(string);
        final int len = strings.length;
        final Integer[] entrys = new Integer[len];
        for (int i = 0; i < len; i++) {
            entrys[i] = toInt(strings[i]);
        }
        return entrys;
    }

    private static ClassEntry[] toClassEntryArray(final String string) {
        final String[] strings = StringUtil.toArray(string);
        final int len = strings.length;
        final ClassEntry[] entrys = new ClassEntry[len];
        for (int i = 0; i < len; i++) {
            entrys[i] = toClassEntry(strings[i]);
        }
        return entrys;
    }
}
