package me.lamergameryt.GarthDiscordBot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.lamergameryt.GarthDiscordBot.Commands.Moderation;
import me.lamergameryt.GarthDiscordBot.Commands.PurgeCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class DBMain extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandClientBuilder ccb = new CommandClientBuilder();
        ccb.setPrefix("-");
        ccb.setOwnerId("237003475823624213");
        ccb.setCoOwnerIds("196301270439559168", "287888563020890138");
        ccb.useHelpBuilder(false);
        ccb.setGame(Game.watching("-help | Over Hydrex"));
        ccb.addCommands(new PurgeCommand());

        try {
            new JDABuilder(AccountType.BOT).setToken("Removed bot token for security").addEventListener(ccb.build(), new Moderation()).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
