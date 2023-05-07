package co.istad.mbanking.api.useraccount.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserAccountDto (@NotBlank String createdAt,
                              @NotEmpty Boolean isDeleted) {
}
