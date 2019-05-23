package com.example.demo.common;

public class HttpResult<T>   {
	
	public static final int Code_OK = 1;
	 
	public static final int Code_ERROR = -1;
    // 响应码
    private Integer code;

    // 响应体
    private String msg;
    
    // 数据
    private T data;

    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
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



	public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

  

}