package com.sz.mockbean.dao.po;

import java.io.Serializable;

public class Demo implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String val;

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String VAL = "val";

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return val 
     */
    public String getVal() {
        return val;
    }

    /**
     * 
     * @param val 
     */
    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", val=").append(val);
        sb.append("]");
        return sb.toString();
    }
}