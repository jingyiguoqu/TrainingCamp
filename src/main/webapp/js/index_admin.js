function show_add_tc() {
	$('#add_tc').css('display', 'block');
	$("#main_info").css('-webkit-filter','blur(2px)');
}
function hidden_add_tc() {
	$('#add_tc').css('display', 'none');
	$("#main_info").css('-webkit-filter','');
}
function hidden_update_tc() {
	$('#update_tc').css('display', 'none');
	$("#main_info").css('-webkit-filter','');
}
function show_update_pwd() {
	$('#updatepwd').css('display', 'flex');
	$("#main_info").css('-webkit-filter','blur(2px)');
}
function hidden_update_pwd() {
	$('#updatepwd').css('display', 'none');
	$("#main_info").css('-webkit-filter','');
}
function show_add_user(){
	$('#add_user').css('display', 'block');
}
function hidden_add_user(){
	$('#add_user').css('display', 'none');
}
function hidden_add_stu(){
	$('#add_stu').css('display', 'none');
}
function show_add_stu(){
	$('#add_stu').css('display', 'block');
}
function menu_li_addCss(li){
	//去掉其他li的样式
	var li_list = $(li).siblings();
	for (var i = 0; i < li_list.length; i++) {
		$(li_list[i]).css('background','none');
		$(li_list[i]).children('div').css('visibility','hidden');
		$(li_list[i]).children('span').css('color','#76dad2');
		var src = $(li_list[i]).children('img').attr('src');
		var index = src.lastIndexOf('_');
		src = src.slice(0,index+1) + 'before.png';
		$(li_list[i]).children('img').attr('src',src);
	}
	//设置选中样式
	$(li).css('background','#3f6d95');
	$(li).children('div').css('visibility','inherit');
	$(li).children('span').css('color','#d6dde5');
	var src = $(li).children('img').attr('src');
	var index = src.lastIndexOf('_');
	src = src.slice(0,index+1) + 'after.png';
	$(li).children('img').attr('src',src);
}
//绑定tcid给后台并获取数据
$(".check-click").click(function(){
	var id=$(this).attr("data-id");
	console.log(id);
	$('#update_tc').css('display', 'block');
	$("#main_info").css('-webkit-filter','blur(2px)');
	$.ajax({
		"url":"loadtc.do?tcId="+id,
		"type":"post",
		"dataType":"json",
		"success":function(msg){
			var data=msg.data;
			console.log(data);
			$('#className1').val(data.className);
			$('#username1').val(data.username);
			$('#course1').val(data.type);
			$("input[name=seatType][value="+data.seatType+"]").attr("checked",true);
			$('#tcIdupdate').val(id);
			console.log($('#tcIdupdate').val());
		}
	});
})



//跳转到训练营页面
$(".tcmanager").parent().click(function(){
	$('#main_info').css('display', 'block');
	$('#seatInfo').css('display', 'none');
	$('#table_container').css('display', 'none');
	$("#add_user").css("display","none");
	$("#table_container_stu").css("display","none");
	hidden_update_pwd();
	//清除学生信息
	clearstu();
	$("#main_info").css('-webkit-filter','');
	location="toIndex.do";
})

//退出登录
function signout() {
	location='signout.do';
}
//跳转到修改用户页面
$(".usermanage").parent().click(function(){
	$('#main_info').css('display', 'none');
	$('#seatInfo').css('display', 'none');
	$('#table_container').css('display', 'block');
	$('#add_tc').css('display', 'none');
	$('#update_tc').css('display', 'none');
	$("#add_user").css("display","none");
	$("#table_container_stu").css("display","none");
	hidden_update_pwd();
	$("#userpage").html(1);
	var num=0;
	userlist(num);
})
//跳转到修改学生页面
$(".stumanager").parent().click(function(){
	console.log("stumanager");
	$('#main_info').css('display', 'none');
	$('#seatInfo').css('display', 'none');
	$('#table_container').css('display', 'none');
	$('#add_tc').css('display', 'none');
	$('#update_tc').css('display', 'none');
	$("#add_user").css("display","none");
	$("#table_container_stu").css('display','block');
	getStuList();
})
//判断是否登录
//如果卡的话就删掉
		$("#body").click(function(){
			var temp=false;
			$.ajax({
				async:false,
				type:"GET",
				url:'checksign.do',
				"success":function(msg){
					var data=msg.data;
					temp=data;
				}
			});
			if(temp!=true){
				alert("登录信息已过期,请重新登录");
				location="toLogin.do";
			}
		})
		
		
		
//点开页面判断是不是管理员
window.onload=function(){
		menu_li_addCss($('#menu_ul li')[0]);
		var jobId=$("#body").attr("job-id");
		if(jobId==1){
			$(".usermanage").css("display","block");
			$("#addtcicon").css("display","block");
			$(".option_img").css("display","block");
		}
	}




//显示历史训练营
$(".histclist").click(function() {
	$('#main_info').css('display', 'block');
	$('#seatInfo').css('display', 'none');
	$('#table_container').css('display', 'none');
	$("#add_user").css("display","none");
	$("#table_container_stu").css("display","none");
	hidden_update_pwd();
	//清除学生信息
	clearstu();
	$("#main_info").css('-webkit-filter','');
	$("#main_info").empty();
	var url="histclist.do";
	$.getJSON(url,function(msg){
		data=msg.data;
		for(var i=0;i<data.length;i++){
			var e=data[i];
			var tc=$(
					"<div class='tcInfo'  id='' his-tc='1'>"+
					"<div class='tcInfo_img tcInfoClass' onclick='classseat(this);' data-id='"+e.tcId+"' style='background-image:url(img/index_admin/"+e.type+".png);'>"+
						"<div class='main_mess'>"+
							"<span id='course'>"+e.type+"</span><br />"+
							"<label>讲师:</label>"+
							"<span id='teacher'>"+e.username+"</span><br />"+
							"<label>教室:</label>"+
							"<span id='classroom'>"+e.className+"</span><br />"+
							"<label>已座/总数:</label>"+
							"<span id='num_mess'>"+e.numPeople+"/"+e.seatNum+"</span><br />"+
						"</div>"+
						"<div class='create_time'>"+
							"<label>创建时间:</label>"+
							"<span id='created_time'>"+e.createdTime+
							"</span><br />"+
						"</div>"+
					"</div>"+
				"</div>"		
			);
			$("#main_info").append(tc);
		}
	});
	
	
})

//点击修改密码
function password1() {
	hidden_update_pwd();
	alert("修改成功!");
}
