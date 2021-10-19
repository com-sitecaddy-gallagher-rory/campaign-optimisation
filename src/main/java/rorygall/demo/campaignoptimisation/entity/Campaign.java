package rorygall.demo.campaignoptimisation.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Campaign {

    /*public Campaign(String name, BigDecimal budget, int impressions, BigDecimal revenue) {
        this.name = name;
        this.budget = budget;
        this.impressions = impressions;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private BigDecimal budget;

    @Column(name = "impressions")
    private int impressions;

    @Column(name = "revenue")
    private BigDecimal revenue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_group")
    @JsonIgnore
    private CampaignGroup group;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Recommendation> recommendationList;

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", impressions=" + impressions +
                ", revenue=" + revenue +
                //", group=" + group.getName() +
                //", recommendation=" + recommendation +
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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(final BigDecimal budget) {
        this.budget = budget;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(final int impressions) {
        this.impressions = impressions;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(final BigDecimal revenue) {
        this.revenue = revenue;
    }

    public CampaignGroup getGroup() {
        return group;
    }

    public void setGroup(final CampaignGroup group) {
        this.group = group;
    }
}
