package br.com.lcv.bot;

import br.com.lcv.hospede.FaixaEtaria;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.hospede.Sexo;
import br.com.lcv.hotel.Classificacao;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.Quarto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lucas on 19/07/19.
 */
@Service
public class KeyboardService {

    public static final String PREFIXO_CALLBACK_CONFIRMACAO_NOME_HOTEL = "confirmacaoNomeHotel";
    public static final String PREFIXO_CALLBACK_NAO_SABE_NOME_HOTEL = "NaoSabeNomeHotel";
    public static final String PREFIXO_CALLBACK_HOSPEDES_ADT = "hospedesADT";
    public static final String PREFIXO_CALLBACK_HOSPEDES_CHD = "hospedesCHD";
    public static final String PREFIXO_CALLBACK_HOSPEDES_INF = "hospedesINF";
    public static final String PREFIXO_CALLBACK_CLASSIFICAO_HOTEL = "classificacaoHotel";
    public static final String PREFIXO_CALLBACK_HOTEIS = "hoteis";
    public static final String PREFIXO_CALLBACK_MAIS_INFO_HOTEL = "maisInfoHotel";
    public static final String PREFIXO_CALLBACK_QUARTOS = "quartos";
    public static final String PREFIXO_CALLBACK_MAIS_INFO_QUARTO = "maisInfoQuarto";
    public static final String PREFIXO_CALLBACK_QUARTO_SELECIONADO = "quartoSelecionado";
    public static final String PREFIXO_CALLBACK_PREENCHER_HOSPEDES = "preencherHospedes";
    public static final String PREFIXO_CALLBACK_SEXO_HOSPEDE = "sexoHospede";
    public static final String PREFIXO_CALLBACK_LISTAR_DADOS_PARA_ALTERACAO = "listarDadosParaAlteracao";
    public static final String PREFIXO_CALLBACK_MODIFICAR_DADOS_HOSPEDES = "modificarDadosHospedes";
    public static final String PREFIXO_CALLBACK_SAIR_MODIFICACAO_DADOS_HOSPEDES = "sairModificarDadosHospedes";
    public static final String PREFIXO_CALLBACK_CONFIRMACAO_HOSPEDES = "confirmacaoHospedes";
    public static final String PREFIXO_CALLBACK_CONFIRMACAO_INFORMACOES_RESERVA = "confirmacaoInformacoesReserva";
    public static final String PREFIXO_CALLBACK_CONFIRMACAO_RESPONSAVEL = "confirmacaoResponsavelsReserva";
    public static final String PREFIXO_CALLBACK_VOLTAR_HOTEIS = "voltarHoteis";
    public static final String PREFIXO_CALLBACK_VOLTAR_QUARTOS = "voltarQuartos";
    public static final String PREFIXO_CALLBACK_VOLTAR_PREENCHER_HOSPEDES = "voltarPreencherHospedes";
    public static final String PREFIXO_CALLBACK_VOLTAR_INICIO = "voltarInicio";
    static final String CALLBACK_DELIMITADOR = ":";

    InlineKeyboardMarkup montaConfirmacaoNomeHotelKeyboard() {
        String callbackSim = PREFIXO_CALLBACK_CONFIRMACAO_NOME_HOTEL + CALLBACK_DELIMITADOR;
        String callbackVoltar = PREFIXO_CALLBACK_NAO_SABE_NOME_HOTEL + CALLBACK_DELIMITADOR;

        return montaDuasOpcoesKeyboard(callbackSim, callbackVoltar, "Sim", "Não sei");
    }

    public InlineKeyboardMarkup montaQtdHospedesKeyboard(String prefixo) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        List<InlineKeyboardButton> linha1 = new ArrayList<>();
        List<InlineKeyboardButton> linha2 = new ArrayList<>();
        List<InlineKeyboardButton> linha3 = new ArrayList<>();

        int numeroMinimoHospedes = 0;
        if (PREFIXO_CALLBACK_HOSPEDES_ADT.equals(prefixo)) {
            numeroMinimoHospedes = 1;
        }

        String prefixoCallback = prefixo + CALLBACK_DELIMITADOR;

        for (int i = numeroMinimoHospedes; i < 10; i++) {
            String textoBotao = String.valueOf(i);
            if (i < 4) {
                linha1.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallback + textoBotao));
            } else if (i < 7) {
                linha2.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallback + textoBotao));
            } else {
                linha3.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallback + textoBotao));
            }
        }

        teclado.add(linha1);
        teclado.add(linha2);
        teclado.add(linha3);

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaClassificacaoHotelKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        String prefixoCallback = PREFIXO_CALLBACK_CLASSIFICAO_HOTEL + CALLBACK_DELIMITADOR;

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();
        for (Classificacao classificacao : Classificacao.values()) {
            String textoBotao = classificacao.getTexto();
            if (classificacao.equals(Classificacao.UNDEF)) {
                textoBotao = "Não, quero ver todos os resultados";
            }
            List<InlineKeyboardButton> linha = new ArrayList<>();
            linha.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallback + classificacao.getValor()));

            teclado.add(linha);
        }

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaListaHoteisKeyboard(List<Hotel> hoteis) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        String prefixoCallback = PREFIXO_CALLBACK_HOTEIS + CALLBACK_DELIMITADOR;

        for (Hotel hotel : hoteis) {
            String textoBotao = hotel.getNome();
            Long hotelId = hotel.getId();

            List<InlineKeyboardButton> linha = new ArrayList<>();
            linha.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallback + hotelId));

            teclado.add(linha);
        }

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaMaisInfoListaQuartosHotelKeyboard(List<Quarto> quartos) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        String prefixoCallbackMaisInfo = PREFIXO_CALLBACK_MAIS_INFO_HOTEL + CALLBACK_DELIMITADOR;
        String prefixoCallback = PREFIXO_CALLBACK_QUARTOS + CALLBACK_DELIMITADOR;
        String prefixoCallbackVoltar = PREFIXO_CALLBACK_VOLTAR_HOTEIS + CALLBACK_DELIMITADOR;

        List<InlineKeyboardButton> linhaMaisInfoHotel = new ArrayList<>();
        linhaMaisInfoHotel.add(new InlineKeyboardButton("Ver mais informações").setCallbackData(prefixoCallbackMaisInfo));

        teclado.add(linhaMaisInfoHotel);

        for (Quarto quarto : quartos) {
            String textoBotao = quarto.getTipoQuarto().getValor();
            Long quartoId = quarto.getId();

            List<InlineKeyboardButton> linha = new ArrayList<>();
            linha.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallback + quartoId));

            teclado.add(linha);
        }

        List<InlineKeyboardButton> linhaVoltar = new ArrayList<>();
        linhaVoltar.add(new InlineKeyboardButton("<- Voltar à lista de hotéis").setCallbackData(prefixoCallbackVoltar));

        teclado.add(linhaVoltar);

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaMaisInfoQuartoKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        String prefixoCallbackMaisInfo = PREFIXO_CALLBACK_MAIS_INFO_QUARTO + CALLBACK_DELIMITADOR;

        List<InlineKeyboardButton> linhaMaisInfoQuarto = new ArrayList<>();
        linhaMaisInfoQuarto.add(new InlineKeyboardButton("Ver mais informações").setCallbackData(prefixoCallbackMaisInfo));

        teclado.add(linhaMaisInfoQuarto);

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaConfirmacaoQuartoEscolhidoKeyboard() {
        String callbackSim = PREFIXO_CALLBACK_QUARTO_SELECIONADO + CALLBACK_DELIMITADOR;
        String callbackVoltar = PREFIXO_CALLBACK_VOLTAR_QUARTOS + CALLBACK_DELIMITADOR;

        return montaDuasOpcoesKeyboard(callbackSim, callbackVoltar, "Sim", "Não, quero escolher outro quarto");
    }

    public InlineKeyboardMarkup montaHospedesKeyboard(List<Hospede> hospedes) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        String prefixoCallbackPreencherHospedes = PREFIXO_CALLBACK_PREENCHER_HOSPEDES + CALLBACK_DELIMITADOR;
        String prefixoCallbackListarDados = PREFIXO_CALLBACK_LISTAR_DADOS_PARA_ALTERACAO + CALLBACK_DELIMITADOR;

        for (Hospede hospede : hospedes) {
            int indiceHospede = hospedes.indexOf(hospede);
            int indiceHospedeTexto = indiceHospede + 1;

            String textoBotao;
            if (!hospede.isTodosDadosPreenchidos()) {
                textoBotao = "Preencher dados do hóspede " + indiceHospedeTexto + " (" + hospede.getFaixaEtaria() + ")";

                List<InlineKeyboardButton> linha = new ArrayList<>();
                linha.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallbackPreencherHospedes + indiceHospede));

                teclado.add(linha);
            } else {
                if (hospede.getSexo().equals(Sexo.MASCULINO)) {
                    textoBotao = "Modificar dados do " + hospede.getNome() + " (" + hospede.getFaixaEtaria() + ")";
                } else {
                    textoBotao = "Modificar dados da " + hospede.getNome() + " (" + hospede.getFaixaEtaria() + ")";
                }

                List<InlineKeyboardButton> linha = new ArrayList<>();
                linha.add(new InlineKeyboardButton(textoBotao).setCallbackData(prefixoCallbackListarDados + indiceHospede));

                teclado.add(linha);
            }
        }

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaSexoHospedeKeyboard() {
        String callbackMasculino = PREFIXO_CALLBACK_SEXO_HOSPEDE + CALLBACK_DELIMITADOR + Sexo.MASCULINO.getValor();
        String callbackFeminino = PREFIXO_CALLBACK_SEXO_HOSPEDE + CALLBACK_DELIMITADOR + Sexo.FEMININO.getValor();

        return montaDuasOpcoesKeyboard(callbackMasculino, callbackFeminino, Sexo.MASCULINO.getValor(), Sexo.FEMININO.getValor());
    }

    public InlineKeyboardMarkup montaDadosHospedeKeyboard(Hospede hospede) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        String prefixoCallbackModificarDados = PREFIXO_CALLBACK_MODIFICAR_DADOS_HOSPEDES + CALLBACK_DELIMITADOR;
        String prefixoCallbackSair = PREFIXO_CALLBACK_SAIR_MODIFICACAO_DADOS_HOSPEDES + CALLBACK_DELIMITADOR;

        List<String> dados;
        if (hospede.getFaixaEtaria().equals(FaixaEtaria.INF)) {
            dados = Hospede.DADOS_HOSPEDES_INF;
        } else {
            dados = Hospede.DADOS_HOSPEDES;
        }

        for (String dado : dados) {
            List<InlineKeyboardButton> linha = new ArrayList<>();
            linha.add(new InlineKeyboardButton(dado).setCallbackData(prefixoCallbackModificarDados + dado));

            teclado.add(linha);
        }

        List<InlineKeyboardButton> linhaSair = new ArrayList<>();
        linhaSair.add(new InlineKeyboardButton("Tudo OK!").setCallbackData(prefixoCallbackSair));

        teclado.add(linhaSair);

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup montaConfirmacaoHospedesKeyboard() {
        String callbackSim = PREFIXO_CALLBACK_CONFIRMACAO_HOSPEDES + CALLBACK_DELIMITADOR;
        String callbackVoltar = PREFIXO_CALLBACK_VOLTAR_PREENCHER_HOSPEDES + CALLBACK_DELIMITADOR;

        return montaDuasOpcoesKeyboard(callbackSim, callbackVoltar, "Sim", "Não, preciso mudar algumas informações");
    }

    public InlineKeyboardMarkup montaConfirmacaoInformacoesReservaKeyboard() {
        String callbackSim = PREFIXO_CALLBACK_CONFIRMACAO_INFORMACOES_RESERVA + CALLBACK_DELIMITADOR;
        String callbackVoltar = PREFIXO_CALLBACK_VOLTAR_INICIO + CALLBACK_DELIMITADOR;

        return montaDuasOpcoesKeyboard(callbackSim, callbackVoltar, "Sim", "Não, vou pensar mais um pouco...");
    }

    public InlineKeyboardMarkup montaResponsavelReservaKeyboard(List<Hospede> hospedes) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        String prefixoCallback = PREFIXO_CALLBACK_CONFIRMACAO_RESPONSAVEL + CALLBACK_DELIMITADOR;

        List<Hospede> adultos =
                hospedes.stream().filter(hospede -> hospede.getFaixaEtaria().equals(FaixaEtaria.ADT)).collect(Collectors.toList());

        for (Hospede adulto : adultos) {
            List<InlineKeyboardButton> linhaAdultos = new ArrayList<>();
            linhaAdultos.add(new InlineKeyboardButton(adulto.getNome()).setCallbackData(prefixoCallback + adulto.getCpf()));

            teclado.add(linhaAdultos);
        }

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup montaDuasOpcoesKeyboard(String callbackOpcao1, String callbackOpcao2, String textoOpcao1,
                                                         String textoOpcao2) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> teclado = new ArrayList<>();

        List<InlineKeyboardButton> linha1 = new ArrayList<>();
        linha1.add(new InlineKeyboardButton(textoOpcao1).setCallbackData(callbackOpcao1));

        List<InlineKeyboardButton> linha2 = new ArrayList<>();
        linha2.add(new InlineKeyboardButton(textoOpcao2).setCallbackData(callbackOpcao2));

        teclado.add(linha1);
        teclado.add(linha2);

        inlineKeyboardMarkup.setKeyboard(teclado);

        return inlineKeyboardMarkup;
    }

}
