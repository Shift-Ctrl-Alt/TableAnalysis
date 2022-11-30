package com.oymn.tableanalysis.dao.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("分页后的返回值")
public class PageResult<T> {
    
    @ApiModelProperty("实际数量")
    private Integer total;
    
    @ApiModelProperty("数据")
    private List<T> data;

    public PageResult(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
