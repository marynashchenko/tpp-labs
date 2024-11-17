package org.tables.zhek;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.tables.profession.Profession;

@Entity
public class Zhek {

@Id
    @GeneratedValue
    private int zhek_id;

    private String zhek_district;

    private String zhek_address;

    @OneToMany
    private Profession[] professions_id;

    public int getZhek_id() {
        return zhek_id;
    }

    public String getZhek_district() {
        return zhek_district;
    }

    public String getZhek_address() {
        return zhek_address;
    }

    public Profession[] getProfessions_id() {
        return professions_id;
    }

    public void setZhek_id(int zhek_id) {
        this.zhek_id = zhek_id;
    }

    public void setZhek_district(String zhek_district) {
        this.zhek_district = zhek_district;
    }

    public void setZhek_address(String zhek_address) {
        this.zhek_address = zhek_address;
    }

    public void setProfessions_id(Profession[] professions_id) {
        this.professions_id = professions_id;
    }

    public String toString() {
        return "Zhek: " + this.zhek_district + " ID: " + this.zhek_id + " Address: " + this.zhek_address +
                "\n\t Professions: " + java.util.Arrays.toString(this.professions_id);
    }

}
