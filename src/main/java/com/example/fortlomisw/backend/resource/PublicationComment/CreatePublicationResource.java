package com.example.fortlomisw.backend.resource.PublicationComment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreatePublicationResource {
    private String CommentDescription;

    private Date registerdate;
}