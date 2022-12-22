package nure.ua.noalco.service;

import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.Profile;
import nure.ua.noalco.exception.EntityNotFoundException;
import nure.ua.noalco.repository.ProfileRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private ProfileRepository profileRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Profile getProfile(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return unwrapProfile(profile, id);
    }

    @Override
    public Profile getProfile(String username) {
        Optional<Profile> user = profileRepository.findByEmail(username);
        return unwrapProfile(user, 404L);
    }

    @Override
    public Profile saveProfile(Profile profile) {
        profile.setPassword(bCryptPasswordEncoder.encode(profile.getPassword()));
        return profileRepository.save(profile);
    }

    static Profile unwrapProfile(Optional<Profile> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Profile.class);
    }
}
