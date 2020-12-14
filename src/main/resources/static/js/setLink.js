/**
 * 
 */

function setLink(url){
	var scelta = document.getElementsByName("id");
	var i = 0;
	while(i<scelta.length && !scelta[i].checked){
		i++;
	}
	if(i<scelta.length){
		window.location.href = url+scelta[i].value;
	}
	
}


