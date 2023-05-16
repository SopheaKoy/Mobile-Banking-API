package co.istad.mbanking.api.user;
import co.istad.mbanking.api.account.Account;
import co.istad.mbanking.api.auth.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<Account> accountList;

    //Auth feature info
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;
    private List<Role> roles;
}
