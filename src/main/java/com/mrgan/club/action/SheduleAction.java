package com.mrgan.club.action;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mrgan.club.control.SheduleControl;
import com.mrgan.club.model.ResponseResult;

@Controller
public class SheduleAction {
	Logger logger = Logger.getLogger(SheduleAction.class);

	@Autowired
	private SheduleControl sheduleControl;

	@RequestMapping("/init")
	@ResponseBody
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("index");
		mav.getModel().put("items", sheduleControl.getInfo().getItems());
		mav.getModel().put("dates", sheduleControl.getInfo().getSheduleDate());
		mav.getModel().put("shedules", sheduleControl.getSheduleMap());
		return mav;
	}

	@RequestMapping("/set")
	@ResponseBody
	public ResponseResult set(HttpServletRequest request,
			HttpServletResponse response) {
		String key = request.getParameter("name");
		String value = request.getParameter("value");
		String pk = request.getParameter("pk");
		logger.info(key + " " + value + " " + pk);
		sheduleControl.setName(pk, key, value);
		return new ResponseResult("ok", "ok");
	}

	@RequestMapping("/setDate")
	public ModelAndView setDate(HttpServletRequest request,
			HttpServletResponse response) {
		String dates = request.getParameter("dates");
		sheduleControl.updateSheduleDate(dates);
		return new ModelAndView("redirect:/init.go");
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(System.out);
		oos.writeObject(sheduleControl.getSheduleMap());
		oos.flush();
		oos.close();
		return new ModelAndView("redirect:/init.go");
	}

	@RequestMapping("/rollback")
	public ModelAndView rollback(HttpServletRequest request,
			HttpServletResponse response) {
		String dates = request.getParameter("dates");
		sheduleControl.updateSheduleDate(dates);
		return new ModelAndView("redirect:/init.go");
	}
}
