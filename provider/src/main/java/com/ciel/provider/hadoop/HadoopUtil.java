package com.ciel.provider.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;


@ConfigurationProperties(prefix = "hdfs")
@Component
public class HadoopUtil {

    private String path;

    private String username;

    /**
     * 获取HDFS配置信息
     * @return
     */
    public  Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", path);

        configuration.set("dfs.replication", "2"); //副本数量

        return configuration;
    }





    /**
     * 获取HDFS文件系统对象
     * @return
     * @throws Exception
     */
    public FileSystem getFileSystem() throws Exception {
        // 客户端去操作hdfs时是有一个用户身份的，默认情况下hdfs客户端api会从jvm中获取一个参数作为自己的用户身份 DHADOOP_USER_NAME=hadoop
//        FileSystem hdfs = FileSystem.get(getHdfsConfig()); //默认获取
//        也可以在构造客户端fs对象时，通过参数传递进去
        FileSystem fileSystem = FileSystem.get(new URI(path), getConfiguration(), username);
        return fileSystem;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

