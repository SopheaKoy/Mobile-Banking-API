package co.istad.mbanking.api.account;

import co.istad.mbanking.api.accounttype.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String account_no;
    private String account_name;
    private String profile;
    private Integer pin;
    private String password;
    private String phone_number;
    private Integer transfer_limit;
    private AccountType  account_type;

}
