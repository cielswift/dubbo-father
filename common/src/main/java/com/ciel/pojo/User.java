package com.ciel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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

    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();

    @TableField(exist = false)
    private List<Permissions> permissions = new ArrayList<>(); //临时权限

   // @NotBlank	判断字符串是否为null 或者是空串(去掉首尾空格)
   // @NotEmpty:	判断字符串是否null 或者是空串。
    //@Length	判断字符的长度(最大或者最小)
    //@Min	判断数值最小值
    //@Max	判断数值最大值
    //@Email	判断邮箱是否合法

//    @NotBlank(message="用户名不能为空")  //非空校验
//    @Length(min=3,max=6)
 //   @Valid
 //   @Validated


}
