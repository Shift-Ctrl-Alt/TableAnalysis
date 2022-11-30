package com.oymn.tableanalysis.service.impl;

import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.mapper.CatalogDao;
import com.oymn.tableanalysis.dao.pojo.Catalog;
import com.oymn.tableanalysis.service.CatalogService;
import com.oymn.tableanalysis.vo.CatalogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {
    
    @Autowired
    private CatalogDao catalogDao;

    @Override
    public List<CatalogVo> getAllCatalog() {
        List<Catalog> catalogList = catalogDao.getAllCatalog();
        
        //将catalog转化为catalogVo
        List<CatalogVo> catalogVoList = catalogList.stream().map(catalog -> {
            CatalogVo catalogVo = new CatalogVo();
            BeanUtils.copyProperties(catalog, catalogVo);
            return catalogVo; 
        }).collect(Collectors.toList());
        
        //获取每个子目录
        catalogVoList.forEach(e ->{
            List<CatalogVo> childList = getChildList(e.getId(), catalogVoList);
            e.setChildList(childList != null ? childList : null);
        });
        
        //获取第一层子目录
        List<CatalogVo> result = catalogVoList.stream().filter(t -> t.getParentId() == 0).collect(Collectors.toList());
        
        return result;
    }

    @Override
    public String addCatalog(Catalog catalog) {
        Long parentId = catalog.getParentId();
        if(parentId != 0){
            Catalog dbCatalog = catalogDao.getCatalogById(parentId);
            if(dbCatalog == null){
                throw new ConditionException("父类型不存在");
            }
        }
        
        //添加目录
        catalogDao.addCatalog(catalog);
        
        return catalog.getId().toString();
    }

    @Override
    public void deleteCatalog(Long id) {
        catalogDao.deleteCatalog(id);
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        catalogDao.updateCatalog(catalog);
    }

    //获取子目录
    private List<CatalogVo> getChildList(Long id, List<CatalogVo> catalogVoList) {
        return catalogVoList.stream().filter(t -> t.getParentId() == id).collect(Collectors.toList());
    }
}
