package dev.gomorrha.adventcalendar.tools;

import dev.gomorrha.adventcalendar.AdventCalendar;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class getTime {
    AdventCalendar main = AdventCalendar.getInstance();
    FileConfiguration config = main.getConfig();

    public Integer getMonth(){
        ZoneId Zone = ZoneId.of(Objects.requireNonNull(config.getString("TimeZone")));
        LocalDateTime now = LocalDateTime.now(Zone);

        return now.getMonthValue();
    }

    public Integer getDay(){
        ZoneId Zone = ZoneId.of(Objects.requireNonNull(config.getString("TimeZone")));
        LocalDateTime now = LocalDateTime.now(Zone);

        return now.getDayOfMonth();
    }

    public String getInfo(){
        ZoneId Zone = ZoneId.of(Objects.requireNonNull(config.getString("TimeZone")));
        LocalDateTime now = LocalDateTime.now(Zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.");

        return now.format(formatter);
    }

}
