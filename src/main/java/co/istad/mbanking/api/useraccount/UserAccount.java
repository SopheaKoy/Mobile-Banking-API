package co.istad.mbanking.api.useraccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private Integer id;
    private Integer userId;
    private Integer accountId;
    private LocalDateTime createdAt;
    private Boolean isDisabled;
}
