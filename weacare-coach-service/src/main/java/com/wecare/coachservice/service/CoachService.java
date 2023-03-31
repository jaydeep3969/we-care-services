package com.wecare.coachservice.service;

import com.wecare.coachservice.dto.CoachDTO;
import com.wecare.coachservice.dto.LoginDTO;
import com.wecare.coachservice.entity.CoachEntity;
import com.wecare.coachservice.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoachService {
	@Autowired
	private CoachRepository coachRepository;

	public String createCoach(CoachDTO coachDTO) {
		this.coachRepository.saveAndFlush(CoachDTO.createCoachEntity(coachDTO));
		return "Coach Added Successfully";
	}
	
	public Optional<CoachDTO> getCoachProfile(String coachId) {
		return CoachEntity.prepareCoachDTO(this.coachRepository.findById(coachId));
	}
	
	public boolean loginCoach(LoginDTO loginDTO) {
		Optional<CoachDTO> coach = CoachEntity.prepareCoachDTO(this.coachRepository.findById(loginDTO.getId()));
		
		if(coach.isEmpty()) {
			return false;
		}
		
		return (coach.get().getPassword().equals(loginDTO.getPassword()));
	}
	
	public List<CoachDTO> showAllCoaches() {
		List<CoachEntity> coachEntityList = this.coachRepository.findAll();

		List<CoachDTO> coachDTOList = coachEntityList.stream()
										.map(coachEntity -> CoachEntity.prepareCoachDTO(Optional.of(coachEntity)).get())
										.collect(Collectors.toList());

		return coachDTOList;
	}

	public boolean checkUserExistence(String coachId) {
		Optional<CoachEntity> coach = this.coachRepository.findById(coachId);
		return !coach.isEmpty();
	}
}
