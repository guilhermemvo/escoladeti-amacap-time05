package br.unicesumar.time5.controller;

import br.unicesumar.time5.types.Status;
import br.unicesumar.time5.entity.Aluno;
import br.unicesumar.time5.entity.Bairro;
import br.unicesumar.time5.entity.Cidade;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import br.unicesumar.time5.entity.DAOInscricao;
import br.unicesumar.time5.entity.Escola;
import br.unicesumar.time5.entity.Evento;
import br.unicesumar.time5.entity.Logradouro;
import br.unicesumar.time5.entity.Material;
import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.Pedido;
import br.unicesumar.time5.entity.PessoaFisica;
import br.unicesumar.time5.entity.Professor;
import br.unicesumar.time5.entity.TipoTelefone;
import br.unicesumar.time5.entity.UnidadeFederativa;
import br.unicesumar.time5.service.AlunoService;
import br.unicesumar.time5.service.BairroService;
import br.unicesumar.time5.service.CidadeService;
import br.unicesumar.time5.service.EscolaService;
import br.unicesumar.time5.service.EventoService;
import br.unicesumar.time5.service.InscricaoService;
import br.unicesumar.time5.service.LogradouroService;
import br.unicesumar.time5.service.MaterialService;
import br.unicesumar.time5.service.PaisService;
import br.unicesumar.time5.service.PedidoService;
import br.unicesumar.time5.service.PessoaFisicaService;
import br.unicesumar.time5.service.ProfessorService;
import br.unicesumar.time5.service.TipoTelefoneService;
import br.unicesumar.time5.service.UnidadeFederativaService;
import java.util.Date;

/**
 *
 * @author Narayane
 */
@Controller
@RequestMapping("/site")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SiteController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
            .getLogger(EventoController.class);

    @Autowired
    private EventoService eventoService;

    @Autowired
    private PessoaFisicaService pessoaService;

    @Autowired
    private EscolaService escolaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private TipoTelefoneService tipoTelefoneService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private UnidadeFederativaService ufservice;

    @Autowired
    private LogradouroService logradouroService;

    @Autowired
    private BairroService bairroService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(value = {"evento/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Evento> todosEventos() {
        return eventoService.getTodosEventos();
    }

    
    @RequestMapping(value = "evento/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Evento carregar(@PathVariable Long id) {
        Evento evento = eventoService.recuperarPeloId(id);
        return evento;
    }
      
    
    @RequestMapping(value = "pessoa/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody PessoaFisica pessoafisica) {
        pessoaService.salvar(pessoafisica);
        return "OK";
    }

    @RequestMapping(value = "/pedido/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvarPedido(@RequestBody Pedido pedido) {
        pedido.setStatus(Status.PRODUCAO.getDescricao());
        if (pedido.getDataPedido() == null) {
            Date data = new Date();
            pedido.setDataPedido(data);
        } else {
            Date data = pedido.getDataPedido();
            data.setDate(data.getDate() + 1);
            pedido.setDataPedido(data);
        }
        pedidoService.salvar(pedido);
        return "OK";
    }

    @RequestMapping(value = "evento/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvarInscrito(@RequestBody DAOInscricao i) {
        inscricaoService.salvar(i);
        return "OK";
    }

    @RequestMapping(value = {"material/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Material> todosLivros() {
        return materialService.getTodosMaterial();
    }

    @RequestMapping(value = {"/material/busca/{material}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Material> listaMaterial(@PathVariable String material) {
        System.out.println("busca material");
        return materialService.procurarMaterial(material);

    }

    @RequestMapping(value = "carregar/inscrito/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public PessoaFisica cpf(@PathVariable String cpf) {
        return pessoaService.carregarCPF(cpf);
    }

    @RequestMapping(value = "/carregar/pessoa/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public PessoaFisica carregarCpf(@PathVariable String cpf) {
        return pessoaService.carregarCPF(cpf);
    }

    @RequestMapping(value = "/carregar/escola/{cnpj}", method = RequestMethod.GET)
    @ResponseBody
    public Escola carregarEscolaCnpj(@PathVariable String cnpj) {
        return escolaService.BuscaEscolaPorCNPJ(cnpj);
    }

    @RequestMapping(value = "/carregar/aluno/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public Aluno carregarAluoCpf(@PathVariable String cpf) {
        return alunoService.carregarCPF(cpf);
    }

    @RequestMapping(value = "/carregar/professor/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public Professor carregarProfessorCpf(@PathVariable String cpf) {
        return professorService.carregarCPF(cpf);
    }

    @RequestMapping(value = {"tipoTelefone/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<TipoTelefone> todos() {
        return tipoTelefoneService.getTodosTiposTelefone();
    }

    @RequestMapping(value = {"pais/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Pais> todosPais() {
        return paisService.getTodosPaises();
    }

    @RequestMapping(value = "uf/findbyCodigoPais/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<UnidadeFederativa> findbyCodigoPais(@PathVariable Long id) {
        return ufservice.findbyCodigoPais(id);
    }

    @RequestMapping(value = "logradouro/procurarLogradouroPorIdBairro/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Logradouro> procurarLogradouroPorIdBairro(@PathVariable Long id) {
        return logradouroService.procurarLogradouroPorIdBairro(id);
    }

    @RequestMapping(value = "bairro/procurarBairroPorIdCidade/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Bairro> procurarBairroPorIdCidade(@PathVariable Long id) {
        return bairroService.procurarBairroPorIdCidade(id);
    }

    @RequestMapping(value = {"cidade/procurarCidadePorIdUf/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Cidade> procurarCidadePorIdUf(@PathVariable Long id) {
        return cidadeService.procurarCidadePorIdUf(id);
    }
}
