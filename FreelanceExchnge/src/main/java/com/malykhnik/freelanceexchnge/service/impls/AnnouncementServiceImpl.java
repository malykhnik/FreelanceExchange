package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.repository.AnnouncementRepository;
import com.malykhnik.freelanceexchnge.repository.UserRepository;
import com.malykhnik.freelanceexchnge.service.AnnouncementService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    @Override
    public void saveNewAnnouncement(FreelanceAnnouncement freelanceAnnouncement) {
        String authorityName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByUsername(authorityName) != null) {
            User user = userRepository.findByUsername(authorityName);
            freelanceAnnouncement.setUser(user);
        } else {
            throw new UsernameNotFoundException("Такого пользователя не существует!");
        }
        announcementRepository.save(freelanceAnnouncement);
    }

    public List<FreelanceAnnouncement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public List<FreelanceAnnouncement> findAnnouncementByUsername(String username) {
        return announcementRepository.findAnnouncementByUsername(username);
    }

    public Optional<FreelanceAnnouncement> findAnnouncementById(Long id)  {
        return announcementRepository.findById(id);
    }

    public void deleteAnnouncementById(Long id) {
        announcementRepository.deleteById(id);
    }
}
