package com.oymn.tableanalysis.service;


import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Permission;

public interface PermissionService {


    Long addPermission(Permission permission);

    void updatePermission(Permission permission);

    PageResult<Permission> pagePermission(Integer pageNo, Integer pageSize);

    Permission getPermissionById(Long permissionId);
    
    
}
