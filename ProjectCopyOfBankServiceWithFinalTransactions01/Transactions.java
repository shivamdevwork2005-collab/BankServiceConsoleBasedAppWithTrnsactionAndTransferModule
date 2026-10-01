package ProjectCopyOfBankServiceWithFinalTransactions01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transactions {

    String transactionId;
    String name;
    float amount;
    String transactionType;
    String transactionDate;
    String accountNo;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return "\n=========================== Transaction Details ==========================" +
                "\nName             : " + name +
                "\nAccount Number   : " + accountNo +
                "\nTransaction ID   : " + transactionId +
                "\nAmount           : Rs" + amount +
                "\nTransaction Type : " + transactionType +
                "\nTransaction Date : " + sdf.format(new Date()) +
                "\n==========================================================================";
    }
}
