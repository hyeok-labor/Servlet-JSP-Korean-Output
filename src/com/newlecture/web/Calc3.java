package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet Context 사전의 문맥, 책갈피 역할을 함 => 페이지를 이어갈 수 있게
 * 											상태값을 유지하게 함. 문맥 유지
 */
@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Cookie 를 이용하기
		Cookie[] cookies = request.getCookies();


		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");

		String exp = "";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					exp=c.getValue();
					break;	// 찾았으면 검색 그만
				}
			}
		}

		if(operator != null && operator.equals("=")) {

		}
		else {
			exp += (value==null)?"":value;
			exp += (operator==null)?"":operator;
			exp += (dot==null)?"":dot;
		}

		Cookie expCookie = new Cookie("exp",exp);
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");




	}

}
