package cn.kxlove.beetl;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * beetl 自动配置
 * @author: happyhaha
 * @date: 16/8/19
 */
@Configuration
@EnableConfigurationProperties(BeetlProperties.class)
public class BeetlAutoConfiguration extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(BeetlAutoConfiguration.class);

    @Autowired
    private BeetlProperties properties;

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        logger.debug("beetl初始化....");
        try {
            ResourcePatternResolverLoader loader = new ResourcePatternResolverLoader(properties.getRoot());

            beetlGroupUtilConfiguration.setResourceLoader(loader);
            if (!StringUtils.isEmpty(properties.getProperties())) {
                beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:"+properties.getProperties()));
            }

            logger.debug("beetl成功....");

            return beetlGroupUtilConfiguration;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType(properties.getContentType());
        beetlSpringViewResolver.setSuffix(properties.getSuffix());
        beetlSpringViewResolver.setPrefix(properties.getPrefix());
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
	

	
}
