/**
 * 
 */

function executaAjaxGet(urlChamada, divDestino, preExecute, posExecute){
	$.ajax({
		type : "GET",
		url : urlChamada,
		beforeSend: preExecute,
		success : function(response) {
			// we have the response
			var x = document.getElementById(divDestino);
			x.innerHTML = response;
			
		},
		complete: posExecute,
		error: function(xhr){
            alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});
	
}

function executaAjaxPost(divDestino, formOrigem, preExecute, posExecute){
	$
	.ajax({
		type : 'POST',
		url : formOrigem.action,
		data : $(formOrigem).serialize(),
		beforeSend: preExecute,
		success : function(response) {
			document.getElementById(divDestino).innerHTML = response;
			$("#myModal").modal('hide');
		},
		complete: posExecute,
		error: function(xhr){
            alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});		
}
function carregaListaEstados() {

	executaAjaxGet("/data/estado/obtertodos", "listaEstados", "", "");

}


function carregaListaCidades() {
	var sel = document.getElementById('selecaoEstado');
	executaAjaxGet("/data/cidadeporidestado/" + sel.value, "listaCidades", "", "");
}

function setaDadosModal(valor) {
$.ajax({
	type : "GET",
	url : "/data/cidade/" + valor,
	success : function(response) {
		// we have the response
		var x = document.getElementById("formModalCidade");
		x.innerHTML = response;
		var y = document.getElementById("myModal");
		var w = y.getElementsByTagName("form");
		var z = y.getElementsByTagName("select")
		z[0].onchange = "";
		$("#myModal").modal();

	},
	error: function(xhr){
        alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
	}
});
}



function setaDadosModalEstado(valor) {
	$.ajax({
		type : "GET",
		url : "/data/estado/" + valor,
		success : function(response) {
			// we have the response
			var x = document.getElementById("formModalEstado");
			x.innerHTML = response;
			var y = document.getElementById("myModal");
			var w = y.getElementsByTagName("form");
			atachaSubmitAjax(w[0]);
			$("#myModal").modal();

		},
		error : function(e) {
			alert('Error: SetaDadosModal - ' + e);
		}
	});
}


$body = $("body");

$(document).on({
	ajaxStart : function() {
		$body.addClass("loading");
	},
	ajaxStop : function() {
		$body.removeClass("loading");
	}
});