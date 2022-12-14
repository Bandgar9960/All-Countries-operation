package com.neo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.entity.UrlUtil;
import com.neo.repository.UrlRespository;

@Service
public class UrlUtilityService {

	@Autowired
	private UrlRespository urlRespository;

	public UrlUtil saveUrl(UrlUtil urlUtil) {
		UrlUtil util = urlRespository.save(urlUtil);
		return util;

	}

	public UrlUtil getUrl(String name) {
		UrlUtil urlName = urlRespository.findByName(name);
		return urlName;

	}

}
