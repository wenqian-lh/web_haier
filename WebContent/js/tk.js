
function showMsg(msg, color, callback) {
	$("#popup_info").text(msg).css("color", color);
	$(".popup_con").fadeIn('fast', function() {
		setTimeout(function() {
			$(".popup_con").fadeOut('fast', callback);
		}, 2000);
	})
}