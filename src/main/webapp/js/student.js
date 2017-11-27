/*//加载用户到添加学员表单
$(function(){
	$.getJSON("listUserByJobId.do",function(result){
		$("#user_stu").empty();
		$.each(result.data,function(i,user){
			
			$("#user_stu").append(
				'<option value='+user.userId+'>'+user.username+'</option>'
			);
		});
	});
})*/
function createTable_stu(head, data) {
	if(head & data) {
		return "";
	}
	var html = "<tbody><tr>";
	for(var i = 0; i < head.length; i++) {
		html += "<th>" + head[i] + '</th>'
	}
	html += "<th class = 'edit_delete'>编辑</th><th class = 'edit_delete'>删除</th></tr>";
	for(var j = 0; j < data.length && j<10; j++) {
		html += "<tr>"
		for(var k = 0; k < data[j].length-1; k++) {
			html += "<td><input type='text' readonly='readonly' value='" +
				data[j][k] + "' /></td>"
		}
		html += "<td class = 'edit_delete'><a href='javascript:edit_stu(" + j + ",1)' id='a"+j+"' stu-id="+data[j][data[j].length-1]+">编辑</a></td>";
		html += "<td class = 'edit_delete'><a href='javascript:delete_stu(" + j + ",1)' stu-id='"+data[j][data[j].length-1]+"'>删除</a></td></tr>";
	}
	document.getElementById("stu_table").innerHTML = html + '</tbody>';
	$('.table_css table tr:even').css('background-color','#f5f5f5');
}
//编辑变成保存，并把输入框变成可输入
function edit_stu(index,flag) {
	//记住当前点击行的坐标
	edit_save_stu(false, 'save_stu(' + index + ','+flag+')', '保存', index);
	
	
}
//保存变为只读
function save_stu(index,flag) {
	//ajax保存数据
	console.log(flag);
	if(flag==1){
		edit_save_stu('readonly', 'edit_stu(' + index + ','+flag+')', '编辑', index);
		var params = {
				studentId:$("#a"+index).attr("stu-id"),
				name:$("#a"+index).parent().siblings().eq(0).find("input").val(),
				major:$("#a"+index).parent().siblings().eq(1).find("input").val(),
				learnPro:$("#a"+index).parent().siblings().eq(2).find("input").val(),
				degree:$("#a"+index).parent().siblings().eq(3).find("input").val(),
				type:$("#a"+index).parent().siblings().eq(4).find("input").val(),
				source:$("#a"+index).parent().siblings().eq(5).find("input").val(),
				basic:$("#a"+index).parent().siblings().eq(6).find("input").val(),
				studyTime:$("#a"+index).parent().siblings().eq(7).find("input").val(),
				username:$("#a"+index).parent().siblings().eq(8).find("input").val()
				
		}
		console.log(params);
		var url = "studentupdate.do";
		$.post(url,params,function(result){
			if(result.state==1){
				getStuList();
				alert('保存成功');
			}else{
				alert('修改失败');
			}
			
		})
	}else if(flag==0){
		edit_save_stu('readonly', 'edit_stu(' + index + ','+flag+')', '编辑', index);
		var params = {
				//studentId:$("#a"+index).attr("stu-id"),
				name:$("#add_new_stu").children().siblings().eq(0).find("input").val(),
				major:$("#add_new_stu").children().siblings().eq(1).find("input").val(),
				learnPro:$("#add_new_stu").children().siblings().eq(2).find("input").val(),
				degree:$("#add_new_stu").children().siblings().eq(3).find("input").val(),
				type:$("#add_new_stu").children().siblings().eq(4).find("input").val(),
				source:$("#add_new_stu").children().siblings().eq(5).find("input").val(),
				basic:$("#add_new_stu").children().siblings().eq(6).find("input").val(),
				studyTime:$("#add_new_stu").children().siblings().eq(7).find("input").val(),
				username:$("#add_new_stu").children().siblings().eq(8).find("input").val()
				
		}
		console.log(params);
		var url = "insertStu.do";
		$.post(url,params,function(result){
			if(result.state==1){
				getStuList();
				alert('保存成功');
			}else{
				alert('修改失败');
			}
			
		})
	}
	//修改新建行readonly属性
	
	
}
//切换编辑保存超链接
function edit_save_stu(rearonly_bool, fun, mess, index) {
	var tr_checked = $('#stu_table tr')[index + 1];
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
function delete_stu(num) {
	if(confirm("确定删除？")) {
		//ajax请求删除，remove该行元素
		var studentId=$("#a"+num).attr("stu-id");
		console.log(studentId);
		var params = {"studentId":studentId}
		var url= "studelete.do";
		$.post(url,params,function(result){
			if(result.state==1){
				getStuList();
				alert("删除成功");
			}else{
				alert("删除失败")
			}
		})
		//刷新第一页
		
		
	}
	
}


function getStuList(){
	
	//定义访问项目信息的url
	var url = "findStuList.do";
	//获取表单数据(查询时用)
	
	//动态设置页码信息
	var pageCurrent = $('#pageId').data("pageCurrent");
	if(!pageCurrent){
		pageCurrent=1;
	}
	var params = {"pageCurrent":pageCurrent};
	//发起异步请求
	$.getJSON(url,params,function(result){
			//设置当前页数据
			setStuList(result.data.list);
			//设置分页信息
			
			setPagination(result.data.pageObject);
	})
}
function setStuList(list){
	$("#stu_table").empty();
	var head = ['姓名', '专业','学习进度', '学历', '类型','来源','基础','学习时长','课程顾问'];
	var data=new Array();
	for(var i=0;i<list.length;i++){
		data[i]=new Array();
		data[i][0]=list[i].name;
		data[i][1]=list[i].major;
		data[i][2]=list[i].learnPro;
		data[i][3]=list[i].degree;
		data[i][4]=list[i].type;
		data[i][5]=list[i].source;
		data[i][6]=(list[i].basic?"是":"否");
		data[i][7]=list[i].studyTime;
		data[i][8]=list[i].username;
		data[i][9]=list[i].studentId;
	}
	console.log(data);
	var selector="stu_table";
	createTable_stu(head, data,selector);
}
//增加方法
function append_newTr_stu(){
	//
	var tdNum = $('#stu_table th').length;
	var trNum = $('#stu_table tr').length - 1;
	console.log(trNum);
	if (trNum >= 9) {
		var head = $('#stu_table tr')[0];
		$('#stu_table').empty();
		$('#stu_table').append(head);
		trNum = 0;
	}
	//给他添加一个id以便获取参数
	var tr_html = "<tr id='add_new_stu'>";
	for (var i = 0; i < tdNum-2; i++) {
		tr_html += "<td><input type='text'/></td>";
	}
	tr_html += "<td class = 'edit_delete' ><a href='javascript:edit_stu(" + trNum + ")'></a></td>";
	tr_html += "<td class = 'edit_delete'><a href='javascript:delete_stu(" + trNum + ")'>删除</a></td></tr>";
	$('#stu_table').append(tr_html);
	edit_stu(trNum,0);
	$('#table_css table tr:even').css('background-color','#f5f5f5');
}
