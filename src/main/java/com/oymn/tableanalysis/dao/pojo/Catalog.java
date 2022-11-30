package com.oymn.tableanalysis.dao.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("目录实体类")
public class Catalog {
    
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
    
    @ApiModelProperty("创建时间")
    private Date createTime;
    
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
