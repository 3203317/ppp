package com.qingshu.common.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.core.mybatis.dao.MbsCommonDao;

/**
 * 生成一年的详细时间（年、月、日、周、季），用于曲线的查询
 * @author ZhengBin
 *
 */
public class GenerateDate {
	@Autowired
	public MbsCommonDao mbsCommonDao ;
	
	/**
	 * 生成一年的详细时间
	 */
	public void generateDate() {

		/*List<DateDetail> dateLists = new ArrayList<DateDetail>();
		
		Calendar calendar = Calendar.getInstance();
		int nowYear = calendar.get(Calendar.YEAR);
		calendar.set(nowYear, 0, 1);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		while(calendar.get(Calendar.YEAR) == nowYear) {
			String month = dateStr.substring(5, 7);
			String quarter = "";
			if(month.equals("01") || month.equals("02") || month.equals("03")) {
				quarter = "1";
			} else if(month.equals("04") || month.equals("05") || month.equals("06")) {
				quarter = "2";
			} else if(month.equals("07") || month.equals("08") || month.equals("09")) {
				quarter = "3";
			} else if(month.equals("10") || month.equals("11") || month.equals("12")) {
				quarter = "4";
			}
			
			DateDetail dateDetail = new DateDetail();
			dateDetail.setYear(dateStr.substring(0, 4));
			dateDetail.setMonth(month);
			dateDetail.setDay(dateStr.substring(8, 10));
			dateDetail.setWeek(calendar.get(Calendar.WEEK_OF_YEAR) + "");  //周
			dateDetail.setQuarter(quarter);
			dateDetail.setDatestr(dateStr);
			
			dateLists.add(dateDetail);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		}
		
		mbsCommonDao.batchInsert("DateDetail.batchInsert", dateLists);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("year", nowYear);
		mbsCommonDao.update("DateDetail.updateWeek", paramMap);*/
	}

}
