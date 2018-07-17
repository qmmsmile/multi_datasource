package com.qmm.multi.exception;

import lombok.Getter;

/**
 * @author chenzhipeng.
 */

@Getter
public enum CustomError {
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(5000, "未知服务器错误"),
    ERROR_PARAMS(5050, "入参校验错误:"),
    ERROR_DAY(5051, "日期错误:"),
    SUCCESS(-10000000, "success"),

    REPEAT_TABLE(3000,"该表格模板已建立，不得重建。"),
    REPEAT_TEMPLATE(3001,"该模板名称已存在，请重新命名。"),
    TEMPLATE_ID_NOT_NULL(3002,"模板id不能为空。"),
    IS_COVER(3003,"该模板已经导入数据，此次导入是否需要覆盖之前导入内容？"),
    ERROR_FILE_TYPE(3004,"文件类型不正确，请重新上传。"),
    IS_PARTITION(3005,"此表为分区表，请选择分区。");

    /**
     * 错误代码.
     */
    private Integer code;

    /**
     * 错误消息.
     */
    private String msg;

    CustomError(final Integer code, final String msg) {
        this.code = addBasic(code);
        this.msg = msg;
    }

    /**
     * 起始错误编码.
     */
    private static final int START_CODE = 10000000;
    /**
     * 从10000000开始编码.
     * @param code 简单代码
     * @return 全码
     */
    private Integer addBasic(final Integer code) {
        return START_CODE + code;
    }
}
