package br.unicesumar.time5.service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author NuriellyCaroline
 */
public class RelatorioService {
    
    public void listaRelatorio(HttpServletResponse response, Connection connection, String nomeRelatorio, Class classe, String fileName) 
            throws IOException, JRException, SQLException {
        
        response.setHeader("Content-Disposition", "inline; filename="+ fileName);
        HashMap<String, Object> params = new HashMap<>();

        String reportStream = classe.getProtectionDomain().getCodeSource().getLocation().getPath();
        String diretorioProjeto = reportStream.substring(1, reportStream.indexOf("projetoFull"));

        reportStream = diretorioProjeto + "relatorios//" + nomeRelatorio;

        params.put("diretorioProjeto", diretorioProjeto);

        OutputStream out = response.getOutputStream();

        JasperPrint jp = JasperFillManager.fillReport(reportStream, params, connection);

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

        exporter.exportReport();
    }
    
     public void listaID(HttpServletResponse response, Connection connection, Long id, String nomeRelatorio, Class classe, String fileName, 
             String variavelRelatorio) throws IOException, JRException, SQLException {
         
        response.setHeader("Content-Disposition", "inline; filename=filename="+ fileName);
        HashMap<String, Object> params = new HashMap<>();
        
        String reportStream = classe.getProtectionDomain().getCodeSource().getLocation().getPath();
        String diretorioProjeto = reportStream.substring(1, reportStream.indexOf("projetoFull"));
        
        reportStream = diretorioProjeto + "relatorios//" + nomeRelatorio;
        
        params.put(variavelRelatorio, id);
        params.put("diretorioProjeto", diretorioProjeto);
        
        OutputStream out = response.getOutputStream();

        JasperPrint jp = JasperFillManager.fillReport(reportStream, params, connection);

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

        exporter.exportReport();
    }
}
