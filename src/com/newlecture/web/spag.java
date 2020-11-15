package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class spag extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num=Integer.parseInt(num_);

		String result;

		if(num%2 == 0){
			result = "짝수";
		}
		else{
			result = "홀수";
		}

		request.setAttribute("result",result);

		String[] names = {"newlec","dragon"};
		request.setAttribute("names",names);

		HashMap<String, Object> notice = new HashMap<String,Object>();
		notice.put("id", 1);
		notice.put("title", "EL Good");
		request.setAttribute("notice",notice);

		// redirect : 현재 작업하던 것과는 전혀 상관없이 디렉팅
		// forward : 현재 작업하던 것과 관련있는 것들을 이어가기 위해서 사용
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}