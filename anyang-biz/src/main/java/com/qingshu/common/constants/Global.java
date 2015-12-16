package com.qingshu.common.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 *全局变量定义
 */
public class Global {
	/* 用户屏幕锁定状态*/
	public static Short JSUSer_Lock=0;
	public static Short JSUSer_Unlock=1;
	/* 菜单级别定义 */
	public static Short FirstMenu=0;
	public static Short SecondMenu=1;
	/* 是否允许删除 */
	public static Short Delete_Allow=1;
	public static Short Delete_NoAllow=0;
	/* 权限拥有者类型 */
	public static String JSRole = "JSRole";
	public static String JSUser = "JSUser";
	/* 企业用户类型 */
	public static String JXCompany = "JXCompany";
	public static String JGCompany = "JGCompany";
	/* 是否重点企业 */
	public static Short isScale_0 = 0;
	public static Short isScale_1 = 1;
	/*全局用户划分*/
	public static Short JSUser_1=1;//局
	public static Short JSUser_2=2;//办事处
	public static Short JSUser_3=3;//工业企业
	public static Short JSUser_4=4;//信息企业
	public static Short JSUser_5=5;//工业、信息企业
	/* 权限类型 */
	public static String JSMenu = "JSMenu";
	/*机构等级划分*/
	public static Short JSOrgain_1=1;
	public static Short JSOrgain_2=2;
	public static Short JSOrgain_3=3;
	public static Short JSOrgain_5=5;
	/*消息接受者类型*/
	public static String JSMessage_JSUser="JSUser";
	public static String JSMessage_JSRole="JSRole";
	public static String JSMessage_JSOrganization="JSOrganization";
	
	/*消息类型*/
	public static Short JSMessage_Public=0;/*群发消息*/
	public static Short JSMessage_System=1;/*系统消息*/
	public static Short JSMessage_Private=2;/*个人消息*/
	/*消息状态*/
	public static Short JSMessage_New=0;/*新建*/
	public static Short JSMessage_Read=1;/*已读*/
	public static Short JSMessage_Garbage=2;/*在垃圾箱中*/
	public static Short JSMessage_Delete=3;/*已删除*/

	/*文件法规*/
	public static Short JSNews_1=0;
	public static Short JSNews_2=1;
	public static Short JSNews_3=2;
	
	/*指标上报状态*/
	public static Short JSReport_1=1;
	public static Short JSReport_3=3;//工信局审核
	public static Short JSReport_2=2;//办事处审核
	
	/*指标类型*/
	public static Short JSIndex_1=1;/*工业总产值*/
	public static Short JSIndex_2=2;/*工业销售产值*/
	public static Short JSIndex_3=3;/*工业增加值*/
	public static Short JSIndex_4=4;/*产销率*/
	public static Short JSIndex_5=5;/*工业产品产量*/
	public static Short JSIndex_6=6;/*新产品产值*/
	public static Short JSIndex_7=7;/*出口交货值*/
	public static Short JSIndex_8=8;/*工业用电量*/
	public static Short JSIndex_9=9;/*耗煤量*/
	public static Short JSIndex_10=10;/*用水量*/
	public static Short JSIndex_11=11;/*主营业务收入*/
	public static Short JSIndex_12=12;/*利润总额*/
	public static Short JSIndex_13=13;/*税金*/
	public static Short JSIndex_14=14;/*利税*/
	public static Short JSIndex_15=15;/*应收帐款*/
	public static Short JSIndex_16=16;/*产成品*/
	public static Short JSIndex_17=17;/*两项资金占用*/
	//企业问题类型
	public final static List<String> QUESTION_TYPE=new ArrayList<String>(Arrays.asList("融资","要素保障(煤电油气运)","土地","环评","项目审批","市场开拓 ","企业减负","周边环境","其他"));
}
