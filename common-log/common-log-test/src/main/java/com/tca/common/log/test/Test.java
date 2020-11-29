package com.tca.common.log.test;

import com.tca.common.log.jcl.log4j2.JclLogging;
import com.tca.common.log.slf4j.log4j2.Slf4jLogging;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
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
        JclLogging.main(args);
        Slf4jLogging.main(args);
    }
}
