package com.zt.gp.bean;

import lombok.Data;

@Data
public class BaseResult {
    public String status;
    public String message;
    public Object data;
    BaseResult() {
        this.status = "0";
    }

    public static BaseResult ok() {
        return new BaseResult();
    }

    public static BaseResult data(Object data){
        BaseResult ok = ok();
        ok.setData(data);
        return ok;
    }

    public static BaseResult ok(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage(message);
        return baseResult;
    }
    public static BaseResult ok(String message,Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }


    public static BaseResult error(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus("1");
        baseResult.setMessage(message);
        return baseResult;
    }

    public static BaseResult error(String status, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}
