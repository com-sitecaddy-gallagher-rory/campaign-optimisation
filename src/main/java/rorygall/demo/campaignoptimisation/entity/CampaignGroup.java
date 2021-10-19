package rorygall.demo.campaignoptimisation.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaigngroup")
public class CampaignGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="group", cascade= CascadeType.ALL)
    //@JsonIgnore
    private List<Campaign> campaignList;

    @OneToMany(mappedBy="campaignGroup", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Optimisation> optimisationList;

    @Override
    public String toString() {
        return "CampaignGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", campaignList=" + campaignList +
                ", optimisation=" + optimisationList +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public List<Optimisation> getOptimisationList() {
        return optimisationList;
    }

    public void setOptimisationList(final List<Optimisation> optimisationList) {
        this.optimisationList = optimisationList;
    }

    public void setCampaignList(final List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

/*
    public Optimisation getOptimisation() {
        return optimisation;
    }

    public void setOptimisation(final Optimisation optimisation) {
        this.optimisation = optimisation;
    }
*/
}
