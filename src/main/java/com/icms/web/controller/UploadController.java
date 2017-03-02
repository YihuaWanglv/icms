package com.icms.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.icms.model.base.ImageObject;
import com.icms.model.base.JsonObject;
import com.icms.util.ChineseToSpell;
import com.icms.util.ObjectUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = Logger.getLogger(UploadController.class);

	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private static final String IMAGE_PATH = "image";
	private static final String FILE_SEPARATOR = File.separator;
	private static final String WEB_SEPARATOR = "/";

	@Value("${path.image.upload}")
	private String imageSavePath;
	@Value("${path.image.base.url}")
	private String imageBaseUrl;

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	@ResponseBody
	public void SingleImageUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params, HttpSession session) throws IOException {
		ServletFileUpload upload;
		String filedir = null;
		ServletContext context = session.getServletContext();
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory
															// for disk-based
															// file items
		upload = new ServletFileUpload(factory);// Create a new file upload
												// handler
		upload.setSizeMax(this.MAXSize);// Set overall request size constraint
										// 4194304
		System.out.println("filedir=" + filedir);

		Date today = new Date();
		filedir = createImageSavePath(imageSavePath, today);
		System.out.println("filedir=" + filedir);
		System.out.println("context.getContextPath()=" + context.getContextPath());
		System.out.println("context.getRealPath(IMAGE_PATH)=" + context.getRealPath(IMAGE_PATH));

		PrintWriter out = response.getWriter();
		try {
			List<FileItem> items = upload.parseRequest(request);
			if (items != null && !items.isEmpty()) {
				File dirFile = new File(filedir);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				for (FileItem fileItem : items) {
					String filename = fileItem.getName();
					filename = formatImageName(filename);
					String filepath = filedir + File.separator + filename;

					System.out.println("文件保存路径为:" + filepath);

					File file = new File(filepath);
					InputStream inputSteam = fileItem.getInputStream();
					BufferedInputStream fis = new BufferedInputStream(inputSteam);
					FileOutputStream fos = new FileOutputStream(file);
					int f;
					while ((f = fis.read()) != -1) {
						fos.write(f);
					}
					fos.flush();
					fos.close();
					fis.close();
					inputSteam.close();
					System.out.println("文件：" + filename + "上传成功!");
				}
			}
			System.out.println("上传文件成功!");
			out.write("上传文件成功!");
		} catch (FileUploadException e) {
			e.printStackTrace();
			out.write("上传文件失败:" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			out.write("上传文件失败:" + e.getMessage());
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
	private String formatImageName(String filename) {
		return addDateInfoToName(ChineseToSpell.cnToSpellWithFirstCharUpCaseAndTooLongBridge(filename, 50));
	}

	private String addDateInfoToName(String filename) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhMMss");
		return df.format(date) + "_" + filename;
	}

	private String createImageSavePath(String imagePath, Date today) {
		return imagePath + FILE_SEPARATOR + getDatePath(today);
	}

	private String getDatePath(Date today) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(today);
	}

	/**
	 * 上传单文件
	 * 
	 * @param request
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadSingleImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadSingleImage(MultipartHttpServletRequest request, String fieldName) throws Exception {
		logger.info("upload file start method .............................");
		Iterator<String> iter = request.getFileNames();
		String result = "error|没有文件被上传";
		while (iter.hasNext()) {
			List<MultipartFile> multipartFiles = request.getFiles(iter.next());
			/* 没有文件被上传 */
			if (multipartFiles == null || multipartFiles.isEmpty()) {
				return result;
			}

			try {
				for (MultipartFile multipartFile : multipartFiles) {
					if (multipartFile.isEmpty()) {

						continue;
					}

//					String originalFileName = multipartFile.getOriginalFilename();// 新加的,获取文件名
					String fileExtension = ObjectUtil.getExtension(multipartFile.getOriginalFilename());
					String datetime = getDatePath(new Date());
					String fileName = UUID.randomUUID().toString() + "." + fileExtension;
					String filePath = imageSavePath + File.separator + datetime;
					String imageUrl = imageBaseUrl + WEB_SEPARATOR + datetime + WEB_SEPARATOR + fileName;
					String fileFullPath = filePath + File.separator + fileName;
					logger.info("[filePath]=" + filePath);
					logger.info("[imageUrl]=" + imageUrl);

					File directory = new File(filePath);
					if (!directory.exists()) {
						directory.mkdir();
					}
					File file = new File(fileFullPath);
					multipartFile.transferTo(file);
					result = imageUrl;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = "error|服务器上传图片错误";
			}
		}

		logger.info("upload file result:" + result);
		return result;
	}

	/**
	 * 上传多文件
	 * 
	 * @author wanglvyihua <wanglvyihua@gmail.com>
	 * 
	 * @param request
	 *            org.springframework.web.multipart.MultipartHttpServletRequest
	 *            对象
	 * @param fieldName
	 *            表单 file 域的名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadMutiImage", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject uploadMutiImage(MultipartHttpServletRequest request, String fieldName) throws Exception {
		logger.info("upload file start method .............................");
		JsonObject responseJson = new JsonObject();

		Iterator<String> iter = request.getFileNames();
		while (iter.hasNext()) {
			List<MultipartFile> multipartFiles = request.getFiles(iter.next());
			/* 没有文件被上传 */
			if (multipartFiles == null || multipartFiles.isEmpty()) {
				responseJson.setMessage("No image.");
				responseJson.setStatus(1);
				return responseJson;
			}
			List<ImageObject> ios = new ArrayList<ImageObject>();
			for (MultipartFile multipartFile : multipartFiles) {
				if (multipartFile.isEmpty()) {

					continue;
				}

				String originalFileName = multipartFile.getOriginalFilename();// 新加的,获取文件名
				String fileExtension = ObjectUtil.getExtension(multipartFile.getOriginalFilename());
				String datetime = getDatePath(new Date());
				String fileName = UUID.randomUUID().toString() + "." + fileExtension;
				String filePath = imageSavePath + File.separator + datetime;
				String imageUrl = imageBaseUrl + WEB_SEPARATOR + datetime + WEB_SEPARATOR + fileName;
				String fileFullPath = filePath + File.separator + fileName;
				logger.info("[filePath]=" + filePath);
				logger.info("[imageUrl]=" + imageUrl);
				try {

					File directory = new File(filePath);
					if (!directory.exists()) {
						directory.mkdir();
					}
					File file = new File(fileFullPath);
					multipartFile.transferTo(file);
					ImageObject io = new ImageObject();
					io.setOriginFileName(originalFileName);
					io.setUrl(imageUrl);
					ios.add(io);
				} catch (Exception e) {
					e.printStackTrace();

					continue;
				}
			}
			responseJson.setData(ios);
		}

		logger.info("upload file result json:" + new Gson().toJson(responseJson));
		return responseJson;
	}
}
