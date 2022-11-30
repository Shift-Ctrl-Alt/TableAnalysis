package com.oymn.tableanalysis.service;

import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import com.oymn.tableanalysis.dao.pojo.LayerFile;
import com.oymn.tableanalysis.vo.LayerFileParam;
import com.oymn.tableanalysis.vo.LayerFileVo;

import java.util.List;

public interface LayerFileService {


    LayerFileVo getLayerFile(LayerFileParam layerFileParam);

    void deleteLayerFile(Long layerFileId);

    void addColorAttr(ColorAttr colorAttr);

    void deleteColorAttr(Long id);

    LayerFile getLayerFileById(Long layerId);

    String addLayerFile(LayerFile layerFile);
}
