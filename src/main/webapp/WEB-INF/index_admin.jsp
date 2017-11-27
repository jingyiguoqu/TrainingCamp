<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" /> 
		<title>管理员主页</title>
		<link rel="stylesheet" type="text/css" href="css/index_admin.css" />
		<link rel="stylesheet" type="text/css" href="css/add_tc.css" />
		<link rel="stylesheet" type="text/css" href="css/seat_info.css" />
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type="text/css" href="css/bookingSeats.css" />
	</head>

	<body id="body" job-id='${user.jobId}'>
		<div class="main">
			<!--logo，菜单-->
			<header>
				<!--------------------------------------------------->
				<img src="img/index_admin/logo.png" id="logo" />
				<!--------------------------------------------------->
				<section>
					<img src="img/index_admin/admin.png" id="admin_img" />
					<label>${user.username}</label>
					<img src="img/index_admin/update.png" id="" />
					<label onclick="show_update_pwd()">修改密码</label>
					<img src="img/index_admin/exit.png" />
					<label onclick="signout()">退出登录</label>
				</section>
			</header>
			<!--main菜单-->
			<div id="menu">
				<ol id="menu_ul">
					<!--------------------------------------------------->
					<li onclick="menu_li_addCss(this)"><div></div><img src="img/index_admin/tc_manage_before.png"/><span class="tcmanager">训练营管理</span></li>
					<li onclick="menu_li_addCss(this)"><div></div><img src="img/index_admin/user_mana_before.png"/><span class="usermanage">用户信息管理</span></li>
					<li onclick="menu_li_addCss(this)"><div></div><img src="img/index_admin/user_mana_before.png"/><span class="stumanager">学员信息管理</span></li>
					<!--------------------------------------------------->
				</ol>
			</div>
			<!--main-->
			<div id="main_info">
				<c:forEach items="${Tclist }" var="e">
				<!--一个训练营-->
				<div class="tcInfo" id="">
					<!--装背景图-->
					<div class="tcInfo_img tcInfoClass" onclick="classseat(this);" data-id="${e.tcId}" style="background-image:url(img/index_admin/${e.type}.png);">
						<!--主要信息-->
						<div class="main_mess">
							<span id="course">${e.type}</span><br />
							<label>讲师:</label>
							<span id="teacher">${e.username }</span><br />
							<label>教室:</label>
							<span id="classroom">${e.className }</span><br />
							<label>已座/总数:</label>
							<span id="num_mess">${e.numPeople}/${e.seatNum }</span><br />
						</div>
						<!--创建时间-->
						<!--
						<div class="create_time">
							<label>创建时间:</label>
							<span id="created_time">
								
								<fmt:formatDate value="${e.createdTime }" type="date" dateStyle="default"/>
							</span><br />
						</div>  -->
					</div>
					<!--操作图标-->
					<div class="option_img">
						<img src="img/index_admin/edit.png" /><label data-id="${e.tcId}" class="check-click" onmouseover="style.cursor='pointer';">修改</label>
						<img src="img/index_admin/delete.png" /><label onclick="if(confirm('是否删除?')){location='delete.do?tcId=${e.tcId}';}" onmouseover="style.cursor='pointer';">删除</label>
					</div>
				</div>
				</c:forEach>
				<!--增加训练营图标-->
				<div class="tcInfo">
					<!--装背景图-->
					<div class="tcInfo_img" id="addtcicon" onclick="show_add_tc();" onmouseover="style.cursor='pointer';" 
						style="background-image:url(img/index_admin/add.png);">
					</div>
				</div>
			</div>
			<!--增加训练营-->
			<!-- -------------------------------------------------------->
			<div id="add_tc">
				<p>训练营信息管理</p>
				<form action="addTc.do" method="post">
					教室:
					<input type="text" id="" class="in_class" name="className"/><br /> 课程:
					<select id="course_select" name="type">
						<option value="java">Java</option>
						<option value="web">Web</option>
						<option value="UID">UID</option>
					</select>
					<br /> 讲师:
					<input type="text" id="" class="in_class"  name="username"/><br /> 布局:
					<img src="img/index_admin/seat_A.png" />
					<input type="radio" value="A" name="seatType" class="seatType" />
					<img src="img/index_admin/seat_B.png" />
					<input type="radio" value="B" name="seatType" class="seatType" /><br />
					<input type="submit" value="保存" class="btn" />
					<input type="button" value="取消" class="btn" onclick="hidden_add_tc()" />
				</form>
			</div>
			<!--修改训练营-->
			<!-- -------------------------------------------------------->
			<div id="update_tc">
				<p>训练营信息管理</p>
				<form action="update.do" method="post">
					教室:
					<input type="text" id="className1" class="in_class" name="className" value=""/><br /> 课程:
					<select id="course_select" name="type">
						<option value="java">Java</option>
						<option value="web">Web</option>
						<option value="UID">UID</option>
					</select>
					<br /> 讲师:
					<input type="text" id="username1" class="in_class"  name="username"  value=""/><br /> 布局:
					<img src="img/index_admin/seat_A.png" />
					<input type="radio" value="A" name="seatType" class="seatType" />
					<img src="img/index_admin/seat_B.png" />
					<input type="radio" value="B" name="seatType" class="seatType" /><br />
					<input type="hidden" id="tcIdupdate" name="tcId" value="" />
					<input type="submit" value="保存" class="btn" />
					<input type="button" value="取消" class="btn" onclick="hidden_update_tc()" />
				</form>
			</div>
			<!-- -------------------------------------------------------->
			<!--11/9号-->
			
			<!--座位详细信息-->
			<div class="seatInfo" id="seatInfo" tc-id="">
				<!--教室详细信息编号-->
				<div class="seatInfo_header">
					<span>教室：<span id="SeatClassName"></span> 讲师：
					<span id="SeatUserName"></span> 已定/剩余：
					<span id="SeatNum"></span> 创建时间：
					<span id="SeatDate"></span>
					</span>
				</div>
				<!--图例-->
				<!-------------------------------------------------------->
				<div class="legend">
					<span class="legend_1"><img src="img/seat/legend_available.png" />&nbsp;可选座位</span>
					<span class="legend_2"><img src="img/seat/legend_unAvailable.png" />&nbsp;已选座位</span>
					<span class="legend_2"><img src="img/seat/legend_D.png" />&nbsp;D阶段</span>
				</div>
				<!-------------------------------------------------------->
				<!--座位信息-->
				<div class="seat_states">
					<!--投影仪标记-->
					<div class="seat_states_hr">
						<hr /><span>投影仪</span>
						<hr />
					</div>
					<!--座位-->
					<div class="seat_details">
						<!--第一行-->
						<div class="left" id="seat1" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="left" id="seat2" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						
						<div class="right" id="seat5" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="right" id="seat4" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<!--中间-->
						<div class="left middle" id="seat3" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div><br />
						<!--第二行-->
						<div class="left" id="seat6" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="left" id="seat7" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						
						<div class="right" id="seat10" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="right" id="seat9" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<!--中间-->
						<div class="left middle" id="seat8" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div><br />
						<!--第三行-->
						<div class="left" id="seat11" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="left" id="seat12" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						
						<div class="right" id="seat15" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="right" id="seat14" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<!--中间-->
						<div class="left middle" id="seat13" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div><br />
						<!--第四行-->
						<div class="left" id="seat16" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="left" id="seat17" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						
						<div class="right" id="seat20" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="right" id="seat19" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<!--中间-->
						<div class="left middle" id="seat18" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div><br />
						<!--第五行-->
						<div class="left" id="seat21" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="left" id="seat22" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						
						<div class="right" id="seat25" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<div class="right" id="seat24" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div>
						<!--中间-->
						<div class="left middle" id="seat23" style="background-image: url(img/seat/available.png);">
							<span class="stu_name"></span><br /><span class="stu_cou"></span>
						</div><br />
					</div>
				</div>
				<!-------------------------------------------------------->
				<!--学员信息-->
				<div class="stu_details">
					<div>
						<img src="img/seat/people_ico.png" class="people_ico"/>
					</div>
					<div class="stu_details_ture">
						座位编号：<span></span><br /> 姓名：
						<span></span><br /> 课程顾问：
						<span></span><br /> 类型：
						<span></span><br /> 来源：
						<span></span><br /> 学历：
						<span></span><br /> 专业：
						<span></span><br /> 基础：
						<span></span><br /> 学习时长：
						<span></span><br /> 学习进度：
						<span></span><br />
						<div id="removestu" class="changeMouse"  stu-id="" onmouseover="style.cursor='pointer';" onclick="removestu()"><img src="img/seat/delete.png" class="stu_delete_ico"/></div>
					</div>
				</div>
				<!-------------------------------------------------------->
			</div>
			<!--11月10号-->
			<!------------------------------------------------------------->
			<!--账号管理-->
			<div class="table_container"  id="table_container">
				<div class="search">
					<img src="img/seat/search.png"/>
					<input type="search" value="请输入用户名" onfocus="javascript:this.value = '';"
					onblur="javascript:if(this.value == null || this.value == ''){this.value = '请输入用户名'}"/>
				</div>
				<div id = "table_css" class="table_css">
					<table  id="admin_table" class="admin_table" id="admin_table" cellspacing="" cellpadding="">
				</table>
				</div>
				<div class="tableFooter">
					<input type="button" value="增加" onclick="append_newTr();" id="appenduser"/>
					<div class="up_down">
						<a href="javascript:up()">上一页</a>
						<label id="userpage">1</label>
						<a href="javascript:down()">下一页</a>
					</div>
				</div>
			</div>
			
			<!------------------------------------------------------------->
			<!--学员管理-->
			<div class="table_container"  id="table_container_stu">
				<div class="search">
					<img src="img/seat/search.png"/>
					<input type="search" value="请输入用户名" onfocus="javascript:this.value = '';"
					onblur="javascript:if(this.value == null || this.value == ''){this.value = '请输入用户名'}"/>
				</div>
				<div id = "table_css" class="table_css">
					<table   class="admin_table" id="stu_table" cellspacing="" cellpadding="">
				</table>
				</div>
				<div class="tableFooter" id="pageId">
					<input type="button" value="增加" onclick="append_newTr_stu();"/>
					<div class="up_down">
						<a href="javascript:up()" class="pre">上一页</a>
						<label id="userpage" class="pageCurrent">1</label>
						<a href="javascript:down()" class="next">下一页</a>
					</div>
				</div>
			</div>
			<!-- ------------------------------------------------------------------ -->
			<!--修改密码-->
			<div class="update_pwd"  id="updatepwd">
				<h4>修改密码</h4> 
				
				<div class="in_info">
				<form action="update_pwd.do" method="post">
					<img src="img/login/password_ico.png" />
					<input type="text" id="update_pwd_info" value="请输入新密码" 
					onfocus="javascript:this.style.display = 'none';
					document.getElementById('password').style.display = 'inline';
					document.getElementById('password').focus();
					"/>
				<input type="password" id="password" name="new_pwd"
					onblur="javascript:if(this.value == null || this.value == ''){this.style.display = 'none';
						document.getElementById('update_pwd_info').style.display = 'inline';
					}"/>
				</div>
				<div class="submit_newpwd">
					<input type="submit" value="保存" class="btn_update" onclick="password1()"/>
					<input type="button" value="取消" class="btn_update" onclick="hidden_update_pwd();"/>
				</div>
				</form>
			</div>
			<!------------------------------------------------------------->
			<!--学员订座-->
			<!------------------------------------------------------------->
			<div class="bookingSeats" id="bookingstu">
				<div class="bookingSeats_con">
					<div class="search_stu">
						<img src="img/seat/search.png" />
						<input type="text" value="请输入姓名" onfocus="javascript:this.value = '';"
							onblur="javascript:if(this.value == null || this.value == ''){this.value = '请输入姓名'}"/>
						<!------------------------------------------------------------->
					</div>
					<div class="stu_list">
						<ul class="stu_list_ul">
						
						</ul>
					</div>
					<div class="operation">
						<button type="button" onclick="bookingstu()">订座</button>
						<button type="button" onclick="hid_bookingseat()">取消</button>
					</div>
				</div class="bookingSeats_con">
			</div>
		</div>
	</body>
	<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="js/index_admin.js"></script>
	<script type="text/javascript" src="js/manage_admin.js"></script>
	<script type="text/javascript" src="js/class_stu.js"></script>
	<script type="text/javascript" src="js/student.js"></script>
	<script type="text/javascript" src="js/page.js"></script>
</html>