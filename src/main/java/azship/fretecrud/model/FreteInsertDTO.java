package azship.fretecrud.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class FreteInsertDTO {
    String id;
    HashMap<String, String> freteInfo;
}
