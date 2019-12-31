package com.luke.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.luke.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean createFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理
        shiroFilterFactoryBean.setSecurityManager(createSecurityManager());
        //添加Shiro内置过滤器
        /**
         * Shiro 内置过滤器，可以实现权限相关的拦截器
         * 常用过滤器：
         * anon:无需认证（登录）可以访问
         * authc:必须认证才可以访问
         * user: 如果使用了rememberMe功能，可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */

        Map<String,String> filterMap=new LinkedHashMap<String,String>();
        filterMap.put("/user/other","anon"); //不需要认证,注意：anon要放到前面
        //添加搜全过滤器
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        //所有操作需要登录
        filterMap.put("/user/**","authc"); //所有/user/**资源需要认证

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //修改未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        return shiroFilterFactoryBean;
    }


    /**
     * 创建SecurityManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager createSecurityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(createRealm());
        return securityManager;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean
    public UserRealm createRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialet,用于thymeleaf中使用shiro标签
     *
     */
    @Bean
    public ShiroDialect createShiroDialect(){
        return new ShiroDialect();
    }
}
