package jp.ac.ohara.E.seisaku.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.ohara.E.seisaku.model.GakuseiHyouModel;

public interface GakuseiRepository extends JpaRepository<GakuseiHyouModel, Long> {
	public Optional<GakuseiHyouModel> findByNameAndMail(String name,String mail);
}