package org.example;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Message;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class bot extends ListenerAdapter {
    //reader
    public static List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    public static void readAllDataAtOnce(String file)
    {
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] searchcsv(String searchWord) {
        try {
            System.out.println("Trying to search");
            CSVReader reader = new CSVReader(new FileReader("/Users/michaelchen/Downloads/forreal-master/src/main/java/output.csv"));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) { //while the next line is not null
                for (String cell : nextLine) {
                    if (cell.toLowerCase().contains(searchWord.toLowerCase())) {
                        System.out.println("Found" + cell);
                        return nextLine;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new String[]{"No match found."};
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String mypath = "/Users/michaelchen/Downloads/forreal-master/src/main/java/output.csv";
        System.out.println("test");
        if (event.getAuthor().isBot())
            return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g.
        // console view (strip discord formatting)
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Message recieved. Trying to read.").queue();
        channel.sendMessage("trying to search csv").queue();
        String[] toJoin = searchcsv(content);
        channel.sendMessage("trying to join string array").queue();
        String toSend = String.join(",", toJoin);
        channel.sendMessage("trying to send joined string").queue();
        channel.sendMessage(toSend).queue();
    }
}
