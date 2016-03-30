package com.mrgan.club.control;

import java.util.HashMap;
import java.util.Map;

import com.mrgan.club.model.SheduleInfo;

public class SheduleControl {
	private Map<String, Map<String, String>> sheduleMap;
	private SheduleInfo info;

	private String sheduleDate = "24-Mar,7-Apr,21-Apr,5-May";
	private String items = "Sharing,TME,Timer,Grammarian,Ah-counter,TTM,Speaker 1,Speaker 2,Speaker 3,Speaker 4,IE 1,IE 2,IE 3,IE 4,GE";;

	public SheduleControl() {
		init();
	}

	private void init() {
		initSheduleInfo();
		initSheduleMap();
	}

	private void initSheduleMap() {
		sheduleMap = new HashMap<String, Map<String, String>>();
		for (String item : info.getItems()) {
			Map<String, String> tmp = new HashMap<String, String>();
			for (String date : info.getSheduleDate()) {
				tmp.put(date, "reserve");
			}
			sheduleMap.put(item, tmp);
		}
	}

	private void initSheduleInfo() {
		info = new SheduleInfo();
		info.setItems(items.split(","));
		info.setSheduleDate(sheduleDate.split(","));
	}

	public void setName(String item, String date, String value) {
		sheduleMap.get(item).put(date, value);
	}

	public void updateSheduleDate(String sheduleDate) {
		info.setSheduleDate(sheduleDate.split(","));
		String[] dates = sheduleDate.split(",");
		for (Map.Entry<String, Map<String, String>> item : sheduleMap
				.entrySet()) {
			Map<String, String> dateMap = item.getValue();
			Map<String, String> tmp = new HashMap();
			for (String date : dates) {
				tmp.put(date,
						dateMap.get(date) == null ? "reserve" : dateMap
								.get(date));
			}
			item.setValue(tmp);
		}
	}

	public Map<String, Map<String, String>> getSheduleMap() {
		return sheduleMap;
	}

	public void setSheduleMap(Map<String, Map<String, String>> sheduleMap) {
		this.sheduleMap = sheduleMap;
	}

	public SheduleInfo getInfo() {
		return info;
	}

	public void setInfo(SheduleInfo info) {
		this.info = info;
	}

	public String getSheduleDate() {
		return sheduleDate;
	}

	public void setSheduleDate(String sheduleDate) {
		this.sheduleDate = sheduleDate;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
