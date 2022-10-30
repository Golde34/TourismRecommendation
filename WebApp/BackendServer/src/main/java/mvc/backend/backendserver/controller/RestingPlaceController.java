package mvc.backend.backendserver.controller;


import mvc.backend.backendserver.entity.RestingPlace;
import mvc.backend.backendserver.repository.RestingPlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restingplace")

public class RestingPlaceController {
    @Autowired
    private RestingPlaceRepo restingPlaceRepo;

    @GetMapping("/all")
    public List<RestingPlace> getAllRestingPlace()
    {
        List<RestingPlace> restingPlaceList = restingPlaceRepo.findAll();
        return restingPlaceList;
    }
}
