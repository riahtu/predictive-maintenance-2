var isLiveStreamPaused = false;
var metricSelected = 'cpu';
var timeChartPointer = 0;
var metricGlobal = '';
var donutDataPointer = 0;

function pushNotification()
{
	console.log('Notification Pushed...');
}

function checkForNotification(readingIdArr)
{
	console.log('Checking for Notifications...');
	var cpuThresholdValue = 92;
	var ramThresholdValue = 60;
	var diskThresholdValue = 590;
	
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
			pattern: ['#3c78b4', '#eb7e26', '#d75151']
		},
		data: {
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
			  // Adding extra time row in the tooltip.
			  var dateValue = "";
			  for(var i=0; i<monitoringData[metricGlobal].Predicted.length;i++)
			  {
				  if(title === monitoringData[metricGlobal].Predicted[i].readingId)
				  {
					  dateValue = monitoringData[metricGlobal].Predicted[i].timestamp;
					  break;
				  }
			  }
			  text += "<tr class='" + $$.CLASS.tooltipName + "-" + lineIndex + "'>";
			  text += "<td class='name'><span style='background-color:#555555'></span>Time</td>";
			  text += "<td class='value'>" + dateValue + "</td>";
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
		axis: {
			y:{
				label: {
					text: 'Y-Axis Label',
					position: 'outer-middle'
				}
				
			},
			x: {
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
		
		// Get metric specific details for data and labels and append below.
		if(!isLiveStreamPaused)
		{
			try
			{
				var metricData = monitoringData[metricGlobal];
				
				for(var i=timeChartPointer;i<timeChartPointer+10;i++)
				{
					actualDataArr.push(metricData.Actual[i].readingValue);
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
				
				timeChartPointer +=10;
				
				timeChart.flow({
							columns: [
									// ['Actual', 40, 800, 25, 150],
									// ['Predicted', 120, 200, 50, 600],
									// ['Issues', 0, 35, 0, 0]
									actualDataArr,
									predictedDataArr,
									issuesDataArr
								],
								// length: 0,
								duration: 15000,
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
				['data', gaugeValue]
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
