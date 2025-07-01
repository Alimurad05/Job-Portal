package az.turing.jobportal.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
    private String message;
    private Integer errorCode;
    private LocalDateTime timestamp;
}
