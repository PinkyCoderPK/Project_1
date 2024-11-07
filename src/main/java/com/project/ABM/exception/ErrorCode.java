package com.project.ABM.exception;

public enum ErrorCode {
    UNCATEGORY_EXCEPTION(9999,"Lỗi chưa xác định"),
    USER_EXISTED(1001,"Đã tồn tại tên người dùng, vui lòng đặt tên khác"),
    USERNAME_INVALID(1002,"Username phải từ 3 đến 24 kí tự"),
    PHONENUMBER_INVALID(1003,"Số điện thoại phải là 10 chữ số"),
    INVALID_KEY(5555,"Invalid message key"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
