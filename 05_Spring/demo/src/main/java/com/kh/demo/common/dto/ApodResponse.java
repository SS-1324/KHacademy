package com.kh.demo.common.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApodResponse {
    private String date;
    private String title;
    private String explanation;
    private String url;
    private String hdurl;
    private String copyright;

    @JsonProperty("media_type")
    private String mediaType;
}
