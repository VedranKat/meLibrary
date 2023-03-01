package springStudy.library.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetail {

    private String code;
    private String message;
    private String description;
}
