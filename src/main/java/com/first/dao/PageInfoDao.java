package com.first.dao;

import com.first.model.PageInfo;

public interface PageInfoDao {
	/**
	 * 插入网页信息
	 * @param pageInfo
	 * @return
	 */
	public int insertPageInfo(PageInfo pageInfo);
}
