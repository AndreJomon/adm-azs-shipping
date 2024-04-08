package azship.fretecrud.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Frete {
    @Getter
    @Setter
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Getter
    private List<CustomProperty> customProperties = new ArrayList<>();

    public Frete () {
        super();
    }

    public void fillCustomPropertiesFromHashMap(HashMap<String, String> hashMap) {
        for (String key : hashMap.keySet()) {
            this.customProperties.add(new CustomProperty(key, hashMap.get(key)));
        }
    }

    public void updateCustomProperty(CustomProperty customPropertyUpdated) {
        this.customProperties.removeIf(customProperty -> Objects.equals(customPropertyUpdated.getName(), customProperty.getName()));

        this.customProperties.add(customPropertyUpdated);
    }
}
