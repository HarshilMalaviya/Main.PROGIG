package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.CertificateDto;
import com.Ntra.PROGIGS.Service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @PostMapping("/addcertificate")
    public CertificateDto addCertificate(@RequestBody CertificateDto certificateDto) {
        return certificateService.addCertificate(certificateDto);
    }

    @DeleteMapping("/deletecertificate/{id}")
    public void deleteCertificate(@PathVariable int id) {
        certificateService.removeCertificate(id);
    }
}
