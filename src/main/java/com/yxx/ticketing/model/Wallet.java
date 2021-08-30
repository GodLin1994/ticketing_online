package com.yxx.ticketing.model;

/**
 *
 *
 */
public class Wallet {

    private Integer id;
    private Double balance;
    private Integer score;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", score=" + score +
                '}';
    }

    public Wallet(Integer id, Double balance, Integer score) {
        this.id = id;
        this.balance = balance;
        this.score = score;
    }

    public Wallet() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
