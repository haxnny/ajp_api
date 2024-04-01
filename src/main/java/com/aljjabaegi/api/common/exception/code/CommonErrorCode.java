package com.aljjabaegi.api.common.exception.code;

/**
 * 공통 Exception enumeration
 *
 * @author GEONLEE
 * @since 2024-04-02
 */
public enum CommonErrorCode implements ErrorCode {
    SERVICE_ERROR("ER_SV_01", "요청한 서비스에 문제가 발생했습니다. 잠시 후에 다시 시도해 주세요."),
    INVALID_PARAMETER("ER_CT_02", "적합하지 않은 인자가 전달되었습니다."),
    ENTITY_NOT_FOUND("ER_CT_03", "데이터가 존재하지 않습니다.");

    private final String status;
    private final String message;

    CommonErrorCode(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }
}