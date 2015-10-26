
myApp.directive('valTelefone', function() {

    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            var validate = function(inputValue) {

                var validated = validaTelefone(inputValue);

                if (validated !== inputValue) {
                    modelCtrl.$setViewValue(validated);
                    modelCtrl.$render();
                }
                return validated;
            }

            modelCtrl.$parsers.push(validate);
            validate(scope[attrs.ngModel]);  // uppercase initial value
        }
    };

});

myApp.directive('valMoeda', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            var validate = function(inputValue) {
               var validated = inputValue.toUpperCase();
                var validated = validated.toString().replace(/[^0-9]/, '');
                var validated = validated.replace('  ', ' ');
                var validated = validated.replace(/\s+$/, "");
                var validated = validated.replace(/^\s+/, "");
                var validated = formatReal(validated);
                if (validated !== inputValue) {
                    modelCtrl.$setViewValue(validated);
                    modelCtrl.$render();
                }
                return validated;
            }
            modelCtrl.$parsers.push(validate);
            validate(scope[attrs.ngModel]);  // uppercase initial value
        }
    };
});

myApp.directive('valNome', function() {

    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            var limiteQuantidade = 4;

            var validate = function(inputValue) {
                var validated = inputValue.toUpperCase();
                var validated = validated.toString().replace(/[^a-zA-Z\s]/, '');
                var validated = validated.replace('  ', ' ');
                var validated = validated.replace(/\s+$/, "");
                var validated = validated.replace(/^\s+/, "");

                modelCtrl.$setValidity('invalidNome', validaQuantidadeCaracteres(validated, limiteQuantidade));

                if (validated !== inputValue) {
                    modelCtrl.$setViewValue(validated);
                    modelCtrl.$render();
                }
                return validated;
            }

            modelCtrl.$parsers.push(validate);
            validate(scope[attrs.ngModel]);  // uppercase initial value
        }
    };

});

myApp.directive('valCpf', function() {

    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            attrs.uiMask = "999.999.999-99";
            scope.$watch(attrs.ngModel, function(newVal, oldVal) {
                modelCtrl.$setValidity('invalidCPF', validaCPF(newVal));

            });



        }
    };

});

myApp.directive('valCnpj', function() {

    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            attrs.uiMask = "99.999.999/9999-99";
            scope.$watch(attrs.ngModel, function(newVal, oldVal) {
                modelCtrl.$setValidity('invalidCNPJ', validaCNPJ(newVal));

            });

        }
    };

});

//**************VALIDAÇÃO*****************



//Validação de Comparação de Igualdade de Campos
//
//<input type="password"  name="senha"          ng-model="usuarioAtual.senha">   - aqui o ng-model foi referenciado ao objeto
//<input type="password"  name="confirmarSenha" ng-model="confirmarSenha"data-match="usuarioAtual.senha">  - aqui o ng-model foi referenciado a uma variável local
// e o data-match, é com que campo vai ser feito a validação, neste exemplo o confirmarSenha (variavel local), esta apontando para verificar se o campo é igual do usuarioAtual.senha (variavel do objeto usuarioAtual)



myApp.directive('match', function() {
    return {
        require: 'ngModel',
        restrict: 'A',
        scope: {
            match: '='
        },
        link: function(scope, elem, attrs, ctrl) {
            scope.$watch(function() {
                return (ctrl.$pristine && angular.isUndefined(ctrl.$modelValue)) || scope.match === ctrl.$modelValue;
            }, function(currentValue) {
                ctrl.$setValidity('match', currentValue);
            });
        }
    };
});
