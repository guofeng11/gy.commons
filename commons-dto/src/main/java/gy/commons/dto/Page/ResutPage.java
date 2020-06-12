package gy.commons.dto.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ResutPage.java
 * @Author guofeng
 * @Description 分页响应结果 E可序列化的数据类型
 * @Version 1.0.0
 * @Date 2020年05月08日 13:41:00
 */
public class ResutPage<E> implements Serializable {
    private static final long serialVersionUID = -618693714707697493L;

    //返回的页码信息
    private ResponePage pageInfo;
    //返回的数据集
    private List<E> data;

    public ResponePage getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ResponePage pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public ResutPage(ResponePage pageInfo, List<E> data) {
        this.pageInfo = pageInfo;
        this.data = data;
    }

    public ResutPage() {
    }
}
