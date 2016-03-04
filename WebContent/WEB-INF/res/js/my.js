(function($) {
	// 备份jquery的ajax方法
	var _ajax = $.ajax;
	// 重写jquery的ajax方法
	$.ajax = function(options) {
		var complete = options.complete;
		var success = options.success;
		options.success = function(data) {
			/*if(data != null && data.message != null && data.message != ""){
			}*/
			if (data.hasSuccess == false) {
				alert(data.msg);
			}else{
				
				$('#toast').fadeIn(500); 
				$('#toast').fadeOut(500);
				if (success) {
					success(data);
				}
			}			
		};
		options.complete = function(httpRequest, status) {
			$('#loadingToast').hide();
			if (complete) {
				complete(httpRequest, status);
			}
		};
		var error = options.error;
		options.error = function(XMLHttpRequest, msg, e) {
			if (error) {
				error(XMLHttpRequest, msg, e);
			}
		};
		options.async = true;
		$('#loadingToast').show();
		return _ajax(options);
	}
})(jQuery);