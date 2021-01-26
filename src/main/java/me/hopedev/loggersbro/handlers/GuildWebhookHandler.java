package me.hopedev.loggersbro.handlers;

import me.hopedev.loggersbro.LoggersBro;
import me.hopedev.topggwebhooks.bots.BotVoteData;
import me.hopedev.topggwebhooks.enums.VotingType;
import me.hopedev.topggwebhooks.servers.GuildVoteData;
import me.hopedev.topggwebhooks.servers.GuildWebhookEvent;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;
import me.hopedev.topggwebhooks.utils.Query;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class GuildWebhookHandler implements GuildWebhookListener {

    @Override
    public void onWebhookRequest(GuildWebhookEvent event) {
        DiscordApi api = LoggersBro.getApi();
        GuildVoteData voteData = event.getVote();


        // Query Parser thing
        Query query = voteData.getQuery();

        ServerTextChannel channel = api.getServerTextChannelById(LoggersBro.announcementChannel).get();

        EmbedBuilder eb = new EmbedBuilder()
                .setImage("https://i.imgur.com/T13kvPO.jpeg")
                .setTitle("Wow! A Vote for our Server!")
                .setDescription("Thank you so much for Voting for our Guild "+api.getServerById(voteData.getGuildID()).get().getName())
                .addField("User", "("+voteData.getUserID()+") "+api.getUserById(voteData.getUserID()).join().getMentionTag())
                .addField("for the Server ID", voteData.getGuildID().toString())
                .addField("What kind of vote?", voteData.getType().equals(VotingType.TEST) ? "It was a test, no need for panic!"
                        : voteData.getType().equals(VotingType.UPVOTE) ? "Thank you for your geniune vote!" : "Wow I'm confused i think.")

                .addField("Additionally..", query.toString().startsWith("notification") ? "They voted through our Notifications! we appreciate it" : "Nothing.. nevermind");

        channel.sendMessage(eb);

        // https://i.imgur.com/TC7j2r4.png
    }
}
