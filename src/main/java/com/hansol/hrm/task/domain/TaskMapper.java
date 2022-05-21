package com.hansol.hrm.task.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskMapper {

	List<Task> findAll();

	Task findBy(Long taskId);

	Task findByString(@Param("name") String name, @Param("code") String code);

	Long insertOne(Task taskEntity);

	Long updateOne(@Param("taskId") Long taskId, @Param("taskEntity") Task task);

	Long deleteOne(Long taskId);
}
