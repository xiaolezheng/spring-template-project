package com.xxx.xtable.api;

import lombok.Data;

@Data
public class Result<T> {
    private int status;
    private T data;
    private String message;

    public Result() {
    }

    public boolean succeed(){
        return status == Status.SUCC.ordinal();
    }

    public boolean failed(){
        return status == Status.FAIL.ordinal();
    }

    private Result(int status) {
        this.status = status;
    }

    private Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private Result(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static Result fail() {
        Result result = new Result(Status.FAIL.ordinal());
        return result;
    }

    public static Result fail(String message) {
        Result result = new Result(Status.FAIL.ordinal());
        result.setMessage(message);

        return result;
    }

    public static <T> Result succ(T data) {
        Result result = new Result(Status.SUCC.ordinal());
        result.setData(data);

        return result;
    }

    public static Result succ() {
        Result result = new Result(Status.SUCC.ordinal());
        return result;
    }


    enum Status {
        FAIL, SUCC;
    }
}
