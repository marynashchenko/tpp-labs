package org.tables.worker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Worker {

    @Id
    @GeneratedValue
    private int worker_id;
    private String worker_name;
    private String worker_surname;
    private Integer worker_age;


    public int getWorker_id() {
        return worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public String getWorker_surname() {
        return worker_surname;
    }

    public Integer getWorker_age() {
        return worker_age;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public void setWorker_surname(String worker_surname) {
        this.worker_surname = worker_surname;
    }

    public void setWorker_age(Integer worker_age) {
        this.worker_age = worker_age;
    }

    public String toString() {
        return "Worker: " + this.worker_name + " " + this.worker_surname + " ID: " + this.worker_id + " Age: " + this.worker_age;
    }
}
