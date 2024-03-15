package jp.ac.ohara.E.seisaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.E.seisaku.model.SyusekiHyouModel;
import jp.ac.ohara.E.seisaku.repository.SyusekiRepository;
@Service
@Transactional
public class SyusekiService {

	@Autowired
	private SyusekiRepository repository;

	/**
	 * アドレス帳一覧の取得
	 * @return List<AddressBook>
	 */
	public List<SyusekiHyouModel> getAddressList() {
	    List<SyusekiHyouModel> entityList = this.repository.findAll();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressBook
	 */
	public SyusekiHyouModel get(@NonNull Long index) {
		SyusekiHyouModel seisaku = this.repository.findById(index).orElse(new SyusekiHyouModel());
		return seisaku;
	}

	/**
	 * アドレス帳データの保存
	 * @param AddressBook addressBook
	 */
	public void save(@NonNull SyusekiHyouModel syusekihyou) {
		this.repository.save(syusekihyou);
	}

	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}
