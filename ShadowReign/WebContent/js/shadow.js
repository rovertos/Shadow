$(document).ready(function(){

	Game.init();
	
});

Game = {
	colors: ["#007fff","#FFCC33","#CC66CC","#669966","#FF3333","#CC6600"],
	init: function(){
		
		for (var x=0; x<10; x++){
			for (var y=0; y<10; y++){
				$(".grid").append("<div class='cell' id='c"+x+y+"'><div class='ci c1'></div><div class='ci c2'></div><div class='ci c3'></div><div class='ci c4'></div></div>");
			}
		}
		
		$.getJSON("map.json", function(data){
			$.each(data.map, function(index, cell) {
				var classes = "reg nb" + cell.borders[0] + " eb" + cell.borders[1] + 
								" sb" + cell.borders[2] + " wb" + cell.borders[3] +
								" b" + cell.borders[0] + cell.borders[1] + cell.borders[2] + cell.borders[3] +
								" duch" + cell.duchy + " kdom" + cell.kingdom + " " + cell.lord;
				$("#c"+cell.xy[0]+cell.xy[1]).addClass(classes);				
			});
		});
		
	}
}