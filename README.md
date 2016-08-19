# spring-boot-beetl-start 
spring-boot与beetl整合

使用spring-boot自动配置功能然后将beetl作为spring的`ViewResolver`


### 使用
####maven
``` xml
    <dependency>
        <groupId>cn.kxlove</groupId>
        <artifactId>spring-boot-beetl-</artifactId>
        <version>1.4.0.RELEASE</version>
    </dependency>
```
####gradle
``` groovy
    compile ('cn.kxlove:spring-boot-beetl-start:1.0.0')
```

###关于`beetl`的`ResourceLoader`

重写了原来的的`ClasspathResourceLoader`,resource用过spring带的`ResourcePatternResolver`去加载
