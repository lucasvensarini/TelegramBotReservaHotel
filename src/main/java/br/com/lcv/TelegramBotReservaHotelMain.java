package br.com.lcv;

import br.com.lcv.bot.BotInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

/**
 * Created by lucas on 25/06/19.
 */
@SpringBootApplication
public class TelegramBotReservaHotelMain {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotReservaHotelMain.class, args);
    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(botInterface());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public BotInterface botInterface() {
        return new BotInterface();
    }

}
