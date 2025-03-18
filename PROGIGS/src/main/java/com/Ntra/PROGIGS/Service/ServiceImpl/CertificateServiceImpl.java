package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.CertificateDto;
import com.Ntra.PROGIGS.Entity.Certificates;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.CertificateMapper;
import com.Ntra.PROGIGS.Repository.CertificateRepo;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private CertificateRepo certificateRepo;
    @Autowired
    private CertificateMapper certificateMapper;

    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;


    @Override
    public CertificateDto editCertificate(CertificateDto certificate, int id) {
        Certificates certificates=certificateRepo.findById(id).orElseThrow(()->new RuntimeException("Certificate not found"));
        certificates.setCertificateIssuer(certificate.getCertificateIssuer());
        certificates.setCertificateName(certificate.getCertificateName());
        certificates.setIssuedDate(certificate.getIssuedDate());
        certificateRepo.save(certificateMapper.MapToCertificates(certificate));
        return certificate;
    }

    @Override
    public CertificateDto addCertificate(CertificateDto certificate) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        int id = user.getProfile().getId();
        Profile profile = this.profileRepo.findById(id).get();
        certificate.setProfile(profile);
        certificateRepo.save(certificateMapper.MapToCertificates(certificate));
        return certificate;
    }

    @Override
    public void removeCertificate(int id) {
        certificateRepo.deleteById(id);
    }


}
