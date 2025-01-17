package com.aljjabaegi.api.domain.member;

import com.aljjabaegi.api.common.response.ItemResponse;
import com.aljjabaegi.api.common.response.ItemsResponse;
import com.aljjabaegi.api.domain.member.record.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 Controller
 *
 * @author GEONLEE
 * @since 2024-04-01<br />
 * 2024-04-02 GEONLEE - 응답 객체 Builder Pattern 적용
 */
@RestController
@Tag(name = "사용자 정보 조회 / 편집", description = "담당자: GEONLEE")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService userService;

    @GetMapping(value = "/v1/members")
    @Operation(summary = "전체 사용자 조회", operationId = "API-MEMBER-01")
    public ResponseEntity<ItemsResponse<MemberSearchResponse>> getUserList() {
        List<MemberSearchResponse> userSearchResponseList = userService.getMemberList();
        long size = userSearchResponseList.size();
        return ResponseEntity.ok()
                .body(ItemsResponse.<MemberSearchResponse>builder()
                        .status("OK")
                        .message("데이터를 조회하는데 성공하였습니다.")
                        .size(size)
                        .items(userSearchResponseList).build());
    }

    @GetMapping(value = "/v1/members/{memberId}")
    @Operation(summary = "사용자 ID로 조회", operationId = "API-MEMBER-02")
    public ResponseEntity<ItemResponse<MemberSearchResponse>> getMembers(@PathVariable String memberId) {
        MemberSearchResponse userSearchResponse = userService.getMembers(memberId);

        return ResponseEntity.ok()
                .body(ItemResponse.<MemberSearchResponse>builder()
                        .status("OK")
                        .message("데이터를 조회하는데 성공하였습니다.")
                        .item(userSearchResponse).build());
    }

    @GetMapping(value = "/v1/check/member-id/{memberId}")
    @Operation(summary = "사용자 ID 중복 여부 조회", operationId = "API-MEMBER-03")
    public ResponseEntity<ItemResponse<Boolean>> checkMemberId(@PathVariable String memberId) {
        boolean isDuplication = userService.checkMemberId(memberId);
        String message = (isDuplication) ? "중복된 ID가 존재합니다." : "사용 가능한 ID 입니다.";
        return ResponseEntity.ok()
                .body(ItemResponse.<Boolean>builder()
                        .status("OK")
                        .message(message)
                        .item(isDuplication).build());
    }

    @PostMapping(value = "/v1/member")
    @Operation(summary = "사용자 정보 추가", operationId = "API-MEMBER-04")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ItemResponse<MemberCreateResponse>> createMember(@RequestBody @Valid MemberCreateRequest parameter) {
        MemberCreateResponse createdMember = userService.createMember(parameter);
        return ResponseEntity.ok()
                .body(ItemResponse.<MemberCreateResponse>builder()
                        .status("OK")
                        .message("데이터를 추가하는데 성공하였습니다.")
                        .item(createdMember).build());
    }

    @PutMapping(value = "/v1/member")
    @Operation(summary = "사용자 정보 수정", operationId = "API-USER-05")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ItemResponse<MemberModifyResponse>> modifyUser(@RequestBody @Valid MemberModifyRequest parameter) {
        MemberModifyResponse modifiedMember = userService.modifyMember(parameter);
        return ResponseEntity.ok()
                .body(ItemResponse.<MemberModifyResponse>builder()
                        .status("OK")
                        .message("데이터를 수정하는데 성공하였습니다.")
                        .item(modifiedMember).build());
    }

    @DeleteMapping(value = "/v1/member/{memberId}")
    @Operation(summary = "사용자 삭제", operationId = "API-USER-06")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ItemResponse<Long>> deleteMember(@PathVariable String memberId) {
        Long deleteCount = userService.deleteMember(memberId);
        return ResponseEntity.ok()
                .body(ItemResponse.<Long>builder()
                        .status("OK")
                        .message("데이터를 삭제하는데 성공하였습니다.")
                        .item(deleteCount).build());
    }
}
