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
$(document).ready(function(){
	$('.modalOpen').on('click', modalOpen);
	$('.modalClose').on('click', modalClose);
	$('.modal-container').on('click', function(e){
		if(!$('.modal-wrapper').has(e.target).length){
			$('html').removeClass('modalOn');
			$(this).fadeOut(0);
			$(this).delay(50).fadeOut(0);
			$(this).removeClass('show');

		}
	});
});
function modalOpen(modalID){
	$('html').addClass('modalOn');
	$(this).data('modal') ? modalID = $(this).data('modal') : '';
	$('.modal-container.show').length > 0 ? $('#' + modalID).css({
		'z-index': $('.modal-container.show').css('z-index') + 1,
		'background': 'transparent'
	}) : '';
	$('#' + modalID).css('display', 'flex').focus();
	$('#' + modalID + ' .modal-wrapper').delay(100).fadeIn(100).focus();
	$('#' + modalID).addClass('show').focus();;
}
function modalClose(){
	$('html').removeClass('modalOn');
	$(this).closest('.modal-container').fadeOut(0);
	$(this).closest('.modal-container').removeClass('show');
}