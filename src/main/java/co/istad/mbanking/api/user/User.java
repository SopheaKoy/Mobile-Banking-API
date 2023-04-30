package co.istad.mbanking.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class User {
    //!POJO Class User
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;
}
