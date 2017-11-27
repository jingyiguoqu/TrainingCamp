//教室页面
function classseat(btn){
	$('#main_info').css('display', 'none');
	$('#seatInfo').css('display', 'block');
	$('#table_container').css('display', 'none');
	$("#add_user").css("display","none");
	//绑定seat-id
	for(var i=1;i<=25;i++){
		$("#seat"+i).attr("seat-id",i);
	}
	
	var id=$(btn).attr("data-id");
	
	//给教室页面绑定tcid
	$(".seatInfo").attr("tc-id",id);
	//调用显示训练营id的方法
	tcTxt(id);
	//调用显示学生的方法
	showstu(id);
}
//显示训练营信息的方法
function tcTxt(id) {
	$.ajax({
		"url":"loadtcseat.do?tcId="+id,
		"type":"post",
		"dataType":"json",
		"success":function(msg){
			var data=msg.data;
			$('#SeatClassName').html(data.className);
			$('#SeatUserName').html(data.username);
			$('#SeatNum').html(data.numPeople+"/"+(data.seatNum-data.numPeople));
			$('#SeatDate').html(data.createdTime);
			if(data.seatType=="A"){
				$('.middle').attr("class", "left middle");
			}else if(data.seatType=="B"){
				$('.middle').attr("class", "right middle");
			}
		}
	});
}

//教室显示学生页面的方法
function showstu(id) {
	
	clearstu();
	
	$.ajax({
		"url":"seatlist.do?tcId="+id,
		"type":"post",
		"dataType":"json",
		"success":function(msg){
			var data=msg.data;
			for(var i=0;i<data.length;i++){
				var s=data[i];
				$("#seat"+s.serialNum).find("span").eq(0).html(s.name);
				$("#seat"+s.serialNum).find("span").eq(1).html(s.username);
				if(s.learnPro=="D"){
					$("#seat"+s.serialNum).css("background-image","url(img/seat/D.png)");
				}else{
					$("#seat"+s.serialNum).css("background-image","url(img/seat/unAvailable.png)");
				}
				$("#seat"+s.serialNum).attr("data-id",s.studentId);
			}
		}
	});
}
//隐藏选座
function hid_bookingseat() {
	$(".bookingSeats").css('display', 'none');
}
//显示学生信息
$(".seat_details").find("div").click(function(){
	$temp=$(this).attr("data-id");
	var stuid=$(this).attr("data-id");
	//检查该属性是否存在
	if(typeof($temp)=="undefined"){//要加typeof()
		 $(".stu_details").css('display', 'none');
		 var seatid=$(this).attr("id");
		 seatnum=seatid.split("seat")[1];
		 $(".bookingSeats").css('display', 'block');
		 $(".bookingSeats").attr("seat-id",seatid);
		 var url="unseatstu.do";
		 $(".stu_list_ul").empty();
		 $.getJSON(url,function(data){
			 console.log(data.length);
			 var a=data.length-1;
			 for(var i=0;i<a;i++){
				 if(i%2!=1){
					 var stu=data[i];
					 var stu1=data[i+1];
					 var li=$(
				    	  "<li>" +
				    	  "<p><label>"+stu.name+"</label>" +
				    	  "<input type='radio' name='stu_name' id='stu_name' value='"+stu.studentId+"'/>&nbsp;" +
				    	  "<label>"+stu1.name+"</label>" +
				    	  "<input type='radio' name='stu_name' id='stu_name' value='"+stu1.studentId+"'/></p>" +
				    	  "</li>"
					 		);
					
				 }
				 $(".stu_list_ul").append(li);
			 }
			 if(a%2==0){
				 var stu=data[a];
				 var li=$(
				    	  "<li>" +
				    	  "<p><label>"+stu.name+"</label>" +
				    	  "<input type='radio' name='stu_name' id='stu_name' value='"+stu.studentId+"'/>" +
				    	  "</p></li>"
					 		);
				 $(".stu_list_ul").append(li);
			 }
			 
		});
		var tcid= $(".seatInfo").attr("tc-id"); 
		//绑定教室id和座位id给订座按钮
		$("#bookingstu").attr("tc-id",tcid);
		$("#bookingstu").attr("seat-id",seatnum);
	}else{
		$(".stu_details").css('display', 'block');
		$(".stu_details").find("span").eq(0).html($(this).attr("seat-id"));
		$.ajax({
			"url":"seatstu.do?studentId="+stuid,
			"type":"post",
			"dataType":"json",
			"success":function(msg){
				var data=msg.data;
				for(var i=1;i<=data.length;i++){
					$(".stu_details").find("span").eq(i).html(data[i-1]);
				}
			}
		
		});
		var id=$(".seatInfo").attr("tc-id");
		//给取消按钮绑定tcid和stuid
		$("#removestu").attr("tc-id",id);
		$("#removestu").attr("stu-id",stuid);
	}	
})


//清除学生信息
function clearstu() {
	for(var i=1;i<=25;i++){
		$("#seat"+i).find("span").eq(0).html("");
		$("#seat"+i).find("span").eq(1).html("");
		$("#seat"+i).css("background-image","url(img/seat/available.png)");
		$("#seat"+i).removeAttr("data-id");
		$(".stu_details").css('display', 'none');
	}
}

//取消座位
function removestu() {
	var tcId=$("#removestu").attr("tc-id");
	var studentId=$("#removestu").attr("stu-id");
	var url="removestu.do?tcId="+tcId+"&studentId="+studentId;
	$.getJSON(url,function(data){
		tcTxt(tcId);
		showstu(tcId);
	});
	
}

//点击订座按钮发生的事件
function bookingstu() {
	var tcId=$("#bookingstu").attr("tc-id");
	var serialNum=$("#bookingstu").attr("seat-id");
	var studentId=$("input[type='radio']:checked").val();
//	console.log(tcId);
//	console.log(serialNum);
//	console.log(studentId);
	var url="bookingstu.do?tcId="+tcId+"&studentId="+studentId+"&serialNum="+serialNum;
	$.getJSON(url,function(data){
		tcTxt(tcId);
		showstu(tcId);
		$(".bookingSeats").css('display', 'none');
	});
}