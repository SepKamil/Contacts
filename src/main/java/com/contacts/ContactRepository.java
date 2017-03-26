package com.contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kamil on 2017-03-25.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
