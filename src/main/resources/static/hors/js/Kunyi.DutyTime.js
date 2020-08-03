$(function(){
	$objDIVWeekday =$("#DIVWeekday");
	if ($objDIVWeekday.length > 0) {
		$WeekdayTop = $objDIVWeekday.offset().top-45;
		$__W.bind('scroll', function() {
			var scrollTop = $__D.scrollTop();
			var heg1 = $WeekdayTop;
			if (scrollTop <= heg1) {
				if ($objDIVWeekday.css("position") == "fixed")
					$objDIVWeekday.css({
						"position": "absolute","top":"90px"
					});
			} else {
				if ($objDIVWeekday.css('position') == "absolute")
					$objDIVWeekday.css({
						"position": "fixed","top":"45px"
					});
			}
		});
	}
});
$__W.load(function(){
	$(".general .visitList .namelist").each(function(i, e) {
		$t=$(this);
		$t.find(".list01").css("height",($t.height()-43)+"px");;
	});
});