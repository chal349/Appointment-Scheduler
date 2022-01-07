package model;

/**
 * @author Corey Hall
 */

/**
 * Reports Class and variables- contains methods used in controller classes.
 */
public class Reports  {
    private String type;
    private String month;
    private String total;

    /**
     * Reports Constructor
     * @param type
     * @param month
     * @param total
     */
    public Reports(String type, String month, String total) {
        this.type = type;
        this.month =  month;
        this.total = total;
    }

    //GETTERS AND SETTERS

    /**
     * gets month
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * sets month
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * gets type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * sets type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets total
     * @return total
     */
    public String getTotal() {
        return total;
    }

    /**
     * sets total
     * @param total
     */
    public void setTotal(String total) {
        this.total = total;
    }
}
