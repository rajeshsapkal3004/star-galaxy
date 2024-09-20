package com.cartwheel.galaxy.service.impl;

import com.cartwheel.galaxy.dto.EmailBody;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendMail(EmailBody emailBody){
        String logoImageUrl = "https://img.freepik.com/free-vector/gradient-galaxy-logo-template_23-2149203103.jpg?w=740&t=st=1705334907~exp=1705335507~hmac=3d552495ddc3ef1972503328a9e22891489a60a21a0b4209912ad675605f3177";

        boolean flag=false;
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("sapkalrajesh3004@gmail.com");
            helper.setTo(emailBody.getTo());
            helper.setSubject(emailBody.getSubject());
           // helper.setText(emailBody.getMessage());
            String verificationLink = emailBody.getMessage();
            String htmlContent = "<html><body>"
                    + "<h2>Let’s get you started</h2><br>"
                    + "<img src='" + logoImageUrl + "' alt='Star-Galaxy logo' style='width:100px; height:100px;'>"
                    + "<p>Hi " + "User" + ",</p>"
                    + "<p>Click to confirm your Star-Galaxy registration!</p>"
                    + "<a href='" + verificationLink + "' style='"
                    + "background-color: #4CAF50; /* Green */"
                    + "border: none;"
                    + "color: white;"
                    + "padding: 15px 32px;"
                    + "text-align: center;"
                    + "text-decoration: none;"
                    + "display: inline-block;"
                    + "font-size: 16px;"
                    + "margin: 4px 2px;"
                    + "cursor: pointer;"
                    + "border-radius: 8px;'>Verify Email</a><br>"
                    + "<p>Let Star-Galaxy coach you through the hiring process at Star-Galaxy.</p>"
                    + "<ul>"
                    + "<li>Stand out from the competition</li>"
                    + "<li>Get automated progress updates</li>"
                    + "<li>Discover matching jobs & insights</li>"
                    + "</ul>"
                    + "<p>Your friends at Star-Galaxy</p>"
                    + "<p>If you received this email by mistake, please disregard it.</p>"
                    + "<p>This is an automated message from the Star-Galaxy team, you are receiving this as per Star-Galaxy Terms & Conditions.</p>"
                    + "</body></html>";


            helper.setText(htmlContent, true);

            javaMailSender.send(message);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
