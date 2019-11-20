package br.com.lcv.hotel;

class QuartosNaoEncontradosException extends RuntimeException {

    private static String MENSAGEM_QUARTOS_NAO_ENCONTRADOS = "Quartos não encontrados";

    QuartosNaoEncontradosException() {
        super(MENSAGEM_QUARTOS_NAO_ENCONTRADOS);
    }

}
