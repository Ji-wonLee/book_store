package sms.factory;

import org.springframework.stereotype.Service;

@Service
public class PageFactory {
	
	public String getPageBar(int totalContext, int currentPage, int numPerPage, String url) {
		StringBuffer pageBar = new StringBuffer(); //Pagebar의 구성, 가변의 String 값을 받는 객체로 String이 아닌 StringBuffer을 사용하였습니다.
		int pageBarSize = 20; //한번에 표기할 객체의 수를 결정한다.
		
		System.out.println("test");
		int pageNo = ((currentPage - 1) / pageBarSize) * pageBarSize; // 해당 페이지에서 표시해야하는 객체의 시작점
		int pageEnd = pageNo + pageBarSize - 1; // 해당 페이지에서 표시해야하는 객체의 마지막 객체 번호
		int totalPage = (int)Math.ceil((double)totalContext / numPerPage); //나눠지는 페이지 전체 개수
		
		pageBar.append("<ul class='pagination justify-content-center pagination-sm'>"); // list를 시작
		
		 if(pageNo == 1) //페이지 구성이 하나일 경우 메롱 <- 나예은
	        {
	            pageBar.append("<li class='page-item' disabled>");
	            pageBar.append("<a class='page-link' href='#' tabindex='-1'>이전</a>");
	            pageBar.append("</li>"); //페이지의 개수를 표시하지 않고 이전을 표시, 페이지 위치를 바꿀 필요가 없음
	        } else {
	            pageBar.append("<li class='page-item'>");
	            pageBar.append("<a class='page-link' href='javascript:fn_paging(" + (pageNo-1) + ")'>이전</a>");
	            pageBar.append("</li>");// 현재 페이지 이전에 이전을 표시
	        }
		
		 while(!(pageNo > pageEnd || pageNo > totalPage)) {//객체의 수가 페이지의 최댓값을 넘어가고, 전체 페이지 수를 넘어가는 경우가 아니라면
			 if(pageNo == currentPage) {
				pageBar.append("<li class='page-item disabled'>");
				pageBar.append("<a class='page-link' href='#'>" + pageNo + "</a>");
				pageBar.append("</li>");
			 } else {
				pageBar.append("<li class='page-item'>");
				pageBar.append("<a class='page-link' href='javascript:fn_paging(" + (pageNo) + ")'>" + pageNo + "</a>");
				pageBar.append("</li>");
			 }
			 pageNo++;
		 }
		 
		 if(pageNo>totalPage) { //페이지가 전체페이지의 수를 넘어가는 경우
			pageBar.append("<li class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>다음</a>");
			pageBar.append("</li>"); //그냥 바로 다음을 생성, 페이지 위치를 바꿀 필요가 없음
		 } else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging(" + (pageNo) + ")'>다음</a>");
			pageBar.append("</li>"); //페이지 수 뒤에 다음을 생성
		 }
		 
		pageBar.append("</ul>");
		 
		pageBar.append("<script>");
		pageBar.append("function fn_paging(no){");
		pageBar.append("location.assign('" + url + "?cPage='+no+'&numPerpage=" + numPerPage + "'); }");
		pageBar.append("</script>");
		 
		System.out.println(pageBar);
		
		return new String(pageBar);
	}
}
