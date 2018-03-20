package com.training.model.profile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="location")
@NamedQuery(name="Locatino.findAll", query="SELECT p FROM Location p")
public class Location implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String longitude;
    private String latitude;

    @Column(name = "profile_id")
    private Integer profileId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }
}
