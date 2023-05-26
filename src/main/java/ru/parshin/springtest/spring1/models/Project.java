package ru.parshin.springtest.spring1.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String project_name;

    private Long parent_id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Toggle> toggles;



    private String path = "";

    //-----------------------------------------------------


    public Project() {
    }

    public Project(String project_name) {
        this.project_name = project_name;
    }

    public Project(String project_name, Long parent_id) {
        this.project_name = project_name;
        this.parent_id = parent_id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProjects(Project project) {
        this.projects.add(project);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long project_id) {
        this.id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String toString() {
        return this.project_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if(path.trim().length() != 0) {
            this.path += path + "-";
        }
        else{
            this.path = path;
        }
    }

    public List<Long> getFullUrl(String url){
        List<Long> listUrl = new ArrayList<>();
        if (url.trim().length() == 0){
            listUrl.add(id);
            return listUrl;
        }
        url = url.substring(url.indexOf("-") + 1);
        url += this.id;
        for (String retval : url.split("-", 0)) {
            listUrl.add(Long.parseLong(retval, 10));
        }
        if (listUrl.size() > 0)
            listUrl.remove(listUrl.size() - 1);
        return listUrl;
    }


    public void addToggle(Toggle toggle) {
        this.toggles.add(toggle);
    }

    public List<Toggle> getToggles() {
        return toggles;
    }
}
