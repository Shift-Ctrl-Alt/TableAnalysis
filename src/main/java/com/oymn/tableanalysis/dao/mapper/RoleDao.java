package com.oymn.tableanalysis.dao.mapper;



import com.oymn.tableanalysis.dao.pojo.Permission;
import com.oymn.tableanalysis.dao.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {
    
    List<String> getPermissionByUserId(Long userId);

    Collection<ConfigAttribute> getPermissionByPath(String url);

    Role getRoleByName(String roleName);

    void setUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRoleById(Long roleId);

    int getRoleCount();


    List<Role> getRoles(Map<String, Integer> params);

    void addPermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    Role getRoleById(Long roleId);

    List<Permission> getPermissionByRoleId(Long roleId);

    void deletePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    Long getRolePermissionByRidAndPid(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    List<Role> getRoleByUsername(String username);

    void deletePermissionByRoleId(Long roleId);
}
