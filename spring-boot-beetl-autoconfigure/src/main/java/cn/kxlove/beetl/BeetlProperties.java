package cn.kxlove.beetl;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: happyhaha
 * @date: 16/8/19
 */
@ConfigurationProperties(prefix="beetl")
public class BeetlProperties {


    private String root = "/templates/";

    private String charset = "UTF-8";

    private String contentType = "text/html;charset=UTF-8";

    private String prefix = "";

    private String suffix = ".btl";

    private String properties = "";

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
