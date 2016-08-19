package cn.kxlove.beetl;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Resource;
import org.beetl.core.ResourceLoader;
import org.beetl.core.fun.FileFunctionWrapper;
import org.beetl.core.misc.BeetlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author: happyhaha
 * @date: 16/8/19
 */
public class ResourcePatternResolverLoader implements ResourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(BeetlAutoConfiguration.class);

    private String root;
    private boolean autoCheck = false;
    private String charset;

    private ResourcePatternResolver resourcePatternResolver;
    public ResourcePatternResolverLoader() {
        this("");
    }

    public ResourcePatternResolverLoader(String root) {
        this(root,"UTF-8");
    }

    public ResourcePatternResolverLoader(String root,String charset) {
        this(root,charset, ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader()));
    }

    public ResourcePatternResolverLoader(String root,String charset,ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
        if (root.equals("/")) {
            this.root = "";
        } else {
            this.root = root;
        }
        this.charset = charset;
    }

    @Override
    public Resource getResource(String key)
    {
        if (key.startsWith("/")) {
           key =  key.substring(1,key.length());
        }
        return new PatternResource(key, "classpath:"+root + key, this);
    }


    @Override
    public void close()
    {
    }

    @Override
    public boolean isModified(Resource key)
    {
        if (this.autoCheck)
        {
            return key.isModified();
        }
        else
        {
            return false;
        }
    }

    @Override
    public void init(GroupTemplate gt)
    {
        Map<String, String> resourceMap = gt.getConf().getResourceMap();
        if (resourceMap.get("root") != null)
        {
            String temp = resourceMap.get("root");
            if (temp.equals("/") || temp.length() == 0)
            {

            }
            else
            {

                if (this.root.endsWith("/"))
                {
                    this.root = this.root + resourceMap.get("root");
                }
                else
                {
                    this.root = this.root + "/" + resourceMap.get("root");
                }

            }

        }

        if (this.charset == null)
        {
            this.charset = resourceMap.get("charset");

        }

        String functionSuffix = resourceMap.get("functionSuffix");

        this.autoCheck = Boolean.parseBoolean(resourceMap.get("autoCheck"));
        String functionRoot = resourceMap.get("functionRoot");
        //初始化functions
        GroupTemplate gt1 = gt;

        try {
            PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
            String s = root + functionRoot + File.separator + "**/*." + functionSuffix;
            org.springframework.core.io.Resource[] resources = pathMatchingResourcePatternResolver.getResources(s);
            if (resources!= null && resources.length > 0)
            {
                for (org.springframework.core.io.Resource resource : resources) {
                    URL url = resource.getURL();
                    String file = url.getFile();
                    int i = file.lastIndexOf(functionRoot + File.separator);
                    String resourceId = file.substring(i,file.length());
                    String fileName = resource.getFilename();
                    String ns = "";
                    fileName = fileName.substring(0, (fileName.length() - functionSuffix.length() - 1));
                    String functionName = ns.concat(fileName);
                    FileFunctionWrapper fun = new FileFunctionWrapper(resourceId);
                    gt.registerFunction(functionName,fun);
                }
            }
        } catch (IOException e) {
            logger.error("初始化functions 失败:"+e.getMessage());
        }

    }

    @Override
    public boolean exist(String key)
    {
        URL url = null;
        try {
            url = resourcePatternResolver.getResource(root + key).getURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url!=null;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    @Override
    public String getResourceId(Resource resource, String id)
    {
        if (resource == null)
            return id;
        else
            return BeetlUtil.getRelPath(resource.getId(), id);
    }

    public ResourcePatternResolver getResourcePatternResolver() {
        return resourcePatternResolver;
    }

    public void setResourcePatternResolver(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }


}
