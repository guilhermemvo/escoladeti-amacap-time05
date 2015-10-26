angular
        .module('escoladeti.repositoryService', [])
        .factory('repositoryService', repositoryService);

function repositoryService($http, growl) {
   
    return {
            get: function(url){
                return $http({
                            method: "GET",
                            url: url,
                            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                        })
                        .error(function(data, status) {
                        });                  
            },
            saveAndFlush: function(url,data){
                return $http({
                    method: "POST",
                    url: url,
                    headers: {'Content-Type': 'application/json; charset=UTF-8'},
                    data: angular.toJson(data)
                })
                .error(function(data, status) {
                });                
            },
            save: function(url,data){                
                $http({
                    method: "POST",
                    url: url,
                    headers: {'Content-Type': 'application/json; charset=UTF-8'},
                    data: angular.toJson(data)
                }).success(function (status){
                    growl.success("Cadastro efetuado com sucesso!");
                })
                .error(function(data, status) {
                    growl.error("Houve um erro no cadastro!");
                });                
            },
            cancelar: function(url, data){
            	$http({
                    method: "POST",
                    url: url,
                    headers: {'Content-Type': 'application/json; charset=UTF-8'},
                    data: angular.toJson(data)
                }).success(function (status){
                	if(status === "CANCELADO"){
                		growl.info("Este Pedido já está Cancelado!");
                	}else{	
                			growl.success("Pedido cancelado com sucesso!");
                	}
                    console.log(data);                
                })
                .error(function(data, status) {
                    growl.error("Houve um erro no cancelamento!");
                    console.log(data);
                    console.log(status);
                });         
            },
            update: function(url){}
    };
    
};	
