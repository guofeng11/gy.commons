package gy.commons.dto.Page;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @ClassName RequestPage.java
 * @Author guofeng
 * @Description 分页请求页面信息 在请求参数中使用
 * @Version 1.0.0
 * @Date 2020年05月08日 13:22:00
 */
public class RequestPage implements Serializable {
    private static final long serialVersionUID = -1242147142268893267L;

    //当前页
    @Min(value = 1,message = "{page.current.min}")
    private int currentPage;
    //页容量
    @Min(value = 1,message = "{page.rows.min}")
    private int rowsPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowsPage() {
        return rowsPage;
    }

    public void setRowsPage(int rowsPage) {
        this.rowsPage = rowsPage;
    }

    public RequestPage() {

    }

    public RequestPage(int currentPage, int rowsPage) {
        this.currentPage = currentPage;
        this.rowsPage = rowsPage;
    }

    @Override
    public String toString() {
        return "ResponePage{" +
                "currentPage=" + currentPage +
                ", rowsPage=" + rowsPage +
                '}';
    }
}
