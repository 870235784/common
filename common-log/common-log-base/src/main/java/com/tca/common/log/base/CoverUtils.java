package com.tca.common.log.base;

/**
 * @author zhoua
 * @Date 2020/12/3
 */
public class CoverUtils {

    private CoverUtils() {}

    private static final String COVER_SYMBOL = "*";

    /**
     * 全遮盖
     * @return
     */
    public static String coverAll() {
        return "******";
    }

    /**
     * 通用覆盖
     * @param msg
     * @return
     */
    public static String coverCommon(String msg) {
        return getStarStr(msg, 3, 3);
    }

    /**
     * 遮盖姓名
     *
     * @param name 姓名
     * @return
     */
    public static String coverName(String name) {
        if (name.length() <= 2) {
            return getStarStr(name, 1, 0);
        } else {
            return getStarStr(name, 1, 1);
        }
    }

    /**
     * 遮盖身份证号码
     *
     * @param idCard 身份证号码
     * @return
     */
    public static String coverIdCard(String idCard) {
        return getStarStr(idCard, 3, 4);
    }

    /**
     * 遮盖银行卡号码
     *
     * @param bankCard 银行卡号码
     * @return
     */
    public static String coverBankCard(String bankCard) {
        return getStarStr(bankCard, 4, 4);
    }

    /**
     * 遮盖手机号
     *
     * @param phone 手机号
     * @return
     */
    public static String coverPhone(String phone) {
        return getStarStr(phone, 3, 4);
    }


    /**
     * 遮盖邮箱
     * @param email 邮箱
     * @return
     */
    public static String coverEmail(String email) {
        int indexOf = email.lastIndexOf("@");

        if (indexOf == -1) {
            return getStarStr(email, 1, 1);
        }

        return getStarStr(email, 1, email.length() - indexOf + 1);
    }

    /**
     * 遮盖 sessionId
     * @param sessionId sessionId
     * @return
     */
    public static String coverSessionId(String sessionId) {
        return getStarStr(sessionId, 6, 6);
    }



    /**
     * 对字符加星号处理: 除前面几位和后面几位外, 其他的字符以*代替
     *
     * @param content  传入的字符串
     * @param frontNum 保留前面字符的位数
     * @param endNum   保留后面字符的位数
     * @return 带星号的字符串
     */

    public static String getStarStr(String content, int frontNum, int endNum) {
        if (frontNum >= content.length() || frontNum < 0) {
            return content;
        }
        if (endNum >= content.length() || endNum < 0) {
            return content;
        }
        if (frontNum + endNum >= content.length()) {
            return content;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
            sb.append(COVER_SYMBOL);
        }
        return content.substring(0, frontNum) + sb.toString()
                + content.substring(content.length() - endNum, content.length());

    }

    public static void main(String[] args) {
        System.out.println(coverName("球大帅"));
        System.out.println(getStarStr("球大帅", 1, 1));
    }
}
