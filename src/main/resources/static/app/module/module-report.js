ModuleReport = (function() {
	window.charts = {};
	var api = {
		category: '/report/category',
		categoryTime:'/report/time/category',
		project: '/report/project',
		projectTime: '/report/time/project',
		location: '/report/location',
		locationTime: '/report/time/location',
		updateChartConfig: '/report/config/save'
	}
	function showReport(_config) {
		$('#chart_container').append(getContainer(_config.key, _config));
		loadReport(_config.key, _config.type, _config.title, _config.muti)
	}
	function getContainer(_id, _config) {
		return '<div class="jumbotron">'
		    +'		<div class="container">'
		    +'			<div class="row">'+getChartConfigHtml(_config)+'</div>'
		    +'  		<div class="row">'
		    +'    			<div>'
		    +'      			<canvas id="'+_id+'"></canvas>'
		    +'    			</div>'
		    +'  		</div>'
		    +'		</div>'
			+'	</div>';
	}
	function getChartConfigHtml(_config) {
		var checkedStatus = _config.enabled ? 'checked="checked"' : '';
		var html = '<form class="form-inline">'+
          '<div class="col-lg-12">'+
            '<div class="form-group col-lg-1">'+
              '<button class="btn btn-default btn-chart-config" type="button" title="Config">'+
                '<span class="glyphicon glyphicon-cog"></span>'+
              '</button>'+
            '</div>'+
            '<div class="form-group form-edit col-lg-2">'+
              '<label>'+
                '<input type="checkbox" class="form-inline-checkbox" '+checkedStatus+'>Show This Chart'+
              '</label>'+
            '</div>'+
            '<div class="form-group form-edit col-lg-4">'+
              '<label for="exampleInputEmail1">Chart Title: </label>'+
              '<input type="text" class="form-control" placeholder="Chart Title" value="'+_config.title+'">'+
            '</div>'+
            '<div class="form-group form-edit col-lg-1">'+
              '<button type="submit" class="btn btn-default">Save</button>'+
            '</div>'+
          '</div> '+
        '</form>';
        return html;
	}
	function loadReport(_key, _type, _title, _muti) {
		var _url = api[_key];
		$.ajax({
	        type: "GET",
	        url: _url,
	        data: null,
	        success: function(data) {
	        	if (data) {
	        		var configData = initData(_type, data, _muti);
			        var ctx = document.getElementById(_key).getContext("2d");
			        var config = initConfig(_type, configData, null, _title);
		            window.charts[_key] = new Chart(ctx, config);
	        	}
	        }
	    });
	}
	function updateChartConfig() {
		$.ajax({
	        type: "POST",
	        url: _url,
	        data: null,
	        success: function(data) {
	        	if (data) {
	        		
	        	}
	        }
	    });
	}
	function initData(_type, _data, _muti) {
		var label = [];
        var datasets = [];
        label = _data.data.labels;
      	var ds = _data.data.dataset;
      	for (var key in ds) {
      		var dataset = {};
      		dataset.label = key;
      		dataset.data = ds[key];
      		if (_type == 'bar') {
      			dataset.backgroundColor = "rgba(220,220,220,0.5)";
      		} else if (_type == 'line') {
      			dataset.fill = false;
	      		dataset.borderColor = Lib_Random.randomColor(0.4);
	      		dataset.backgroundColor = Lib_Random.randomColor(0.5);
	      		dataset.pointBorderColor = Lib_Random.randomColor(0.7);
	      		dataset.pointBackgroundColor = Lib_Random.randomColor(0.5);
	      		dataset.pointBorderWidth = 1;
      		}
      		datasets.push(dataset);
      	}
		var data = {
            labels: label,
            datasets: datasets
        };
      	return data;
	}
	function initConfig(_type, _data, _option, _title) {
		var c = {
			type: _type||'bar',
            data: _data||[],
            options: _option||{
                elements: {
                    rectangle: {
                        borderWidth: 2,
                        borderColor: 'rgb(0, 255, 0)',
                        borderSkipped: 'bottom'
                    }
                },
                responsive: true,
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: _title||'TimeItem时间统计报告'
                }
            }
		};
		return c;
	}
	
	return {
		getContainer:getContainer,
		showReport: showReport
	}
})();