package com.oymn.tableanalysis.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.oymn.tableanalysis.dao.pojo.ColorAttr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@ApiModel("图层文件实体类，用于和前端交互")
@Data
public class LayerFileVo {

    @ApiModelProperty("主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("图层的时间")
    private Long surveyTime;

    @ApiModelProperty("地区")
    private String area;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("创建者")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    @ApiModelProperty("所属目录")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long catalogId;

    @ApiModelProperty("颜色属性链表")
    private List<ColorAttrVo> colorAttrVoList;
    
}
