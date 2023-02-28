package com.example.ip_etfbl_api.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingUtil {

    public static void logException(Throwable e, Log log)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(e);
        builder.append(System.lineSeparator());
        for(StackTraceElement el : e.getStackTrace())
        {
            builder.append(el);
            builder.append(System.lineSeparator());
        }
        log.error(builder);
    }

    public static <T> void logException(Throwable e, Class<T> clazz)
    {
        logException(e, LogFactory.getLog(clazz));
    }
}
