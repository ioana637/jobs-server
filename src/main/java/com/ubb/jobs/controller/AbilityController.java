package com.ubb.jobs.controller;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.UserAbilitiesDto;
import com.ubb.jobs.model.Ability;
import com.ubb.jobs.model.Level;
import com.ubb.jobs.model.UserAbilities;
import com.ubb.jobs.service.AbilityService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(EndPoint.ABILITY)
@Slf4j
public class AbilityController {

    @Autowired
    private AbilityService abilityService;

    @GetMapping()
    public ResponseEntity<List<AbilityDto>> getAllAbilities() {
        List<AbilityDto> abilities = abilityService.findAll();
        log.info("Returning " + abilities.toString());
        return new ResponseEntity<>(abilities, HttpStatus.OK);
    }

    @GetMapping(EndPoint.ABILITY_LEVELS)
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = abilityService.findAllAbilitiesLevels();
        log.info("Returning " + levels.toString());
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping("/user={user]")
    public ResponseEntity<List<AbilityDto>> getAbilitiesForUser() {
        return null;
    }

    @PostMapping(EndPoint.USER_ABILITY)
    public ResponseEntity<UserAbilitiesDto> addAbility(@RequestBody UserAbilitiesDto userAbilitiesDto) {
        UserAbilitiesDto saved = abilityService.save(userAbilitiesDto);
        if (saved == null) {
            log.info("Unable to add ability to given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
