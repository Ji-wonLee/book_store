package sms.factory;

import org.springframework.stereotype.Service;

@Service
public class PageFactory {
	
	public String getPageBar(int totalContext, int currentPage, int numPerPage, String url) {
		StringBuffer pageBar = new StringBuffer(); //Pagebar�� ����, ������ String ���� �޴� ��ü�� String�� �ƴ� StringBuffer�� ����Ͽ����ϴ�.
		int pageBarSize = 20; //�ѹ��� ǥ���� ��ü�� ���� �����Ѵ�.
		
		System.out.println("test");
		int pageNo = ((currentPage - 1) / pageBarSize) * pageBarSize; // �ش� ���������� ǥ���ؾ��ϴ� ��ü�� ������
		int pageEnd = pageNo + pageBarSize - 1; // �ش� ���������� ǥ���ؾ��ϴ� ��ü�� ������ ��ü ��ȣ
		int totalPage = (int)Math.ceil((double)totalContext / numPerPage); //�������� ������ ��ü ����
		
		pageBar.append("<ul class='pagination justify-content-center pagination-sm'>"); // list�� ����
		
		 if(pageNo == 1) //������ ������ �ϳ��� ��� �޷� <- ������
	        {
	            pageBar.append("<li class='page-item' disabled>");
	            pageBar.append("<a class='page-link' href='#' tabindex='-1'>����</a>");
	            pageBar.append("</li>"); //�������� ������ ǥ������ �ʰ� ������ ǥ��, ������ ��ġ�� �ٲ� �ʿ䰡 ����
	        } else {
	            pageBar.append("<li class='page-item'>");
	            pageBar.append("<a class='page-link' href='javascript:fn_paging(" + (pageNo-1) + ")'>����</a>");
	            pageBar.append("</li>");// ���� ������ ������ ������ ǥ��
	        }
		
		 while(!(pageNo > pageEnd || pageNo > totalPage)) {//��ü�� ���� �������� �ִ��� �Ѿ��, ��ü ������ ���� �Ѿ�� ��찡 �ƴ϶��
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
		 
		 if(pageNo>totalPage) { //�������� ��ü�������� ���� �Ѿ�� ���
			pageBar.append("<li class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>����</a>");
			pageBar.append("</li>"); //�׳� �ٷ� ������ ����, ������ ��ġ�� �ٲ� �ʿ䰡 ����
		 } else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging(" + (pageNo) + ")'>����</a>");
			pageBar.append("</li>"); //������ �� �ڿ� ������ ����
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
