package com.oymn.tableanalysis.controller;

import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Permission;
import com.oymn.tableanalysis.service.PermissionService;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("权限的相关接口")
@RestController
@RequestMapping("/system/permission")
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    @ApiOperation("添加新的权限")
    @PostMapping("/add")
    @PreAuthorize("@ex.hasAuthority('system:permission:add')")
    public Result<String> addPermission(@ApiParam("权限实体类") @RequestBody Permission permission){
        String id = permissionService.addPermission(permission);
        return Result.success(id);  
    }
    
    @ApiOperation("修改权限")
    @PutMapping("/update")
    @PreAuthorize("@ex.hasAuthority('system:permission:update')")
    public Result updatePermission(@ApiParam("权限实体类") @RequestBody Permission permission){
        permissionService.updatePermission(permission);
        return Result.success();
    }
    
    @ApiOperation("分页查询权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页几条数据", required = true),
            @ApiImplicitParam(name = "pageNo", value = "第几页", required = true)
    })
    @GetMapping("/page-permission")
    @PreAuthorize("@ex.hasAuthority('system:permission:page')")
    public Result<PageResult<Permission>> pagePermission(@RequestParam Integer pageNo,
                                                         @RequestParam Integer pageSize){
        PageResult<Permission> permissionList = permissionService.pagePermission(pageNo, pageSize);
        return Result.success(permissionList);
    }
    
    
}
