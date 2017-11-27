//创建表格
//head:表头字段数组,data:表类容,形如test方法(最下面)中的data
function createTable(head, data) {
	if(head & data) {
		return "";
	}
	var html = "<tbody><tr>";
	for(var i = 0; i < head.length; i++) {
		html += "<th>" + head[i] + '</th>'
	}
	html += "<th class = 'edit_delete'>编辑</th><th class = 'edit_delete'>删除</th></tr>";
	for(var j = 0; j < data.length && j < 10; j++) {
		html += "<tr>"
		for(var k = 0; k < data[j].length-1; k++) {
			html += "<td><input type='text' readonly='readonly' value='" +
				data[j][k] + "' /></td>"
		}
		html += "<td class = 'edit_delete'><a href='javascript:edit_admin(" + j + ",1)' id='a"+j+"' user-id="+data[j][data[j].length-1]+">编辑</a></td>";
		html += "<td class = 'edit_delete'><a href='javascript:delete_admin(" + j + ",1)' user-id='"+data[j][data[j].length-1]+"'>删除</a></td></tr>";
	}
	document.getElementById("admin_table").innerHTML = html + '</tbody>';
	$('#table_css table tr:even').css('background-color','#f5f5f5');
}
//编辑变成保存，并把输入框变成可输入
function edit_admin(index,flag) {
	//记住当前点击行的坐标
	edit_save(false, 'save_admin(' + index + ','+flag+')', '保存', index);
	
	
}
//保存变为只读
function save_admin(index,flag) {
	//ajax保存数据
	console.log(flag);
	//修改新建行readonly属性
	if(flag==1){
		edit_save('readonly', 'edit_admin(' + index + ','+flag+')', '编辑', index);
		var userId=$("#a"+index).attr("user-id");
		var username=$("#a"+index).parent().siblings().eq(0).find("input").val();
		var password=$("#a"+index).parent().siblings().eq(1).find("input").val();
		var jobName=$("#a"+index).parent().siblings().eq(2).find("input").val();
		var phone=$("#a"+index).parent().siblings().eq(3).find("input").val();
		$.ajax({
			"url":"userupdate.do?username="+username+"&userId="+userId+"&password="+password+"&jobName="+jobName+"&phone="+phone,
			"type":"post",
			"dataType":"json",
			"success":function(){
				
			}
		});
		alert('保存成功');
	}else if(flag==0){
		var username=$("#add_new").children().siblings().eq(0).find("input").val();
		var password=$("#add_new").children().siblings().eq(1).find("input").val();
		var jobName=$("#add_new").children().siblings().eq(2).find("input").val();
		var phone=$("#add_new").children().siblings().eq(3).find("input").val();
		var url="usersave.do?username="+username+"&password="+password+"&jobName="+jobName+"&phone="+phone;
		console.log(url);
		$.ajax({
			async:false,
			"url":url,
			"type":"post",
			"dataType":"json",
			"success":function(){
				
			}
		});
		$("#appenduser").attr("onclick","append_newTr();");
		//刷新第一页
		var num=0;
		userlist(num);
	}
	
}
//切换编辑保存超链接
function edit_save(rearonly_bool, fun, mess, index) {
	var tr_checked = $('#admin_table tr')[index + 1];
	var input_list = $(tr_checked).find('input');
	for(var i = 0; i < input_list.length; i++) {
		input_list[i].readOnly = rearonly_bool;
		if(rearonly_bool === false) {
			$(input_list[i]).css('border', '2px solid #1197db');
		} else {
			$(input_list[i]).css('border', 'none');
		}
	}
	$(tr_checked).find('a')[0].href = 'javascript:' + fun;
	$(tr_checked).find('a')[0].innerText = mess;
}
//删除
function delete_admin(num,flag) {
	if(flag==0){
		console.log(1);
		$("#appenduser").attr("onclick","append_newTr();");
		var num=0;
		userlist(num);
	}
	if(flag==1){
		if(confirm("确定删除？")) {
			//ajax请求删除，remove该行元素
			var userId=$("#a"+num).attr("user-id");
			$.ajax({
				"url":"userdelete.do?userId="+userId,
				"type":"post",
				"dataType":"json",
				"success":function(){
					
				}
			});
	}
	
		//刷新第一页
		var num=0;
		userlist(num);
	}
	
}

////点击增加用户显示的
//$("#adduser").click(function(){
//	$("#add_user").css("display","block");
//	list_job();
//})
//function list_job(){
//$.getJSON("list_job.do",function(data){
//		$("#job_sel").empty();
//		$.each(data,function(i,job){
//			
//			$("#job_sel").append(
//				'<option value='+job.jobId+'>'+job.jobName+'</option>'
//			);
//		});
//	});
//}

////点击保存用户
//$("#addsave").click(function() {
//	var username=$("#add_new").val();
//	var password=$("#pass_word").val();
//	var jobId=$("#job_sel").val();
//	var phone=$("#user_phone").val();
//	$.ajax({
//		async:false,
//		"url":"usersave.do?username="+username+"&password="+password+"&jobId="+jobId+"&phone="+phone,
//		"type":"post",
//		"dataType":"json",
//		"success":function(){
//			
//		}
//	});
//	//刷新第一页
//	var num=0;
//	userlist(num);
//	//隐藏并清除添加用户列表的数据
//	$("#add_user").css("display","none");
//	$("#user_name").val("");
//	$("#pass_word").val("");
//	$("#job_sel").val("");
//	$("#user_phone").val("");
//})

//列出用户表的方法
		function userlist(num){
			$("#admin_table").empty();
			//列出用户列表
			$.ajax({
				
				"url":"userlist.do?num="+num,
				"type":"post",
				"dataType":"json",
				"success":function(msg){
					var list=msg.data;
					var head = ['账户名', '密码', '职位', '电话'];
					var data=new Array();
					for(var i=0;i<list.length;i++){
						data[i]=new Array();
						data[i][0]=list[i].username;
						data[i][1]=list[i].password;
						data[i][2]=list[i].jobName;
						data[i][3]=list[i].phone;
						data[i][4]=list[i].userId;
					}
					console.log(data);
					var selector = "admin_table";
					createTable(head, data,selector);
				}
			});
		}

//点击上一页
function up() {
	var count=$("#userpage").html();
	if(count>1){
		count=count-1;
	}
	var num=(count-1)*10;
	//调用列出用户列表的函数
	userlist(num);
	$("#userpage").html(count);
	
}
//点击下一页
function down() {
	var url="usercount.do";
	$.getJSON(url,function(result){
		
		var count=$("#userpage").html();
		var page=parseInt(result/10)+1;
		if(count<page){
			count=parseInt(count)+1;
		}
		var num=(count-1)*10;
		//调用列出用户列表的函数
		userlist(num);
		$("#userpage").html(count);
	});
	
}


//测试用的
//a.name,

//(function testTable() {
//	var head = ['账户名', '密码', '职位', '电话'];
//	var data = [
//		['张三', '123456', '讲师', '13111118888'],
//		['李四', '123456', '课程顾问', '13122228888'],
//		['赵武', '123456', '讲师', '13133338888']
//	];
//	console.log(data);
//	createTable(head, data);
//})();
//(function(){
//	$('#table_css table tr:even').css('background-color','#f5f5f5');
//})();
//function append_newTr(){
//	//
//}

//增加方法
function append_newTr(){
	//
	var tdNum = $('#admin_table th').length;
	var trNum = $('#admin_table tr').length - 1;
	console.log(trNum);
	if (trNum >= 9) {
		var head = $('#admin_table tr')[0];
		$('#admin_table').empty();
		$('#admin_table').append(head);
		trNum = 0;
	}
	//给他添加一个id以便获取参数
	var tr_html = "<tr id='add_new'>";
	for (var i = 0; i < tdNum-2; i++) {
		tr_html += "<td><input type='text'/></td>";
	}
	tr_html += "<td class = 'edit_delete' ><a href='javascript:edit_admin(" + trNum + ")'></a></td>";
	tr_html += "<td class = 'edit_delete'><a href='javascript:delete_admin(" + trNum + ",0)'>取消</a></td></tr>";
	$('#admin_table').append(tr_html);
	edit_admin(trNum,0);
	$('#table_css table tr:even').css('background-color','#f5f5f5');
	
	$("#appenduser").removeAttr("onclick");
}



$("#addstu").click(function(){
	$("#add_stu").css("display","block");
	console.log($("#user_stu").val());
})
$("#stusave").click(function() {
	var params = {
			name:$("#name").val(),
			major:$("#major").val(),
			learnPro:$("#learnPro").val(),
			degree:$("#degree").val(),
			type:$("#type").val(),
			source:$("#source").val(),
			basic:$("#basic").val(),
			studyTime:$("#studyTime").val(),
			preSituation:$("#preSituation").val(),
			userId:$("#user_stu").val()
	}
	console.log(params);
	var url = "insertStu.do";
	$.post(url,params,function(result){
		if(result.state==1){
			alert("保存成功");
			setStuList();
		}else{
			alert("保存失败")
		}
		
	})
	//刷新第一页
	
	
	//隐藏并清除添加用户列表的数据
	$("#add_stu").css("display","none");
	/*$("#user_name").val("");
	$("#pass_word").val("");
	$("#job_sel").val("");
	$("#user_phone").val("");*/
})
