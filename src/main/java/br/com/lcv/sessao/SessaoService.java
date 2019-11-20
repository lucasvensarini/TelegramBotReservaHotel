package br.com.lcv.sessao;

import br.com.lcv.usuario.UsuarioNaoAutorizadoException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas on 19/07/19.
 */
@Service
public class SessaoService {

    private Map<Integer, Sessao> sessoes;

    public SessaoService() {
        this.sessoes = new HashMap<>();
    }

    public Sessao iniciaSessaoUsuario(Integer usuarioId) {
        Sessao sessao = new Sessao();
        sessoes.put(usuarioId, sessao);

        return sessao;
    }

    public Sessao buscaSessaoUsuario(Integer usuarioId) {
        return sessoes.get(usuarioId);
    }

    public void encerraSessaoUsuario(Integer usuarioId) {
        sessoes.remove(usuarioId);
    }

    public void validaUsuario(int usuarioId) {
        Sessao sessao = buscaSessaoUsuario(usuarioId);

        boolean autorizado = sessao != null && sessao.isUsuarioAutorizado();
        if (!autorizado) {
            throw new UsuarioNaoAutorizadoException();
        }
    }

    public void validaSessao(int usuarioId) {
        Sessao sessao = buscaSessaoUsuario(usuarioId);

        boolean sessaoValida = sessao != null && sessao.isSessaoValida();
        if (!sessaoValida) {
            throw new SessaoInvalidaException();
        }
    }

}
