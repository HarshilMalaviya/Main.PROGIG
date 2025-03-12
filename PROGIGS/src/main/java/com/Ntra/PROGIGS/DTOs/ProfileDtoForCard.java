package com.Ntra.PROGIGS.DTOs;

import lombok.Data;

@Data
public class ProfileDtoForCard {
    private int id;
    private String firstName;
    private String lastName;
    private String fieldOfWork;
    private String imageUrl;
    private String Location;
    private String hourlyRate;
}
