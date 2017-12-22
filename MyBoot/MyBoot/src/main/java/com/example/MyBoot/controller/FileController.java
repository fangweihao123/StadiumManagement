package com.example.MyBoot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Controller
@ResponseBody
public class FileController {


    @RequestMapping(value = "/testUpload", method= RequestMethod.POST)
    public void testUploadFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {
        FileOutputStream fos=new FileOutputStream(new File("F://test//src//file//upload.jpg"));
        FileInputStream fs=(FileInputStream) multiReq.getFile("file").getInputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=fs.read(buffer))!=-1){
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
    }
}
