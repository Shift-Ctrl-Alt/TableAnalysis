package com.oymn.tableanalysis.config;
import com.oymn.tableanalysis.handler.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)   //开启prePostEnabled注解的使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //用于密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //用于登录验证时对Token的验证
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //认证失败处理器
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    //权限不足处理器
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //认证所有的请求
                .authorizeRequests()
                .antMatchers("/user/login","/user/register","/user/token/check").permitAll()
                // 除上面外的所有请求全部需要鉴权认证成功
                .anyRequest().authenticated();

        http.headers().frameOptions().disable();

        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        //配置异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //配置权限不足处理器
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //所需要用到的静态资源，允许访问
        web.ignoring().antMatchers( "/swagger-ui.html",
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/webjars/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}
