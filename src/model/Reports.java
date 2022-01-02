package model;

public class Reports  {
    private String type;
    private String month;
    private String total;

    public Reports(String type, String month, String total) {
        this.type = type;
        this.month =  month;
        this.total = total;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
