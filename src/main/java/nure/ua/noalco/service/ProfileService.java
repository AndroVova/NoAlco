package nure.ua.noalco.service;

import nure.ua.noalco.entity.Profile;

public interface ProfileService {
    Profile getProfile(Long id);
    Profile getProfile(String username);
    Profile updateProfile(Long id);
    Profile saveProfile(Profile user);
}
