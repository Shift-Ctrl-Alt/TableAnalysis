package com.oymn.tableanalysis.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.oymn.tableanalysis.common.TimeSelectConstant;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.mapper.LayerFileDao;
import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import com.oymn.tableanalysis.dao.pojo.LayerFile;
import com.oymn.tableanalysis.service.CatalogService;
import com.oymn.tableanalysis.service.ColorAttrService;
import com.oymn.tableanalysis.service.LayerFileService;
import com.oymn.tableanalysis.vo.ColorAttrVo;
import com.oymn.tableanalysis.vo.LayerFileParam;
import com.oymn.tableanalysis.vo.LayerFileVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.management.QueryEval;
import java.awt.*;
import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LayerFileServiceImpl implements LayerFileService {

    @Autowired
    private LayerFileDao layerFileDao;
    
    @Autowired
    private ColorAttrService colorAttrService;
    
    @Autowired
    private CatalogService catalogService;

    @Override
    public LayerFileVo getLayerFile(LayerFileParam layerFileParam) {
        
        LayerFile layerFile = layerFileDao.getLayerFile(layerFileParam.getCatalogId(), layerFileParam.getQueryTime(), layerFileParam.getArea());

        LayerFileVo layerFileVo = new LayerFileVo();
        if (layerFile != null) {
            BeanUtils.copyProperties(layerFile, layerFileVo);
            List<ColorAttr> colorAttrList = colorAttrService.getColorAttr(layerFile.getId());
            if(colorAttrList != null){
                List<ColorAttrVo> colorAttrVoList = colorAttrList.stream().map(e -> {
                    ColorAttrVo colorAttrVo = new ColorAttrVo();
                    BeanUtils.copyProperties(e, colorAttrVo);
                    return colorAttrVo;
                }).collect(Collectors.toList());
                layerFileVo.setColorAttrVoList(colorAttrVoList);
            }
        }

        return layerFileVo;
    }

    @Override
    public void deleteLayerFile(Long layerFileId) {
        layerFileDao.deleteLayerFile(layerFileId);
        //删除该图层的颜色属性
        colorAttrService.deleteColorAttrByLayerId(layerFileId);
    }

    @Override
    public void addColorAttr(ColorAttr colorAttr) {
        Long layerId = colorAttr.getLayerId();
        LayerFile layerFile = getLayerFileById(layerId);
        if(layerFile == null){
            throw new ConditionException("该图层不存在");
        }
        
        colorAttrService.addColorAttr(colorAttr);
    }
    
    @Override
    public LayerFile getLayerFileById(Long layerId) {
        return layerFileDao.getLayerFileById(layerId);
    }

    @Override
    public void deleteColorAttr(Long id) {
        colorAttrService.deleteColorAttr(id);
    }

    @Override
    public String addLayerFile(LayerFile layerFile) {
        layerFileDao.addLayerFile(layerFile);
        return layerFile.getId().toString();
    }
}
