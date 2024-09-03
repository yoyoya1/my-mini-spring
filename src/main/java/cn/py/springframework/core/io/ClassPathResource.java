package cn.py.springframework.core.io;

import cn.hutool.core.util.ClassUtil;
import com.sun.org.apache.xml.internal.security.utils.ClassLoaderUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;

public class ClassPathResource implements Resource{

    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : Thread.currentThread().getContextClassLoader());
    }

    /**
     * @return InputStream classPath下的指定文件流
     * @throws IOException io异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + "cannot be opened because it does not exist"
            );
        }
        return is;
    }
}
