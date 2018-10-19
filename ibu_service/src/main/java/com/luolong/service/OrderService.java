package com.luolong.service;

import com.luolong.model.Order;
import com.luolong.page.Pagination;

public interface OrderService {
    /**
     * 分页查询
     */
    Pagination getPage(Order order, int currentPage, int pageSize);
}