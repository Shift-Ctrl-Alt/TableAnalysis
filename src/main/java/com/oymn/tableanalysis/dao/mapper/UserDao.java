package com.oymn.tableanalysis.dao.mapper;

import com.oymn.tableanalysis.dao.pojo.Role;
import com.oymn.tableanalysis.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    
    void addUser(User user);

    User getUserByName(String username);

    void updateUser(User user);

    void deleteUserById(Long id);

    int getUserCount();
    
    List<User> getUsers(Map<String, Object> params);

    User getUserById(Long userId);

    Long getUserRoleByUidAndRid(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void addRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void deleteRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    List<Role> getRolesByUserId(Long userId);

    void deleteRoleByUserId(Long userId);
}
