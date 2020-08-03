$(function(){	
	$("#RegForm").Validform({
		tiptype:3,
		showAllError:true,
		postonce:true,
		ajaxPost:true,
		datatype:{
			"zh2-5": /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,5}$/,
			"zh2-2": /^[\u4E00-\u9FA5\uf900-\ufa2d]{2}$/,
			"idcard":function(gets,obj,curform,datatype){
				var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
				var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
			
				if (gets.length == 18){   
					var a_idCard = gets.split("");// 得到身份证数组   
					if (isValidityBrithBy18IdCard(gets)&&isTrueValidateCodeBy18IdCard(a_idCard)) {   
						return true;   
					}   
					return false;
				}
				return false;
				function isTrueValidateCodeBy18IdCard(a_idCard) {   
					var sum = 0; // 声明加权求和变量   
					if (a_idCard[17].toLowerCase() == 'x') {   
						a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
					}   
					for ( var i = 0; i < 17; i++) {   
						sum += Wi[i] * a_idCard[i];// 加权求和   
					}   
					valCodePosition = sum % 11;// 得到验证码所位置   
					if (a_idCard[17] == ValideCode[valCodePosition]) {   
						return true;   
					}
					return false;   
				}
				function isValidityBrithBy18IdCard(idCard18){   
					var year = idCard18.substring(6,10);   
					var month = idCard18.substring(10,12);   
					var day = idCard18.substring(12,14);   
					var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
					// 这里用getFullYear()获取年份，避免千年虫问题   
					if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){   
						return false;   
					}
					return true;   
				}
			}
		},
		beforeSubmit:function(curform){
			$("#btnRegister").val("注册中...");
			$("#btnRegister").attr("disabled","disabled");
		},
		callback:function(data){
			$("#btnRegister").val("注册");
			$("#Validform_msg").hide();
			if(data.status=="y"){
				Kunyi.Msg.ShowTipsMsg("注册成功！","1888","1");
				window.setTimeout(function(){
					window.top.document.location.href ="login.html";
				},2188);
			}else{
				$("#tbHashReg").val(data.hash);
				Kunyi.Msg.ShowTipsMsg(data.info,"1888","2");
				$("#btnRegister").removeAttr("disabled");
			}
			$("#IMGCheckcode").click();
		}
	});
	
})