package com.qingshu.common.bean;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.base.pojo.JSNews;
import com.qingshu.common.util.DocumentUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.mybatis.dao.MbsCommonDao;

public class NewsJob {
	@Autowired
	public MbsCommonDao mbsCommonDao ;
	public void work() {
		String url = "http://www.iitha.gov.cn/cms/html/main/col25/column_25_1.html";
		String cssQuery = ".listLinkStyle";
		String urlfile = "http://www.zzgy.gov.cn/sub_column_singllist.jsp?urltype=tree.TreeTempUrl&wbtreeid=12030";
		String cssQuery2 = ".f14288";
		String urlfile3="http://www.miit.gov.cn/n11293472/n11293832/n11294042/n12876231/index.html";
		String cssQuery3=".black14_24";
		List<JSNews> newsList=DocumentUtils.getDocumentList(url, cssQuery);
		List<JSNews> filelist=DocumentUtils.getFileList(urlfile, cssQuery2);
		List<JSNews> fileList2=DocumentUtils.getFileList2(urlfile3, cssQuery3);
		if(!ObjectUtils.isEmpty(newsList))
		{
			mbsCommonDao.delete("JSNews.deleteAll");
			mbsCommonDao.batchInsert("JSNews.batchInsert",newsList);
			mbsCommonDao.batchInsert("JSNews.batchInsert",filelist);
			mbsCommonDao.batchInsert("JSNews.batchInsert",fileList2);
		}
		System.out.println("当前时间:" + new Date().toString());
	}
}
