
$(function(){

	var mySwiper = new Swiper ('.swiper-container', {
		    direction: 'horizontal', // 垂直切换选项
		   	slidesPerView: 5,
		  	freeMode: true,
		  });


    $("#tab>li").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
        var dataid=$(this).attr("data-id");
        $("#tab-"+dataid).siblings().hide();
        $("#tab-"+dataid).show();
    });



 
});


layui.use(['flow','laytpl'], function(){
    var flow = layui.flow;
    var laytpl = layui.laytpl;
    flow.load({
        elem: '#main-content-list' //指定列表容器
        ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get('/home/lable/lablelist?pageNum='+page+"&id="+$("#lableId").val(), function(res){
                //假设你的列表返回在data集合中
                var getTpl = $("#main-content-list-template").html();
                laytpl(getTpl).render(res, function(html){
                    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                    next(html, page < res.pages);
                });
            });
        }
    });
});