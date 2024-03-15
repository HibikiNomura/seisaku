	package jp.ac.ohara.E.seisaku.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.E.seisaku.model.GakuseiHyouModel;
import jp.ac.ohara.E.seisaku.repository.GakuseiRepository;
 

@Service
@Transactional
public class  GakuseiService{

    @Autowired
    private GakuseiRepository repository;

    /**
     * 学生一覧の取得
     * @return List<Gakusei>
     */
    public List<GakuseiHyouModel> getGakuseiList() {
        List<GakuseiHyouModel> entityList = this.repository.findAll();
        return entityList;
    }

    /**
     * 詳細データの取得
     * @param @NonNull Long index

     */
    public GakuseiHyouModel get(@NonNull Long index) {
        GakuseiHyouModel gakusei = this.repository.findById(index).orElse(new GakuseiHyouModel());
        return gakusei;
    }

    public void save(@NonNull GakuseiHyouModel gakusei) {
        this.repository.save(gakusei);
    }

    /**
     * アドレス帳データの削除
     * @param @NonNull Long index
     */
    public void delete(@NonNull Long index) {
        this.repository.deleteById(index);
    }
    
    public boolean login(String name, String mail) {
    	Optional<GakuseiHyouModel> option = repository.findByNameAndMail(name, mail);
    	return option.isPresent();
    }
}