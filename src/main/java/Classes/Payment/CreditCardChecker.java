package Classes.Payment;

import java.time.LocalDate;
import java.util.Calendar;

public class CreditCardChecker {

    private String cardNumber;
    private Integer expirationMonth;
    private Integer expirationYear;

    public CreditCardChecker(String cardNumber, Integer expirationMonth, Integer expirationYear) {
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    public static Boolean checkCreditCardDate(Integer expirationMonth, Integer expirationYear) {
        if(expirationMonth > 12 || expirationMonth < 0)
            return false;
        if(expirationYear > 9999 || expirationYear < 0)
            return false;

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) + 1);

        System.out.println(year + " " +  expirationYear + " " +  month + " " + expirationMonth);


        if(year <= expirationYear) {
            if(year == expirationYear && month > expirationMonth)
                return false;
            return true;
        }

        return false;
    }

    public static Boolean checkCreditCardNumber(String cardNumber) {
        int tempnum = 0;
        int sum = 0;
        boolean odd = false;

        for(int i = cardNumber.length()-1; i >= 0; i--) {
            tempnum = cardNumber.charAt(i) - '0';

            if(odd)
                tempnum *= 2;

            sum += tempnum > 9 ? tempnum - 9 : tempnum;

            odd = !odd;
        }

        System.out.println(sum);

        return (sum % 10 == 0);
    }

    public Boolean check() {
         return (checkCreditCardDate(expirationMonth, expirationYear) && checkCreditCardNumber(cardNumber));
    }
}
