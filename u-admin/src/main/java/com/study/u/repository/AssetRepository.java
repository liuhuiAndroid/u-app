package com.study.u.repository;

import com.study.u.dataobject.Asset;
import com.study.u.dataobject.Product;
import com.study.u.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, String> {

    Asset findByUsername(String username);

}
