package com.ciel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName("ssh_role")
public class Role extends BasePojo {

    private String name;

    @TableField(exist = false)
    private List<User> users = new ArrayList<>();

    @TableField(exist = false)
    private List<Permissions> permissions = new ArrayList<>();
}
