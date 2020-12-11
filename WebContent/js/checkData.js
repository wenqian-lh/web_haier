//验证移动手机号码
function isPhoneNo(phone) {
var reg = /^1[34578]\d{9}$/; //验证手机号码
return reg.test(phone);
}
//邮箱验证：
function isEmail(email) {
var reg = /([\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)/;
return reg.test(email);
}

