package com.luolong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luolong.dao.OrderDao;
import com.luolong.model.Order;
import com.luolong.page.Pagination;
import com.luolong.util.dialect.Page;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

    @Override
    public Pagination getPage(Order order, int currentPage, int pageSize) {
    	Page<Order> page = new Page<Order>(currentPage, pageSize);
        List<Order> list = orderDao.getPage(page, order);
        Pagination p = new Pagination(currentPage, pageSize, page.getTotalCount());
        p.setList(list);
        return p;
    }
    
}