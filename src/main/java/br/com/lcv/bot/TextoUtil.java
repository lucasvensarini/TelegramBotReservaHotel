package br.com.lcv.bot;

import org.springframework.stereotype.Component;

import java.text.Normalizer;

/**
 * Created by lucas on 17/10/19.
 */
@Component
public class TextoUtil {

    public String normalizaTexto(String texto) {
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return textoNormalizado.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

}
