package com.oymn.tableanalysis.service;

import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColorAttrService {


    List<ColorAttr> getColorAttr(Long layerFileId);

    void deleteColorAttrByLayerId(Long layerFileId);

    void addColorAttr(ColorAttr colorAttr);

    void deleteColorAttr(Long id);
    
}
