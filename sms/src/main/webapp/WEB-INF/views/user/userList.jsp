<%@page import="sms.dto.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
    	@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR&display=swap');
    	.header-container {
       		font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
            padding: 0 20px;
        }

        .header-container h3 {
            margin: 0;
        }

        nav {
        	font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
            width: 100%;
            background: #ffd9df;
            padding: 10px 0;
        }

        nav ul {
            display: flex;
            justify-content: space-around;
            list-style: none;
            padding: 0;
            margin: 0;
        }

        nav ul li {
            margin: 0;
        }

        nav ul li a {
            text-decoration: none;
            color: black;
        }
		.buttons {
			font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
		}
        footer {
        	font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
            padding: 10px;
            text-align: center;
            color: #8c8c8c;
        }

        .table-container {
        	font-family: "Hahmlet", serif;
 			font-optical-sizing: auto;
  			font-weight: 400;
  			font-style: normal;
            margin-top: 20px;
        }

        table {
	        font-family: "Hahmlet", serif;
  			font-optical-sizing: auto;
  			font-weight: 400;
  			font-style: normal;
        	border-collapse: collapse;
            width: 100%;
        
        }

        th, td {
            border-bottom: 1px solid #ddd;
            padding: 8px;
        }

        th {
        	text-align: center;
            background-color: #f2f2f2;
            text-align: left;
        }

        td img {
        	text-align: left;
            max-width: 50px;
            height: auto;
        }
		.pageBar{
			text-align: center;
			font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
 			font-size: 1.5em;
 			padding: 8px 20px;
		}
        .page-link {
        	display: inline-block;
            color: black;
            float: center;
            padding: 8px 20px;
            text-decoration: none;
        }

        .page-link:hover:not(.active) {
            background-color: #c2c2d6;
            border-radius: 5px;
        }

        @media (max-width: 600px) {
            nav, article {
                width: 100%;
                height: auto;
            }
        }
</style>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
	<script>
		function s2ab(s) { 
    		var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
    		var view = new Uint8Array(buf);  //create uint8array as viewer
    		for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
    			return buf;    
		}
	
		function exportExcel(){ 
    		// step 1. workbook 생성
    		var wb = XLSX.utils.book_new();
    		// step 2. 시트 만들기 
    		var newWorksheet = excelHandler.getWorksheet();
    		// step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
    		XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());
    		// step 4. 엑셀 파일 만들기 
    		var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
    		// step 5. 엑셀 파일 내보내기 
    		saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), excelHandler.getExcelFileName());
		}
	
		$(document).ready(function() { 
    		$("#excelFileExport").click(function(){
       			exportExcel();
    		});
		});
		var today = new Date();

		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);
		var hours = ('0' + today.getHours()).slice(-2); 
		var minutes = ('0' + today.getMinutes()).slice(-2);
		var seconds = ('0' + today.getSeconds()).slice(-2);
		
		var dateString = year + month + day+"_" + hours + minutes;
		
		var excelHandler = {
        	getExcelFileName : function(){
            	return 'user_manager_list_'+dateString+'.xlsx';
        	},
        	
        	getSheetName : function(){
            	return '사용자관리_'+dateString;
        	},
        	
        	getExcelData : function(){
            	return document.getElementById('tableData'); 
        	},
        	
        	getWorksheet : function(){
            	return XLSX.utils.table_to_sheet(this.getExcelData());
        	}
		}
	</script>
	<title>회원 관리</title>
</head>
<body>
	<div class = "header-container">
		<h3>관리자 ${user_id} 님 환영합니다.</h3>
     	<a href="/sms/toAdminMain"><img style="height:100px; width:auto;" src="resources/logo_01.png" alt="logo" width=auto height="100px"/></a>
     	<button type="button" onclick="location.href='index.jsp'">로그아웃</button>
   	</div>
	<nav>
        <ul>
            <li><a href="/sms/toAdminMain">메인화면</a></li>
        </ul>
    </nav>
    <section>
    	<div class= buttons>
		<button style="margin-top: 3px;border-radius: 3px; width: 150px; height: 40px; background-color : #f7e1ea;" onclick="history.back()" type="submit" >뒤로가기</button>
		<input style="margin-top: 3px; border-radius: 3px; width: 150px; height: 40px; background-color : #f7e1ea;" type="button" id="excelFileExport" value="다운로드"/>
		</div>
			<div class="table-container">
				<table id = "tableData">
					<tr>
						<th>사용자아이디</th>
						<th>사용자명</th>
						<th>사용자주소</th>
						<th>사용자연락처</th>
						<th>사용자등급</th>
					</tr>
					<c:forEach var = "t" items="${userList}">
						<tr>
							<td>${t.user_id}</td>
							<td>${t.name}</td>
							<td>${t.address}</td>
							<td>${t.phonenum}</td>
							<td>${t.gname}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
	</section>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>