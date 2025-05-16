package io.camunda.demo.process_payments;


import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BalanceWorker {

    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void increaseBalance(int amount) {
        this.balance += amount;
    }

    @JobWorker(type = "increase-balance")
    public Map<String, Integer> increaseBalance() {
        increaseBalance(20);
        return Map.of("balance", getBalance());
    }
}
