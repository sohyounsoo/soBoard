package com.board.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class NavigatorInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(NavigatorInfo.class);

    public static final int DEFAULT_PAGE_SIZE  =  15;
    public static final int DEFAULT_SCREEN_SIZE = 10;
    public static final String MIV_ORDER = "miv_sort";
    public static final String MIV_PAGESIZE = "miv_pageSize";
    public static final String MIV_PAGE = "miv_pageNo";
    public static final String MIV_START_INDEX = "miv_start_index";
    public static final String MIV_END_INDEX = "miv_end_index";
    public static final String TOT_COUNT_FIELD_NAME = "total_cnt";
    private int pageNo = 1;
    private int totalCount = 0;
    private int pageCount = 0;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int screenSize = DEFAULT_SCREEN_SIZE;
    private int startPage = 0;
    private int endPage = 0;
    private List list = null;
    private Map<String, Object> param = null;

    /**
     * List페이지에 관련 된 정보를 가지고 다닌다.
     * 
     * @author goindole
     * 
     */
    public NavigatorInfo() {

    }

    /**
     * exam) new NavigatorInfo(_req, this.listOp, new
     * NavigatorOrderInfo("A:group_name"));
     * 
     * @param _req
     * @param listOp
     * @param defaultOrder
     * @param pageSize
     *            : 한페이지에 나오는 기본수 ( 1-100)
     */

    public int getTotalCnt() {
        return totalCount;
    }

    public void setTotalCnt(int count) {
        this.totalCount = count;
    }

    public void setTotalCount(int count) {
        this.totalCount = count;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int currentPage) {
        this.pageNo = currentPage;
    }

    public long getEndPage() {
        return endPage;
    }

    /**
     * Total Pages
     * @return
     */
    public long getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getStartPage() {
        return (pageNo - 1) / screenSize * screenSize + 1;
    }

    /**
     * screensize와 tocalcount 등의 값으로 page등을 계산 한다.
     * 
     */
    public void sync() {
        pageCount = totalCount / pageSize
                + (totalCount % pageSize == 0 ? 0 : 1);
        startPage = (pageNo - 1) / screenSize * screenSize + 1;
        endPage = startPage + screenSize - 1;
        if (endPage > pageCount) {
            endPage = pageCount;
        }
    }

    public void setBoardList(List<BoardVO> list) {
        this.list = list;
        if (list != null) {
            if (list.size() > 0) {
                sync();
            }
        }
    }

    public List<Map<String, Object>> getList() {
        if (this.list == null) {
            return Collections.EMPTY_LIST;
        }
        return this.list;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public String toString() {
        return "";
    }

    public String getPagging() {
        int iTemp = 0;

        if ( this.totalCount < 1 ) {
        	return "";
        }

        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        
        String ctxpath = req.getContextPath();
        
       	String img_path = ctxpath+"/images/front/common";
        String	func_name = "go_Page";
        
        StringBuffer page = new StringBuffer();
        
        int intStartPage = ( (int)Math.floor((pageNo-1)/this.screenSize) * this.screenSize ) + 1;

        page.append("<div class='paging_area' >\n");
        
        if ( intStartPage>1 ) {
	        page.append("<a href=\"javascript:"+func_name+"("+1+")\" class=\"stimg\"> <img src=\""+img_path+"/btn_paging_first.png\" alt=\"맨앞으로\" /> </a>\n");
	    } else {
	        page.append(" <img src=\""+img_path+"/btn_paging_first.png\" alt=\"맨앞으로\" /> \n");
	    }
        
		if ( this.pageNo > 1) {
			page.append("<a href=\"javascript:"+func_name+"("+(this.pageNo-1)+")\" class=\"stimg\"> <img src=\""+img_path+"/btn_paging_prev.png\" alt=\"앞으로\" /> </a>\n");
		} else {
			page.append(" <img src=\""+img_path+"/btn_paging_prev.png\" alt=\"앞으로\" /> \n");
		}
	    
	    for (int i=intStartPage; i<intStartPage + this.screenSize ; i++) {
	    	iTemp=i;
	        if ( i<=this.pageCount ) {
	        	if ( i !=this.pageNo ) {
	        		page.append("<a href='javascript:"+func_name+"("+i+")'>"+ i +"</a>\n");
		        } else {
		        	page.append("<strong>"+i+"</strong>\n");
		        }
	        } else	{
	        	break;
	        }
	    }

	    if ( this.pageNo < this.pageCount && ( iTemp+1 ) < pageCount ) {
	    	page.append("<a href=\"javascript:"+func_name+"("+(this.pageNo+1)+")\" class=\"stimg\"> <img src=\""+img_path+"/btn_paging_next.png\" alt=\"뒤로\" /> </a>\n");
	    } else {
	    	page.append(" <img src=\""+img_path+"/btn_paging_next.png\" alt=\"뒤로\" /> \n");
	    }
	    
	    if (iTemp+1<= this.pageCount) {
	    	page.append("<a href=\"javascript:"+func_name+"("+( pageCount )+")\" class=\"stimg\"> <img src=\""+img_path+"/btn_paging_last.png\" alt=\"맨뒤로\" /> </a>\n");
	    } else {
	    	page.append(" <img src=\""+img_path+"/btn_paging_last.png\" alt=\"맨뒤로\" /> \n");
		}
			
	    page.append("</div>\n");
			
       return page.toString();
    }
    
}
