package co.istad.mbanking.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
@RequiredArgsConstructor
public class MailUntil {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Setter
    @Getter
    @Builder
    public static class Meta<T>{
        private String to ;
        private String from;
        private String subject;
        private String templateUrl;
        private T data;
    }
    public void send(Meta<?> meta) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        // 1. Prepare template
        Context context = new Context();
        context.setVariable("name",meta.data);
        String htmlTemplate = templateEngine.process(meta.templateUrl, context);
        helper.setText(htmlTemplate, true);

        // 2. Prepare email information
        helper.setTo(meta.to);
        helper.setFrom(meta.from);
        helper.setSubject(meta.subject);
        // 3. send mail
        javaMailSender.send(mimeMessage);
    }
}
