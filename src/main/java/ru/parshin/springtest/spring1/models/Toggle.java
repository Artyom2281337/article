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

    private String name;

    private String link;

    private boolean active = false;

    //----------------------------------------


    public Toggle() {
    }

    public Toggle(String toggle_name) {
        this.name = toggle_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String toggle_name) {
        this.name = toggle_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String toggle_type) {
        this.link = toggle_type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name;
    }
}
