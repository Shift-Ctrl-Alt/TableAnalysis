package com.oymn.tableanalysis.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("搜索图层时用的参数")
@Data
public class LayerFileParam {
    
    @ApiModelProperty("目录的id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long catalogId;
    
    @ApiModelProperty("查询的日期")
    private Long queryTime;
    
    @ApiModelProperty("查询的地区")
    private String area;
}
