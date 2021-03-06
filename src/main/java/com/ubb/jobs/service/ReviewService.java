package com.ubb.jobs.service;
import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.ReviewDto;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Review;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.ReviewRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import com.ubb.jobs.utils.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    @Qualifier("MailSender")
    private MailSender mailSender;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;

    public List<ReviewDto> getReviewsOrdered(){
        List<ReviewDto> dtos = reviewRepo.getOrderedReviews();
        return dtos;
    }
    public ReviewDto add(ReviewDto dto) {
       ReviewDto reviewDto = reviewRepo.addReview(dto);
        UserDto userFrom = userRepo.getOne(Integer.valueOf(reviewDto.getUserFrom().getId()));
        UserDto userTo = userRepo.getOne(Integer.valueOf(reviewDto.getUserFor().getId()));
       Thread mailThread = new Thread(()->{
           mailSender.sendMail("Ai primit un nou review din partea userului " + userFrom.getUsername(), "Intra in aplicatie pentru mai multe detalii", userTo.getEmail());
       });
       mailThread.start();
       return reviewDto;
    }

    @Transactional
    public Integer delete(Integer reviewId){
        return reviewRepo.deleteReview(reviewId);
    }

    public ReviewDto getOne(Integer id){
        return reviewRepo.getOne(id);
    }

    public List<ReviewDto> getReviewsOfUser(Integer userId){

        List<ReviewDto> dtos = reviewRepo.findForUser(userId);
        return buildDto(dtos);
    }

    public List<ReviewDto> getReviewsForUser(Integer idUserFor){
        List<ReviewDto> dtos = reviewRepo.findReviewsForUser(idUserFor);
        return buildDto(dtos);
    }

    private List<ReviewDto> buildDto(List<ReviewDto> dtos) {
        return dtos.stream().map(dto-> {
            JobDto job;
            if (dto.getJob().getId() != null) {
                job = jobRepo.findJobById(Integer.valueOf(dto.getJob().getId()));
                dto.getJob().setTitle(job.getTitle());
            }
            UserDto userFor = userRepo.getOne(Integer.valueOf(dto.getUserFor().getId()));
            userFor.setPassword(null);
            dto.setUserFor(userFor);
            UserDto userFrom = userRepo.getOne(Integer.valueOf(dto.getUserFrom().getId()));
            userFrom.setPassword(null);
            dto.setUserFrom(userFrom);
            return dto;
        }).collect(Collectors.toList());
    }

    public double getMeanOfStarsforUser(Integer idUserFor){
        List<ReviewDto> dtos = reviewRepo.findReviewsForUser(idUserFor);
        double mean=0;
        for(ReviewDto dto :dtos)
            mean+=Double.valueOf(dto.getStars());
        return mean/dtos.size();
    }

}
