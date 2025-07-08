package com.rest.rest.studentProfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private StudentProfileMapper studentProfileMapper;

    public StudentProfileResponseDto createStudentProfile(StudentProfileRequestDto stdProReqDto){
        return studentProfileMapper.toStudentProfileDto(studentProfileRepository.save(studentProfileMapper.toStudentProfile(stdProReqDto)));
    }

    public List<StudentProfileResponseDto> getAllStudentProfileList(){
        return studentProfileRepository.findAll().stream().map(studentProfileMapper::toStudentProfileDto).toList();
    }
}
