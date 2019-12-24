/**
 * ce scripte permer de verfier les donner saisie par l'admin avant l'envoi du formulaire
 */
function verif(){
	var err ="";
	if (document.getElementById("nom").value=='')
		err+="-nom";
	if (document.getElementById("prenom").value=='')
		err+="-prenom";
	if (document.getElementById("login").value=='')
		err+="-identifiant";
	if (document.getElementById("password").value=='')
		err+="-mot de passe";
	if(err != ""){
		alert("Formulaire incomplet: \n "+err)
	}
	else if(confirm(" Transmettre le formulaire ?")){
		
		document.getElementById('se conecter').submit()
	}
	
	
}