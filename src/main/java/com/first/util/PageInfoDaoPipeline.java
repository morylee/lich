package com.first.util;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.first.model.PageInfo;
import com.first.service.PageInfoService;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class PageInfoDaoPipeline implements PageModelPipeline<PageInfo> {
	
	@Resource
	private static PageInfoService pageInfoService;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		pageInfoService = (PageInfoService) context.getBean("pageInfoServiceImpl");
	}

	@Override
	public void process(PageInfo pageInfo, Task task) {
		System.out.println("++++++");
		pageInfoService.insertPageInfo(pageInfo);
	}
}
