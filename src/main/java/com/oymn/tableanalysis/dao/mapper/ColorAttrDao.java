package com.oymn.tableanalysis.dao.mapper;

import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColorAttrDao {

    List<ColorAttr> getColorAttr(@Param("layerFileId") Long layerFileId);

    void deleteColorAttrByLayerId(Long layerFileId);

    void addColorAttr(ColorAttr colorAttr);

    void deleteColorAttr(Long id);
}
