package com.skishop.servlets.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.skishop.dao.user.UserDao;
import com.skishop.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String age = request.getParameter("age");

		response.getWriter().println(name + ":" + age);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 1.获取客户请求
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// 2.获取数据库表中用户信息
		UserDao ud = new UserDao();
		List<User> userlist = ud.checkUserInfo();
		// 3.将请求中的信息和数据库中信息匹配
		for (int i = 0; i < userlist.size(); i++) {
			User u = userlist.get(i);
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null) {
					user = new User();
					user.setEmail(email);
					user.setPassword(password);
				}
				session.setAttribute("user", user);
				response.sendRedirect("productlist");
			}
		}
	}
}
