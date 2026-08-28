package com.marcandohuellitas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String token) {
        String subject = "Bienvenido a Marcando Huellitas - Verifica tu cuenta";
        String link = "http://18.224.7.10.nip.io/src/pages/auth/verify.html?token=" + token;
        
        String body = "<html><body style='font-family: Arial, sans-serif; text-align: center; color: #333;'>"
                + "<h1 style='color: #f2b58f;'>¡Hola, amante de los animales!</h1>"
                + "<p>Gracias por unirte a la comunidad de Marcando Huellitas. Para poder adoptar o comprar en nuestra tienda, necesitamos verificar tu correo electrónico.</p>"
                + "<a href='" + link + "' style='display: inline-block; padding: 10px 20px; margin: 20px 0; background-color: #f2b58f; color: white; text-decoration: none; border-radius: 5px; font-weight: bold;'>Verificar mi cuenta</a>"
                + "<p>O copia y pega el siguiente enlace en tu navegador:</p>"
                + "<p><a href='" + link + "'>" + link + "</a></p>"
                + "<p>Si tú no solicitaste este registro, por favor ignora este correo.</p>"
                + "<p>Con cariño,<br>El equipo de Marcando Huellitas 🐾</p>"
                + "</body></html>";
                
        sendHtmlEmail(to, subject, body);
    }

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Marcando Huellitas - Recuperación de contraseña";
        String link = "http://18.224.7.10.nip.io/src/pages/auth/reset-password.html?token=" + token;
        
        String body = "<html><body style='font-family: Arial, sans-serif; text-align: center; color: #333;'>"
                + "<h1 style='color: #f2b58f;'>Recuperación de contraseña</h1>"
                + "<p>Hemos recibido una solicitud para cambiar tu contraseña. Haz clic en el botón de abajo para crear una nueva contraseña.</p>"
                + "<a href='" + link + "' style='display: inline-block; padding: 10px 20px; margin: 20px 0; background-color: #f2b58f; color: white; text-decoration: none; border-radius: 5px; font-weight: bold;'>Restablecer mi contraseña</a>"
                + "<p>Si tú no solicitaste este cambio, puedes ignorar este correo de forma segura.</p>"
                + "</body></html>";
                
        sendHtmlEmail(to, subject, body);
    }

    private void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Error enviando correo a " + to + ": " + e.getMessage());
        }
    }
}
