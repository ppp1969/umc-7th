package umc.study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.ErrorReasonDTO;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // Article Error
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // FoodCategory Error
    FOODCATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOODCATEGORY4001", "해당 푸드카테고리가 없습니다. "),
    
    // Region Error
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4001", "해당 지역이 없습니다."),

    // Store Error
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "해당 식당이 없습니다."),

    // Mission Error
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당 미션이 없습니다."),

    // Paging
    NEGATIVE_PAGE(HttpStatus.BAD_REQUEST, "PAGE4001", "페이지는 1이상 값이어야 합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    // 코드 기반으로 Status 찾아서 맵핑하는 함수
    // 애노테이션에서 해당 Code를 날리면, ExceptionAdvice에서 이 함수를 사용함!
    // ExceptionAdvice에서 code를 통해 Status 찾고 response 생성
    public static Optional<ErrorStatus> fromCode(String code) {
        return Arrays.stream(values())
                .filter(status -> status.getCode().equals(code))
                .findFirst();
    }
    
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

}
