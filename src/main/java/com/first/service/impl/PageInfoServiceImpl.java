package com.first.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.dao.PageInfoDao;
import com.first.model.PageInfo;
import com.first.service.PageInfoService;

@Service
public class PageInfoServiceImpl implements PageInfoService {
	@Autowired
	PageInfoDao pageInfoDao;

	@Override
	public int insertPageInfo(PageInfo pageInfo) {
		return pageInfoDao.insertPageInfo(pageInfo);
	}
}
