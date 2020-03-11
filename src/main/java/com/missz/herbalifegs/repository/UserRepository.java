package com.missz.herbalifegs.repository;

import com.missz.herbalifegs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


//继承jparepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsernameAndPassword(String username,String password);

}
