package sms.factory;

import org.springframework.stereotype.Service;

@Service
public class PageFactory {
	
	public String getPageBar(int totalData, int cPage, int numPerpage, String url) {
		StringBuffer pageBar= new StringBuffer(); //Pagebar�� ����, ������ String ���� �޴� ��ü�� String�� �ƴ� StringBuffer�� ����Ͽ����ϴ�.
		int totalPage=(int)(Math.ceil((double)totalData/numPerpage));//�������� ������ ��ü ����
		int pageBarSize=5; //�ѹ��� ǥ���� ��ü�� ���� �����Ѵ�.

		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1; // �ش� ���������� ǥ���ؾ��ϴ� ��ü�� ������
		int pageEnd = pageNo + pageBarSize - 1; // �ش� ���������� ǥ���ؾ��ϴ� ��ü�� ������ ��ü ��ȣ
		
		pageBar.append("<ul style='display: inline-block' class='pagination justify-content-center pagination-sm'>");
		
		if(pageNo==1) {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>����");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}else {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>����");
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
			pageBar.append("<a class='page-link' href='#'>����");
			pageBar.append("</a>");
			pageBar.append("</li>");
		} else {
			pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>����");
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