package ru.parshin.springtest.spring1.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Toggle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String toggle_name;

    private String toggle_type;

    private boolean active = false;

    //----------------------------------------


    public Toggle() {
    }

    public Toggle(String toggle_name) {
        this.toggle_name = toggle_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToggle_name() {
        return toggle_name;
    }

    public void setToggle_name(String toggle_name) {
        this.toggle_name = toggle_name;
    }

    public String getToggle_type() {
        return toggle_type;
    }

    public void setToggle_type(String toggle_type) {
        this.toggle_type = toggle_type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return toggle_name;
    }
}
