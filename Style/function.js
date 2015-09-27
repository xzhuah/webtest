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
 function modifyItem() {  
 var id=document.getElementById("userID").value;
 var pass=document.getElementById("userPass").value;
        if (id == "") {  
            alert("No User ID！");  
            document.getElementById("userID").focus();  
            return;  
        }   
		if (pass == "") {  
            alert("No User Password！");  
            document.getElementById("userPass").focus();  
            return;  
        } 
		if(pp(pp(id)+pp(pass))[0]=="Z"&&pp(pp(id)+pp(pass))[8]=="V"&&pp(pp(id)+pp(pass))[11]=="h"&&pp(pp(id)+pp(pass))[16]=="T"&&pp(pp(id)+pp(pass))[22]=="5"&&pp(pp(id)+pp(pass))[4]==pp(pp(id)+pp(pass))[20]&&pp(pp(id)+pp(pass))[28]=="a"){
			 alert("Welcome "+id);
			 id=id.replace(id[0],id[0].toUpperCase());
			 var h = document.createElement("h2");
			 var t = document.createTextNode("Dear "+id+" Thank you for debugging for me ☺");
			 var hh = document.createElement("h2");
			 var tt=  document.createTextNode("I will give you a little ");
			 h.appendChild(t);
			 hh.appendChild(tt);
			 var ttt=document.createElement("img");
			// var ttt2=document.createElement("img");
			 ttt.src="image/gift.png";
			// ttt2.src="image/gift.png";
			 ttt.setAttribute("onclick"," appendOther();");
			 var link=document.createElement("a");link.appendChild(ttt);
			 link.href="https://netbeans.org/downloads/index.html";
			 link.target="_blank";
			
			 hh.appendChild(link);
			// hh.appendChild(ttt2);
			 document.getElementById("append").appendChild(h);
			 document.getElementById("append").appendChild(hh);
			
			
		}else{
			alert("Welcome "+id);
		}
		   
    }  
	
function pp(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function(match, p1) {
        return String.fromCharCode('0x' + p1);
    }));
}
function appendOther(){
	 var anothetTitle = document.createElement("h2");
	 var hint=  document.createTextNode("Ha Ha, I know eclipse is hard to use, you may try this new IDE.");
	 anothetTitle.appendChild(hint);
	 var link2 = document.createElement("a");
	 link2.href="download/SimpleTXTDBMS.java";
	 link2.target="_blank";
	 var say=document.createTextNode("Anyway here is an ");
	 var say2=document.createTextNode(" developed by myself, It can act like a simple database!! I hope it is helpful.");
	 var hint2= document.createTextNode("API");
	  anothetTitle.appendChild(say);
	 link2.appendChild(hint2);
	  anothetTitle.appendChild(link2);
	 anothetTitle.appendChild(say2);
	 document.getElementById("append").appendChild(anothetTitle);
}
	
