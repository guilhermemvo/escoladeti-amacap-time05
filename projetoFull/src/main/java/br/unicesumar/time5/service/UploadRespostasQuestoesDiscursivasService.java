package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.UploadResult;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PauloHenrique
 */
@Service
@Transactional
public class UploadRespostasQuestoesDiscursivasService {

    private final String rootPath = System.getProperty("catalina.home");
    private final String root = rootPath + "/webapps";
    private final String local = "/imagens";

    private final String rootPathFisico = System.getProperty("catalina.base");
    private final String rootFisico = rootPathFisico + "/webapps";
    private final String localFisico = "/imagens";

    public String getRootPath() {
        return local;
    }

    public boolean deletarArquivo(String arquivo) {

        File file = new File(rootFisico + localFisico, arquivo);
        boolean delete = file.delete();
        System.out.println("deletar foto:" + delete);
        System.out.println(file);
        return delete;

    }

    public UploadResult receberArquivo(MultipartFile file) {
        
        System.out.println("recebendo arquivo...");
        File pasta = new File(rootFisico + localFisico);
        if (!pasta.exists() || !pasta.isDirectory()) {
            if (pasta.mkdirs()) {
                System.out.println("pasta criada:" + pasta);
            } else {
                System.out.print("erro ao criar pasta");
            };
        }
        System.out.println(file.getContentType());
        File arquivoDestino = new File(pasta, file.getOriginalFilename());

        try {
            file.transferTo(arquivoDestino);
            System.out.println("arquivo transferido");
        } catch (IOException | IllegalStateException ex) {
            return new UploadResult(file.getOriginalFilename(), "danger", ex.getMessage());
        }
        return new UploadResult(file.getOriginalFilename(), "success", "Beleza, boy");
    }
}
