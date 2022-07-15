package br.com.lcv.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lucas on 09/08/19.
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private long telegramId;

}
