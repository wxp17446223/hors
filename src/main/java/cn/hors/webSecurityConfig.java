package cn.hors;

import cn.hors.bean.PAccount;
import cn.hors.bean.PResource;
import cn.hors.consts.SystemConst;
import cn.hors.service.PAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Configuration
@EnableWebSecurity
//开启注解式权限校验
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true,proxyTargetClass = true)
public class webSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService service;
    @Resource
    private PAccountService pAccountService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new PasswordEncoder() {

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                // TODO Auto-generated method stub
                //MD5  32位小写加密方式
                return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
            }

            @Override
            public String encode(CharSequence rawPassword) {
                // TODO Auto-generated method stub
                return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
            }
        });
    }
    //DigestUtils.md5DigestAsHex("123456");
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        //http.formLogin();//开启表单登录
        http.authorizeRequests()
                .antMatchers("/") // 不需要登录就可以访问
                .permitAll()
//                .antMatchers("/p1").permitAll() //任何人都可以访问p1
//                .antMatchers("/p2").hasAnyRole("admin") //只有拥有admin角色的人可以访问
//                 .antMatchers("/p3").access("hasRole('admin') or hasAuthority('p3')")//只有拥有p3权限的可以访问
//                //除以上请求之外的其他请求 需要登录才能访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("name")
                .loginPage("/account/l").permitAll()
                .loginProcessingUrl("/index")
                //登录成功处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        String name = request.getParameter("name");
                        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                        for (GrantedAuthority authority : authorities) {
                            System.out.println(authority.getAuthority());
                        }
                        PAccount account =  pAccountService.findByAccount(name);
                        HttpSession session = request.getSession();
                        List<PResource> resources = account.getResources();
                        // pid 子菜单集合
                        Map<Integer,List<PResource>> map = new HashMap<>();
                        for (PResource resource : resources) {
                            if(resource.getType() == 1){
                                if(!map.containsKey(resource.getPid())){
                                    map.put(resource.getPid(),new ArrayList<>());
                                }
                                map.get(resource.getPid()).add(resource);
                            }
                        }
                        account.setHead("/hors/images/"+account.getHead());
                        session.setAttribute(SystemConst.MENUS,map);
                        session.setAttribute(SystemConst.LOGIN_STATUS,account);
                        response.sendRedirect("/backstage/index");
                    }
                })
                //.successForwardUrl("/account/login")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        //"/hors/css/styles.css","/hors/js/jquery-1.7.1.min.js",
        web.ignoring().antMatchers("/find","/depart/**/*","/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html",
                "/**/*.png", "/**/*.jpg","/**/*.gif","/**/*.svg","/**/*.eot","/**/*.ttf","/**/*.woff","/**/*.woff2");
    }
}
