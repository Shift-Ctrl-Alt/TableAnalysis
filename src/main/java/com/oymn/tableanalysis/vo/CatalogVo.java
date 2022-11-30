package com.oymn.tableanalysis.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("目录实体类，用于和前端交互")
@Data
public class CatalogVo {

    @ApiModelProperty("主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("所属的上一层目录的id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty("时间选择的方式")
    private int timeSelect;

    @ApiModelProperty("创建者")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;
    
    @ApiModelProperty("子目录")
    private List<CatalogVo> childList;
}
