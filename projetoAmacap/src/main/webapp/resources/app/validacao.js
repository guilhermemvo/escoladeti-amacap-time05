
function validaQuantidadeCaracteres(string, qtd) {
    len = string.toString();
    len = len.length;

    if ((len < qtd)) {
        return false;
    }
    return true;
}
function checkvisualizar(string) {
    if (string == 'Visualizar') {
        return true;
    }else if (string == 'Editar') {
        return false;
    }
}

function validaCPF(strCPF) {
    if (strCPF != "") {
        var Soma;
        var Resto;
        Soma = 0;

        if (strCPF == "00000000000")
            return false;
        for (i = 1; i <= 9; i++)
            Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
        Resto = (Soma * 10) % 11;
        if ((Resto == 10) || (Resto == 11))
            Resto = 0;
        if (Resto != parseInt(strCPF.substring(9, 10)))
            return false;
        Soma = 0;
        for (i = 1; i <= 10; i++)
            Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
        Resto = (Soma * 10) % 11;
        if ((Resto == 10) || (Resto == 11))
            Resto = 0;
        if (Resto != parseInt(strCPF.substring(10, 11)))
            return false;
    }
    return true;
}
function validaCNPJ(cnpj) {
    cnpj = cnpj.replace(/[^\d]+/g, '');
    if (cnpj != "") {
        if (cnpj.length != 14)
            return false;

        // Elimina CNPJs invalidos conhecidos
        if (cnpj == "00000000000000" ||
                cnpj == "11111111111111" ||
                cnpj == "22222222222222" ||
                cnpj == "33333333333333" ||
                cnpj == "44444444444444" ||
                cnpj == "55555555555555" ||
                cnpj == "66666666666666" ||
                cnpj == "77777777777777" ||
                cnpj == "88888888888888" ||
                cnpj == "99999999999999")
            return false;

        // Valida DVs
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0, tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0))
            return false;

        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1))
            return false;
    }
    return true;

}
function soNumero(value) {
    var value = value.trim().replace(/[^0-9]/, '');
    return value;
}
function validaTelefone(value) {
    var value = value.trim().replace('-', '');
    var value = value.trim().replace('-', '');
    var value = value.trim().replace('(', '');
    var value = value.trim().replace(')', '');
    var value = value.trim().replace(' ', '');
    var value = value.trim().replace('+', '');

    var country, city, number;

    switch (value.length) {
        case 8: // ######## -> ####-####
            country = "";
            city = "";
            number = value.slice(0);
            number = number.slice(0, 4) + '-' + number.slice(4);
            result = (number).trim();
            break;
        case 10: // PP######## ->  (PP) ####-####
            city = value.slice(0, 2);
            number = value.slice(2, 10);
            number = number.slice(0, 4) + '-' + number.slice(4);
            result = ("(" + city + ") " + number).trim();
            break;
        case 11: // PP######### ->  (PP) #####-####
            city = value.slice(0, 2);
            number = value.slice(2, 11);
            number = number.slice(0, 5) + '-' + number.slice(5);
            result = ("(" + city + ") " + number).trim();
            break;
        case 12: // +CCPP######## -> +CC (PP) ####-####
            country = value.slice(0, 2);
            city = value.slice(2, 4);
            number = value.slice(4, 12);
            number = number.slice(0, 4) + '-' + number.slice(4);
            result = "+" + country + ("(" + city + ") " + number).trim();
            break;
        case 13: // +CCPP######### -> +CC (PP) #####-####
            country = value.slice(0, 2);
            city = value.slice(2, 4);
            number = value.slice(4, 13);
            number = number.slice(0, 5) + '-' + number.slice(5);
            result = "+" + country + ("(" + city + ") " + number).trim();
            break;
        default:
            return value;
    }

    if (country == 1) {
        country = "";
    }
    if (!result) {
        result = ("+" + country + " (" + city + ") " + number).trim();
    }
    return result;
}
;


function formatReal(int)
{
    var int = int.trim().replace('-', '');
    var int = int.trim().replace('-', '');
    var int = int.trim().replace('(', '');
    var int = int.trim().replace(')', '');
    var int = int.trim().replace(' ', '');
    var int = int.trim().replace('+', '');
    var int = int.trim().replace(',', '');
    var int = int.trim().replace('.', '');

    var tmp = int;
    var neg = false;

    tmp = tmp.replace(/([0-9]{2})$/g, ",$1");

    if (tmp.length > 6)
        tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");

    if (tmp.length > 9)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2,$3");

    if (tmp.length > 12)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2.$3,$4");
    
    
        if (tmp.length > 1 & tmp.length < 2) {
            tmp = tmp.replace(",", "0,");
        } else {
            tmp = tmp.replace(",", ",");
        }
    return (neg ? '-' + tmp : tmp);
}