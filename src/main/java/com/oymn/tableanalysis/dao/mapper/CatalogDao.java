package com.oymn.tableanalysis.dao.mapper;


import com.oymn.tableanalysis.dao.pojo.Catalog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatalogDao {


    List<Catalog> getAllCatalog();

    void addCatalog(Catalog catalog);

    void deleteCatalog(Long id);

    void updateCatalog(Catalog catalog);

    Catalog getCatalogById(Long id);
    
}
