package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class RegisterDto{
        @NotBlank
        String name;
        @NotBlank
        String address;
    }
}
