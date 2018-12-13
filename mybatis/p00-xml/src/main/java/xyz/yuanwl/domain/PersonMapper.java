package xyz.yuanwl.domain;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/16 14:33
 */
public interface PersonMapper {
	Person getPersonWithIdCard(Integer id);
	Person getPersonWithIdCard2(Integer id);
	Person getPersonWithBankCards(Integer id);
	Person getPersonWithAllInfo(Integer id);
}
