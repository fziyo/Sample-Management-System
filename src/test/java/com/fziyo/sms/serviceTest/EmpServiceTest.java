package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.service.EmpService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmpServiceTest {
    @Autowired
    private EmpService empService;
    @MockitoBean
    private EmpMapper empMapper;
    @Test
    public void testSave() {
        EmpCreateDto empCreateDto = new EmpCreateDto();
        empCreateDto.setEmpNo("f84382");
        empCreateDto.setGender(1);
        empCreateDto.setName("Fuzi");
        empCreateDto.setTeamId(1);
        empCreateDto.setRoleId(1);
        empService.save(empCreateDto);
        
        ArgumentCaptor<Emp> captor = ArgumentCaptor.forClass(Emp.class);
        verify(empMapper).insert(captor.capture());
        Emp savedEmp = captor.getValue();
        assertEquals("f84382", savedEmp.getEmpNo());
        assertEquals("Fuzi", savedEmp.getName());
    }
    
    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(1,2,3);
        empService.deleteByIds(ids);
        verify(empMapper).deleteByIds(ids);
    }
    
    @Test
    void testUpdate() {
        EmpUpdateDto empUpdateDto = new EmpUpdateDto();
        empService.update(empUpdateDto);
        ArgumentCaptor<Emp> captor = ArgumentCaptor.forClass(Emp.class);
        verify(empMapper).update(captor.capture());
        
    }
    
    @Test
    void testGetById() {
        Emp emp = new Emp();
        emp.setId(1);
        
        when(empMapper.getById(1)).thenReturn(emp);
        
        empService.getById(1);
        
        verify(empMapper).getById(1);
    }
    
    @Test
    void testGetAll() {
        empService.getAll();
        verify(empMapper).list();
    }
}
