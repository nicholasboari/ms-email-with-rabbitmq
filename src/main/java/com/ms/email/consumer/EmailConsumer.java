package com.ms.email.consumer;

import com.ms.email.dto.EmailRequestDTO;
import com.ms.email.model.Email;
import com.ms.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailRequestDTO emailRequestDTO){
        Email email = new Email();
        BeanUtils.copyProperties(emailRequestDTO, email);
        emailService.sendEmail(email);
        System.out.println("Email Status: " + email.getStatusEmail().toString());
    }
}
