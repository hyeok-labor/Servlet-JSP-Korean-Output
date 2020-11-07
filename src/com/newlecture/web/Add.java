package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");					// encoding 을 어떻게 할 것인지
		response.setContentType("text/html; charset=UTF-8");	// UTF-8 형식으로 읽으라고 요청하는것


		String numX = request.getParameter("x");
		String numY = request.getParameter("y");

		int x=0,y=0;
		int result=0;
		// post요청을 받은 x와 y의 값이 null 또는 공백이 아닐 때
		// Integer로 형 변환 후 계산
		if(numX!=null && !numX.equals(""))
			x = Integer.parseInt(numX);
		if(numY!=null && !numY.equals(""))
			y = Integer.parseInt(numY);

		result = x+y;

		response.getWriter().printf("result is %d\n",result);


	}

}
