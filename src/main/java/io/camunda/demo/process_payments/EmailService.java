package io.camunda.demo.process_payments;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.Console;

@Service
public class EmailService {

    private final static Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @JobWorker(type = "send-email")
    public void sendEmail(
            @Variable(name = "employee_email") String employeeEmail,
            @Variable(name = "vacations_approved") Boolean vacationsApproved,
            @Variable(name = "rejection_reason") String rejectionReason
    ) {
        LOG.info("Sending email to {}", employeeEmail);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@codexdigital.pt");
        message.setTo(employeeEmail);
        LOG.info("vacations approved: {}", vacationsApproved);
        String subject;
        String body;

        if (vacationsApproved) {
            subject = "Vacations Approved";
            body = "Vacations Approved";
        } else {
            subject = "Vacations Rejected";
            body = "Vacations rejected\n\nRejection reason: " + rejectionReason;
        }

        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}

