package com.qingshu.common.util;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qingshu.base.pojo.JSNews;

public class DocumentUtils {
	@SuppressWarnings("unchecked")
	public static <T> List<T> getDocumentList(String url, String cssQuery) {
		List<Object> newsList = ObjectUtils.getArrayList();
		Short newsType=0;
		int j = 0;
		try {
			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select(cssQuery);
			for (Element item : items) {
				JSNews news = new JSNews();
				++j;
				Elements links = item.select("a");
				Elements dates = item.parent().select("em").select("span");
				String link = links.attr("href");
				String linkText = links.text();
				String date = dates.text();
				if (j < 6) {
					news.setTitle(linkText);
					news.setHref(link);
					news.setType(newsType);
					news.setCreateDate(DateUtils.dstrToDate(date));
					newsList.add(news);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<T>) newsList;
	}
	@SuppressWarnings("unchecked")
	public static <T> List<T> getFileList(String url, String cssQuery) {
		List<Object> newsList = ObjectUtils.getArrayList();
		Short fileType=1;
		int j = 0;
		try {
			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select(cssQuery);
			Elements items2 = doc.select(".timestyle729247351_14288");
			for (Element item : items) {
				JSNews news = new JSNews();
				Element dates = items2.get(j);
				++j;
				Elements links = item.select("a");
				String link = links.attr("href");
				String linkText = links.text();
				if (j < 6) {
					news.setTitle(linkText);
					news.setHref(link);
					news.setType(fileType);
					news.setCreateDate(DateUtils.dstrToDate(dates.text()));
					newsList.add(news);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<T>) newsList;
	}
	@SuppressWarnings("unchecked")
	public static <T> List<T> getFileList2(String url, String cssQuery) {
		List<Object> newsList = ObjectUtils.getArrayList();
		Short fileType=2;
		int j = 0;
		try {
			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select(cssQuery);
			
			for (Element item : items) {
				JSNews news = new JSNews();
				j++;
				Elements links = item.getElementsByTag("td");
				String link = links.get(0).children().attr("href");
				String linkText = links.get(0).children().text();
				String date=DateUtils.getYearNow()+"-"+links.get(1).text();
				if (j < 6) {
					news.setTitle(linkText);
					news.setHref(link);
					news.setType(fileType);
					news.setCreateDate(DateUtils.dstrToDate(date));
					newsList.add(news);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<T>) newsList;
	}
	public static void main(String[] args) {
		getFileList2("http://www.miit.gov.cn/n11293472/n11293832/n11294042/n12876231/index.html",".black14_24");
		
	}
}
