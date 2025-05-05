package com.example.notification_service.service;


import com.example.order_service.event.OrderEvent;
import com.example.notification_service.entity.Notification;
import com.example.notification_service.repository.NotificationRepository;
import com.example.payment_service.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleOrderEvent(OrderEvent orderEvent) {
        if ("ORDER_CREATED".equals(orderEvent.getEventType())) {
            try {
                // Préparer le contenu de l'e-mail
                String subject = "Confirmation de commande #" + orderEvent.getOrder();
                String content = "Bonjour,\n\n"
                        + "Votre commande #" + orderEvent.getOrder() + " a été confirmée.\n"
                        + "Nous vous tiendrons informé de son avancement.\n\n"
                        + "Cordialement,\nL'équipe du service client";

                // Envoyer l'e-mail
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(orderEvent.getCustomerEmail());
                message.setSubject(subject);
                message.setText(content);
                mailSender.send(message);

                // Sauvegarder la notification
                Notification notification = new Notification();
                notification.setOrderId(orderEvent.getOrder());
                notification.setCustomerEmail(orderEvent.getCustomerEmail());
                notification.setMessage("Order confirmation sent");
                notification.setSentAt(LocalDateTime.now());
                notification.setSentSuccessfully(true);

                notificationRepository.save(notification);

            } catch (Exception e) {
                // Gestion d'erreur et sauvegarde du statut d'échec
                Notification failedNotification = new Notification();
                failedNotification.setOrderId(orderEvent.getOrder());
                failedNotification.setCustomerEmail(orderEvent.getCustomerEmail());
                failedNotification.setMessage("Failed to send notification: " + e.getMessage());
                failedNotification.setSentAt(LocalDateTime.now());
                failedNotification.setSentSuccessfully(false);

                notificationRepository.save(failedNotification);
            }
        }
    }

    @KafkaListener(topics = "payment-events", groupId = "notification-group")
    public void handlePaymentEvent(PaymentEvent paymentEvent) {
        if ("PAYMENT_SUCCESS".equals(paymentEvent.getEventType())) {
            try {
                // Préparer le contenu de l'e-mail
                String subject = "Paiement reçu pour la commande #" + paymentEvent.getOrderId();
                String content = "Bonjour,\n\n"
                        + "Nous avons bien reçu votre paiement pour la commande #" + paymentEvent.getOrderId() + ".\n"
                        + "Montant : " + paymentEvent.getPaymentId() + "\n"
                        + "Merci pour votre confiance.\n\n"
                        + "Cordialement,\nL'équipe de facturation";

                // Envoyer l'e-mail
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(paymentEvent.getCustomerEmail());
                message.setSubject(subject);
                message.setText(content);
                mailSender.send(message);

                // Sauvegarder la notification
                Notification notification = new Notification();
                notification.setOrderId(paymentEvent.getOrderId());
                notification.setCustomerEmail(paymentEvent.getCustomerEmail());
                notification.setMessage("Payment confirmation sent");
                notification.setSentAt(LocalDateTime.now());
                notification.setSentSuccessfully(true);

                notificationRepository.save(notification);

            } catch (Exception e) {
                // Gestion d'erreur et sauvegarde du statut d'échec
                Notification failedNotification = new Notification();
                failedNotification.setOrderId(paymentEvent.getOrderId());
                failedNotification.setCustomerEmail(paymentEvent.getCustomerEmail());
                failedNotification.setMessage("Failed to send payment notification: " + e.getMessage());
                failedNotification.setSentAt(LocalDateTime.now());
                failedNotification.setSentSuccessfully(false);

                notificationRepository.save(failedNotification);
            }
        }
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

}
