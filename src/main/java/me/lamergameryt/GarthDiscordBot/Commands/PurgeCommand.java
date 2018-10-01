package me.lamergameryt.GarthDiscordBot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.lamergameryt.GarthDiscordBot.Config;
import me.lamergameryt.GarthDiscordBot.Lang;
import net.dv8tion.jda.core.Permission;

import java.util.concurrent.TimeUnit;

public class PurgeCommand extends Command {
    public PurgeCommand() {
        super.name = "purge";
    }

    @Override
    protected void execute(CommandEvent event) {
        Lang lang = new Lang(event);
        event.getMessage().delete().queue();
        if (!event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            return;
        }

        if (event.getMessage().getContentRaw().trim().split(" ").length < 2) {
            event.getTextChannel().sendMessage(lang.purgeCommandNumberNotSpecified()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
            return;
        }

        int toPurge;
        try {
            toPurge = Integer.parseInt(event.getArgs());
        } catch (NumberFormatException e) {
            event.getTextChannel().sendMessage(lang.purgeNumberInvalidMessage(event.getArgs())).complete().delete().queueAfter(3, TimeUnit.SECONDS);
            return;
        }

        if (toPurge < 3 || toPurge > 100) {
            event.getTextChannel().sendMessage(lang.purgeCommandNumberBoundsWrongMessage()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
            return;
        }

        event.getTextChannel().deleteMessages(event.getTextChannel().getHistory().retrievePast(toPurge).complete()).queue();
        event.getTextChannel().sendMessage(lang.purgeCommandSuccessMessage(toPurge)).complete().delete().queueAfter(3, TimeUnit.SECONDS);
        event.getGuild().getTextChannelsByName(Config.LOG_CHANNEL, true).get(0).sendMessage(lang.purgeCommandLogMessage(toPurge)).queue();

    }
}
