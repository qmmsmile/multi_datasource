package com.qmm.multi.exception;

import com.qmm.multi.util.EmptyUtils;
import lombok.Getter;

/**
 *
 * @author chenzhipeng
 * @date 2018/1/6
 */
@Getter
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 123456789L;

    private final Integer code;

    public CustomException(CustomError customError) {
        this(customError.getCode(), customError.getMsg());
    }

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomException(Integer code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    /**
     * 异常堆栈概要信息
     * @param e
     * @return
     */
    public static String summaryStackInfo(Throwable e) {
        String msg = e.toString();
        if (EmptyUtils.isNotEmpty(e.getStackTrace())) {
            msg += " at " + e.getStackTrace()[0];
        }
        Integer length = msg.length();
        return msg.substring(0, length>255?255:length);
    }
}
