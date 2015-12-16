package com.qingshu.common.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.common.util.DateUtils;
import com.qingshu.core.mybatis.dao.MbsCommonDao;
/**
 * 每月月初生成上个月的销售月报
 * @author ZhengBin
 *
 */
public class GenerateSalesMonthJob {
	@Autowired
	public MbsCommonDao mbsCommonDao ;
	
	public void generateSalesByMonth() {
		/*String lastMonth = DateUtils.getLastMonth(); //上月日期
		List<ShopSaleMonth> lists = mbsCommonDao.selectList("ShopSaleMonth.findByDate", lastMonth);
		if(lists != null && lists.size() > 0) {
			return;
		}
		
		List<ShopSaleMonth> shopSaleMonthList = new ArrayList<ShopSaleMonth>();
		Map<String, Object> pramMap = new HashMap<String, Object>();
		pramMap.put("dateDetail", lastMonth);
		//总销售额 
		List<ShopSaleByMonth> salesByMonthList = mbsCommonDao.selectList("ShopGoodsPrice.salesByMonth", pramMap);
		//监控 40个品种
		List<ShopSaleByMonth> vegeFortyByMonthList = mbsCommonDao.selectList("ShopGoodsPrice.vegeFortyByMonth", pramMap);
		//蔬菜销售额
		List<ShopSaleByMonth> vegeByMonthList = mbsCommonDao.selectList("ShopGoodsPrice.vegeByMonth", pramMap);
		//蔬菜品种
		List<ShopSaleByMonth> vegeNumByMonthList = mbsCommonDao.selectList("ShopGoodsPrice.vegeNumByMonth", pramMap);
		if(salesByMonthList != null && salesByMonthList.size() > 0) {
			for(int i=0; i<salesByMonthList.size(); i++) {
				ShopSaleByMonth month1 = salesByMonthList.get(i);
				ShopSaleByMonth month2 = vegeFortyByMonthList.get(i);
				ShopSaleByMonth month3 = vegeByMonthList.get(i);
				ShopSaleByMonth month4 = vegeNumByMonthList.get(i);
				
				ShopSaleMonth shopSaleByMonth = new ShopSaleMonth();  
				//统计月份
				shopSaleByMonth.setSaleMonth(lastMonth);
				//企业
				shopSaleByMonth.setEnterprise(month1.getEnterprise());
				//销售总额
				shopSaleByMonth.setSalesTotal(month1.getSalesTotal()); 
				//蔬菜销售总额
				shopSaleByMonth.setSalesVege(month3.getSalesVege()); 
				//蔬菜销售额占销售总额比例 = 蔬菜销售总额/销售总额
				shopSaleByMonth.setRateVege(getRateFormat(month3.getSalesVege(), month1.getSalesTotal()));  
				//40个监测品种销售额
				shopSaleByMonth.setSalesVegeForty(month2.getSalesVegeForty());  
				//40个监测品种销售额占蔬菜销售额比例
				shopSaleByMonth.setRateVegeForty(getRateFormat(month2.getSalesVegeForty(), month1.getSalesTotal()));  
				//蔬菜品种
				shopSaleByMonth.setVegeNum(month4.getVegeNum());  
				//蔬菜销售数量
				shopSaleByMonth.setSaleVolumeVege(getWeight(month3.getSaleVolumeVege())); 
				//40个监测品种销售数量
				shopSaleByMonth.setSaleVolumeVegeForty(getWeight(month2.getSaleVolumeVegeForty()));   
				
				double marketAvgPrice = 0; // 市场均价
				double saleAvgPrice = 0; // 销售均价
				List<Goods> goodsList = mbsCommonDao.selectList("Goods.queryAll");
				if(goodsList != null && goodsList.size() > 0) {
					for(Goods goods : goodsList) {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("dateDetail", lastMonth);
						param.put("goodsId", goods.getId());
						//获取单品市场均价
						ShopGoodsPrice shopGoodsPrice1 = mbsCommonDao.selectOne("ShopGoodsPrice.getMarketPriceByGoodsId", param);
						//获取单品销售均价和销售额
						ShopGoodsPrice shopGoodsPrice2 = mbsCommonDao.selectOne("ShopGoodsPrice.getSalePriceByGoodsId", param);
						
						double marketPrice = shopGoodsPrice1.getPrice();
						double salePrice = shopGoodsPrice2.getPrice();
						double factSales = shopGoodsPrice2.getFactSales();
						//市场均价= 各个单品市场均价*权   的 和     权=单品销售额/所有单品销售额
						saleAvgPrice = saleAvgPrice + divide(salePrice * factSales , month2.getSalesVegeForty());
						//销售均价= 各个单品销售均价*权   的 和     权=单品销售额/所有单品销售额
						marketAvgPrice = marketAvgPrice + divide(marketPrice * factSales, month2.getSalesVegeForty());
					}
					
					double rateFavorable = divide(marketAvgPrice - saleAvgPrice, marketAvgPrice);
					double surrenderProfits = (marketAvgPrice - saleAvgPrice) * month2.getSaleVolumeVegeForty();
					//40个监测品种平均优惠幅度 =（市场均价-销售均价）/市场均价
					shopSaleByMonth.setRateFavorable(getRateFormat(rateFavorable));  
					//让利金额 =（市场均价-销售均价）*40个品种销售总数量
					shopSaleByMonth.setSurrenderProfits(surrenderProfits);
				}
				shopSaleMonthList.add(shopSaleByMonth);
			}
		}
		
		if(shopSaleMonthList != null && salesByMonthList.size() > 0) {
			mbsCommonDao.batchInsert("ShopSaleMonth.batchInsert", shopSaleMonthList);
		}*/
	}
	
	/**
	 * 获取利率,（格式化为%）
	 * @param small
	 * @param big
	 * @return
	 */
	private String getRateFormat(double small, double big) {
		if(big == 0) {
			return "--";
		}
		return new DecimalFormat("#.##%").format(small / big);
	}
	/**
	 * 两个double类型的数值相除
	 * @param value1
	 * @param value2
	 * @return
	 */
	private double divide(double value1, double value2) {
		if(value2 == 0 ) {
			return 0.0;
		}
		return value1 / value2;
	}
	
	/**
	 * 格式化为%
	 * @param small
	 * @param big
	 * @return
	 */
	private String getRateFormat(double value) {
		return new DecimalFormat("#.##%").format(value);
	}
	
	/**
	 * 公斤转换为万公斤
	 */
	private double getWeight(double weight) {
		return new BigDecimal(weight).divide(new BigDecimal(10000))
		   .setScale(3, BigDecimal.ROUND_HALF_UP)
		   .doubleValue();
	}
}
