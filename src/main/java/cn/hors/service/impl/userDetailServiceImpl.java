package cn.hors.service.impl;

import cn.hors.bean.PAccount;
import cn.hors.bean.PResource;
import cn.hors.bean.PRole;
import cn.hors.service.PAccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class userDetailServiceImpl implements UserDetailsService{
    @Resource
    private PAccountService service;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        PAccount pAccount = service.findByAccount(name);
//        System.out.println(pAccount.getPassword());
        List<PRole> roles = pAccount.getRoles();
        for (PRole role : roles) {
            pAccount.addRole(role.getCode());
            List<PResource> resources = role.getResources();
            for (PResource resource : resources) {
                pAccount.addAuthority(resource.getAuthority());
            }
        }
        return pAccount;
    }
}
