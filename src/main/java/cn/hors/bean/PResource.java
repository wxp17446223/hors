package cn.hors.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PResource {
    private Integer id;

    private String name;

    private String url;
    private Integer pid;
    /**
     * 子资源列表
     */
    private List<PResource> children;
    /**
     * 父资源
     */
    private PResource parent;
    /**
     * 资源类型
     */
    private Integer type;
    /**
     * 权限
     */
    private String authority;

    private String code;
}