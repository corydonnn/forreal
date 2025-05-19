package org.example;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.example.bot.readAllDataAtOnce;

public class Main {

    public static void main(String[] arguments) throws Exception {
        String token = ""; //very secure token
        JDA api = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new bot())
                .build().awaitReady();
        readAllDataAtOnce("C:\\Users\\gabet\\IdeaProjects\\forreal\\src\\main\\java\\output.csv");
    }

}