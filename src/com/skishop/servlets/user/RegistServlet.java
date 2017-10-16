package com.skishop.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.skishop.dao.user.UserDao;
import com.skishop.entity.User;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 1.获取用户信息
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String tpassword = request.getParameter("tpassword");

		if (email != null) {
			if (password.equals(tpassword) && password != null) {
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null) {
					user = new User();
				}

				User u = new User();
				u.setLastname(lastname);
				u.setFirstname(firstname);
				u.setEmail(email);
				u.setPassword(tpassword);
				u.setTpassword(tpassword);

				UserDao ud = new UserDao();
				ud.addUserInfo(u);
				session.setAttribute("user", u);
				response.sendRedirect("ok.jsp");
			} else {
				System.out.println("您输入的两次密码不相同，请重新填写");
			}
		} else {
			System.out.println("邮箱不能为空");
		}
	}

}
