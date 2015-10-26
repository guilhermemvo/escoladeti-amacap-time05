package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Email;
import br.unicesumar.time5.repository.EmailRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private EmailRepository emailRepository;

    public DataPage<Email> getEmails(Integer pagina) {
        return new DataPage<>(emailRepository.findAll(pageRequestForAsc(pagina, "nome")));
    }

    public void salvar(Email email) {
        emailRepository.save(email);
    }

    public Email recuperarPeloId(Long id) {
        return emailRepository.findOne(id);
    }

    public void remover(Email email) {
        emailRepository.delete(email);
    }

    public List<Email> getTodosEmails() {
        return emailRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Email carregar(Long id) {
        return emailRepository.findOne(id);
    }

    public EmailRepository getEmailRepository() {
        return emailRepository;
    }

}
