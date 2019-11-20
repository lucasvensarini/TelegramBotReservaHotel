package br.com.lcv.hotel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lucas on 24/10/19.
 */
public class HotelRepositoryCustomImpl implements HotelRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Hotel>> findHoteis(String cidade, int qtdHospedes, String nome, Classificacao classificacao) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Hotel> query = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = query.from(Hotel.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criaCidadePredicate(criteriaBuilder, hotelRoot, cidade));
        predicates.add(criaQtdHospedesPredicate(criteriaBuilder, hotelRoot, qtdHospedes));

        if (nome != null) {
            predicates.add(criaNomePredicate(criteriaBuilder, hotelRoot, nome));
        }

        if (!classificacao.equals(Classificacao.UNDEF)) {
            predicates.add(criaClassificacaoPredicate(criteriaBuilder, hotelRoot, classificacao));

        }

        query.select(hotelRoot).distinct(true).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        List<Hotel> resultList = entityManager.createQuery(query).getResultList();

        return Optional.of(resultList);
    }

    private Predicate criaCidadePredicate(CriteriaBuilder criteriaBuilder, Root<Hotel> hotelRoot, String cidade) {
        Path<String> enderecoPath = hotelRoot.get("endereco");
        Path<Object> cidadePath = enderecoPath.get("cidade");
        Path<Object> nomeNormalizadoPath = cidadePath.get("nomeNormalizado");

        return criteriaBuilder.equal(nomeNormalizadoPath, cidade);
    }

    private Predicate criaQtdHospedesPredicate(CriteriaBuilder criteriaBuilder, Root<Hotel> hotelRoot, int qtdHospedes) {
        Join<Hotel, Quarto> quartosJoin = hotelRoot.join("quartos");
        Path<Integer> capacidadePath = quartosJoin.get("capacidade");

        return criteriaBuilder.greaterThanOrEqualTo(capacidadePath, qtdHospedes);
    }

    private Predicate criaNomePredicate(CriteriaBuilder criteriaBuilder, Root<Hotel> hotelRoot, String nome) {
        Path<String> nomePath = hotelRoot.get("nome");
        return criteriaBuilder.equal(nomePath, nome);
    }

    private Predicate criaClassificacaoPredicate(CriteriaBuilder criteriaBuilder, Root<Hotel> hotelRoot, Classificacao classificacao) {
        Path<String> classificacaoPath = hotelRoot.get("classificacao");
        return criteriaBuilder.equal(classificacaoPath, classificacao);
    }

}