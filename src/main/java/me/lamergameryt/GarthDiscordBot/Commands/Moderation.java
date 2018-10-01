package me.lamergameryt.GarthDiscordBot.Commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Moderation extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getChannel().getName().equalsIgnoreCase("showcase")) {
            return;
        }

        String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(event.getMessage().getContentRaw().trim());
        if(m.find()) {
            event.getMessage().delete().queueAfter(500, TimeUnit.MILLISECONDS);
            event.getChannel().sendMessage("Please do not send links here.").complete().delete().queueAfter(2, TimeUnit.SECONDS);
        }
    }
}
