package cn.py.springframework.core.io;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLResource implements Resource{
    private final URL url;

    public URLResource(URL url) {
        this.url = url;
    }

    /**
     * 通过Http方式读取云服务中的文件内容,也可以把配置文件放在gitee或者github中
     * @return urlInputStream
     * @throws IOException e
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try {
            return urlConnection.getInputStream();
        } catch (Exception ex) {
            if (urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlConnection).disconnect();
            }
            throw ex;
        }
    }
}
