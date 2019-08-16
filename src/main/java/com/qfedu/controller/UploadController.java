package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class UploadController {

    @Autowired
    private UserService userService;

    // 表单中其他非文件数据，可以直接通过参数读取
    // 注意：servlet+fileUpload的形式，不能通过getParameter获取非文件数据
    @RequestMapping("/upload.do")
    public String uploadFile(@RequestParam MultipartFile upfile, String name){

        System.out.println(name);
        // 上传文件的目录
        String dir = "D:/upload";
        // 获取上传的文件的文件名
        String fileName = upfile.getOriginalFilename();

        // 判断目录是否存在
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }

        // 要保存的文件的File对象
        File newFile = new File(dir, fileName);
        try {
            // 保存文件
            upfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取上传文件的输入流，进行流的拷贝，也可以实现文件的上传
        //InputStream inputStream = upfile.getInputStream();

        return "redirect:/success.html";
    }

    @RequestMapping("/upload2.do")
    @ResponseBody
    public JsonResult uploadFile2(@RequestParam("file") MultipartFile upfile, HttpSession session){

        User u = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if(u == null){
            new JsonResult(0, "未登陆");
        }
        // 上传文件的目录
        String dir = "D:/upload";
        // 获取上传的文件的文件名
        String fileName = upfile.getOriginalFilename();

        // 判断目录是否存在
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }

        // 要保存的文件的File对象
        File newFile = new File(dir, fileName);
        try {
            // 保存文件
            upfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取上传文件的输入流，进行流的拷贝，也可以实现文件的上传
        //InputStream inputStream = upfile.getInputStream();

        // 操作数据库
        userService.updateHeadImg(u.getUid(), "/upload/" + fileName);

        return new JsonResult(1, "上传成功");
    }

    @RequestMapping("/download.do")
    public void downloadFile(String fileName, HttpServletResponse response){

        String dir = "D:/upload";

        // 文件流的拷贝
        File file = new File(dir, fileName);
        try {
            // 获取要下载文件的输入流
            FileInputStream inputStream = new FileInputStream(file);
            System.out.println(fileName);
            // 针对指定内容，进行url编码，会将空格/中文等特殊符号，转换为%和两个十六进制值的格式
            fileName = URLEncoder.encode(fileName, "utf-8");
            System.out.println(fileName);
            // 设置响应头，指定下载的文件的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 获取响应的输出流
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] buff = new byte[1024];
            int len = -1;
            while((len = inputStream.read(buff)) != -1){
                outputStream.write(buff, 0, len);
            }

            inputStream.close();
            outputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}



