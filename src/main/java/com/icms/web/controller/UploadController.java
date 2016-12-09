package com.icms.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileItemFactory;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//import org.slf4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = Logger.getLogger(UploadController.class);
	
	private final long MAXSize = 4194304*2L;//4*2MB
	private static final String IMAGE_PATH = "image";
	private static final String FILE_SEPARATOR = File.separator;
	private static final String WEB_SEPARATOR = "/";
	
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	@ResponseBody
	public void SingleImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params, HttpSession session) throws IOException {
		ServletFileUpload upload;
		String filedir=null;
		ServletContext context = session.getServletContext();
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory for disk-based file items
		upload = new ServletFileUpload(factory);// Create a new file upload handler
		upload.setSizeMax(this.MAXSize);// Set overall request size constraint 4194304
//		filedir=context.getRealPath("images");
//		filedir=context.getRealPath(IMAGE_PATH);
		System.out.println("filedir="+filedir);
		
		Date today = new Date();
		filedir = createImageSavePath(context.getRealPath(IMAGE_PATH), today);
		System.out.println("context.getContextPath()="+context.getContextPath());
		System.out.println("context.getRealPath(IMAGE_PATH)="+context.getRealPath(IMAGE_PATH));
		
		PrintWriter out=response.getWriter();
		try {
			List<FileItem> items = upload.parseRequest(request);
			if(items!=null	&& !items.isEmpty()){
				File dirFile = new File(filedir);
				if (!dirFile.exists())
				{
					dirFile.mkdirs();
				}
				for (FileItem fileItem : items) {
					String filename=fileItem.getName();
//					filename = formatImageName(filename);
					String filepath=filedir+File.separator+filename;

					System.out.println("文件保存路径为:"+filepath);
					
					File file=new File(filepath);
					InputStream inputSteam=fileItem.getInputStream();
					BufferedInputStream fis=new BufferedInputStream(inputSteam);
				    FileOutputStream fos=new FileOutputStream(file);
				    int f;
				    while((f=fis.read())!=-1)
				    {
				       fos.write(f);
				    }
				    fos.flush();
				    fos.close();
				    fis.close();
					inputSteam.close();
//					imageService.createImage(filename,getDatebaseLocaltionPath(today,filename), today);
					System.out.println("文件："+filename+"上传成功!");
				}
			}
			System.out.println("上传文件成功!");
			out.write("上传文件成功!");
		} catch (FileUploadException e) {
			e.printStackTrace();
			out.write("上传文件失败:"+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			out.write("上传文件失败:"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: formatImageName
	 * @Description: 格式化图片文件名，添加时间信息前缀
	 * @param filename
	 * @return
	 * @return String
	 * @exception/throws
	 */
//	private String formatImageName(String filename)
//	{
//		return addDateInfoToName(ChineseToSpell.cnToSpellWithFirstCharUpCaseAndTooLongBridge(filename,50));
//	}


	private String addDateInfoToName(String filename)
	{
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhMMss");
		return df.format(date)+"_"+filename;
	}


	private String createImageSavePath(String imagePath, Date today)
	{
		return imagePath + FILE_SEPARATOR + getDatePath(today);
	}
	private String getDatePath(Date today) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(today);
	}
	private String getDatebaseLocaltionPath(Date today, String name){
		return WEB_SEPARATOR + IMAGE_PATH + WEB_SEPARATOR + getDatePath(today) + WEB_SEPARATOR + name;
	}
}
