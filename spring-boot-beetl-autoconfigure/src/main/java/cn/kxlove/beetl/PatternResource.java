package cn.kxlove.beetl;

import org.beetl.core.Resource;
import org.beetl.core.ResourceLoader;
import org.beetl.core.exception.BeetlException;

import java.io.*;
import java.net.URL;

/**
 * @author happyhaha
 * Created on 16/8/19
 */
public class PatternResource extends Resource {

    private String path;

    private File file = null;
    private long lastModified = 0;

    public PatternResource(String id, String path ,ResourceLoader loader) {
        super(id, loader);
        this.path = path;
    }

    @Override
    public Reader openReader() {
        ResourcePatternResolverLoader resolverLoader = (ResourcePatternResolverLoader) resourceLoader;
        URL url = null;
        try {
            url = resolverLoader.getResourcePatternResolver().getResource(path).getURL();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (url == null)
        {
            BeetlException be = new BeetlException(BeetlException.TEMPLATE_LOAD_ERROR);
            be.resourceId = this.id;
            throw be;
        }
        InputStream is;
        try
        {
            is = url.openStream();
        }
        catch (IOException e1)
        {
            BeetlException be = new BeetlException(BeetlException.TEMPLATE_LOAD_ERROR);
            be.resourceId = this.id;
            throw be;
        }

        if (is == null)
        {
            BeetlException be = new BeetlException(BeetlException.TEMPLATE_LOAD_ERROR);
            be.resourceId = this.id;
            throw be;
        }

        if (url.getProtocol().equals("file"))
        {
            file = new File(url.getFile());
            lastModified = file.lastModified();
        }

        Reader br;
        try
        {
            br = new BufferedReader(new InputStreamReader(is, ((ResourcePatternResolverLoader) this.resourceLoader).getCharset()));
            return br;
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
    }

    @Override
    public boolean isModified()
    {
        if (file != null)
        {
            return file.lastModified() != this.lastModified;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getId()
    {
        return id;
    }
}
