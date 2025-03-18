package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.CertificateDto;
import com.Ntra.PROGIGS.Entity.Certificates;
import com.Ntra.PROGIGS.Service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile/certification")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public CertificateDto addCertificate(@RequestBody CertificateDto certificateDto) {
        return certificateService.addCertificate(certificateDto);
    }
    @PutMapping
    public CertificateDto updateCertificate(@RequestBody CertificateDto certificateDto,@PathVariable int id) {
        return certificateService.editCertificate(certificateDto,id);
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable int id) {
        certificateService.removeCertificate(id);
    }
}
