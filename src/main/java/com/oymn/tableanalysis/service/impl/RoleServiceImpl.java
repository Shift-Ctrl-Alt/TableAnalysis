package com.oymn.tableanalysis.service.impl;

import com.oymn.tableanalysis.common.StatusCode;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.mapper.RoleDao;
import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Permission;
import com.oymn.tableanalysis.dao.pojo.Role;
import com.oymn.tableanalysis.service.PermissionService;
import com.oymn.tableanalysis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private PermissionService permissionService;
    
    @Override
    public List<String> getPermissionByUserId(Long userId) {
        return roleDao.getPermissionByUserId(userId);
    }

    @Override
    public Collection<ConfigAttribute> getPermissionByPath(String url) {
        return roleDao.getPermissionByPath(url);
    }

    @Override
    public void setUserRole(Long userId, String roleName) {
        if(userId == null || roleName == null){
            throw new ConditionException(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }
        
        //判断该角色是否存在
        Role role = roleDao.getRoleByName(roleName);
        if(role == null){
            throw new ConditionException("该角色不存在");
        }
        
        roleDao.setUserRole(userId, role.getId());
    }

    @Override
    public Long addRole(Role role) {
        Role dbRole = roleDao.getRoleByName(role.getName());
        if(dbRole != null){
            throw new ConditionException("该角色名称已存在");
        }
        
        roleDao.addRole(role);
        return role.getId();
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleById(Long roleId) {
        roleDao.deleteRoleById(roleId);
        roleDao.deletePermissionByRoleId(roleId);
    }

    @Override
    public PageResult<Role> pageRole(Integer pageNo, Integer pageSize) {
        if(pageNo == null || pageSize == null){
            throw new ConditionException(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }
        Map<String, Integer> params = new HashMap<>();
        params.put("start", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        int count = roleDao.getRoleCount();
        List<Role> roleList = roleDao.getRoles(params);
        
        return new PageResult<>(count, roleList);
    }

    @Override
    public void addPermission(Long roleId, Long permissionId) {
        if(roleId == null || permissionId == null){
            throw new ConditionException(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }
        
        Role role = roleDao.getRoleById(roleId);
        if(role == null){
            throw new ConditionException("该角色不存在");
        }
        
        Permission permission = permissionService.getPermissionById(permissionId);
        if(permission == null){
            throw new ConditionException("该权限不存在");
        }
        
        Long id = roleHasPermission(roleId, permissionId);
        if(id != null){
            throw new ConditionException("角色已具有该权限");
        }
        
        
        roleDao.addPermission(roleId, permissionId);
    }

    private Long roleHasPermission(Long roleId, Long permissionId) {
        return roleDao.getRolePermissionByRidAndPid(roleId, permissionId);
    }

    @Override
    public List<Permission> getPermissionByRoleId(Long roleId) {
        return roleDao.getPermissionByRoleId(roleId);
    }

    @Override
    public void deletePermission(Long roleId, Long permissionId) {
        roleDao.deletePermission(roleId, permissionId);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleDao.getRoleById(roleId);
    }

    @Override
    public List<Role> getRoleByUsername(String username) {
        return roleDao.getRoleByUsername(username);
    }
}
