
package com.icms.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.icms.model.Post;


public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

	

}
