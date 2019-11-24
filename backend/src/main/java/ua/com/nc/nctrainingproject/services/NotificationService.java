package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Notification;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.NotificationPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.List;

@Service
public class NotificationService {

	private final NotificationPostgreDAO notificationPostgreDAO;
	private final UserPostgreDAO userPostgreDAO;

	@Autowired
	public NotificationService(NotificationPostgreDAO notificationPostgreDAO, UserPostgreDAO userPostgreDAO) {
		this.notificationPostgreDAO = notificationPostgreDAO;
		this.userPostgreDAO = userPostgreDAO;
	}

	public List<Notification> getAllNotifications() {
		return notificationPostgreDAO.getAllNotifications();
	}

	public void createNotification(Notification notification) {
		if (userPostgreDAO.getUserById(notification.getUserId()) != null &&
				notificationPostgreDAO.getNotificationByActionID(notification.getActionId()) != null) {
			notificationPostgreDAO.createNotification(notification);
		}
	}

	public void deleteNotification(int userId, int actionId) {
		if (userPostgreDAO.getUserById(userId) != null &&
				notificationPostgreDAO.getNotificationByActionID(actionId) != null) {
			notificationPostgreDAO.deleteNotification(userId, actionId);
		}
	}

	public Notification getNotificationByActionID(int actionId) {
		return notificationPostgreDAO.getNotificationByActionID(actionId);
	}

	public Notification getNotificationByUserID(int userId) {
		return notificationPostgreDAO.getNotificationByUserID(userId);
	}

	public Notification getNotificationByUserActionID(int userId, int actionId) {
		return notificationPostgreDAO.getNotificationByUserActionID(userId, actionId);
	}

}