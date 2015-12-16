package com.qingshu.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class DataBackup {
	static {
		Properties pro = new Properties();
		try {
			pro.load(DataBackup.class.getClassLoader().getResourceAsStream(
					"backup.properties"));
			//"sh", "-c", mysql
			os = pro.getProperty("os");
			command = "mysqldump -h" + pro.getProperty("hostname")
					+ " -u" + pro.getProperty("username") + " -p"
					+ pro.getProperty("password") + " "
					+ pro.getProperty("database") + " > ";
			dir = pro.getProperty("dir");
			isInit = true;
		} catch (IOException e) {
			e.printStackTrace();
			isInit = false;
		}
	}
	private static boolean isInit;
	private static String command;
	private static String os;
	private static final String OS_WINDOW = "window";
	private static final String OS_LINUX = "linux";
	private static String dir;

	/*
	 * public static void main(String[] args) {
	 * System.out.println(backup("D:")); }
	 */
	public synchronized static void backup(String savepath,Map<String,Object> resultMap) {
		if (!isInit) {
			resultMap.put("isOK", false);
			resultMap.put("errormsg", "配置文件初始化失败！");
			return ;
		}
		String filename = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String com =command+ savepath + "/" + filename + ".sql";
		System.out.println(com);
		Process p  = null;
		String errorMsg = "";
		try {
			if(os.equals(OS_WINDOW)){
				p = Runtime.getRuntime().exec("cmd /c "+com, null, new File(dir));
			}else if(os.equals(OS_LINUX)){
				p = Runtime.getRuntime().exec(new String[]{"sh","-c",com}, null);
			}
			
			String line;
			BufferedReader normalReader = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			BufferedReader errorReader  = new BufferedReader(new InputStreamReader(p.getErrorStream()));  
		    while((line = normalReader.readLine()) != null){  
		    }  
		    while((line = errorReader.readLine()) != null){  
		    	errorMsg += line;
		    }  
		    int result = p.waitFor();
		    boolean isOK = false;
		    if(result == 0){
		    	isOK = true;
		    }else{
		    	resultMap.put("errormsg", errorMsg);
		    }
		    resultMap.put("isOK", isOK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isOK", false);
			resultMap.put("errormsg", "命令执行错误！");
		}finally{
			if(p != null){
				p.destroy();
			}
		}
	}

	public static File[] getBackupFiles(String dir) {
		File file = new File(dir);
		if (file.exists()) {
			return file.listFiles();
		} else {
			file.mkdirs();
			return null;
		}
	}

	public static void delete(String filename) {
		File file = new File(filename);
		if (file.exists())
			file.delete();
	}
}
