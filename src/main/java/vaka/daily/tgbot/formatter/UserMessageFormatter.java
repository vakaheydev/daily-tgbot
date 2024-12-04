package vaka.daily.tgbot.formatter;

import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMessageFormatter implements MessageFormatter<User> {
    @Override
    public String format(User user) {
        StringBuilder msg = new StringBuilder();

        msg.append("\nВсе расписания:\n");

        List<Schedule> schedules = user.getSchedules();

        for (var schedule : schedules) {
            msg.append(f("<---- Расписание '%s': ---->\n", schedule.getName()));

            for (var task : schedule.getTasks()) {
                msg.append(f("\nЗадание '%s' | %s | до %s | %s",
                        task.getName(),
                        task.getDescription(),
                        task.getDeadline().toString(),
                        task.getStatus() ? "Сделано" : "Надо сделать"));
                msg.append("\n---\n");
            }
        }

        return msg.toString();
    }

    private static String f(String s, Object... args) {
        return String.format(s, args);
    }

}
