package com.aljjabaegi.api.domain.user.record;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 사용자 조회 응답 record
 *
 * @author GEONLEE
 * @since 2024-04-01
 */
@Schema(description = "사용자 조회 응답 record")
public record UserSearchResponse(
        @Schema(description = "사용자 ID", example = "honggildong123")
        String userId,
        @Schema(description = "사용자 명", example = "홍길동")
        String userName,
        @Schema(description = "전화번호", example = "010-1234-5678")
        String cellphone,
        @Schema(description = "생성 일시", example = "2024-04-01 00:00:00")
        String createDate,
        @Schema(description = "수정 일시", example = "2024-04-01 00:00:00")
        String modifyDate
) {
}
