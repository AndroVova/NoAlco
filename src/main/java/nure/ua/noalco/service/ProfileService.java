package nure.ua.noalco.service;

import nure.ua.noalco.entity.Profile;

public interface ProfileService {
    Profile getProfile(Long id);
    Profile getProfile(String username);
    Profile saveProfile(Profile user);
}
