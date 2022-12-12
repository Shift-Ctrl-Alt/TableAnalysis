package com.oymn.tableanalysis.controller;

import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Permission;
import com.oymn.tableanalysis.dao.pojo.Role;
import com.oymn.tableanalysis.service.RoleService;
import com.oymn.tableanalysis.utils.SnowFlake;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色的相关接口")
@RestController
@RequestMapping("/system/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @ApiOperation("添加新的角色")
    @PostMapping("/add")
    @PreAuthorize("@ex.hasAuthority('system:role:add')")
    public Result<String> addRole(@ApiParam("角色实体类") @RequestBody Role role){
        role.setId(SnowFlake.nextId());
        String id = roleService.addRole(role);
        return Result.success(id);
    }
    
    @ApiOperation("编辑修改角色")
    @PutMapping("/update")
    @PreAuthorize("@ex.hasAuthority('system:role:update')")
    public Result updateRole(@ApiParam("角色实体类") @RequestBody Role role){
        roleService.updateRole(role);
        return Result.success();
    }
    
    @ApiOperation("删除角色")
    @DeleteMapping("/delete")
    @PreAuthorize("@ex.hasAuthority('system:role:delete')")
    public Result deleteRole(@ApiParam("角色的id") @RequestParam Long roleId){
        roleService.deleteRoleById(roleId);
        return Result.success();
    }
    
    @ApiOperation("分页查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页几条数据", required = true),
            @ApiImplicitParam(name = "pageNo", value = "第几页", required = true)
    })
    @GetMapping("/page-role")
    @PreAuthorize("@ex.hasAuthority('system:role:page')")
    public Result<PageResult<Role>> pageRole(@RequestParam Integer pageSize,
                                             @RequestParam Integer pageNo){
        PageResult<Role> pageRole = roleService.pageRole(pageNo, pageSize);
        return Result.success(pageRole);
    }
    
    @ApiOperation("给角色添加权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色的id", required = true),
            @ApiImplicitParam(name = "permissionId", value = "权限的id", required = true)
    })
    @PostMapping("/add-permission")
    @PreAuthorize("@ex.hasAuthority('system:role:addPermission')")
    public Result addPermission(@RequestParam Long roleId, @RequestParam Long permissionId){
        roleService.addPermission(roleId, permissionId);
        return Result.success();
    }
    
    @ApiOperation("获取该角色的所有权限")
    @PostMapping("/get-permission")
    @PreAuthorize("@ex.hasAuthority('system:role:getPermission')")
    public Result<List<Permission>> getPermission(@RequestParam @ApiParam("角色的id") Long roleId){
        List<Permission> permissionList = roleService.getPermissionByRoleId(roleId);
        return Result.success(permissionList);
    }
    
    @ApiOperation("删除该角色的某个权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色的id", required = true),
            @ApiImplicitParam(name = "permissionId", value = "权限的id", required = true)
    })
    @DeleteMapping("/delete-permission")
    @PreAuthorize("@ex.hasAuthority('system:role:deletePermission')")
    public Result deletePermission(@RequestParam Long roleId, @RequestParam Long permissionId){
        roleService.deletePermission(roleId, permissionId);
        return Result.success();
    }
    
    
    
    
}
