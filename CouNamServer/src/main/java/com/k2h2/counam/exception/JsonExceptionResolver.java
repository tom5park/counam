package com.k2h2.counam.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.k2h2.counam.entity.User;

public class JsonExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		response.setStatus(500);
		response.setContentType("application/json; charset=UTF-8");
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", ex.getMessage());
			om.writeValue(response.getWriter(), map);
		} catch (Throwable t) {
			//ExceptionHandler.error(t);
		}
		
		System.out.println(String.format("__[O40]__: %s", 1));
		return null;
	}

}
