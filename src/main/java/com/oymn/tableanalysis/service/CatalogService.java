package com.oymn.tableanalysis.service;

import com.oymn.tableanalysis.dao.pojo.Catalog;
import com.oymn.tableanalysis.vo.CatalogVo;

import java.util.List;

public interface CatalogService {
    //查询目录
    List<CatalogVo> getAllCatalog();
    
    String addCatalog(Catalog catalog);

    void deleteCatalog(Long id);

    void updateCatalog(Catalog catalog);
    
}
