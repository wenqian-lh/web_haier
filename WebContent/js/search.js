var page = 1;
var rows = 20;
var search_goods = new Vue({
	el: "#search",
	data: {
		name: "",
		goods: [],
		pics: [],
		total: 0		
	},
	mounted: function() {
		var index_name = location.search;
		axios.post("goods", qs.stringify({op: "findByName", name: index_name.replace("?",""), page: 1, rows: rows})).then(rt => {
			if (rt.status == 200) {					
				this.total = rt.data.total;
				this.goods = rt.data.rows;
				$("#pagination").showPage(Math.ceil(this.total / rows));
			}
		})
	},
	methods: {
		search: function() {
			axios.post("goods", qs.stringify({op: "findByName", name: this.name, page: 1, rows: rows})).then(rt => {
				if (rt.status == 200) {					
					this.total = rt.data.total;
					this.goods = rt.data.rows;
					$("#pagination").showPage(Math.ceil(this.total / rows));
				}
			})
		}
	}
})

function findByPage(index) {
	page = index;
	axios.post("goods", qs.stringify({op:"findByName", name: search_goods.$data.name, page:page, rows:rows})).then(rt => {
		if (rt.status == 200) {
			search_goods.$data.goods = rt.data.rows;
		}
	}, "json");
}