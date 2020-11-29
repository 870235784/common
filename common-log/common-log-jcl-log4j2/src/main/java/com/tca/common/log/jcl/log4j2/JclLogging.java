package com.tca.common.log.jcl.log4j2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author zhouan
 * @Date 2020/11/28
 */
public class JclLogging {

    public static void main(String[] args) {
        Log log = LogFactory.getLog(JclLogging.class);
        log.info("log = " + log);
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
    }
}
