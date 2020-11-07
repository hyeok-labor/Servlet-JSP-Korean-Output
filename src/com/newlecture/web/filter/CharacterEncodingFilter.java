package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")	// /*에 해당하는 모든 url에 대해서 필터를 수행
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request
			, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {

		// 필터 실행 전 수행
		System.out.println("before filter");
		request.setCharacterEncoding("UTF-8");
		// 필터 실행
		chain.doFilter(request, response);
		// 필터 실행 후 수행
		System.out.println("after filter");

	}

}
