package com.luolong.service;

import java.util.List;

import com.luolong.model.Role;
import com.luolong.model.User;
import com.luolong.page.Pagination;

public interface UserService {
    User selectUserById(Integer userId);  
    
    User selectUser(User user);
    
    /**
     * 分页查询
     * @param account
     * @param currentPage
     * @param pageSize
     * @return
     */
    Pagination getPage(User user, int currentPage, int pageSize);
    
    int add(User user);
    
    int edit(User user);
    
    List<Role> getUserRoleByUser(User user);
    
    /**
     * 导出
     * @param user
     * @return
     */
    public User exportUser(User user);
}