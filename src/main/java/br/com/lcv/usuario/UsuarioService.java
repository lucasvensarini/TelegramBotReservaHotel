package br.com.lcv.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lucas on 09/08/19.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void buscaUsuarioPorTelegramId(long telegramId) {
        usuarioRepository.findUsuarioByTelegramId(telegramId).orElseThrow(UsuarioNaoAutorizadoException::new);
    }

}
