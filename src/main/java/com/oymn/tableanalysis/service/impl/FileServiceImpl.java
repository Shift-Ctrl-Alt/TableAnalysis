package com.oymn.tableanalysis.service.impl;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    //定义图片的上传路径
    @Value("${IMG_BASE_PATH}")
    private String IMG_BASE_PATH;
    //定义服务器的访问地址
    @Value("${IMG_SERVER_PATH}")
    private String IMG_SERVER_PATH;

    /**
     * 上传文件
     *
     * @param uploadFile
     * @param 
     */
    @Override
    public String uploadFile(MultipartFile uploadFile, String dir, HttpServletRequest request) {
        
        if(uploadFile == null){
            throw new ConditionException("文件为空");
        }
        
        if(dir == null || "".equals(dir)){
            throw new ConditionException("请指定上传的目录");
        }
        
        //获得文件名
        String filename = uploadFile.getOriginalFilename();
        //获取文件名后缀
        String fileSuffix = filename.substring(filename.lastIndexOf("."));
        //创建UUID，用来保持文件名字的唯一性
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //进行文件名称的拼接
        String newFileName = uuid + fileSuffix;
        
        //日期目录
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datePath = dateFormat.format(new Date());
        
        //指定上传后的路径
        File targetPath = new File(IMG_BASE_PATH + dir, datePath);
        //文件夹路径不存在
        if(!targetPath.exists()){
            targetPath.mkdirs();
        }

        //最终的文件
        File targetFile = new File(targetPath, newFileName);

        try {
            uploadFile.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConditionException("文件上传错误，请重新上传");
        }
        
        //返回文件的服务器地址
        return IMG_SERVER_PATH + dir + "/" + datePath + "/" + newFileName;
    }
}
