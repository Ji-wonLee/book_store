package sms.factory;

import org.springframework.stereotype.Service;

@Service
public class PageFactory {
    public static String getPageBar(int totalData, int cPage, int numPerpage, String url) {
        StringBuffer pageBar = new StringBuffer();
        int totalPage = (int) (Math.ceil((double) totalData / numPerpage));
        int pageBarSize = 10;

        int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
        int pageEnd = pageNo + pageBarSize - 1;

        pageBar.append("<ul style='display: inline-block' class='pagination justify-content-center pagination-sm'>");

        if (pageNo == 1) {
            pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
            pageBar.append("<a class='page-link' href='#'>이전");
            pageBar.append("</a>");
            pageBar.append("</li>");
        } else {
            pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
            pageBar.append("<a class='page-link' href='" + url + "&cPage=" + (pageNo - 1) + "&numPerpage=" + numPerpage + "'>다음");
            pageBar.append("</a>");
            pageBar.append("</li>");
        }

        while (!(pageNo > pageEnd || pageNo > totalPage)) {
            if (pageNo == cPage) {
                pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
                pageBar.append("<a class='page-link' href='#'>" + pageNo);
                pageBar.append("</a>");
                pageBar.append("</li>");
            } else {
                pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
                pageBar.append("<a class='page-link' href='" + url + "&cPage=" + pageNo + "&numPerpage=" + numPerpage + "'>" + pageNo);
                pageBar.append("</a>");
                pageBar.append("</li>");
            }
            pageNo++;
        }

        if (pageNo > totalPage) {
            pageBar.append("<li style='float:left; list-style-type: none' class='page-item disabled'>");
            pageBar.append("<a class='page-link' href='#'>이전");
            pageBar.append("</a>");
            pageBar.append("</li>");
        } else {
            pageBar.append("<li style='float:left; list-style-type: none' class='page-item'>");
            pageBar.append("<a class='page-link' href='" + url + "&cPage=" + pageNo + "&numPerpage=" + numPerpage + "'>다음");
            pageBar.append("</a>");
            pageBar.append("</li>");
        }
        pageBar.append("</ul>");

        return new String(pageBar);
    }
}
