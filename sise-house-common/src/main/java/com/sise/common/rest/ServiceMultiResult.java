package com.sise.common.rest;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/19 20:42
 */
public class ServiceMultiResult<T> implements Serializable {
    private static final long serialVersionUID = -4694309942571729405L;
    private long total;
    private List<T> result;

    public ServiceMultiResult() {
    }

    public ServiceMultiResult(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getResultSize() {
        if (this.result == null) {
            return 0;
        }
        return this.result.size();
    }
}
