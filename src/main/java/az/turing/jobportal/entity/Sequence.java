package az.turing.jobportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {
    private String id;
    private long seq;

}
