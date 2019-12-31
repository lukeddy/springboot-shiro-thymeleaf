package com.luke.shiro;

import com.luke.domain.User;
import com.luke.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //改为从数据库中获取授权信息
        Subject subject=SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        User dbUser=userService.findById(user.getId());
        info.addStringPermission(dbUser.getPermissions());

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设用户信息为
//        String uname="admin";
//        String upwd="123456";


        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;

        User user=userService.findByUsername(token.getUsername());
        if(null==user){
            //用户名不匹配
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
