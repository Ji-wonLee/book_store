<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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

	<button onclick="history.back()" type="submit" >뒤로가기</button>
	<input type="button" id="excelFileExport" value="다운로드"/>
	
	<title>회원 관리</title>
</head>

<body>

<table id="tableData" border='1' style='margin-top:10px'>
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
</body>
</html>