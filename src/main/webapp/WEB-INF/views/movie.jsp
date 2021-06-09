<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CGV영화차트</title>
<style>
table {width: 100%; border-top: 1px solid #444444; border-collapse: collapse;}
tr, td {border-bottom: 1px solid #444444;padding: 10px;}
</style>
</head>
<body>
	<button id="btnInsert">선택저장</button>
	<table id="tbl" border=1></table>		
	<script id="temp" type="text/x-handlebars-template">	
		<tr class="title">
			<td>선택</td>
			<td>랭킹</td>
			<td>포스터</td>
			<td>제목</td>
			<td>개봉일</td>
		</tr>	
		{{#each .}} //arrayList 자체니까 . 
		<tr class="row">
			<td><input type="checkbox" class="chk"/></td>
			<td class="rank">{{rank}}</td>
			<td><img src="{{image}}" width=50/></td>
			<td class="title">{{{title}}}</td>
			<td class="openDate">{{openDate}}</td>
		</tr>	
		{{/each}}
	</script>
</body>
<script>
	//선택저장버튼을 클릭한 경우 (상품정보 테이블에 vo뽑아오기)
	$("#btnInsert").on("click",function(){
		if(!confirm("선택한 항목을 저장하실래요?")) return;
		$("#tbl .row .chk:checked").each(function(){
			var row = $(this).parent().parent();
			var image = row.find("img").attr("src");
			var title = row.find(".title").html();
			var odate = row.find(".openDate").html();
			var rank = row.find(".rank").html();			
			//alert(pid + "\n" + image + "\n" + pname + "\n" + pmall + "\n" + price + "\n" + query);	
			$.ajax({
				type:"get",
				url:"/movie/insert",
				data:{"title":title,"image":image,"odate":odate,"rank":rank},
				success:function(){
					}				
				});
			});
			alert("저장완료")
		});	
	
	//상품정보 출력
	getList();
	function getList(){
		$.ajax({
			type:"get",
			url:"movie.json",
			dataType:"json",
			success:function(data){
				  var temp=Handlebars.compile($("#temp").html());
			      $("#tbl").html(temp(data));
			}
		});
	}
</script>
</html>