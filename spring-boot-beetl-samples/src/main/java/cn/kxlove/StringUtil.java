package cn.kxlove;

/**
 * @author happyhaha
 * Created on 16/8/19
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
