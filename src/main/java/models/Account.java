package models;


public class Account {
    private String accountNumber;
    private String accountName;
    private String city;
    private String state;
    private String salesOrg;
    private String salesChannel;
    private Boolean favorite;

    public Account() {
    }

    public Account(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.accountName = account.getAccountName();
        this.city = account.getCity();
        this.salesOrg = account.getSalesOrg();
        this.state = account.getState();
        this.salesChannel = account.getSalesChannel();
        this.favorite = account.getFavorite();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
