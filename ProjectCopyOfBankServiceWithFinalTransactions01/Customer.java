package ProjectCopyOfBankServiceWithFinalTransactions01;

import java.util.ArrayList;
import java.util.Objects;

public class Customer {

    private String name;
    private String AdharNo;
    private String accountType;
    private float balance;
    private String accountNo;
    private String upiId;
    private String bankName;
    private ArrayList<Transactions> transactions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdharNo() {
        return AdharNo;
    }

    public void setAdharNo(String adharNo) {
        AdharNo = adharNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getBankName() {
        return bankName;
    }

    public ArrayList<Transactions> getTransactions(String accountNo) {

        if(accountNo.contains("BOB")){
            ArrayList<Transactions> t = BankServiceImplOfBob.transactions.get(accountNo);
            return t;
        }else if(accountNo.contains("SBI")){
            ArrayList<Transactions> t = BankServiceImplOfSbi.transactions.get(accountNo);
            return t;
        }else if(accountNo.contains("HDFC")){
            ArrayList<Transactions> t = BankServiceImplOfHdfc.transactions.get(accountNo);
            return t;
        }else if(accountNo.contains("PNB")){
            ArrayList<Transactions> t = BankServiceImplOfPunjabNationalBank.transactions.get(accountNo);
            return t;
        }else{
            return null;
        }
    }

    public void setTransactions(Transactions transactions) {
        if (this.transactions == null) {
            this.transactions = new ArrayList<>();
        }
        this.transactions.add(transactions);
    }

//    public void addTransaction(Transactions transaction) {
//        transactions.put(transaction.getTransactionId(), transaction);
//    }


    @Override
    public String toString() {
        return "Customer: " +
                "\n name= " + name + '\'' +
                ",\n AdharNo = " + AdharNo + '\'' +
                ",\n accountType = " + accountType + '\'' +
                ",\n balance = " + balance +
                ",\n accountNo = " + accountNo + '\'' +
                ",\n bankName = " + bankName + '\'' ;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(accountNo, customer.accountNo);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hashCode(accountNo);
//    }


}
