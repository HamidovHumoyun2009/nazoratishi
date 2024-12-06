package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {

    private final UserService userService;
    private final EventService eventService;
    private final BotCommandHandler botCommandHandler;

    public MyBot() {
        this.userService = new UserService();
        this.eventService = new EventService();
        this.botCommandHandler = new BotCommandHandler(userService, eventService);

        // Adminni qo'shish
        userService.addAdmin(684349666, "Admin User");
    }

    @Override
    public String getBotUsername() {
        return "@ChatGptedfsdfds_bot";
    }

    @Override
    public String getBotToken() {
        return "7615080251:AAGziG5DBD4rD2DUV9gPy9TRzen5UocTeSM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String userName = update.getMessage().getFrom().getFirstName();

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));

            if (messageText.equals("/start")) {
                System.out.println(update.getMessage().getChatId());
                message = botCommandHandler.handleStartCommand(chatId, userName);
            } else if (messageText.equals("/register")) {
                message = botCommandHandler.handleRegisterCommand(chatId, userName);
            } else if (messageText.equals("/events")) {
                message = botCommandHandler.handleEventsCommand(chatId);
            } else if (messageText.startsWith("/add_event")) {
                message = botCommandHandler.handleAddEventCommand(chatId, messageText);
            } else if (messageText.startsWith("/delete_event")) {
                message = botCommandHandler.handleDeleteEventCommand(chatId, messageText);
            } else if (messageText.equals("/my_events")) {
                message = botCommandHandler.handleMyEventsCommand(chatId);
            } else {
                message.setText("Noto‘g‘ri buyruq. Iltimos, quyidagi buyruqlardan birini kiriting:\n" +
                        "/start\n/register\n/events\n/my_events\n/add_event\n/delete_event");
            }

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
