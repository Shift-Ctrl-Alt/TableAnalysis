package com.oymn.tableanalysis.dao.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

@ApiOperation("颜色属性")
@Data
public class ColorAttr {
    
    @ApiModelProperty("主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("图层id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long layerId;

    @ApiModelProperty("rgb值")
    private String rgb;

    @ApiModelProperty("颜色代表的名称")
    private String name;
    
    @ApiModelProperty("起始值")
    private Long fromValue;
    
    @ApiModelProperty("结束值")
    private Long toValue;
    
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;
}
