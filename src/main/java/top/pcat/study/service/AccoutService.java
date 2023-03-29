package top.pcat.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.AccountDao;
import top.pcat.study.dao.ClassDao;

@Transactional
@Slf4j
@Service
public class AccoutService {


    @Autowired
    AccountDao accountDao;

    public String getRongToken(String userId) {
        return accountDao.getRongToken(userId);
    }
}
