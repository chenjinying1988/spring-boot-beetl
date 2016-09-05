# spring-boot-beetl-start 

[![Build Status](https://travis-ci.org/happyhaha1/spring-boot-beetl.svg?branch=master)](https://travis-ci.org/happyhaha1/spring-boot-beetl)

spring-boot与beetl整合

使用spring-boot自动配置功能然后将beetl作为spring的`ViewResolver`


### 使用
####maven
``` xml
    <dependency>
        <groupId>cn.kxlove</groupId>
        <artifactId>spring-boot-beetl-</artifactId>
        <version>1.0.0</version>
    </dependency>
```
####gradle
``` groovy
    compile ('cn.kxlove:spring-boot-beetl-start:1.0.0')
```

###关于`beetl`的`ResourceLoader`

重写了原来的的`ClasspathResourceLoader`,resource用过spring带的`ResourcePatternResolver`去加载


### `application.properties`中的参数

`beetl.root` 该参数是指定文件地址 默认为`/templates/`

`beetl.charset` 该参数是指定字符集 默认为`UTF-8`

`beetl.contentType` 该参数指定内容类型 默认为`text/html;charset=UTF-8`

`beetl.prefix`    该参数指定解析前缀 默认为空

`beetl.suffix`    该参数指定解析后缀 默认为`.btl`

`beetl.properties` 该参数指定beetl的配置文件位置 默认为空




