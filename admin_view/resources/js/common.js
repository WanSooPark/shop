$(window).bind('ready load scroll',function(){
	var wt = $(this).scrollTop();
	$('#wrap .init').each(function () {
		var st = $(this).offset().top - window.innerHeight + (window.innerHeight / 4);
		if (wt > st) {
			$(this).addClass('animate');
		}
		if (st >= wt) {
			$(this).removeClass('animate');
		}
	});
});
$(function() {
	$(window).scroll( function() {
		if ($(document).scrollTop() > 0 ) {
			$('#header').addClass('fixed');
		} else {
			$('#header').removeClass('fixed');
		};
	});
})
$(function() {
	$('.tab-area').on('click', '.tab-btn li', function(e){
		var idx =  $(this).index();
		$(this).closest('.tab-area').find('.tab-btn').find('li').removeClass('active');
		$(this).addClass('active');
		$(this).closest('.tab-area').find('.tab-box').removeClass('active').eq(idx).addClass('active');
		return false;
	});
});