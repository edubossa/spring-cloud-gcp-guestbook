package com.example.guestbook;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// http://localhost:8081/guestbookMessages
@RepositoryRestResource
public interface GuestbookMessageRepository extends PagingAndSortingRepository<GuestbookMessage, Long> {
}

