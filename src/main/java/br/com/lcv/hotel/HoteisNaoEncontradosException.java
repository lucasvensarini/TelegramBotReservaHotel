package br.com.lcv.hotel;

/**
 * Created by lucas on 07/10/19.
 */
class HoteisNaoEncontradosException extends RuntimeException {

    private static String MENSAGEM_HOTEIS_NAO_ENCONTRADOS = "Não encontrei nenhum hotel. Digite /reservarhotel para reinicar o processo.";

    HoteisNaoEncontradosException() {
        super(MENSAGEM_HOTEIS_NAO_ENCONTRADOS);
    }

}
