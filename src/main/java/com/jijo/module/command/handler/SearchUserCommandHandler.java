package com.jijo.module.command.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jijo.module.command.SearchUserCommand;
import com.jijo.module.dto.UserSearchResultDto;
import com.jijo.module.exception.ModuleRuntimeException;
import com.jijo.module.model.User;

/**
 * @author jijo.lawrence
 *
 */
public class SearchUserCommandHandler extends AbstractCommandHandler {

    private static final String PHONE = "phone";

    private static final String EMAIL = "email";

    private static final String ADDRESS = "address";

    private static final String SEX = "sex";

    private static final String LAST_NAME = "lastName";

    private static final String FIRST_NAME = "firstName";

    private static final String ID = "id";

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchUserCommandHandler.class);

    @Autowired
    private EntityManager em;

    @Transactional
    @CommandHandler
    public UserSearchResultDto handle(final SearchUserCommand cmd) {
        LOGGER.debug("Searching User: {0}", cmd);
        try {
            UserSearchResultDto dto = searchUsingCriteria(cmd);
            LOGGER.debug("User Search finished: {0}", cmd);
            return dto;
        } catch (Exception e) {
            throw new ModuleRuntimeException(e);
        }
    }

    /**
     * @param cmd
     * @return
     */
    private UserSearchResultDto searchUsingCriteria(final SearchUserCommand cmd) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);

        Root<User> user = cq.from(User.class);
        cq.orderBy(cb.asc(user.get(ID)));

        // Column selections
        String[] columnList = cmd.getColumnList().split(",");
        @SuppressWarnings("rawtypes")
        List<Selection> selectionList = new ArrayList<>();
        for (String column : columnList) {
            selectionList.add(user.get(column.trim()));
        }
        cq.multiselect(selectionList.toArray(new Selection[selectionList.size()]));

        // User filtering
        List<Predicate> predicateList = new ArrayList<>();
        if (cmd.getUser().getId() != null) {
            predicateList.add(cb.equal(user.get(ID), cmd.getUser().getId()));
        }

        if (cmd.getUser().getFirstName() != null) {
            predicateList.add(cb.equal(user.get(FIRST_NAME), cmd.getUser().getFirstName()));
        }

        if (cmd.getUser().getLastName() != null) {
            predicateList.add(cb.equal(user.get(LAST_NAME), cmd.getUser().getLastName()));
        }

        if (cmd.getUser().getSex() != null) {
            predicateList.add(cb.equal(user.get(SEX), cmd.getUser().getSex()));
        }

        if (cmd.getUser().getAddress() != null) {
            predicateList.add(cb.equal(user.get(ADDRESS), cmd.getUser().getAddress()));
        }

        if (cmd.getUser().getEmail() != null) {
            predicateList.add(cb.equal(user.get(EMAIL), cmd.getUser().getEmail()));
        }

        if (cmd.getUser().getPhone() != null) {
            predicateList.add(cb.equal(user.get(PHONE), cmd.getUser().getPhone()));
        }
        cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

        TypedQuery<Object> query = em.createQuery(cq);

        // Pagination
        query.setFirstResult((cmd.getPageNumber() - 1) * cmd.getPageSize());
        query.setMaxResults(cmd.getPageSize());

        // final result
        UserSearchResultDto dto = new UserSearchResultDto();
        dto.setResultList(query.getResultList());
        return dto;
    }

}
