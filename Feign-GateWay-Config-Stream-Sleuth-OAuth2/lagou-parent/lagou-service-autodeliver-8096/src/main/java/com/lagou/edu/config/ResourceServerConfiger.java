package com.lagou.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer // 开启资源服务器功能
@EnableWebSecurity // 开启web访问安全
public class ResourceServerConfiger extends ResourceServerConfigurerAdapter {

    /**
     * 该⽅法⽤于定义资源服务器向远程认证服务器发起请求，进⾏token校验等事宜
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

//        // 设置当前资源服务的资源id
//        resources.resourceId("autodeliver");
//
//        // 定义token服务对象（token校验就应该靠token服务对象）
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//
//        // 校验端点/接⼝设置
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
//
//        // 携带客户端id和客户端安全码
//        remoteTokenServices.setClientId("client_lagou");
//        remoteTokenServices.setClientSecret("123456");
//
//        resources.tokenServices(remoteTokenServices);

        resources.resourceId("autodeliver").tokenStore(tokenStore()).stateless(true);// 无状态设置
    }


    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    private String sing_key="123456";

    @Autowired
    private LagouAccessTokenConvertor lagouAccessTokenConvertor;

    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(sing_key);
        jwtAccessTokenConverter.setVerifier(new MacSigner(sing_key));

        jwtAccessTokenConverter.setAccessTokenConverter(lagouAccessTokenConvertor);

        return jwtAccessTokenConverter;
    }

    /**
     * 场景：⼀个服务中可能有很多资源（API接⼝）
     * 某⼀些API接⼝，需要先认证，才能访问
     * 某⼀些API接⼝，压根就不需要认证，本来就是对外开放的接⼝
     * 我们就需要对不同特点的接⼝区分对待（在当前configure⽅法中完成），
     * 设置是否需要经过认证
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()

                //autodeliver为前缀的请求需要认证
                .antMatchers("/autodeliver/**").authenticated()

                //demo为前缀的请求需要认证
                .antMatchers("/demo/**").authenticated()
                .anyRequest().permitAll();
    }
}
