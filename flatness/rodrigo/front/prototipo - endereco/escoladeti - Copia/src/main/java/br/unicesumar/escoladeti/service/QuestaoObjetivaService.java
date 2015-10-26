package br.unicesumar.escoladeti.service;

import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unicesumar.escoladeti.controller.DataPage;
import br.unicesumar.escoladeti.entity.Alternativa;
import br.unicesumar.escoladeti.entity.QuestaoObjetiva;
import br.unicesumar.escoladeti.repository.AlternativaRepository;
import br.unicesumar.escoladeti.repository.QuestaoObjetivaRepository;

@Service
public class QuestaoObjetivaService {

	private static final Logger logger = LoggerFactory.getLogger(QuestaoObjetivaService.class);

	@Autowired
	private QuestaoObjetivaRepository questaoRepository;

	@Autowired
	private AlternativaRepository alternativaRepository;

	public QuestaoObjetivaRepository getQuestaoRepository() {
		return questaoRepository;
	}

	public AlternativaRepository getAlternativaRepository() {
		return alternativaRepository;
	}

	public DataPage<QuestaoObjetiva> getQuestoes(Integer pagina) {
		logger.debug("Executando serviço de busca de questão para página: {}", pagina);
		return new DataPage<>(getQuestaoRepository().findAll(pageRequestForAsc(pagina, "enunciado")));
	}

	public void remover(QuestaoObjetiva questao) {
		logger.debug("Remover questão: {}", questao);

		for (Alternativa alternativa : questao.getAlternativas()) {
			logger.debug("Removendo alternativa: {}", alternativa);
			getAlternativaRepository().delete(alternativa);
		}

		getQuestaoRepository().delete(questao);
		logger.debug("Remover concluído.");
	}

	public void salvar(QuestaoObjetiva questao) {
		questao.verificarConsistencia();
		logger.debug("Salvar questão: {}", questao);

		QuestaoObjetiva questaoSalva = getQuestaoRepository().save(questao);

		List<Alternativa> alternativasDesnecessarias = getAlternativaRepository().findAllByQuestao(questao);

		for (Alternativa alternativa : questao.getAlternativas()) {
			alternativasDesnecessarias.remove(alternativa);
			alternativa.setQuestao(questaoSalva);

			logger.debug("Salvando alternativa: {}", alternativa);
			getAlternativaRepository().save(alternativa);
		}

		for (Alternativa alternativa : alternativasDesnecessarias) {
			logger.debug("Removendo alternativa desnecessária: {}", alternativa);
			getAlternativaRepository().delete(alternativa);
		}

		logger.debug("Salvar concluído.");
	}

	public QuestaoObjetiva carregar(Long id) {
		logger.debug("Carregando questão id: {}.", id);
		return getQuestaoRepository().findOne(id);
	}

}
