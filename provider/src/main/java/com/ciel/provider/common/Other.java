package com.ciel.provider.common;

import lombok.Data;

@Data
public class Other {
    //测试:通过 app-other.xml添加bean,然后@ImportResource(locations = "classpath:app-other.xml")导入配置文件
    private String name;
    private String address;
}
