package com.oymn.tableanalysis.controller;

import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.pojo.PageResult;
import com.oymn.tableanalysis.dao.pojo.Role;
import com.oymn.tableanalysis.dao.pojo.User;
import com.oymn.tableanalysis.service.UserService;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("用户的相关接口")
@RestController
@RequestMapping("/system/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    //用于对密码的加密
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 添加用户
     * @param user 实体对象
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
    @PreAuthorize("@ex.hasAuthority('system:user:add')")
    public Result<Long> addUser(@ApiParam("用户实体类") @RequestBody User user) {
        
        if(user.getUsername().isEmpty() || "".equals(user.getUsername().trim())){
            throw new ConditionException("用户名为空或者只包含空格");
        }
        if(user.getPassword().isEmpty() || "".equals(user.getPassword().trim())){
            throw new ConditionException("密码为空或者只包含空格");
        }

        User dbUser = userService.getUserByName(user.getUsername());
        if(dbUser != null){
            throw new ConditionException("用户名已存在");
        }

        String newPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPwd);

        // 保存数据
        Long id = userService.addUser(user);
        return Result.success(id);
    }
    
    @ApiOperation("编辑用户")
    @PutMapping("/update")
    @PreAuthorize("@ex.hasAuthority('system:user:update')")
    public Result updateUser(@ApiParam("用户实体类") @RequestBody User user){
        if(user.getPassword().isEmpty() || "".equals(user.getPassword().trim())){
            throw new ConditionException("密码为空或者只包含空格");
        }

        String newPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPwd);
        
        userService.updateUser(user);
        return Result.success();
    }
    
    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    @PreAuthorize("@ex.hasAuthority('system:user:delete')")
    public Result deleteUser(@RequestParam Long id){
        userService.deleteUserById(id);
        return Result.success();
    }
    
    @ApiOperation("分页列表用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页几条数据", required = true),
            @ApiImplicitParam(name = "pageNo", value = "第几页", required = true)
    })
    @GetMapping("/page-user")
    @PreAuthorize("@ex.hasAuthority('system:user:page')")
    public Result<PageResult<User>> pageUser(@RequestParam Integer pageSize,
                                             @RequestParam Integer pageNo){
        PageResult<User> pageUser = userService.pageUser(pageNo, pageSize);
        return Result.success(pageUser);
    }
    
    @ApiOperation("给用户分配角色")
    @PostMapping("/add-role")
    @PreAuthorize("@ex.hasAuthority('system:user:addRole')")
    public Result addRole(@RequestParam Long userId, @RequestParam Long roleId){
        userService.addRole(userId, roleId);
        return Result.success();
    }
    
    @ApiOperation("删除用户的角色")
    @DeleteMapping("/delete-role")
    @PreAuthorize("@ex.hasAuthority('system:user:deleteRole')")
    public Result deleteRole(@RequestParam Long userId, @RequestParam Long roleId){
        userService.deleteRole(userId, roleId);
        return Result.success();
    }
    
    @ApiOperation("查询用户的角色")
    @GetMapping("/get-role")
    @PreAuthorize("@ex.hasAuthority('system:user:getRole')")
    public Result<List<Role>> getRoles(@RequestParam @ApiParam("用户的id") Long userId){
        List<Role> roleList = userService.getRolesByUserId(userId);
        return Result.success(roleList);
    }
    
    
    @ApiOperation("测试是否联网")
    @GetMapping("/test")
    public Result test(){
        return Result.success("success");
    }
    
}
