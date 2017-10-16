package com.skishop.servlets.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.skishop.dao.product.CartDao;
import com.skishop.service.Cart;
import com.skishop.service.CartItem;

/**
 * Servlet implementation class ProductPayServlet
 */
public class CartPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//1.获取session用户信息
		HttpSession session=request.getSession();
		Cart c=(Cart)session.getAttribute("cart");
		Iterator i=c.container.values().iterator();
		//2.连接数据库
		CartDao cd = new CartDao();
		while(i.hasNext()){
			CartItem ci=(CartItem)i.next();
			ci.getProduct().getId();
			cd.addProductInfo(ci);
		}
		response.sendRedirect("end.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
