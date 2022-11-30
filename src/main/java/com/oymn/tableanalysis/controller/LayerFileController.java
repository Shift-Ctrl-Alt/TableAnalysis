package com.oymn.tableanalysis.controller;


import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import com.oymn.tableanalysis.dao.pojo.LayerFile;
import com.oymn.tableanalysis.service.FileService;
import com.oymn.tableanalysis.service.LayerFileService;
import com.oymn.tableanalysis.utils.SnowFlake;
import com.oymn.tableanalysis.vo.LayerFileParam;
import com.oymn.tableanalysis.vo.LayerFileVo;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ApiOperation("图层文件相关接口")
@RestController
@RequestMapping("layer-file")
public class LayerFileController {
    
    @Autowired
    private LayerFileService layerFileService;
    
    @Autowired
    private FileService fileService;
    
    @ApiOperation("添加图层文件")
    @PostMapping("add")
    public Result<String> addLayerFile(@ApiParam("图层文件") @RequestBody LayerFile layerFile){
        layerFile.setId(SnowFlake.nextId());
        String id = layerFileService.addLayerFile(layerFile);
        return Result.success(id);
    }

    /**
     * 删除图层文件
     * @param layerFileId
     * @return
     */
    @ApiOperation("删除图层文件")
    @DeleteMapping("delete")
    public Result deleteLayerFile(@ApiParam("图层id") @RequestParam Long layerFileId){
        layerFileService.deleteLayerFile(layerFileId);
        return Result.success();
    }

    /**
     * 获取图层文件及颜色属性
     * @param layerFileParam
     * @return
     */
    @ApiOperation("获取图层文件")
    @GetMapping("getLayerFile")
    public Result<LayerFileVo> getLayerFile(@ApiParam("参数封装") LayerFileParam layerFileParam){
        LayerFileVo layerFileVo = layerFileService.getLayerFile(layerFileParam);
        return Result.success(layerFileVo);
    }

    /**
     * 图层添加颜色属性
     * @param colorAttr
     * @return
     */
    @ApiOperation("图层添加颜色属性")
    @PostMapping("add/colorAttr")
    public Result addColorAttr(@ApiParam("颜色属性") @RequestBody ColorAttr colorAttr) {
        colorAttr.setId(SnowFlake.nextId());
        layerFileService.addColorAttr(colorAttr);
        return Result.success();
    }

    /**
     * 图层删除颜色属性
     * @param id
     * @return
     */
    @ApiOperation("图层删除颜色属性")
    @DeleteMapping("delete/colorAttr")
    public Result deleteColorAttr(@ApiParam("该颜色属性的id") @RequestParam Long id){
        layerFileService.deleteColorAttr(id);
        return Result.success();
    }

    /**
     * 上传文件
     * @param uploadFile
     * @param dir
     * @param request
     * @return
     */
    @ApiOperation("上传图层文件")
    @PostMapping("upload")
    public Result uploadFile(@RequestPart("file") @RequestParam(value = "file", required = true) MultipartFile uploadFile,
                             @RequestParam("目录") String dir,
                             HttpServletRequest request) {
        String imgPath = fileService.uploadFile(uploadFile, dir, request);
        return Result.success(imgPath);
    }
    
}
