package ProjectCopyOfBankServiceWithFinalTransactions;

public class Transactions {

    String transactionId;
    String name;
    float amount;
    String transactionType;
    String transactionDate;
    String accountNo;

    @Override
    public String toString() {
        return "\n=========================== Transaction Details ==========================" +
                "\nName             : " + name +
                "\nAccount Number   : " + accountNo +
                "\nTransaction ID   : " + transactionId +
                "\nAmount           : Rs" + amount +
                "\nTransaction Type : " + transactionType +
                "\nTransaction Date : " + transactionDate +
                "\n==========================================================================";
    }
}
