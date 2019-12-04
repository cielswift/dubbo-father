package com.ciel.provider.shiro;

import com.ciel.pojo.Permissions;
import com.ciel.pojo.Role;
import com.ciel.pojo.User;
import com.ciel.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user  = (User)principals.getPrimaryPrincipal();

        for(Role role:user.getRoles()){

            authorizationInfo.addRole(role.getName()); //添加角色

            for(Permissions p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getName()); //添加权限
            }
        }

        for(Permissions p: user.getPermissions()){
            authorizationInfo.addStringPermission(p.getName()); //添加临时权限
        }

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        String userName = (String)token.getPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUserName(userName);

        user = userService.findById(user.getId());

        if(user == null){
            throw new AuthenticationException("用户不存在");
        }

        ByteSource byteSource = ByteSource.Util.bytes(user.getId()); //使用userid 做盐

        //盐在前, 密码在后 加密
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user.getPassword(), //密码
                byteSource, //盐
                getName()  //realm name
        );
        return authenticationInfo;
    }

}