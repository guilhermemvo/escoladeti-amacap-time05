function UsuarioController ($scope) {
	$scope.codigo = 0;
	$scope.usuario = {};
	$scope.usuarios = [];

	$scope.salvarUsuario = function (usuario) {
		if(!$scope.usuario.codigo){
			$scope.codigo++;
			usuario.codigo = $scope.codigo;
			$scope.usuarios.push(usuario);
			$scope.usuario = {};
		};
	}

	$scope.deletaUsuario = function(index){
		if(confirm("Deseja excluir o usuário?"))
			$scope.usuarios.splice(index,1);
	}
}