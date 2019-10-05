package com.ciel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("ssh_user")
public class User extends BasePojo{

    private String name;
    private String password;


    @TableField(exist = false)
    private List<App> apps = new ArrayList<>();

    private Date birthday;

    private Boolean sex;

    private String headSculpturePath;

    private BigDecimal price;

}
