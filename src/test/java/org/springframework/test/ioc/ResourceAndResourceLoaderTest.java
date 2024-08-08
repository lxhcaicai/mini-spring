package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        //加载classpath下的资源
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        Assertions.assertEquals(content, "hello world");

        // 加载文件系统资源
        resource = resourceLoader.getResource("src/test/resources/hello.txt");
        Assertions.assertTrue(resource instanceof FileSystemResource);
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        Assertions.assertEquals(content, "hello world");

        //加载url资源
        resource = resourceLoader.getResource("https://www.baidu.com");
        Assertions.assertTrue(resource instanceof UrlResource);
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
