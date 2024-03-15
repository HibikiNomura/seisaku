package jp.ac.ohara.E.seisaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.E.seisaku.model.SeisekiHyouModel;
import jp.ac.ohara.E.seisaku.repository.SeisekiRepository;
@Service
@Transactional
public class SeisekiService {

	@Autowired
	private SeisekiRepository repository;

	/**
	 * アドレス帳一覧の取得
	 * @return List<AddressBook>
	 */
	public List<SeisekiHyouModel> getAddressList() {
	    List<SeisekiHyouModel> entityList = this.repository.findAll();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressBook
	 */
	public SeisekiHyouModel get(@NonNull Long index) {
		SeisekiHyouModel seisaku = this.repository.findById(index).orElse(new SeisekiHyouModel());
		return seisaku;
	}

	/**
	 * アドレス帳データの保存
	 * @param AddressBook addressBook
	 */
	public void save(@NonNull SeisekiHyouModel seisekihyou) {
		this.repository.save(seisekihyou);
	}

	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}
