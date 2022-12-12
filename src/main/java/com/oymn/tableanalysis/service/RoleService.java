package com.oymn.tableanalysis.service;

import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Permission;
import com.oymn.tableanalysis.dao.pojo.Role;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    /**
     * 通过用户名获取用户的所有权限
     * @param userId
     * @return
     */
    List<String> getPermissionByUserId(Long userId);

    /**
     * 通过访问路径获取所需权限
     * @param url
     * @return
     */
    Collection<ConfigAttribute> getPermissionByPath(String url);

    /**
     * 给用户设置角色
     * @param userId
     * @param roleName
     */
    void setUserRole(Long userId, String roleName);

    /**
     * 添加角色
     * @param role
     * @return
     */
    String addRole(Role role);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 通过id删除角色
     * @param roleId
     */
    void deleteRoleById(Long roleId);

    /**
     * 分页查询角色
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult<Role> pageRole(Integer pageNo, Integer pageSize);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    void addPermission(Long roleId, Long permissionId);

    /**
     * 通过角色id获取权限
     * @param roleId
     * @return
     */
    List<Permission> getPermissionByRoleId(Long roleId);

    /**
     * 删除角色的某个权限
     * @param roleId
     * @param permissionId
     */
    void deletePermission(Long roleId, Long permissionId);

    /**
     * 通过id获取角色
     * @param roleId
     * @return
     */
    Role getRoleById(Long roleId);

    /**
     * 通过用户名获取角色
     * @param username
     * @return
     */
    List<Role> getRoleByUsername(String username);
    
}
