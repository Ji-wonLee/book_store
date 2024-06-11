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
	* {
 		 box-sizing: border-box;
	}
	
	section {
    display: flex;
    flex-direction: row;
    align-items: stretch;
	}

	nav {
		float: left;
  		width: 20%;
  		background: #9ec7ae;
 		padding: 20px;
	}
	
	article {
		float: left;
		padding: 20px;
 		width: 80%;
 		background-color: #dbf0e6;
	}
	
	article table {
		border-collapse: collapse;
		width : 100%';
	}
	
	article th {
		border-bottom: 1px solid #dddddd;
  		text-align: center;
  		padding: 8px;
	}
	
	article td {
		border-bottom: 1px solid #dddddd;
  		text-align: left;
  		padding: 8px;
	}
	
	section::after {
		content: "";
  		display: table;
  		clear: both;
	}
	
	footer {
		padding: 10px;
 		text-align: center;
 		color : #8c8c8c;
	}
	
	@media (max-width: 600px){
  		nav, article {
   			 width: 100%;
    		height: auto;
 		 }
	}
</style>
	<meta charset="UTF-8">
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
	</script>
	<script>
		
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
	<section>
		<nav id="nav">
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
				<div class = "userText">
					<h3>관리자 </h3>
					<h3>님 환영합니다. </h3>
				</div>
				<ul>
     				<li><a href="http://localhost:8080/sms">로그아웃</a></li>
   				</ul>
		</nav>
		<article id="article">
				<button onclick="history.back()" type="submit" >뒤로가기</button>
				<input type="button" id="excelFileExport" value="다운로드"/>
				
				<div class = "printInventoryList">
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
			</form>
		</article>
	</section>
	<script>
        <!-- 화면 크기가 변경될 때마다 높이를 조정-->
        window.addEventListener('resize', function() {
            var section = document.getElementById('section');
            var article = document.getElementById('article');
            section.style.height = article.offsetHeight + 'px';
        });

        <!-- 초기 로드 시에도 높이를 조정-->
        window.dispatchEvent(new Event('resize'));
    </script>'
    <script>
    let changedValues = {};
    let inputId = {};

    function valueChanged(input) {
        changedValues[input.id] = input.value;
    }
<%--
    function sendChangedValues() {
        // 변경된 값이 없으면 전송하지 않음
        if (Object.keys(changedValues).length === 0) {
            alert("변경된 값이 없습니다.");
            return;
        }

        // 변경된 값만을 서버로 전송
        fetch('InventoryController', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(changedValues),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('서버 응답 실패');
            }
            return response.json();
        })
        .then(data => {
            // 서버 응답 처리
            console.log(data);
            // 변경된 값 초기화
            changedValues = {};
        })
        .catch(error => {
            console.error('에러:', error);
        });
    }--%>
    </script>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>