package com.ciel.provider.hadoop;


import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dfs")
public class FileUploadController {


    @Autowired
    private HadoopUtil hadoopUtil;

    @GetMapping("/bas")
    public void otherTest() throws Exception {
        FileSystem fs = hadoopUtil.getFileSystem();
        fs.mkdirs(new Path("/ciel")); //创建文件夹,create创建文件, append 追写文件

        fs.copyFromLocalFile //上传文件
                (true, new Path("D:/mapred-site.xml"), new Path("/ciel/application-pro.yml"));

        // 2 执行下载操作
        // boolean delSrc 指是否将原文件删除
        // Path src 指要下载的文件路径
        // Path dst 指将文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false, new Path("/xiapeixin/xia.el"), new Path("D:/xia.el"), true);

        // 2 执行删除
        // fs.delete(new Path("/0508/"), true);

        // 2 修改文件名称
        // fs.rename(new Path("/old.txt"), new Path("/new.txt"));

        // 2 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("这是文件:::"+fileStatus.getPath().getName());
            }else {
                System.out.println("这是文件夹:::"+fileStatus.getPath().getName());
            }
        }
        fs.close();
    }

    @GetMapping("/oth")
    public void other2() throws Exception {
        FileSystem fs = hadoopUtil.getFileSystem();

        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();

            // 输出详情
            // 文件名称
            System.out.println("文件名称"+status.getPath().getName());
            // 长度
            System.out.println("长度"+status.getLen());
            // 权限
            System.out.println("权限"+status.getPermission());
            // 分组
            System.out.println("分组"+status.getGroup());

            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();

            for (BlockLocation blockLocation : blockLocations) {
                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.print("块存储的主机节点"+host + "=====");
                }
            }

            System.out.println("+++++++++++++++++++++++++++");
        }
         fs.close();
    }

    @GetMapping("/noapi")
    public void noapi() throws Exception {

        FileSystem fs = hadoopUtil.getFileSystem();
        // 2 创建输入流
        FileInputStream fis = new FileInputStream(new File("d:/xa.el"));

        // 3 获取输出流
        FSDataOutputStream fos = fs.create(new Path("/xiapeixin/cc.el"));

        // 4 流对拷
        IOUtils.copyBytes(fis, fos, hadoopUtil.getConfiguration()); //上传

        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);

        //////////////////////////////////////////////////////////////

        // 2 获取输入流
        FSDataInputStream fisd = fs.open(new Path("/xiapeixin/cc.el"));

        // 3 获取输出流
        FileOutputStream fosd = new FileOutputStream(new File("d:/ax.el"));

        // 4 流的对拷
        IOUtils.copyBytes(fisd, fosd, hadoopUtil.getConfiguration()); //下载

        // 5 关闭资源
        IOUtils.closeStream(fosd);
        IOUtils.closeStream(fisd);

        fs.close();
    }


    @GetMapping("/fenkuai")
    public void fenkuai() throws Exception {

        FileSystem fs = hadoopUtil.getFileSystem();
        if(fs.exists(new Path("/ciel/application-pro.yml"))){
            fs.copyFromLocalFile
                    (true, new Path("D:/hadoop.avi"), new Path("/ciel/hadoop.avi"));
        }

        // 2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/ciel/hadoop.avi"));

        // 3 创建输出流
        FileOutputStream fos = new FileOutputStream(new File("D:/zzz/z.avi"));

        // 4 流的拷贝
        byte[] buf = new byte[1024];

        for(int i =0 ; i < 1024 * 128; i++){
            fis.read(buf);
            fos.write(buf);
        }

        // 5关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);

        ////////////////////////////////////////////
        //下载第二块

        // 2 打开输入流
        FSDataInputStream fis2 = fs.open(new Path("/ciel/hadoop.avi"));

        // 3 定位输入数据位置
        fis2.seek(1024*1024*128);

        // 4 创建输出流
        FileOutputStream fos2 = new FileOutputStream(new File("D:/zzz/z.avi"),true); //追加写入

        // 5 流的对拷
        IOUtils.copyBytes(fis2, fos2, hadoopUtil.getConfiguration());

        // 6 关闭资源
        IOUtils.closeStream(fis2);
        IOUtils.closeStream(fos2);

    }




    @PostMapping
    public Map hdfsupload(@RequestParam("file") MultipartFile file)  {
        try {
            if (null != file) {
                DecimalFormat df = new DecimalFormat("0.00");
                long start = System.currentTimeMillis();

                FileSystem fs = hadoopUtil.getFileSystem();
                InputStream fis = file.getInputStream();

                FSDataOutputStream outputStream = fs.create(new Path("/upload/" + file.getOriginalFilename())); //原始名称 .appned() 追加
                // outputStream.write(file.getBytes());

                byte[] b = new byte[1024 * 3];
                int n;
                while ((n = fis.read(b)) != -1) {
                    outputStream.write(b, 0, n);
                }
                outputStream.close();
                fs.close();
                fis.close();

                long end = System.currentTimeMillis();

                System.out.println("上传成功：" + df.format(((end - start) * 1.00d) / 1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Map.of("filepath", "");
    }

    @GetMapping("/list")
    public Map hdfsList()  {
        try {
            FileSystem fs = hadoopUtil.getFileSystem();
            List<HadoopFile> lists = new ArrayList<HadoopFile>();
            iteratorShowFiles(fs, new Path("/"), lists);
            return Map.of("list", lists);
        } catch (Exception e) {

        }
        return null;
    }

    @RequestMapping("/download")
    public void download(String path, HttpServletResponse response) {
        if (null == path) {
            return;
        }
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            long start = System.currentTimeMillis();
            FileSystem fs = hadoopUtil.getFileSystem();
            InputStream in = fs.open(new Path(path));
            byte[] bytes = null;
            ServletOutputStream out = null;
            try {
                response.setContentType("multipart/form-data");
                out = response.getOutputStream();
                bytes = new byte[10240];
                int n;
                while ((n = in.read(bytes)) != -1) {
                    out.write(bytes, 0, n);
                }
                out.flush();
                long end = System.currentTimeMillis();
                System.out.println(path + "，打开时间：" + df.format(((end - start) * 1.00d) / 1000));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        out = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void iteratorShowFiles(FileSystem hdfs, Path path, List<HadoopFile> lists) {
        try {
            if (hdfs == null || path == null) {
                return;
            }
            //获取文件列表
            FileStatus[] files = hdfs.listStatus(path);
            //展示文件信息
            for (int i = 0; i < files.length; i++) {
                try {
                    if (files[i].isDirectory()) {
                        //System.out.println(">>>" + files[i].getPath() + ", dir owner:" + files[i].getOwner());
                        //递归调用
                        iteratorShowFiles(hdfs, files[i].getPath(), lists);
                    } else if (files[i].isFile()) {
                        //System.out.println("   " + files[i].getPath() + ", length:" + files[i].getLen() + ", name:" + files[i].getPath().getName());
                        String postfix = files[i].getPath().getName().substring(files[i].getPath().getName().lastIndexOf(".") + 1, files[i].getPath().getName().length()).toUpperCase();//文件后缀
                        HadoopFile hadoopFile = new HadoopFile();
                        hadoopFile.setPath(files[i].getPath().toString().replace(hadoopUtil.getPath(), ""));
                        hadoopFile.setName(files[i].getPath().getName());
                        if ("BMP".equalsIgnoreCase(postfix) || "PNG".equalsIgnoreCase(postfix) || "GIF".equalsIgnoreCase(postfix) || "JPG".equalsIgnoreCase(postfix) || "JPEG".equalsIgnoreCase(postfix)) {
                            hadoopFile.setType("image");
                        } else if ("MP4".equalsIgnoreCase(postfix) || "AVI".equalsIgnoreCase(postfix) || "MOV".equalsIgnoreCase(postfix) || "RMVB".equalsIgnoreCase(postfix) || "RM".equalsIgnoreCase(postfix) || "FLV".equalsIgnoreCase(postfix) || "3GP".equalsIgnoreCase(postfix)) {
                            hadoopFile.setType("video");
                        } else {
                            hadoopFile.setType("text");
                        }
                        lists.add(hadoopFile);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void mkdir() throws Exception{
//        // 给定一个目录，若该目录存在，则直接删除再创建
//        // 若该目录不存在，直接创建
//        Path f = new Path("/test1");
//        if(fs.exists(f)){
//            fs.delete(f, true);
//        }
//        fs.mkdirs(f);
//    }

}
