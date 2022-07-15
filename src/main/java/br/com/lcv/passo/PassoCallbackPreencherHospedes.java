package br.com.lcv.passo;

import br.com.lcv.hospede.Hospede;
import br.com.lcv.reserva.ReservaDTO;
import br.com.lcv.sessao.Sessao;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/08/19.
 */
@Component
public class PassoCallbackPreencherHospedes implements PassoCallback {

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        int indiceHospede = Integer.parseInt(valorCallback);

        ReservaDTO reservaDTO = sessao.getReservaDTO();
        List<Hospede> hospedes = reservaDTO.getHospedes();
        Hospede hospede = hospedes.get(indiceHospede);

        sessao.adicionaAtributoHospedeCorrente(hospede);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_NOME);

        mensagens.add(new Mensagem(new SendMessage(chatId, "Nome?")));

        return mensagens;
    }

}
