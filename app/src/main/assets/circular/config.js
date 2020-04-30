$(window).load(function(){
});

$(window).resize(function(){
	window.location.replace(location);
});



function dial(percent,width,height,cx,cy,r1,r2,eleId,colorFlag){
	var paper = Raphael(document.getElementById(eleId),width,height);
	var a = 225 - percent * 270 / 100, a0 = 225;
	var dial_0 = paper.path(doughnut_path(cx,cy,r1,r2,-45,225)).attr({"fill":"#FFF","stroke":"none"}),
		dial_1 = paper.path(doughnut_path(cx,cy,r1,r2,225,225)).attr({"fill":"#0AFAFF","stroke":"none"});
	if (colorFlag=="gradient") {
		dial_0.attr(
			{
				"fill":"#FFFFFF"
			}
		)
		dial_1.attr(
			{
				gradient: '#0AFAFF',
				'stroke-linejoin': 'round',
				rotation: -90
			}
		);
	};
	drawTemp();
	function drawTemp(){
		if(a0>a){
			a0--;
			dial_0.attr({"path":doughnut_path(cx,cy,r1,r2,-45,a0)});
			dial_1.attr({"path":doughnut_path(cx,cy,r1,r2,a0-1,225)});
			setTimeout(drawTemp,1);
		}
	}
	function doughnut_path(cx,cy,r1,r2,startAngle,endAngle){
		var rad = Math.PI / 180;
		var x1 = cx + r1 * Math.cos(-startAngle * rad), y1 = cy + r1 * Math.sin(-startAngle * rad),
			x2 =  cx + r2 * Math.cos(-startAngle * rad), y2 = cy + r2 * Math.sin(-startAngle * rad),
			x3 =  cx + r2 * Math.cos(-endAngle * rad), y3 = cy + r2 * Math.sin(-endAngle * rad),
			x4 =  cx + r1 * Math.cos(-endAngle * rad), y4 = cy + r1 * Math.sin(-endAngle * rad);    //四点坐标
		return ["M",x2,y2,"A",r2,r2, 0, +(endAngle - startAngle > 180),0,x3,y3,"L",x4,y4,"A",r1,r1,0,+( endAngle - startAngle > 180),1,x1,y1,"z"];
	}
}
