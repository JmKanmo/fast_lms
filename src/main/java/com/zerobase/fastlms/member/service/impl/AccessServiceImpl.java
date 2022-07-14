package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.admin.dto.AccessDto;
import com.zerobase.fastlms.admin.mapper.AccessMapper;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.Access;
import com.zerobase.fastlms.member.model.AccessData;
import com.zerobase.fastlms.member.repository.AccessRepository;
import com.zerobase.fastlms.member.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {
    private final AccessRepository accessRepository;
    private final AccessMapper accessMapper;

    @Override
    public boolean saveAccessData(AccessData accessData) {
        try {
            accessRepository.save(Access.of(accessData));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<AccessDto> accessList(MemberParam memberParam) {
        List<AccessDto> accessDtoList = accessMapper.accessList(memberParam.getUserId());
        return accessDtoList;
    }
}
