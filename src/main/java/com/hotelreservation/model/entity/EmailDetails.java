package com.hotelreservation.model.entity;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmailDetails {

    private String recipient;

    private String msgBody;

    private String subject;

    private String attachment;

}
