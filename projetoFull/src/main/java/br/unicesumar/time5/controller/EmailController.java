package br.unicesumar.time5.controller;

    import br.unicesumar.time5.entity.EmailSender;
    import java.io.Serializable;
    import org.springframework.context.annotation.Scope;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.context.WebApplicationContext;

    import br.unicesumar.time5.service.EmailService;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;

    @Controller
    @RequestMapping("/site/email")
    @Scope(WebApplicationContext.SCOPE_SESSION)
    public class EmailController implements Serializable {

        @RequestMapping(value = "/salvar", method = RequestMethod.POST)
        @ResponseBody
        public String salvar(@RequestBody EmailSender email) {

            email.setAssunto("Contato Site: " + email.getAssunto());
            email.setMensagem("Nome: " + email.getNome() + " Email: " + email.getEmail()+ " Mensagem: " + email.getMensagem());
            ApplicationContext context
                    = new ClassPathXmlApplicationContext("Spring-Mail.xml");

            EmailService mm = (EmailService) context.getBean("mailMail");
            mm.sendMail(email);
            return "OK";
        }

    }