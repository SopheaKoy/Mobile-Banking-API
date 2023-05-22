package co.istad.mbanking.api.auth;

import co.istad.mbanking.api.auth.web.AuthDto;
import co.istad.mbanking.api.auth.web.LogInDto;
import co.istad.mbanking.api.auth.web.RegisterDto;
import co.istad.mbanking.api.user.User;
import co.istad.mbanking.api.user.UserMapStruct;
import co.istad.mbanking.util.MailUntil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final PasswordEncoder encoder;
    private final MailUntil mailUntil;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void register(RegisterDto registerDto) {
        User user = userMapStruct.registerUserDtoUser(registerDto);
        user.setIsVerified(false);
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getEmail());
        if(authMapper.register(user)){
            for (Integer role : registerDto.roleIds()){
                authMapper.createUserRole(user.getId(), role);
            }
        }
    }

    @Override
    public void verify(String email) {
        User user = authMapper.selectByEmail(email).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND , "Email has not been found"));
        String verifiedCode=UUID.randomUUID().toString();
        user.setVerifiedCode(verifiedCode);
        if(authMapper.updateVerifiedCode(email,verifiedCode)){
            user.setVerifiedCode(verifiedCode);
        }else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "email can not be verify");
        }
        MailUntil.Meta<?> meta = MailUntil.Meta.builder()
                .to(email)
                .from(from)
                .subject("Account Verification.")
                .templateUrl("auth/verify")
                .data(user)
                .build();

        try {
            mailUntil.send(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR ,
                    e.getMessage());
        }
    }

    @Override
    public void checkVerify(String email, String code) {
        User user  = authMapper.selectByEmailAndVerifiedCode(email,code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found."));
        if (!user.getIsVerified()){
            authMapper.updateIsVerifyStatus(email,code);
        }
    }

    @Override
    public AuthDto login(LogInDto loginDto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.email() ,loginDto.password());
        authentication = daoAuthenticationProvider.authenticate(authentication);

        log.info("Authentication {}" ,authentication);
        log.info("Authentication {}" ,authentication.getName());
        log.info("Authentication {}" ,authentication.getCredentials());

        String basicAuthFormat = authentication.getName() +":"+authentication.getCredentials();
        String encoding = Base64.getEncoder().encodeToString(basicAuthFormat.getBytes());

        log.info("Base {}" , encoding);

        return new AuthDto(String.format("Basic %s",encoding));
    }

}