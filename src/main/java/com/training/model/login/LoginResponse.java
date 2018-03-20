package com.training.model.login;

import com.training.model.common.AbstractResponse;
import com.training.model.profile.Profile;
import com.training.model.profile.ProfileResponse;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private AbstractResponse abstractResponse;
    private ProfileResponse profile;

    public AbstractResponse getAbstractResponse() {
        return abstractResponse;
    }

    public void setAbstractResponse(AbstractResponse abstractResponse) {
        this.abstractResponse = abstractResponse;
    }

    public ProfileResponse getProfile() {
        return profile;
    }

    public void setProfile(ProfileResponse profile) {
        this.profile = profile;
    }
}
