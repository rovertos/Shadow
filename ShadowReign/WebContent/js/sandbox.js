$(document).ready(function(){

	Sandbox.init();
	
	$(document).keypress(function(e){
		Sandbox.lastpressed = e.key;
	});
	
	$(".kd").bind("click", function(){
		var kid = $(this).parent().attr('id');	
		var d = $(this).text(); 
		var idx = kid.indexOf(d);
		var x = Sandbox.lastpressed == "q" ? 0 : 1;
		if (Sandbox.kdoms[kid][idx][x] == 0){
			Sandbox.kdoms[kid][idx][x] = 1;
		} else {
			Sandbox.kdoms[kid][idx][x] = 0;
		}
		Sandbox.lastpressed = null;
		Sandbox.draw();
	});
	
	$(".force").bind("click", function(){
		var conid = ($(this).attr('id')).substring(1);
		if (Sandbox.lastpressed == "1" || Sandbox.lastpressed == "2" || Sandbox.lastpressed == "3"
			 || Sandbox.lastpressed == "4"  || Sandbox.lastpressed == "5"  || Sandbox.lastpressed == "6"){
			Sandbox.kcons[conid][1] = Sandbox.lastpressed;
		} else {
			if (Sandbox.kcons[conid][0] == 0) {
				Sandbox.kcons[conid][0] = 1;
			} else {
				Sandbox.kcons[conid][0] = 0;
			}
		}
		Sandbox.lastpressed = null;
		Sandbox.draw();
	});
	
});

Sandbox = {
	
	dims: [["O","I"],["N","S"],["E","W"]],	
	lastpressed: null,	
	kdoms: [],	
	kcons: [],
	
	init: function(){
		Sandbox.kdoms = [];
		Sandbox.kcons = [];
		for (var x=0; x<2; x++){
			for (var y=0; y<2; y++){
				for (var z=0; z<2; z++){
					var k = Sandbox.dims[0][x] + Sandbox.dims[1][y] + Sandbox.dims[2][z];
					var karr = [[0,0],[0,0],[0,0]];
					Sandbox.kdoms[k] = karr;
					var nbrs = nkdom(k);
					Sandbox.kcons[k+"-"+nbrs[0]] = [0,2];
					Sandbox.kcons[k+"-"+nbrs[1]] = [0,2];
					Sandbox.kcons[k+"-"+nbrs[2]] = [0,2];
				}
			}
		}				
		Sandbox.draw();		
	},
	
	draw: function(){
		
		for (var x=0; x<2; x++){
			for (var y=0; y<2; y++){
				for (var z=0; z<2; z++){					
					var k = Sandbox.dims[0][x] + Sandbox.dims[1][y] + Sandbox.dims[2][z];
					var karr = Sandbox.kdoms[k];
					for (var i=0; i<3; i++){
						var kel = $("#"+k+" div:nth-child("+(i+1)+")");
						if (karr[i][1]>0){
							kel.removeClass("k0").addClass("k1");
						} else {
							kel.removeClass("k1").addClass("k0");
						}
						if (karr[i][0]>0){
							if (!kel.hasClass("pup")){
								kel.addClass("pup");
							}
						} else {
							kel.removeClass("pup");
						}						
					}
				}
			}
		}
		
		for (var kdom in Sandbox.kdoms){
			var neighbours = nkdom(kdom);
			for (var kn in neighbours){
				var kel = $("#"+kdom+"-"+neighbours[kn]);
				if (kel.length){
					var p = proximity(kdom,neighbours[kn]);
					var dir = kel.hasClass("str") ? "str" : "dia";
					kel.removeClass().addClass("con " + dir + " c"+p);
				}
				
			}
		}
		
		for (var kcon in Sandbox.kcons){
			$("#f"+kcon).html(Sandbox.kcons[kcon][1]);
			if (Sandbox.kcons[kcon][0] == 1){
				$("#a"+kcon).show();
				if ($("#"+kcon).length){
					$("#"+kcon).addClass("att");
				} else {
					var ps = kcon.split("-");
					$("#"+ps[1]+"-"+ps[0]).addClass("att");
				}
			} else {
				$("#a"+kcon).hide();
			}
		}		
		
	}
		
}

function proximity(kdom1,kdom2){
	var p = 0;
	var difference = false;
	var agreement = false;
	var disagreement = false;
	for (var i=0; i<3; i++){
		if (Sandbox.kdoms[kdom1][i][1] > 0 && Sandbox.kdoms[kdom2][i][1] > 0){
			if (kdom1.substring(i,i+1) == kdom2.substring(i,i+1)){
				p+=2;
				agreement = true;
			} else {
				p-=2;
				disagreement = true;
			}			
		} else if (Sandbox.kdoms[kdom1][i][1] > 0){
			if (kdom2.includes(kdom1.substring(i,i+1))){
				p+=1;
			} else {
				p-=1;
			}
			difference = true;
		} else if (Sandbox.kdoms[kdom2][i][1] > 0){
			if (kdom1.includes(kdom2.substring(i,i+1))){
				p+=1;
			} else {
				p-=1;
			}
			difference = true;
		}
	}
	if (agreement && !difference) p+=2;
	if (disagreement && !difference) p-=2;
	return p;
}

function rule(kdom){
	var r = "";
	for (var i=0; i<3; i++){
		if (Sandbox.kdoms[kdom][i][1] > 0)
			r += kdom.substring(i,i+1);
		
	}
	return r;
}

function nkdom(k){
	var parts=k.split("");
	return ndims(parts[0],parts[1],parts[2]);
}

function ndims(k1,k2,k3){
	var neighbours = [];
	neighbours[0] = Sandbox.dims[0][i(Sandbox.dims[0].indexOf(k1))] + k2 + k3;
	neighbours[1] = k1 + Sandbox.dims[1][i(Sandbox.dims[1].indexOf(k2))] + k3;
	neighbours[2] = k1 + k2 + Sandbox.dims[2][i(Sandbox.dims[2].indexOf(k3))];
	return neighbours;
}

function i(n){
	if (n==0) 
		return 1 
	else if (n==1) 
		return 0;
	return null;
}