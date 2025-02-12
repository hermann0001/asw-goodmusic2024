package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecensioneBreveService {

    @Autowired
    private RecensioneBreveRepository recensioneBreveRepository;

    public void createRecensioneBreve(RecensioneBreve r) {
        this.recensioneBreveRepository.save(r);
    }

}
