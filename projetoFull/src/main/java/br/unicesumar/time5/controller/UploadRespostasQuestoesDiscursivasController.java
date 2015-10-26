package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.UploadResult;
import br.unicesumar.time5.service.UploadRespostasQuestoesDiscursivasService;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author PauloHenrique
 */
@Controller
@RequestMapping("rest/uploadRespostasDiscursivas")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UploadRespostasQuestoesDiscursivasController {

    @Autowired
    private UploadRespostasQuestoesDiscursivasService service;

    public UploadRespostasQuestoesDiscursivasService getService() {
        return service;
    }

    @RequestMapping(value = {"/root"}, method = RequestMethod.GET)
    @ResponseBody
    public String root() {
        return service.getRootPath();
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
    @ResponseBody
    public Boolean procurarPorNome(@RequestBody String arquivo) {
        
        String arq = arquivo.replace( "\"", "");
        System.out.println(arq);
        return service.deletarArquivo(arq);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public UploadResult uploadTest(MultipartHttpServletRequest request, HttpServletResponse response) {

        Iterator<String> fileNames = request.getFileNames();
        System.out.println("Relacao de arquivos:" + fileNames);

        UploadResult result = null;

        while (fileNames.hasNext()) {
            String arquivo = fileNames.next();
            System.out.println("aquivo:" + arquivo);

            System.out.println("preparando arquivo...");
            System.out.println(getService());

            System.out.println("Arquivo selecionado: " + request.getFile(arquivo));

            result = getService().receberArquivo(request.getFile(arquivo));
            System.out.println("Upload:" + result);

        }
        return result;
    }
}
