angular
        .module('escoladeti.solicitanteService',['escoladeti.repositoryService'])
        .factory('solicitanteService', solicitanteService);

function solicitanteService (repositoryService){
    return {
        saveSolicitante: function(data){
            repositoryService.save('./solicitante/salvar',data);
        },
        findSolicitanteByCpf: function(cpf){
            return repositoryService.get('./solicitante/consulta/' + cpf);
        },
        findSolicitanteById: function(id){
            return repositoryService.get('./solicitante/carregar/' + id);
        },        
        findSolicitanteByName: function(nome){
            return repositoryService.get('./solicitante/procurarPorNome/' + nome);
        },
        removeSolicitante: function(data){
            repositoryService.delete('./solicitante/remover', data);
        },
        getAllSolicitante: function(){
            return repositoryService.get('./solicitante/todos');
        },
        getPageSolicitante: function(numeroDaPagina){
            return repositoryService.get('./solicitante/listar/pag/' + numeroDaPagina);
        }
    };
    
};