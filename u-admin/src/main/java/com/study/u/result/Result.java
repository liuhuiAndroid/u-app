package com.study.u.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static Result success(){
        return new Result();
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    private Result() {
        this.code = 0;
        this.msg = "成功";
    }

    private Result(T data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if(cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}