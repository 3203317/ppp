package com.qingshu.common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.common.util.DateUtils;
import com.qingshu.core.mybatis.dao.MbsCommonDao;
/**
 * 定时检测{昨日}销售数据的状态，如果没有确认且没有{昨日}没有预警信息，则自动确认，即状态更新为“已确认”
 * @author ZhengBin
 *
 */
public class UpdateSalesStateJob {
	@Autowired
	public MbsCommonDao mbsCommonDao ;
	public void work() {
		/*String nowDateStr = DateUtils.getYesterday(); //昨天
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dateDetail", nowDateStr);
		List<Warning> lists = mbsCommonDao.selectList("Warning.queryAllByDate", paramMap);
		if(lists != null && lists.size() > 0) { // 有预警信息
			//暂不做处理
			
		} else { // 无预警信息，更新数据状态
			mbsCommonDao.update("ShopGoodsPrice.updateStateByDate", paramMap);
		}*/
	}
}
