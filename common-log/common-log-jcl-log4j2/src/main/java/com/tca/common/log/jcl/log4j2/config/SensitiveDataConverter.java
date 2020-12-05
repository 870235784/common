package com.tca.common.log.jcl.log4j2.config;

import com.tca.common.log.base.LogHandler;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

/**
 * @author zhoua
 * @Date 2020/12/3
 */
@Plugin(name = "sensitiveDataConverter", category = PatternConverter.CATEGORY)
@ConverterKeys(value = "sdc")
public class SensitiveDataConverter extends LogEventPatternConverter {

    private LogHandler logHandler;

    protected SensitiveDataConverter(String name, String style) {
        super(name, style);
        logHandler = new LogHandler();
    }


    public static SensitiveDataConverter newInstance(String[] options) {
        return new SensitiveDataConverter("sdc", "sdc");
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder stringBuilder) {
        stringBuilder.append(logEvent.getLoggerName().startsWith("com.tca")?logHandler.invokeMsg(
                logEvent.getMessage().getFormattedMessage()):logEvent.getMessage().getFormattedMessage());
    }
}
