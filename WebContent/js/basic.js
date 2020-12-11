// 管理员权限验证
var admin_checkSuper = new Vue({
	el: ".wu-sidebar",
	data: {
		isSuperAdmin: "", 
		admin: ""
	},
	mounted: function() {
		axios.post("../../admin", qs.stringify({op: "checkAdmin"})).then(rt => {
			if (rt.data.code == 201) {
				this.isSuperAdmin = true;
				this.admin = "admin.html";
				return;
			}
			if (rt.data.code == 200) {
				this.isSuperAdmin = false;
				return;
			}
			if (rt.data.code == 500 || rt.data.mas == "失败") {
				location.href = "login.html";
				return;
			}
		})
	}
})

//安全退出
var admin_out_vue = new Vue({
	el: ".wu-header",
	data: {
		
	},
	methods: {
		out: function() {
			axios.post("../../admin", qs.stringify({op: "adminOut"})).then(rt => {
					location.href = "index.html";
			})
		}
	}
})
