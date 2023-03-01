package springStudy.library.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessage {

    private Date date;
    private String message;
    private String description;
    private List<ErrorDetail> errorDetails;


}
