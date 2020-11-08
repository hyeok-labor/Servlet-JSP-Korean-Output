package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Servlet Context 사전의 문맥, 책갈피 역할을 함 => 페이지를 이어갈 수 있게
 * 											상태값을 유지하게 함. 문맥 유지
 */
@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet(add)
		// apllication 을 이용해 데이터 저장
		ServletContext application = request.getServletContext();
		// session 을 이용해 데이터 저장
		HttpSession session = request.getSession();
		// Cookie 를 이용하기
		Cookie[] cookies = request.getCookies();

		response.setCharacterEncoding("UTF-8");					// encoding 을 어떻게 할 것인지
		response.setContentType("text/html; charset=UTF-8");	// UTF-8 형식으로 읽으라고 요청하는것


		String v_ = request.getParameter("v");
		String op_ = request.getParameter("operator");

		int v=0;

		if(v_!=null && !v_.equals(""))
			v = Integer.parseInt(v_);

		if(op_.equals("=")) {

			//int x = (Integer)application.getAttribute("value");
			//int x = (Integer)session.getAttribute("value");
			int x=0;
			// value 얻기
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x=Integer.parseInt(c.getValue());
					break;	// 찾았으면 검색 그만
				}
			}
			// operator 얻기
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator=c.getValue();
					break;	// 찾았으면 검색 그만
				}
			}

			int y = v;

			//String operator = (String)application.getAttribute("op_");
			//String operator = (String)session.getAttribute("op_");

			int result=0;

			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;

			response.getWriter().printf("result is %d\n",result);

		}
		else {		// 값을 저장
			//			application.setAttribute("value", v);
			//			application.setAttribute("op_", op_);

			//			session.setAttribute("value", v);
			//			session.setAttribute("op_", op_);

			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op_);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);	// 쿠기 생존시간
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie);
			response.addCookie(opCookie);

			response.sendRedirect("calc2.html");
		}




	}

}
