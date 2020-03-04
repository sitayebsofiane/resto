//focntion pour cacher ou montrer les element avec id toggle en fonction si la variable nomClient est vide pas

$(document).ready(function(){
	var nomClient=$('#nom').text();
	var panier=$('#commande').text();
    if(nomClient !=''){
    	$('#toggle1').hide();
    	$('#toggle2').hide();
    }
    else{
    	$('#toggle3').hide();
    	$('#toggle4').hide();
    	$('.panierProduit').hide();
    	$('.panierMenu').hide();
    }
    if(panier =='')
    	$('#commander').hide();
});

