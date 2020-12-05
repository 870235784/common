package com.tca.common.log.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhoua
 * @Date 2020/12/3
 */
public class LogHandler {

    private static Pattern pattern = Pattern.compile("[0-9a-zA-Z]");

    /**
     * 日志脱敏关键字
     */
    private static String[] sensitiveDataKeys = {"name", "phone", "email", "mobile", "idCard", "base64", "token",
        "appSecret", "apiSecret", "password", "purpose", "inviteName", "personName", "sessionId", "linkAddress", "sign"};

    /**
     * 处理日志字符串，返回脱敏后的字符串
     *
     * @param oriMsg
     * @return
     */
    public String invokeMsg(final String oriMsg) {
        //过滤掉转义字符
        String tempMsg = oriMsg;
        // 处理字符串
        if (sensitiveDataKeys != null && sensitiveDataKeys.length > 0) {
            for (String key : sensitiveDataKeys) {
                int index = -1;
                do {
                    index = tempMsg.indexOf(key, index + 1);
                    if (index != -1) {
                        // 判断key是否为单词字符
                        if (!isWordChar(tempMsg, key, index)) {
                            continue;
                        }
                        // 寻找值的开始位置
                        Integer valueStart = getValueStartIndex(tempMsg, index + key.length());
                        if (valueStart == null) {
                            break;
                        }

                        // 查找值的结束位置（逗号，分号）
                        Integer valueEnd = getValueEndEIndex(tempMsg, valueStart);

                        // 对获取的值进行脱敏
                        String subStr = tempMsg.substring(valueStart, valueEnd);
                        subStr = convertMsg(subStr, key);
                        tempMsg = tempMsg.substring(0, valueStart) + subStr + tempMsg.substring(valueEnd);
                    }
                } while (index != -1);
            }
        }
        return tempMsg;
    }


    /**
     * 判断从字符串msg获取的key值是否为单词 , index为key在msg中的索引值
     * @param msg
     * @param key
     * @param index
     * @return
     */
    private boolean isWordChar(String msg, String key, int index) {
        // 必须确定key是一个单词
        // 判断key前面一个字符
        if (index != 0) {
            char preCh = msg.charAt(index - 1);
            Matcher match = pattern.matcher(preCh + "");
            if (match.matches()) {
                return false;
            }
        }
        if (index + key.length() >= msg.length()) {
            return false;
        }
        // 判断key后面一个字符
        char nextCh = msg.charAt(index + key.length());
        Matcher match = pattern.matcher(nextCh + "");
        return !match.matches();
    }


    /**
     * 获取value值的开始位置
     *
     * @param msg        要查找的字符串
     * @param valueStart 查找的开始位置
     * @return
     */
    private Integer getValueStartIndex(String msg, int valueStart) {
        // 寻找值的开始位置
        char ch = msg.charAt(valueStart);
        // key与 value的分隔符
        while (ch == ':' || ch == '=' || ch == '"' || ch == '\\') {
            valueStart++;
            if (valueStart >= msg.length()) {
                return null;
            }
            ch = msg.charAt(valueStart);
        }
        return valueStart;
    }

    /**
     * 获取value值的结束位置
     *
     * @return
     */
    private int getValueEndEIndex(String msg, int valueEnd) {
        while (msg.length() > valueEnd) {
            char ch = msg.charAt(valueEnd);
            //判断结束符
            if (ch == ';' || ch == ',' || ch == '}' || ch == ')' || ch == '&' || ch == '\\' || ch == '"') {
                break;
            }
            valueEnd++;
        }

        if (valueEnd >= msg.length()) {
            valueEnd = msg.length() - 1;
        }

        return valueEnd;
    }

    /**
     * 脱敏
     *
     * @param submsg 原日志信息
     * @param key    要脱敏的key值
     * @return
     */
    private String convertMsg(String submsg, String key) {
        if ("mobile".equals(key) || "phone".equals(key)) {
            return CoverUtils.coverPhone(submsg);
        } else if ("base64".equals(key) || "password".equals(key)) {
            return CoverUtils.coverAll();
        } else if ("sessionId".equals(key)) {
            return CoverUtils.coverSessionId(submsg);
        } else if (key.toLowerCase().contains("name")) {
            return CoverUtils.coverName(submsg);
        } else if (key.toLowerCase().contains("email")) {
            return CoverUtils.coverEmail(submsg);
        }
        return CoverUtils.coverCommon(submsg);
    }

    public static void main(String[] args) {
        LogHandler logHandler = new LogHandler();
        String msg = "{\n" +
                "    \"name\":\"球大帅\",\n" +
                "    \"mobile\":\"13502890000\",\n" +
                "    \"base64\":\"hkasjfoaguaglasugpaogsaugosjghsgusojfjslgupjeljg\",\n" +
                "    \"sessionId\":\"skljfosugosjglsgosjgs\",\n" +
                "    \"token\":\"faklhgopguahgopisg\",\n" +
                "    \"password\":\"12345678\"\n" +
                "}";
        String result = logHandler.invokeMsg(msg);
        System.out.println(result);
    }
}
