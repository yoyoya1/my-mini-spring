package cn.py.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{
    /**
     * @param location url路径
     * @return ClassPathResource/FileSystemResource/URLResource
     */
    @Override
    public Resource getResource(String location) {
        assert location != null;
        if (location.startsWith(CLASS_PATH_PREFIX)) {
            return new ClassPathResource(location.substring(CLASS_PATH_PREFIX.length()));
        } else {
            try {
                return new URLResource(new URL(location));
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
