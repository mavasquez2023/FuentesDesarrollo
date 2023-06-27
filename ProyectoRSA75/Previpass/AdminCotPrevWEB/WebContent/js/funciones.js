function cambiaDiv(id) 
{
	if (tabActual != id)
		tabActual = id;
	for (i = 0; i < listaDivs.length; i++)
	{
		if (listaDivs[i] == id)
		{
			document.getElementById(id).style.display = 'block';
			document.getElementById(id + 'Td').style.color = '#ff8a00';
		} else
		{
			document.getElementById(listaDivs[i]).style.display = 'none';
			document.getElementById(listaDivs[i] + 'Td').style.color = '#000000';
		}
	}
}

function formatNumero(valor)
{
	var tmp = '';
	var str = new String(valor);
	var negativo = 0;
	if (str.charAt(0) == '-')
	{
		negativo = 1;
		str = str.substring(1, str.length);
	}
	count = 0;
	for (i = str.length - 1; i >= 0; i--)
	{
		count++;
		if ((count - 1) % 3 == 0 && (tmp.length > 0))
			tmp = '.' + tmp;
		tmp = str.charAt(i) + tmp;
	}
	if (negativo == 1)
		tmp = '-' + tmp;
	return tmp;
}

function creaNumero(monto)
{
	return new Number(limpiaNumero(monto, ''));
}

function limpiaNumero(texto, newVals)
{
	var vals="1234567890" + newVals;
	for (i = texto.length - 1; i >= 0; i--)
	{
		if (vals.indexOf(texto.charAt(i)) == -1)
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
	}
	return texto;
}
	