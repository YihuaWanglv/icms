
package com.icms.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.icms.model.Post;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

	Page<Post> findByCid(Integer cid, Pageable pageable);

}
