package com.example.guestbook;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookMessageRepository extends PagingAndSortingRepository<GuestbookMessage, Long> {

}

