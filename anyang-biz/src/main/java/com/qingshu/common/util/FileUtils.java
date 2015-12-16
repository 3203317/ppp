package com.qingshu.common.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qingshu.common.pager.PagerInfo;

/**
 * @description:(文件操作辅助类)
 * @author：flyme
 * @data：2013-8-26 下午01:47:58
 * @version：v 1.0
 */
public class FileUtils {
	/**
	 * 遍历文件夹中文件
	 * 
	 * @param filepath
	 * @return 返回file［］数组
	 */
	public static File[] getFileList(String filepath) {
		File d = null;
		File list[] = null;
		try {
			d = new File(filepath);
			if (d.exists()) {
				list = d.listFiles();/* 取得代表目录中所有文件的File对象数组 */
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return list;
	}

	/**
	 * 分页遍历文件夹中文件
	 * 
	 * @param filepath
	 * @return 返回file［］数组
	 */
	public static List<File> getFileList(String filepath, PagerInfo pagerInfo) {
		File d = null;
		List<File> file = new ArrayList<File>();
		try {
			d = new File(filepath);
			if (d.exists()) {
				File list[] = d.listFiles();/* 取得代表目录中所有文件的File对象数组 */
				Integer allCounts = list.length;
				Integer pageSize = pagerInfo.getPageSize();
				Integer curPageNO = pagerInfo.getCurPageNO();
				Integer offset = PagerInfo.getOffset(allCounts, curPageNO, pageSize);
				for (int i = offset; i < pageSize * curPageNO; i++) {
					if (i < list.length) {
						file.add(list[i]);
					}
				}
				PagerInfo pa = new PagerInfo(allCounts, curPageNO, pageSize);
				ContextUtils.getRequest().setAttribute(PagerInfo.PAGERSTR, pa.getToolBar2());

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return file;
	}
	/**
	 * 分页(不含前半部分)遍历文件夹中文件
	 * 
	 * @param filepath
	 * @return 返回file［］数组
	 */
	public static List<File> getFileList1(String filepath, PagerInfo pagerInfo) {
		File d = null;
		List<File> file = new ArrayList<File>();
		try {
			d = new File(filepath);
			if (d.exists()) {
				File list[] = d.listFiles();/* 取得代表目录中所有文件的File对象数组 */
				Integer allCounts = list.length;
				Integer pageSize = pagerInfo.getPageSize();
				Integer curPageNO = pagerInfo.getCurPageNO();
				Integer offset = PagerInfo.getOffset(allCounts, curPageNO, pageSize);
				for (int i = offset; i < pageSize * curPageNO; i++) {
					if (i < list.length) {
						file.add(list[i]);
					}
				}
				PagerInfo pa = new PagerInfo(allCounts, curPageNO, pageSize);
				ContextUtils.getRequest().setAttribute(PagerInfo.PAGERSTR, pa.getToolBar3());
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return file;
	}

	/**
	 * 根据路径创建文件
	 */
	public static File getFile(String path) {
		return new File(path);
	}

	/**
	 * 创建单层路径文件夹
	 */
	public static void makDir(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 创建多层路径文件夹
	 */
	public static void makDirs(File file) {
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 创建多层路径文件夹
	 */
	public static void makDirs(String path) {
		makDirs(getFile(path));
	}

	/**
	 * 创建单层路径文件夹
	 */
	public static void makDir(String path) {
		makDir(getFile(path));
	}

	public static boolean createFile(File file) throws IOException {
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = getFile(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	/**
	 * 根据byte数组，生成文件
	 */
	public static void getFile(byte[] bfile, String filePath,String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath+"\\"+fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	public static void main(String args[]) {
		String filePath = "C:/temp/a/b/c/d/e/f/g.txt";
		File file = new File(filePath);
		try {

			boolean created = createFile(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i + 1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileInputStream;
	}

	public static FileInputStream getFileInputStream(String path) {
		FileInputStream fileInputStream = null;
		fileInputStream = getFileInputStream(getFile(path));
		return fileInputStream;
	}
}
