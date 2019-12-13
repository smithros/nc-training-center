package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.services.UserSettingsService;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping
public class UserSettingsController {
    private final UserSettingsService userSettingsService;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSettings")
    public ResponseEntity<?> getSettings(@RequestParam(name = "userId") int userId) {
        System.out.println("Trying to get settings!!!");
        UserSettings responce = userSettingsService.getUserSettings(userId);
        return Optional.ofNullable(responce).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/getSettings", method = RequestMethod.POST)
    public ResponseEntity<?> getUserSettings(String userId) {
        System.out.println(userId);
        UserSettings response = userSettingsService.getUserSettings(Integer.valueOf(userId));
        System.out.println(response.toString());
        return response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
    }


    @RequestMapping(value = "/updateSettings", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserSettings(String subscribeOnFriends, String achivements, String bookNotification, String subscribeOnFriendReview, String notifyAboutNewFriends, String notifyAboutAchievement, String userId) {
        UserSettings userSettings = new UserSettings();
        userSettings.setSubscribeOnFriends(Boolean.valueOf(subscribeOnFriends));
        userSettings.setAchievements(Boolean.valueOf(achivements));
        userSettings.setBookNotification(Boolean.valueOf(bookNotification));
        userSettings.setSubscribeOnFriendReview(Boolean.valueOf(subscribeOnFriendReview));
        userSettings.setNotifyAboutNewFriends(Boolean.valueOf(notifyAboutNewFriends));
        userSettings.setNotifyAboutAchievement(Boolean.valueOf(notifyAboutAchievement));
        userSettingsService.updateSettings(userSettings,Integer.valueOf(userId));
        return new ResponseEntity<>(HttpStatus.CHECKPOINT);
    }

}
