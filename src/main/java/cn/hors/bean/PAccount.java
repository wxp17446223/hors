package cn.hors.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PAccount implements UserDetails {
    private Integer id;

    private String account;

    private String password;

    private String head;

    private boolean locked;

    private boolean expired;

    private boolean enabled;

    private List<GrantedAuthority> authorities;
    /**
     * 账号拥有的角色信息
     */
    private List<PRole> roles;

    public List<PResource> getResources(){
        List<PResource> resources = new ArrayList<>();
        if (roles != null&&roles.size()>0) {
            for (PRole role : roles) {
                resources.addAll(role.getResources());
            }
        }
        //将资源进行去重操作
        return resources.stream().distinct().collect(Collectors.toList());
    }

    public void addRole(String role){
        if(authorities == null){
            authorities = new ArrayList<>();
        }
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+role;
            }
        });
    }

    public void addAuthority(String authority){
        if(authorities == null){
            authorities = new ArrayList<>();
        }
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return authority;
            }
        });
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !enabled;
    }
}