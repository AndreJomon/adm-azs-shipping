package azship.fretecrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class CustomProperty {
    private String name;
    private String value;
}
