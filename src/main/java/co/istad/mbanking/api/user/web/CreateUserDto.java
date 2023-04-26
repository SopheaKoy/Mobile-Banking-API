package co.istad.mbanking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(@NotBlank(message="Name is required..!")  String name,
                            @NotBlank (message = "Name is required..!")String gender,
                            String oneSignalId,
                            String studentCardId,
                           @NotNull(message ="You hava to confirm , are you a student ? ") Boolean isStudent) {
    //! response to client
}