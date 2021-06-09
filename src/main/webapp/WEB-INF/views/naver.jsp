<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>네이버 쇼핑 검색</title>
<style>
table {width: 100%; border-top: 1px solid #444444; border-collapse: collapse;}
tr, td {border-bottom: 1px solid #444444;padding: 10px;}
</style>
</head>
<body>
	<input type="text" id="query" value="마우스" placeholder="검색어">
	<button id="btnInsert">선택저장</button>
	<table id="tbl" border=1></table>		
	<script id="temp" type="text/x-handlebars-template">
		<tr>
			<td>선택</td>
			<td>상품코드</td>
			<td>이미지</td>
			<td>상품명</td>
			<td>최저가</td>
			<td>판매처</td>
		</tr>	
		{{#each items}}
		<tr class="row">
			<td><input type="checkbox" class="chk"/></td>
			<td class="pid">{{productId}}</td>
			<td><img src="{{image}}" width=100/></td>
			<td class="pname">{{{title}}}</td>
			<td class="price">{{lprice}}</td>
			<td class="pmall">{{mallName}}</td>
		</tr>	
		{{/each}}
	</script>
</body>
<script>
var query=$("#query").val();
	//선택저장버튼을 클릭한 경우 (상품정보 테이블에 vo뽑아오기)
	$("#btnInsert").on("click",function(){
		if(!confirm("선택한 항목을 저장하실래요?")) return;
		$("#tbl .row .chk:checked").each(function(){
			var row = $(this).parent().parent();
			var pid = row.find(".pid").html();
			var image = row.find("img").attr("src");
			var pname = row.find(".pname").html();
			var price = row.find(".price").html();
			var pmall = row.find(".pmall").html();			
			//alert(pid + "\n" + image + "\n" + pname + "\n" + pmall + "\n" + price + "\n" + query);
			$.ajax({
				type:"get",
				url:"read",
				data:{"pid":pid},
				success:function(data){
					if(data==0){
						$.ajax({
							type:"get",
							url:"insert",
							data:{"pid":pid,"pname":pname,"image":image,"price":price,"pmall":pmall,"query":query},
							success:function(){
								}				
							});
						}
					}				
				});
			/*
			 $.ajax({
				type:"get",
				url:"insert",
				data:{"pid":pid,"pname":pname,"image":image,"price":price,"pmall":pmall,"query":query},
				success:function(){
					alert(pid + "\n" + image + "\n" + pname + "\n" + pmall + "\n" + price + "\n" + query);
					}				
				});
			*/
		});
		alert("저장완료")
	});
	$("#query").on("keydown",function(e){
		if(e.keyCode==13) {getList();}
	});
	//상품정보 출력
	getList();
	function getList(){
		$.ajax({
			type:"get",
			url:"shop.json",
			dataType:"json",
			data:{"query":query},
			success:function(data){
				  var temp=Handlebars.compile($("#temp").html());
			      $("#tbl").html(temp(data));
			}
		});
	}
</script>
</html>