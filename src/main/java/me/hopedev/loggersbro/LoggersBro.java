package me.hopedev.loggersbro;

import me.hopedev.loggersbro.handlers.BotWebhookHandler;
import me.hopedev.loggersbro.handlers.GuildWebhookHandler;
import me.hopedev.topggwebhooks.Webhook;
import me.hopedev.topggwebhooks.WebhookBuilder;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.IOException;

public class LoggersBro {

    private static DiscordApi api;
    private static Webhook webhook;

    public static String announcementChannel = "803597231068151818";

    public static void main(String[] args) {

        // Login to Discord
        api = new DiscordApiBuilder().setToken(args[0]).login().join();
        // Send Bot Invite
        System.out.println("Logged in successfully! Bot Invite: "+api.createBotInvite());


        ///// Webhook Setup /////

        webhook = new WebhookBuilder()

                .addBotListener("BotWebhook", new BotWebhookHandler(), "SecretAuthKey") // adding Bot-Webhooks
                .addGuildListener("GuildWebhook", new GuildWebhookHandler(), "SecretAuthKey") // adding Guild-Webhooks
                .setPort(6969) // set to port 6969
                .build();

        try {
            // Starting webhook
            webhook.start();
            System.out.println("Voting webhook was started successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static DiscordApi getApi() {
        return api;
    }
}
