package com.tca.common.log.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouan
 * @Date 2020/11/28
 */
//@Slf4j
public class Test {

//    private static final Log log = LogFactory.getLog(Test.class);

    private static Logger logger ;

    private static Log log;

    public static void main(String[] args) {
        logger = LoggerFactory.getLogger(Test.class);
        log = LogFactory.getLog(Test.class);
        log.info("info");
        log.error("error");
    }
}
