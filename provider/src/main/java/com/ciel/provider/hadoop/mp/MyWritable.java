package com.ciel.provider.hadoop.mp;


import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MyWritable implements Writable,Comparable<MyWritable> {
    public MyWritable(){}

    public String name;
    public Integer age;


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBytes(name);
        out.writeInt(age);
    }

    @Override  //注意反序列化的顺序和序列化的顺序完全一致
    public void readFields(DataInput in) throws IOException {

        name = in.readLine();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "MyWritable{}";
    }

    @Override
    public int compareTo(MyWritable str) {
        return str.age.compareTo(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
