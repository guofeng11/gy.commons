package gy.commons.dto.Page;

import java.io.Serializable;

/**
 * @ClassName ResponePage.java
 * @Author guofeng
 * @Description 分页响应信息
 * @Version 1.0.0
 * @Date 2020年05月08日 13:22:00
 */
public class ResponePage implements Serializable {
    private static final long serialVersionUID = -5147912166745851753L;

    //总页数
    private int totalPage;
    //总记录数
    private long totalRows;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public ResponePage(int totalPage, long totalRows) {
        this.totalPage = totalPage;
        this.totalRows = totalRows;
    }

    public ResponePage() {
    }

    @Override
    public String toString() {
        return "ResponePage{" +
                "totalPage=" + totalPage +
                ", totalRows=" + totalRows +
                '}';
    }
}
