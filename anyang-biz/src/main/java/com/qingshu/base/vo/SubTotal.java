package com.qingshu.base.vo;
/**
 * 分类汇总查询结果集
 */
public class SubTotal {
	private String id;
	
	private String subId;//Id
	private String name;//企业名称
	private String organizationName;//区域名称
	private String year;//年度
	private String month;//月份
	private String industryName;//行业
	private Double outputValue;//总产值
	private Double salesRevenue;//销售收入
	private Double industrialAddValue;//工业增加值
	private Double salesOutputRatio;//产销率
	private Double outputProducts;//产品产量
	private Double newOutputValue;//新产品产值
	private Double valueOfDelivery;//出口交货值
	private Double eleConsumption;//工业用电量
	private Double coalConsumption;//耗煤量
	private Double waterConsumption;//用水量
	private Double mainSales;//主营业务收入
	private Double totalProfit;//利润总额
	private Double totalRevenue;//税金
	private Double totalProfitRevenue;//利税
	private Double receivables;//两项资金占用1：应收账款
	private Double finishedProduct;//两项资金占用2：产成品
	private Double occupyingFunds;//两项资金占用
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public Double getOutputValue() {
		return outputValue;
	}
	public void setOutputValue(Double outputValue) {
		this.outputValue = outputValue;
	}
	public Double getSalesRevenue() {
		return salesRevenue;
	}
	public void setSalesRevenue(Double salesRevenue) {
		this.salesRevenue = salesRevenue;
	}
	public Double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
	public Double getIndustrialAddValue() {
		return industrialAddValue;
	}
	public void setIndustrialAddValue(Double industrialAddValue) {
		this.industrialAddValue = industrialAddValue;
	}
	public Double getOccupyingFunds() {
		return occupyingFunds;
	}
	public void setOccupyingFunds(Double occupyingFunds) {
		this.occupyingFunds = occupyingFunds;
	}
	public Double getEleConsumption() {
		return eleConsumption;
	}
	public void setEleConsumption(Double eleConsumption) {
		this.eleConsumption = eleConsumption;
	}
	public Double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public Double getSalesOutputRatio() {
		return salesOutputRatio;
	}
	public void setSalesOutputRatio(Double salesOutputRatio) {
		this.salesOutputRatio = salesOutputRatio;
	}
	public Double getOutputProducts() {
		return outputProducts;
	}
	public void setOutputProducts(Double outputProducts) {
		this.outputProducts = outputProducts;
	}
	public Double getNewOutputValue() {
		return newOutputValue;
	}
	public void setNewOutputValue(Double newOutputValue) {
		this.newOutputValue = newOutputValue;
	}
	public Double getValueOfDelivery() {
		return valueOfDelivery;
	}
	public void setValueOfDelivery(Double valueOfDelivery) {
		this.valueOfDelivery = valueOfDelivery;
	}
	public Double getCoalConsumption() {
		return coalConsumption;
	}
	public void setCoalConsumption(Double coalConsumption) {
		this.coalConsumption = coalConsumption;
	}
	public Double getWaterConsumption() {
		return waterConsumption;
	}
	public void setWaterConsumption(Double waterConsumption) {
		this.waterConsumption = waterConsumption;
	}
	public Double getMainSales() {
		return mainSales;
	}
	public void setMainSales(Double mainSales) {
		this.mainSales = mainSales;
	}
	public Double getTotalProfitRevenue() {
		return totalProfitRevenue;
	}
	public void setTotalProfitRevenue(Double totalProfitRevenue) {
		this.totalProfitRevenue = totalProfitRevenue;
	}
	public Double getReceivables() {
		return receivables;
	}
	public void setReceivables(Double receivables) {
		this.receivables = receivables;
	}
	public Double getFinishedProduct() {
		return finishedProduct;
	}
	public void setFinishedProduct(Double finishedProduct) {
		this.finishedProduct = finishedProduct;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
