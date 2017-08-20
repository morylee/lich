package com.first.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://a.qidian.com/")
//@HelpUrl("https://github.com/\\w+")
public class PageInfo {
	@ExtractBy(value = "//div[@class='book-mid-info']/h4/a/text()", notNull = true)
	private String name;
	
	@ExtractBy("//div[@class='book-mid-info']/p[@class='author']/a[@class='name']/text()")
	private String author;
	
	@ExtractBy("//div[@class='book-mid-info']/p[@class='intro']/text()")
	private String readme;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}
	
	public static void main(String[] args) {
		OOSpider.create(Site.me().setSleepTime(300)
                , new ConsolePageModelPipeline(), PageInfo.class)
                .addUrl("https://github.com/code4craft").thread(5).run();
//		PageInfo a = OOSpider.create(Site.me().setSleepTime(300)
//                , new ConsolePageModelPipeline(), PageInfo.class).get("http://a.qidian.com/");
//		System.out.println(a);
	}

}
