package sms.dto;

public class PaymentDto {
    private String userId;
    private String recipientName;
    private String recipientAddress;
    private String payerName;
    private String payerAccount;

    public PaymentDto() {}
    
    public PaymentDto(String userId, String recipientName, String recipientAddress, String payerName, String payerAccount) {
        this.userId = userId;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.payerName = payerName;
        this.payerAccount = payerAccount;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }
}
