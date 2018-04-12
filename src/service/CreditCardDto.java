package service;

public class CreditCardDto {

    private String cardNumber;
    private Integer cardId;
    private String cardType;
    private Integer password;
    private Float amount;
    private Integer userId;

    public CreditCardDto(Integer cardId, String cardType, String cardNumber, Integer password, Float amount, Integer userId){
        this.cardNumber = cardNumber;
        this.cardId = cardId;
        this.amount = amount;
        this.cardType = cardType;
        this.password =password;
        this.userId = userId;

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        cardId = cardId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
