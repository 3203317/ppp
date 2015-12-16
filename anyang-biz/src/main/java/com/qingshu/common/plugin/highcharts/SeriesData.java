package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SeriesData {
	private String name;
	private Object y;//平均价格
	private Boolean sliced;
	private Boolean selected;
	private Double tavgp;// 同比平均价格
	private Double havgp;// 环比平均价格
	private Double factSales;
	private Double amount;
	public Double getTavgp() {
		return tavgp;
	}
	public void setTavgp(Double tavgp) {
		this.tavgp = tavgp;
	}
	public Double getHavgp() {
		return havgp;
	}
	public void setHavgp(Double havgp) {
		this.havgp = havgp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getY() {
		return y;
	}
	public void setY(Object y) {
		this.y = y;
	}
	public Boolean getSliced() {
		return sliced;
	}
	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	public Double getFactSales() {
		return factSales;
	}
	public void setFactSales(Double factSales) {
		this.factSales = factSales;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getYStr(){
		 if(y == null){
			 return null;
		 }
		 return String.format("%.2f", y);
			 
	}
	public String getHavgpStr(){
		if(havgp == null){
			return "--";
		}
		return String.format("%.2f",havgp);
	}
	public String getTavgpStr(){
		if(tavgp == null){
			return "--";
		}
		return String.format("%.2f",tavgp);
	}
	public String getTrise(){
		if(tavgp == null){
			return "--";
		}
		return String.format("%.2f", Double.parseDouble(y.toString())-tavgp);
	}
	public String getTradio(){
		if(tavgp == null){
			return "--";
		}
		double radio = (Double.parseDouble(y.toString())-tavgp)*100/tavgp;
		return String.format("%.2f", radio);
	}
	public String getHrise(){
		if(havgp == null){
			return "--";
		}
		return String.format("%.2f", Double.parseDouble(y.toString())-havgp);
	}
	public String getHradio(){
		if(havgp == null){
			return "--";
		}
		double radio = 0.00;
		if((Double.parseDouble(y.toString())-havgp) != 0.00d)
			radio = (Double.parseDouble(y.toString())-havgp)*100/havgp;
		return String.format("%.2f", radio);
	}
	public String getHcolor(){
		if( y== null || havgp == null){
			return "";
		}
		double  rise = Double.parseDouble(y.toString())-havgp;
		if(rise > 0){
			return "red";
		}else if(rise < 0){
			return "green";
		}else {
			return "";
		}
	}
	public String getTcolor(){
		if( y== null || tavgp == null){
			return "";
		}
		double  rise = Double.parseDouble(y.toString())-tavgp;
		if(rise > 0){
			return "red";
		}else if(rise < 0){
			return "green";
		}else {
			return "";
		}
	}
}
