package co.istad.mbanking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SearchByNameDto(
        @NotBlank(message = "name is required..!") String name) {
}
