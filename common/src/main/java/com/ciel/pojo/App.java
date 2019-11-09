package com.ciel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor //所有参数的构造函数
@NoArgsConstructor  //无参数的构造函数
@Accessors(chain = true) //链式风格访问
@RequiredArgsConstructor  //将标记为@NoNull的属性生成一个构造器
@ToString //生成所有属性的toString()方法
@EqualsAndHashCode(callSuper = false) //生成equals()方法和hashCode方法,callSuper使用父类继承的属性; 可通过参数exclude排除一些属性;可通过参数of指定仅使用哪些属性
@Data //@Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集
@TableName("ssh_application")
public class App extends BasePojo{

    @NonNull //为空报错
    @TableField(value = "name")
    private String name;

    private String userId;

    @TableField(exist = false)
    private User user;

//    @SneakyThrows
//    这个注解用在方法上，可以将方法中的代码用try-catch语句包裹起来，捕获异常并在catch中用Lombok.sneakyThrow(e)把异常抛出，
//    可以使用@SneakyThrows(Exception.class)的形式指定抛出哪种异常，
}
