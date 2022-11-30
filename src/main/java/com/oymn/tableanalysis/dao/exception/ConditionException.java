package com.oymn.tableanalysis.dao.exception;

public class ConditionException extends RuntimeException {

    private static final long serialVersionUID = 1;

    private int code;

    public ConditionException(int code, String name){
        super(name);
        this.code = code;
    }

    public ConditionException(String name){
        super(name);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
