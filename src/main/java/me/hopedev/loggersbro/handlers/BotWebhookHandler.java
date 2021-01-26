package me.hopedev.loggersbro.handlers;

import me.hopedev.loggersbro.LoggersBro;
import me.hopedev.topggwebhooks.bots.BotVoteData;
import me.hopedev.topggwebhooks.bots.BotWebhookEvent;
import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.enums.VotingType;
import me.hopedev.topggwebhooks.utils.Query;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class BotWebhookHandler implements BotWebhookListener {

    @Override
    public void onWebhookRequest(BotWebhookEvent event) {
        DiscordApi api = LoggersBro.getApi();
        BotVoteData voteData = event.getVote();


        // Query Parser thing
        Query query = voteData.getQuery();

        ServerTextChannel channel = api.getServerTextChannelById(LoggersBro.announcementChannel).get();

        EmbedBuilder eb = new EmbedBuilder()
                .setImage("https://i.imgur.com/T13kvPO.jpeg")
                .setTitle("Wow! A Vote for our Bot!")
                .setDescription("Thank you so much for Voting for our bot "+api.getYourself().getName())
                .addField("User", "("+voteData.getUserID()+") "+api.getUserById(voteData.getUserID()).join().getMentionTag())
                .addField("for the Bot ID", voteData.getBotID().toString())
                .addField("Anything special?", voteData.isWeekend() ? "WOW! IT'S DOUBLE VOTE WEEKEND!!! DOUBLE REWARDS!" : "Nothing special for today, try during the weekend")
                .addField("What kind of vote?", voteData.getType().equals(VotingType.TEST) ? "It was a test, no need for panic!"
                        : voteData.getType().equals(VotingType.UPVOTE) ? "Thank you for your geniune vote!" : "Wow I'm confused i think.")

                .addField("Additionally..", query.toString().startsWith("notification") ? "They voted through our Notifications! we appreciate it" : "Nothing.. nevermind");

        channel.sendMessage(eb);

        // https://i.imgur.com/8EBX5Yv.png

    }
}
