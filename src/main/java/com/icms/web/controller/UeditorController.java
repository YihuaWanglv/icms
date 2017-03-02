package com.icms.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;


@Controller
public class UeditorController {

//	@RequestMapping("/controller")
//    public @ResponseBody String controller(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
//        request.setCharacterEncoding("utf-8");
//        response.setHeader("Content-Type", "text/html");
//        @SuppressWarnings("resource")
//        ApplicationContext appContext = new ClassPathXmlApplicationContext();
//        String baseState = new MyActionEnter(request, appContext.getResource(configJSONPath).getInputStream()).exec();
//        // response.getWriter().write(baseState);
//        System.err.println(baseState);
//        return baseState;
//    }
	
//	@RequestMapping("/init")
//    public @ResponseBody String controller(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
//        request.setCharacterEncoding( "utf-8" );
//    	response.setHeader("Content-Type" , "text/html");
//    	
//    	String rootPath = request.getRealPath( "/" );
//    	System.out.println("rootPath=" + rootPath);
//        return new ActionEnter( request, rootPath ).exec();
//    }
	
	@RequestMapping("/init")
    public @ResponseBody String initUeditor(HttpServletResponse response,HttpServletRequest request){
        response.setContentType("application/json");
        //配置路径，首先获取webpp根目录绝对路径
        String rootPath = request.getSession().getServletContext().getRealPath("/");
		//将config.json放到与ueditor.config.js同一级的目录下。将ueditor所有文件放入到wapapp-static-ueditor下
		//设置获取服务端配置文件地址修正路径，此路径同时作用于文件上传
        rootPath=rootPath+"static";
        System.out.println("rootPath=" + rootPath);
        String exec = new ActionEnter(request, rootPath).exec();
        return exec;
    }
}
