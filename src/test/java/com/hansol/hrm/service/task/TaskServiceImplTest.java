package com.hansol.hrm.service.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.task.service.TaskService;
import com.hansol.hrm.task.domain.Task;
import com.hansol.hrm.task.domain.TaskMapper;
import com.hansol.hrm.task.dto.TaskDto;

@SpringBootTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskMapper taskMapper;

	@Test
	@DisplayName("01. 업무 전체 조회 테스트")
	@Order(1)
	void findTasks() {
		//given

		//when
		List<Task> taskEntities = taskMapper.findAll();
		List<TaskDto> taskDtoList = taskService.findTasks();

		//then
		assertEquals(taskEntities.size(), taskDtoList.size());

	}

	@Test
	@DisplayName("01-1. 존재하지 않는 ID로 조회")
	@Order(2)
	void findTaskByNotExistsId() {
		//given
		TaskDto taskDto = TaskDto.builder()
			.name("테스트업무")
			.code("W-999999")
			.build();

		//when
		Long taskId = taskService.addTask(taskDto);

		//then
		assertThrows(BaseException.class, () -> taskService.findTaskBy(taskId + 1L));

	}

	@Test
	@DisplayName("01-2. 중복된 업무코드 등록시 예외처리")
	@Order(3)
	void insertDuplicatedTaskCode() {
		//given
		TaskDto taskDto1= TaskDto.builder()
			.name("테스트업무1")
			.code("W-999991")
			.build();

		TaskDto taskDto2 = TaskDto.builder()
			.name("테스트업무2")
			.code("W-999991")
			.build();


		//when

		//then
		taskService.addTask(taskDto1);
		assertThrows(BaseException.class, () -> taskService.addTask(taskDto2));

	}

	@Test
	@DisplayName("02. 업무 추가 테스트")
	@Order(5)
	void addTask() {
		//given
		TaskDto taskDto = TaskDto.builder()
			.name("테스트업무")
			.code("W-999999")
			.build();

		//when
		Long taskId = taskService.addTask(taskDto);
		TaskDto taskById = taskService.findTaskBy(taskId);

		//then
		assertEquals(taskId, taskById.getId());

	}

	@Test
	@DisplayName("03. 업무 수정 테스트")
	@Order(10)
	void editTask() {
		//given
		TaskDto taskDto = TaskDto.builder()
			.name("테스트업무")
			.code("W-999999")
			.build();

		Long taskId = taskService.addTask(taskDto);
		TaskDto findDto = taskService.findTaskBy(taskId);

		//when
		findDto.setName("테스트업무1");
		findDto.setCode("W-999998");

		taskService.editTask(findDto.getId(), findDto);

		//then
		Task taskEntity = taskMapper.findBy(taskId);

		assertEquals("테스트업무1", taskEntity.getName());
		assertEquals("W-999998", taskEntity.getCode());

	}

	@Test
	@DisplayName("04. 업무 삭제 테스트")
	@Order(15)
	void statusToDeleteTask() {
		//given
		TaskDto taskDto = TaskDto.builder()
			.name("테스트업무")
			.code("W-999999")
			.build();

		Long taskId = taskService.addTask(taskDto);
		TaskDto findDto = taskService.findTaskBy(taskId);

		//when
		taskService.deleteTask(findDto.getId());

		//then
		assertNull(taskMapper.findBy(findDto.getId()));

	}
}
