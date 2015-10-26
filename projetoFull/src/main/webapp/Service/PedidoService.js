angular
        .module('escoladeti.pedidoService', ['escoladeti.repositoryService'])
        .factory('pedidoService', pedidoService);


function pedidoService(repositoryService, $timeout) {
    return {
        
        getCountPendentes: function() {
            return repositoryService.get('./pedido/pendente/total/');
        },
        getLogradouroCep: function (value) {
            var data = repositoryService.get('./logradouro/carregar/cep/' + value);            
            return data;
        },
        getPagePedido: function(numeroDaPagina) {
            return repositoryService.get('./pedido/listar/pag/' + numeroDaPagina);
        },
        getAllPedido: function() {
            return repositoryService.get('./pedido/listar');
        },
        getPedidoId: function(id) {
            return repositoryService.get('./pedido/carregar/' + id);
        },
        savePedido: function(pedido) {
            if (pedido.hasOwnProperty("solicitante") && pedido.solicitante.id === "") {
                this.saveSolicitante(pedido.solicitante)
                        .success(function(data) {
                            pedido.solicitante = data;
                        });
            }
            if (pedido.hasOwnProperty("escola") && pedido.escola.id === "") {
                this.saveEscola(pedido.escola)
                        .success(function(data) {
                            pedido.escola = data;
                        });
            }
            if (pedido.hasOwnProperty("professor") && pedido.professor.id === "") {
                this.saveProfessor(pedido.professor)
                        .success(function(data) {
                            pedido.professor = data;
                        });
            }
            if (pedido.hasOwnProperty("aluno") && pedido.aluno.id === "") {
                this.saveAluno(pedido.aluno)
                        .success(function(data) {
                            pedido.aluno = data;
                        });
            }
            if (pedido.hasOwnProperty("materiais")) {
                console.log(pedido.materiais)
                this.saveMaterial(pedido.materiais)
                        .success(function(data) {
                            pedido.materiais = data;
                        });
            }
            $timeout(function() {

                var objetoSolicitante = {
                    id: pedido.solicitante.id,
                    dataCadastro: pedido.solicitante.dataCadastro,
                    dataNascimento: pedido.solicitante.dataNascimento,
                    telefones: pedido.solicitante.telefones,
                    emails: pedido.solicitante.emails,
                    enderecos: pedido.solicitante.enderecos,
                    cpf: pedido.solicitante.cpf,
                    rg: pedido.solicitante.rg,
                    sexo: pedido.solicitante.sexo,
                    observacao: null,
                    status: pedido.solicitante.status,
                    nome: pedido.solicitante.nome
                };

                pedido.solicitante = objetoSolicitante;

                repositoryService.save('./pedido/salvar', pedido);
            }, 100);
        },
        cancelarPedido: function(data) {
            return repositoryService.cancelar('./pedido/cancelar', data);
        },
        saveSolicitante: function(data) {
            return repositoryService.saveAndFlush('./solicitante/salvar', data);
            console.log(data);
        },
        saveMaterial: function(data) {
            return repositoryService.saveAndFlush('./material/salvar', data);
        },
         saveMaterialLivro: function(data) {
             console.log("livro:"+data);
            return repositoryService.saveAndFlush('./livro/salvar', data);
        },
         saveMaterialOutros: function(data) {
             console.log("outros:"+data);
            return repositoryService.saveAndFlush('./outros/salvar', data);
        },
        saveAluno: function(data) {
            return repositoryService.saveAndFlush('./aluno/salvar', data);
            console.log(data);
        },
        saveProfessor: function(data) {
            return repositoryService.saveAndFlush('./professor/salvar', data);
            console.log(data);
        },
        findProfessorByCpf: function(cpf) {
            return repositoryService.get('./professor/consulta/' + cpf);
        },
        findSolicitanteByNomeCpf: function(value) {
            return repositoryService.get('./pessoafisica/consulta/nomecpf/' + value);
        },
        findAlunoByNomeCpf: function(value) {
            return repositoryService.get('./aluno/consulta/nomecpf/' + value);
        },
        findProfessorByNomeCpf: function(value) {
            return repositoryService.get('./professor/consulta/nomecpf/' + value);
        },
        saveEscola: function(data) {
            return repositoryService.saveAndFlush('./escola/salvar', data);
            console.log(data);
        },
        findEscolaByCpnj: function(cnpj) {
            return repositoryService.get('./escola/consulta/' + cnpj);
        },
        getAllEscola: function() {
            return repositoryService.get('./escola/todas');
        },
        setFocus: function(modal, campo) {
            $(modal).on('shown.bs.modal', function() {
                $(campo).focus();
            });
        },
        
        getPageVolume: function(numeroDaPagina) {
            return repositoryService.get('./volume/listar/pag/' + numeroDaPagina);
        }
        
    };

}
;
