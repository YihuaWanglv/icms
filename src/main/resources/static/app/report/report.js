$(function() {
	// if(!Object.keys) Object.keys = function(o){
	//    if (o !== Object(o))
	//       throw new TypeError('Object.keys called on non-object');
	//    var ret=[],p;
	//    for(p in o) if(Object.prototype.hasOwnProperty.call(o,p)) ret.push(p);
	//    return ret;
	// }
	$.ajax({
		type : "GET",
		url : "/data/user/config",
		data : null,
		success : function(data) {
			console.log(data);
			if (data) {
				for (var i = 0; i < data.reportConfigs.length; i++) {
					var config = data.reportConfigs[i];
					if (config && config.enabled) {
						ModuleReport.showReport(config);
					}
				}
			}
		}
	});
	$('.btn-chart-config').on('click', function(){
		if ($('.form-edit').hasClass('hidden')) {
			$('.form-edit').removeClass('hidden');
		} else {
			$('.form-edit').addClass('hidden');
		}
	});

});