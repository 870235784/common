package com.tca.common.log.base.jcl.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouan
 * @Date 2020/11/28
 */
public class LogbackTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogbackTest.class);
        logger.info("log = {}", logger);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
