package com.mrgan.club.action;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.runner.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrgan.club.model.ResponseResult;

@Controller
@RequestMapping("/test")
public class TestAction {
	Logger logger = Logger.getLogger(TestAction.class);

	@RequestMapping("/get")
	@ResponseBody
	public ResponseResult get(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = request.getParameterMap();
		for (Map.Entry entry : (Set<Map.Entry>) map.entrySet()) {
			logger.info(entry.getKey() + " : " + entry.getValue());
			String[] arr = (String[]) entry.getValue();
			for (String string : arr) {
				logger.info(string);
			}
		}
		return new ResponseResult("error", "field cannot be empty!");
		// return "{status : 'error', msg : 'field cannot be empty!'}";
	}

}
