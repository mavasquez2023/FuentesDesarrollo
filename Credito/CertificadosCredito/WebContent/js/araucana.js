function doPrint() {
	document.all.item("noprint").style.visibility = 'hidden';
	$("#titulo").css("text-align", "center");
	window.print();
	document.all.item("noprint").style.visibility = 'visible';
	$("#titulo").css("text-align", "left");
}
