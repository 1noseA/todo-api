package com.example.todoapi.repository.task;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TastRepository {

    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(long taskId);

    @Select("SELECT id, title FROM tasks")
    List<TaskRecord> selectList();

    // MyBatisのinsertメソッドはvoidでないといけないので戻り値としてidを受け取れない
    // オートインクリメントしたidを受け取るには引数のTaskRecordのidにセットするよう以下を使用
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    void insert(TaskRecord record);
}
