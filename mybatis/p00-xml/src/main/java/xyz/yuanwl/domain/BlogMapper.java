package xyz.yuanwl.domain;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/16 17:12
 */
public interface BlogMapper {
	List<Blog> findBlogUsingIf(@Param("title") String title, @Param("author") Author author);

	List<Blog> findBlogUsingChoose(@Param("title") String title, @Param("author") Author author);


	List<Blog> findBlogUsingTrim(@Param("state") String state, @Param("title") String title, @Param("author") Author author);

	List<Blog> findBlogUsingWhere(@Param("state") String state, @Param("title") String title, @Param("author") Author author);

	Integer updateBlogUsingTrim(@Param("id") Integer id, @Param("state") String state, @Param("title") String title, @Param("author") Author author, @Param("featured") Integer featured);

	Integer updateBlogUsingSet(@Param("id") Integer id, @Param("state") String state, @Param("title") String title, @Param("author") Author author, @Param("featured") Integer featured);

	List<Blog> findBlogUsingForeach(Integer[] ids);

	List<Blog> findBlogUsingForeachMap(Map params);

	List<Blog> findBlogUsingBind(@Param("title") String title);

}
