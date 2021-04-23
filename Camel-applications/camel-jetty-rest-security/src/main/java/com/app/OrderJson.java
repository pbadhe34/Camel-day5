package com.app;


 
public class OrderJson {

     
    private int id;
     
    private String partName;
    
    private int amount;
    
    private String customerName;
    
    public String toString()
    {
       return "{\"id\":" +id+
                ",\"partName\":"+"\"" + partName + "\"" +
               ", \"amount\":"+"\"" + amount + "\"" +
               ", \"customerName\":"+"\""+ customerName +"\"}";  
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
