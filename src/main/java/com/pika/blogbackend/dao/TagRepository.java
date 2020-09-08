package com.pika.blogbackend.dao;

import com.pika.blogbackend.po.Tag;
import com.pika.blogbackend.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by limi on 2017/10/16.
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
    Tag getTagById(Long id);
}
