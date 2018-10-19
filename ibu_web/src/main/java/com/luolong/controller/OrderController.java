package com.luolong.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.luolong.model.Order;
import com.luolong.page.Pagination;
import com.luolong.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/orderList.do")
	public ModelAndView userList(Order order) {
		ModelAndView mav = new ModelAndView("/order/orderList");
		if(StringUtils.isBlank(order.getStartTime()) || StringUtils.isBlank(order.getEndTime())){
			Pagination page= new Pagination();
			setTotalSize(0);
			mav.addObject("page", page);
			mav.addObject("totalSize",getTotalSize());
			mav.addObject("pageSize", getPageSize());
			return mav;
		}
		Pagination page = orderService.getPage(order, getCurrentPage(), getPageSize());
		setTotalSize(Long.valueOf(page.getTotalCount()).intValue());
		mav.addObject("page", page);
		mav.addObject("totalSize",getTotalSize());
		mav.addObject("pageSize", getPageSize());
		return mav;
	}
   

}  