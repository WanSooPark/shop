$(window).bind('ready load scroll',function(){
	var wt = $(this).scrollTop();
	$('#wrap .init').each(function () {
		var st = $(this).offset().top - window.innerHeight + (window.innerHeight / 5);
		if (wt > st) {
			$(this).addClass('animate');
		}
	});
});
$(function () {
	$('.tab-area').on('click', '.tab-btns li', function(e){
		var idx =  $(this).index();
		$(this).closest('.tab-area').find('.tab-btns').find('li').removeClass('active');
		$(this).addClass('active');
		$(this).closest('.tab-area').find('.tab-box').removeClass('active').eq(idx).addClass('active');
		return false;
	});
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear: true,
		yearSuffix: '년'
	});
	$(function(){
		$('.datepicker').datepicker({
			dateFormat: "yy-mm-dd"
		}).datepicker("setDate", "0");
	});
	$(document).ready(function() {
		$("#cbx_chkAll").click(function() {
			if($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
			else $("input[name=chk]").prop("checked", false);
		});
		$("input[name=chk]").click(function() {
			var total = $("input[name=chk]").length;
			var checked = $("input[name=chk]:checked").length;
			
			if(total != checked) $("#cbx_chkAll").prop("checked", false);
			else $("#cbx_chkAll").prop("checked", true); 
		});
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
			$('html').css('overflow-y','auto');
		}
	});
});
function modalOpen(modalID){
	$('html').css('overflow-y','hidden');
	$('.modal-container').fadeOut(0);
	$('.modal-container').removeClass('show');
	$(this).data('modal') ? modalID = $(this).data('modal') : '';
	$('.modal-container.show').length > 0 ? $('#' + modalID).css({
		'z-index': $('.modal-container.show').css('z-index') + 1,
		'background': 'transparent'
	}) : '';
	$('#' + modalID).css('display', 'flex').focus();
	$('#' + modalID + ' .modal-wrapper').delay(100).fadeIn(100).focus();
	$('#' + modalID).addClass('show').focus();
	
	return false;
}
function modalClose(){
	$('html').css('overflow-y','auto');
	$(this).closest('.modal-container').fadeOut(0);
	$(this).closest('.modal-container').removeClass('show');
}