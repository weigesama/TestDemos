package xyz.yuanwl.domain;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/15 16:50
 */
public interface OrderMapper {
	Order getOrderErr(Integer id);

	Order getOrderSucc(Integer id);

	Order getOrder2ResultMap(Integer id);
}
