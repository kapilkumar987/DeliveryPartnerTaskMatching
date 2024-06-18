package com.scaler.deliverypartnertaskmatching.controllers;

import com.scaler.deliverypartnertaskmatching.models.Partner;
import com.scaler.deliverypartnertaskmatching.repositories.PartnerRepository;
import com.scaler.deliverypartnertaskmatching.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partners")
public class PartnerLocationController
{
    private final PartnerRepository partnerRepository;
    private final RedisService redisService;

    @Autowired
    public PartnerLocationController(PartnerRepository partnerRepository, RedisService redisService)
    {
        this.partnerRepository = partnerRepository;
        this.redisService = redisService;
    }

    @PostMapping("")
    public Partner addPartner(@RequestBody Partner partner)
    {
        partner.setAvailable(true);
        // return this.partnerRepository.save(partner);

        this.redisService.savePartner(partner);
        return partner;
    }

    @GetMapping("")
    public List<Object> getAllPartners()
    {
        return this.redisService.getAllPartners();
    }

    @PutMapping("/{id}/availability")
    public Partner updateAvailability(@PathVariable Long id, @RequestBody boolean availability)
    {
        Optional<Partner> optionalPartner = this.redisService.getPartnerById(id);
        if (!optionalPartner.isPresent()) {
            throw new RuntimeException("Partner not found");
        }

        Partner partner = optionalPartner.get();
        partner.setAvailable(availability);
        redisService.savePartner(partner);
        return partner;

        /*

        Partner partner = this.partnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Partner not found"));
        partner.setAvailable(availability);
        return this.partnerRepository.save(partner);

         */
    }


}
