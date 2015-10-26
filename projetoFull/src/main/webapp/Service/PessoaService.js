angular
        .module('escoladeti.pessoaService', ['escoladeti.repositoryService'])
        .factory('pessoaService', pessoaService);


function pessoaService(repositoryService, $timeout) {
    return {
        
    
        getLogradouroCep: function (value) {
            var data = repositoryService.get('./logradouro/carregar/cep/' + value);            
            return data;
        },
        saveSolicitante: function(data) {
            return repositoryService.saveAndFlush('./solicitante/salvar', data);
            console.log(data);
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
