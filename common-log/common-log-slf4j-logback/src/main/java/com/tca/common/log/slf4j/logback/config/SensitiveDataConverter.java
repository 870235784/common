package com.tca.common.log.slf4j.logback.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.tca.common.log.base.LogHandler;


/**
 * 敏感信息脱敏处理
 *
 * @author AAA
 */
public class SensitiveDataConverter extends MessageConverter {

    private LogHandler logHandler;

    public SensitiveDataConverter() {
        super();
        logHandler = new LogHandler();
    }

    @Override
    public String convert(ILoggingEvent event) {
        // 获取原始日志
        String oriLogMsg = event.getFormattedMessage();

        // 获取脱敏后的日志
        String afterLogMsg = logHandler.invokeMsg(oriLogMsg);
        return afterLogMsg;
    }

}
