package com.oymn.tableanalysis.controller;

import cn.hutool.core.date.DateUtil;
import com.oymn.tableanalysis.service.CatalogService;
import com.oymn.tableanalysis.service.LayerFileService;
import com.oymn.tableanalysis.vo.CatalogVo;
import com.oymn.tableanalysis.vo.LayerFileParam;
import com.oymn.tableanalysis.vo.LayerFileVo;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApiOperation("主界面的接口")
@RestController
@RequestMapping("main")
public class MainController {
    
    @Autowired
    private CatalogService catalogService;
    
    @Autowired
    private LayerFileService layerFileService;

    /**
     * 获取目录结构
     * @return
     */
    @ApiOperation("获取目录结构")
    @GetMapping("getAllCatalog")
    public Result<List<CatalogVo>> getAllCatalog() {
        List<CatalogVo> catalogVoList = catalogService.getAllCatalog();
        return Result.success(catalogVoList);
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
    
}
