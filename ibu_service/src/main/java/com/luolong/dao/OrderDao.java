package com.luolong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luolong.model.Order;
import com.luolong.util.dialect.Page;

public interface OrderDao {
    
    List<Order> getPage(@Param("page") Page<Order> page,@Param("order") Order order);

}
