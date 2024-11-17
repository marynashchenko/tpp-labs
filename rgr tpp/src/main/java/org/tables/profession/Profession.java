package org.tables.profession;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.tables.worker.Worker;

import java.util.Arrays;

@Entity
public class Profession {


    @Id
    @GeneratedValue
    private int profession_id;
    private String profession_name;
    private String profession_description;

    @OneToMany
    private Worker[] workers_id;

    public int getProfession_id() {
        return profession_id;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public String getProfession_description() {
        return profession_description;
    }

    public Worker[] getWorkers_id() {
        return workers_id;
    }

    public void setProfession_id(int profession_id) {
        this.profession_id = profession_id;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }

    public void setProfession_description(String profession_description) {
        this.profession_description = profession_description;
    }

    public void setWorkers_id(Worker[] worker_id) {
        this.workers_id = worker_id;
    }

    public String toString() {
        return "Profession: " + this.profession_name + " ID: " + this.profession_id + " Description: " + this.profession_description +
                "\n\t Workers: " + Arrays.toString(this.workers_id);
    }

}
