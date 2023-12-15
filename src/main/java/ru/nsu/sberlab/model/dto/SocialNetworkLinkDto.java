package ru.nsu.sberlab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialNetworkLinkDto {
    private final String name;
    private final String baseUrl;
    private final String shortName;
}
