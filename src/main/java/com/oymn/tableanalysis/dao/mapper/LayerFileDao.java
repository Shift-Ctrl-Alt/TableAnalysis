package com.oymn.tableanalysis.dao.mapper;

import com.oymn.tableanalysis.dao.pojo.LayerFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LayerFileDao {


    Long getCollectionIdByCatalogId(Long catalogId);

    LayerFile getLayerFile(@Param("catalogId") Long catalogId,
                           @Param("queryTime") Long queryTime,
                           @Param("area") String area);

    void deleteLayerFile(Long layerFileId);

    LayerFile getLayerFileById(Long layerId);

    Long addLayerFile(LayerFile layerFile);
}
