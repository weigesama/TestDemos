package xyz.yuanwl.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 基实体类。这个类是手动创建的，生成器不会自动创建
 * @author Yuanwl
 * @date 2018/11/21 14:16
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -2597552918330703181L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
}
