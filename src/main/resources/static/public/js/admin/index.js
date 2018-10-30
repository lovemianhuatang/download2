//JavaScript代码区域
 var element;
layui.use('element', function(){
  element = layui.element;
  
});
$(function(){
	$(".menus-tree a").click(function(e){
		e.preventDefault();
		var id;
		if($("li[data-text='"+$.trim($(this).text())+"']").length==0&&$(this).attr('href')!=undefined){
			var centent='<iframe class="main-iframe" name="mainframe" src="'+$(this).attr('href')+'"></iframe>';
			var text=$(this).html();
			id=new Date().getTime();
		    element.tabAdd('tab-menus', {
		        title: text //用于演示
		        ,content: centent
		        ,id: id //实际使用一般是规定好的id，这里以时间戳模拟下
		    });
		    $("li[lay-id='"+id+"']").attr("data-text",$.trim($(this).text()));
		     $(".clildFrame .layui-show>iframe").attr("src",$(".clildFrame .layui-show>iframe").attr("src"));
		}else{
			id=$("li[data-text='"+$.trim($(this).text())+"']").attr("lay-id");
			 $(".clildFrame .layui-show>iframe").attr("src",$(".clildFrame .layui-show>iframe").attr("src"));
		}
		
	    element.tabChange('tab-menus', id);
	   
	})
});
