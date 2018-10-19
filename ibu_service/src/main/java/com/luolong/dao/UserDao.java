package com.luolong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luolong.model.Role;
import com.luolong.model.User;
import com.luolong.model.UserRole;
import com.luolong.util.dialect.Page;

public interface UserDao {
	 /**
     * @param userId
     * @return User
     */
    public User selectUserById(Integer userId);  
    
    
    public User selectUser(User user); 
    
    
    List<User> getPage(@Param("page") Page<User> page,@Param("user") User user);
    
    int add(User user);
    
    int addUserRole(UserRole userRole);
    
    void delUserRole(User user);
    
    int edit(User user);
    
    List<Role> getUserRoleByUser(User user);

}
