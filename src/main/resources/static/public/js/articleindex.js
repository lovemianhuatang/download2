$(function(){
    $(document).delegate("#catalog-01 .category-item-list a","click",function(){
        $(this).addClass("c-active");
        $(this).siblings().removeClass("c-active");
        $("#catalogPidOne").val($(this).attr("data-id"));
        var _this=this;
        $("#catalog-02").html("");
        $("#catalog-03").html("");

        if($(_this).attr("data-id")=="-1"){
            $("#catalogPidTwo").val(-1);
            $("#catalogPidTree").val(-1);
            ajaxLoad();
            return;
        }else{
            ajaxLoad();
        }

        var url="/home/article/getCatalog";
        var data=$("#catalog-form").serializeArray();


        $.post(url,data,function(resultObj){

            if(resultObj!=undefined&&resultObj.length>0){
                var list=' <a href="javascript:;" data-id="-1" class="c-active">全部</a>';
                $.each(resultObj,function(index,obj){
                    list+=' <a href="javascript:;" data-id="'+obj.id+'" >'+obj.name+'</a>';
                });
                var html=' <div class="category-item">'+
                    '<div class="category-item-title layui-hide-xs">二级:</div> '+
                    '  <div class="category-item-list"> '+
                    list+
                    ' </div> '+
                    ' </div>';
                $("#catalog-02").html(html);
            }
        });


    });


    $(document).delegate("#catalog-02 .category-item-list a","click",function(){
        $(this).addClass("c-active");
        $(this).siblings().removeClass("c-active");
        $("#catalogPidTwo").val($(this).attr("data-id"));
        var _this=this;
        $("#catalog-03").html("");
        if($(_this).attr("data-id")=="-1"){
            $("#catalogPidTree").val(-1);
            ajaxLoad();
            return;
        }else{
            ajaxLoad();
        }
        var url="/home/article/getCatalog";
        var data=$("#catalog-form").serializeArray();
        $.post(url,data,function(resultObj){

            if(resultObj!=undefined&&resultObj.length>0){
                var list=' <a href="javascript:;" data-id="-1" class="c-active">全部</a>';
                $.each(resultObj,function(index,obj){
                    list+=' <a href="javascript:;" data-id="'+obj.id+'" >'+obj.name+'</a>';
                });
                var html=' <div class="category-item">'+
                    '<div class="category-item-title layui-hide-xs">三级:</div> '+
                    '  <div class="category-item-list"> '+
                    list+
                    ' </div> '+
                    ' </div>';
                $("#catalog-03").html(html);
            }
        });
        ajaxLoad();
    });


    $(document).delegate("#catalog-03 .category-item-list a","click",function(){
        $(this).addClass("c-active");
        $(this).siblings().removeClass("c-active");
        $("#catalogPidTree").val($(this).attr("data-id"));
        var url="/home/article/getCatalog";
        var data=$("#catalog-form").serializeArray();
        ajaxLoad();
    });


    $("#orderSort-tab a").click(function(){
        $(this).addClass("order-active");
        $(this).siblings().removeClass("order-active");
        $("#orderSort").val($(this).attr("data-id"));
        ajaxLoad();
    });
});


layui.use(['laypage'], function() {
    laypage = layui.laypage;
});

//异步刷新列表方法
function ajaxLoad(){

    var url="/home/article/articlelist";
    var data=$("#catalog-form").serializeArray();
    $.post(url,data,function(resultHtml){
        $("#content-main").html(resultHtml);
        $(window).scrollTop(0);
        laypage.render({
            elem: 'pager' //注意，这里的 test1 是 ID，不用加 # 号
            ,curr:$("#pageNum").val() //当前页码数
            ,count:$("#count").val() //数据总数，从服务端得到
            ,limit:$("#pageSize").val()
            ,layout: ['count', 'prev', 'page', 'next'] //,  'skip'
            ,theme: '#FF5722',
            jump: function(obj, first){
                if(!first){
                    $("#pageNum").val(obj.curr);
                    ajaxLoad();
                }
            }
        });
    });
}

$(function(){
    ajaxLoad();
});

function openLocation(id){
    window.location.href="/home/article/details?id="+id;
}

function openDownload(id){
    window.location.href="/home/article/download?id="+id;
}