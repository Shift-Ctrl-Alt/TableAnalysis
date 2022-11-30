package com.oymn.tableanalysis.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@ApiOperation("颜色属性交互类")
@Data
public class ColorAttrVo {

    @ApiModelProperty("主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    
    @ApiModelProperty("rgb值")
    private String rgb;

    @ApiModelProperty("颜色代表的名称")
    private String name;

    @ApiModelProperty("起始值")
    private Long fromValue;

    @ApiModelProperty("结束值")
    private Long toValue;    
}
