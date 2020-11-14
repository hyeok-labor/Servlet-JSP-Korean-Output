package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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
			ScriptEngine engine = new ScriptEngineManager().getEngineByName(operator);
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(operator != null && operator.equals("C")) {
			exp ="";
		}
		else {
			exp += (value==null)?"":value;
			exp += (operator==null)?"":operator;
			exp += (dot==null)?"":dot;
		}

		Cookie expCookie = new Cookie("exp",exp);
		if(operator !=null && operator.equals("C"))
			expCookie.setMaxAge(0);

		// path 설정은 하나만 가능하다.
		expCookie.setPath("/");
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");

	}

}
