// Copyright (c) 2013-2014, Webit Team. All Rights Reserved.
package webit.script.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Zqq
 */
public final class HttpServletRequestHeader {

    private final HttpServletRequest request;

    public HttpServletRequestHeader(HttpServletRequest request) {
        this.request = request;
    }

    public Object get(String key) {
        return request.getHeader(key);
    }
}
