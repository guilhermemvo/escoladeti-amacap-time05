function materialController($scope){
    $scope.page = {pageNumber: 0};
    $scope.materialAtual = undefined;
    $scope.search = "";
    $scope.nome = "Material";
    $scope.form = "material";    
    $scope.caminholistar = "./View/Material/Material/listarMaterial.html";
    $scope.caminhoeditar = "./View/Material/Material/editarMaterial.html";
    $scope.caminhoinicial = "#/Material/Material/";
    
    $scope.incluir = function() {
        window.location.href = "#Material/Material/Novo/Editar";
    };
};

materialController.$inject = ['$scope'];

angular
    .module('escoladeti.material', [])
    .controller('MaterialController', materialController);