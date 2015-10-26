angular
        .module('escoladeti.validacaoService',[])
        .factory('validacaoService', validacaoService);

function validacaoService(growl){
    return {
        verificarSeExisteTelefoneIguais: function(VPArray, VPNovoTelefone){
            this.existe = false;
            for (var i = 0; i < VPArray.length; i++) {
                if (VPArray[i].numero === VPNovoTelefone.numero && VPArray[i].tipotelefone === VPNovoTelefone.tipotelefone){
                    this.existe = true;
                    break;
                }                
            }
            if(this.existe)
                growl.info("Esse telefone ja existe!");    
                
            return this.existe;
        },
        verificarSeExisteEmailsIguais: function(VPArray, VPNovoEmail){
            this.existe = false;
            for (var i = 0; i < VPArray.length; i++) {
                if (VPArray[i].email === VPNovoEmail.email){
                    this.existe = true;
                    break;
                }                
            }
            if(this.existe)
                growl.info("Esse email ja existe!");    
                
            return this.existe;
        },        
        verificarSeExisteEnderecosIguais: function(VPArray, VPNovoEndereco){
            this.existe = false;
            for (var i = 0; i < VPArray.length; i++) {
                if (VPArray[i].pais === VPNovoEndereco.pais && VPArray[i].uf.sigla === VPNovoEndereco.uf.sigla && 
                    VPArray[i].cidade.nome === VPNovoEndereco.cidade.nome && VPArray[i].cep === VPNovoEndereco.cep &&
                    VPArray[i].complemento === VPNovoEndereco.complemento && VPArray[i].numero === VPNovoEndereco.numero &&
                    VPArray[i].bairro.nome === VPNovoEndereco.bairro.nome && VPArray[i].logradouro.nome === VPNovoEndereco.logradouro.nome)
                {                
                    this.existe = true;
                    break;
                }                
            }
            if(this.existe)
                growl.info("Esse Endereço ja existe!");    
                
            return this.existe;
        }        
    };
    
};
