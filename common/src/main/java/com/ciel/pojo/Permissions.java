package com.ciel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName("ssh_permissions")
public class Permissions extends BasePojo{

    private String name;

    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();

    @TableField
    private List<User> users = new ArrayList<>();

}
