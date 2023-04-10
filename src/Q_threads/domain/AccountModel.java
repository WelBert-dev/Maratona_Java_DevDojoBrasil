package Q_threads.domain;

public class AccountModel {
    private int balance = 50;
    public void withdraw(int amount) { // retirar ou sacar
        this.balance = this.balance - amount;
    }

    public int getBalance() {
        return balance;
    }
}
