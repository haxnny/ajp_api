package com.aljjabaegi.api.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * 공통 응답 구조체
 *
 * @author GEONLEE
 * @since 2024-04-02
 */
@Schema(description = "공통 응답 구조체")
@Builder
public record ItemResponse<T>(
        @Schema(description = "상태 코드", example = "OK")
        String status,
        @Schema(description = "메시지", example = "데이터를 조회하는데 성공하였습니다.")
        String message,
        @Schema(description = "응답 객체")
        T item
) {
}
