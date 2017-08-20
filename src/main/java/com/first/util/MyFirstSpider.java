package com.first.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.first.model.PageInfo;
import com.first.service.UserService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyFirstSpider implements PageProcessor {
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

	@Override
	public Site getSite() {
		return this.site;
	}

	@Override
	public void process(Page page) {
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
		page.putField("site", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//span[@class='pinned-repo-item-content']/span/a/span/text()").toString());
		if(page.getResultItems().get("name") == null){
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
	}

	public static void main(String[] args) {
//		Spider.create(new MyFirstSpider())
//			.addUrl("https://github.com/code4craft")
//			.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
//			.thread(5)
//			.run();
//		@SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
//				,"classpath:conf/spring-mybatis.xml"});
//		userService = (UserService) context.getBean("userServiceImpl");
		
		OOSpider.create(Site.me().setSleepTime(1000), new PageInfoDaoPipeline(), PageInfo.class)
		.addUrl("http://a.qidian.com/").thread(5).run();
	}
}
