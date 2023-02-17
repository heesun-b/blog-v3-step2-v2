package shop.mtcoding.blogv3.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv3.dto.MailResDto;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.User;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HttpSession session;

    public void sendMail(MailResDto mailResDto) throws MessagingException {
        User principal = (User) session.getAttribute("principal");

        if (!principal.getRoll().equals("ADMIN")) {
            throw new CustomException("메일 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper mHelper = new MimeMessageHelper(mail, true, "UTF-8");
        mHelper.setTo(mailResDto.getAddress());
        mHelper.setFrom("heesun9793@naver.com");
        mHelper.setSubject(mailResDto.getTitle());
        mHelper.setText(mailResDto.getMessage(), true);

        mailSender.send(mail);
    }
}
