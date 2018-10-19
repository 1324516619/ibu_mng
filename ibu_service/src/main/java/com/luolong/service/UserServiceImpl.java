package com.luolong.service;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luolong.dao.ExportDataDao;
import com.luolong.dao.UserDao;
import com.luolong.model.ExportDataVo;
import com.luolong.model.Role;
import com.luolong.model.User;
import com.luolong.model.UserRole;
import com.luolong.page.Pagination;
import com.luolong.service.async.ExportDataServiceAsync;
import com.luolong.util.DateUtils;
import com.luolong.util.PrimaryGenerater;
import com.luolong.util.dialect.Page;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired  
    private UserDao userDao; 
    
    @Autowired
    private ExportDataDao exportDataDao;
    
    @Autowired
    private ExportDataServiceAsync exportDataServiceAsync;
    

    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  

    } 
    
    @Override
    public User selectUser(User user) {
    	return userDao.selectUser(user);
    }
    
    @Override
    public Pagination getPage(User user, int currentPage, int pageSize) {
    	Page<User> page = new Page<User>(currentPage, pageSize);
        List<User> list = userDao.getPage(page, user);
        Pagination p = new Pagination(currentPage, pageSize, page.getTotalCount());
        p.setList(list);
        return p;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(User user) {
    	//保存用户
    	int flag = userDao.add(user);
    	//保存用户角色
    	if(StringUtils.isNotEmpty(user.getRoleArr())){
			List<String> roleIds = Arrays.asList(user.getRoleArr().split(","));
			UserRole userRole = null;
			if(roleIds.size() > 0){
				for(int i=0;i<roleIds.size();i++){
					userRole = new UserRole();
					userRole.setUserId(user.getUserId());
					userRole.setRoleId(Integer.parseInt(roleIds.get(i)));
					userDao.addUserRole(userRole);
				}
			}
		}
    	return flag;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(User user) {
    	//修改用户
    	int flag = userDao.edit(user);
    	
    	//删除用户角色
    	userDao.delUserRole(user);
    	
    	//保存用户角色
    	if(StringUtils.isNotEmpty(user.getRoleArr())){
			List<String> roleIds = Arrays.asList(user.getRoleArr().split(","));
			UserRole userRole = null;
			if(roleIds.size() > 0){
				for(int i=0;i<roleIds.size();i++){
					userRole = new UserRole();
					userRole.setUserId(user.getUserId());
					userRole.setRoleId(Integer.parseInt(roleIds.get(i)));
					userDao.addUserRole(userRole);
				}
			}
		}
    	return flag;
    }
    
    @Override
    public List<Role> getUserRoleByUser(User user) {
    	return userDao.getUserRoleByUser(user);
    }
    
    
    @Override
    public User exportUser(User user) {
    	String exportSql = exportUserSql(user);
    	ExportDataVo exportDataVo = new ExportDataVo();
    	exportDataVo.setSearchSql(exportSql);
        exportDataVo.setFileName("用户数据" + DateUtils.getToday(DateUtils.MS_MIU_FORMAT) + ".csv");
        exportDataVo.setBatchNo(PrimaryGenerater.geneterNextNumber());// 批号
        exportDataVo.setReportProcess("1");// 待处理
        exportDataVo.setCreatedId(user.getCreateId().longValue());
        exportDataVo.setCreatedName(user.getCreateName());
        exportDataVo.setCreatedDate(new Date());
        if(exportDataDao.saveExportData(exportDataVo) > 0){
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("user_name", "用户名");
            map.put("user_password", "用户密码");
            map.put("nick_name", "用户昵称");
            map.put("phone", "电话");
            map.put("create_date", "创建时间");
            map.put("type", "用户类型");
            exportDataServiceAsync.exportReportCsv("缴费记录", exportDataVo, map);
        }
    	return user;
    }
    
    public String exportUserSql(User user){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT user_id,user_name,user_password,nick_name,phone,create_date,");
        sb.append("CASE WHEN type =1 THEN '运营人员'  ");
        sb.append(" WHEN type =2 THEN '客户' ");
        sb.append(" WHEN type =3  THEN '销售' END type ");
        sb.append(" FROM t_user");
        sb.append(" where 1=1 ");
        if(user != null){
            if(StringUtils.isNotBlank(user.getUserName())){
            	sb.append(" and user_name like concat('%','").append(user.getUserName()).append("','%')");
            }
            if(StringUtils.isNotBlank(user.getPhone())){
                sb.append(" and phone like concat('%','").append(user.getPhone()).append("','%')");
            }
            sb.append(" order by create_date desc ");
        }
        return sb.toString();
    }
}