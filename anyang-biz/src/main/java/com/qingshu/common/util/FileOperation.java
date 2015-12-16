package com.qingshu.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 公共上传方法
 */
public class FileOperation {
	/**
	 * 
	 * @param request  MultipartHttpServletRequest 实例
	 * @param name   定义的文件名
	 * @param uploadPath   要上传的路径
	 * @return
	 * 调用例子String path= super.uploadFile(mrequest, "file", "/upload/notice/");
	 */
	public static String uploadFile(MultipartHttpServletRequest request,String name,String uploadPath){
		 MultipartFile file= request.getFile(name);
		 String fileRealName = "";
		 if (!file.isEmpty()) {
			 try {
				 String fileName=file.getOriginalFilename();
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				 String datename = sdf.format(new Date());
				 uploadPath += datename + "/";
				 
				 File filePath = new File(request.getSession().getServletContext().getRealPath(uploadPath));   
				 //判读存储文件路是否存在，不存在则创建  
				 if (! filePath.exists()) {
					 filePath.mkdirs();   
					 //System.out.println("上传文件路径不存在，创建成功！");
				 }
				 String fileEx = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				 fileRealName = String.valueOf(new Date().getTime()) + fileEx;
				 //文件开始上传到服务器上                     
				 file.transferTo(new File(filePath.getAbsolutePath()+"/"+fileRealName));
			 } catch (IOException e) {
				 e.printStackTrace();   
			 }  
			 return uploadPath+fileRealName;
		 }else{
			 return "";
		 }
		 
	   } 
	
	/**
	 * 
	 * @param request  MultipartHttpServletRequest 实例  --多个文件上传
	 * @param name   定义form的文件名
	 * @param uploadPath   要上传的路径
	 * 调用例子String path= super.uploadFile(mrequest, "file", "/upload/notice/");
	 */
	public static String uploadFile1(MultipartHttpServletRequest request,String name,String uploadPath){
		 List<MultipartFile> files = request.getFiles(name);
		 String fileRealName = "";
		 String path = "";
		 String xiang=request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
		 for(int i=0;i<files.size();i++){
			 MultipartFile file=files.get(i);
			 if (!file.isEmpty()) {
				 try {
					 String fileName=file.getOriginalFilename();
					 if (i==0){
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						 String datename = sdf.format(new Date());
						 uploadPath += datename + "/";//uploadPath是/upload/projectDocument/20130703/
					 }
					 
					 File filePath = new File(xiang+uploadPath);
					 //判读存储文件路是否存在，不存在则创建  
					 if (! filePath.exists()) {
						 filePath.mkdirs();   
						 //System.out.println("上传文件路径不存在，创建成功！");
					 }
					 String fileEx = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					 fileRealName = String.valueOf(new Date().getTime())+i + fileEx;//时间+文件格式
					 //文件开始上传到服务器上                     
					 file.transferTo(new File(filePath.getAbsolutePath()+"/"+fileRealName));
					 
					 path+=";"+ uploadPath+fileRealName+"#"+fileName;
				 } catch (IOException e) {
					 e.printStackTrace();
				 }  
			 }
		 }
		 if(!path.trim().equals("")){
			 path=path.substring(1);
		 }
		return path;
		 
	   } 
	/**
	 * 下载
	 * @param fileName   路径 "/upload/notice/"
	 * @param response
	 * 在设置Content-Disposition头字段之前，一定要设置Content-Type头字段
	 */
	public static void downloadFile(String fileName,HttpServletResponse response){  
		    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		    try {  
		    response.setCharacterEncoding("utf-8");  
            response.setContentType("multipart/form-data"); //setContentType让浏览器知道下载的是什么文件，multipart/form-data会自动判断下载文件类 
           // response.setHeader("Content-Disposition", "attachment;fileName="+fileName); //向当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
            response.setHeader("Content-Disposition","attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
	        	 //xiang：E:/myspace/.metadata/.me_tcat/webapps/gsme/   fileName：/upload/tfile/20130518/1368864808203.jpg
	        	    String xiang=request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
		            File file=new File(xiang+fileName);  
		            InputStream inputStream=new FileInputStream(file);  
		            OutputStream os=response.getOutputStream();  
		            byte[] b=new byte[1024];  
		            int length;  
		            while((length=inputStream.read(b))>0){  
		                os.write(b,0,length);  
		            }  
		            inputStream.close();  
		        } catch (Exception e) {  
		           e.printStackTrace();
		        }
		    }
	
	/**
	 * 文件下载
	 * @param filePath 附件路径+上传后的文件名，例：/upload/projectDocument/20131115/13844800504050.docx
	 * @param fileName 附件上传时名称【可以有中文】
	 */
	public static void downloadFile2(String filePath,String fileName,HttpServletResponse response){
	    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    try {
	    	response.setCharacterEncoding("utf-8");  
	    	response.setContentType("multipart/form-data"); //setContentType让浏览器知道下载的是什么文件，multipart/form-data会自动判断下载文件类 
	    	// response.setHeader("Content-Disposition", "attachment;fileName="+fileName); //向当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
	    	response.setHeader("Content-Disposition","attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
	    	//xiang：E:/myspace/.metadata/.me_tcat/webapps/gsme/   fileName：/upload/tfile/20130518/1368864808203.jpg
	    	String xiang=request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
	    	File file=new File(xiang+filePath);  
	    	InputStream inputStream=new FileInputStream(file);  
	    	OutputStream os=response.getOutputStream();  
	    	byte[] b=new byte[1024];  
	    	int length;  
	    	while((length=inputStream.read(b))>0){  
	    		os.write(b,0,length);  
	    	}  
	    	inputStream.close();  
	    } catch (Exception e) {  
	    	e.printStackTrace();
	    }
	}
	    	
	/**
	 * 删除文件
	 * @param uploadPath 文件路径
	 */
	public static void deleteFile(String uploadPath){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		////xiang：E:/myspace/.metadata/.me_tcat/webapps/gsme/   uploadPath：/upload/tfile/20130518/1368864808203.jpg
		String path=request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
		File file = new File(path+uploadPath);
		//判读文件是否存在，存在则删除
		if (file.exists()) {
			file.delete();   
		}
	}
		
					 


}
