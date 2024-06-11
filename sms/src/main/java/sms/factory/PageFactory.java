package sms.factory;

import org.springframework.stereotype.Service;

@Service
public class PageFactory {
	
	public String getPageBar(int totalData, int cPage, int numPerpage, String url) {
		StringBuffer pageBar= new StringBuffer(); //Pagebar의 구성, 가변의 String 값을 받는 객체로 String이 아닌 StringBuffer을 사용하였습니다.
		int totalPage=(int)(Math.ceil((double)totalData/numPerpage));//나눠지는 페이지 전체 개수
		int pageBarSize=5; //한번에 표기할 객체의 수를 결정한다.

		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1; // 해당 페이지에서 표시해야하는 객체의 시작점
		int pageEnd = pageNo + pageBarSize - 1; // 해당 페이지에서 표시해야하는 객체의 마지막 객체 번호
		
		pageBar.append("<ul style='display: inline-block' class='pagination justify-content-center pagination-sm'>");
		
		if(pageNo==1) {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>이전");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}else {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}
		
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
				pageBar.append("<a class='page-link' href='#'>"+pageNo);
				pageBar.append("</a>");
				pageBar.append("</li>");
			} else {
				pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
				pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>"+pageNo);
				pageBar.append("</a>");
				pageBar.append("</li>");
			}
			pageNo++;
		}
		
		
		if(pageNo>totalPage) {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>다음");
			pageBar.append("</a>");
			pageBar.append("</li>");
		} else {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>다음");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}
		pageBar.append("</ul>");
		
		pageBar.append("<script>");
		pageBar.append("function fn_paging(no){");
		pageBar.append("location.assign('"+url+"?cPage='+no+'&numPerpage="+numPerpage+"');");
		pageBar.append("}");
		pageBar.append("</script>");
		
		return new String(pageBar);
		
	}
}