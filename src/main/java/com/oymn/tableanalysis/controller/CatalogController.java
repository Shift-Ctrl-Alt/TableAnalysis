package com.oymn.tableanalysis.controller;

import cn.hutool.Hutool;
import com.oymn.tableanalysis.dao.pojo.Catalog;
import com.oymn.tableanalysis.service.CatalogService;
import com.oymn.tableanalysis.utils.SnowFlake;
import com.oymn.tableanalysis.vo.CatalogVo;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiOperation("目录相关的接口")
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @ApiOperation("添加目录结构")
    @PostMapping("add-catalog")
    public Result<String> addCatalog(@ApiParam("目录实体类") @RequestBody Catalog catalog) {
        //todo: createUser赋值
        
        catalog.setId(SnowFlake.nextId());
        String id = catalogService.addCatalog(catalog);
        return Result.success(id);
    }

    @ApiOperation("删除目录结构")
    @DeleteMapping("delete")
    public Result deleteCatalog(Long id) {
        
        catalogService.deleteCatalog(id);
        return Result.success();
    }

    @ApiOperation("修改目录结构")
    @PutMapping("update")
    public Result updateCatalog(@ApiParam("目录实体类") @RequestBody Catalog catalog) {
        catalogService.updateCatalog(catalog);
        return Result.success();
    }

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

}
