package co.istad.mbanking.api.account.web;

import co.istad.mbanking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;

public record AccountDto(
        @NotBlank(message = "User_Account no is required...!") String account_no,
        @NotBlank(message = "User_Account name is required...!") String account_name,
        @NotBlank(message = "Phone number is required...!") String phone_number,
        @NotBlank(message = "User_Account type no is required...!") AccountType account_type
        ) {
}
