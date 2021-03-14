package com.lagou.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer// 开启认证服务器功能
public class OauthServerConfiger extends AuthorizationServerConfigurerAdapter {

    /**
     * 客户端信息
     * ⽤来配置客户端详情服务（ClientDetailsService），客户端详情信息在 这
     * ⾥进⾏初始化，你能够把客户端详情信息写死在这⾥或者是通过数据库来存
     * 储调取详情信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);

        //此处是使用内存存放客户端信息
//        clients.inMemory()// 客户端信息存储在什么地方，可以在内存中，可以在数据库里
//                .withClient("client_lagou")// 添加一个client配置,指定其client_id
//                .secret("123456")// 指定客户端的密码/安全码
//                .resourceIds("autodeliver") // 指定客户端所能访问资源id清单，
//                                            // 此处的资源id是需要在具体的资源服务器上也配置一样
//
//                // 认证类型/令牌颁发模式，可以配置多个在这里，但是不一定都用，
//                // 具体使用哪种方式颁发token，需要客户端调用的时候传递参数指定
//                .authorizedGrantTypes("password","refresh_token")
//
//                // 客户端的权限范围，此处配置为all全部即可
//                .scopes("all");

        //现在改成读取数据库
        clients.withClientDetails(createJdbcClientDetailsService());
    }

    /**
     * 这里需要注入一个DataSource
     *
     * 1、引入pom文件
     * 2、添加配置文件
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 把客户端的信息放
     * @return
     */
    public JdbcClientDetailsService createJdbcClientDetailsService(){
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        return jdbcClientDetailsService;
    }



    /**
     * 认证服务器最终是以api接⼝的⽅式对外提供服务（校验合法性并⽣成令牌、校验令牌等）
     * 那么，以api接⼝⽅式对外的话，就涉及到接⼝的访问权限，我们需要在这⾥进⾏必要的配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);

        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * 认证服务器是玩转token的，那么这⾥配置token令牌管理相关（token此时就是⼀个字符串，
     * 当下的token需要在服务器端存储，
     * 那么存储在哪⾥呢？都是在这⾥配置）
     *
     * ⽤来配置令牌（token）的访问端点和令牌服务(token services)
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);

        endpoints
                .tokenStore(tokenStore()) // 指定token的存储⽅法
                .tokenServices(authorizationServerTokenServices()) // token服务的⼀个描述，可以认为是token⽣成细节的描述，⽐如有效时间多少等
                .authenticationManager(authenticationManager) //指定认证管理器，随后注⼊⼀个到当前类使⽤即可
        .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     该⽅法⽤于创建tokenStore对象（令牌存储对象）
     token以什么形式存储
     */
    public TokenStore tokenStore(){

        //如果是以内存形式存放的Token，则token就是一个类似uuid的字符串
        //return new InMemoryTokenStore();

        //这里使用jwt令牌
        //需要传入一个令牌转换器，因为jwt令牌在进行使用时，需要进行加密解密
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    private String sing_key="123456";

    /**
     * 返回jwt令牌转换器（帮助我们⽣成jwt令牌的）
     * 在这⾥，我们可以把签名密钥传递进去给转换器对象
     * @return
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(sing_key);
        jwtAccessTokenConverter.setVerifier(new MacSigner(sing_key));

        return jwtAccessTokenConverter;
    }



    /**
     * 该⽅法⽤户获取⼀个token服务对象（该对象描述了token有效期等信息）
     */
    public AuthorizationServerTokenServices authorizationServerTokenServices(){

        //使用默认实现
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

        // 是否开启令牌刷新
        defaultTokenServices.setSupportRefreshToken(true);

        defaultTokenServices.setTokenStore(tokenStore());

        //针对Token进行增强，使用Jwttoken转换
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());

        // 设置令牌有效时间（⼀般设置为2个⼩时）,这里设置为20秒
        defaultTokenServices.setAccessTokenValiditySeconds(20);

        // 设置刷新令牌的有效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);

        return defaultTokenServices;
    }



}
