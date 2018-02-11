package com.accounting.service;

import com.accounting.bean.CcodeMaster;
import com.accounting.dao.CcodeDao;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("codeservice")
@Transactional
public class CcodeService
{
  @Autowired
  private CcodeDao cDao;
  
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public int saveCode(CcodeMaster code)
  {
    return this.cDao.saveCode(code);
  }
  
  public List<CcodeMaster> listCode()
  {
    return this.cDao.listCcode();
  }
  
  public void deleteCode(int id)throws ConstraintViolationException
  {
    this.cDao.deleteCcode(id);
  }
  
  public CcodeMaster getCodeByID(int id)
  {
    return this.cDao.getCcodeByid(id);
  }
  public boolean checkCcode(String us) {
        return cDao.checkCcode(us);
    }
 
}
