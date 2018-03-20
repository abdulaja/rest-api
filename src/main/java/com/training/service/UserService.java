package com.training.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.common.ResponseCode;
import com.training.model.common.AbstractResponse;
import com.training.model.common.CreateUpdateResponse;
import com.training.model.login.LoginRequest;
import com.training.model.login.LoginResponse;
import com.training.model.profile.Location;
import com.training.model.profile.LocationResponse;
import com.training.model.profile.Profile;
import com.training.model.profile.ProfileResponse;
import com.training.repository.LocationJpaRepository;
import com.training.repository.ProfileJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserService {

    @Autowired ProfileJpaRepository profileJpaRepository;
    @Autowired LocationJpaRepository locationJpaRepository;
    private Logger log = LoggerFactory.getLogger(getClass());

    public LoginResponse authLogin(LoginRequest loginRequest) throws JsonProcessingException {
        LoginResponse response = new LoginResponse();
        AbstractResponse abstractResponse = new AbstractResponse();

        if ((!Objects.isNull(loginRequest.getUsername()) || !(loginRequest.getUsername() == ""))
                || (!Objects.isNull(loginRequest.getPassword()) || !(loginRequest.getPassword() == ""))) {
            Profile profile = profileJpaRepository.findByParams(loginRequest.getUsername(), loginRequest.getPassword());
            if (!Objects.isNull(profile)) {
                ProfileResponse profileResponse = new ProfileResponse();
                profileResponse.setId(profile.getId());
                profileResponse.setName(profile.getName());
                profileResponse.setEmail(profile.getEmail());
                profileResponse.setPhone(profile.getPhone());
                profileResponse.setTeam(profile.getTeam());
                profileResponse.setPhoto(profile.getPhoto());

                if (!Objects.isNull(profile.getId())) {
                    List<Location> locations = locationJpaRepository.findByProfileId(profile.getId());
                    if (!Objects.isNull(locations) && locations.size() > 0) {
                        LocationResponse locationResponse = new LocationResponse();
                        locationResponse.setLongitude(locations.get(0).getLongitude());
                        locationResponse.setLatitude(locations.get(0).getLatitude());
                        profileResponse.setLocation(locationResponse);
                    }
                }
                response.setProfile(profileResponse);

                abstractResponse.setResponseStatus(ResponseCode.LOGIN_SUCCESS.getCode());
                abstractResponse.setResponseMessage(ResponseCode.LOGIN_SUCCESS.getMessage());
            } else {
                abstractResponse.setResponseStatus(ResponseCode.USER_NOT_EXISTS.getCode());
                abstractResponse.setResponseMessage(ResponseCode.USER_NOT_EXISTS.getMessage());
            }
        } else {
            abstractResponse.setResponseStatus(ResponseCode.LOGIN_REQUEST_EMPTY.getCode());
            abstractResponse.setResponseMessage(ResponseCode.LOGIN_REQUEST_EMPTY.getMessage());
        }
        response.setAbstractResponse(abstractResponse);

        return response;
    }

    public CreateUpdateResponse createOrUpdate(ProfileResponse profileReq, boolean isEdit) throws JsonProcessingException {
        CreateUpdateResponse response = new CreateUpdateResponse();
        AbstractResponse abstractResponse = new AbstractResponse();

        if (isEdit && Objects.isNull(profileReq.getId())) {
            abstractResponse.setResponseStatus(ResponseCode.PROFILE_ID_EMPTY.getCode());
            abstractResponse.setResponseMessage(ResponseCode.PROFILE_ID_EMPTY.getMessage());
            response.setAbstractResponse(abstractResponse);
            response.setSuccess(false);
            return response;
        }

        if (isEdit) {
            Profile profile = profileJpaRepository.findById(profileReq.getId());
            if (!Objects.isNull(profile)) {
                profile.setId(profileReq.getId());
                profile.setName(profileReq.getName());
                profile.setEmail(profileReq.getEmail());
                profile.setPhone(profileReq.getPhone());
                profile.setTeam(profileReq.getTeam());
                profile.setPhoto(profileReq.getPhoto());

                profile = profileJpaRepository.save(profile);

                if (!Objects.isNull(profileReq.getLocation())) {
                    List<Location> locations = locationJpaRepository.findByProfileId(profile.getId());
                    Location location = null;
                    if (!Objects.isNull(locations) && locations.size() > 0) {
                        location = locations.get(0);
                    } else {
                        location = new Location();
                        location.setProfileId(profile.getId());
                    }
                    location.setLatitude(profileReq.getLocation().getLatitude());
                    location.setLongitude(profileReq.getLocation().getLongitude());
                    location = locationJpaRepository.save(location);
                }
            }
            abstractResponse.setResponseStatus(ResponseCode.EDIT_PROFILE_SUCCESS.getCode());
            abstractResponse.setResponseMessage(ResponseCode.EDIT_PROFILE_SUCCESS.getMessage());
            response.setAbstractResponse(abstractResponse);
            response.setSuccess(true);
        }

        return response;
    }

}
