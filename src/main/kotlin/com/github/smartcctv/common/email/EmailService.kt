package com.github.smartcctv.common.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class EmailService {
    fun sendEmail(email: String, username: String, password: String) {
        val javaMailSender = getJavaMailSender(username, password)
        val mimeMessage = javaMailSender.createMimeMessage()

        val mimeMessageHelper = MimeMessageHelper(mimeMessage, false, "UTF-8")
        mimeMessageHelper.setTo(email) // 메일 수신자
        mimeMessageHelper.setSubject("detection notification") // 메일 제목
        mimeMessageHelper.setText(LocalDateTime.now().toString(), false) // 메일 본문 내용, HTML 여부

        // FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        // mimeMessageHelper.addAttachment("Invoice", file);
        javaMailSender.send(mimeMessage)
    }

    private fun getJavaMailSender(username: String, password: String): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587

        mailSender.username = username
        mailSender.password = password

        val props = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.debug"] = "true"

        return mailSender
    }
}
