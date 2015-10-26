function formatPageList(data) {
    for ($i = 0; $i < data.list.length; $i++) {
        if (data.list[$i].cpf != null) {
            data.list[$i].cpf = mascaraCPF(data.list[$i].cpf);
        }
        if (data.list[$i].cnpj != null) {
            data.list[$i].cnpj = mascaraCNPJ(data.list[$i].cnpj);
        }
    }
    return data;

}

function mascaraCPF(cpf) {
    cpf = cpf.slice(0, 3) + '.' + cpf.slice(3, 6) + '.' + cpf.slice(6, 9) + '-' + cpf.slice(9, 11);
    return cpf;
}

function mascaraCNPJ(cnpj) {
    if (cnpj.length == 14) {
        cnpj = cnpj.slice(0, 2) + '.' + cnpj.slice(2, 5) + '.' + cnpj.slice(5, 8) + '/' + cnpj.slice(8, 12) + '-' + cnpj.slice(12, 14);
    }
    if (cnpj.length == 15) {
        cnpj = cnpj.slice(0, 3) + '.' + cnpj.slice(3, 6) + '.' + cnpj.slice(6, 9) + '/' + cnpj.slice(9, 13) + '-' + cnpj.slice(13, 15);
    }
    return cnpj;
}

function validaQuantidadeCaracteres(string, qtd) {
    len = string.toString();
    len = len.length;

    if ((len < qtd)) {
        return false;
    }
    return true;
}
function checkvisualizar(string) {
    if (string === 'Visualizar') {
        document.getElementById("field_set").disabled = true;
        return true;
    } else if (string === 'Editar') {
        document.getElementById("field_set").disabled = false;
        return false;
    }
}

function validaCPF(strCPF) {

    if ((strCPF != "") && (strCPF != null) && (strCPF != undefined)) {
        console.log("CPF:" + strCPF);
        strCPF = strCPF.replace(/[^\d]+/g, '');
        var Soma;
        var Resto;
        Soma = 0;

        if (strCPF === "00000000000" ||
                // strCPF === "11111111111" ||
                strCPF === "22222222222" ||
                strCPF === "33333333333" ||
                strCPF === "44444444444" ||
                strCPF === "55555555555" ||
                strCPF === "66666666666" ||
                strCPF === "77777777777" ||
                strCPF === "88888888888" ||
                strCPF === "99999999999")
            return false;

        for (i = 1; i <= 9; i++)
            Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
        Resto = (Soma * 10) % 11;
        if ((Resto === 10) || (Resto === 11))
            Resto = 0;
        if (Resto !== parseInt(strCPF.substring(9, 10)))
            return false;
        Soma = 0;
        for (i = 1; i <= 10; i++)
            Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
        Resto = (Soma * 10) % 11;
        if ((Resto === 10) || (Resto === 11))
            Resto = 0;
        if (Resto !== parseInt(strCPF.substring(10, 11)))
            return false;
    }
    return true;
}

function validaCNPJ(cnpj) {

    if ((cnpj != "") && (cnpj != null) && (cnpj != undefined)) {
        cnpj = cnpj.replace(/[^\d]+/g, '');
        if (cnpj.length != 14)
            return false;

        // Elimina CNPJs invalidos conhecidos
        if (cnpj == "00000000000000" ||
                //cnpj == "11111111111111" ||
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
    value = value.replace(/[^0-9]/, '');
    return value;
}

function validaTelefone(value) {
    if (value == null) {
        return null;
    } else {
        value = value.replace('-', '');
        value = value.replace('-', '');
        value = value.replace('(', '');
        value = value.replace(')', '');
        value = value.replace(' ', '');
        value = value.replace('+', '');

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

        if (country === 1) {
            country = "";
        }
        if (!result) {
            result = ("+" + country + " (" + city + ") " + number).trim();
        }
        return result;
    }
}
;

function formatReal(int)
{
    int = int.replace('-', '');
    int = int.replace('-', '');
    int = int.replace('(', '');
    int = int.replace(')', '');
    int = int.replace(' ', '');
    int = int.replace('+', '');
    int = int.replace(',', '');
    int = int.replace('.', '');

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

function somenteNumero(campo) {
    var digits = "0123456789.";
    var campo_temp;
    for (var i = 0; i < campo.value.length; i++) {
        campo_temp = campo.value.substring(i, i + 1);
        if (digits.indexOf(campo_temp) == -1) {
            campo.value = campo.value.substring(0, i);
        }
    }
}
