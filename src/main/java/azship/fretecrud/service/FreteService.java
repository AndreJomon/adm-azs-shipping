package azship.fretecrud.service;

import azship.fretecrud.model.CustomProperty;
import azship.fretecrud.model.Frete;
import azship.fretecrud.model.FreteInsertDTO;
import azship.fretecrud.repository.FreteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FreteService {
    @Autowired
    FreteRepository freteRepository;

    @Autowired
    MongoTemplate template;
    public Pageable getPageable(Integer page) {
        int resultPerPage = 5;
        return PageRequest.of(page, resultPerPage);
    }

    public void insertFrete(Frete frete) {
        freteRepository.insert(frete);
    }

    public Page<Frete> searchAllFrete(Integer page) {
        Query query = new Query();

        Pageable pageable = getPageable(page);

        query.with(pageable);

        List<Frete> list = template.find(query, Frete.class);

        return PageableExecutionUtils.getPage(list, pageable, () -> template.count(Query.of(query).limit(-1).skip(-1), Frete.class));
    }

    public Page<Frete> searchFrete(String value, Integer page) {

        Query query = new Query();

        Pageable pageable = getPageable(page);

        query.with(pageable);

        query.addCriteria(Criteria.where("customProperties").elemMatch(Criteria.where("value").is(value)));

        List<Frete> list = template.find(query, Frete.class);

        return PageableExecutionUtils.getPage(list, pageable, () -> template.count(Query.of(query).limit(-1).skip(-1), Frete.class));
    }

    public void deleteFrete(String id) {
        freteRepository.deleteById(id);
    }

    public void updateFrete(FreteInsertDTO dto) {
        Frete frete = template.findById(dto.getId(), Frete.class);

        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(new ObjectId(dto.getId())));

        if (frete != null) {
            for (String key: dto.getFreteInfo().keySet()) {
                frete.updateCustomProperty(new CustomProperty(key, dto.getFreteInfo().get(key)));
            }
            template.findAndReplace(query, frete);
        }
    }
}
