package com.oymn.tableanalysis.vo;

import com.oymn.tableanalysis.common.StatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("统一返回值 Result")
public class Result<T> {
    
    @ApiModelProperty("是否成功返回")
    private boolean success;
    
    @ApiModelProperty("响应码")
    private int code;
    
    @ApiModelProperty("响应的提示信息")
    private String msg;
    
    @ApiModelProperty("响应的具体内容")
    private T data;

    private Result(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(){
        return new Result<>(true, StatusCode.RESPONSE_SUCCESS.getCode(), StatusCode.RESPONSE_SUCCESS.getMsg(), null);
    }
    
    public static <T> Result<T> success(T data){
        return new Result<>(true, StatusCode.RESPONSE_SUCCESS.getCode(), StatusCode.RESPONSE_SUCCESS.getMsg(), data);
    }
    
    public static Result fail(int code, String msg){
        return new Result(false, code, msg, null);
    }
    
    //public static Result fail(int code, String msg){
    //    return new Result(false, code, msg);
    //}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
