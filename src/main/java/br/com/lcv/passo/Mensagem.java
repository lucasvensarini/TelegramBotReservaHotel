package br.com.lcv.passo;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;

/**
 * Created by lucas on 30/08/19.
 */
public class Mensagem {

    private BotApiMethod botApiMethod;
    private SendMediaGroup sendMediaGroup;

    Mensagem(BotApiMethod botApiMethod) {
        this.botApiMethod = botApiMethod;
    }

    Mensagem(SendMediaGroup sendMediaGroup) {
        this.sendMediaGroup = sendMediaGroup;
    }

    public BotApiMethod getBotApiMethod() {
        return botApiMethod;
    }

    public SendMediaGroup getSendMediaGroup() {
        return sendMediaGroup;
    }

    public boolean temBotApiMethod() {
        return getBotApiMethod() != null;
    }

    public boolean temMediaGroup() {
        return getSendMediaGroup() != null;
    }

}
