package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BotCommandHandler {
    private final UserService userService;
    private final EventService eventService;
    private List<User> users = new ArrayList<>();

    public BotCommandHandler(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    public SendMessage handleStartCommand(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Assalomu alaykum, " + userName + "! Tadbirlar botiga xush kelibsiz.");

        if (!userService.isUserRegistered(chatId)) {
            userService.registerUser((int) chatId, userName);
        }

        return message;
    }

    public SendMessage handleRegisterCommand(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        if (userService.isUserRegistered(chatId)) {
            message.setText("Siz allaqachon ro‘yxatdan o‘tgansiz!");
        } else {
            userService.registerUser((int) chatId, userName);
            message.setText("Siz muvaffaqiyatli ro‘yxatdan o‘tdingiz!");
        }

        return message;
    }

    public SendMessage handleEventsCommand(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(eventService.getEventsList());
        return message;
    }

    public SendMessage handleAddEventCommand(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        if (!userService.isAdmin(chatId)) {
            message.setText("Siz admin emassiz, tadbir qo‘shish imkoniyatingiz yo‘q.");
            return message;
        }

        String[] parts = messageText.split(" ", 4);
        if (parts.length < 4) {
            message.setText("Tadbir qo‘shish uchun format: /add_event [Nomi] [Tavsif] [Sanasi]");
        } else {

            eventService.addEvent(1,"Humo","Nimadir", String.valueOf(LocalDateTime.now()));
            message.setText("Yangi tadbir qo‘shildi: ");
        }

        return message;
    }
    public boolean isAdmin(long chatId) {
        for (User user : users) {
            if (user.getTelegramId().equals(String.valueOf(chatId)) && user.getRole() == Role.ADMIN) {
                return true;
            }
        }
        return false;
    }

    public SendMessage handleDeleteEventCommand(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));


        String[] parts = messageText.split(" ");
        if (parts.length < 2) {
            message.setText("Tadbirni o‘chirish uchun format: /delete_event [ID]");
        } else {
            int eventId = Integer.parseInt(parts[1]);
            eventService.deleteEvent((int) chatId);
            message.setText("Tadbir muvaffaqiyatli o‘chirildi!");
        }

        return message;
    }

    public SendMessage handleMyEventsCommand(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(eventService.getUserEvents(chatId));
        return message;
    }

}
