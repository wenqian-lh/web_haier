/*!
 * 左侧全部是商品分类效果
 * 自定义二级分类
 * 2016-03-08
 */
(function($){
	$.fn.tab=function(options){
		$.fn.tab.defaults={
			titCell:'.tabH li',
			mainCell:'.tabBd',
			titCellH:52.5
		};

		return this.each(function(){
			var opts = $.extend({},$.fn.tab.defaults,options);
			var taber = $(this);
			var titCell = $(opts.titCell,taber);
			var mainCell = $(opts.mainCell,taber);
			var mChildBox = mainCell.children();
			var titCellH = opts.titCellH;
			// var titCellH = titCell.height();
			// var titCellPt = parseInt(titCell.css('padding-top'));
			// var titCellPb = parseInt(titCell.css('padding-bottom'));
			var changeClass = function(i){
				titCell.eq(i).addClass('on').siblings().removeClass('on');
				mChildBox.eq(i).show().siblings().hide();
			};
			var removeClass = function(i){
				// console.log(i)
				titCell.eq(i).removeClass('on');
				mChildBox.eq(i).hide();
			};
			var hov = function(obj){
				obj.hover(function(){
					var i = obj.index(this);
					changeClass(i);
				},function(){
					var i = obj.index(this);
					removeClass(i);
				})
			};

			var subChild,subChildSize,maxH,width,height;
			var a = new Array();
			// height = titCellH + titCellPt + titCellPb;
			height = titCellH;
			//先显示，获取高度
			mChildBox.css({ visibility: "hidden", display: "block" });

			// mChildBox单独计算
			for(var j=0;j<mChildBox.size();j++){

				a[0] = new Array();
				a[1] = new Array();
				a[2] = new Array();
				width = 0;
				subChildSize = 0;

				subChild = mChildBox.eq(j).children();
				subChildSize = subChild.size();

				// 将mChildBox子元素宽度存入二维数组
				for(var i=0;i<subChildSize;i++){
					a[0][i]=new Div(
						subChild.eq(i).height(),
						subChild.eq(i).width(),
						parseInt(subChild.eq(i).css('padding-left')),
						parseInt(subChild.eq(i).css('padding-right'))
					);
					a[1][i] = a[0][i].h;
					a[2][i] = a[0][i].w + a[0][i].pl + a[0][i].pr;
				}
				//设置高度
				maxH = Math.max.apply(null,a[1]);
				subChild.css('height',maxH);
				//设置宽度
				width = eval(a[2].join('+'));
				if(width>600) mChildBox.eq(j).css('width',width + subChildSize);
				//设置top，判断是否超出category高度
				if(maxH<(mChildBox.size()-j)*height){
					mChildBox.eq(j).css('top',j*height);
				}else{
					mChildBox.eq(j).css('top',mChildBox.size()*height-maxH-parseInt(mChildBox.css('padding-top'))-parseInt(mChildBox.css('padding-bottom'))-2);
				}
			}

			//获取高度后隐藏
			mChildBox.css({ visibility: "", display: "" });

			// tab效果
			hov(titCell);
			hov(mChildBox);

		})
	}
})(jQuery);

function Div(h,w,pl,pr){
	this.h = h;
	this.w = w;
	this.pl = pl;
	this.pr = pr;
}