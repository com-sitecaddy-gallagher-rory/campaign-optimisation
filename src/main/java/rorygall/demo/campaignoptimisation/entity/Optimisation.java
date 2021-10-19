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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Optimisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;

/* FOR ONLY 1 PER GROUP
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_group")
    @JsonIgnore
    private CampaignGroup group;
*/
    @ManyToOne(cascade= {CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name="id_group")
    @JsonIgnore
    private CampaignGroup campaignGroup;

    @OneToMany(mappedBy="optimisation", cascade= CascadeType.ALL)
    private List<Recommendation> recommendationsList;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public CampaignGroup getCampaignGroup() {
        return campaignGroup;
    }

    public void setCampaignGroup(final CampaignGroup campaignGroup) {
        this.campaignGroup = campaignGroup;
    }

    public List<Recommendation> getRecommendationsList() {
        return recommendationsList;
    }

    public void setRecommendationsList(final List<Recommendation> recommendationsList) {
        this.recommendationsList = recommendationsList;
    }

    @Override
    public String toString() {
        return "Optimisation{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", group=" + campaignGroup.getName() +
                ", recommendationsList=" + recommendationsList +
                '}';
    }
}
