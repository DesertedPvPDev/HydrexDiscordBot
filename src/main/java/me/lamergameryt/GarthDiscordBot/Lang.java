package me.lamergameryt.GarthDiscordBot;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class Lang {
    private CommandEvent event;
    public Lang(CommandEvent event) {
        this.event = event;
    }

    private static final String PURGE_TITLE = "Moderation - Purge";
    public MessageEmbed purgeCommandNumberNotSpecified() {
        return addRequested(new EmbedBuilder().setTitle(PURGE_TITLE).setDescription("Please make sure you specify an amount of message to purge").setColor(Color.RED));
    }

    public MessageEmbed purgeCommandNumberBoundsWrongMessage() {
        return addRequested(new EmbedBuilder().setTitle(PURGE_TITLE).setDescription("Please make sure you have specified a number more than 2 and less than 100!").setColor(Color.RED));
    }

    public MessageEmbed purgeCommandSuccessMessage(int number) {
        return addRequested(new EmbedBuilder().setTitle(PURGE_TITLE).setDescription(number + " of messages of this channel were purged by: " + event.getAuthor().getName()).setColor(Color.CYAN));
    }

    public MessageEmbed purgeNumberInvalidMessage(String number) {
        return addRequested(new EmbedBuilder().setTitle(PURGE_TITLE).setDescription("`" + number + "` is not a valid number. Please make sure you specify a **NUMBER**!").setColor(Color.RED));
    }

    public MessageEmbed purgeCommandLogMessage(int number) {
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        return addExecuted(new EmbedBuilder().setTitle(PURGE_TITLE).setDescription("**Purge Count:** " + number + " messages\n**Channel:** #" + event.getTextChannel().getName() + "\n**Time:** " + new SimpleDateFormat("MM/dd/yy hh:mm a").format(new Date()) + " EST").setColor(Color.ORANGE));
    }

    public MessageEmbed addRequested(EmbedBuilder embed) {
        return embed.setFooter("Requested by " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).setTimestamp(Instant.now()).build();
    }

    public MessageEmbed addExecuted(EmbedBuilder embed) {
        return embed.setFooter("Executed by " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).setTimestamp(Instant.now()).build();
    }
}
