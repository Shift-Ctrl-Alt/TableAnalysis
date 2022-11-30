package com.oymn.tableanalysis.service.impl;

import com.oymn.tableanalysis.common.StatusCode;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.mapper.PermissionDao;
import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Permission;
import com.oymn.tableanalysis.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {
    
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Long addPermission(Permission permission) {
        Permission dbPermission = permissionDao.getPermissionByName(permission.getName());
        if(dbPermission != null){
            throw new ConditionException("权限名称已存在");
        }
        
        permissionDao.addPermission(permission);
        
        return permission.getId();
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }


    @Override
    public PageResult<Permission> pagePermission(Integer pageNo, Integer pageSize) {
        if(pageNo == null || pageSize == null){
            throw new ConditionException(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }
        
        Map<String, Integer> params = new HashMap<>();
        params.put("start", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        int count = permissionDao.getPermissionCount();
        List<Permission> permissionList = permissionDao.pagePermission(params);
        
        return new PageResult<>(count, permissionList);
    }

    @Override
    public Permission getPermissionById(Long permissionId) {
        return permissionDao.getPermissionById(permissionId);
    }
}
