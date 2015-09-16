// JavaScript Document
function changeSource(var1,var2){
	document.getElementById(var1).src=var2;
}
function stopMove(){
	document.getElementById("move").scrollAmount='0';
}
function startMove(){
	document.getElementById("move").scrollAmount='5';
}
function  getColor1(){ 
	document.getElementById("end_text").style.borderColor="blue";	
	window.setTimeout(getColor2,500);
}  
function getColor2(){
	document.getElementById("end_text").style.borderColor="#3F3";	
	window.setTimeout(getColor3,500);
}
function getColor3(){
	document.getElementById("end_text").style.borderColor="orange";
	window.setTimeout(getColor1,500);
}