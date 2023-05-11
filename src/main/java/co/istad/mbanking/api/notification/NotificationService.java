package co.istad.mbanking.api.notification;

import co.istad.mbanking.api.notification.web.CreateNotificationDto;

public interface NotificationService {
    boolean pushNotification(CreateNotificationDto notificationDto);
}
