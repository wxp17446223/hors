package cn.hors.bean;

import lombok.Data;

import java.util.List;

@Data
public class PRole {
    /**
     * 角色标识符
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色拥有的资源信息
     */
    private List<PResource> resources;
}

