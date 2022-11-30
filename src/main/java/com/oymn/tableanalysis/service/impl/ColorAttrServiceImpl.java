package com.oymn.tableanalysis.service.impl;

import com.oymn.tableanalysis.dao.mapper.ColorAttrDao;
import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import com.oymn.tableanalysis.service.ColorAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorAttrServiceImpl implements ColorAttrService {

    @Autowired
    private ColorAttrDao colorAttrDao;

    @Override
    public List<ColorAttr> getColorAttr(Long layerFileId) {
        List<ColorAttr> colorAttrList = colorAttrDao.getColorAttr(layerFileId);
        return colorAttrList;
    }

    @Override
    public void deleteColorAttrByLayerId(Long layerFileId) {
        colorAttrDao.deleteColorAttrByLayerId(layerFileId);
    }

    @Override
    public void addColorAttr(ColorAttr colorAttr) {
        colorAttrDao.addColorAttr(colorAttr);
    }

    @Override
    public void deleteColorAttr(Long id) {
        colorAttrDao.deleteColorAttr(id);
    }
}
