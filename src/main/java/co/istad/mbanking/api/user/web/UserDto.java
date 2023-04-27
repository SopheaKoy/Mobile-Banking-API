package co.istad.mbanking.api.user.web;

import lombok.Builder;

@Builder
public record UserDto(String name,
                      String gender,
                      String oneSignalId,
                      String studentCardId,
                      Boolean isStudent) {
}
