package com.qingshu.common.filters;

import java.util.HashMap;

import org.springframework.ui.Model;

import com.qingshu.common.util.ObjectUtils;

public class CriterionMap extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;

	public Object put(String key, Object value) {
		if(!ObjectUtils.isEmpty(model))
		model.addAttribute(SearchUtil.getField(key), value);
		return super.put(SearchUtil.getField(key), value);
	}

	public CriterionMap(Model model) {
		this.model = model;
	}

	public CriterionMap() {
	}
}
