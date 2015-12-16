package com.qingshu.base.mybatis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.DataBackup;
import com.qingshu.common.util.RequestUtil;
import com.qingshu.common.util.SessionInfo;

@Controller
@RequestMapping("/backup")
public class DataBackupController {
	@RequestMapping(value="/backup.do")
	@ResponseBody
	public String backup(HttpServletRequest request,ModelMap map){
		DataBackup.backup(RequestUtil.getRealPath(SessionInfo.DATA_BACKUP),map);
		Boolean isOK = (Boolean) map.get("isOK");
		if(isOK){
			return "{\"isOK\":true,\"icon\":\"success.gif\",\"msg\":\"备份成功！\"}";
		}else{
			return "{\"isOK\":false,\"icon\":\"error.gif\",\"msg\":\"备份失败！"+map.get("errormsg")+"\"}";
		}
	}
	
	@RequestMapping(value="/list.do")
	public String list(ModelMap map,PagerInfo pager){
		File[] files=DataBackup.getBackupFiles(RequestUtil.getRealPath(SessionInfo.DATA_BACKUP));
		List<DataBackupFile> list = null;
		List<DataBackupFile> pagerList = null;
		if(files != null){
			int pagesize = pager.getPageSize();
			pager.setActionName("list.do");
			pager.setTotalResult(files.length);
			pager.setTotalPage(files.length%pagesize>0?files.length/pagesize+1:files.length/pagesize);
			pager.setPageSize(pagesize);
			if(pager.getCurPageNO()==0){
				pager.setCurPageNO(1);
			}
			list=new ArrayList<DataBackupFile>(files.length);
			for(File file:files){
				list.add(new DataBackupFile(file));
			}
			Collections.sort(list, new Comparator<DataBackupFile>() {
				@Override
				public int compare(DataBackupFile o1, DataBackupFile o2) {
					return (int) (o2.order-o1.order);
				}
			});
			int start = pager.getStart()-1;
			int end = pager.getStart()+pagesize-1;
			pagerList = list.subList(start, end>list.size()?list.size():end);
		}
		map.put("list", pagerList);
		map.put("start_num", pager.getStart());
		map.put("pager",pager.getToolBar2());
		return "/backup/list";
	}
	
	@RequestMapping(value="/download.do")
	public void download(String name,HttpServletResponse response) throws UnsupportedEncodingException{
		String filename=RequestUtil.getRealPath(SessionInfo.DATA_BACKUP+"/"+name);
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));  
		InputStream in = null;  
	    OutputStream out = null;  
	          	         
        try {  
            in = new FileInputStream(filename);  
            int len = 0;  
             byte[] buffer = new byte[1024];  
             out = response.getOutputStream();  
            while((len = in.read(buffer)) > 0) {  
                out.write(buffer,0,len);  
            }  
              
        }catch(Exception e) {  
            throw new RuntimeException(e);  
        }finally {  
            if(in != null) {  
                try {  
                    in.close();  
                }catch(Exception e) {  
                    throw new RuntimeException(e);  
                }  
                  
            }  
        }  
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(String name,ModelMap model){
		DataBackup.delete(RequestUtil.getRealPath(SessionInfo.DATA_BACKUP+"/"+name));
		return list(model,new PagerInfo());
	}
	
	public class DataBackupFile{
		private long order;
		private File file;
		private String name;
		private String fpath;
		private String fsize;
		private String fdate;
		
		public DataBackupFile(File file){
			this.order = file.lastModified();
			this.file=file;
			this.name=file.getName();
			this.fpath=file.getAbsolutePath();
			this.fdate=new SimpleDateFormat("yyyy-MM-dd HH:mm").
				format(new Date(this.order));
		}
		
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getFpath() {
			return fpath;
		}
		public void setFpath(String fpath) {
			this.fpath = fpath;
		}
		public String getFsize() {
			DecimalFormat df=new DecimalFormat("#.##");
			double fsize=file.length();
			if(fsize<1024)
				return df.format(fsize)+"B";
			else if(fsize<1024*1024)
				return df.format(fsize/1024)+"KB";
			else if(fsize<1024*1024*1024)
				return df.format(fsize/(1024*1024))+"MB";
			else
				return df.format(fsize/(1024*1024*1024))+"GB";
		}
		public void setFsize(String fsize) {
			this.fsize = fsize;
		}
		public String getFdate() {
			return fdate;
		}

		public void setFdate(String fdate) {
			this.fdate = fdate;
		}
	}
}
