import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import java.util.Timer;
import java.util.TimerTask;

public class DiscordBot extends ListenerAdapter {

    Control control = new Control();
    Timer timer;
    MessageChannel channel;

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("PUT_YOUR_TOKEN_HERE").build();
        // You can also add event listeners to the already built JDA instance
        // Note that some events may not be received if the listener is added after
        // calling build()
        // This includes events such as the ReadyEvent
        jda.addEventListener(new DiscordBot());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("start")) {
            channel = event.getChannel();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    channel.sendMessage(control.calculate()).queue();
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 100, 2000);

        }

        if (msg.getContentRaw().equals("stop")) {
            timer.cancel();
            control = new Control();
        }
    }
}
