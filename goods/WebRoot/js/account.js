$(function() {
	/*
	 * 1. 得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息！
	 */
	
	$(".errorClass").each(function() {
		showError($(this));//遍历每个元素，使用每个元素来调用showError方法
	});
	
	/*
	 * 2. 切换注册按钮的图片
	 */
	$("#submitBtn").hover(
		function() {
			$("#submitBtn").attr("src", "/goods/images/regist2.jpg");
		},
		function() {
			$("#submitBtn").attr("src", "/goods/images/regist1.jpg");
		}
	);
	
	/*
	 * 
	 * 输入框得到焦点 隐藏错误信息
	 */
	$(".inputClass").focus(function(){
		var  labelId = $(this).attr("id") + "Error";//输入框找到相应的label的id
		$("#" + labelId).text("");//把label的内容清空！！
		showError($("#" + labelId));//隐藏没有信息的label 
	});
	/*
	 * 输入框失去焦点 进行校验
	 * 
	 * 
	 */
	$(".inputClass").blur(function(){
		/*
		 * 谁失去焦点就校验谁
		 * 
		 */
		var id = $(this). attr("id");//获取输入框的ID
		var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";//得到对应的函数名
		eval(funName);//执行函数调用
		
		
	});
	
	
	
});

/*
 * 
 * 登录名校验方法
 */

function validateLoginname(){
	var id="loginname";
	var value = $("#" + id).val();//获取输入框 内容
	/*非空校验
	 */
	if(!value){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id + "Error").text("用户名不能为空!");
		showError($("#" +id +"Error"));
		return false;
	}
	 /* 
	 * 长度校验
	 */
	if(value.length<3||value.length>20){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id +"Error").text("用户名长度在3 ~ 20 之间!");
		showError($("#" + id +"Error"));
		return false;
	}
	
	 /*3 是否注册
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的servlet
		data:{method:"ajaxValidateLoginname", loginname:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
		cache:false,
		success:function(result) {
			
			if(!result) {//如果校验失败
				$("#" + id + "Error").text("用户名已被注册！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	
	return true;
}
/*
 * 
 * 邮箱地址校验方法
 */

function validateEmail(){
	var id="email";
	var value = $("#" + id).val();//获取输入框 内容
	/*非空校验
	 */
	if(!value){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id + "Error").text("邮箱不能为空!");
		showError($("#" +id +"Error"));
		return false;
	}
	 /* 
	 * 长度校验
	 */
	if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id +"Error").text("错误的邮箱格式!");
		showError($("#" + id +"Error"));
		return false;
	}
	
	 /*3 是否注册
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的servlet
		data:{method:"ajaxValidateEmail", email:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
		cache:false,
		success:function(result) {
			
			if(!result) {//如果校验失败
				$("#" + id + "Error").text("eamil已被注册！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	
	
	return true;
}
/*
 * 
 * 手机名校验方法
 */

function validatePhone(){
	var id="phone";
	var value = $("#" + id).val();//获取输入框 内容
	/*非空校验
	 */
	if(!value){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id + "Error").text("号码不能为空!");
		showError($("#" +id +"Error"));
		return false;
	}
	 /* 
	 * 长度校验
	 */
	if(value.length !=11){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id +"Error").text("手机号码格式错误!");
		showError($("#" + id +"Error"));
		return false;
	}
	
	 /*3 是否注册
	 */
	
	return true;
}
/*
 * 
 * 登录密码校验方法
 */

function validateLoginpass(){
	var id="loginpass";
	var value = $("#" + id).val();//获取输入框 内容
	/*非空校验
	 */
	if(!value){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id + "Error").text("密码不能为空!");
		showError($("#" +id +"Error"));
		return false;
	}
	 /* 
	 * 长度校验
	 */
	if(value.length<3||value.length>20){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id +"Error").text("用户名长度在3 ~ 20 之间!");
		showError($("#" + id +"Error"));
		return false;
	}
	
	 /*3 是否注册
	 */
	
	return true;
}
/*
 * 
 * 确认密码名校验方法
 */

function validateReloginpass(){
	var id="reloginpass";
	var value = $("#" + id).val();//获取输入框 内容
	/*非空校验
	 */
	if(!value){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id + "Error").text("确认密码不能为空!");
		showError($("#" +id +"Error"));
		return false;
	}
	 /* 
	 * 两次输入是否一样
	 */
	if(value != $("#loginpass").val()){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id +"Error").text("两次输入不一致");
		showError($("#" + id +"Error"));
		return false;
	}
	
	 /*3 是否注册
	 */
	
	return true;
}
/*
 * 
 * 验证码校验方法
 */

function validateVerifycode(){
	var id="verifycode";
	var value = $("#" + id).val();//获取输入框 内容
	/*非空校验
	 */
	if(!value){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id + "Error").text("验证码不能为空!");
		showError($("#" +id +"Error"));
		return false;
	}
	 /* 
	 * 长度校验
	 */
	if(value.length!=4){
		/*获取对应的label
		 * 添加错误信息
		 * 显示label
		 */
		$("#" + id +"Error").text("错误验证码!");
		showError($("#" + id +"Error"));
		return false;
	}
	
	 /*3 是否正确
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的servlet
		data:{method:"ajaxValidateVerifyCode", verifycode:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
		cache:false,
		success:function(result) {
			
			if(!result) {//如果校验失败
				$("#" + id + "Error").text("验证码错误！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;
}






/*
 * 判断当前元素是否存在内容，如果存在显示，不页面不显示！
 */
function showError(ele) {
	var text = ele.text();//获取元素的内容
	if(!text) {//如果没有内容
		ele.css("display", "none");//隐藏元素
	} else {//如果有内容
		ele.css("display", "");//显示元素
	}
}
function change(){
	/*
	 * 1. 鑾峰彇<img>鍏冪礌
	 * 2. 閲嶆柊璁剧疆瀹冪殑src
	 * 3. 浣跨敤姣鏉ユ坊鍔犲弬鏁�
	 */
	$("#imgVerifyCode").attr("src", "/goods/VerifyCodeServlet?a=" + new Date().getTime());
}