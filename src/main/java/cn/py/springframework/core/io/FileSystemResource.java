package cn.py.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileSystemResource implements Resource{

    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = this.file.getPath();
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(this.path);
    }

    /**
     * 读取对应路径下的文件内容,如txt、excel文件
     * @return FileInputStream
     * @throws IOException e
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
