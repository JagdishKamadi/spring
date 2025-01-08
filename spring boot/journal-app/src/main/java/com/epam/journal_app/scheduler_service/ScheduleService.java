package com.epam.journal_app.scheduler_service;

import com.epam.journal_app.model.AppUser;
import com.epam.journal_app.repository.AppUserRepository;
import com.epam.journal_app.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final EmailService emailService;
    private final AppUserRepository appUserRepository;

    public ScheduleService(EmailService emailService, AppUserRepository appUserRepository) {
        this.emailService = emailService;
        this.appUserRepository = appUserRepository;
    }

    /**
     * Please refer the below site for cron expression
     * http://www.cronmaker.com/;jsessionid=node01qkzrlnn5w7081izu3acm5e0ot1532922.node0?0
     */
    @Scheduled(cron = "0 2 21 1/1 * ?")
    public void sendEmailToUpdateTheProfile() {
        List<AppUser> appUserList = appUserRepository.findAll();

        appUserList.stream().forEach(appUser -> {
            emailService.sendMail(appUser.getEmail(), "Hi " + appUser.getFullName(), "Testing cron scheduler");
        });
    }
}
