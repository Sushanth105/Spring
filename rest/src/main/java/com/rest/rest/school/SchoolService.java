package com.rest.rest.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolMapper schoolMapper;

    public SchoolResponseDto createSchool(SchoolRequestDto schoolRequestDto) {
        
        return schoolMapper.toSchoolDto(schoolRepository.save(schoolMapper.toSchool(schoolRequestDto)));
    }

    public List<SchoolResponseDto> getAllStudentList(){
        return schoolRepository.findAll().stream().map(schoolMapper::toSchoolDto).toList();
    }

    public void deleteSchoolById(int id){
        schoolRepository.deleteById(id);
    }
}
