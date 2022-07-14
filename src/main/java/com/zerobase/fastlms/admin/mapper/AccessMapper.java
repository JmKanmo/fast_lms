package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.dto.AccessDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccessMapper {
    List<AccessDto> accessList(String userId);
}
