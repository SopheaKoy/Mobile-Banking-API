package co.istad.mbanking.api.accounttype.web;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeDto(
                        @NotBlank(message = "name is required...!")
                        String name) {
    //!Data Transfer object = DAO
}
