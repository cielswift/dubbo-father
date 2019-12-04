package com.ciel.provider.j8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;

@RepAnntion(value = "bb")
@RepAnntion(value = "aa")
public class J8ANN<@TypeParam T> {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        RepAnntion annotation = J8ANN.class.getAnnotation(RepAnntion.class);
        RepAnntion[] annotationsByType = J8ANN.class.getAnnotationsByType(RepAnntion.class); //获取多个重复的注解

        Annotation[] annotations = J8ANN.class.getAnnotations(); //这里看到 其实是RepActions2 ,重复注解的容器

        System.out.println(annotation);

        Arrays.stream(annotationsByType).forEach(System.out::println);

        Arrays.stream(annotations).forEach(System.out::println);


        try (FileInputStream file = new FileInputStream(new File("c:/ciel/a.txt"))) { //自动关闭流

        } catch (Exception e) {
        }
        var xia = new J8ANN<>(); //类型推断

        FileInputStream file = new FileInputStream(new File("c:/ciel/a.txt"));

        file.transferTo(new FileOutputStream(new File("c:/ciel/b.txt"))); //写入

        Charset charset = Charset.forName("utf-8");

        // CopyOnWriteArrayList  适合 读多写少
        //ConcurrentHashMap  //适合少量并发 线程安全
        // ConcurrentSkipListMap //适合大量并发 线程安全

    }

}
