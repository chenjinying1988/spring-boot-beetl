package cn.kxlove;

/**
 * @author 郑开心
 * @date 16/9/5
 */
public class StringUtil {

    public StringUtil(){}

    public int length(String str) {
        if(str == null) {
            throw new NullPointerException("Error:length(null)");
        } else {
            return str.length();
        }
    }
}
