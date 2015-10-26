var moduleAssociadoPJ = angular.module('escoladeti.associadopj', ['ngRoute']);

moduleAssociadoPJ.controller('AssociadoPJController', ['$scope', '$http', '$rootScope', '$routeParams','pessoaService', function($scope, $http, $rootScope, $routeParams, pessoaService) {

        $scope.message = 'Controller dos Associados Pessoa Jurídica!';
        $scope.page = {pageNumber: 0};
        $scope.associadoPJAtual = undefined;
        $scope.search = "";
        $scope.listaPessoaJuridica = [];
        $scope.nome = "Associado Pessoa Jurídica";
        $scope.form = "AssociadoPJ";
        $scope.caminholistar = "./View/Pessoa/AssociadoPJ/listarAssociadoPJ.html";
        $scope.caminhoeditar = "./View/Pessoa/AssociadoPJ/editarAssociadoPJ.html";
        $scope.caminhoinicial = "#/Pessoa/AssociadoPJ/";
        $scope.caminhorelatorio = "associadopj/relatorio/listaRelatorio";
        $scope.displayBtnRelatorio = "inline";
        $scope.associadoPJ = [];

        $scope.incluir = function() {
            window.location.href = "#Pessoa/AssociadoPJ/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };
        $scope.encontraEnderecoPorCEP = function (value) {
            $scope.enderecos;
            pessoaService.getLogradouroCep(value)
                    .success(function (data) {
                        $scope.loadTodosPaises();
                        $scope.enderecos.pais = data.cidade.unidadefederativa.pais;
                        $scope.procurarUfPorIdPais(data.cidade.unidadefederativa.pais.id);
                        $scope.enderecos.uf = data.cidade.unidadefederativa;
                        $scope.procurarCidadePorIdUf(data.cidade.unidadefederativa.id);
                        $scope.enderecos.cidade = data.cidade;
                        $scope.procurarBairroPorIdCidade(data.cidade.id);
                        $scope.enderecos.bairro = data.bairro;
                        $scope.procurarLogradouroPorIdBairro(data.bairro.id);
                        $scope.enderecos.tipologradouro = data.tipologradouro;
                        $scope.enderecos.logradouro = data;
                        console.log($scope.enderecos);
                    });
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.associadoPJAtual = {
                    id:"",
                    ativo:"",
                    valorContribuicao:"",
                    pessoaJuridica:""
                    
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.associadoDadoId($routeParams.objeto);
            }
            $scope.loadTodasPessoasJuridicas();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.associaPJdoAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.associadoPJ = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'id');
        };

        $scope.loadTodasPessoasJuridicas = function() {
            $http({
                method: "GET",
                url: "./pessoajuridica/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaPessoaJuridica = data;
            }).error(function(data, status) {
	                alert("Erro ao buscar Pessoas Juridica!");
            });
        };

        $scope.removerServidor = function(associado) {
            $http({
                method: "DELETE",
                url: "./associadopj/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(associado))
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
            }).error(function(data, status) {
                    alert("Não foi possível excluir o registro!")
                
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./associadopj/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                alert("Erro ao buscar Associado!" + data + status);
            });
        };


        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./associadopj/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.associadoPJAtual)
            }).success(function(data, status) { 
            	window.location.href = $scope.caminhoinicial;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar Associado!"+data+status);
            });
        };

        $scope.associadoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./associadopj/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.associadoPJAtual = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar Associado ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };
        
//        $scope.procurarPorNome = function(nome) {
//            if(nome != ''){                                 
//                $http({
//                    method: "GET",
//                    url: "./associadopjS/procurarPorNome/" + nome,
//                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
//                }).success(function(data, status) {
//                    $scope.page = data;
//                }).error(function(data, status) {
//                    alert("Erro ao buscar Associado!");
//                });
//            }else{
//                $scope.load(0);
//            }                        
//        };          

    }
]);


