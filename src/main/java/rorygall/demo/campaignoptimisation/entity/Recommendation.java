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
import javax.persistence.OneToOne;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name = "budget")
    private BigDecimal budgetRecommended;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_optimisation")
    @JsonIgnore
    private Optimisation optimisation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_campaign")
    //@JsonIgnore
    private Campaign campaign;

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", budget=" + budgetRecommended +
                //", optimisation=" + optimisation +
                ", campaign=" + campaign.getName() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public BigDecimal getBudgetRecommended() {
        return budgetRecommended;
    }

    public void setBudgetRecommended(final BigDecimal budgetRecommended) {
        this.budgetRecommended = budgetRecommended;
    }

    public Optimisation getOptimisation() {
        return optimisation;
    }

    public void setOptimisation(final Optimisation optimisation) {
        this.optimisation = optimisation;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(final Campaign campaign) {
        this.campaign = campaign;
    }
}
