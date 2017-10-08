var isLiveStreamPaused = false;
var metricSelected = 'cpu';
var timeChartPointer = 0;
var metricGlobal = '';
var donutDataPointer = 0;

var cpuThresholdValue = 92;
var ramThresholdValue = 60;
var diskThresholdValue = 500;

var initialDate = new Date();

var globalDateArr = [new Date('Sun Oct 08 2017 09:49:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 09:50:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:51:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:52:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:53:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:54:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:55:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:56:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:57:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:58:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 09:59:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 10:00:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:01:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:02:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:03:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:04:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:05:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:06:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:07:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:08:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:09:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:10:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:11:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:12:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:13:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:14:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:15:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:16:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:17:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:18:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:19:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 10:20:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:21:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:22:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:23:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:24:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:25:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:26:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:27:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:28:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:29:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 10:30:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:31:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:32:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:33:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:34:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:35:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:36:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:37:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:38:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:39:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 10:40:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:41:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:42:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:43:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:44:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:45:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:46:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:47:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:48:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:49:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:50:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:51:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:52:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:53:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:54:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:55:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:56:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:57:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:58:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 10:59:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 11:00:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:01:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:02:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:03:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:04:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:05:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:06:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:07:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:08:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:09:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 11:10:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:11:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:12:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:13:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:14:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:15:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:16:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:17:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:18:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:09:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 11:10:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:11:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:12:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:13:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:14:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:15:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:16:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:17:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:18:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:19:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 11:20:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:21:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:22:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:23:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:24:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:25:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:26:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:27:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:28:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:19:57 GMT+0530 (India Standard Time)'), 
						new Date('Sun Oct 08 2017 11:20:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:21:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:22:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:23:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:24:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:25:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:26:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:27:57 GMT+0530 (India Standard Time)'),
						new Date('Sun Oct 08 2017 11:28:57 GMT+0530 (India Standard Time)')];


var startTime = new Date();


function pushNotification()
{
	console.log('Notification Pushed...');
}

function checkForNotification(readingIdArr)
{
	console.log('Checking for Notifications...');
	
	for(var k=1;k<readingIdArr.length;k++)
	{
		try
		{
			if(monitoringData.disk.Actual[readingIdArr[k]] !== null)
			{
				if(monitoringData.disk.Actual[readingIdArr[k]] >= diskThresholdValue)
					pushNotification();
			}
			else
			{
				if(monitoringData.disk.Predicted[readingIdArr[k]] >= diskThresholdValue)
					pushNotification();
			}
			
			if(monitoringData.cpu.Actual[readingIdArr[k]] !== null)
			{
				if(monitoringData.cpu.Actual[readingIdArr[k]] >= cpuThresholdValue)
					pushNotification();
			}
			else
			{
				if(monitoringData.cpu.Predicted[readingIdArr[k]] >= cpuThresholdValue)
					pushNotification();
			}
			
			if(monitoringData.ram.Actual[readingIdArr[k]] !== null)
			{
				if(monitoringData.ram.Actual[readingIdArr[k]] >= ramThresholdValue)
					pushNotification();
			}
			else
			{
				if(monitoringData.ram.Predicted[readingIdArr[k]] >= ramThresholdValue)
					pushNotification();
			}
		}
		catch(e)
		{
			console.log(e);
		}
	}
}

function triggerDashboardChange(metric)
{
	if(metricGlobal !== '')
	{
		console.log('Selected Metric: '+metric);
		timeChartPointer = 0;
		donutDataPointer = 0;
		isLiveStreamPaused = false;
		timeChart.destroy();
		initializeTimeChart();
		initializeDonut('Sample Donut', [{key:'People who love Snooker', value:100}]);
		loadChartData();
	}
	else
	{
		alert('Select the Metric from Dropdown');
	}
}


var timeChart = null;

initializeTimeChart();

function initializeTimeChart()
{
	timeChart = c3.generate({
		bindto: '#chartData',
		color: {
			pattern: ['#3c78b4', '#eb7e26', '#ccffcc', '#d75151']
		},
		data: {
			x:'x',
			columns: [
			
			  ]
		},
		
		tooltip: {
			contents: function(d, defaultTitleFormat, defaultValueFormat, color){
				var $$ = this, config = $$.config,
				  titleFormat = config.tooltip_format_title || defaultTitleFormat,
				  nameFormat = config.tooltip_format_name || function (name) { return name; },
				  valueFormat = config.tooltip_format_value || defaultValueFormat,
				  text, i, title, value, name, bgcolor, lineIndex;
			  for (i = 0; i < d.length; i++) {
				  if (! (d[i] && (d[i].value || d[i].value === 0))) { continue; }
				  lineIndex = d[i].id
				  if (! text) {
					  title = titleFormat ? titleFormat(d[i].x) : d[i].x;
					  text = "<table style='color:black;' class='" + $$.CLASS.tooltip + "'>" + (title || title === 0 ? "<tr><th style='color:black;' colspan='2'>Time: " + title + "</th></tr>" : "");
				  }

				  name = nameFormat(d[i].name);
				  value = valueFormat(d[i].value, d[i].ratio, d[i].id, d[i].index);
				  bgcolor = $$.levelColor ? $$.levelColor(d[i].value) : color(d[i].id);

				  text += "<tr class='" + $$.CLASS.tooltipName + "-" + d[i].id + "'>";
				  text += "<td class='name'><span style='background-color:" + bgcolor + "'></span>" + name + "</td>";
				  text += "<td class='value'>" + value + "</td>";
				  text += "</tr>";
			  }
			  
			  text += "<tr class='" + $$.CLASS.tooltipName + "-" + lineIndex + "'>";
			  text += "<td class='name'><span style='background-color:#555555'></span>Time</td>";
			  text += "<td class='value'>" + formatTimestampToYearTime(d[0].x) + "</td>";
			  text += "</tr>";
			  
			  return text + "</table>";
			},
			format: {}
		},
		grid:{
			x: {
				show: false
			},
			y: {
				show: true
			}
		},
		zoom: {
			enabled: false
		},
		axis: {
			y:{
				label: {
					text: 'Y-Axis Label',
					position: 'outer-middle'
				}
				
			},
			x: {
				type:'timeseries',
				tick:{
					// format:'%Y-%m-%d'
					format: function(x){
						// return x.getDate() +'/'+ x.getMonth() +' '+ x.getHours() + ':'+ x.getMinutes() +':'+ x.getSeconds();
						return x.getHours() + ':'+ x.getMinutes() +':'+ x.getSeconds();
						// return x;
					}
				},
				label: {
					text: 'X-Axis Label',
					position: 'outer-middle'
				}
			}
		},
		subchart: {
			show: false
		}
	});
}

// setTimeout(function(){
	// timeChart.flow({
                // columns: [
						// ['Actual', 40, 800, 25, 150],
						// ['Predicted', 120, 200, 50, 600],
						// ['Issues', 0, 35, 0, 0]
					// ],
					// // length: 0,
					// duration: 10000,
					// done:loadChartData
				// });
				
	// timeChart.axis.labels({
		  // x: 'New X Axis Label',
		  // y: 'New Y Axis Label'
		// });
	// }, 2500);


function formatTimestampToYearTime(date) {
	var d = new Date(date),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();
	hours = d.getHours();
	minutes = d.getMinutes();
	seconds = d.getSeconds();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	var formattedTime = [year, month, day].join('-') +' '+ [hours, minutes, seconds].join(':');
	return formattedTime;
}
	
function formatDate(date) {
	var d = new Date(date),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();
	hours = d.getHours();
	minutes = d.getMinutes();
	seconds = d.getSeconds();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	var formattedTime = [year, month, day].join('-');// +' '+ [hours, minutes, seconds].join(':');
	return formattedTime;
}

function loadChartData()
{
	console.log("loadChartData() called with metric: "+metricGlobal);
	//timeChartPointer
	if(metricGlobal !== '')
	{
		var actualDataArr = ["Actual"];
		var predictedDataArr = ["Predicted"];
		var issuesDataArr = ["Issues"];
		var readingIdArr = ["IDArray"];
		var thresholdDataArr = ["Threshold"];
		var dateArr = ['x'];
		
		// Get metric specific details for data and labels and append below.
		if(!isLiveStreamPaused)
		{
			try
			{
				var metricData = monitoringData[metricGlobal];
				
				var metricData2 = metricInfo.dataArr;
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					// actualDataArr.push(metricData.Actual[i].readingValue);
					actualDataArr.push(metricData2[i].actual);
				}
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					predictedDataArr.push(metricData.Predicted[i].readingValue);
				}
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					issuesDataArr.push(metricData.Issues[i].readingValue);
				}
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					readingIdArr.push(metricData.Issues[i].readingId);
				}
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					var thresholdValue = 0;
					if(metricGlobal === 'cpu')
						thresholdDataArr.push(cpuThresholdValue);
					if(metricGlobal === 'ram')
						thresholdDataArr.push(ramThresholdValue);
					if(metricGlobal === 'disk')
						thresholdDataArr.push(diskThresholdValue);
				}
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					// var datedate = startTime;
					// dateArr.push(globalDateArr[i]);
					dateArr.push(new Date(metricData2[i].date));
					
					
					
				}
				
				
				timeChartPointer +=10;
				
				timeChart.flow({
							columns: [
									// ['Actual', 40, 800, 25, 150],
									// ['Predicted', 120, 200, 50, 600],
									// ['Issues', 0, 35, 0, 0]
									dateArr,
									actualDataArr,
									// predictedDataArr,
									// issuesDataArr,
									// thresholdDataArr
								],
							// length: 0,
							duration: 2000,
							done:loadChartData
						});
				
				// Setting X and Y Axis legends
				timeChart.axis.labels({
					  x: metricData.XLabel,
					  y: metricData.YLabel
					});
					
			}
			catch(e)
			{
				console.log(e);
				console.log('ERROR while reading Timeseries Data..');
			}
		}
		
		
		// Plot other Dashboard Elements based on Real-time data coming as part of Timeseries Chart.
		// ---- Plotting Donut for Key-Influencers
		console.log('Fetching Donut Data for metric: '+metricGlobal);
		var donutData = keyInfluencerData[metricGlobal][donutDataPointer];
		var donutValueArr = [{key:donutData.label1, value:donutData.value1}, {key:donutData.label2, value:donutData.value2}, {key:donutData.label3, value:donutData.value3}, {key:donutData.label4, value:donutData.value4}];
		initializeDonut('Key Influencer', donutValueArr);
		donutDataPointer += 1;
		
		// ---- Plotting Gauge Chart
		console.log('Determining Gauge Chart Value from Time-series data for metric: '+metricGlobal);
		var gaugeValue = 0;
		
		switch(metricGlobal)
		{
			case 'cpu':
				{
					var sum = 0;
					for(var j=1;j<actualDataArr.length;j++)
					{
						sum +=actualDataArr[j];
					}
					gaugeValue = sum/(actualDataArr.length-1);
				}
				break;
			case 'ram':
				{
					var sumPercent = 0;
					var overallRAMCapacity = 64;
					for(var j=1;j<actualDataArr.length;j++)
					{
						sumPercent += (actualDataArr[j]/overallRAMCapacity)*100;
					}
					gaugeValue = sumPercent/(actualDataArr.length-1);
				}
				break;
			case 'disk':
				{
					var sumPercent = 0;
					var overallDISKCapacity = 650;
					for(var j=1;j<actualDataArr.length;j++)
					{
						sumPercent += (actualDataArr[j]/overallDISKCapacity)*100;
					}
					gaugeValue = sumPercent/(actualDataArr.length-1);
				}
				break;
			default:
				alert("not a valid metric");
		}
		//var gaugeValue = actualDataArr[5]/650;
		initializeGauge(Math.ceil(gaugeValue));
		
		
		// ---- Updating the Metric Cards Panel
		console.log('Determining the Overall Data for Health Cards');
		var overallCPUHealth = 0;
		var overallRAMHealth = 0;
		var overallDiskHealth = 0;
		var cpuSum = 0;
		var ramSum = 0;
		var diskSum = 0;
		var overallRAMCapacity = 64;
		var overallDISKCapacity = 650;
		
		for(var k=1;k<readingIdArr.length;k++)
		{
			try
			{
				diskSum += (monitoringData.disk.Actual[readingIdArr[k]].readingValue/overallDISKCapacity)*100;
				cpuSum += monitoringData.cpu.Actual[readingIdArr[k]].readingValue;
				ramSum += (monitoringData.ram.Actual[readingIdArr[k]].readingValue/overallRAMCapacity)*100;				
			}
			catch(e)
			{
				console.log(e);
			}
		}
		overallCPUHealth = cpuSum/(readingIdArr.length-1);
		overallRAMHealth = ramSum/(readingIdArr.length-1);
		overallDiskHealth = diskSum/(readingIdArr.length-1);
		
		$('#overallCPUValue').text((isNaN(overallCPUHealth)==true)?'_%':Math.ceil(overallCPUHealth)+'%');
		$('#overallRAMValue').text((isNaN(overallRAMHealth)==true)?'_%':Math.ceil(overallRAMHealth)+'%');
		$('#overallDiskValue').text((isNaN(overallDiskHealth)==true)?'_%':Math.ceil(overallDiskHealth)+'%');
		var overallHealthIndex = ((100 - overallCPUHealth) + (100 - overallRAMHealth) + (100 - overallDiskHealth))/3;
		$('#overallHealthValue').text((isNaN(overallHealthIndex)==true)?'_%':Math.ceil(overallHealthIndex)+'%');

		
		// ---- Managing the Notifications
		checkForNotification(readingIdArr);
		
	}
	else
	{
		alert('Select the Metric from Dropdown');
	}
}
// ################

var donutChart = null;
initializeDonut('Sample Donut', [{key:'People who love Snooker', value:100}]);
function initializeDonut(title, valuePairArr)
{
	var donutDataArr = [];
	
	for(var i=0;i<valuePairArr.length;i++)
	{
		donutDataArr.push([valuePairArr[i].key, valuePairArr[i].value]);
	}
	
	donutChart = c3.generate({
		bindto: '#chartUsage',
		color: {
			pattern: ['#3e78b4', '#e87d29', '#673AB7', '#009688', '#4ca02f']
		},
		
		data: {
			// columns: [
				// ['People who Love Snooker', 100]
			// ],
			columns: donutDataArr,
			type : 'donut',
			onclick: function (d, i) { console.log("onclick", d, i); },
			onmouseover: function (d, i) { console.log("onmouseover", d, i); },
			onmouseout: function (d, i) { console.log("onmouseout", d, i); }
		},
		donut: {
			title: title
		},
		tooltip: {
			contents: function(d, defaultTitleFormat, defaultValueFormat, color){
				var $$ = this, config = $$.config,
				  titleFormat = config.tooltip_format_title || defaultTitleFormat,
				  nameFormat = config.tooltip_format_name || function (name) { return name; },
				  valueFormat = config.tooltip_format_value || defaultValueFormat,
				  text, i, title, value, name, bgcolor, lineIndex;
			  for (i = 0; i < d.length; i++) {
				  if (! (d[i] && (d[i].value || d[i].value === 0))) { continue; }
				  lineIndex = d[i].id
				  if (! text) {
					  title = titleFormat ? titleFormat(d[i].x) : d[i].x;
					  text = "<table style='color:black;' class='" + $$.CLASS.tooltip + "'>" + (title || title === 0 ? "<tr><th style='color:black;' colspan='2'>Reading ID: " + title + "</th></tr>" : "");
				  }

				  name = nameFormat(d[i].name);
				  value = valueFormat(d[i].value, d[i].ratio, d[i].id, d[i].index);
				  bgcolor = $$.levelColor ? $$.levelColor(d[i].value) : color(d[i].id);

				  text += "<tr class='" + $$.CLASS.tooltipName + "-" + d[i].id + "'>";
				  text += "<td class='name'><span style='background-color:" + bgcolor + "'></span>" + name + "</td>";
				  text += "<td class='value'>" + value + "</td>";
				  text += "</tr>";
			  }
			  
			  return text + "</table>";
			},
			format: {}
		}
	});
}

// setTimeout(function () {
    // chart.load({
        // columns: [
            // ["CPU Usage", 25],
            // ["Threads Running", 45],
            // ["Service Instance", 15],
			// ["Disk Usage", 15]
        // ]
    // });
// }, 1500);



// setTimeout(function () {
    // chart.unload({
        // ids: 'data1'
    // });
    // chart.unload({
        // ids: 'data2'
    // });
// }, 2500);

// ################

var gaugeChart = null;
initializeGauge(0);
function initializeGauge(gaugeValue)
{
	gaugeChart = c3.generate({
		bindto: '#chartGauge',
		data: {
			columns: [
				[metricGlobal.toUpperCase()+' Usage', gaugeValue]
			],
			type: 'gauge',
			onclick: function (d, i) { console.log("onclick", d, i); },
			onmouseover: function (d, i) { console.log("onmouseover", d, i); },
			onmouseout: function (d, i) { console.log("onmouseout", d, i); }
		},
		gauge: {
			   label: {
				   format: function(value, ratio) {
					   return value + '%';
				   },
				   show: true // to turn off the min/max labels.
			   },
		   min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
		   max: 100, // 100 is default
		   // units: '%',
		   width: 90 // for adjusting arc thickness
		},
		color: {
			pattern: ['#60B044', '#F6C600', '#F97600', '#FF0000'], // the three color levels for the percentage values.
			threshold: {
	//            unit: 'value', // percentage is default
	//            max: 200, // 100 is default
				values: [30, 60, 90, 100]
			}
		},
		size: {
			height: 320
		},
		label: {
			format: function (value, ratio) {
			  return value + 'm';
			}
		},
		tooltip: {
			contents: function(d, defaultTitleFormat, defaultValueFormat, color){
				var $$ = this, config = $$.config,
				  titleFormat = config.tooltip_format_title || defaultTitleFormat,
				  nameFormat = config.tooltip_format_name || function (name) { return name; },
				  valueFormat = config.tooltip_format_value || defaultValueFormat,
				  text, i, title, value, name, bgcolor, lineIndex;
			  for (i = 0; i < d.length; i++) {
				  if (! (d[i] && (d[i].value || d[i].value === 0))) { continue; }
				  lineIndex = d[i].id
				  if (! text) {
					  title = titleFormat ? titleFormat(d[i].x) : d[i].x;
					  text = "<table style='color:black;' class='" + $$.CLASS.tooltip + "'>" + (title || title === 0 ? "<tr><th style='color:black;' colspan='2'>Reading ID: " + title + "</th></tr>" : "");
				  }

				  name = nameFormat(d[i].name);
				  value = valueFormat(d[i].value, d[i].ratio, d[i].id, d[i].index);
				  bgcolor = $$.levelColor ? $$.levelColor(d[i].value) : color(d[i].id);

				  text += "<tr class='" + $$.CLASS.tooltipName + "-" + d[i].id + "'>";
				  text += "<td class='name'><span style='background-color:" + bgcolor + "'></span>" + name + "</td>";
				  text += "<td class='value'>" + value + "</td>";
				  text += "</tr>";
			  }
			  
			  return text + "</table>";
			}
		}
	});
}
// setTimeout(function () {
    // gaugeChart.load({
        // columns: [['data', 10]]
    // });
// }, 1000);

// setTimeout(function () {
    // gaugeChart.load({
        // columns: [['data', 50]]
    // });
// }, 2000);

// setTimeout(function () {
    // gaugeChart.load({
        // columns: [['data', 55]]
    // });
// }, 3000);

// setTimeout(function () {
    // gaugeChart.load({
        // columns: [['data', 0]]
    // });
// }, 4000);

// setTimeout(function () {
    // gaugeChart.load({
        // columns: [['data', 70]]
    // });
// }, 5000);
