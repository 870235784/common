package com.tca.common.log.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author zhouan
 * @Date 2020/11/28
 */
public class JclLog4j2 {

    private final static Log log = LogFactory.getLog(JclLog4j2.class);

    public static void main(String[] args) {
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
