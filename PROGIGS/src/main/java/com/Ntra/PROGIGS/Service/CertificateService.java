package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.CertificateDto;

public interface CertificateService {
    public CertificateDto addCertificate(CertificateDto certificate);
    public void removeCertificate(int id);
}
